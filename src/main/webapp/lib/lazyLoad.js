/**
 * Created by L.H.S on 2017/8/14.
 */

/**
 * 实现懒加载功能的模块lazy,该模块依赖于ui.router
 * 1.用户在创建模块A时，首先应该加载lazyLoad.js文件，加载文件将自动生成"lazy"模块
 * 2.然后调用"lazy"模块的makeLazy方法使得模块A具有懒加载功能
 * 3.调用模块A的StateConfig方法进行状态配置
 * example：
 * var resourceModule = angular.module("A", dependency);
 * resourceModule = angular.module('lazy').makeLazy(resourceModule);
 * resourceModule.StateConfig(stateConfig);
 */

define(["ui-router/angular-ui-router"], function (router) {

    // 初始化懒加载模块
    var lazy = angular.module('lazy', ['ui.router']);

    /**
     * 1.给模块添加异步注册（可以在模块运行阶段）指令、控制器、服务等angular组件的方法
     * 2.给模块添加异步封装后的状态配置方法
     * @param {module} module AngularJS模块
     * @returns {module} 与传入的模块相同，但该模块已经具备了懒加载功能（API）
     */

    lazy.makeLazy = function (module) {
        // 在config中获取provider的引用(只能在config阶段获取),
        // 以便在模块运行阶段调用,从而实现懒加载
        module.config(function ($compileProvider, $filterProvider, $controllerProvider, $provide) {
            module.directive = lazy.register($compileProvider.directive);
            module.filter = lazy.register($filterProvider.register);
            module.controller = lazy.register($controllerProvider.register);
            module.provider = lazy.register($provide.provider);
            module.service = lazy.register($provide.service);
            module.factory = lazy.register($provide.factory);
            module.value = lazy.register($provide.value);
            module.constant = lazy.register($provide.constant);
        });

        // 对ui.router的状态配置方法$stateProvider.state做了进一步的封装，
        // 用户直接传入配置对象，即可完成状态的配置
        module.StateConfig = function (routerConfig) {
            if (!angular.isObject(routerConfig)) { // 传入的路由配置非法
                return;
            }

            module.config(["$stateProvider", "$urlRouterProvider", function ($stateProvider, $urlRouterProvider) {
                // 通过$stateProvider进行状态配置
                if (isConfigArrayLike(routerConfig.stateConfig)) {
                    var normalConfig = null;
                    // 对数组中的每一个元素，先进行解析，然后$stateProvider配置状态
                    angular.forEach(routerConfig.stateConfig, function (stateConfig, key) {
                        normalConfig = lazy.parseConfig(stateConfig);
                        $stateProvider.state(normalConfig);
                    });
                }

                // 通过$urlRouterProvider进行url路由配置
                if (isConfigArrayLike(routerConfig.urlMatch)) {
                    angular.forEach(routerConfig.urlMatch, function (urlMatch, key) {
                        if (urlMatch.length === 2) {
                            $urlRouterProvider.when(urlMatch[0], urlMatch[1]);
                        } else if (urlMatch.length === 1) {
                            $urlRouterProvider.otherwise(urlMatch[0]);
                        }
                    });
                }
            }]);

            /**
             * 判断用户传入配置是否是有效的数组
             * @param {Array} config配置数组
             * @returns {Boolean}
             标识配置是否为有效的数组
             */
            function isConfigArrayLike(config) {
                return angular.isArray(config) && config.length > 0;
            }

        };

        // 返回更新后的模块
        return module;
    };

    // 生成一个异步注册AngularJS组件的方法
    lazy.register = function (registrationMethod) {
        /**
         * @param {String} name 注册的AngularJS组件名称
         * @param {Array|function} constructor注册组件的构造函数
         * @returns {module} module
         返回对应的AngularJS模块
         */
        return function (name, constructor) {
            registrationMethod(name, constructor);
        };

    };

    // 解析用户传入的state配置，为了方便用户使用，对ui.router的state接口做了进一步封装
    // 用户只需传入某状态需要依赖的文件路径(scripts属性)，即可自动完成文件的异步加载
    lazy.parseConfig = function (stateConfig) {
        if (!stateConfig.scripts) {
            return stateConfig;
        }
        stateConfig.resolve = stateConfig.resolve || {};
        stateConfig.resolve.deps = function ($q, $rootScope) {
            return $q.all([
                load(stateConfig.scripts['directives'] || null),
                load(stateConfig.scripts['controllers'] || null),
                load(stateConfig.scripts['services'] || null),
                load(stateConfig.scripts['factories'] || null),
                load(stateConfig.scripts['js'] || null)
            ]);

            function load(url) {
                var deferred = $q.defer();
                // 如果没有需要加载的文件，则立即resolve
                if (url === null) {
                    deferred.resolve();
                    return deferred.promise;
                }

                // 加载文件
                require(url, function () {
                    $rootScope.$apply(function () {
                        deferred.resolve();
                    });
                });
                return deferred.promise;
            };
        };

        return stateConfig;
    };

    // 返回懒加载模块
    return lazy;
});
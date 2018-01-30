/**
 * Created by L.H.S on 2017/8/14.
 */

/**
 * 主框架module，该模块依赖于angularjs ng module和tiny wcc module
 * 所有的service router都在这里进行统一的配置
 */

define(['ui-router/angular-ui-router',
        'framework/backend/topbarCtrl',
        'backend/login/configures/loginRouterConfig',
        'backend/backend/configures/backendRouterConfig'
    ],
    function (router, topbarCtrl, loginRouterConfig, backendRouterConfig) {
        'use strict';

        // 注入框架的配置文件（新增业务模块在此处添加注册）
        var dependency = [
            'ngAnimate',
            'ngSanitize',
            'ui.bootstrap',
            'ui.router',
            loginRouterConfig.name,
            backendRouterConfig.name
        ];

        var framework_backend = angular.module('framework-backend', dependency);

        framework_backend.controller('topbarCtrl', topbarCtrl);

        framework_backend.run(function ($rootScope, $state) {
            $rootScope.$on('$stateChangeStart', function (event, toState) {

                // 判断是否登录
                $.ajax({
                    url: 'validate',
                    type: 'GET',
                    success: function (resp) {
                        if (resp == 'true' || resp == true) {
                            // 已登录
                            if (toState.name === "login") {
                                $state.go('backend.abroadCountry');
                            }

                        } else {
                            // 未登录
                            event.preventDefault();
                            $state.transitionTo("login", null, {notify: false});
                            $state.go('login');
                        }
                    },
                    error: function (err) {
                        console.log(err);
                    }
                });
            });
        });

        // 初始跳转至首页模块
        framework_backend.config(['$urlRouterProvider', function ($urlRouterProvider) {
            $urlRouterProvider.otherwise('/login');
        }]);

        return framework_backend;
    });

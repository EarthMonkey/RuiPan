/**
 * Created by L.H.S on 2017/8/14.
 */

/**
 * 主框架module，该模块依赖于angularjs ng module和tiny wcc module
 * 所有的service router都在这里进行统一的配置
 */

define(['ui-router/angular-ui-router',
        'framework/topnav/topnavCtrl',
        'framework/topnav/navListService',
        'framework/footer/footerCtrl',
        'business/home/configures/homeRouterConfig',
        'business/abroad/configures/abroadRouterConfig',
        'business/introduction/configures/introRouterConfig',
        'business/rank/configures/rankRouterConfig',
        'business/success/configures/successRouterConfig',
        'business/promotion/configures/promotionRouterConfig',
        'business/language/configures/langRouterConfig',
        'business/cooperation/configures/coopRouterConfig',
        'business/about/configures/aboutRouterConfig'
    ],
    function (router, topnavCtrl, navListService, footerCtrl,
              homeRouterConfig, abroadRouterConfig, introRouterConfig, rankRouterConfig, successRouterConfig,
              promotionRouterConfig, langRouterConfig, coopRouterConfig, aboutRouterConfig) {
        'use strict';

        // 注入框架的配置文件（新增业务模块在此处添加注册）
        var dependency = [
            'ngAnimate',
            'ngSanitize',
            'ui.bootstrap',
            'ui.router',
            homeRouterConfig.name,
            abroadRouterConfig.name,
            introRouterConfig.name,
            rankRouterConfig.name,
            successRouterConfig.name,
            promotionRouterConfig.name,
            langRouterConfig.name,
            coopRouterConfig.name,
            aboutRouterConfig.name
        ];

        var framework = angular.module('framework', dependency);

        framework.controller('topnavCtrl', topnavCtrl); // 顶部菜单控制器
        framework.service('navListService', navListService); // 菜单列表数据服务

        framework.controller('footerCtrl', footerCtrl); // 底部footer控制器

        // 初始跳转至首页模块
        framework.config(['$urlRouterProvider', function ($urlRouterProvider) {
            $urlRouterProvider.otherwise('/home');
        }]);

        return framework;
    });

/**
 * Created by L.H.S on 2017/8/14.
 */

define(['lazy-load/lazyLoad'],

    function (lazyLoadModule) {
        'use strict';

        var configArr = [{
            name: 'application',
            url: '/application',
            templateUrl: 'business/application/views/application.html',
            controller: 'application.ctrl',
            scripts: {
                controllers: ['business/application/controllers/appCtrl']
            }
        }];

        var appModule = angular.module('application.config', ['ui.router']);
        appModule = lazyLoadModule.makeLazy(appModule);
        // stateConfig属性配置路由状态基本信息；urlMatch属性配置异常url对应的url路径
        appModule.StateConfig({
            stateConfig: configArr,
            urlMatch: [['/application/', '/application'], ['/application', '/application']]
        });

        return appModule;
    });
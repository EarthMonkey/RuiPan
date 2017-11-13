/**
 * Created by L.H.S on 2017/11/13.
 */

define(['lazy-load/lazyLoad'],

    function (lazyLoadModule) {
        'use strict';

        var configArr = [{
            name: 'login',
            url: '/login',
            templateUrl: 'backend/login/views/login.html',
            controller: 'loginCtrl',
            scripts: {
                controllers: ['backend/login/controllers/loginCtrl']
            }
        }];

        var loginModule = angular.module('login.config', ['ui.router']);
        loginModule = lazyLoadModule.makeLazy(loginModule);
        // stateConfig属性配置路由状态基本信息；urlMatch属性配置异常url对应的url路径
        loginModule.StateConfig({
            stateConfig: configArr,
            urlMatch: [['/login/', '/login'], ['/login', '/login']]
        });

        return loginModule;
    });
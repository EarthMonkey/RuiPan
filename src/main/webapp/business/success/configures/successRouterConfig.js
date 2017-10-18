/**
 * Created by L.H.S on 2017/8/14.
 * 留学
 */

define(['lazy-load/lazyLoad'],

    function (lazyLoadModule) {
        'use strict';

        var configArr = [{
            name: 'success',
            url: '/success',
            templateUrl: 'business/success/views/success.html',
            controller: 'successCtrl',
            scripts: {
                controllers: ['business/success/controllers/successCtrl']
            }
        }];

        var successModule = angular.module('success.config', ['ui.router']);
        successModule = lazyLoadModule.makeLazy(successModule);
        // stateConfig属性配置路由状态基本信息；urlMatch属性配置异常url对应的url路径
        successModule.StateConfig({
            stateConfig: configArr,
            urlMatch: [['/success/', '/success'], ['/success', '/success']]
        });

        return successModule;
    });
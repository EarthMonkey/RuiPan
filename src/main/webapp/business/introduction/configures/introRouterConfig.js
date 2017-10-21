/**
 * Created by L.H.S on 2017/8/14.
 * 留学
 */

define(['lazy-load/lazyLoad'],

    function (lazyLoadModule) {
        'use strict';

        var configArr = [{
            name: 'introduction',
            url: '/introduction/:country/:type',
            templateUrl: 'business/introduction/views/introduction.html',
            controller: 'introCtrl',
            scripts: {
                controllers: ['business/introduction/controllers/introCtrl']
            }
        }];

        var introModule = angular.module('intro.config', ['ui.router']);
        introModule = lazyLoadModule.makeLazy(introModule);
        // stateConfig属性配置路由状态基本信息；urlMatch属性配置异常url对应的url路径
        introModule.StateConfig({
            stateConfig: configArr,
            urlMatch: [['/introduction/', '/introduction'], ['/introduction', '/introduction']]
        });

        return introModule;
    });
/**
 * Created by L.H.S on 2017/8/14.
 * 留学
 */

define(['lazy-load/lazyLoad'],

    function (lazyLoadModule) {
        'use strict';

        var configArr = [{
            name: 'abroad',
            url: '/abroad',
            templateUrl: 'business/abroad/views/abroad.html',
            controller: 'abroad.ctrl',
            scripts: {
                controllers: ['business/abroad/controllers/abroadCtrl']
            }
        }];

        var abroadModule = angular.module('abroad.config', ['ui.router']);
        abroadModule = lazyLoadModule.makeLazy(abroadModule);
        // stateConfig属性配置路由状态基本信息；urlMatch属性配置异常url对应的url路径
        abroadModule.StateConfig({
            stateConfig: configArr,
            urlMatch: [['/abroad/', '/abroad'], ['/abroad', '/abroad']]
        });

        return abroadModule;
    });
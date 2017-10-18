/**
 * Created by L.H.S on 2017/8/14.
 * 留学
 */

define(['lazy-load/lazyLoad'],

    function (lazyLoadModule) {
        'use strict';

        var configArr = [{
            name: 'about',
            url: '/about',
            templateUrl: 'business/about/views/about.html',
            controller: 'aboutCtrl',
            scripts: {
                controllers: ['business/about/controllers/aboutCtrl']
            }
        }];

        var aboutModule = angular.module('about.config', ['ui.router']);
        aboutModule = lazyLoadModule.makeLazy(aboutModule);
        // stateConfig属性配置路由状态基本信息；urlMatch属性配置异常url对应的url路径
        aboutModule.StateConfig({
            stateConfig: configArr,
            urlMatch: [['/about/', '/about'], ['/about', '/about']]
        });

        return aboutModule;
    });
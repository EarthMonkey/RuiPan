/**
 * Created by L.H.S on 2017/8/14.
 * 留学
 */

define(['lazy-load/lazyLoad'],

    function (lazyLoadModule) {
        'use strict';

        var configArr = [{
            name: 'language',
            url: '/language/:type',
            templateUrl: 'business/language/views/lang.html',
            controller: 'langCtrl',
            scripts: {
                controllers: ['business/language/controllers/langCtrl']
            }
        }];

        var langModule = angular.module('lang.config', ['ui.router']);
        langModule = lazyLoadModule.makeLazy(langModule);
        // stateConfig属性配置路由状态基本信息；urlMatch属性配置异常url对应的url路径
        langModule.StateConfig({
            stateConfig: configArr,
            urlMatch: [['/language/', '/language'], ['/language', '/language']]
        });

        return langModule;
    });
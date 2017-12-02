/**
 * Created by L.H.S on 2017/8/14.
 * 留学
 */

define(['lazy-load/lazyLoad'],

    function (lazyLoadModule) {
        'use strict';

        var configArr = [{
            name: 'cooperation',
            url: '/cooperation',
            templateUrl: 'business/cooperation/views/coop.html',
            controller: 'coopCtrl',
            scripts: {
                controllers: ['business/cooperation/controllers/coopCtrl']
            }
        }, {
            name: 'cooperationDetail',
            url: '/cooperation-detail/:id',
            templateUrl: 'business/cooperation/views/cooperation-detail.html',
            controller: 'detailCtrl',
            scripts: {
                controllers: ['business/cooperation/controllers/detailCtrl']
            }
        }, {
            name: 'cooperationNews',
            url: '/cooperation-news/:id',
            templateUrl: 'business/cooperation/views/cooperation-news.html',
            controller: 'newsCtrl',
            scripts: {
                controllers: ['business/cooperation/controllers/newsCtrl']
            }
        }];

        var coopModule = angular.module('coop.config', ['ui.router']);
        coopModule = lazyLoadModule.makeLazy(coopModule);
        // stateConfig属性配置路由状态基本信息；urlMatch属性配置异常url对应的url路径
        coopModule.StateConfig({
            stateConfig: configArr,
            urlMatch: [['/cooperation/', '/cooperation'], ['/cooperation', '/cooperation']]
        });

        return coopModule;
    });
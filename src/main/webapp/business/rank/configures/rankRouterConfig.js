/**
 * Created by L.H.S on 2017/8/14.
 * 留学
 */

define(['lazy-load/lazyLoad'],

    function (lazyLoadModule) {
        'use strict';

        var configArr = [{
            name: 'rank',
            url: '/rank/:country',
            templateUrl: 'business/rank/views/rank.html',
            controller: 'rankCtrl',
            scripts: {
                controllers: ['business/rank/controllers/rankCtrl']
            }
        }];

        var rankModule = angular.module('rank.config', ['ui.router']);
        rankModule = lazyLoadModule.makeLazy(rankModule);
        // stateConfig属性配置路由状态基本信息；urlMatch属性配置异常url对应的url路径
        rankModule.StateConfig({
            stateConfig: configArr,
            urlMatch: [['/rank/', '/rank'], ['/rank', '/rank']]
        });

        return rankModule;
    });
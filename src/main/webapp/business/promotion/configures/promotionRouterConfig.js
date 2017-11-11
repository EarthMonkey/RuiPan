/**
 * Created by L.H.S on 2017/8/14.
 * 留学
 */

define(['lazy-load/lazyLoad'],

    function (lazyLoadModule) {
        'use strict';

        var configArr = [{
            name: 'promotion',
            url: '/promotion',
            templateUrl: 'business/promotion/views/promotion.html',
            controller: 'promotionCtrl',
            scripts: {
                controllers: ['business/promotion/controllers/promotionCtrl']
            }
        }, {
            name: 'promotionDetail',
            url: '/promotion-detail/:id',
            templateUrl: 'business/promotion/views/promotion-detail.html',
            controller: 'detailCtrl',
            scripts: {
                controllers: ['business/promotion/controllers/detailCtrl']
            }
        }];

        var promotionModule = angular.module('promotion.config', ['ui.router']);
        promotionModule = lazyLoadModule.makeLazy(promotionModule);
        // stateConfig属性配置路由状态基本信息；urlMatch属性配置异常url对应的url路径
        promotionModule.StateConfig({
            stateConfig: configArr,
            urlMatch: [['/promotion/', '/promotion'], ['/promotion', '/promotion']]
        });

        return promotionModule;
    });
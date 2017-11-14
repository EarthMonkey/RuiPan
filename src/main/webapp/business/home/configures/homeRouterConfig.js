/**
 * Created by L.H.S on 2017/8/14.
 */

define(['lazy-load/lazyLoad'],

    function (lazyLoadModule) {
        'use strict';

        var configArr = [{
            name: 'home',
            url: '/home',
            templateUrl: 'business/home/views/home.html',
            controller: 'homeCtrl',
            scripts: {
                controllers: ['business/home/controllers/homeCtrl']
            }
        }];

        var homeModule = angular.module('home.config', ['ui.router']);
        homeModule = lazyLoadModule.makeLazy(homeModule);
        // stateConfig属性配置路由状态基本信息；urlMatch属性配置异常url对应的url路径
        homeModule.StateConfig({
            stateConfig: configArr,
            urlMatch: [['/home/', '/home'], ['/home', '/home']]
        });

        return homeModule;
    });
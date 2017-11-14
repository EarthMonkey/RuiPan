/**
 * Created by L.H.S on 2017/11/13.
 */

define(['lazy-load/lazyLoad'],

    function (lazyLoadModule) {
        'use strict';

        var configArr = [{
            name: 'backend',
            url: '/backend',
            templateUrl: 'backend/backend/views/leftBar.html',
            controller: 'leftBarCtrl',
            scripts: {
                controllers: ['backend/backend/controllers/leftBarCtrl'],
                services: ['backend/backend/services/leftBarService']
            }
        }, {
            name: 'backend.abroadCountry',
            url: '/abroad-country',
            templateUrl: 'backend/backend/views/abroad/country.html',
            controller: 'abroadCountryCtrl',
            scripts: {
                controllers: ['backend/backend/controllers/abroad/abroadCountryCtrl']
            }
        }];

        var backendModule = angular.module('backend.config', ['ui.router']);
        backendModule = lazyLoadModule.makeLazy(backendModule);
        // stateConfig属性配置路由状态基本信息；urlMatch属性配置异常url对应的url路径
        backendModule.StateConfig({
            stateConfig: configArr,
            urlMatch: [['/backend/', '/backend'], ['/backend', '/backend']]
        });

        return backendModule;
    });
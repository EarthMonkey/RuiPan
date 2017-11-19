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
                controllers: [
                    'backend/backend/controllers/leftBarCtrl',
                    'backend/backend/controllers/modals/textFormCtrl',
                    'backend/backend/controllers/modals/editorCtrl',
                    'backend/backend/controllers/modals/messageCtrl'
                ],
                services: [
                    'backend/backend/services/leftBarService',
                    'backend/backend/services/commonService'
                ]
            }
        }, {
            name: 'backend.abroadCountry',
            url: '/abroad-country',
            templateUrl: 'backend/backend/views/abroadCountry.html',
            controller: 'abroadCountryCtrl',
            scripts: {
                controllers: ['backend/backend/controllers/abroadCountryCtrl']
            }
        }, {
            name: 'backend.abroadMethod',
            url: '/abroad-method',
            templateUrl: 'backend/backend/views/abroadMethod.html',
            controller: 'abroadMethodCtrl',
            scripts: {
                controllers: ['backend/backend/controllers/abroadMethodCtrl']
            }
        }, {
            name: 'backend.abroadService',
            url: '/abroad-service',
            templateUrl: 'backend/backend/views/abroadService.html',
            controller: 'abroadServiceCtrl',
            scripts: {
                controllers: ['backend/backend/controllers/abroadServiceCtrl']
            }
        }, {
            name: 'backend.about',
            url: '/about',
            templateUrl: 'backend/backend/views/about.html',
            controller: 'aboutCtrl',
            scripts: {
                controllers: ['backend/backend/controllers/aboutCtrl']
            }
        }, {
            name: 'backend.consult',
            url: '/consult',
            templateUrl: 'backend/backend/views/consult.html',
            controller: 'consultCtrl',
            scripts: {
                controllers: ['backend/backend/controllers/consultCtrl']
            }
        }, {
            name: 'backend.cooperation',
            url: '/cooperation',
            templateUrl: 'backend/backend/views/cooperation.html',
            controller: 'cooperationCtrl',
            scripts: {
                controllers: ['backend/backend/controllers/cooperationCtrl']
            }
        }, {
            name: 'backend.home',
            url: '/home',
            templateUrl: 'backend/backend/views/home.html',
            controller: 'homeCtrl',
            scripts: {
                controllers: ['backend/backend/controllers/homeCtrl']
            }
        }, {
            name: 'backend.language',
            url: '/language',
            templateUrl: 'backend/backend/views/language.html',
            controller: 'languageCtrl',
            scripts: {
                controllers: ['backend/backend/controllers/languageCtrl']
            }
        }, {
            name: 'backend.promotion',
            url: '/promotion',
            templateUrl: 'backend/backend/views/promotion.html',
            controller: 'promotionCtrl',
            scripts: {
                controllers: ['backend/backend/controllers/promotionCtrl']
            }
        }, {
            name: 'backend.schoolInfo',
            url: '/school-info',
            templateUrl: 'backend/backend/views/schoolInfo.html',
            controller: 'schoolInfoCtrl',
            scripts: {
                controllers: ['backend/backend/controllers/schoolInfoCtrl']
            }
        }, {
            name: 'backend.schoolRank',
            url: '/school-rank',
            templateUrl: 'backend/backend/views/schoolRank.html',
            controller: 'schoolRankCtrl',
            scripts: {
                controllers: ['backend/backend/controllers/schoolRankCtrl']
            }
        }, {
            name: 'backend.specialClass',
            url: '/special-class',
            templateUrl: 'backend/backend/views/specialClass.html',
            controller: 'specialClassCtrl',
            scripts: {
                controllers: ['backend/backend/controllers/specialClassCtrl']
            }
        }, {
            name: 'backend.specialInfo',
            url: '/special-info',
            templateUrl: 'backend/backend/views/specialInfo.html',
            controller: 'specialInfoCtrl',
            scripts: {
                controllers: ['backend/backend/controllers/specialInfoCtrl']
            }
        }, {
            name: 'backend.success',
            url: '/success',
            templateUrl: 'backend/backend/views/success.html',
            controller: 'successCtrl',
            scripts: {
                controllers: ['backend/backend/controllers/successCtrl']
            }
        }, {
            name: 'backend.user',
            url: '/user',
            templateUrl: 'backend/backend/views/user.html',
            controller: 'userCtrl',
            scripts: {
                controllers: ['backend/backend/controllers/userCtrl']
            }
        }, {
            name: 'backend.article',
            url: '/article?initInfo',
            templateUrl: 'backend/backend/views/modals/article.html',
            controller: 'articleCtrl',
            scripts: {
                controllers: ['backend/backend/controllers/modals/articleCtrl']
            }
        }];

        var backendModule = angular.module('backend.config', ['ui.router', "ng.ueditor"]);
        backendModule = lazyLoadModule.makeLazy(backendModule);
        // stateConfig属性配置路由状态基本信息；urlMatch属性配置异常url对应的url路径
        backendModule.StateConfig({
            stateConfig: configArr,
            urlMatch: [['/backend/', '/backend'], ['/backend', '/backend']]
        });

        return backendModule;
    });
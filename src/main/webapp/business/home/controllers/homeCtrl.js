/**
 * Created by L.H.S on 2017/8/14.
 */

define([''], function () {
    'use strict';

    var homeCtrl = ['$scope', function ($scope) {

            // 轮播图
            $scope.myInterval = 5000;
            $scope.noWrapSlides = false;
            $scope.active = 0;
            $scope.slides = [{
                image: '/theme/source/homepage-bg.png',
                title: '十三年如一日',
                text: "探索在中国涉外教育服务的道路上",
                id: 0
            }, {
                image: '/theme/source/promotion-0.png',
                title: '十三年如一日',
                text: "探索在中国涉外教育服务的道路上",
                id: 1
            }];

            // country tab
            $scope.countryTab = [
                {title: '美国', show: true},
                {title: '英国'},
                {title: '澳洲'},
                {title: '加拿大'}
            ];

            var LAST_TAB = $scope.countryTab[0];
            $scope.changeTab = function (tab) {
                tab.show = true;
                LAST_TAB.show = false;
                LAST_TAB = tab;
            };

        }

    ];

    var homeModule = angular.module('home.config');
    homeModule.controller('homeCtrl', homeCtrl);

});
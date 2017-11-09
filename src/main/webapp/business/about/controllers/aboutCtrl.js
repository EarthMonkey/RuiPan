/**
 * Created by L.H.S on 2017/8/14.
 */

define([''], function () {
    'use strict';

    var aboutCtrl = ['$scope', function ($scope) {

        // 轮播图
        $scope.myInterval = 5000;
        $scope.noWrapSlides = false;
        $scope.active = 0;
        $scope.slides = [{
            image: '/theme/source/homepage-bg.png',
            title: '瑞泮留学',
            text: "探索在中国涉外教育服务的道路上",
            id: 0
        }, {
            image: '/theme/source/promotion-0.png',
            title: '瑞泮留学',
            text: "探索在中国涉外教育服务的道路上",
            id: 1
        }];

    }];

    var homeModule = angular.module('about.config');
    homeModule.controller('aboutCtrl', aboutCtrl);

});
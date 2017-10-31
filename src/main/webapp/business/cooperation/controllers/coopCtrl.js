/**
 * Created by L.H.S on 2017/8/14.
 */

define([''], function () {
    'use strict';

    var coopCtrl = ['$scope', function ($scope) {

        // 轮播图
        $scope.myInterval = 3000;
        $scope.noWrapSlides = false;
        $scope.active = 0;
        $scope.slides = [];
        for (var i = 0; i < 3; i++) {
            $scope.slides.push({
                image: '/theme/source/promotion-' + i + '.png',
                text: 'This is a title',
                id: i
            });
        }

    }];

    var homeModule = angular.module('coop.config');
    homeModule.controller('coopCtrl', coopCtrl);

});
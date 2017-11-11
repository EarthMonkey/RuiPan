/**
 * Created by L.H.S on 2017/8/14.
 */

define([''], function () {
    'use strict';

    var langCtrl = ['$scope', '$state', function ($scope, $state) {

        var langType = $state.params.type;
        console.log(langType);

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

    var homeModule = angular.module('lang.config');
    homeModule.controller('langCtrl', langCtrl);

});
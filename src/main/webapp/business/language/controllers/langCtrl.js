/**
 * Created by L.H.S on 2017/8/14.
 */

define([''], function () {
    'use strict';

    var langCtrl = ['$scope', function ($scope) {

        $scope.test = "语言培训";

        $scope.filterType = [
            {id: 'tofel', label: '托福'},
            {id: 'ielts', label: '雅思'},
            {id: 'other', label: '其他'}
        ];
        $scope.selectedType = $scope.filterType[0];

        $scope.filterClick = function (filter) {
            $scope.selectedType = filter;
        };

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
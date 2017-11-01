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

        // 高中
        $scope.highTab = [
            {title: '中英', show: true},
            {title: '中美'}
        ];
        /** 分页 */
        $scope.totalItems_high = 41;  // 此处为每页10条
        $scope.currentPage_high = 1;
        $scope.pageChanged_high = function () {
            console.log($scope.currentPage_high);
        };

        // 职业
        $scope.careerTab = [
            {title: '中英', show: true},
            {title: '中美'},
            {title: '中加'},
            {title: '中德'},
            {title: '中澳'}
        ];
        /** 分页 */
        $scope.totalItems_career = 41;  // 此处为每页10条
        $scope.currentPage_career = 1;
        $scope.pageChanged_career = function () {
            console.log($scope.currentPage_career);
        };

        // 大学
        $scope.collegeTab = [
            {title: '中英', show: true},
            {title: '中美'},
            {title: '中加'},
            {title: '中德'},
            {title: '中澳'}
        ];
        /** 分页 */
        $scope.totalItems_college = 41;  // 此处为每页10条
        $scope.currentPage_college = 1;
        $scope.pageChanged_college = function () {
            console.log($scope.currentPage_college);
        };

        var LAST_TAB = [$scope.highTab[0], $scope.careerTab[0], $scope.collegeTab[0]];
        $scope.changeTab = function (tab, syb) {
            tab.show = true;
            LAST_TAB[syb].show = false;
            LAST_TAB[syb] = tab;
        }
    }];

    var homeModule = angular.module('coop.config');
    homeModule.controller('coopCtrl', coopCtrl);

});
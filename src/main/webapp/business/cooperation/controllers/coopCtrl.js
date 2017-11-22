/**
 * Created by L.H.S on 2017/8/14.
 */

define([''], function () {
    'use strict';

    var coopCtrl = ['$scope', '$state', '$timeout', function ($scope, $state, $timeout) {

        // 轮播图
        $.ajax({
            url: '/homepage/getCarouselFigure?category=合作办学',
            type: 'GET',
            success: function (resp) {
                var temp = [];
                resp.forEach(function (item, i) {
                    temp.push({
                        img: 'upload/images/' + item.imagePath,
                        title: item.title,
                        text: item.subTitle,
                        id: i
                    });
                });
                $timeout(function () {
                    $scope.slides = temp;
                });
            },
            error: function (err) {
                console.log(err);
            }
        });

        // 轮播图
        $scope.myInterval = 3000;
        $scope.noWrapSlides = false;
        $scope.active = 0;
        $scope.slides = [];

        // 资讯
        $.ajax({
            url: '/News/getLatestNewsByCategory?category=合作办学',
            type: 'GET',
            success: function (resp) {
                $scope.newsList = resp;
            },
            error: function (err) {
                console.log(err);
            }
        });

        // 高中
        $scope.highTab = [];
        $.ajax({
            url: '/CooperativeEducation/getSubclassificationByCategory?category=高中',
            type: 'GET',
            success: function (resp) {
                $scope.highTab = resp;
                if (resp[0]) {
                    resp[0].show = true;
                    LAST_TAB[0] = resp[0];
                    getData(0);
                }
            },
            error: function (err) {
                console.log(err);
            }
        });

        /** 分页 */
        $scope.totalItems_high = 41;  // 此处为每页10条
        $scope.currentPage_high = 1;
        $scope.pageChanged_high = function () {
            console.log($scope.currentPage_high);
        };

        // 职业
        $scope.careerTab = [];
        $.ajax({
            url: '/CooperativeEducation/getSubclassificationByCategory?category=职业学院',
            type: 'GET',
            success: function (resp) {
                $scope.careerTab = resp;
                if (resp[0]) {
                    resp[0].show = true;
                    LAST_TAB[1] = resp[0];
                    getData(1);
                }
            },
            error: function (err) {
                console.log(err);
            }
        });

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
        $.ajax({
            url: '/CooperativeEducation/getSubclassificationByCategory?category=大学',
            type: 'GET',
            success: function (resp) {
                $scope.collegeTab = resp;
                if (resp[0]) {
                    resp[0].show = true;
                    LAST_TAB[2] = resp[0];
                    getData(2);
                }
            },
            error: function (err) {
                console.log(err);
            }
        });

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
            getData(syb);
        };

        function getData(syb) {
            if (!LAST_TAB[syb].id) {
                return;
            }

            $.ajax({
                url: '/CooperativeEducation/getCooperativeSchemeByCcid?ccid=' + LAST_TAB[syb].id,
                type: 'GET',
                success: function (resp) {
                    console.log(resp);
                    if (syb === 0) {
                        $timeout(function () {
                            $scope.highList = resp;
                        });
                    } else if (syb === 1) {
                        $timeout(function () {
                            $scope.careerList = resp;
                        });
                    } else {
                        $timeout(function () {
                            $scope.colleList = resp;
                        });
                    }
                },
                error: function (err) {
                    console.log(err);
                }
            });
        }
    }];

    var homeModule = angular.module('coop.config');
    homeModule.controller('coopCtrl', coopCtrl);

});
/**
 * Created by L.H.S on 2017/8/14.
 */

define([''], function () {
    'use strict';

    var homeCtrl = ['$scope', '$state', '$timeout', function ($scope, $state, $timeout) {

        // 轮播图
        $.ajax({
            url: 'homepage/getCarouselFigure?category=首页',
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
        $scope.myInterval = 5000;
        $scope.noWrapSlides = false;
        $scope.active = 0;
        $scope.slides = [];

        // country tab
        $scope.countryTab = [];

        var LAST_TAB = $scope.countryTab[0];
        $scope.changeTab = function (tab) {
            tab.show = true;
            LAST_TAB.show = false;
            LAST_TAB = tab;
        };

        // 成功案例
        $.ajax({
            url: 'SuccessfulCase/getSuccessfulCaseGroupByCountry?limit=5',
            type: 'GET',
            success: function (resp) {
                var keys = Object.keys(resp);
                $timeout(function () {
                    keys.forEach(function (key) {
                        $scope.countryTab.push({
                            title: key,
                            cases: resp[key]
                        });
                    });
                    $scope.countryTab[0].show = true;
                });
            },
            error: function (err) {
                console.log(err);
                console.log('fail to get success');
            }
        });

        $scope.getSucDetail = function (id) {
            $state.go("successDetail", {sucId: id});
        };

        // 获取推荐顾问
        $.ajax({
            url: 'Consultant/getRecommendConsultant',
            type: 'GET',
            success: function (resp) {
                resp.splice(5);
                $timeout(function () {
                    $scope.consultants = resp;
                });
            },
            error: function (err) {
                console.log(err);
            }
        });

        // 服务的学校和企业
        $.ajax({
            url: 'homepage/getServedCompany',
            type: 'GET',
            success: function (resp) {
                $timeout(function () {
                    $scope.coopList = resp;
                });
            },
            error: function (err) {
                console.log(err);
            }
        });

        // 荣誉资质
        $scope.honours = [];
        $.ajax({
            url: 'homepage/getHonor',
            type: 'GET',
            success: function (resp) {
                $timeout(function () {
                    $scope.honours = resp;
                });
            },
            error: function (err) {
                console.log(err);
            }
        });

        // 获取顾问详情
        $scope.getConsultDetail = function (item) {
            $state.go("consultant", {conId: item.id});
        };
    }];

    var homeModule = angular.module('home.config');
    homeModule.controller('homeCtrl', homeCtrl);

});
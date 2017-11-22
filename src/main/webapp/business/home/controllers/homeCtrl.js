/**
 * Created by L.H.S on 2017/8/14.
 */

define([''], function () {
    'use strict';

    var homeCtrl = ['$scope', '$state', '$timeout', function ($scope, $state, $timeout) {

        // 轮播图
        $.ajax({
            url: '/homepage/getCarouselFigure?category=首页',
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

        // 获取推荐顾问
        $.ajax({
            url: '/Consultant/getRecommendConsultant',
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

        // 获取合作学校
        $.ajax({
            url: '/AboutUs/getCooperativePartner',
            type: 'GET',
            success: function (resp) {
                $scope.coopList = resp;
            },
            error: function (err) {
                console.log(err);
            }
        });
    }];

    var homeModule = angular.module('home.config');
    homeModule.controller('homeCtrl', homeCtrl);

});
/**
 * Created by L.H.S on 2017/8/14.
 */

define([''], function () {
    'use strict';

    var promotionCtrl = ['$scope', '$state', '$timeout', function ($scope, $state, $timeout) {


        // 获取轮播图
        $.ajax({
            url: '/homepage/getCarouselFigure?category=背景提升',
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

        // 左侧导航栏
        $scope.leftBar = [
            {label: '游学', link: '#tour'},
            {label: '实习', link: '#practice'},
            {label: '科研', link: '#research'},
            {label: '国际义工', link: '#volunteer'},
            {label: '短期项目', link: '#project'}
        ];

        $scope.activeBar = "#tour";
        $scope.barClick = function (bar) {
            $scope.activeBar = bar.link;
            // 缓动
            var pos = angular.element(bar.link)[0].offsetTop;
            $('html,body').animate({scrollTop: pos}, 500);
        };

        $(window).scroll(function (e) {
            var wst = $(window).scrollTop();
            for (var i = 0; i < $scope.leftBar.length; i++) {
                if (wst > 1043) {
                    $('.left_bar').addClass('left_bar_fixed');
                } else {
                    $('.left_bar').removeClass('left_bar_fixed');
                }
            }
        });

        // 获取项目
        $.ajax({
            url: '/Promotion/getBackgroundPromoteByCategory?category=游学',
            type: 'GET',
            success: function (resp) {
                console.log(resp);
                $scope.tourList = resp;
            },
            error: function (err) {
                console.log(err);
            }
        });

        // 获取项目
        $.ajax({
            url: '/Promotion/getBackgroundPromoteByCategory?category=实习',
            type: 'GET',
            success: function (resp) {
                console.log(resp);
                $scope.practiceList = resp;
            },
            error: function (err) {
                console.log(err);
            }
        });

        // 获取项目
        $.ajax({
            url: '/Promotion/getBackgroundPromoteByCategory?category=科研',
            type: 'GET',
            success: function (resp) {
                console.log(resp);
                $scope.researchList = resp;
            },
            error: function (err) {
                console.log(err);
            }
        });

        // 获取项目
        $.ajax({
            url: '/Promotion/getBackgroundPromoteByCategory?category=国际义工',
            type: 'GET',
            success: function (resp) {
                console.log(resp);
                $scope.volunList = resp;
            },
            error: function (err) {
                console.log(err);
            }
        });

        // 获取项目
        $.ajax({
            url: '/Promotion/getBackgroundPromoteByCategory?category=短期项目',
            type: 'GET',
            success: function (resp) {
                console.log(resp);
                $scope.proList = resp;
            },
            error: function (err) {
                console.log(err);
            }
        });

        // 详情
        $scope.getDetail = function () {

            $state.go('promotionDetail', {id: 'test'});
        };

        // 获取三个推荐方案
        $.ajax({
            url: '/StudyAbroad/getRecommendApplicationScheme',
            type: 'GET',
            success: function (resp) {
                $timeout(function () {
                    $scope.recomendList = resp;
                });
            },
            error: function (err) {
                console.log(err);
            }
        });
    }];

    var homeModule = angular.module('promotion.config');
    homeModule.controller('promotionCtrl', promotionCtrl);

});
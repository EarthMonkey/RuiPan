/**
 * Created by L.H.S on 2017/8/14.
 */

define([''], function () {
    'use strict';

    var promotionCtrl = ['$scope', '$state', function ($scope, $state) {

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
                if (wst > 865) {
                    $('.left_bar').addClass('left_bar_fixed');
                } else {
                    $('.left_bar').removeClass('left_bar_fixed');
                }
            }
        });

        // 详情
        $scope.getDetail = function () {

            $state.go('promotionDetail', {id: 'test'});
        }

    }];

    var homeModule = angular.module('promotion.config');
    homeModule.controller('promotionCtrl', promotionCtrl);

});
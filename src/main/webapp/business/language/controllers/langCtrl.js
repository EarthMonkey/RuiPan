/**
 * Created by L.H.S on 2017/8/14.
 */

define([''], function () {
    'use strict';

    var langCtrl = ['$scope', '$state', '$timeout', function ($scope, $state, $timeout) {

        var langType = $state.params.type;

        // 轮播图
        $.ajax({
            url: '/homepage/getCarouselFigure?category=' + langType,
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

        // 资讯
        $.ajax({
            url: '/News/getLatestNewsByCategory?category=' + langType,
            type: 'GET',
            success: function (resp) {
                $scope.newsList = resp;
            },
            error: function (err) {
                console.log(err);
            }
        });

        // 介绍信息
        $.ajax({
            url: '/LanguageTraining/getTrainIntroducePublish?category=' + langType,
            type: 'GET',
            success: function (resp) {
                console.log(resp);
                $scope.introList = resp;
                resp.forEach(function (item) {
                    $.ajax({
                        url: '/getText?path=' + item.textPath,
                        type: 'GET',
                        success: function (resp) {
                            console.log(resp);
                            item.content = resp;
                        },
                        error: function (err) {
                            console.log(err);
                        }
                    });
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


    }];

    var homeModule = angular.module('lang.config');
    homeModule.controller('langCtrl', langCtrl);

});
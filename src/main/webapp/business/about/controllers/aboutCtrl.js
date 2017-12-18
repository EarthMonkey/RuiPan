/**
 * Created by L.H.S on 2017/8/14.
 */

define([''], function () {
    'use strict';

    var aboutCtrl = ['$scope', '$state', '$timeout', function ($scope, $state, $timeout) {

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

        $scope.contactModel = {
            name: '',
            email: '',
            phone: '',
            content: ''
        };

        $scope.contact = function () {

            var pass = true;
            for (var key in $scope.contactModel) {
                if ($scope.contactModel[key] == '') {
                    pass = false;
                    break;
                }
            }

            if (!pass) {
                showMess('warn');
                return;
            }

            $.ajax({
                url: '/AboutUs/addContactUs',
                type: 'POST',
                data: $scope.contactModel,
                success: function () {
                    showMess('success');
                },
                error: function (err) {
                    console.log(err);
                    showMess('error');
                }
            })

        };

        // 获取顾问详情
        $scope.getConsultDetail = function (item) {
            $state.go("consultant", {conId: item.id});
        };

        function showMess(type) {
            $scope.mesType = type;
            $timeout(function () {
                $scope.mesType = '';
            }, 3000);
        }

    }];

    var homeModule = angular.module('about.config');
    homeModule.controller('aboutCtrl', aboutCtrl);

});
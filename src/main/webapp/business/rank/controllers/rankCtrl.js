/**
 * Created by L.H.S on 2017/8/14.
 */

define([''], function () {
    'use strict';

    var rankCtrl = ['$scope', '$state', '$timeout', function ($scope, $state, $timeout) {

        var couEnMap = {
            america: '美国',
            british: '英国',
            australia: '澳洲',
            canada: '加拿大',
            global: '其他'
        };

        var country = couEnMap[$state.params.country];

        $scope.leftBar = [];

        // 选择排名分类
        $scope.barClick = function (bar) {
            $scope.activeBar = bar;

            // 获取所有院校排名
            $.ajax({
                url: '/School/getAllSchoolRankingByPid?pid=' + bar.id,
                type: 'GET',
                success: function (resp) {
                    $timeout(function () {
                        $scope.rankList = resp;
                    });
                },
                error: function (err) {
                    console.log(err);
                }
            });
        };

        // 获取筛选类别
        $.ajax({
            url: '/Profession/getAllCategoryByCountry?country=' + country,
            type: 'GET',
            success: function (resp) {
                var temp = [];
                for (var key in resp) {
                    resp[key].forEach(function (item) {
                        temp.push({
                            id: item.pid,
                            label: item.subclassification
                        });
                    });
                }

                $timeout(function () {
                    $scope.leftBar = temp;
                    $scope.barClick(temp[0]);
                });
            },
            error: function (err) {
                console.log(err);
            }
        });


        $scope.getDetail = function () {
            $state.go('schoolDetail', {name: 'Harvard'});
        }

    }];

    var rankModule = angular.module('rank.config');
    rankModule.controller('rankCtrl', rankCtrl);

});
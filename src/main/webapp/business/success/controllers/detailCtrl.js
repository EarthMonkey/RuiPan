/**
 * Created by L.H.S on 2017/11/11.
 */

define([''], function () {
    'use strict';

    var detailCtrl = ['$scope', '$state', '$timeout', function ($scope, $state, $timeout) {

        var sucId = $state.params.sucId;

        $.ajax({
            url: '/SuccessfulCase/getSuccessfulCase?id=' + sucId,
            type: 'GET',
            success: function (resp) {
                getHtml(resp.textPath);
                $timeout(function () {
                    $scope.successCase = resp;
                });
            },
            error: function (err) {
                console.log("fail to get success case");
                console.log(err);
            }
        });

        function getHtml(path) {
            $.ajax({
                url: '/getText?path=' + path,
                type: 'GET',
                success: function (resp) {
                    $timeout(function () {
                        $scope.successCase.content = resp;
                    });
                },
                error: function (err) {
                    console.log(err);
                }
            })
        }

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

        // 获取全局推荐案例
        $.ajax({
            url: '/SuccessfulCase/getRecommendSuccessfulCase',
            type: 'GET',
            success: function (resp) {
                resp.splice(4);
                $timeout(function () {
                    $scope.recSucList = resp;
                });
            },
            error: function (err) {
                console.log(err);
            }
        });

    }];

    var successModule = angular.module('success.config');
    successModule.controller('detailCtrl', detailCtrl);
});
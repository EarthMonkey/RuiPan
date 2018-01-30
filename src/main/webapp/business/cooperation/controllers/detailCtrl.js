/**
 * Created by L.H.S on 2017/11/11.
 */
define([''], function () {
    'use strict';

    var detailCtrl = ['$scope', '$state', '$timeout',function ($scope, $state, $timeout) {

        var proId = $state.params.id;

        $.ajax({
            url: 'CooperativeEducation/getCooperativeSchemeById?id=' + proId,
            type: 'GET',
            success: function (resp) {
                getHtml(resp.textPath);
                $timeout(function () {
                    $scope.articleModel = resp;
                });
            },
            error: function (err) {
                console.log(err);
                console.log('fail to get article')
            }
        });


        function getHtml(path) {
            $.ajax({
                url: 'getText?path=' + path,
                type: 'GET',
                success: function (resp) {
                    $timeout(function () {
                        $scope.articleModel.content = resp;
                    });
                },
                error: function (err) {
                    console.log(err);
                }
            });
        }

        // 获取三个推荐方案
        $.ajax({
            url: 'StudyAbroad/getRecommendApplicationScheme',
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
            url: 'SuccessfulCase/getRecommendSuccessfulCase',
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

        // 获取案例详情
        $scope.getCaseDetail = function (item) {
            $state.go("successDetail", {sucId: item.id});
        };

    }];

    var homeModule = angular.module('coop.config');
    homeModule.controller('detailCtrl', detailCtrl);
});
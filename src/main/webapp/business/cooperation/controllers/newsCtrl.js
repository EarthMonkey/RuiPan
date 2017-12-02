/**
 * Created by L.H.S on 2017/11/11.
 */
define([''], function () {
    'use strict';

    var newsCtrl = ['$scope', '$state', '$timeout', function ($scope, $state, $timeout) {

        var urlMap = {
            'cooperation': '合作办学',
            'language': '语言培训'
        };

        $scope.backUrl = {
            url: $state.params.url,
            label: urlMap[$state.params.url]
        };
        var newsId = $state.params.id;

        $scope.stateGo = function () {
            var url = $scope.backUrl.url;
            if (url === 'language') {
                $state.go(url, {type: '校长教师培训'});
            } else {
                $state.go(url);
            }

        };

        $.ajax({
            url: '/News/getNewsById?id=' + newsId,
            type: 'GET',
            success: function (resp) {
                resp.pulishTime = new Date(resp.pulishTime);
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
                url: '/getText?path=' + path,
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

    var homeModule = angular.module('coop.config');
    homeModule.controller('newsCtrl', newsCtrl);
});
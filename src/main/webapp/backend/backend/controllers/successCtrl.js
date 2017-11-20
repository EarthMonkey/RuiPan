/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var successCtrl = ['$scope', '$state', 'commonService', function ($scope, $state, commonService) {

        $scope.selectedCoun = 'america';
        $scope.filterCountry = [];

        $scope.selectedEdu = 'business';
        $scope.filterEdu = [];

        $scope.selectedSp = 'finance';
        $scope.filterSp = [];

        var Sp_Map = [];

        $scope.filterClick = function (syb, selected) {
            if (syb == 0) {
                $scope.selectedCoun = selected;
                getSpClass();
            } else if (syb == 1) {
                $scope.selectedEdu = selected;
                $scope.filterSp = Sp_Map[selected.index];
                $scope.selectedSp = $scope.filterSp[0];
                getData();
            } else {
                $scope.selectedSp = selected;
                getData();
            }
        };

        // 获取国家
        $.ajax({
            url: "/StudyAbroad/getAllCountry",
            type: 'GET',
            success: function (data) {
                $scope.filterCountry = data;
                $scope.selectedCoun = data[0];
                getSpClass();
            },
            error: function (err) {
                console.log(err);
                showMess('danger', '获取国家列表失败');
            }
        });

        // 获取大类
        function getSpClass() {
            Sp_Map = [[]];
            $.ajax({
                url: '/Profession/getAllCategoryByCountry?country=' + $scope.selectedCoun,
                type: 'GET',
                success: function (resp) {
                    $scope.filterEdu = [];

                    var count = 0;
                    for (var key in resp) {
                        $scope.filterEdu.push({id: key, index: count});
                        Sp_Map[count] = [];
                        resp[key].forEach(function (item) {
                            Sp_Map[count].push({
                                id: item.subclassification,
                                pid: item.pid
                            });
                        });
                        count++;
                    }

                    $scope.selectedEdu = $scope.filterEdu[0];
                    $scope.filterSp = Sp_Map[0];
                    $scope.selectedSp = $scope.filterSp[0];
                    getData();
                },
                error: function (err) {
                    console.log(err);
                    showMess('danger', '获取大类专业失败');
                }
            })
        }

        $scope.successList = [];
        $scope.recomendList = [];

        // 获取数据
        function getData() {

            // 获取所有成功案例
            $.ajax({
                url: '/SuccessfulCase/getSuccessfulCaseByPid?pid=' + $scope.selectedSp.pid,
                type: 'GET',
                success: function (resp) {
                    console.log(resp);
                    $scope.successList = resp;
                },
                error: function (err) {
                    console.log(err);
                    showMess('danger', '获取所有案例失败');
                }
            });

            // 获取全局推荐案例
            $.ajax({
                url: '/SuccessfulCase/getRecommendSuccessfulCase',
                type: 'GET',
                success: function (resp) {
                    console.log(resp);
                    $scope.recomendList = resp;
                },
                error: function (err) {
                    console.log(err);
                    showMess('danger', '获取推荐案例失败');
                }
            })
        }

        function showMess(type, data) {
            commonService.showMessage($scope, {
                type: type,
                content: data
            });
        }

    }];

    var backendModule = angular.module('backend.config');
    backendModule.controller('successCtrl', successCtrl);
});
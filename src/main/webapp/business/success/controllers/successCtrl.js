/**
 * Created by L.H.S on 2017/8/14.
 */

define([''], function () {
        'use strict';

        var successCtrl = ['$scope', '$state', '$timeout', function ($scope, $state, $timeout) {

            $scope.selectedCoun = '';
            $scope.filterCountry = [];

            $scope.selectedEdu = '';
            $scope.filterEdu = [];

            $scope.selectedSp = '';
            $scope.filterSp = [];

            var Sp_Map = []; // 专业

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
                }
            });

            // 获取大类
            function getSpClass() {
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
                    }
                });
            }

            // 获取数据
            function getData() {

                if ($scope.selectedSp == '' || $scope.selectedSp == null) {
                    return;
                }

                // 获取所有成功案例
                $.ajax({
                    url: '/SuccessfulCase/getSuccessfulCaseByPid?pid=' + $scope.selectedSp.pid,
                    type: 'GET',
                    success: function (resp) {
                        $timeout(function () {
                            $scope.successList = resp;
                        });
                    },
                    error: function (err) {
                        console.log(err);
                    }
                });

            }

            /** 分页 */
            $scope.totalItems = 41;  // 此处为每页10条
            $scope.currentPage = 1;
            $scope.pageChanged = function () {
                console.log($scope.currentPage);
            };

            // 获取案例详情
            $scope.getDetail = function (item) {
                $state.go("successDetail", {sucId: item.id});
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
        successModule.controller('successCtrl', successCtrl);

    }
);
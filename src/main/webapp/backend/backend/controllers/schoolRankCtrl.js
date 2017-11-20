/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var schoolRankCtrl = ['$scope', '$state', 'commonService', function ($scope, $state, commonService) {

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

        $scope.rankList = [];

        // 获取数据
        function getData() {
            // 获取所有院校排名
            $.ajax({
                url: '/School/getAllSchoolRankingByPid?pid=' + $scope.selectedSp.pid,
                type: 'GET',
                success: function (resp) {
                    console.log(resp);
                    $scope.rankList = resp;
                },
                error: function (err) {
                    console.log(err);
                    showMess('danger', '获取院校排名失败');
                }
            })
        }

        var fields = [
            {id: 'ranking', label: '排名名次'},
            {id: 'name', label: '院校名称', readonly: true},
            {id: 'scoreRequirements', label: '分数要求'},
            {id: 'applicationDifficulty', label: '申请难度'}
        ];
        // 增加
        $scope.addRank = function () {
            commonService.openTextForm('').result.then(function (data) {
                
            })
        };

        // 修改
        $scope.modRank = function (item, pos) {

        };

        // 删除
        $scope.delRank = function (item, pos) {

        };

        function showMess(type, data) {
            commonService.showMessage($scope, {
                type: type,
                content: data
            });
        }
    }];

    var backendModule = angular.module('backend.config');
    backendModule.controller('schoolRankCtrl', schoolRankCtrl);
});
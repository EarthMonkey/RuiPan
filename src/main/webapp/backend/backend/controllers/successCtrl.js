/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var successCtrl = ['$scope', '$state', 'commonService', function ($scope, $state, commonService) {

        $scope.selectedCoun = '';
        $scope.filterCountry = [];

        $scope.selectedEdu = '';
        $scope.filterEdu = [];

        $scope.selectedSp = '';
        $scope.filterSp = [];

        var Sp_Map = []; // 专业
        var School_Map = []; // 学校Map
        var School_Names = []; // 学校

        var Consult_Map = []; // 顾问map
        var Consult_Names = []; // 顾问option

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
            });

            // 根据国家获取学校
            School_Map = [];
            $.ajax({
                url: '/School/getSchoolPublished?country=' + $scope.selectedCoun,
                type: 'GET',
                success: function (resp) {
                    School_Map = resp;
                    resp.forEach(function (item) {
                        School_Names.push(item.collegeName);
                    });
                },
                error: function (err) {
                    console.log(err);
                    showMess("danger", '获取学校失败');
                }
            });
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
                    $scope.recomendList = resp;
                },
                error: function (err) {
                    console.log(err);
                    showMess('danger', '获取推荐案例失败');
                }
            });

            // 根据专业获取顾问
            $.ajax({
                url: '/Consultant/getByPid?pid=' + $scope.selectedSp.pid,
                type: 'GET',
                success: function (resp) {
                    Consult_Map = resp;
                    resp.forEach(function (item) {
                        Consult_Names.push(item.name);
                    });
                },
                error: function (err) {
                    console.log(err);
                    showMess('danger', '获取专业顾问失败');
                }
            })

        }

        var sucFields = [
            {id: 'name', label: '学生称呼'},
            {id: 'degree', label: '学生学位'},
            {id: 'sid', label: '录取学校', type: 'combox', options: []},
            {id: 'enrollmentTime', label: '录取时间'},
            {id: 'languageScore', label: '语言成绩'},
            {id: 'gpa', label: 'GPA成绩'},
            {id: 'gmatSatGre', label: 'GMAT/SAT/..'},
            {id: 'undergraduateMajor', label: '本科专业'},
            {id: 'enrollmentTime', label: '录取时间'},
            {id: 'consultant', label: '服务顾问', type: 'combox', options: []}
        ];

        // 增加成功案例
        $scope.addSuc = function () {

            scuFields[2].options = School_Names;


        };

        // 修改成功案例
        $scope.modSuc = function (item) {

        };

        // 删除成功案例
        $scope.delSuc = function (item, pos) {

        };

        // 查看成功案例
        $scope.getSuc = function (item) {

        };

        // 增加推荐
        $scope.addRec = function () {

        };

        // 修改推荐
        $scope.modRec = function (item, pos) {

        };

        // 删除推荐
        $scope.delRec = function (item, pos) {

        };

        // 查看推荐
        $scope.getRec = function (item) {

        };


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
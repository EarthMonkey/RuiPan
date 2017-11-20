/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var specialInfoCtrl = ['$scope', '$state', 'commonService', function ($scope, $state, commonService) {

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

        $scope.detailAdvice = {};
        $scope.courseList = [];
        $scope.workList = [];

        // 获取数据
        function getData() {
            // 详情、建议
            $.ajax({
                url: '/Profession/getProfessionIntroducePublished?pid=' + $scope.selectedSp.pid,
                type: 'GET',
                success: function (resp) {
                    console.log(resp);
                    $scope.detailAdvice = resp;
                },
                error: function (err) {
                    console.log(err);
                    showMess('danger', '获取详情和建议失败');
                }
            });

            // 专业课程
            $.ajax({
                url: '/Profession/getProfessionCourse?pid=' + $scope.selectedSp.pid,
                type: 'GET',
                success: function (resp) {
                    $scope.courseList = resp;
                },
                error: function (err) {
                    console.log(err);
                    showMess('danger', '获取课程列表失败');
                }
            });

            // 就业去向、领域
            $.ajax({
                url: '/Profession/getEmploymentCompany?pid=' + $scope.selectedSp.pid,
                type: 'GET',
                success: function (resp) {
                    $scope.workList = resp;
                },
                error: function (err) {
                    console.log(err);
                    showMess('danger', '获取就业去向失败');
                }
            })
        }

        var courseFields = [
            {id: 'majorField', label: '专业方向'},
            {id: 'majorCourse', label: '专业课程'}
        ];

        // 创建/修改详情建议
        $scope.editDetail = function () {
            // 若修改则添加 initObj

            var initInfo = {
                title: '编辑专业详情和申请建议',
                fields: [
                    {id: 'applicationAdvice', label: '申请建议', type: 'textarea'},
                    {id: 'detailSynopsis', label: '详情导语', type: 'textarea'}
                ],
                backState: 'backend.specialInfo',
                ajaxUrl: '/Profession/addProfessionIntroduce',
                pid: $scope.selectedSp.pid,
                gidKey: 'pid'
            };
            $state.go('backend.article', {initInfo: JSON.stringify(initInfo)});
        };

        $scope.getDetail = function () {
            var initInfo = {
                title: '专业详情和申请建议',
                fields: [
                    {id: 'applicationAdvice', label: '申请建议', type: 'textarea'},
                    {id: 'detailSynopsis', label: '详情导语', type: 'textarea'}
                ],
                backState: 'backend.specialInfo',
                ajaxUrl: '/Profession/addProfessionIntroduce',
                pid: $scope.selectedSp.pid,
                pdiKey: 'pid',
                initObj: {
                    objId: $scope.detailAdvice.pid,
                    url: '/Profession/getProfessionIntroducePublished?pid=' + $scope.detailAdvice.pid
                },
                readonly: true
            };

            $state.go("backend.article", {initInfo: JSON.stringify(initInfo)});
        };

        // 添加专业课程
        $scope.addCourse = function () {
            var courInstance = commonService.openTextForm('添加专业课程', courseFields);
            courInstance.result.then(function (data) {
                data.pid = $scope.selectedSp.pid;
                $.ajax({
                    url: '/Profession/addProfessionCourse',
                    type: "POST",
                    data: data,
                    success: function (resp) {
                        $scope.courseList.push(resp);
                        showMess('success', '添加成功');
                    },
                    error: function (err) {
                        console.log(err);
                        showMess('danger', '添加失败');
                    }
                })
            })
        };

        // 修改专业课程
        $scope.modCourse = function (item, pos) {
            var courInstance = commonService.openTextForm('修改专业课程', courseFields, item);
            courInstance.result.then(function (data) {
                data.pid = $scope.selectedSp.pid;
                data.id = item.id;
                console.log(data)
                $.ajax({
                    url: '/Profession/updateProfessionCourse',
                    type: 'PUT',
                    data: data,
                    success: function (resp) {
                        $scope.courseList[pos] = resp;
                    },
                    error: function (err) {
                        console.log(errr);
                        showMess('danger', '修改失败');
                    }
                })
            });
        };

        // 删除专业课程
        $scope.delCourse = function (item, pos) {
            var courInstance = commonService.confirm("专业课程：" + item.majorCourse);
            courInstance.result.then(function (resp) {
                if (resp) {
                    $.ajax({
                        url: '/Profession/deleteProfessionCourse?id=' + item.id,
                        type: 'DELETE',
                        success: function () {
                            $scope.courseList.splice(pos, 1);
                            showMess('success', '删除成功');
                        },
                        error: function (err) {
                            console.log(err);
                            showMess('danger', '删除失败');
                        }
                    })
                }
            });

        };

        var workFields = [
            {id: 'employmentCompany', label: '就业去向'},
            {id: 'logo', label: 'logo图片', type: 'img'}
        ];
        // 添加就业去向
        $scope.addWork = function () {
            commonService.openTextForm('添加就业去向', workFields).result.then(function (data) {
                data.pid = $scope.selectedSp.pid;


            });
        };

        // 修改就业去向
        $scope.modWork = function (item, pos) {

        };

        // 删除就业去向
        $scope.delWork = function (item, pos) {

        };

        function showMess(type, data) {
            commonService.showMessage($scope, {
                type: type,
                content: data
            });
        }

    }];

    var backendModule = angular.module('backend.config');
    backendModule.controller('specialInfoCtrl', specialInfoCtrl);
});
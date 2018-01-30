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

        var Consult_Map = []; // 顾问map

        var Case_Map = []; // 案例map

        // 获取所有顾问
        $.ajax({
            url: 'Consultant/get',
            type: 'GET',
            success: function (resp) {
                resp.forEach(function (item) {
                    Consult_Map.push({
                        id: item.id,
                        label: item.name
                    })
                })
            },
            error: function (err) {
                console.log(err);
                showMess('danger', '获取专业顾问失败');
            }
        });

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
            url: "StudyAbroad/getAllCountry",
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
                url: 'Profession/getAllCategoryByCountry?country=' + $scope.selectedCoun,
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
                url: 'School/getSchoolPublished?country=' + $scope.selectedCoun,
                type: 'GET',
                success: function (resp) {
                    resp.forEach(function (item) {
                        School_Map.push({
                            id: item.sid,
                            label: item.collegeName
                        });
                    })
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

            if ($scope.selectedSp == '' || $scope.selectedSp == null) {
                return;
            }

            Case_Map = [];

            // 获取所有成功案例
            $.ajax({
                url: 'SuccessfulCase/getSuccessfulCaseByPid?pid=' + $scope.selectedSp.pid,
                type: 'GET',
                success: function (resp) {
                    $scope.successList = resp;

                    resp.forEach(function (item) {
                        Case_Map.push(item.name + ' @' + item.id);
                    });

                },
                error: function (err) {
                    console.log(err);
                    showMess('danger', '获取所有案例失败');
                }
            });

            // 获取全局推荐案例
            $.ajax({
                url: 'SuccessfulCase/getRecommendSuccessfulCase',
                type: 'GET',
                success: function (resp) {
                    $scope.recomendList = resp;
                },
                error: function (err) {
                    console.log(err);
                    showMess('danger', '获取推荐案例失败');
                }
            });
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
            {id: 'cid', label: '服务顾问', type: 'combox', options: []}
        ];

        // 增加成功案例
        $scope.addSuc = function () {

            sucFields[2].options = School_Map;
            sucFields[8].options = Consult_Map;

            var initInfo = {
                title: '增加成功案例',
                fields: sucFields,
                backState: 'backend.success',
                ajaxUrl: 'SuccessfulCase/addSuccessfulCase',
                pid: $scope.selectedSp.pid,
                gidKey: 'pid'
            };

            $state.go("backend.article", {initInfo: JSON.stringify(initInfo)});
        };

        // 修改成功案例
        $scope.modSuc = function (item) {

            sucFields[2].options = School_Map;
            sucFields[8].options = Consult_Map;

            var initInfo = {
                title: '增加成功案例',
                fields: sucFields,
                backState: 'backend.success',
                ajaxUrl: 'SuccessfulCase/updateSuccessfulCase',
                pid: $scope.selectedSp.pid,
                gidKey: 'pid',
                initObj: {
                    objId: item.id,
                    url: 'SuccessfulCase/getSuccessfulCase?id=' + item.id
                }
            };

            $state.go("backend.article", {initInfo: JSON.stringify(initInfo)});
        };

        // 删除成功案例
        $scope.delSuc = function (item, pos) {

            commonService.confirm('成功案例：' + item.name).result
                .then(function (resp) {
                    if (resp) {
                        $.ajax({
                            url: 'SuccessfulCase/deleteSuccessfulCase?id=' + item.id,
                            type: 'DELETE',
                            success: function () {
                                $scope.successList.splice(pos, 1);
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

        // 查看成功案例
        $scope.getSuc = function (item) {
            sucFields[2].options = School_Map;
            sucFields[8].options = Consult_Map;

            var initInfo = {
                title: '增加成功案例',
                fields: sucFields,
                backState: 'backend.success',
                ajaxUrl: 'SuccessfulCase/updateSuccessfulCase',
                pid: $scope.selectedSp.pid,
                gidKey: 'pid',
                initObj: {
                    objId: item.id,
                    url: 'SuccessfulCase/getSuccessfulCase?id=' + item.id
                },
                readonly: true
            };

            $state.go("backend.article", {initInfo: JSON.stringify(initInfo)});
        };

        // 增加推荐
        $scope.addRec = function () {

            var field = [
                {id: 'slogan', label: '推荐标语', type: 'textarea'},
                {id: 'case', label: '选择案例', type: 'combox', options: Case_Map}
            ];

            commonService.openTextForm('增加推荐', field).result
                .then(function (data) {
                    data.rid = data.case.split(' @')[1];
                    delete data.case;

                    $.ajax({
                        url: 'SuccessfulCase/addRecommendSuccessfulCase',
                        type: 'POST',
                        data: data,
                        success: function (resp) {
                            $scope.recomendList.unshift(resp);
                            showMess('success', '添加成功');
                        },
                        error: function (err) {
                            console.log(err);
                            showMess('danger', '添加失败');
                        }
                    })
                });

        };

        // 修改推荐
        $scope.modRec = function (item, pos) {
            var field = [
                {id: 'slogan', label: '推荐标语', type: 'textarea'}
            ];

            commonService.openTextForm('增加推荐', field, {slogan: item.slogan}).result
                .then(function (data) {
                    $.ajax({
                        url: 'SuccessfulCase/updateRecommendSuccessfulCase',
                        type: 'PUT',
                        data: {
                            id: item.id,
                            rid: item.rid,
                            slogan: data.slogan
                        },
                        success: function () {
                            item.slogan = data.slogan;
                            showMess('success', '修改成功');
                        },
                        error: function (err) {
                            console.log(err);
                            showMess('danger', '修改失败');
                        }
                    })
                });
        };

        // 删除推荐
        $scope.delRec = function (item, pos) {

            commonService.confirm('推荐案例：' + item.successfulCaseVO.name).result
                .then(function (resp) {
                    if (resp) {

                        $.ajax({
                            url: 'SuccessfulCase/deleteRecommendSuccessfulCase?id=' + item.id,
                            type: 'DELETE',
                            success: function () {
                                $scope.recomendList.splice(pos, 1);
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
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

        $scope.rankList = [];

        // 获取数据
        function getData() {
            // 获取所有院校排名
            $.ajax({
                url: '/School/getAllSchoolRankingByPid?pid=' + $scope.selectedSp.pid,
                type: 'GET',
                success: function (resp) {
                    $scope.rankList = resp;
                },
                error: function (err) {
                    console.log(err);
                    showMess('danger', '获取院校排名失败');
                }
            });
        }

        var fields = [
            {id: 'collegeName', label: '院校名称', type: 'combox', options: []},
            {id: 'ranking', label: '排名名次'},
            {id: 'scoreRequirements', label: '分数要求'},
            {id: 'applicationDifficulty', label: '申请难度'}
        ];

        // 增加
        $scope.addRank = function () {
            // 根据国家获取学校
            $.ajax({
                url: '/School/getSchoolPublished?country=' + $scope.selectedCoun,
                type: 'GET',
                success: function (resp) {
                    var nameOption = [];
                    resp.forEach(function (item) {
                        nameOption.push(item.collegeName);
                    });
                    fields[0].options = nameOption;

                    commonService.openTextForm('添加排名信息', fields).result.then(function (data) {
                        data.pid = $scope.selectedSp.pid;
                        for (var i = 0; i < resp.length; i++) {
                            if (data.collegeName == resp[i].collegeName) {
                                data.sid = resp[i].sid;
                                delete data.collegeName;
                                break;
                            }
                        }

                        $.ajax({
                            url: '/School/addSchoolRanking',
                            type: 'POST',
                            data: data,
                            success: function (resp) {
                                $scope.rankList.push(resp);
                                showMess('success', '添加成功');
                            },
                            error:function (err) {
                                console.log(err);
                                showMess('danger', '添加失败');
                            }
                        })
                    });
                },
                error: function (err) {
                    console.log(err);
                    showMess("danger", '获取学校失败');
                }
            });
        };

        // 修改
        $scope.modRank = function (row, pos) {
            // 根据国家获取学校
            $.ajax({
                url: '/School/getSchoolPublished?country=' + $scope.selectedCoun,
                type: 'GET',
                success: function (resp) {
                    console.log(resp);
                    var nameOption = [];
                    resp.forEach(function (item) {
                        nameOption.push(item.collegeName);
                    });
                    fields[0].options = nameOption;

                    row.collegeName = row.schoolBySid.collegeName;
                    commonService.openTextForm('修改排名信息', fields, row).result.then(function (data) {
                        data.pid = row.pid;
                        data.id = row.id;
                        for (var i = 0; i < resp.length; i++) {
                            if (data.collegeName == resp[i].collegeName) {
                                data.sid = resp[i].sid;
                                delete data.collegeName;
                                break;
                            }
                        }

                        $.ajax({
                            url: '/School/updateSchoolRanking',
                            type: 'PUT',
                            data: data,
                            success: function (resp) {
                                $scope.rankList[pos] = resp;
                                showMess('success', '修改成功');
                            },
                            error:function (err) {
                                console.log(err);
                                showMess('danger', '修改失败');
                            }
                        })
                    });
                },
                error: function (err) {
                    console.log(err);
                    showMess("danger", '获取学校失败');
                }
            });
        };

        // 删除
        $scope.delRank = function (item, pos) {
            commonService.confirm('排名信息：' + item.schoolBySid.collegeName).result.then(function (resp) {
                if (resp) {
                    $.ajax({
                        url: '/School/deleteSchoolRanking?id=' + item.id,
                        type: 'DELETE',
                        success: function () {
                            $scope.rankList.splice(pos, 1);
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

        $scope.getDetail = function (item) {
            var combox = {
                id: 'country',
                label: '选择国家',
                options: $scope.filterCountry
            };
            var detailFields = [
                {id: 'collegeName', label: '学校名称'},
                {id: 'address', label: '学校地址'},
                {id: 'officialWebsite', label: '官网地址'},
                {id: 'synopsis', label: '导语介绍', type: 'textarea'},
                {id: 'schoolBadge', label: '学校logo', type: 'img'}
            ];
            var initInfo = {
                title: '学校详情',
                combox: combox,
                fields: detailFields,
                backState: 'backend.schoolRank',
                ajaxUrl: '/School/updateSchool',
                initObj: {
                    objId: item.sid,
                    url: '/School/getSchoolBySid?sid=' + item.sid
                },
                readonly: true
            };
            $state.go('backend.article', {initInfo: JSON.stringify(initInfo)});
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
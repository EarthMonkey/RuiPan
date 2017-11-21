/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var consultCtrl = ['$scope', '$state', 'commonService', function ($scope, $state, commonService) {

        $scope.consultList = [];
        $scope.arrangeList = [];

        // 国家筛选
        $scope.selectedCoun = '';
        $scope.filterCountry = [];

        $scope.star = function (item) {

            if (item.isRecommend=='false') { // 推荐
                $.ajax({
                    url: '/Consultant/recommend',
                    type: 'PUT',
                    data: {id: item.id},
                    success: function () {
                        item.isRecommend = 'true';
                        showMess('success', '推荐成功');
                    },
                    error: function (err) {
                        console.log(err);
                        showMess('danger', '操作失败');
                    }
                })
            } else { // 不推荐

                $.ajax({
                    url: '/Consultant/cancleRecommend',
                    type: 'PUT',
                    data: {id: item.id},
                    success: function () {
                        item.isRecommend = 'false';
                        showMess('success', '取消推荐成功');
                    },
                    error: function (err) {
                        console.log(err);
                        showMess('danger', '操作失败');
                    }
                })
            }
        };

        // 获取所有顾问
        $.ajax({
            url: '/Consultant/get',
            type: 'GET',
            success: function (resp) {
                $scope.consultList = resp;
            },
            error: function (err) {
                console.log(err);
                showMess('danger', '获取顾问失败');
            }
        });

        // 获取所有国家
        $.ajax({
            url: "/StudyAbroad/getAllCountry",
            type: 'GET',
            success: function (data) {
                $scope.filterCountry = data;
                $scope.selectedCoun = $scope.filterCountry[0];
                getGid();
            },
            error: function (err) {
                console.log(err);
                showMess('danger', '获取国家列表失败');
            }
        });

        var consutlFields = [
            {id: 'name', label: '顾问姓名'},
            {id: 'country', label: '所属国籍'},
            {id: 'profession', label: '所属专业'},
            {id: 'workingHours', label: '工作时长', placeholder: '小数或整数'},
            {id: 'offerNumber', label: '获得offer数'},
            {id: 'applicationSuccessRate', label: '成功率'},
            {id: 'synopsis', label: '简介', type: 'textarea'},
            {id: 'headSculpture', label: '顾问相片', type: 'img'}
        ];

        // 增加顾问
        $scope.addConsult = function () {

            var initInfo = {
                title: '添加顾问',
                fields: consutlFields,
                backState: 'backend.consult',
                ajaxUrl: '/Consultant/add'
            };

            $state.go("backend.article", {initInfo: JSON.stringify(initInfo)});
        };

        // 修改顾问
        $scope.modConsult = function (item) {

            var initInfo = {
                title: '修改顾问信息',
                fields: consutlFields,
                backState: 'backend.consult',
                ajaxUrl: '/Consultant/updateConsultant',
                initObj: {
                    objId: item.id,
                    url: '/Consultant/getById?id=' + item.id
                }
            };

            $state.go("backend.article", {initInfo: JSON.stringify(initInfo)});
        };

        // 删除顾问
        $scope.delConsult = function (item, pos) {

            commonService.confirm('顾问：' + item.name).result
                .then(function (resp) {
                    if (resp) {
                        $.ajax({
                            url: '/Consultant/delete?id=' + item.id,
                            type: 'DELETE',
                            success: function () {
                                $scope.consultList.splice(pos, 1);
                                showMess('success', '删除成功');
                            },
                            error: function (err) {
                                console.log(err);
                                showMess('danger', '删除失败');
                            }
                        });
                    }
                });
        };

        // 查看顾问
        $scope.getConsult = function (item) {
            var initInfo = {
                title: '修改顾问信息',
                fields: consutlFields,
                backState: 'backend.consult',
                ajaxUrl: '/Consultant/updateConsultant',
                initObj: {
                    objId: item.id,
                    url: '/Consultant/getById?id=' + item.id
                },
                readonly: true
            };

            $state.go("backend.article", {initInfo: JSON.stringify(initInfo)});
        };

        $scope.selectedEdu = '研究生';
        $scope.filterEdu = ['研究生', '本科生', '高中生'];

        // 初始化数据
        var GID = '';

        $scope.filterClick = function (syb, selected) {
            if (syb == 0) {
                $scope.selectedCoun = selected;
            } else {
                $scope.selectedEdu = selected;
            }
            getGid();
        };

        function getGid() {
            $.ajax({
                url: '/StudyAbroad/getGid?country=' + $scope.selectedCoun + "&grade=" + $scope.selectedEdu,
                type: 'GET',
                success: function (resp) {
                    GID = resp;
                    getArrange();
                },
                error: function (err) {
                    console.log(err);
                    showMess('danger', '服务器出错啦');
                }
            });
        }

        // 根据gid获取业务顾问
        function getArrange() {
            $.ajax({
                url: '/Consultant/getByGid?gid=' + GID,
                type: 'GET',
                success: function (resp) {
                    console.log(resp);
                    $scope.arrangeList = resp;
                },
                error: function (err) {
                    console.log(err);
                    showMess('danger', '获取业务顾问失败');
                }
            })
        }

        // 增加业务顾问
        $scope.addArrange = function () {

            var consultsOpt = [];
            $scope.consultList.forEach(function (item) {
                consultsOpt.push(item.name + " @" + item.id);
            });

            var fields = [
                {id: 'consult', label: '选取顾问', type: 'combox', options: consultsOpt}
            ];

            commonService.openTextForm('分派业务', fields).result
                .then(function (data) {
                    var arr = data.consult.split(" @");
                    var conId = arr[1];
                    var cName = arr[0];
                    $.ajax({
                        url: '/Consultant/addConsultantBusinessByGid',
                        type: 'POST',
                        data: {
                            cid: conId,
                            gid: GID
                        },
                        success: function (resp) {
                            resp.name = cName;
                            $scope.arrangeList.push(resp);
                            showMess('success', '操作成功');
                        },
                        error: function (err) {
                            console.log(err);
                            showMess('danger', '操作失败');
                        }
                    })

                });

        };

        // 删除业务顾问
        $scope.delArrange = function (item, pos) {

            commonService.confirm('顾问业务：' + item.name).result
                .then(function (resp) {
                    if (resp) {
                        $.ajax({
                            url: '/Consultant/deleteConsultantBusinessByGid?gid=' + GID + '&cid=' + item.id,
                            type: 'DELETE',
                            success: function () {
                                $scope.arrangeList.splice(pos, 1);
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
    backendModule.controller('consultCtrl', consultCtrl);
});
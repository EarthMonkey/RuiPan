/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var abroadMethodCtrl = ['$scope', '$state', 'commonService', function ($scope, $state, commonService) {

        $scope.selectedCoun = '美国';
        $scope.filterCountry = [
            {id: '美国', label: '美国'},
            {id: '英国', label: '英国'},
            {id: '澳洲', label: '澳洲'},
            {id: '加拿大', label: '加拿大'},
            {id: '其他', label: '其他'}
        ];

        $scope.selectedEdu = '研究生';
        $scope.filterEdu = [
            {id: '研究生', label: '研究生'},
            {id: '本科生', label: '本科生'},
            {id: '高中生', label: '高中生'}
        ];

        $scope.filterClick = function (syb, selected) {
            if (syb == 0) {
                $scope.selectedCoun = selected.id;
            } else {
                $scope.selectedEdu = selected.id;
            }
            getGid();
        };

        // 初始化数据
        var GID = '';

        getGid();
        function getGid() {
            $.ajax({
                url: '/StudyAbroad/getGid?country=' + $scope.selectedCoun + "&grade=" + $scope.selectedEdu,
                type: 'GET',
                success: function (resp) {
                    GID = resp;
                    getData();
                },
                error: function (err) {
                    console.log(err);
                    showMess('danger', '服务器出错啦');
                }
            });
        }

        $scope.methodList = [];
        $scope.recomendList = [];

        // 初始化数据
        function getData() {
            // 获取所有方案
            $.ajax({
                url: '/StudyAbroad/getApplicationSchemeByGid?gid=' + GID,
                type: 'GET',
                success: function (resp) {
                    for (var key in resp) {
                        resp[key].forEach(function (item) {
                            $scope.methodList.push(item);
                        });
                    }
                },
                error: function (err) {
                    console.log(err);
                    showMess('danger', '获取所有方案失败');
                }
            });
        }

        // 获取三个推荐方案
        $.ajax({
            url: '/StudyAbroad/getRecommendApplicationScheme',
            type: 'GET',
            success: function (resp) {
                console.log(resp);
            },
            error: function (err) {
                console.log(err);
                showMess('danger', '获取推荐方案失败');
            }
        });


        var combox = {
            id: 'subdivisionGrade',
            label: '年级分类',
            options: ["初一在读", '初二在读', '初三在读', '高一在读', '高二在读', '高三在读',
                '大一在读', '大二在读', '大三在读', '大四在读', '大学毕业N年', '大专在读/毕业',
                '研一在读', '研二在读/毕业']
        };
        var methodField = [
            {id: "title", label: '方案标题', placeholder: '单申ESL课程'},
            {id: 'synopsis', label: '方案导语'}
        ];
        // 添加方案
        $scope.addMethod = function () {
            var initInfo = {
                title: '添加方案',
                combox: combox,
                fields: methodField,
                backState: 'backend.abroadMethod',
                ajaxUrl: '/StudyAbroad/addApplicationScheme',
                gid: GID,
                gidKey: 'gid'
            };
            $state.go("backend.article", {initInfo: JSON.stringify(initInfo)});
        };

        // 修改方案
        $scope.modMethod = function (item) {
            var initInfo = {
                title: '修改方案',
                combox: combox,
                fields: methodField,
                backState: 'backend.abroadMethod',
                ajaxUrl: '/StudyAbroad/updateApplicationScheme',
                gid: GID,
                gidKey: 'gid',
                initObj: {
                    objId: item.id,
                    url: '/StudyAbroad/getApplicationSchemeById?id=' + item.id
                }
            };
            $state.go("backend.article", {initInfo: JSON.stringify(initInfo)});
        };

        // 删除方案
        $scope.delMethod = function (item, pos) {
            $.ajax({
                url: '/StudyAbroad/deleteApplicationScheme?id=' + item.id,
                type: 'DELETE',
                success: function () {
                    showMess('success', '删除成功');
                    $scope.methodList.splice(pos, 1);
                },
                error: function (err) {
                    console.log(err);
                    showMess('danger', '删除失败');
                }
            })
        };

        $scope.getMethod = function (item) {
            var initInfo = {
                title: '方案详情',
                combox: combox,
                fields: methodField,
                backState: 'backend.abroadMethod',
                ajaxUrl: '/StudyAbroad/updateApplicationScheme',
                gid: GID,
                gidKey: 'gid',
                initObj: {
                    objId: item.id,
                    url: '/StudyAbroad/getApplicationSchemeById?id=' + item.id
                },
                readonly: true
            };
            $state.go("backend.article", {initInfo: JSON.stringify(initInfo)});
        };

        function showMess(type, data) {
            commonService.showMessage($scope, {
                type: type,
                content: data
            });
        }
    }];

    var backendModule = angular.module('backend.config');
    backendModule.controller('abroadMethodCtrl', abroadMethodCtrl);
});
/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var schoolInfoCtrl = ['$scope', '$state', 'commonService', function ($scope, $state, commonService) {

        $scope.selectedCoun = 'america';
        $scope.filterCountry = [];

        $scope.filterClick = function (selected) {
            $scope.selectedCoun = selected;
            getData();
        };

        // 获取国家
        $.ajax({
            url: "StudyAbroad/getAllCountry",
            type: 'GET',
            success: function (data) {
                $scope.filterCountry = data;
                $scope.selectedCoun = data[0];
                getData();
            },
            error: function (err) {
                console.log(err);
                showMess('danger', '获取国家列表失败');
            }
        });

        $scope.schoolList = [];

        // 根据国家获取学校
        function getData() {
            $.ajax({
                url: 'School/getSchoolPublished?country=' + $scope.selectedCoun,
                type: 'GET',
                success: function (resp) {
                    $scope.schoolList = resp;
                },
                error: function (err) {
                    console.log(err);
                    showMess("danger", '获取学校失败');
                }
            });
        }

        var fields = [
            {id: 'collegeName', label: '学校名称'},
            {id: 'address', label: '学校地址'},
            {id: 'officialWebsite', label: '官网地址'},
            {id: 'synopsis', label: '导语介绍', type: 'textarea'},
            {id: 'schoolBadge', label: '学校logo', type: 'img'}
        ];

        $scope.addSchool = function () {
            var combox = {
                id: 'country',
                label: '选择国家',
                options: $scope.filterCountry
            };
            var initInfo = {
                title: '添加学校',
                combox: combox,
                fields: fields,
                backState: 'backend.schoolInfo',
                ajaxUrl: 'School/addSchool'
            };
            $state.go('backend.article', {initInfo: JSON.stringify(initInfo)});
        };

        $scope.modSchool = function (item, readonly) {
            var combox = {
                id: 'country',
                label: '选择国家',
                options: $scope.filterCountry
            };
            var initInfo = {
                title: '添加学校',
                combox: combox,
                fields: fields,
                backState: 'backend.schoolInfo',
                ajaxUrl: 'School/updateSchool',
                initObj: {
                    objId: item.sid,
                    url: 'School/getSchoolBySid?sid=' + item.sid
                }
            };
            if (readonly) {
                initInfo.readonly = true;
            }
            $state.go('backend.article', {initInfo: JSON.stringify(initInfo)});
        };

        $scope.delSchool = function (item, pos) {
            commonService.confirm("学校：" + item.collegeName).result.then(function (resp) {
                if (resp) {
                    $.ajax({
                        url: 'School/deleteSchool?sid=' + item.sid,
                        type: 'DELETE',
                        success: function () {
                            $scope.schoolList.splice(pos, 1);
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
    backendModule.controller('schoolInfoCtrl', schoolInfoCtrl);
});
/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var languageCtrl = ['$scope', '$state', 'commonService', function ($scope, $state, commonService) {

        $scope.selectedType = '校长教师培训';
        $scope.filterType = ['校长教师培训', '英语培训', '中文培训', '小语种培训', '其他'];

        $scope.filterClick = function (selected) {
            $scope.selectedType = selected;
            getData();
        };

        $scope.introList = [];

        getData();
        function getData() {


            // 介绍信息
            $.ajax({
                url: '/LanguageTraining/getTrainIntroducePublish?category=' + $scope.selectedType,
                type: 'GET',
                success: function (resp) {
                    console.log(resp);
                    $scope.introList = resp;
                },
                error: function (err) {
                    console.log(err);
                    showMess('danger', '获取介绍信息失败');
                }
            });
        }

        var combox = {
            id: 'category',
            label: '所属类别',
            options: ['校长教师培训', '英语培训', '中文培训', '小语种培训', '其他']
        };

        // 增加介绍
        $scope.addIntro = function () {

            var initInfo = {
                title: '添加介绍模块',
                combox: combox,
                fields: [{id: 'title', label: '模块标题'}],
                backState: 'backend.language',
                ajaxUrl: '/LanguageTraining/addTrainIntroduce'
            };

            $state.go("backend.article", {initInfo: JSON.stringify(initInfo)});
        };

        // 修改介绍
        $scope.modIntro = function (item, pos) {

            var initInfo = {
                title: '添加介绍模块',
                combox: combox,
                fields: [{id: 'title', label: '模块标题'}],
                backState: 'backend.language',
                ajaxUrl: '/LanguageTraining/updateTrainIntroduce',
                initObj: {
                    objId: item.id,
                    url: '/LanguageTraining/getTrainIntroduceById?id=' + item.id
                }
            };

            $state.go("backend.article", {initInfo: JSON.stringify(initInfo)});
        };

        // 删除介绍
        $scope.delIntro = function (item, pos) {

            commonService.confirm('介绍信息：' + item.title).result
                .then(function (resp) {
                    if (resp) {
                        $.ajax({
                            url: '/LanguageTraining/deleteTrainIntroduce?id=' + item.id,
                            type: 'DELETE',
                            success: function () {
                                $scope.introList.splice(pos, 1);
                                showMess('success', '删除成功');
                            },
                            error: function (err) {
                                console.log(err);
                                showMess('danger', '删除失败')
                            }
                        })
                    }
                });
        };

        // 获取介绍
        $scope.getIntro = function (item) {
            var initInfo = {
                title: '添加介绍模块',
                combox: combox,
                fields: [{id: 'title', label: '模块标题'}],
                backState: 'backend.language',
                ajaxUrl: '/LanguageTraining/updateTrainIntroduce',
                initObj: {
                    objId: item.id,
                    url: '/LanguageTraining/getTrainIntroduceById?id=' + item.id
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
    backendModule.controller('languageCtrl', languageCtrl);
});
/**
 * Created by L.H.S on 2017/11/16.
 */

define([''], function () {
    'use strict';

    var articleCtrl = ['$scope', '$state', '$timeout', 'commonService',
        function ($scope, $state, $timeout, commonService) {

            /**
             * 类型：new，modify
             * 返回url
             * fields
             * modify：id
             * */

            // 下拉框
            $scope.combox = {
                id: '',
                options: [],
                select: function (option) {
                    $scope.model[$scope.combox.id] = option;
                }
            };

            // 输入框
            $scope.fields = [];

            // editor
            $scope.config = {
                autoHeightEnabled: true
            };

            // 数据模型
            $scope.model = {
                content: ''
            };

            // 图片
            $scope.hasPicture = "";

            // 获取初始值
            var initInfo = JSON.parse($state.params.initInfo);
            if (initInfo.combox) {
                $scope.combox.id = initInfo.combox.id;
                $scope.combox.options = initInfo.combox.options;
                $scope.combox.label = initInfo.combox.label;
                // 绑定数据模型
                $scope.model[initInfo.combox.id] = initInfo.combox.options[0];
            }
            $scope.fields = initInfo.fields;
            $scope.title = initInfo.title;

            if (initInfo.initObj) {  // 修改
                // 根据id获取数据
                $.ajax({
                    url: initInfo.initObj.url,
                    type: 'GET',
                    success: function (resp) {
                        console.log(resp);
                        $scope.model = resp;
                        // 根据textPath获取html
                        if (resp.textPath) {
                            commonService.getHTML($scope, resp.textPath);
                        }
                    },
                    error: function (err) {
                        console.log(err);
                        showMess('danger', '获取数据失败');
                    }
                });
            }
            if (initInfo.hasPicture) {
                $scope.hasPicture = true;
            }
            if (initInfo.readonly) {
                // 设置editor只读
                $scope.config.readonly = true;
            }

            // 获取HTML回调
            $scope.getHtmlCallback = function (text) {
                console.log(text);
                $scope.model.content = text;
            };

            // 保存
            $scope.ok = function () {

                if ($scope.config.readonly) {
                    delete initInfo.readonly;
                    $state.go('backend.article', {initInfo: JSON.stringify(initInfo)});
                    return;
                }

                if ($scope.model.content === '') {
                    showMess('danger', '文本不可为空');
                    return;
                }

                if (initInfo.initObj) {
                    commonService.updateHTML($scope, $scope.model.content);
                } else {
                    commonService.uploadHTML($scope, $scope.model.content);
                }
            };

            // 上传HTML回调
            $scope.uploadCallback = function (path) {
                var data = angular.copy($scope.model);
                data.textPath = path;
                if (data.gid) {
                    data.gid = initInfo.gid;
                } else if (data.pid) {
                    data.pid = initInfo.pid;
                }
                data.flag = 1;
                delete data.content;
                $.ajax({
                    url: initInfo.ajaxUrl,
                    type: 'POST',
                    data: data,
                    success: function () {
                        showMess('success', '保存成功', initInfo.backState);
                    },
                    error: function (err) {
                        console.log(err);
                        showMess('danger', '保存方案失败');
                    }
                });
            };

            // 更新HTML回调
            $scope.updateHtmlCallback = function () {
                var data = angular.copy($scope.model);
                if (data.gid) {
                    data.gid = initInfo.gid;
                } else if (data.pid) {
                    data.pid = initInfo.pid;
                }
                data.flag = 1;
                delete data.content;
                $.ajax({
                    url: initInfo.ajaxUrl,
                    type: 'PUT',
                    data: data,
                    success: function () {
                        showMess('success', '更新成功', initInfo.backState);
                    },
                    error: function (err) {
                        console.log(err);
                        showMess('danger', '更新方案失败');
                    }
                })
            };

            // 返回上一级
            $scope.cancel = function () {
                $state.go(initInfo.backState);
            };

            function showMess(type, data, backState) {
                commonService.showMessage($scope, {
                    type: type,
                    content: data
                }, backState);
            }
        }];

    var backendModule = angular.module('backend.config');
    backendModule.controller('articleCtrl', articleCtrl);
});
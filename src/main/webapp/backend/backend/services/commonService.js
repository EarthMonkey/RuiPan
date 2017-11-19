/**
 * Created by L.H.S on 2017/11/16.
 */

define([''], function () {
    'use strict';

    var commonService = function ($uibModal, $timeout, $state) {

        var service = this;

        // 消息提示
        service.showMessage = function ($scope, message, state) {
            $scope.message = message;
            var time = 3000;
            if (message.type == 'danger') {
                time = 5000;
            }
            if (state) {
                time = 1000;
            }
            $timeout(function () {
                $scope.message = '';
                if (state) {
                    $state.go(state);
                }
            }, time);
        };

        // 删除确认
        service.confirm = function (content) {
            return $uibModal.open({
                animation: true,
                backdrop: 'static',
                templateUrl: 'backend/backend/views/modals/message.html',
                controller: 'messageCtrl',
                resolve: {
                    content: function () {
                        return content;
                    }
                }
            });
        };

        // 打开textForm
        service.openTextForm = function (title, fields, initObj) {
            return $uibModal.open({
                animation: true,
                backdrop: 'static',
                keyboard: false,
                templateUrl: 'backend/backend/views/modals/textForm.html',
                controller: 'textFormCtrl',
                resolve: {
                    title: function () {
                        return title;
                    },
                    fields: function () {
                        return fields;
                    },
                    initObj: function () {
                        return initObj;
                    }
                }
            });
        };

        // 打开文本编辑器
        service.openEditor = function (title, initHtml, limit) {
            return $uibModal.open({
                animation: true,
                templateUrl: 'backend/backend/views/modals/editorModal.html',
                controller: 'editorCtrl',
                backdrop: 'static',
                keyboard: false,
                size: 'lg',
                resolve: {
                    title: function () {
                        return title;
                    },
                    initHtml: function () {
                        return initHtml;
                    },
                    limit: function () {
                        return limit;
                    }
                }
            });
        };

        // 上传html
        service.uploadHTML = function ($scope, text) {
            $.ajax({
                url: '/uploadText',
                type: 'POST',
                data: {text: text},
                success: function (resp) {
                    console.log(resp);
                    $scope.uploadCallback(resp);
                },
                error: function (err) {
                    console.log(err);
                    service.showMessage($scope, {type: 'danger', message: '上传文本失败'});
                }
            })
        };
        
        // 获取html
        service.getHTML = function ($scope, path) {
            $.ajax({
                url: '/getText?path=' + path,
                type: 'GET',
                success: function (resp) {
                    console.log(resp);
                    $scope.getHtmlCallback(resp);
                },
                error: function (err) {
                    console.log(err);
                    service.showMessage($scope, {type: 'danger', message: '获取文本失败'});
                }
            })
        };

        // 更新HTML
        service.updateHTML = function ($scope, text, path) {
            $.ajax({
                url: "/updateText",
                type: 'PUT',
                data: {
                    text: text,
                    fileName: path
                },
                success: function (resp) {
                    service.showMessage($scope, {type: 'success', message: '更新成功'});
                    $scope.updateHtmlCallback();
                },
                error: function (err) {
                    console.log(err);
                    service.showMessage($scope, {type: 'danger', message: '更新文本失败'});
                }
            })
        };

        // 显示加载条
        $(document).ajaxStart(function () {
            showLoading();
        });

        $(document).ajaxStop(function () {
            hideLoading();
        });

        var loadInstance;

        function showLoading() {
            loadInstance = $uibModal.open({
                animation: true,
                backdrop: 'static',
                keyboard: false,
                templateUrl: 'backend/backend/views/modals/loading.html'
            });
        }

        function hideLoading() {
            if (loadInstance)
                loadInstance.close();
        }
    };

    var backendModule = angular.module('backend.config');
    backendModule.service('commonService', commonService);
});
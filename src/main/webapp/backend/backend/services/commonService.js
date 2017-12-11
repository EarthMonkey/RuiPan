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
                    $timeout(function () {
                        $state.go(state);
                    }, 100);
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

        // 格式化时间
        Date.prototype.Format = function (fmt) {
            var o = {
                "M+": this.getMonth() + 1, //Month
                "d+": this.getDate(), //Day
                "h+": this.getHours(), //Hour
                "m+": this.getMinutes(), //Minute
                "s+": this.getSeconds(), //Second
                "q+": Math.floor((this.getMonth() + 3) / 3), //Season
                "S": this.getMilliseconds() //millesecond
            };
            if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
            for (var k in o)
                if (new RegExp("(" + k + ")").test(fmt))
                    fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            return fmt;
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
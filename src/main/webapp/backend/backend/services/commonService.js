/**
 * Created by L.H.S on 2017/11/16.
 */

define([''], function () {
    'use strict';

    var commonService = function ($uibModal, $timeout) {

        var service = this;

        // 消息提示
        service.showMessage = function ($scope, message) {
            $scope.message = message;
            var time = 3000;
            if (message.type == 'danger') {
                time = 5000;
            }
            $timeout(function () {
                $scope.message = '';
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
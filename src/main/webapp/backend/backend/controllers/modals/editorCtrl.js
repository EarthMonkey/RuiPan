/**
 * Created by L.H.S on 2017/11/16.
 */

define([''], function () {
    'use strict';

    var editorCtrl = ['$scope', '$uibModalInstance', '$timeout', 'title', 'initHtml', 'limit',
        function ($scope, $uibModalInstance, $timeout, title, initHtml, limit) {

            $scope.title = title;  // 标题
            // 数据模型
            $scope.model = "";
            if (initHtml) {
                $scope.model = angular.copy(initHtml);
            }

            $scope.config = {};
            if (limit) {
                $scope.config.maximumWords = limit;
            }

            $scope.ok = function () {
                var model = angular.copy($scope.model);
                if (model === "") {
                    showError("文本不能为空");
                    return;
                }
                $uibModalInstance.close(model);
            };

            $scope.cancel = function () {
                $uibModalInstance.dismiss('cancel');
            };

            function showError(message) {
                $scope.modalMess = message;
                $timeout(function () {
                    $scope.modalMess = "";
                }, 3000);
            }
        }];

    var backendModule = angular.module('backend.config');
    backendModule.controller('editorCtrl', editorCtrl);
});
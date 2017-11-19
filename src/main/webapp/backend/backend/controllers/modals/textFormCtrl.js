/**
 * Created by L.H.S on 2017/11/16.
 */

define([''], function () {
    'use strict';

    var textFormCtrl = ['$scope', '$uibModalInstance', '$timeout', 'title', 'fields', 'initObj',
        function ($scope, $uibModalInstance, $timeout, title, fields, initObj) {

            $scope.title = title;  // 标题
            $scope.fields = fields;  // id,label,placeholder
            // 数据模型
            $scope.model = {};
            if (initObj) {
                $scope.model = angular.copy(initObj);
            } else {
                fields.forEach(function (item) {
                    $scope.model[item.id] = "";
                    if (item.type == 'checkbox') {
                        $scope.model[item.id] = false;
                    }
                });
            }

            // 下拉框
            $scope.comboxSelect = function (op, id) {
                $scope.model[id] = op;
            };

            $scope.ok = function () {

                for (var key in $scope.model) {
                    if ($scope.model[key] === "") {
                        showError("请填写完整信息");
                        return;
                    }
                }
                $uibModalInstance.close($scope.model);
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
    backendModule.controller('textFormCtrl', textFormCtrl);
});
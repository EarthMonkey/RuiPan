/**
 * Created by L.H.S on 2017/11/16.
 */

define([''], function () {
    'use strict';

    var textFormCtrl = ['$scope', '$uibModalInstance', 'title', 'fields', 'initObj',
        function ($scope, $uibModalInstance, title, fields, initObj) {

            $scope.title = title;  // 标题
            $scope.fields = fields;  // id,label
            // 数据模型
            $scope.model = {};
            if (initObj) {
                $scope.model = initObj;
            }

            $scope.ok = function () {
                $uibModalInstance.close($scope.model);
            };

            $scope.cancel = function () {
                $uibModalInstance.dismiss('cancel');
            };
        }];

    var backendModule = angular.module('backend.config');
    backendModule.controller('textFormCtrl', textFormCtrl);
});
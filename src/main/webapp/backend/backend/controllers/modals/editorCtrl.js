/**
 * Created by L.H.S on 2017/11/16.
 */

define([''], function () {
    'use strict';

    var editorCtrl = ['$scope', '$uibModalInstance', 'title', 'initHtml',
        function ($scope, $uibModalInstance, title, initHtml) {

            $scope.title = title;  // 标题
            // 数据模型
            $scope.model = "请输入……";
            if (initHtml) {
                $scope.model = initHtml;
            }

            $scope.ok = function () {
                $uibModalInstance.close($scope.model);
            };

            $scope.cancel = function () {
                $uibModalInstance.dismiss('cancel');
            };
        }];

    var backendModule = angular.module('backend.config');
    backendModule.controller('editorCtrl', editorCtrl);
});
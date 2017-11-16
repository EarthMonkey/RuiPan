/**
 * Created by L.H.S on 2017/11/16.
 */

define([''], function () {
    'use strict';

    var messageCtrl = ['$scope', '$uibModalInstance', 'content',
        function ($scope, $uibModalInstance, content) {

            $scope.content = content;

            $scope.ok = function () {
                $uibModalInstance.close(true);
            };

            $scope.cancel = function () {
                $uibModalInstance.dismiss(false);
            };
        }];

    var backendModule = angular.module('backend.config');
    backendModule.controller('messageCtrl', messageCtrl);
});
/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var userCtrl = ['$scope', function ($scope) {

        $scope.test = "用户管理";

    }];

    var backendModule = angular.module('backend.config');
    backendModule.controller('userCtrl', userCtrl);
});
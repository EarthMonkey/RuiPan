/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var schoolInfoCtrl = ['$scope', function ($scope) {

        $scope.test = "院校管理-院校信息";

    }];

    var backendModule = angular.module('backend.config');
    backendModule.controller('schoolInfoCtrl', schoolInfoCtrl);
});
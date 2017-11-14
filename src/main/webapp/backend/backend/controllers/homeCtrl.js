/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var homeCtrl = ['$scope', function ($scope) {

        $scope.test = "首页管理";

    }];

    var backendModule = angular.module('backend.config');
    backendModule.controller('homeCtrl', homeCtrl);
});
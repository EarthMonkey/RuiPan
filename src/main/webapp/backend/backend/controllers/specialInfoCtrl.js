/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var specialInfoCtrl = ['$scope', function ($scope) {

        $scope.test = "专业管理-信息管理";

    }];

    var backendModule = angular.module('backend.config');
    backendModule.controller('specialInfoCtrl', specialInfoCtrl);
});
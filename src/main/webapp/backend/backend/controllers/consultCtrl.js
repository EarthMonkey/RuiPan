/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var consultCtrl = ['$scope', function ($scope) {

        $scope.test = "顾问管理";

    }];

    var backendModule = angular.module('backend.config');
    backendModule.controller('consultCtrl', consultCtrl);
});
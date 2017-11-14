/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var cooperationCtrl = ['$scope', function ($scope) {

        $scope.test = "合作办学";

    }];

    var backendModule = angular.module('backend.config');
    backendModule.controller('cooperationCtrl', cooperationCtrl);
});
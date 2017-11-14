/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var successCtrl = ['$scope', function ($scope) {

        $scope.test = "案例管理";

    }];

    var backendModule = angular.module('backend.config');
    backendModule.controller('successCtrl', successCtrl);
});
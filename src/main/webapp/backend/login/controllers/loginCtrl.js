/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var loginCtrl = ['$scope', function ($scope) {

        $scope.test = "welcome to login"

    }];

    var loginModule = angular.module('login.config');
    loginModule.controller('loginCtrl', loginCtrl);
});
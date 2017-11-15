/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var loginCtrl = ['$scope', '$state', function ($scope, $state) {

        $scope.user = {
            username: '',
            password: ''
        };

        // 登录
        $scope.login = function () {
            var sessionStorage = window.sessionStorage;
            sessionStorage.setItem("user", $scope.user.username);

            $state.go('backend.abroadCountry');
        };

    }];

    var loginModule = angular.module('login.config');
    loginModule.controller('loginCtrl', loginCtrl);
});
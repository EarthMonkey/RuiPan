/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var loginCtrl = ['$scope', '$rootScope', '$state', function ($scope, $rootScope, $state) {

        $scope.user = {
            username: '',
            password: ''
        };

        // 登录
        $scope.login = function () {
            $rootScope.USER = angular.copy($scope.user.username);
            $state.go('backend.abroadCountry');
        };

    }];

    var loginModule = angular.module('login.config');
    loginModule.controller('loginCtrl', loginCtrl);
});
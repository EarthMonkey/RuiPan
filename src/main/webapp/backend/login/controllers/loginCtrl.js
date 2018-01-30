/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var loginCtrl = ['$scope', '$state', '$timeout', function ($scope, $state, $timeout) {

        $scope.user = {
            username: '',
            password: ''
        };

        // 登录
        $scope.login = function () {

            if ($scope.user.username == '') {
                showError('请输入用户名');
                return;
            }

            if ($scope.user.password == '') {
                showError('请输入密码');
                return;
            }

            $.ajax({
                url: 'login',
                type: 'POST',
                data: {
                    username: $scope.user.username,
                    password: $scope.user.password
                },
                success: function (resp) {
                    if (resp === 'success') {
                        $state.go('backend.abroadCountry');

                    } else if (resp === 'no_user') {
                        $timeout(function () {
                            showError('用户名不存在');
                        });
                    } else {
                        $timeout(function () {
                            showError('密码错误');
                        });
                    }

                },
                error: function (err) {
                    console.log(err);
                    showError('连接失败，请稍后再试');
                }
            });
        };

        $scope.errorTip = '';
        function showError(content) {
            $scope.errorTip = content;
            $timeout(function () {
                $scope.errorTip = '';
            }, 3000);
        }

    }];

    var loginModule = angular.module('login.config');
    loginModule.controller('loginCtrl', loginCtrl);
});
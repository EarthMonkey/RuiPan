/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var userCtrl = ['$scope', 'commonService', function ($scope, commonService) {

        $.ajax({
            url: '/getUserMessage',
            type: 'GET',
            success: function (resp) {
                resp.lastLoginTime = new Date(resp.lastLoginTime).Format("yyyy-MM-dd hh:mm:ss")
                $scope.userInfo = resp;
            },
            error: function (err) {
                console.log(err);
                showMess('danger', '获取用户信息失败');
            }
        });

        var pwdFields = [
            {id: 'oldPassword'}
        ];
        $scope.changePwd = function () {

        };

        function showMess(type, data) {
            commonService.showMessage($scope, {
                type: type,
                content: data
            });
        }
    }];

    var backendModule = angular.module('backend.config');
    backendModule.controller('userCtrl', userCtrl);
});
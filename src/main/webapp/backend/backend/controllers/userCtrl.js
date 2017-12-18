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
            {id: 'oldPassword', label: '原密码', type: 'password'},
            {id: 'newPassword', label: '新密码', type: 'password'},
            {id: 'confirmPass', label: '确认密码', type: 'password'}
        ];
        
        $scope.changePwd = function () {
            commonService.openTextForm('修改密码', pwdFields).result
                .then(function (data) {
                    $.ajax({
                        url: '/changePassword',
                        type: 'PUT',
                        data: {
                            oldPassword: data.oldPassword,
                            newPassword: data.newPassword
                        },
                        success: function (resp) {
                            if (resp === 'success') {
                                showMess('success', '修改成功');
                            } else if (resp === 'wrong_password') {
                                showMess('danger', '原密码错误');
                            }
                        },
                        error: function (err) {
                            console.log(err);
                            showMess('danger', '更改失败');
                        }
                    })
                });
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
/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var userCtrl = ['$scope', 'commonService', function ($scope, commonService) {

        $.ajax({
            url: 'getUserMessage',
            type: 'GET',
            success: function (resp) {
                resp.lastLoginTime = new Date(resp.lastLoginTime).Format("yyyy-MM-dd hh:mm:ss");
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
                        url: 'changePassword',
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

        $scope.userList = [];
        // 获取所有user
        $.ajax({
            url: 'getAllUser',
            type: 'GET',
            success: function (resp) {
                resp.forEach(function (item) {
                    if (item.lastLoginTime) {
                        item.lastLoginTime = new Date(item.lastLoginTime).Format("yyyy-MM-dd hh:mm:ss");
                    }
                });
                $scope.userList = resp;
            },
            error: function (err) {
                console.log(err);
                showMess('danger', '获取用户失败');
            }
        });

        // 添加user
        $scope.addUser = function () {

            var field = [
                {id: 'username', label: '用户名'},
                {id: 'newPassword', label: '新密码', type: 'password'},
                {id: 'confirmPass', label: '确认密码', type: 'password'}
            ];

            commonService.openTextForm('添加用户', field).result
                .then(function (data) {
                    $.ajax({
                        url: 'addUser',
                        type: 'POST',
                        data: {
                            username: data.username,
                            password: data.newPassword
                        },
                        success: function (resp) {
                            if (resp === "no_permission") {
                                showMess('danger', '没有管理员权限');
                            } else {
                                $scope.userList.push(data);
                                showMess('success', '添加成功');
                            }
                        },
                        error: function (err) {
                            console.log(err);
                            showMess('danger', '添加失败');
                        }
                    })
                });

        };


        // 删除user
        $scope.deleteUser = function (item, pos) {
            commonService.confirm('用户：' + item.username).result
                .then(function (resp) {
                    if (resp) {
                        $.ajax({
                            url: 'deleteUser?username=' + item.username,
                            type: 'DELETE',
                            success: function () {
                                showMess('success', '删除成功');
                                $scope.userList.splice(pos, 1);
                            },
                            error: function (err) {
                                console.log(err);
                                showMess('danger', '删除失败');
                            }
                        })
                    }
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
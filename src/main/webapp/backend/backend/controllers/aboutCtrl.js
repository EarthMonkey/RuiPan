/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var aboutCtrl = ['$scope', '$state', 'commonService', function ($scope, $state, commonService) {

        $scope.coopList = [];
        $scope.toReply = []; // 未答复
        $scope.hasReply = []; // 已答复

        // 获取公司介绍
        $.ajax({
            url: 'Configure/getCompanyInformation',
            type: 'GET',
            success: function (resp) {
                $scope.companyInfo = resp;
            },
            error: function (err) {
                console.log(err);
                showMess('danger', '获取公司介绍失败');
            }
        });

        var introField = [{
            id: 'introduce',
            label: '公司介绍',
            type: 'textarea'
        }];
        // 更新公司介绍
        $scope.modIntroduce = function () {
            commonService.openTextForm('更新公司介绍', introField, {introduce: $scope.companyInfo.introduce.value})
                .result.then(function (data) {
                    $.ajax({
                        url: 'Configure/update',
                        type: 'PUT',
                        data: {
                            id: $scope.companyInfo.introduce.id,
                            value: data.introduce
                        },
                        success: function () {
                            $scope.companyInfo.introduce.value = data.introduce;
                            showMess('success', '更新成功');
                        },
                        error: function (err) {
                            console.log(err);
                            showMess('danger', '更新失败');
                        }
                    })
            })
        };

        var characterField = [{
            id: 'character',
            label: '特点描述',
            type: 'textarea'
        }];

        // 更新公司特点
        $scope.modCharacter = function (id) {
            commonService.openTextForm("更新公司特点", characterField, {character: $scope.companyInfo[id].value})
                .result.then(function (data) {
                    $.ajax({
                        url: 'Configure/update',
                        type: 'PUT',
                        data: {
                            id: $scope.companyInfo[id].id,
                            value: data.character
                        },
                        success: function () {
                            $scope.companyInfo[id].value = data.character;
                            showMess('success', '更新成功');
                        },
                        error: function (err) {
                            console.log(err);
                            showMess('danger', '更新失败');
                        }
                    })
            });
        };

        // 获取联系方式
        $.ajax({
            url: 'Configure/getContactInformation',
            type: 'GET',
            success: function (resp) {
                $scope.contactInfo = resp;
            },
            error: function (err) {
                console.log(err);
                showMess('danger', '获取联系方式失败');
            }
        });

        // 更新联系方式
        $scope.modContact = function (id, label) {
            var contactField = [{
                id: 'contact',
                label: label,
                type: 'textarea'
            }];

            if (id === 'qrcode') {
                contactField[0].type = 'img';
            }

            commonService.openTextForm("更新联系方式", contactField, {contact: $scope.contactInfo[id].value})
                .result.then(function (data) {
                $.ajax({
                    url: 'Configure/update',
                    type: 'PUT',
                    data: {
                        id: $scope.contactInfo[id].id,
                        value: data.contact
                    },
                    success: function () {
                        $scope.contactInfo[id].value = data.contact;
                        showMess('success', '更新成功');
                    },
                    error: function (err) {
                        console.log(err);
                        showMess('danger', '更新失败');
                    }
                })
            });
        };

        // 获取合作学校
        $.ajax({
            url: 'AboutUs/getCooperativePartner',
            type: 'GET',
            success: function (resp) {
                $scope.coopList = resp;
            },
            error: function (err) {
                console.log(err);
                showMess('danger', '获取合作学校失败');
            }
        });

        // 获取未答复
        $.ajax({
            url: 'AboutUs/getUnreplyedContactUs',
            type: 'GET',
            success: function (resp) {
                $scope.toReply = resp;
            },
            error: function (err) {
                console.log(err);
                showMess('danger', '获取未答复消息失败');
            }
        });

        // 获取已答复
        $.ajax({
            url: 'AboutUs/getReplyedContactUs',
            type: 'GET',
            success: function (resp) {
                $scope.hasReply = resp;
            },
            error: function (err) {
                console.log(err);
                showMess('danger', '获取已答复失败');
            }
        });

        var coopFields = [
            {id: 'name', label: '单位名称'},
            {id: 'country', label: '所属国家'},
            {id: 'category', label: '所属类型'},
            {id: 'cooperateAt', label: '开始时间'},
            {id: 'birefIntroduce', label: '单位简介', type: 'textarea'},
            {id: 'imagePath', label: 'logo图标', type: 'img'}
        ];

        // 增加合作
        $scope.addCoop = function () {

            commonService.openTextForm('添加合作', coopFields).result
                .then(function (data) {
                    $.ajax({
                        url: 'AboutUs/addCooperativePartner',
                        type: 'POST',
                        data: data,
                        success: function (resp) {
                            $scope.coopList.push(resp);
                            showMess('success', '添加成功');
                        },
                        error: function (err) {
                            console.log(err);
                            showMess('danger', '添加失败');
                        }
                    })
                });

        };

        // 修改合作
        $scope.modCoop = function (item, pos) {

            commonService.openTextForm('修改合作', coopFields, item).result
                .then(function (data) {
                    data.id = item.id;
                    $.ajax({
                        url: 'AboutUs/updateCooperativePartner',
                        type: 'PUT',
                        data: data,
                        success: function (resp) {
                            $scope.coopList[pos] = data;
                            showMess('success', '修改成功');
                        },
                        error: function (err) {
                            console.log(err);
                            showMess('danger', '修改失败');
                        }
                    })
                });

        };

        // 删除合作
        $scope.delCoop = function (item, pos) {
            commonService.confirm('合作：' + item.name).result
                .then(function (resp) {
                    if (resp) {
                        $.ajax({
                            url: 'AboutUs/deleteCooperativePartner?id=' + item.id,
                            type: 'DELETE',
                            success: function () {
                                $scope.coopList.splice(pos, 1);
                                showMess('success', '删除成功');
                            },
                            error: function (err) {
                                console.log(err);
                                showMess('danger', '删除失败');
                            }
                        })
                    }
                });
        };

        $scope.tabs = [
            {title: '未处理', type: 0},
            {title: '已处理', type: 1}
        ];

        // 答复
        $scope.reply = function (item, pos) {

            var field = [
                {id: 'replyMessage', label: '答复记录', type: 'textarea'}
            ];

            commonService.openTextForm('答复消息', field).result
                .then(function (data) {
                    data.id = item.id;

                    $.ajax({
                        url: 'AboutUs/replyContactUs',
                        type: 'PUT',
                        data: data,
                        success: function (resp) {
                            $scope.toReply.splice(pos, 1);
                            $scope.hasReply.push(resp);
                            showMess('success', '答复成功');
                        },
                        error: function (err) {
                            console.log(err);
                            showMess('danger', '答复失败');
                        }
                    });

                });

        };

        // 删除未答复
        $scope.delToReply = function (item, pos) {

            commonService.confirm('消息：' + item.content).result
                .then(function (resp) {
                    if (resp) {
                        $.ajax({
                            url: 'AboutUs/deleteContactUs?id=' + item.id,
                            type: 'DELETE',
                            success: function () {
                                $scope.toReply.splice(pos, 1);
                                showMess('success', '删除成功');
                            },
                            error: function (err) {
                                console.log(err);
                                showMess('danger', '删除失败');
                            }
                        });

                    }
                });
        };

        // 删除已答复
        $scope.delHasReply = function (item, pos) {

            commonService.confirm('消息：' + item.content).result
                .then(function (resp) {
                    if (resp) {
                        $.ajax({
                            url: 'AboutUs/deleteContactUs?id=' + item.id,
                            type: 'DELETE',
                            success: function () {
                                $scope.hasReply.splice(pos, 1);
                                showMess('success', '删除成功');
                            },
                            error: function (err) {
                                console.log(err);
                                showMess('danger', '删除失败');
                            }
                        });

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
    backendModule.controller('aboutCtrl', aboutCtrl);
})
;
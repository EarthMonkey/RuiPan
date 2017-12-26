/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var homeCtrl = ['$scope', '$state', 'commonService', function ($scope, $state, commonService) {

        $scope.carousList = [];
        $scope.serviceList = [];

        // 获取轮播图
        $.ajax({
            url: '/homepage/getCarouselFigure?category=首页',
            type: 'GET',
            success: function (resp) {
                $scope.carousList = resp;
            },
            error: function (err) {
                console.log(err);
                showMess('danger', '获取轮播图失败');
            }
        });

        var carousFields = [
            {id: 'title', label: '一级标题'},
            {id: 'subTitle', label: '二级标题'},
            {id: 'link', label: '超链接'},
            {id: 'imagePath', label: '轮播图片', type: 'img'}
        ];

        // 增加轮播图
        $scope.addCarousel = function () {

            commonService.openTextForm('添加轮播图', carousFields).result
                .then(function (data) {
                    data.category = '首页';
                    $.ajax({
                        url: '/homepage/addCarouselFigure',
                        type: 'POST',
                        data: data,
                        success: function (resp) {
                            $scope.carousList.push(resp);
                            showMess('success', '添加成功');
                        },
                        error: function (err) {
                            console.log(err);
                            showMess('danger', '添加失败');
                        }
                    });
                });

        };

        // 修改轮播图
        $scope.modCarousel = function (item, pos) {

            commonService.openTextForm('修改轮播图', carousFields, item).result
                .then(function (data) {
                    data.category = '首页';
                    data.id = item.id;
                    $.ajax({
                        url: '/homepage/updateCarouselFigure',
                        type: 'PUT',
                        data: data,
                        success: function (resp) {
                            $scope.carousList[pos] = resp;
                            showMess('success', '修改成功');
                        },
                        error: function (err) {
                            console.log(err);
                            showMess('danger', '修改失败');
                        }
                    });
                });

        };

        // 删除轮播图
        $scope.delCarousel = function (item, pos) {
            commonService.confirm("轮播图：" + item.title).result
                .then(function (resp) {
                    if (resp) {
                        $.ajax({
                            url: '/homepage/deleteCarouselFigure?id=' + item.id,
                            type: 'DELETE',
                            success: function () {
                                $scope.carousList.splice(pos, 1);
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

        // 荣誉资质
        $scope.honours = [];

        $.ajax({
            url: '/homepage/getHonor',
            type: 'GET',
            success: function (resp) {
                $scope.honours = resp;
            },
            error: function (err) {
                console.log(err);
                showMess('danger', '获取荣誉资质失败');
            }
        });

        $scope.addHonour = function () {
            var fields = [
                {id: 'name', label: '荣誉名称'},
                {id: 'getAt', label: '获取时间'},
                {id: 'imagePath', label: '证书图片', type: 'img'}
            ];

            commonService.openTextForm('添加荣誉资质', fields).result
                .then(function (data) {
                    $.ajax({
                        url: '/homepage/addHonor',
                        type: 'POST',
                        data: data,
                        success: function (resp) {
                            $scope.honours.push(resp);
                            showMess('success', '添加成功');
                        },
                        error: function (err) {
                            console.log(err);
                            showMess('danger', '添加荣誉资质失败');
                        }
                    });
                });
        };

        $scope.delHonour = function (item, pos) {
            commonService.confirm("荣誉资质：" + item.name).result
                .then(function (resp) {
                    if (resp) {

                        $.ajax({
                            url: '/homepage/deleteHonor?id=' + item.id,
                            type: 'DELETE',
                            success: function () {
                                $scope.honours.splice(pos, 1);
                                showMess('success', '删除成功');
                            },
                            error: function (err) {
                                console.log(err);
                                showMess('danger', '删除荣誉资质失败');
                            }
                        });

                    }
                });
        };

        // 服务的学校和企业
        $.ajax({
            url: '/homepage/getServedCompany',
            type: 'GET',
            success: function (resp) {
                $scope.serviceList = resp;
            },
            error: function (err) {
                console.log(err);
                showMess('danger', '获取服务的学校失败');
            }
        });

        var coopFields = [
            {id: 'name', label: '单位名称'},
            {id: 'country', label: '所属国家'},
            {id: 'category', label: '所属类型'},
            {id: 'briefIntroduce', label: '单位简介', type: 'textarea'},
            {id: 'imagePath', label: 'logo图标', type: 'img'}
        ];

        // 增加合作
        $scope.addCoop = function () {

            commonService.openTextForm('添加服务', coopFields).result
                .then(function (data) {
                    $.ajax({
                        url: '/homepage/addServedCompany',
                        type: 'POST',
                        data: data,
                        success: function (resp) {
                            $scope.serviceList.push(resp);
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

            commonService.openTextForm('修改服务', coopFields, item).result
                .then(function (data) {
                    data.id = item.id;
                    $.ajax({
                        url: '/homepage/updateServedCompany',
                        type: 'PUT',
                        data: data,
                        success: function (resp) {
                            $scope.serviceList[pos] = data;
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
            commonService.confirm('服务：' + item.name).result
                .then(function (resp) {
                    if (resp) {
                        $.ajax({
                            url: '/homepage/deleteServedCompany?id=' + item.id,
                            type: 'DELETE',
                            success: function () {
                                $scope.serviceList.splice(pos, 1);
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

        function showMess(type, data) {
            commonService.showMessage($scope, {
                type: type,
                content: data
            });
        }

    }];

    var backendModule = angular.module('backend.config');
    backendModule.controller('homeCtrl', homeCtrl);
});
/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var homeCtrl = ['$scope', '$state', 'commonService', function ($scope, $state, commonService) {

        $scope.carousList = [];

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
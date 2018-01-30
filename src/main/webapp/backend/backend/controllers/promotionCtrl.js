/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var promotionCtrl = ['$scope', '$state', 'commonService', function ($scope, $state, commonService) {

        // 轮播图
        $scope.carousList = [];

        // 获取轮播图
        $.ajax({
            url: 'homepage/getCarouselFigure?category=背景提升',
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
                    data.category = '背景提升';
                    $.ajax({
                        url: 'homepage/addCarouselFigure',
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
                    data.category = '背景提升';
                    data.id = item.id;
                    $.ajax({
                        url: 'homepage/updateCarouselFigure',
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
                            url: 'homepage/deleteCarouselFigure?id=' + item.id,
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


        $scope.selectedType = '游学';
        $scope.filterType = ['游学', '实习', '科研', '国际义工', '短期项目'];

        $scope.filterClick = function (selected) {
            $scope.selectedType = selected;
            getProject();
        };

        $scope.methodList = [];

        // 获取项目
        function getProject() {
            $.ajax({
                url: 'Promotion/getBackgroundPromoteByCategory?category=' + $scope.selectedType,
                type: 'GET',
                success: function (resp) {
                    $scope.methodList = resp;
                },
                error: function (err) {
                    console.log(err);
                    showMess('danger', '获取项目失败');
                }
            })
        }

        getProject();

        var methodField = [
            {id: "title", label: '项目名称'},
            {id: 'synopsis', label: '项目导语'},
            {id: 'thumbnail', label: '宣传图片', type: 'img'}
        ];

        // 增加方案
        $scope.addMethod = function () {

            var initInfo = {
                title: '添加' + $scope.selectedType + '项目',
                fields: methodField,
                backState: 'backend.promotion',
                ajaxUrl: 'Promotion/addBackgroundPromote',
                category: $scope.selectedType,
                gidKey: 'category'
            };

            $state.go("backend.article", {initInfo: JSON.stringify(initInfo)});
        };

        // 修改方案
        $scope.modMethod = function (item) {

            var initInfo = {
                title: '添加' + $scope.selectedType + '项目',
                fields: methodField,
                backState: 'backend.promotion',
                ajaxUrl: 'Promotion/updateBackgroundPromote',
                category: $scope.selectedType,
                gidKey: 'category',
                initObj: {
                    objId: item.id,
                    url: 'Promotion/getBackgroundPromote?id=' + item.id
                }
            };

            $state.go("backend.article", {initInfo: JSON.stringify(initInfo)});
        };

        // 删除方案
        $scope.delMethod = function (item, pos) {

            commonService.confirm('项目：' + item.title).result
                .then(function (resp) {
                    if (resp) {
                        $.ajax({
                            url: 'Promotion/deleteBackgroundPromote?id=' + item.id,
                            type: 'DELETE',
                            success: function () {
                                $scope.methodList.splice(pos, 1);
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

        // 查看方案
        $scope.getMethod = function (item) {

            var initInfo = {
                title: $scope.selectedType + '项目',
                fields: methodField,
                backState: 'backend.promotion',
                ajaxUrl: 'Promotion/updateBackgroundPromote',
                category: $scope.selectedType,
                gidKey: 'category',
                initObj: {
                    objId: item.id,
                    url: 'Promotion/getBackgroundPromote?id=' + item.id
                },
                readonly: true
            };

            $state.go("backend.article", {initInfo: JSON.stringify(initInfo)});
        };


        function showMess(type, data) {
            commonService.showMessage($scope, {
                type: type,
                content: data
            });
        }
    }];

    var backendModule = angular.module('backend.config');
    backendModule.controller('promotionCtrl', promotionCtrl);
});
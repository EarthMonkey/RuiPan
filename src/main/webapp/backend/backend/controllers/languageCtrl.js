/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var languageCtrl = ['$scope', '$state', 'commonService', function ($scope, $state, commonService) {

        $scope.selectedType = '校长教师培训';
        $scope.filterType = ['校长教师培训', '英语培训', '中文培训', '小语种培训', '其他'];

        $scope.filterClick = function (selected) {
            $scope.selectedType = selected;
            getData();
        };

        $scope.introList = [];
        $scope.carousList = [];
        $scope.newsList = [];

        getData();
        function getData() {

            // 轮播图
            $.ajax({
                url: '/homepage/getCarouselFigure?category=' + $scope.selectedType,
                type: 'GET',
                success: function (resp) {
                    $scope.carousList = resp;
                },
                error: function (err) {
                    console.log(err);
                    showMess('danger', '获取轮播图失败');
                }
            });

            // 资讯
            $.ajax({
                url: '/News/getAllNewsByCategory?category=' + $scope.selectedType,
                type: 'GET',
                success: function (resp) {
                    $scope.newsList = resp;
                },
                error: function (err) {
                    console.log(err);
                    showMess('danger', '获取资讯失败');
                }
            });

            // 介绍信息
            $.ajax({
                url: '/LanguageTraining/getTrainIntroducePublish?category=' + $scope.selectedType,
                type: 'GET',
                success: function (resp) {
                    $scope.introList = resp;
                },
                error: function (err) {
                    console.log(err);
                    showMess('danger', '获取介绍信息失败');
                }
            });
        }

        var carousFields = [
            {id: 'category', label: '选择类别', type: 'combox', options: $scope.filterType},
            {id: 'title', label: '一级标题'},
            {id: 'subTitle', label: '二级标题'},
            {id: 'link', label: '超链接'},
            {id: 'imagePath', label: '轮播图片', type: 'img'}
        ];

        // 增加轮播图
        $scope.addCarousel = function () {

            commonService.openTextForm('添加轮播图', carousFields).result
                .then(function (data) {
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

        // 资讯field
        var newsField = [
            {id: 'title', label: '资讯标题'},
            {id: 'synopsis', label: '资讯导语'}
        ];

        // 增加资讯
        $scope.addNews = function () {

            var initInfo = {
                title: '添加' + $scope.selectedType + '资讯',
                fields: newsField,
                backState: 'backend.language',
                ajaxUrl: '/News/addNews',
                category: $scope.selectedType,
                gidKey: 'category'
            };

            $state.go("backend.article", {initInfo: JSON.stringify(initInfo)});
        };

        // 修改资讯
        $scope.modNews = function (item, pos) {
            var initInfo = {
                title: '修改' + $scope.selectedType + '资讯',
                fields: newsField,
                backState: 'backend.language',
                ajaxUrl: '/News/updateNews',
                category: $scope.selectedType,
                gidKey: 'category',
                initObj: {
                    objId: item.id,
                    url: '/News/getNewsById?id=' + item.id
                }
            };

            $state.go("backend.article", {initInfo: JSON.stringify(initInfo)});
        };

        // 删除资讯
        $scope.delNews = function (item, pos) {

            commonService.confirm('资讯:' + item.title).result
                .then(function (resp) {
                    if (resp) {

                        $.ajax({
                            url: '/News/deleteNews?id=' + item.id,
                            type: 'DELETE',
                            success: function () {
                                $scope.newsList.splice(pos, 1);
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

        // 查看资讯
        $scope.getNews = function (item) {
            var initInfo = {
                title: '修改' + $scope.selectedType + '资讯',
                fields: newsField,
                backState: 'backend.language',
                ajaxUrl: '/News/updateNews',
                category: $scope.selectedType,
                gidKey: 'category',
                initObj: {
                    objId: item.id,
                    url: '/News/getNewsById?id=' + item.id
                },
                readonly: true
            };

            $state.go("backend.article", {initInfo: JSON.stringify(initInfo)});
        };

        // 增加介绍
        $scope.addIntro = function () {

            var initInfo = {
                title: '添加' + $scope.selectedType + '介绍块',
                fields: [{id: 'title', label: '模块标题'}],
                backState: 'backend.language',
                ajaxUrl: '/LanguageTraining/addTrainIntroduce',
                category: $scope.selectedType,
                gidKey: 'category'
            };

            $state.go("backend.article", {initInfo: JSON.stringify(initInfo)});
        };

        // 修改介绍
        $scope.modIntro = function (item, pos) {

            var initInfo = {
                title: '修改' + $scope.selectedType + '介绍块',
                fields: [{id: 'title', label: '模块标题'}],
                backState: 'backend.language',
                ajaxUrl: '/LanguageTraining/updateTrainIntroduce',
                category: $scope.selectedType,
                gidKey: 'category',
                initObj: {
                    objId: item.id,
                    url: '/LanguageTraining/getTrainIntroduceById?id=' + item.id
                }
            };

            $state.go("backend.article", {initInfo: JSON.stringify(initInfo)});
        };

        // 删除介绍
        $scope.delIntro = function (item, pos) {

            commonService.confirm('介绍信息：' + item.title).result
                .then(function (resp) {
                    if (resp) {
                        $.ajax({
                            url: '/LanguageTraining/deleteTrainIntroduce?id=' + item.id,
                            type: 'DELETE',
                            success: function () {
                                $scope.introList.splice(pos, 1);
                                showMess('success', '删除成功');
                            },
                            error: function (err) {
                                console.log(err);
                                showMess('danger', '删除失败')
                            }
                        })
                    }
                });
        };

        // 获取介绍
        $scope.getIntro = function (item) {
            var initInfo = {
                title: '介绍块详情',
                fields: [{id: 'title', label: '模块标题'}],
                backState: 'backend.language',
                ajaxUrl: '/LanguageTraining/updateTrainIntroduce',
                category: $scope.selectedType,
                gidKey: 'category',
                initObj: {
                    objId: item.id,
                    url: '/LanguageTraining/getTrainIntroduceById?id=' + item.id
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
    backendModule.controller('languageCtrl', languageCtrl);
});
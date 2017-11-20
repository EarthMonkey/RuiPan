/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var cooperationCtrl = ['$scope', '$state', 'commonService', function ($scope, $state, commonService) {

        $scope.carousList = [];
        $scope.newsList = [];
        $scope.methodList = [];

        // 轮播图
        $.ajax({
            url: '/homepage/getCarouselFigure?category=合作办学',
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
                    data.category = '合作办学';
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
                    data.category = '合作办学';
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

        // 资讯列表
        $.ajax({
            url: '/News/getAllNewsByCategory?category=合作办学',
            type: 'GET',
            success: function (resp) {
                $scope.newsList = resp;
            },
            error: function (err) {
                console.log(err);
                showMess('danger', '获取资讯失败');
            }
        });

        // 资讯field
        var newsField = [
            {id: 'title', label: '资讯标题'},
            {id: 'synopsis', label: '资讯导语'}
        ];

        // 增加资讯
        $scope.addNews = function () {

            var initInfo = {
                title: '添加合作办学资讯',
                fields: newsField,
                backState: 'backend.cooperation',
                ajaxUrl: '/News/addNews',
                category: '合作办学',
                gidKey: 'category'
            };

            $state.go("backend.article", {initInfo: JSON.stringify(initInfo)});
        };

        // 修改资讯
        $scope.modNews = function (item) {
            var initInfo = {
                title: '修改合作办学资讯',
                fields: newsField,
                backState: 'backend.cooperation',
                ajaxUrl: '/News/updateNews',
                category: '合作办学',
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
                backState: 'backend.cooperation',
                ajaxUrl: '/News/updateNews',
                category: '合作办学',
                gidKey: 'category',
                initObj: {
                    objId: item.id,
                    url: '/News/getNewsById?id=' + item.id
                },
                readonly: true
            };

            $state.go("backend.article", {initInfo: JSON.stringify(initInfo)});
        };

        $scope.selectedType = '高中';
        $scope.filterType = ['高中', '大学', '职业学院'];

        $scope.selectedCoun = '';
        $scope.filterCountry = [];

        // 获取小分类
        getCountry();
        function getCountry() {

            $scope.selectedCoun = '';
            $scope.filterCountry = [];

            $.ajax({
                url: '/CooperativeEducation/getSubclassificationByCategory?category=' + $scope.selectedType,
                type: 'GET',
                success: function (resp) {
                    $scope.filterCountry = resp;
                    if (resp[0]) {
                        $scope.selectedCoun = resp[0];
                        getData();
                    }
                },
                error: function (err) {
                    console.log(err);
                    showMess('danger', '获取国家分类失败');
                }
            });
        }

        $scope.filterClick = function (syb, selected) {
            if (syb == 0) {
                $scope.selectedType = selected;
                getCountry();
            } else {
                $scope.selectedCoun = selected;
                getData();
            }
        };

        function getData() {

            if (!$scope.selectedCoun.id) {
                return;
            }

            $.ajax({
                url: '/CooperativeEducation/getCooperativeSchemeByCcid?ccid=' + $scope.selectedCoun.id,
                type: 'GET',
                success: function (resp) {
                    $scope.methodList = resp;
                },
                error: function (err) {
                    console.log(err);
                    showMess('danger', '获取方案列表失败');
                }
            });

        }

        // 增加合作方
        $scope.addCoop = function () {

            var coopFields = [
                {id: 'subclassification', label: '合作方'}
            ];

            var title = '添加' + $scope.selectedType + '合作方';
            commonService.openTextForm(title, coopFields).result
                .then(function (data) {
                    data.category = $scope.selectedType;

                    $.ajax({
                        url: '/CooperativeEducation/addCooperativeCategory',
                        type: 'POST',
                        data: data,
                        success: function (resp) {
                            $scope.filterCountry.push(resp);
                            showMess('success', '添加成功');
                        }
                    })
                });

        };

        // 删除合作方
        $scope.delCoop = function (item, pos) {

            commonService.confirm('合作方：' + item.subclassification).result
                .then(function (resp) {
                    if (resp) {
                        $.ajax({
                            url: '/CooperativeEducation/deleteCooperativeCategory?id=' + item.id,
                            type: 'DELETE',
                            success: function () {
                                $scope.filterCountry.splice(pos, 1);
                                showMess('success', '删除成功');

                                if ($scope.selectedCoun.id !== $scope.filterCountry[0].id) {
                                    $scope.selectedCoun = $scope.filterCountry[0];
                                    getData();
                                }
                            },
                            error: function (err) {
                                console.log(err);
                                showMess('danger', '删除失败');
                            }
                        })
                    }
                });

        };

        var methodField = [
            {id: "title", label: '方案名称'},
            {id: 'synopsis', label: '方案导语'},
            {id: 'thumbnail', label: '宣传图片', type: 'img'}
        ];

        // 增加方案
        $scope.addMethod = function () {

            var initInfo = {
                title: '添加' + $scope.selectedCoun.subclassification + $scope.selectedType + '合作办学方案',
                fields: methodField,
                backState: 'backend.cooperation',
                ajaxUrl: '/CooperativeEducation/addCooperativeScheme',
                ccid: $scope.selectedCoun.id,
                gidKey: 'ccid'
            };

            $state.go("backend.article", {initInfo: JSON.stringify(initInfo)});
        };

        // 修改方案
        $scope.modMethod = function (item) {

            var initInfo = {
                title: '添加' + $scope.selectedCoun.subclassification + $scope.selectedType + '合作办学方案',
                fields: methodField,
                backState: 'backend.cooperation',
                ajaxUrl: '/CooperativeEducation/updateCooperativeScheme',
                ccid: $scope.selectedCoun.id,
                gidKey: 'ccid',
                initObj: {
                    objId: item.id,
                    url: '/CooperativeEducation/getCooperativeSchemeById?id=' + item.id
                }
            };

            $state.go("backend.article", {initInfo: JSON.stringify(initInfo)});
        };

        // 删除方案
        $scope.delMethod = function (item, pos) {

            commonService.confirm('办学方案：' + item.title).result
                .then(function (resp) {
                    if (resp) {
                        $.ajax({
                            url: '/CooperativeEducation/deleteCooperativeScheme?id=' + item.id,
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
                title: '添加' + $scope.selectedCoun.fcsubclassification + $scope.selectedType + '合作办学方案',
                fields: methodField,
                backState: 'backend.cooperation',
                ajaxUrl: '/CooperativeEducation/updateCooperativeScheme',
                ccid: $scope.selectedCoun.id,
                gidKey: 'ccid',
                initObj: {
                    objId: item.id,
                    url: '/CooperativeEducation/getCooperativeSchemeById?id=' + item.id
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
    backendModule.controller('cooperationCtrl', cooperationCtrl);
});
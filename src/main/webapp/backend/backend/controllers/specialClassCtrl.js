/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var specialClassCtrl = ['$scope', 'commonService', function ($scope, commonService) {

        $scope.selectedCoun = '美国';
        $scope.filterCountry = [];

        // 获取国家
        $.ajax({
            url: "StudyAbroad/getAllCountry",
            type: 'GET',
            success: function (data) {
                $scope.filterCountry = data;
                $scope.selectedCoun = data[0];
                getData();
            },
            error: function (err) {
                console.log(err);
                showMess('danger', '获取国家列表失败');
            }
        });

        $scope.filterClick = function (selected) {
            $scope.selectedCoun = selected;
            getData();
        };

        // 数据
        $scope.spList = [];

        // 获取分类
        function getData() {
            $.ajax({
                url: 'Profession/getAllCategoryByCountry?country=' + $scope.selectedCoun,
                type: 'GET',
                success: function (resp) {
                    $scope.spList = [];
                    for (var key in resp) {
                        resp[key].forEach(function (item) {
                            $scope.spList.push(item);
                        });
                    }
                },
                error: function (err) {
                    console.log(err);
                    showMess('danger', '获取分类信息失败');
                }
            })
        }

        // 增加分类
        $scope.addSp = function () {
            var spFields = [
                {id: 'country', label: '选择国家', type: 'combox', options: $scope.filterCountry},
                {id: 'category', label: '选择大类', type: 'combox', options: ['商科', '理科', '工科', '文科', '艺术']},
                {id: 'subclassification', label: '专业名称'}
            ];

            var spInstance = commonService.openTextForm('专业分类管理', spFields);

            spInstance.result.then(function (data) {
                $.ajax({
                    url: 'Profession/addProfessionCategory',
                    type: 'POST',
                    data: data,
                    success: function (resp) {
                        $scope.spList.push(resp);
                        showMess('success', '添加成功');
                    },
                    error: function (err) {
                        console.log(err);
                        showMess('danger', '添加失败');
                    }
                });
            });
        };

        // 删除分类
        $scope.delSp = function (item, pos) {
            $.ajax({
                url: 'Profession/deleteProfessionCategory?pid=' + item.pid,
                type: 'DELETE',
                success: function () {
                    $scope.spList.splice(pos, 1);
                    showMess('success', '删除成功');
                },
                error: function (err) {
                    console.log(err);
                    showMess('danger', '删除失败');
                }
            })
        };

        function showMess(type, data) {
            commonService.showMessage($scope, {
                type: type,
                content: data
            });
        }

    }];

    var backendModule = angular.module('backend.config');
    backendModule.controller('specialClassCtrl', specialClassCtrl);
});
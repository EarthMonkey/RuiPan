/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var abroadCountryCtrl = ['$scope', '$timeout', 'commonService', function ($scope, $timeout, commonService) {

        commonService.showLoading();
        $scope.countryList = [];
        $.ajax({
            url: "/StudyAbroad/getAllCountry",
            type: 'GET',
            success: function (data) {
                commonService.hideLoading();
                $scope.countryList = data;
            },
            error: function (err) {
                console.log(err);
                commonService.showMessage($scope, {
                    type: 'danger',
                    content: '获取国家列表失败'
                });
            }
        });

        $scope.addCountry = function () {
            var modalInstance = commonService.openTextForm('留学国家管理',
                [{id: 'country', label: '留学国家'}]
            );

            modalInstance.result.then(function (resp) {
                commonService.showLoading();
                $.ajax({
                    url: "/StudyAbroad/addCountry",
                    type: 'POST',
                    data: resp,
                    success: function (data) {
                        if (data == "success") {
                            $timeout(function () {
                                commonService.showMessage($scope, {
                                    type: 'success',
                                    content: '添加成功'
                                });
                                $scope.countryList.push(resp.country);
                            });
                        } else {
                            commonService.showMessage($scope, {
                                type: 'danger',
                                content: data
                            });
                        }
                    },
                    error: function (err) {
                        console.log(err);
                        commonService.showMessage($scope, {
                            type: 'danger',
                            content: '添加失败'
                        });
                    }
                });
            });
        };

        $scope.delCountry = function (item, pos) {
            var messageInstance = commonService.confirm("国家: " + item);
            messageInstance.result.then(function (resp) {
                if (resp) {
                    commonService.showLoading();
                    $.ajax({
                        url: '/StudyAbroad/deleteCountry?country=' + item,
                        type: 'DELETE',
                        success: function () {
                            $timeout(function () {
                                commonService.showMessage($scope, {
                                    type: 'success',
                                    content: '删除成功'
                                });
                                $scope.countryList.splice(pos, 1);
                            });
                        },
                        error: function (err) {
                            console.log(err);
                            commonService.showMessage($scope, {
                                type: 'danger',
                                content: '删除失败'
                            });
                        }
                    });
                }
            });
        };

    }];

    var backendModule = angular.module('backend.config');
    backendModule.controller('abroadCountryCtrl', abroadCountryCtrl);
});
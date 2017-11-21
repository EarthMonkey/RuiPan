/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var abroadCountryCtrl = ['$scope', 'commonService', function ($scope, commonService) {

        // 获取所有国家
        $.ajax({
            url: "/StudyAbroad/getAllCountry",
            type: 'GET',
            success: function (data) {
                $scope.countryList = data;
            },
            error: function (err) {
                console.log(err);
                showMess('danger', '获取国家列表失败');
            }
        });

        $scope.addCountry = function () {
            var modalInstance = commonService.openTextForm('留学国家管理',
                [{id: 'country', label: '留学国家'}]
            );

            modalInstance.result.then(function (resp) {
                $.ajax({
                    url: "/StudyAbroad/addCountry",
                    type: 'POST',
                    data: resp,
                    success: function (data) {
                        if (data == "success") {
                            $scope.countryList.push(resp.country);
                            showMess('success', '添加成功');
                        } else {
                            showMess('danger', data);
                        }
                    },
                    error: function (err) {
                        console.log(err);
                        showMess('danger', '添加失败');
                    }
                });
            });
        };

        $scope.delCountry = function (item, pos) {
            var messageInstance = commonService.confirm("国家: " + item);
            messageInstance.result.then(function (resp) {
                if (resp) {
                    $.ajax({
                        url: '/StudyAbroad/deleteCountry?country=' + item,
                        type: 'DELETE',
                        success: function () {
                            $scope.countryList.splice(pos, 1);
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
    backendModule.controller('abroadCountryCtrl', abroadCountryCtrl);
});
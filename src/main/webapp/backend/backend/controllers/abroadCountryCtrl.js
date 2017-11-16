/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var abroadCountryCtrl = ['$scope', '$http', 'commonService', function ($scope, $http, commonService) {

        $http.get("/StudyAbroad/getAllCountry").then(function (resp) {
            $scope.countryList = resp.data;
        }).catch(function (err) {
            console.log(err);
            commonService.showMessage($scope, {
                type: 'danger',
                content: '获取国家列表失败'
            });
        });

        $scope.addCountry = function () {
            var modalInstance = commonService.openTextForm('留学国家管理',
                [{id: 'country', label: '留学国家'}]
            );

            modalInstance.result.then(function (resp) {
                var jsonResp = JSON.stringify(resp);
                $http.post('/StudyAbroad/addCountry', resp.country).then(function () {
                    commonService.showMessage($scope, {
                        type: 'success',
                        content: '添加成功'
                    });
                    $scope.countryList.push(resp.country);
                }).catch(function (err) {
                    console.log(err);
                    commonService.showMessage($scope, {
                        type: 'danger',
                        content: '添加失败'
                    });
                });
            });
        };

        $scope.delCountry = function (item) {
            var messageInstance = commonService.confirm("国家: " + item);
            messageInstance.result.then(function (resp) {
                if (resp) {
                    $http.delete('/StudyAbroad/deleteCountry?country=' + item).then(function () {
                        commonService.showMessage($scope, {
                            type: 'success',
                            content: '删除成功'
                        }).catch(function (err) {
                            console.log(err);
                            commonService.showMessage($scope, {
                                type: 'danger',
                                content: '删除失败'
                            });
                        });
                    })
                }
            });
        };

    }];

    var backendModule = angular.module('backend.config');
    backendModule.controller('abroadCountryCtrl', abroadCountryCtrl);
});
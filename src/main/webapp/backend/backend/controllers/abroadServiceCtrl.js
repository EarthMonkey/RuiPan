/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var abroadServiceCtrl = ['$scope', 'commonService', '$timeout', function ($scope, commonService, $timeout) {

        // 删选
        $scope.selectedCoun = '美国';
        $scope.filterCountry = [
            {id: '美国', label: '美国'},
            {id: '英国', label: '英国'},
            {id: '澳洲', label: '澳洲'},
            {id: '加拿大', label: '加拿大'},
            {id: '其他', label: '其他'}
        ];

        $scope.selectedEdu = '研究生';
        $scope.filterEdu = [
            {id: '研究生', label: '研究生'},
            {id: '本科生', label: '本科生'},
            {id: '高中生', label: '高中生'}
        ];

        $scope.filterClick = function (syb, selected) {
            if (syb == 0) {
                $scope.selectedCoun = selected.id;
            } else {
                $scope.selectedEdu = selected.id;
            }
            getGid();
        };

        getGid();
        function getGid() {
            $.ajax({
                url: '/StudyAbroad/getGid?country=' + $scope.selectedCoun + "&grade=" + $scope.selectedEdu,
                type: 'GET',
                beforeSend: function () {
                    commonService.showLoading();
                },
                success: function (resp) {
                    console.log(resp);
                    $timeout(function () {
                       commonService.hideLoading();
                    });
                },
                error: function (err) {
                    console.log(err);
                    $timeout(function () {
                        commonService.showMessage($scope, {
                            type: 'danger',
                            content: '服务器出错啦'
                        });
                    });
                }
            })
        }

        // 增加硬性条件
        $scope.conditionList = [];
        $scope.addCondition = function () {
            var fields = [
                {id: 'rank', label: '等级标题', placeholder: 'e.g.Top10申请标准'},
                {id: 'subject', label: '要求标题', placeholder: 'e.g.GPA成绩要求'},
                {id: 'score', label: '要求内容', placeholder: 'e.g.3.0+'}
            ];
            var conditonInstance = commonService.openTextForm('增加硬性条件要求', fields);
            conditonInstance.result.then(function (resp) {
                commonService.showLoading();
                $.ajax({
                    url: "/StudyAbroad/addHardCondition",
                    type: 'POST',
                    data: resp,
                    success: function (data) {
                        if (data == "success") {
                            $timeout(function () {
                                commonService.showMessage($scope, {
                                    type: 'success',
                                    content: '添加成功'
                                });
                                $scope.conditionList.push(resp.country);
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
            })
        };

        function showMess(type, data) {
            $timeout(function () {
                commonService.showMessage($scope, {
                    type: type,
                    content: data
                });
            });
        }

    }];

    var backendModule = angular.module('backend.config');
    backendModule.controller('abroadServiceCtrl', abroadServiceCtrl);
});
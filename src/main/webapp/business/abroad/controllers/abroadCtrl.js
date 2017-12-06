/**
 * Created by L.H.S on 2017/8/14.
 */

define([''], function () {
    'use strict';

    var abroadCtrl = ['$scope', '$state', '$timeout', function ($scope, $state, $timeout) {

        var country = $state.params.country;
        var type = $state.params.type;

        var typeMap = {
            postgraduate: '研究生',
            undergraduate: '本科生',
            'high-school': '高中生'
        };
        $scope.stType = typeMap[type];

        var countryMap = {
            america: '美',
            british: '英',
            australia: '澳',
            canada: '加拿大',
            global: '学'
        };
        $scope.couType = countryMap[country];

        $scope.condition = [];

        var couEnMap = {
            america: '美国',
            british: '英国',
            australia: '澳洲',
            canada: '加拿大',
            global: '其他'
        };

        // 获取GID
        $.ajax({
            url: '/StudyAbroad/getGid?country=' + couEnMap[country] + "&grade=" + typeMap[type],
            type: 'GET',
            success: function (resp) {
                getData(resp);
            },
            error: function (err) {
                console.log(err);
            }
        });

        function getData(gid) {

            // 硬性条件
            $.ajax({
                url: '/StudyAbroad/getHardCondionByGid?gid=' + gid,
                type: 'GET',
                success: function (resp) {
                    var count = 0;
                    var temp = [];
                    for (var key in resp) {
                        if (count < 3) {
                            temp.push(
                                {title: key, content: resp[key]}
                            )
                        } else {
                            break;
                        }
                        count++;
                    }

                    $timeout(function () {
                        $scope.condition = temp;
                    });
                },
                error: function (err) {
                    console.log(err);
                }
            });

            // 申请要素
            $.ajax({
                url: '/StudyAbroad/getApplicationElementByGid?gid=' + gid,
                type: 'GET',
                success: function (resp) {
                    resp.forEach(function (item) {
                        if (item.category === "申请基本条件") {
                            $scope.factorTab[0].synopsis = item.synopsis;
                        } else if (item.category === '申请材料') {
                            $scope.factorTab[1].synopsis = item.synopsis;
                        } else if (item.category === '选校因素') {
                            $scope.factorTab[2].synopsis = item.synopsis;
                        } else {
                            $scope.factorTab[3].synopsis = item.synopsis;
                        }
                    });
                },
                error: function (err) {
                    console.log(err);
                }
            });

            // 研究生申请方案
            $.ajax({
                url: '/StudyAbroad/getApplicationSchemeByGid?gid=' + gid,
                type: 'GET',
                success: function (resp) {
                    $scope.programs = resp;
                },
                error: function (err) {
                    console.log(err);
                }
            });

            // 常见问题
            $.ajax({
                url: '/StudyAbroad/getPublishQuestionsByGid?gid=' + gid,
                type: 'GET',
                success: function (resp) {
                    var center = Math.ceil(resp.length / 2);
                    $scope.problems_r = resp.splice(center);
                    $scope.problems_l = resp;
                },
                error: function (err) {
                    console.log(err);
                }
            });

            // 根据gid获取业务顾问
            $.ajax({
                url: '/Consultant/getByGid?gid=' + gid,
                type: 'GET',
                success: function (resp) {
                    $timeout(function () {
                        $scope.consultants = resp;
                    });
                },
                error: function (err) {
                    console.log(err);
                }
            })
        }

        $scope.numberLabel = ['一', '二', '三', '四'];

        // 研究生申请方案
        $scope.programs = [];

        $scope.factorTab = [
            {title: '申请基本条件要求', show: true},
            {title: '申请材料'},
            {title: '选校因素'},
            {title: '所需费用'}
        ];

        // 获取方案详情
        $scope.getMethodDetail = function (item) {
            $state.go("method", {id: item.id});
        };

        var LAST_TAB = $scope.factorTab[0];
        $scope.changeTab = function (tab) {
            tab.show = true;
            LAST_TAB.show = false;
            LAST_TAB = tab;
        };

        // 外籍顾问
        $scope.consultants = [];

        // 问题资讯
        $scope.problems_l = [];

        $scope.problems_r = [];

        $scope.expandClick = function (answer) {
            answer.expanded = !answer.expanded;
        }

    }];

    var homeModule = angular.module('abroad.config');
    homeModule.controller('abroadCtrl', abroadCtrl);

});
/**
 * Created by L.H.S on 2017/8/14.
 */

define([''], function () {
    'use strict';

    var introCtrl = ['$scope', '$state', '$timeout', 'navListService', function ($scope, $state, $timeout, navListService) {

        var couEnMap = {
            america: '美国',
            british: '英国',
            australia: '澳洲',
            canada: '加拿大',
            global: '其他'
        };

        var country = couEnMap[$state.params.country];
        $scope.country = country;
        var tempType = $state.params.type.split('\.');
        var PID = tempType[1];
        $scope.sp = tempType[1];

        // 获取子导航
        $.ajax({
            url: 'Profession/getAllCategoryByCountry?country=' + country,
            type: 'GET',
            success: function (resp) {

                for (var key in resp) {
                    var arr = resp[key];

                    switch (key) {
                        case '商科': {
                            var chArr1 = [];
                            arr.forEach(function (item) {
                                chArr1.push({
                                    text: item.subclassification,
                                    assist: 'business.' + item.pid + '.' + item.subclassification,
                                    link: navListService.navList[2]
                                });
                            });
                            $timeout(function () {
                                navListService.navChild_sp[0].children = chArr1;
                                navListService.navChild_sp[0].assist = chArr1[0].assist;
                                navListService.navChild[3].assist = chArr1[0].assist;
                            });
                            break;
                        }
                        case '理科': {
                            var chArr2 = [];
                            arr.forEach(function (item) {
                                chArr2.push({
                                    text: item.subclassification,
                                    assist: 'science.' + item.pid + '.' + item.subclassification,
                                    link: navListService.navList[2]
                                });
                            });
                            $timeout(function () {
                                navListService.navChild_sp[1].children = chArr2;
                                navListService.navChild_sp[1].assist = chArr2[0].assist;
                            });
                            break;
                        }
                        case  '工科': {
                            var chArr3 = [];
                            arr.forEach(function (item) {
                                chArr3.push({
                                    text: item.subclassification,
                                    assist: 'engineer.' + item.pid + '.' + item.subclassification,
                                    link: navListService.navList[2]
                                });
                            });
                            $timeout(function () {
                                navListService.navChild_sp[2].children = chArr3;
                                navListService.navChild_sp[2].assist = chArr3[0].assist;
                            });
                            break;
                        }
                        case  '文科': {
                            var chArr4 = [];
                            arr.forEach(function (item) {
                                chArr4.push({
                                    text: item.subclassification,
                                    assist: 'liberal.' + item.pid + '.' + item.subclassification,
                                    link: navListService.navList[2]
                                });
                            });
                            $timeout(function () {
                                navListService.navChild_sp[3].children = chArr4;
                                navListService.navChild_sp[3].assist = chArr4[0].assist;
                            });
                            break;
                        }
                        case  '艺术': {
                            var chArr5 = [];
                            arr.forEach(function (item) {
                                chArr5.push({
                                    text: item.subclassification,
                                    assist: 'art.' + item.pid + '.' + item.subclassification,
                                    link: navListService.navList[2]
                                });
                            });
                            $timeout(function () {
                                navListService.navChild_sp[4].children = chArr5;
                                navListService.navChild_sp[4].assist = chArr5[0].assist;
                            });
                            break;
                        }
                    }
                }
                getData();
            },
            error: function (err) {
                console.log(err);
            }
        });


        function getData() {

            // 详情、建议
            $.ajax({
                url: 'Profession/getProfessionIntroducePublished?pid=' + PID,
                type: 'GET',
                success: function (resp) {
                    $timeout(function () {
                        $scope.detailAdvice = resp;
                    });
                },
                error: function (err) {
                    console.log(err);
                }
            });

            // 专业课程
            $.ajax({
                url: 'Profession/getProfessionCourse?pid=' + PID,
                type: 'GET',
                success: function (resp) {
                    $timeout(function () {
                        $scope.courseList = resp;
                    });
                },
                error: function (err) {
                    console.log(err);
                }
            });

            // 就业去向
            $.ajax({
                url: 'Profession/getEmploymentCompany?pid=' + PID,
                type: 'GET',
                success: function (resp) {
                    resp.splice(5);
                    $scope.prosWhere = resp;
                },
                error: function (err) {
                    console.log(err);
                }
            });

            // 领域
            $.ajax({
                url: 'Profession/getEmploymentPost?pid=' + PID,
                type: 'GET',
                success: function (resp) {
                    $scope.prosField = resp;
                },
                error: function (err) {
                    console.log(err);
                }
            });

            // 申请条件
            $.ajax({
                url: 'Profession/getApplicationAdvice?pid=' + PID,
                type: 'GET',
                success: function (resp) {
                    $timeout(function () {
                        $scope.conList = resp;
                    });
                },
                error: function (err) {
                    console.log(err);
                }
            });

            // 薪资水平
            $.ajax({
                url: 'Profession/getSalary?pid=' + PID,
                type: 'GET',
                success: function (resp) {
                    if (resp.length > 0) {
                        var sum = 0;
                        resp.forEach(function (item) {
                            sum += Number(item.salary);
                        });
                        $timeout(function () {
                            $scope.salary = (sum / resp.length).toFixed(0);
                        });
                    }
                },
                error: function (err) {
                    console.log(err);
                }
            });

            // 专业院校排名
            $.ajax({
                url: 'School/getAllSchoolRankingByPid?pid=' + PID,
                type: 'GET',
                success: function (resp) {
                    $timeout(function () {
                        $scope.rankData = resp;
                    });
                },
                error: function (err) {
                    console.log(err);
                }
            });

            // 获取所有成功案例
            $.ajax({
                url: 'SuccessfulCase/getSuccessfulCaseByPid?pid=' + PID,
                type: 'GET',
                success: function (resp) {
                    if (resp[0]) {
                        $scope.sucCase = resp[0];
                        getConsultant(resp[0].cid);
                    }
                },
                error: function (err) {
                    console.log(err);
                }
            });
        }

        function getConsultant(id) {
            $.ajax({
                url: 'Consultant/getById?id=' + id,
                type: 'GET',
                success: function (resp) {
                    $timeout(function () {
                        $scope.consultor = resp;
                    });
                },
                error: function (err) {
                    console.log(err);
                }
            })
        }

        // 左侧导航栏
        $scope.leftBar = [
            {label: '专业详细介绍', link: '#detail'},
            {label: '专业课程设置', link: '#course'},
            {label: '就业前景', link: '#prospect'},
            {label: '申请条件', link: '#condition'},
            {label: '独家申请建议', link: '#advice'},
            {label: '专业院校排名', link: '#rank'},
            {label: '成功案例', link: '#success'}
        ];

        $scope.activeBar = "#detail";
        $scope.barClick = function (bar) {
            $scope.activeBar = bar.link;
            // 缓动
            var pos = angular.element(bar.link)[0].offsetTop;
            $('html,body').animate({scrollTop: pos}, 500);
        };

        $(window).scroll(function (e) {
            var wst = $(window).scrollTop();
            for (var i = 0; i < $scope.leftBar.length; i++) {
                if (wst > 812) {
                    $('.left_bar').addClass('left_bar_fixed');
                } else {
                    $('.left_bar').removeClass('left_bar_fixed');
                }
            }
        });

        // 申请条件
        $scope.conList = [];

        // 就业前景
        $scope.prosWhere = [];

        $scope.prosField = [];

        $scope.salary = 0;

        // 院校排名
        $scope.rankCol = ['排名', '学校名', '成绩要求', '申请难度分析', '院校申请'];
        $scope.rankData = [];

        // 获取三个推荐方案
        $.ajax({
            url: 'StudyAbroad/getRecommendApplicationScheme',
            type: 'GET',
            success: function (resp) {
                $timeout(function () {
                    $scope.recomendList = resp;
                });
            },
            error: function (err) {
                console.log(err);
            }
        });


    }];

    var homeModule = angular.module('intro.config');
    homeModule.controller('introCtrl', introCtrl);

});
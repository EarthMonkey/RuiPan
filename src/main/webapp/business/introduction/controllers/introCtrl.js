/**
 * Created by L.H.S on 2017/8/14.
 */

define([''], function () {
    'use strict';

    var introCtrl = ['$scope', '$state', '$location', '$anchorScroll', function ($scope, $state, $location, $anchorScroll) {

        var country = $state.params.country;
        var type = $state.params.type;
        $scope.test = "专业介绍: " + country + " - " + type;

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
                if (wst > 630) {
                    $('.left_bar').addClass('left_bar_fixed');
                } else {
                    $('.left_bar').removeClass('left_bar_fixed');
                }
            }
        });

        // 申请条件
        $scope.condition = [
            {label: 'GPA', score: 3.5},
            {label: 'GRE', score: "325 + 3.5"},
            {label: 'TOFEL', score: 105}
        ];

        // 就业前景
        $scope.prosWhere = [
            {img: '/theme/source/pro-where-1.png', text: '银行'},
            {img: '/theme/source/pro-where-2.png', text: '证券'},
            {img: '/theme/source/pro-where-3.png', text: '保险'},
            {img: '/theme/source/pro-where-4.png', text: '投资机构'},
            {img: '/theme/source/pro-where-5.png', text: '金融机构'}
        ];

        $scope.prosField = ['风险管理人士', '高校科研人员', '股票交易员', '证券分析师', '金融工程师'];

        // 院校排名
        $scope.rankCol = ['排名', '学校名', '成绩要求', '申请难度分析', '院校申请'];
        $scope.rankData = [
            {rank: 1, school: '卡耐基梅隆大学', score: '无最低分说明', difficulty: '申请难度大'},
            {rank: 2, school: '哥伦比亚大学', score: '无最低分说明', difficulty: '申请难度大'},
            {rank: 2, school: '普林斯顿大学', score: '无最低分说明', difficulty: '申请难度大'}
        ]

    }];

    var homeModule = angular.module('intro.config');
    homeModule.controller('introCtrl', introCtrl);

});
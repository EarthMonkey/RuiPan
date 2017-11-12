/**
 * Created by L.H.S on 2017/8/14.
 */

define([''], function () {
    'use strict';

    var abroadCtrl = ['$scope', '$state', function ($scope, $state) {

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


        $scope.condition = [
            {title: 'Top10', score: ['3.0', '95', '320', '700']},
            {title: 'Top50', score: ['3.0', '95', '320', '700']},
            {title: 'Top100', score: ['3.0', '95', '320', '700']}
        ];

        $scope.scoreType = ['GPA', 'TOFEL', 'GRE', 'GMAT'];

        // 研究生申请方案
        $scope.programs = [
            {title: '大四在读', methods: ['申请博士', '申请博士', '单申ESL课程']},
            {title: '大三在读', methods: ['（ESL）+申请转学', '申请硕士', '申请博士']},
            {title: '大二在读', methods: ['（ESL）+申请转学', '申请硕士', '申请博士']},
            {title: '大一在读', methods: ['（ESL）+申请转学', '申请硕士', '申请博士']},
            {title: '研二在读/毕业', methods: ['申请博士', '申请博士', '单申ESL课程']},
            {title: '研一在读', methods: ['申请博士', '申请博士', '单申ESL课程']},
            {title: '大学毕业已工作N年', methods: ['申请博士', '申请博士', '单申ESL课程']},
            {title: '大专在读/毕业', methods: ['（ESL）+插读大1-3', '（ESL）+专升硕', '单申ESL课程']}
        ];

        $scope.factorTab = [
            {title: '申请基本条件要求', show: true},
            {title: '申请材料'},
            {title: '选校因素'},
            {title: '所需费用'}
        ];

        var LAST_TAB = $scope.factorTab[0];
        $scope.changeTab = function (tab) {
            tab.show = true;
            LAST_TAB.show = false;
            LAST_TAB = tab;
        };

        $scope.baseCondition = [{
            label: "学术背景",
            span: "大学本科在读或本科硕士、毕业，建议大学GPA3.0以上"
        }, {
            label: "语言成绩",
            span: "大学本科在读或本科硕士、毕业，建议大学GPA3.0以上"
        }, {
            label: "研究生考试",
            span: "大学本科在读或本科硕士、毕业，建议大学GPA3.0以上"
        }, {
            label: "经济担保",
            span: "大学本科在读或本科硕士、毕业，建议大学GPA3.0以上"
        }, {
            label: "实习经历",
            span: "大学本科在读或本科硕士、毕业，建议大学GPA3.0以上"
        }];

        // 外籍顾问
        $scope.consultants = [{
            img: '/theme/source/consult-1.jpg',
            name: '狼叔',
            special: '美国普林斯顿大学建筑业',
            brief: '【公司创始人，领导公司在中国、东南亚、俄罗斯和印度等国家和地区发型了多款手机游戏软件】',
            order: 127
        }, {
            img: '/theme/source/consult-2.jpg',
            name: 'Daniel',
            special: '美国普林斯顿大学东亚研究专业',
            brief: '【公司创始人，领导公司在中国、东南亚、俄罗斯和印度等国家和地区发型了多款手机游戏软件】',
            order: 127
        }, {
            img: '/theme/source/consult-3.jpg',
            name: 'Adam',
            special: '美国芝加哥大学哲学专业',
            brief: '【公司创始人，领导公司在中国、东南亚、俄罗斯和印度等国家和地区发型了多款手机游戏软件】',
            order: 127
        }, {
            img: '/theme/source/consult-4.jpg',
            name: '休杰克曼',
            special: '美国威廉姆斯学院生物、中文双学位',
            brief: '【公司创始人，领导公司在中国、东南亚、俄罗斯和印度等国家和地区发型了多款手机游戏软件】',
            order: 127
        }];

    }];

    var homeModule = angular.module('abroad.config');
    homeModule.controller('abroadCtrl', abroadCtrl);

});
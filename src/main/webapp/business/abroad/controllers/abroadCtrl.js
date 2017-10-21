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

        $scope.condition = [
            {title: 'Top10', score: ['3.0', '95', '320', '700']},
            {title: 'Top50', score: ['3.0', '95', '320', '700']},
            {title: 'Top100', score: ['3.0', '95', '320', '700']}
        ];

        $scope.scoreType = ['GPA', 'TOFEL', 'GRE', 'GMAT'];

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
        }

        $scope.baseCondition = [{
            label: "学术背景",
            span: "大学本科在读或本科硕士、毕业，建议大学GPA3.0以上"
        },{
            label: "语言成绩",
            span: "大学本科在读或本科硕士、毕业，建议大学GPA3.0以上"
        },{
            label: "研究生考试",
            span: "大学本科在读或本科硕士、毕业，建议大学GPA3.0以上"
        },{
            label: "经济担保",
            span: "大学本科在读或本科硕士、毕业，建议大学GPA3.0以上"
        },{
            label: "实习经历",
            span: "大学本科在读或本科硕士、毕业，建议大学GPA3.0以上"
        }]

    }];

    var homeModule = angular.module('abroad.config');
    homeModule.controller('abroadCtrl', abroadCtrl);

});
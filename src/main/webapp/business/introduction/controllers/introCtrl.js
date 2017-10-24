/**
 * Created by L.H.S on 2017/8/14.
 */

define([''], function () {
    'use strict';

    var introCtrl = ['$scope', '$state', function ($scope, $state) {

        var country = $state.params.country;
        var type = $state.params.type;
        $scope.test = "专业介绍: " + country + " - " + type;

        // 申请条件
        $scope.condition = [
            {label: 'GPA', score: 3.5},
            {label: 'GRE', score: "325 + 3.5"},
            {label: 'TOFEL', score: 105}
        ];

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
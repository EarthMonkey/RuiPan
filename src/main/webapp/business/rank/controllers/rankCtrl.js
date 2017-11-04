/**
 * Created by L.H.S on 2017/8/14.
 */

define([''], function () {
    'use strict';

    var rankCtrl = ['$scope', '$state', function ($scope, $state) {

        var country = $state.params.country;
        $scope.test = "院校排名: " + country;

        $scope.leftBar = [];
        var spTypes = ['金融专业', '美国文理学院', '计算机科学专业', '美国综合大学', '会计专业',
            '教育类专业', '美国高中', '电子电气工程专业', '机械工程专业', '企业管理专业', '高级工商管理硕士专业'];
        spTypes.forEach(function (st, i) {
            $scope.leftBar.push({
                label: st,
                id: i
            });
        });

        // 选择排名分类
        $scope.activeBar = $scope.leftBar[0];
        $scope.barClick = function (bar) {
            $scope.activeBar = bar;
        };

        $scope.getDetail = function () {
            $state.go('schoolDetail', {name: 'Harvard'});
        }

    }];

    var rankModule = angular.module('rank.config');
    rankModule.controller('rankCtrl', rankCtrl);

});
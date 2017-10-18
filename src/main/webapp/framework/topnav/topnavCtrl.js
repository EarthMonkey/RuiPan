/**
 * Created by L.H.S on 2017/8/14.
 */

define([], function () {

    'use strict';

    var topnavCtrl = ['$scope', 'navListService', '$state', function ($scope, navListService, $state) {

        $scope.menus = {
            url: 'framework/topnav/topnav.tpl.html'
        };

        // 顶部导航栏
        $scope.navList = navListService.navList;

        // 二级导航栏（普通）
        $scope.navChild = navListService.navChild;

        // 二级导航栏（专业介绍）
        $scope.navChild_sp = navListService.navChild_sp;

        // 判断Menu的子状态激活时，是否需要隶属于其父状态
        $scope.isActived = function (firstLevelState) {
            if (typeof (firstLevelState) !== 'undefined') {
                var rootState = (firstLevelState + '').split('.');
                return $state.includes(rootState[0]);
            }
            return false;
        };

        /** 下拉筛选框 */
        $scope.combox = angular.copy($scope.navList[1].children);  // 留学服务下拉框
        $scope.selectedComb = angular.copy($scope.navList[1].children[0]);
        // 选择下拉框
        $scope.combSelect = function (op) {
            $scope.selectedComb = op;
        };

        // 不同页面更新combox
        $scope.setCombox = function () {

        }

    }];

    return topnavCtrl;
});
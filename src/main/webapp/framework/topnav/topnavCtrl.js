/**
 * Created by L.H.S on 2017/8/14.
 */

define([], function () {

    'use strict';

    var topnavCtrl = ['$scope', 'navListService', '$state', function ($scope, navListService, $state, $timeout) {

        $scope.menus = {
            url: 'framework/topnav/topnav.tpl.html'
        };

        // 顶部导航栏
        $scope.navList = navListService.navList;

        // 当前状态，由于异步加载，初始化为空
        $scope.currState = $state;
        $scope.$watch('currState.current.name', function (newValue) {
            $scope.currentState = newValue;
            $scope.combox = navListService.comboxAll[newValue];  // 留学服务下拉框

            // 更新下拉筛选框
            $scope.combox = navListService.comboxAll[newValue] != null ? navListService.comboxAll[newValue] : [];
            $scope.selectedComb = $scope.combox[0];
        });

        $scope.navListLink = function (state) {
            $scope.currentState = state;
            if (state == 'introduction') {
                $scope.navChild = navListService.navChild_sp;
            } else {
                $scope.navChild = navListService.navChild;
            }
            $state.go(state);
        };

        // 二级导航栏（普通）
        $scope.navChild = navListService.navChild;

        $scope.navChildLink = function (nc) {
            if (nc.state) {
                $scope.navListLink(nc.state); // 链接到第一层
            }
        };

        // 判断Menu的子状态激活时，是否需要隶属于其父状态
        $scope.isActived = function (firstLevelState) {
            if (typeof (firstLevelState) !== 'undefined') {
                var rootState = (firstLevelState + '').split('.');
                return $state.includes(rootState[0]);
            }
            return false;
        };

        /** 下拉筛选框 */
        // 选择下拉框
        $scope.combSelect = function (op) {
            $scope.selectedComb = op;
        };

    }];

    return topnavCtrl;
});
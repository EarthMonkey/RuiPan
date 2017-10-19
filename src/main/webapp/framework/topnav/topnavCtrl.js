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
            if (!newValue) return;

            $scope.currentState = newValue;
            // 更新下拉筛选框
            var tempBox = navListService.comboxAll[newValue];  // 留学服务下拉框
            $scope.combox = tempBox != null ? tempBox : [];

            if ($scope.selectedComb == null) {
                var curCountry = $scope.currState.params.country;
                if (curCountry) {
                    for (var i = 0; i < $scope.combox.length; i++) {
                        if ($scope.combox[i].country == curCountry) {
                            $scope.selectedComb = $scope.combox[i];
                            break;
                        }
                    }
                } else {
                    $scope.selectedComb = $scope.combox[0];
                }
            }
        });

        $scope.navListLink = function (nav, country, index) {

            $scope.currentState = nav.state;
            if (nav.state == 'introduction') {
                $scope.navChild = navListService.navChild_sp;
            } else {
                $scope.navChild = navListService.navChild;
            }

            if (nav.children) {
                country = country == null ? nav.children[0].country : country;
                $state.go(nav.state, {country: country});

                var tempBox = navListService.comboxAll[nav.state];  // 留学服务下拉框
                $scope.combox = tempBox != null ? tempBox : [];

                $scope.selectedComb = $scope.combox[index]; // 更新下拉框
            } else {
                $state.go(nav.state);
            }
        };

        // 二级导航栏（普通）
        $scope.navChild = navListService.navChild;

        $scope.navChildLink = function (nc) {
            if (nc.state) {
                $scope.navListLink(nc); // 链接到第一层
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
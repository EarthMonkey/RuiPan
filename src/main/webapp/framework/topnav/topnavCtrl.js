/**
 * Created by L.H.S on 2017/8/14.
 */

define([], function () {

    'use strict';
    var topnavCtrl = ['$scope', 'navListService', '$state', '$rootScope', function ($scope, navListService, $state, $rootScope) {

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

            var curCountry = $scope.currState.params.country;
            var assist = $state.params.type;

            // 更新assist
            if ($scope.currentState == 'abroad' || $scope.currentState == 'introduction') {
                assist = assist == null ? $scope.navChild[0].assist : assist;
            }
            $scope.currentAssist = assist;

            // 更新navChild
            if ($scope.currentState == 'introduction') {
                $scope.navChild = navListService.navChild_sp;
            }

            if ($scope.selectedComb == null) {
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

        $scope.navListLink = function (nav, country, index, langType) {

            $scope.currentState = nav.state;
            if (nav.state == 'introduction') {
                $scope.navChild = navListService.navChild_sp;
            } else {
                $scope.navChild = navListService.navChild;
            }

            // 直接点击顶部导航栏，清除副导航栏选中状态
            if (country == null) {
                if (nav.state === 'abroad' || nav.state === 'introduction') {
                    $scope.currentAssist = $scope.navChild[0].assist;
                } else {
                    $scope.currentAssist = null;
                }
            }

            if (nav.children) {
                if (nav.state === "language") {
                    langType = (langType) ? langType : '校长教师培训';
                    $state.go("language", {type: langType});
                    return;
                }

                country = country == null ? nav.children[0].country : country;
                $state.go(nav.state, {country: country, type: $scope.currentAssist});

                var tempBox = navListService.comboxAll[nav.state];  // 留学服务下拉框
                $scope.combox = tempBox != null ? tempBox : [];

                index = index == null ? 0 : index;
                $scope.selectedComb = $scope.combox[index]; // 更新下拉框
            } else {
                $state.go(nav.state);
                $scope.selectedComb = '';
            }
        };

        // 二级导航栏（普通）
        $scope.navChild = navListService.navChild;

        $scope.navChildLink = function (nc) {
            $scope.currentAssist = nc.assist;
            if ($scope.selectedComb) {
                $scope.navListLink(nc.link, $scope.selectedComb.country, $scope.selectedComb.index);
            } else if (nc.assist) {
                $scope.navListLink(nc.link, 'america', 0);
            } else {
                $scope.navListLink(nc.link); // 链接到第一层
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
            $state.go($scope.currentState, {country: op.country, type: $scope.currentAssist});
        };

        $rootScope.refreshComb = function (op) {
            $scope.selectedComb = op;
        }

    }];

    return topnavCtrl;
});
/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var leftBarCtrl = ['$scope', '$rootScope', '$state', '$timeout', 'leftBarService', function ($scope, $rootScope, $state, $timeout, leftBarService) {

        // if (!$rootScope.USER) {
        //     $state.go("login");
        // }

        $scope.menus = leftBarService.getMenus();
        // 展开当前状态
        for (var i = 0; i < $scope.menus.length; i++) {
            var isActive = $state.includes("backend." + $scope.menus[i].id);
            if (isActive) {
                $scope.menus[i].isOpen = true;
                break;
            } else {
                if ($state.current.name.indexOf($scope.menus[i].id) != -1) {
                    $scope.menus[i].isOpen = true;
                    break;
                }
            }
        }

        // 判断Menu的子状态激活时，是否需要隶属于其父状态
        $scope.isActived = function (curState) {
            return $state.includes(curState);
        };
    }];

    var backendModule = angular.module('backend.config');
    backendModule.controller('leftBarCtrl', leftBarCtrl);
});
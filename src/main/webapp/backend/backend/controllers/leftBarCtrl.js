/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var leftBarCtrl = ['$scope', '$rootScope', '$state', 'leftBarService', function ($scope, $rootScope, $state, leftBarService) {

        $scope.menus = leftBarService.getMenus();

        // if (!$rootScope.USER) {
        //     $state.go("login");
        // }

    }];

    var backendModule = angular.module('backend.config');
    backendModule.controller('leftBarCtrl', leftBarCtrl);
});
/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var schoolRankCtrl = ['$scope', function ($scope) {

        $scope.test = "院校管理-院校排名";

    }];

    var backendModule = angular.module('backend.config');
    backendModule.controller('schoolRankCtrl', schoolRankCtrl);
});
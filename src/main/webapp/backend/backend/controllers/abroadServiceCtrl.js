/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var abroadServiceCtrl = ['$scope', function ($scope) {

        $scope.test = "留学管理-留学服务";

    }];

    var backendModule = angular.module('backend.config');
    backendModule.controller('abroadServiceCtrl', abroadServiceCtrl);
});
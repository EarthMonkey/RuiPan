/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var abroadMethodCtrl = ['$scope', function ($scope) {

        $scope.test = "留学管理-留学方案";

    }];

    var backendModule = angular.module('backend.config');
    backendModule.controller('abroadMethodCtrl', abroadMethodCtrl);
});
/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var specialClassCtrl = ['$scope', function ($scope) {

        $scope.test = "专业管理-专业分类";

    }];

    var backendModule = angular.module('backend.config');
    backendModule.controller('specialClassCtrl', specialClassCtrl);
});
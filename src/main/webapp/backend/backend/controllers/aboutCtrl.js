/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var aboutCtrl = ['$scope', function ($scope) {

        $scope.test = "关于我们";

    }];

    var backendModule = angular.module('backend.config');
    backendModule.controller('aboutCtrl', aboutCtrl);
});
/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var aboutCtrl = ['$scope', function ($scope) {

        $scope.tabs = [
            { title:'未处理', type: 0},
            { title:'已处理', type: 1}
        ];

    }];

    var backendModule = angular.module('backend.config');
    backendModule.controller('aboutCtrl', aboutCtrl);
});
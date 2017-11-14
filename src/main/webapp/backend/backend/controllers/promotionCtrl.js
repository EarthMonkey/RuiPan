/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var promotionCtrl = ['$scope', function ($scope) {

        $scope.test = "背景提升";

    }];

    var backendModule = angular.module('backend.config');
    backendModule.controller('promotionCtrl', promotionCtrl);
});
/**
 * Created by L.H.S on 2017/8/14.
 */

define([''], function () {
    'use strict';

    var coopCtrl = ['$scope', function ($scope) {

        $scope.test = "合作办学";

    }];

    var homeModule = angular.module('coop.config');
    homeModule.controller('coopCtrl', coopCtrl);

});
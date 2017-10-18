/**
 * Created by L.H.S on 2017/8/14.
 */

define([''], function () {
    'use strict';

    var successCtrl = ['$scope', function ($scope) {

        $scope.test = "成功案例";

    }];

    var homeModule = angular.module('success.config');
    homeModule.controller('successCtrl', successCtrl);

});
/**
 * Created by L.H.S on 2017/8/14.
 */

define([''], function () {
    'use strict';

    var introCtrl = ['$scope', function ($scope) {

        $scope.test = "专业介绍";

    }];

    var homeModule = angular.module('intro.config');
    homeModule.controller('introCtrl', introCtrl);

});
/**
 * Created by L.H.S on 2017/8/14.
 */

define([''], function () {
    'use strict';

    var langCtrl = ['$scope', function ($scope) {

        $scope.test = "语言培训";

    }];

    var homeModule = angular.module('lang.config');
    homeModule.controller('langCtrl', langCtrl);

});
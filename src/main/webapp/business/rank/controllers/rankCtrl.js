/**
 * Created by L.H.S on 2017/8/14.
 */

define([''], function () {
    'use strict';

    var rankCtrl = ['$scope', function ($scope) {

        $scope.test = "院校排名";

    }];

    var homeModule = angular.module('rank.config');
    homeModule.controller('rankCtrl', rankCtrl);

});
/**
 * Created by L.H.S on 2017/8/14.
 */

define([''], function () {
    'use strict';

    var aboutCtrl = ['$scope', function ($scope) {

        $scope.test = "关于我们";

    }];

    var homeModule = angular.module('about.config');
    homeModule.controller('aboutCtrl', aboutCtrl);

});
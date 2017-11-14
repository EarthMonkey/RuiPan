/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var languageCtrl = ['$scope', function ($scope) {

        $scope.test = "语言培训";

    }];

    var backendModule = angular.module('backend.config');
    backendModule.controller('languageCtrl', languageCtrl);
});
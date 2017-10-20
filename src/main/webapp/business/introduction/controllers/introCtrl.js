/**
 * Created by L.H.S on 2017/8/14.
 */

define([''], function () {
    'use strict';

    var introCtrl = ['$scope', '$state', function ($scope, $state) {

        var country = $state.params.country;
        $scope.test = "专业介绍: " + country;

    }];

    var homeModule = angular.module('intro.config');
    homeModule.controller('introCtrl', introCtrl);

});
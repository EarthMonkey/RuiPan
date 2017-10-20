/**
 * Created by L.H.S on 2017/8/14.
 */

define([''], function () {
    'use strict';

    var rankCtrl = ['$scope', '$state', function ($scope, $state) {

        var country = $state.params.country;
        $scope.test = "院校排名: " + country;

    }];

    var homeModule = angular.module('rank.config');
    homeModule.controller('rankCtrl', rankCtrl);

});
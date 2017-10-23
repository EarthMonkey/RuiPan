/**
 * Created by L.H.S on 2017/8/14.
 */

define([''], function () {
    'use strict';

    var introCtrl = ['$scope', '$state', function ($scope, $state) {

        var country = $state.params.country;
        var type = $state.params.type;
        $scope.test = "专业介绍: " + country + " - " + type;

        $scope.condition = [
            {label: 'GPA', score: 3.5},
            {label: 'GRE', score: "325 + 3.5"},
            {label: 'TOFEL', score: 105}
        ];


    }];

    var homeModule = angular.module('intro.config');
    homeModule.controller('introCtrl', introCtrl);

});
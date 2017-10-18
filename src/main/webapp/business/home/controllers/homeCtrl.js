/**
 * Created by L.H.S on 2017/8/14.
 */

define([''], function () {
    'use strict';

    var homeCtrl = ['$scope', 'homeService',
        function ($scope, homeService) {

            $scope.test = homeService.getTest();

        }

    ];

    var homeModule = angular.module('home.config');
    homeModule.controller('homeCtrl', homeCtrl);

});
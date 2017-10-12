/**
 * Created by L.H.S on 2017/8/14.
 */

define([''], function () {
    'use strict';

    var appCtrl = ['$scope', function ($scope) {

            $scope.test = "welcome to application page";

        }

    ];

    var homeModule = angular.module('abroad.config');
    homeModule.controller('abroad.ctrl', appCtrl);

});
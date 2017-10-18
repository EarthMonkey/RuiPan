/**
 * Created by L.H.S on 2017/8/14.
 */

define([''], function () {
    'use strict';

    var abroadCtrl = ['$scope', function ($scope) {

            $scope.test = "留学服务";

        }

    ];

    var homeModule = angular.module('abroad.config');
    homeModule.controller('abroadCtrl', abroadCtrl);

});
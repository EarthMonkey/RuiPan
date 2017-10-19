/**
 * Created by L.H.S on 2017/8/14.
 */

define([''], function () {
    'use strict';

    var abroadCtrl = ['$scope', '$state', function ($scope, $state) {

        var country = $state.params.country;
        $scope.test = "留学服务: " + country;

    }];

    var homeModule = angular.module('abroad.config');
    homeModule.controller('abroadCtrl', abroadCtrl);

});
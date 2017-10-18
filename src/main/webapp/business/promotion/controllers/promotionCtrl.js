/**
 * Created by L.H.S on 2017/8/14.
 */

define([''], function () {
    'use strict';

    var promotionCtrl = ['$scope', function ($scope) {

        $scope.test = "背景提升";

    }];

    var homeModule = angular.module('promotion.config');
    homeModule.controller('promotionCtrl', promotionCtrl);

});
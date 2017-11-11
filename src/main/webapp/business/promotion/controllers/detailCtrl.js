/**
 * Created by L.H.S on 2017/11/11.
 */
define([''], function () {
    'use strict';

    var detailCtrl = ['$scope', '$state', function ($scope, $state) {

        var proId = $state.params.id;
        console.log(proId);

        $scope.proTitle = "耶鲁大学暑期项目";

    }];

    var homeModule = angular.module('promotion.config');
    homeModule.controller('detailCtrl', detailCtrl);
});
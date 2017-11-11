/**
 * Created by L.H.S on 2017/11/11.
 */

define([''], function () {
    'use strict';

    var detailCtrl = ['$scope', '$state', function ($scope, $state) {

        var sucId = $state.params.sucId;
        console.log(sucId);

        $scope.sucTitle = "名校教授亲笔推荐，斩获哈佛录取";

    }];

    var successModule = angular.module('success.config');
    successModule.controller('detailCtrl', detailCtrl);
});
/**
 * Created by L.H.S on 2017/11/4.
 */

define([''], function () {
    'use strict';

    var detailCtrl = ['$scope', '$state', function ($scope, $state) {

        var schoolName = $state.params.name;

        $scope.schoolImg = ['/theme/source/school-photo-1.png', '/theme/source/school-photo-2.png', '/theme/source/school-photo-3.png'];

        $scope.tabs = [
            {title: '院校介绍', show: true},
            {title: '录取要求'},
            {title: '录取榜'}
        ]
    }];

    var rankModule = angular.module('rank.config');
    rankModule.controller('detailCtrl', detailCtrl);
});
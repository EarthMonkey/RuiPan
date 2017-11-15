/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var schoolInfoCtrl = ['$scope', function ($scope) {

        $scope.selectedCoun = 'america';
        $scope.filterCountry = [
            {id: 'america', label: '美国'},
            {id: 'british', label: '英国'},
            {id: 'australia', label: '澳洲'},
            {id: 'canada', label: '加拿大'},
            {id: 'global', label: '其他'}
        ];

        $scope.filterClick = function (selected) {
            $scope.selectedCoun = selected.id;
        };

    }];

    var backendModule = angular.module('backend.config');
    backendModule.controller('schoolInfoCtrl', schoolInfoCtrl);
});
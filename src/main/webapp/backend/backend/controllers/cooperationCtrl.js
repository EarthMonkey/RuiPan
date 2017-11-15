/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var cooperationCtrl = ['$scope', function ($scope) {

        $scope.selectedType = 'hig-school';
        $scope.filterType = [
            {id: 'hig-school', label: '高中'},
            {id: 'career', label: '职业学院'},
            {id: 'college', label: '大学'}
        ];

        $scope.selectedCoun = 'british';
        $scope.filterCountry = [
            {id: 'british', label: '中英'},
            {id: 'america', label: '中美'},
            {id: 'canada', label: '中加'},
            {id: 'german', label: '中德'},
            {id: 'australia', label: '中澳'}
        ];

        $scope.filterClick = function (syb, selected) {
            if (syb == 0) {
                $scope.selectedType = selected.id;
            } else {
                $scope.selectedCoun = selected.id;
            }
        };

    }];

    var backendModule = angular.module('backend.config');
    backendModule.controller('cooperationCtrl', cooperationCtrl);
});
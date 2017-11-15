/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var promotionCtrl = ['$scope', function ($scope) {

        $scope.selectedType = 'tour';
        $scope.filterType = [
            {id: 'tour', label: '游学'},
            {id: 'practice', label: '实习'},
            {id: 'research', label: '科研'},
            {id: 'volunteer', label: '国际义工'},
            {id: 'project', label: '短期项目'}
        ];

        $scope.filterClick = function (selected) {
            $scope.selectedType = selected.id;
        };

    }];

    var backendModule = angular.module('backend.config');
    backendModule.controller('promotionCtrl', promotionCtrl);
});
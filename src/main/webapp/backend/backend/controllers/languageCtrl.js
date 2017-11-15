/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var languageCtrl = ['$scope', function ($scope) {

        $scope.selectedType = 'teacher';
        $scope.filterType = [
            {id: 'teacher', label: '校长教师培训'},
            {id: 'english', label: '英语培训'},
            {id: 'chinese', label: '中文培训'},
            {id: 'small', label: '小语种培训'},
            {id: 'other', label: '其他'}
        ];

        $scope.filterClick = function (selected) {
            $scope.selectedType = selected.id;
        };

    }];

    var backendModule = angular.module('backend.config');
    backendModule.controller('languageCtrl', languageCtrl);
});
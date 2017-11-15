/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var schoolRankCtrl = ['$scope', function ($scope) {

        $scope.selectedCoun = 'america';
        $scope.filterCountry = [
            {id: 'america', label: '美国'},
            {id: 'british', label: '英国'},
            {id: 'australia', label: '澳洲'},
            {id: 'canada', label: '加拿大'},
            {id: 'global', label: '其他'}
        ];

        $scope.selectedEdu = 'business';
        $scope.filterEdu = [
            {id: 'business', label: '商科'},
            {id: 'science', label: '理科'},
            {id: 'engineer', label: '工科'},
            {id: 'liberal', label: '文科'},
            {id: 'art', label: '艺术'}
        ];

        $scope.selectedSp = 'finance';
        $scope.filterSp = [
            {id: 'finance', label: '金融'},
            {id: 'account', label: '会计'},
            {id: 'market', label: '市场营销'}
        ];

        $scope.filterClick = function (syb, selected) {
            if (syb == 0) {
                $scope.selectedCoun = selected.id;
            } else if (syb == 1) {
                $scope.selectedEdu = selected.id;
            } else {
                $scope.selectedSp = selected.id;
            }
        };

    }];

    var backendModule = angular.module('backend.config');
    backendModule.controller('schoolRankCtrl', schoolRankCtrl);
});
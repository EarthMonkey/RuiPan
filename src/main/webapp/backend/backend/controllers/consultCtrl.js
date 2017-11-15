/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var consultCtrl = ['$scope', function ($scope) {

        $scope.consultIntro = [
            {starred: true},
            {starred: false},
            {starred: false},
            {starred: false}
        ];

        $scope.star = function (item) {
            item.starred = !item.starred;
        };


        $scope.selectedCoun = 'america';
        $scope.filterCountry = [
            {id: 'america', label: '美国'},
            {id: 'british', label: '英国'},
            {id: 'australia', label: '澳洲'},
            {id: 'canada', label: '加拿大'},
            {id: 'global', label: '其他'}
        ];

        $scope.selectedEdu = 'postgraduate';
        $scope.filterEdu = [
            {id: 'postgraduate', label: '研究生'},
            {id: 'undergraduate', label: '本科生'},
            {id: 'hig-school', label: '高中'}
        ];

        $scope.filterClick = function (syb, selected) {
            if (syb == 0) {
                $scope.selectedCoun = selected.id;
            } else {
                $scope.selectedEdu = selected.id;
            }
        };
    }];

    var backendModule = angular.module('backend.config');
    backendModule.controller('consultCtrl', consultCtrl);
});
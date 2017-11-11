/**
 * Created by L.H.S on 2017/8/14.
 */

define([''], function () {
    'use strict';

    var successCtrl = ['$scope', '$state', function ($scope, $state) {

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

        /** 分页 */
        $scope.totalItems = 41;  // 此处为每页10条
        $scope.currentPage = 1;
        $scope.pageChanged = function () {
            console.log($scope.currentPage);
        };

        // 获取详情
        $scope.getDetail = function () {

            $state.go("successDetail", {sucId: 'example1'});
        };

    }];

    var successModule = angular.module('success.config');
    successModule.controller('successCtrl', successCtrl);

});
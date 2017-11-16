/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var abroadCountryCtrl = ['$scope', '$http', '$uibModal', 'textFormService', 'editorService', function ($scope, $http, $uibModal, textFormService, editorService) {

        $http.get("/StudyAbroad/getAllCountry").then(function (resp) {
            console.log(resp)
        }).catch(function (err) {
            console.log(err)
        });

        $scope.addCountry = function () {
            var modalInstance = textFormService.open($uibModal, '留学国家管理',
                [{id: 'country', label: '留学国家'}]
            );

            modalInstance.result.then(function (resp) {
                console.log(resp)
            });
        };

        $scope.modCountry = function (item) {

            editorService.open($uibModal, 'editor')

        };

        $scope.delCountry = function (item) {

        };

    }];

    var backendModule = angular.module('backend.config');
    backendModule.controller('abroadCountryCtrl', abroadCountryCtrl);
});
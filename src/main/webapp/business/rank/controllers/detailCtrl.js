/**
 * Created by L.H.S on 2017/11/4.
 */

define([''], function () {
    'use strict';

    var detailCtrl = ['$scope', '$state', '$timeout', function ($scope, $state, $timeout) {

        var sid = $state.params.name;

        $.ajax({
            url: 'School/getSchoolBySid?sid=' + sid,
            type: 'GET',
            success: function (resp) {
                console.log(resp);
                getHtml(resp.textPath);
                $timeout(function () {
                    $scope.schoolObj = resp;
                });
            },
            error: function (err) {
                console.log(err);
            }
        });

        function getHtml(path) {
            $.ajax({
                url: 'getText?path=' + path,
                type: 'GET',
                success: function (resp) {
                    $timeout(function () {
                        $scope.schoolObj.content = resp;
                    });
                },
                error: function (err) {
                    console.log(err);
                }
            })
        }

        $scope.schoolImg = ['theme/source/school-photo-1.png', 'theme/source/school-photo-2.png', 'theme/source/school-photo-3.png'];

        $scope.tabs = [
            {title: '院校介绍', show: true},
            {title: '录取要求'},
            {title: '录取榜'}
        ];

        var LAST_TAB = $scope.tabs[0];
        $scope.changeTab = function (tab) {
            tab.show = true;
            LAST_TAB.show = false;
            LAST_TAB = tab;
        };

    }];

    var rankModule = angular.module('rank.config');
    rankModule.controller('detailCtrl', detailCtrl);
});
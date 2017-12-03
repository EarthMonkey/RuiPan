/**
 * Created by L.H.S on 2017/11/13.
 */

define([], function () {

    'use strict';
    var topbarCtrl = ['$scope', '$rootScope', '$state', function ($scope, $rootScope, $state) {

        $scope.menus = {
            url: 'framework/backend/topbar.html'
        };
    }];

    return topbarCtrl;
});
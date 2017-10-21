/**
 * Created by L.H.S on 2017/10/21.
 */

define([], function () {

    'use strict';

    var footerCtrl = ['$scope', '$state', function ($scope, $state) {

        $scope.footer = {
            url: 'framework/footer/footer.tpl.html'
        };

    }];

    return footerCtrl;
});
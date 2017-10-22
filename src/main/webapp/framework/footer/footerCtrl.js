/**
 * Created by L.H.S on 2017/10/21.
 */

define([], function () {

    'use strict';

    var footerCtrl = ['$scope', '$state', '$rootScope', function ($scope, $state, $rootScope) {

        $scope.footer = {
            url: 'framework/footer/footer.tpl.html'
        };

        $scope.footerNav = [{
            text: '留学指导',
            state: 'abroad',
            children: [
                {text: '美国留学', country: 'america'},
                {text: '英国留学', country: 'british'},
                {text: '澳洲留学', country: 'australia'},
                {text: '加拿大留学', country: 'canada'},
                {text: '其他国际留学', country: 'global'}
            ]
        },
            {
                text: '专业介绍',
                state: 'introduction',
                children: [
                    {text: '商科', assist: 'business.finance'},
                    {text: '理科', assist: 'science'},
                    {text: '工科', assist: 'engineer'},
                    {text: '文科', assist: 'liberal'},
                    {text: '艺术', assist: 'art'}
                ]
            },
            {
                text: '院校排名',
                state: 'rank',
                children: [
                    {text: '美国院校', country: 'america'},
                    {text: '英国院校', country: 'british'},
                    {text: '澳洲院校', country: 'australia'},
                    {text: '加拿大院校', country: 'canada'},
                    {text: '其他国际院校', country: 'global'}
                ]
            }];

        $scope.navLink = function (state, op, type) {

            if (state == 'introduction') {
                $state.go(state, {country: 'america', type: type});
                op = {text: '美国专业', country: 'america'};
            } else {
                $state.go(state, {country: op.country});
            }

            $rootScope.refreshComb(op);
        }

    }];

    return footerCtrl;
});
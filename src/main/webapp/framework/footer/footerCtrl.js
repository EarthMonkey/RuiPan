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
            text: '瑞泮留学',
            state: 'abroad',
            children: [
                {text: '高中留学', state: 'abroad', type: 'high-school'},
                {text: '本科留学', state: 'abroad', type: 'undergraduate'},
                {text: '硕博留学', state: 'abroad', type: 'postgraduate'},
                {text: '专业介绍', state: 'introduction'},
                {text: '院校排名', state: 'rank'}
            ],
            type: 'postgraduate'
        }, {
            text: '语言培训',
            state: 'language',
            children: [
                {text: '校长教师培训', state: 'language', type: '校长教师培训'},
                {text: '英语培训', state: 'language', type: '英语培训'},
                {text: '中文培训', state: 'language', type: '中文培训'},
                {text: '小语种培训', state: 'language', type: '小语种培训'}
            ],
            type: '校长培训'
        }, {
            text: '背景提升',
            state: 'promotion',
            children: [
                {text: '游学经历', state: 'promotion'},
                {text: '实习经历', state: 'promotion'},
                {text: '科研经历', state: 'promotion'},
                {text: '国际义工', state: 'promotion'},
                {text: '短期项目', state: 'promotion'}
            ]
        }, {
            text: '关于瑞泮',
            state: 'about',
            children: [
                {text: '公司介绍', state: 'about'},
                {text: '顾问团队', state: 'about'},
                {text: '合作办学', state: 'cooperation'},
                {text: '商务合作', state: 'about'}
            ]
        }];

        $scope.navLink = function (nav) {

            switch (nav.state) {
                case 'abroad': {
                    $state.go(nav.state, {country: 'america', type: nav.type});
                    break;
                }
                case 'rank': {
                    $state.go('rank', {country: 'america'});
                    break;
                }
                case 'introduction': {
                    $state.go(nav.state, {country: 'america', type: 'business.'});
                    break;
                }
                case 'language': {
                    $state.go("language", {type: nav.type});
                    break;
                }
                default: {
                    $state.go(nav.state);
                }
            }

            $rootScope.refreshComb(nav);
        }

    }];

    return footerCtrl;
});
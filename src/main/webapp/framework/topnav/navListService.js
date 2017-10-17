/**
 * Created by L.H.S on 2017/8/15.
 */

/**

 * 提供菜单列表数据（该数据用于顶部菜单栏和左侧菜单栏显示）

 */

define(function () {
    'use strict';

    var service = function () {
        this.navList = [
            {
                text: '首页',
                state: 'home'
            },
            {
                text: '留学服务',
                state: 'abroad',
                children: [
                    {text: '美国留学', img: 'america.svg'},
                    {text: '英国留学', img: 'british.svg'},
                    {text: '澳洲留学', img: 'australia.svg'},
                    {text: '加拿大留学', img: 'canada.svg'},
                    {text: '其他国际留学', img: 'global.svg'}
                ]
            },
            {
                text: '专业介绍',
                state: 'introduction',
                children: [
                    {text: '商科专业'},
                    {text: '工科专业'},
                    {text: '理科专业'}
                ]
            },
            {
                text: '院校排名',
                state: 'rank',
                children: [
                    {text: '美国院校排名', img: 'america.svg'},
                    {text: '英国院校排名', img: 'british.svg'},
                    {text: '澳洲院校排名', img: 'australia.svg'},
                    {text: '加拿大院校排名', img: 'canada.svg'},
                    {text: '其他国际院校排名', img: 'global.svg'}
                ]
            },
            {
                text: '成功案例',
                state: 'success'
            },
            {
                text: '背景提升',
                state: 'promotion'
            },
            {
                text: '语言培训',
                state: 'language'
            },
            {
                text: "合作办学",
                state: 'cooperation'
            },
            {
                text: '关于我们',
                state: 'about'
            }
        ];
    };

    return service;
});
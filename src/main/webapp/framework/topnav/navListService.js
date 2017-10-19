/**
 * Created by L.H.S on 2017/8/15.
 */

/**

 * 提供菜单列表数据（该数据用于顶部菜单栏和左侧菜单栏显示）

 */

define(function () {
    'use strict';

    var service = function () {

        /** 一级导航栏 */
        this.navList = [
            {
                text: '首页',
                state: 'home'
            },
            {
                text: '留学服务',
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
                    {text: '美国专业', country: 'america'},
                    {text: '英国专业', country: 'british'},
                    {text: '澳洲专业', country: 'australia'},
                    {text: '加拿大专业', country: 'canada'},
                    {text: '其他国际专业', country: 'global'}
                ]
            },
            {
                text: '院校排名',
                state: 'rank',
                children: [
                    {text: '美国院校排名', country: 'america'},
                    {text: '英国院校排名', country: 'british'},
                    {text: '澳洲院校排名', country: 'australia'},
                    {text: '加拿大院校排名', country: 'canada'},
                    {text: '其他国际院校排名', country: 'global'}
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

        /** 二级导航栏 */
        // 研究生、本科生、高中申请，专业介绍，院校排名，成功案例
        this.navChild = [
            {
                text: '研究生申请'
            }, {
                text: '本科生申请'
            }, {
                text: '高中申请'
            }, {
                text: '专业介绍',
                state: 'introduction'
            }, {
                text: '院校排名',
                state: 'rank'
            }, {
                text: '成功案例',
                state: 'success'
            }

        ];

        // 商科，理科，工科，文科，艺术
        this.navChild_sp = [
            {
                text: '商科',
                children: [
                    {text: '金融'},
                    {text: '会计'},
                    {text: '市场营销'}
                ]
            }, {
                text: '理科'
            }, {
                text: '工科'
            }, {
                text: '文科'
            }, {
                text: '艺术'
            }
        ];

        // 下拉筛选框
        this.comboxAll = {
            abroad: [
                {text: '美国留学', country: 'america'},
                {text: '英国留学', country: 'british'},
                {text: '澳洲留学', country: 'australia'},
                {text: '加拿大留学', country: 'canada'},
                {text: '其他国际留学', country: 'global'}
            ],
            introduction: [
                {text: '美国专业', country: 'america'},
                {text: '英国专业', country: 'british'},
                {text: '澳洲专业', country: 'australia'},
                {text: '加拿大专业', country: 'canada'},
                {text: '其他国际专业', country: 'global'}
            ],
            rank: [
                {text: '美国院校', country: 'america'},
                {text: '英国院校', country: 'british'},
                {text: '澳洲院校', country: 'australia'},
                {text: '加拿大院校', country: 'canada'},
                {text: '其他国际院校', country: 'global'}
            ]
        };

    };

    return service;
});
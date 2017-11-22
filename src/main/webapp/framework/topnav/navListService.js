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
                state: 'language',
                children: [
                    {text: '校长教师培训', type: '校长教师培训'},
                    {text: '英语培训', type: '英语培训'},
                    {text: '中文培训', type: '中文培训'},
                    {text: '小语种培训', type:'小语种培训'},
                    {text: '其他', type: '其他'}
                ]
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

        /** 二级导航栏
         * @param link: 链到第一层
         * */
        // 研究生、本科生、高中生申请，专业介绍，院校排名，成功案例
        this.navChild = [
            {
                text: '研究生申请',
                assist: 'postgraduate',
                link: this.navList[1]
            }, {
                text: '本科生申请',
                assist: 'undergraduate',
                link: this.navList[1]
            }, {
                text: '高中生申请',
                assist: 'high-school',
                link: this.navList[1]
            }, {
                text: '专业介绍',
                state: 'introduction',
                assist: 'business',
                link: this.navList[2]
            }, {
                text: '院校排名',
                state: 'rank',
                link: this.navList[3]
            }, {
                text: '成功案例',
                state: 'success',
                link: this.navList[4]
            }

        ];

        // 商科，理科，工科，文科，艺术
        this.navChild_sp = [
            {
                text: '商科',
                children: [
                    {text: '金融', assist: 'business.金融', link: this.navList[2]},
                    {text: '会计', assist: 'business.会计', link: this.navList[2]},
                    {text: '市场营销', assist: 'business.市场', link: this.navList[2]}
                ],
                assist: 'business.金融',
                link: this.navList[2]
            }, {
                text: '理科',
                assist: 'science',
                link: this.navList[2]
            }, {
                text: '工科',
                assist: 'engineer',
                link: this.navList[2]
            }, {
                text: '文科',
                assist: 'liberal',
                link: this.navList[2]
            }, {
                text: '艺术',
                assist: 'art',
                link: this.navList[2]
            }
        ];

        // 下拉筛选框
        this.comboxAll = {
            abroad: [
                {text: '美国留学', country: 'america', index: 0},
                {text: '英国留学', country: 'british', index: 1},
                {text: '澳洲留学', country: 'australia', index: 2},
                {text: '加拿大留学', country: 'canada', index: 3},
                {text: '其他国际留学', country: 'global', index: 4}
            ],
            introduction: [
                {text: '美国专业', country: 'america', index: 0},
                {text: '英国专业', country: 'british', index: 1},
                {text: '澳洲专业', country: 'australia', index: 2},
                {text: '加拿大专业', country: 'canada', index: 3},
                {text: '其他国际专业', country: 'global', index: 4}
            ],
            rank: [
                {text: '美国院校', country: 'america', index: 0},
                {text: '英国院校', country: 'british', index: 1},
                {text: '澳洲院校', country: 'australia', index: 2},
                {text: '加拿大院校', country: 'canada', index: 3},
                {text: '其他国际院校', country: 'global', index: 4}
            ]
        };

    };

    return service;
});
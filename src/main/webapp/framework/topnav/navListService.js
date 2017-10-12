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
                // 首页
                text: '首页',
                type: 'home',
                state: 'home'
            },
            {
                text: '留学',
                type: 'abroad',
                state: 'abroad',
                children: [
                    {text: '美国'},
                    {text: '英国'},
                    {text: '澳洲'},
                    {text: '新西兰'},
                    {text: '加拿大'},
                    {text: '一带一路国家'}
                ]
            },
            {
                text: "中外合作办学",
                type: 'cooperation',
                state: 'cooperation'
            },
            {
                text: '考试培训',
                type: 'training',
                state: 'training'
            },
            {
                text: '背景提升',
                type: 'promotion',
                state: 'promotion'
            },
            {
                text: 'STEM云中心',
                type: 'stem',
                state: 'stem'
            },
            {
                text: '关于我们',
                type: 'about',
                state: 'about'
            }
            // {
            //     type: 'parent',   // layout中菜单数据通过该值获取
            //     text: 'Layout示例', // menu中文本显示值
            //     state: 'parent.children11', //该menu激活时，初始激活的路由状态
            //     children: [ // menu子项配置
            //         {
            //             text: 'children1',
            //             state: 'parent.children11',
            //             children: [
            //                 {
            //                     text: 'children11',
            //                     state: 'parent.children11'
            //                 },
            //                 {
            //                     text: 'children12',
            //                     state: 'parent.children12'
            //                 }
            //             ]
            //         }
            //     ]
            // }
        ];
    };

    return service;
});
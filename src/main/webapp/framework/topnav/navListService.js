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
                type: 'home',
                text: '首页',
                state: 'home'
            },
            {
                text: '应用',
                type: 'application',
                state: 'application'
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
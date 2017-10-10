/**
 * Created by L.H.S on 2017/8/13.
 */

/**
 * 工程的module加载配置文件
 * module的基础路径为"工程名/"
 */

'use strict';
require.config({
    baseUrl: './',
    paths: {
        'ui-router': 'lib/angular',
        'lazy-load': 'lib'
    }
});

/**
 * 主启动类，手动给html element绑定module
 */
require(['framework/framework'], function (app) {
    angular.bootstrap($('html'), [app.name]);
});
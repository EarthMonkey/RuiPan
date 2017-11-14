/**
 * Created by L.H.S on 2017/8/14.
 */

define([''], function () {
    'use strict';

    var leftBarService = function () {

        var service = this;

        service.getMenus = function () {
            return leftBar;
        };

        var leftBar = [{
            id: 'user',
            label: '用户管理',
            children: [
                {id: 'backend.user', label: '用户管理'}
            ],
            isOpen: true
        }, {
            id: 'home',
            label: '首页管理',
            children: [
                {id: 'backend.home', label: '首页管理'}
            ]
        }, {
            id: 'abroad',
            label: '留学管理',
            children: [
                {id: 'backend.abroadCountry', label: '国家管理'},
                {id: 'backend.abroadService', label: '留学服务'},
                {id: 'backend.abroadMethod', label: '留学方案'}
            ]
        }, {
            id: 'special',
            label: '专业管理',
            children: [
                {id: 'backend.specialClass', label: '专业分类'},
                {id: 'backend.specialInfo', label: '信息管理'}
            ]
        }, {
            id: 'school',
            label: '院校管理',
            children: [
                {id: 'backend.schoolInfo', label: '院系信息管理'},
                {id: 'backend.schoolRank', label: '院校排名管理'}
            ]
        }, {
            id: 'success',
            label: '案例管理',
            children: [
                {id: 'backend.success', label: '案例管理'}
            ]
        }, {
            id: 'consult',
            label: '顾问管理',
            children: [
                {id: 'backend.consult', label: '顾问管理'}
            ]
        }, {
            id: 'promotion',
            label: '背景提升',
            children: [
                {id: 'backend.promotion', label: '背景提升'}
            ]
        }, {
            id: 'language',
            label: '语言培训',
            children: [
                {id: 'backend.language', label: '语言培训'}
            ]
        }, {
            id: 'cooperation',
            label: '合作办学',
            children: [
                {id: 'backend.cooperation', label: '合作办学'}
            ]
        }, {
            id: 'about',
            label: '关于我们',
            children: [
                {id: 'backend.about', label: '关于我们'}
            ]
        }];
    };

    var backendModule = angular.module('backend.config');
    backendModule.service('leftBarService', leftBarService);

});
/**
 * Created by L.H.S on 2017/8/14.
 */

define([''], function () {
    'use strict';

    var homeCtrl = function () {

        var service = this;

        service.getTest = function () {
            return "welcome to homepage";
        };

    };

    var homeModule = angular.module('home.config');
    homeModule.service('homeService', homeCtrl);

});
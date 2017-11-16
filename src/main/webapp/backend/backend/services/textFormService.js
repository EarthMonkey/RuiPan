/**
 * Created by L.H.S on 2017/11/16.
 */

define([''], function () {
    'use strict';

    var textFormService = function () {

        var service = this;

        /**
         * fields: [{id: 'english', label: 'chinese'}]
         * */
        service.open = function ($uibModal, title, fields, initObj) {
            return $uibModal.open({
                animation: true,
                templateUrl: 'backend/backend/views/modals/textForm.html',
                controller: 'textFormCtrl',
                resolve: {
                    title: function () {
                        return title;
                    },
                    fields: function () {
                        return fields;
                    },
                    initObj: function () {
                        return initObj;
                    }
                }
            });
        }
    };

    var backendModule = angular.module('backend.config');
    backendModule.service('textFormService', textFormService);
});
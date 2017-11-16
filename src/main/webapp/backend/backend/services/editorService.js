/**
 * Created by L.H.S on 2017/11/16.
 */

define([''], function () {
    'use strict';

    var editorService = function () {

        var service = this;

        /**
         * fields: [{id: 'english', label: 'chinese'}]
         * */
        service.open = function ($uibModal, title, initHtml) {
            return $uibModal.open({
                animation: true,
                templateUrl: 'backend/backend/views/modals/editorModal.html',
                controller: 'editorCtrl',
                size: 'lg',
                resolve: {
                    title: function () {
                        return title;
                    },
                    initHtml: function () {
                        return initHtml;
                    }
                }
            });
        }
    };

    var backendModule = angular.module('backend.config');
    backendModule.service('editorService', editorService);
});
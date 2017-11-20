/**
 * Created by L.H.S on 2017/11/16.
 */

define([''], function () {
    'use strict';

    var articleCtrl = ['$scope', '$state', '$timeout', 'commonService',
        function ($scope, $state, $timeout, commonService) {

            /**
             * 类型：new，modify
             * 返回url
             * fields
             * modify：id
             * */

            // 下拉框
            $scope.combox = {
                id: '',
                options: [],
                select: function (option) {
                    $scope.model[$scope.combox.id] = option;
                }
            };

            // 输入框
            $scope.fields = [];

            // editor
            $scope.config = {
                autoHeightEnabled: true
            };

            // 数据模型
            $scope.model = {
                content: ''
            };

            // 图片
            $scope.hasPicture = "";

            // 获取初始值
            var initInfo = JSON.parse($state.params.initInfo);
            if (initInfo.combox) {
                $scope.combox.id = initInfo.combox.id;
                $scope.combox.options = initInfo.combox.options;
                $scope.combox.label = initInfo.combox.label;
                // 绑定数据模型
                $scope.model[initInfo.combox.id] = initInfo.combox.options[0];
            }
            $scope.fields = initInfo.fields;
            $scope.title = initInfo.title;

            if (initInfo.initObj) {  // 修改
                // 根据id获取数据
                $.ajax({
                    url: initInfo.initObj.url,
                    type: 'GET',
                    success: function (resp) {
                        console.log(resp);
                        $scope.model = resp;
                        // 根据textPath获取html
                        if (resp.textPath) {
                            commonService.getHTML($scope, resp.textPath);
                        }
                    },
                    error: function (err) {
                        console.log(err);
                        showMess('danger', '获取数据失败');
                    }
                });
            }

            if (initInfo.readonly) {
                // 设置editor只读
                $scope.config.readonly = true;
            }

            // 获取HTML回调
            $scope.getHtmlCallback = function (text) {
                console.log(text);
                $scope.model.content = text;
            };

            // 选择图片
            $scope.selectImg = function () {
                var file = document.getElementById("imgInput");
                var pic = document.getElementById("preImg");
                if (window.FileReader) {//chrome,firefox7+,opera,IE10,IE9，IE9也可以用滤镜来实现
                    var oFReader = new FileReader();
                    oFReader.readAsDataURL(file.files[0]);
                    oFReader.onload = function (oFREvent) {
                        pic.src = oFREvent.target.result;
                    };
                }
                else if (document.all) {//IE8-
                    file.select();
                    var reallocalpath = document.selection.createRange().text//IE下获取实际的本地文件路径
                    if (window.ie6) $scope.imgSrc = reallocalpath; //IE6浏览器设置img的src为本地路径可以直接显示图片
                    else { //非IE6版本的IE由于安全问题直接设置img的src无法显示本地图片，但是可以通过滤镜来实现，IE10浏览器不支持滤镜，需要用FileReader来实现，所以注意判断FileReader先
                        pic.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src=\"" + reallocalpath + "\")";
                        pic.src = 'data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==';//设置img的src为base64编码的透明图片，要不会显示红xx
                    }
                }
                else if (file.files) {//firefox6-
                    if (file.files.item(0)) {
                        url = file.files.item(0).getAsDataURL();
                        pic.src = url;
                    }
                }
            };

            // 保存
            $scope.ok = function () {

                if ($scope.config.readonly) {
                    delete initInfo.readonly;
                    $state.go('backend.article', {initInfo: JSON.stringify(initInfo)});
                    return;
                }

                if ($scope.model.content === '') {
                    showMess('danger', '文本不可为空');
                    return;
                }

                if (initInfo.initObj) {
                    commonService.updateHTML($scope, $scope.model.content);
                } else {
                    commonService.uploadHTML($scope, $scope.model.content);
                }
            };

            // 上传HTML回调
            $scope.uploadCallback = function (path) {
                var data = angular.copy($scope.model);
                data.textPath = path;
                if (data.gid) {
                    data.gid = initInfo.gid;
                } else if (data.pid) {
                    data.pid = initInfo.pid;
                }
                data.flag = 1;
                delete data.content;
                $.ajax({
                    url: initInfo.ajaxUrl,
                    type: 'POST',
                    data: data,
                    success: function () {
                        showMess('success', '保存成功', initInfo.backState);
                    },
                    error: function (err) {
                        console.log(err);
                        showMess('danger', '保存方案失败');
                    }
                });
            };

            // 更新HTML回调
            $scope.updateHtmlCallback = function () {
                var data = angular.copy($scope.model);
                if (data.gid) {
                    data.gid = initInfo.gid;
                } else if (data.pid) {
                    data.pid = initInfo.pid;
                }
                data.flag = 1;
                delete data.content;
                $.ajax({
                    url: initInfo.ajaxUrl,
                    type: 'PUT',
                    data: data,
                    success: function () {
                        showMess('success', '更新成功', initInfo.backState);
                    },
                    error: function (err) {
                        console.log(err);
                        showMess('danger', '更新方案失败');
                    }
                })
            };

            // 返回上一级
            $scope.cancel = function () {
                $state.go(initInfo.backState);
            };

            function showMess(type, data, backState) {
                commonService.showMessage($scope, {
                    type: type,
                    content: data
                }, backState);
            }
        }];

    var backendModule = angular.module('backend.config');
    backendModule.controller('articleCtrl', articleCtrl);
});
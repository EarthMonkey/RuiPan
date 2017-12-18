/**
 * Created by L.H.S on 2017/11/16.
 */

define([''], function () {
    'use strict';

    var textFormCtrl = ['$scope', '$uibModalInstance', '$timeout', 'title', 'fields', 'initObj',
        function ($scope, $uibModalInstance, $timeout, title, fields, initObj) {

            $scope.title = title;  // 标题
            $scope.fields = fields;  // id,label,placeholder

            var ImgObj = "";

            // 数据模型
            $scope.model = {};
            if (initObj) {
                $scope.model = angular.copy(initObj);
                for (var i = 0; i < fields.length; i++) {
                    if (fields[i].type === 'img') {
                        ImgObj = {
                            id: fields[i].id,
                            file: '',
                            path: initObj[fields[i].id]
                        }
                    }
                }
            } else {
                fields.forEach(function (item) {
                    $scope.model[item.id] = "";
                    if (item.type == 'checkbox') {
                        $scope.model[item.id] = false;
                    }
                    if (item.type === 'img') {
                        ImgObj = {
                            id: item.id,
                            file: '',
                            path: ''
                        }
                    }
                });
            }

            // 下拉框
            $scope.comboxSelect = function (op, id) {
                $scope.model[id] = op;
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

                ImgObj.file = file.files[0];
            };

            function uploadImg() {
                var formData = new FormData();
                formData.append('oneFile', ImgObj.file);

                $.ajax({
                    url: '/oneUpload',
                    type: 'POST',
                    data: formData,
                    processData: false,
                    contentType: false,
                    success: function (resp) {
                        ImgObj.path = resp;
                        $scope.model[ImgObj.id] = resp;

                        for (var key in $scope.model) {
                            if ($scope.model[key] === "") {
                                showError("请填写完整信息");
                                return;
                            }
                        }

                        $uibModalInstance.close($scope.model);
                    },
                    error: function (err) {
                        console.log(err);
                        showError('上传图片失败');
                    }
                });
            }

            $scope.ok = function () {

                if (ImgObj !== '') { // 先上传图片
                    if (initObj) {
                        if (ImgObj.file !== '') {
                            uploadImg();
                        } else {

                            for (var key in $scope.model) {
                                if ($scope.model[key] === "") {
                                    showError("请填写完整信息");
                                    return;
                                }
                            }
                            $scope.model[ImgObj.id] = initObj[ImgObj.id];
                            $uibModalInstance.close($scope.model);
                        }
                    } else {
                        if (ImgObj.file === '') {
                            showError('请上传图片');
                            return;
                        }
                        uploadImg();
                    }
                } else {

                    for (var key in $scope.model) {
                        if ($scope.model[key] === "") {
                            showError("请填写完整信息");
                            return;
                        }
                    }

                    if ($scope.fields[0].type === 'password') {
                        if ($scope.model.newPassword !== $scope.model.confirmPass) {
                            showError("两次密码不一致");
                            return;
                        }
                    }

                    $uibModalInstance.close($scope.model);
                }

            };

            $scope.cancel = function () {
                $uibModalInstance.dismiss('cancel');
            };

            function showError(message) {
                $scope.modalMess = message;
                $timeout(function () {
                    $scope.modalMess = "";
                }, 3000);
            }
        }];

    var backendModule = angular.module('backend.config');
    backendModule.controller('textFormCtrl', textFormCtrl);
});
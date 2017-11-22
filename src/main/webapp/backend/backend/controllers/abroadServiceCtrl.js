/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var abroadServiceCtrl = ['$scope', 'commonService', function ($scope, commonService) {

        // 删选
        $scope.selectedCoun = '美国';
        $scope.filterCountry = [
            {id: '美国', label: '美国'},
            {id: '英国', label: '英国'},
            {id: '澳洲', label: '澳洲'},
            {id: '加拿大', label: '加拿大'},
            {id: '其他', label: '其他'}
        ];

        $scope.selectedEdu = '研究生';
        $scope.filterEdu = [
            {id: '研究生', label: '研究生'},
            {id: '本科生', label: '本科生'},
            {id: '高中生', label: '高中生'}
        ];

        $scope.filterClick = function (syb, selected) {
            if (syb == 0) {
                $scope.selectedCoun = selected.id;
            } else {
                $scope.selectedEdu = selected.id;
            }
            getGid();
        };

        var GID = '';

        getGid();
        function getGid() {
            $.ajax({
                url: '/StudyAbroad/getGid?country=' + $scope.selectedCoun + "&grade=" + $scope.selectedEdu,
                type: 'GET',
                success: function (resp) {
                    GID = resp;
                    getData();
                },
                error: function (err) {
                    console.log(err);
                    showMess('danger', '服务器出错啦');
                }
            })
        }

        $scope.conditionList = [];
        $scope.questionList = [];

        // 初始化数据
        function getData() {
            // 硬性条件
            $.ajax({
                url: '/StudyAbroad/getHardCondionByGid?gid=' + GID,
                type: 'GET',
                success: function (resp) {
                    for (var key in resp) {
                        resp[key].forEach(function (item) {
                            $scope.conditionList.push(item);
                        });
                    }
                },
                error: function (err) {
                    console.log(err);
                    showMess('danger', '获取硬性条件失败');
                }
            });

            // 申请要素
            $.ajax({
                url: '/StudyAbroad/getApplicationElementByGid?gid=' + GID,
                type: 'GET',
                success: function (resp) {
                    resp.forEach(function (item) {
                        for (var i = 0; i < $scope.factorTabs.length; i++) {
                            if (item.category === $scope.factorTabs[i].category) {
                                $scope.factorTabs[i] = item;
                                break;
                            }
                        }
                    });
                },
                error: function (err) {
                    console.log(err);
                    showMess('danger', '获取申请要素失败');
                }
            });

            // 常见问题
            $.ajax({
                url: '/StudyAbroad/getQuestionsByGid?gid=' + GID,
                type: 'GET',
                success: function (resp) {
                    $scope.questionList = resp;
                },
                error: function (err) {
                    console.log(err);
                    showMess('danger', '获取常见问题失败');
                }
            });
        }

        // 增加硬性条件
        var conditionFields = [
            {id: 'rank', label: '等级标题', placeholder: 'e.g.Top10申请标准'},
            {id: 'subject', label: '要求标题', placeholder: 'e.g.GPA成绩要求'},
            {id: 'score', label: '要求内容', placeholder: 'e.g.3.0+'}
        ];

        $scope.addCondition = function () {

            var conditonInstance = commonService.openTextForm('增加硬性条件要求', conditionFields);
            conditonInstance.result.then(function (resp) {
                resp.gid = GID;
                $.ajax({
                    url: "/StudyAbroad/addHardCondition",
                    type: 'POST',
                    data: resp,
                    success: function (data) {
                        $scope.conditionList.push(data);
                    },
                    error: function (err) {
                        console.log(err);
                        showMess('danger', '添加失败');
                    }
                });
            })
        };

        // 修改硬性条件
        $scope.modCondition = function (item, pos) {

            var conditionInstance = commonService.openTextForm('修改硬性条件要求', conditionFields, item);
            conditionInstance.result.then(function (resp) {
                resp.id = item.id;
                resp.gid = GID;
                $.ajax({
                    url: '/StudyAbroad/changeHardCondition',
                    type: 'PUT',
                    data: resp,
                    success: function (data) {
                        $scope.conditionList[pos] = resp;
                        showMess('success', '修改成功');
                    },
                    error: function (err) {
                        console.log(err);
                        showMess('danger', '修改失败');
                    }
                })
            });
        };

        // 删除硬性条件
        $scope.delCondition = function (item, pos) {
            var conditionInstance = commonService.confirm(item.rank + " " + item.subject);
            conditionInstance.result.then(function (resp) {
                if (resp) {
                    $.ajax({
                        url: '/StudyAbroad/deleteHardCondition?id=' + item.id,
                        type: 'DELETE',
                        success: function () {
                            $scope.conditionList.splice(pos, 1);
                            showMess('success', '删除成功');
                        },
                        error: function (err) {
                            console.log(err);
                            showMess('danger', '删除失败');
                        }
                    })
                }
            });
        };

        // 申请要素
        $scope.factorTabs = [
            {category: '申请基本条件'},
            {category: '申请材料'},
            {category: '选校因素'},
            {category: '所需费用'}
        ];

        // 添加 or 修改
        $scope.editFactor = function (tab, pos) {
            var factorInstance = commonService.openEditor(tab.category, tab.synopsis, 200);
            factorInstance.result.then(function (data) {
                tab.gid = GID;
                tab.synopsis = data;
                tab.textPath = "";
                tab.flag = 1;

                console.log(tab);
                if (tab.id) { // 修改
                    $.ajax({
                        url: '/StudyAbroad/updateApplicationElement',
                        type: 'PUT',
                        data: tab,
                        success: function (resp) {
                            $scope.factorTabs[pos] = tab;
                            showMess('success', '修改成功');
                        },
                        error: function (err) {
                            console.log(err);
                            showMess('danger', '修改失败');
                        }
                    });

                } else { // 创建
                    $.ajax({
                        url: '/StudyAbroad/addApplicationElement',
                        type: 'POST',
                        data: tab,
                        success: function (resp) {
                            $scope.factorTabs[pos] = tab;
                            showMess('success', '添加成功');
                        },
                        error: function (err) {
                            console.log(err);
                            showMess('danger', '添加失败');
                        }
                    });
                }
            });
        };

        // 增加问题
        var quesFields = [
            {id: 'question', label: '常见问题'},
            {id: 'answer', label: '问题答案'},
            {id: 'isShow', label: '推荐展示', type: 'checkbox'}
        ];

        $scope.addQuestion = function () {

            var questionInstance = commonService.openTextForm('添加常见问题', quesFields);
            questionInstance.result.then(function (data) {
                data.gid = GID;
                $.ajax({
                    url: '/StudyAbroad/addQuestion',
                    type: 'POST',
                    data: data,
                    success: function (resp) {
                        showMess('success', '添加成功');
                        $scope.questionList.push(resp);
                    },
                    error: function (err) {
                        console.log(err);
                        showMess('danger', '添加失败');
                    }
                });
            })
        };

        // 修改问题
        $scope.modQues = function (item, pos) {

            var questionInstance = commonService.openTextForm('修改常见问题', quesFields, item);
            questionInstance.result.then(function (data) {
                data.gid = GID;
                data.id = item.id;

                $.ajax({
                    url: '/StudyAbroad/updateQuestion',
                    type: 'PUT',
                    data: data,
                    success: function () {
                        $scope.questionList[pos] = data;
                        showMess('success', '修改成功');
                    },
                    error: function (err) {
                        console.log(err);
                        showMess('danger', '修改失败');
                    }
                })
            });
        };

        // 删除问题
        $scope.delQues = function (item, pos) {
            var quesInstance = commonService.confirm("常见问题：" + item.question);
            quesInstance.result.then(function (resp) {
                if (resp) {
                    $.ajax({
                        url: '/StudyAbroad/deleteQuestion?id=' + item.id,
                        type: 'DELETE',
                        success: function () {
                            $scope.questionList.splice(pos, 1);
                            showMess('success', '删除成功');
                        },
                        error: function (err) {
                            console.log(err);
                            showMess('danger', '删除失败');
                        }
                    })
                }
            });
        };

        function showMess(type, data) {
            commonService.showMessage($scope, {
                type: type,
                content: data
            });
        }

    }];

    var backendModule = angular.module('backend.config');
    backendModule.controller('abroadServiceCtrl', abroadServiceCtrl);
});
/**
 * Created by L.H.S on 2017/11/13.
 */


define([''], function () {
    'use strict';

    var specialInfoCtrl = ['$scope', '$state', 'commonService', function ($scope, $state, commonService) {

        $scope.selectedCoun = 'america';
        $scope.filterCountry = [];

        $scope.selectedEdu = 'business';
        $scope.filterEdu = [];

        $scope.selectedSp = 'finance';
        $scope.filterSp = [];

        var Sp_Map = [];

        $scope.filterClick = function (syb, selected) {
            if (syb == 0) {
                $scope.selectedCoun = selected;
                getSpClass();
            } else if (syb == 1) {
                $scope.selectedEdu = selected;
                $scope.filterSp = Sp_Map[selected.index];
                $scope.selectedSp = $scope.filterSp[0];
            } else {
                $scope.selectedSp = selected;
                getData();
            }
        };

        // 获取国家
        $.ajax({
            url: "/StudyAbroad/getAllCountry",
            type: 'GET',
            success: function (data) {
                $scope.filterCountry = data;
                $scope.selectedCoun = data[0];
                getSpClass();
            },
            error: function (err) {
                console.log(err);
                showMess('danger', '获取国家列表失败');
            }
        });

        // 获取大类
        function getSpClass() {
            Sp_Map = [[]];
            $.ajax({
                url: '/Profession/getAllCategoryByCountry?country=' + $scope.selectedCoun,
                type: 'GET',
                success: function (resp) {
                    $scope.filterEdu = [];

                    var count = 0;
                    for (var key in resp) {
                        $scope.filterEdu.push({id: key, index: count});
                        Sp_Map[count] = [];
                        resp[key].forEach(function (item) {
                            Sp_Map[count].push({
                                id: item.subclassification,
                                pid: item.pid
                            });
                        });
                        count++;
                    }

                    $scope.selectedEdu = $scope.filterEdu[0];
                    $scope.filterSp = Sp_Map[0];
                    $scope.selectedSp = $scope.filterSp[0];
                    getData();
                },
                error: function (err) {
                    console.log(err);
                    showMess('danger', '获取大类专业失败');
                }
            })
        }

        $scope.detailAdvice = {};
        $scope.courseList = [];
        $scope.workList = [];
        $scope.fieldList = [];
        $scope.conList = [];
        $scope.salaryList = [];

        // 获取数据
        function getData() {
            // 详情、建议
            $.ajax({
                url: '/Profession/getProfessionIntroducePublished?pid=' + $scope.selectedSp.pid,
                type: 'GET',
                success: function (resp) {
                    $scope.detailAdvice = resp;
                },
                error: function (err) {
                    console.log(err);
                    showMess('danger', '获取详情和建议失败');
                }
            });

            // 专业课程
            $.ajax({
                url: '/Profession/getProfessionCourse?pid=' + $scope.selectedSp.pid,
                type: 'GET',
                success: function (resp) {
                    $scope.courseList = resp;
                },
                error: function (err) {
                    console.log(err);
                    showMess('danger', '获取课程列表失败');
                }
            });

            // 就业去向、领域
            $.ajax({
                url: '/Profession/getEmploymentCompany?pid=' + $scope.selectedSp.pid,
                type: 'GET',
                success: function (resp) {
                    $scope.workList = resp;
                },
                error: function (err) {
                    console.log(err);
                    showMess('danger', '获取就业去向失败');
                }
            });

            // 领域
            $.ajax({
                url: '/Profession/getEmploymentPost?pid=' + $scope.selectedSp.pid,
                type: 'GET',
                success: function (resp) {
                    $scope.fieldList = resp;
                },
                error: function (err) {
                    console.log(err);
                    showMess('danger', '获取就业领域失败');
                }
            });

            // 申请条件
            $.ajax({
                url: '/Profession/getApplicationAdvice?pid=' + $scope.selectedSp.pid,
                type: 'GET',
                success: function (resp) {
                    $scope.conList = resp;
                },
                error: function (err) {
                    console.log(err);
                    showMess('danger', '获取申请条件失败');
                }
            });

            // 薪资水平
            $.ajax({
                url: '/Profession/getSalary?pid=' + $scope.selectedSp.pid,
                type: 'GET',
                success: function (resp) {
                    $scope.salaryList = resp;
                },
                error: function (err) {
                    console.log(err);
                    showMess('danger', '获取薪资水平失败');
                }
            })
        }

        var courseFields = [
            {id: 'majorField', label: '专业方向'},
            {id: 'majorCourse', label: '专业课程'}
        ];

        // 创建/修改详情建议
        $scope.editDetail = function () {
            // 若修改则添加 initObj

            var initInfo = {
                title: '编辑专业详情和申请建议',
                fields: [
                    {id: 'applicationAdvice', label: '申请建议', type: 'textarea'},
                    {id: 'detailSynopsis', label: '详情导语', type: 'textarea'}
                ],
                backState: 'backend.specialInfo',
                ajaxUrl: '/Profession/addProfessionIntroduce',
                pid: $scope.selectedSp.pid,
                gidKey: 'pid',
                initObj: {
                    objId: $scope.selectedSp.pid,
                    url: '/Profession/getProfessionIntroducePublished?pid=' + $scope.selectedSp.pid
                }
            };
            $state.go('backend.article', {initInfo: JSON.stringify(initInfo)});
        };

        $scope.getDetail = function () {
            var initInfo = {
                title: '专业详情和申请建议',
                fields: [
                    {id: 'applicationAdvice', label: '申请建议', type: 'textarea'},
                    {id: 'detailSynopsis', label: '详情导语', type: 'textarea'}
                ],
                backState: 'backend.specialInfo',
                ajaxUrl: '/Profession/addProfessionIntroduce',
                pid: $scope.selectedSp.pid,
                pdiKey: 'pid',
                initObj: {
                    objId: $scope.detailAdvice.pid,
                    url: '/Profession/getProfessionIntroducePublished?pid=' + $scope.detailAdvice.pid
                },
                readonly: true
            };

            $state.go("backend.article", {initInfo: JSON.stringify(initInfo)});
        };

        // 添加专业课程
        $scope.addCourse = function () {
            var courInstance = commonService.openTextForm('添加专业课程', courseFields);
            courInstance.result.then(function (data) {
                data.pid = $scope.selectedSp.pid;
                $.ajax({
                    url: '/Profession/addProfessionCourse',
                    type: "POST",
                    data: data,
                    success: function (resp) {
                        $scope.courseList.push(resp);
                        showMess('success', '添加成功');
                    },
                    error: function (err) {
                        console.log(err);
                        showMess('danger', '添加失败');
                    }
                })
            })
        };

        // 修改专业课程
        $scope.modCourse = function (item, pos) {
            var courInstance = commonService.openTextForm('修改专业课程', courseFields, item);
            courInstance.result.then(function (data) {
                data.pid = $scope.selectedSp.pid;
                data.id = item.id;
                $.ajax({
                    url: '/Profession/updateProfessionCourse',
                    type: 'PUT',
                    data: data,
                    success: function (resp) {
                        $scope.courseList[pos] = resp;
                    },
                    error: function (err) {
                        console.log(errr);
                        showMess('danger', '修改失败');
                    }
                })
            });
        };

        // 删除专业课程
        $scope.delCourse = function (item, pos) {
            var courInstance = commonService.confirm("专业课程：" + item.majorCourse);
            courInstance.result.then(function (resp) {
                if (resp) {
                    $.ajax({
                        url: '/Profession/deleteProfessionCourse?id=' + item.id,
                        type: 'DELETE',
                        success: function () {
                            $scope.courseList.splice(pos, 1);
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

        var workFields = [
            {id: 'employmentCompany', label: '就业去向'},
            {id: 'logo', label: 'logo图片', type: 'img'}
        ];
        // 添加就业去向
        $scope.addWork = function () {
            commonService.openTextForm('添加就业去向', workFields).result
                .then(function (data) {
                    data.pid = $scope.selectedSp.pid;

                    $.ajax({
                        url: '/Profession/addEmploymentCompany',
                        type: 'POST',
                        data: data,
                        success: function (resp) {
                            $scope.workList.push(resp);
                            showMess('success', '添加成功');
                        },
                        error: function (err) {
                            console.log(err);
                            showMess('danger', '添加失败');
                        }
                    });

                });
        };

        // 修改就业去向
        $scope.modWork = function (item, pos) {
            commonService.openTextForm('修改就业去向', workFields, item).result
                .then(function (data) {
                    data.pid = item.pid;
                    data.id = item.id;

                    $.ajax({
                        url: '/Profession/updateEmploymentCompany',
                        type: 'PUT',
                        data: data,
                        success: function (resp) {
                            $scope.workList[pos] = resp;
                            showMess('success', '修改成功');
                        },
                        error: function (err) {
                            console.log(err);
                            showMess('danger', '修改失败');
                        }
                    });
                });
        };

        // 删除就业去向
        $scope.delWork = function (item, pos) {
            commonService.confirm("就业去向：" + item.employmentCompany).result
                .then(function (resp) {
                    if (resp) {
                        $.ajax({
                            url: '/Profession/deleteEmploymentCompany?id=' + item.id,
                            type: 'DELETE',
                            success: function () {
                                $scope.workList.splice(pos, 1);
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

        // 添加就业去向
        $scope.addField = function () {
            var field = [{id: 'post', label: '就业领域'}];
            commonService.openTextForm('添加就业领域', field).result
                .then(function (data) {
                    data.pid = $scope.selectedSp.pid;

                    $.ajax({
                        url: '/Profession/addEmploymentPost',
                        type: 'POST',
                        data: data,
                        success: function (resp) {
                            $scope.fieldList.push(resp);
                            showMess('success', '添加成功');
                        },
                        error: function (err) {
                            console.log(err);
                            showMess('danger', '添加失败');
                        }
                    });

                });
        };

        // 删除就业去向
        $scope.delField = function (item, pos) {
            commonService.confirm('就业领域：' + item.post).result
                .then(function (resp) {
                    if (resp) {
                        $.ajax({
                            url: '/Profession/deleteEmploymentPost?id=' + item.id,
                            type: 'DELETE',
                            success: function () {
                                $scope.fieldList.splice(pos, 1);
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

        var conFields = [
            {id: 'item', label: '条件项'},
            {id: 'advice', label: '申请建议', type: 'textarea'}
        ];

        // 增加申请条件
        $scope.addCon = function () {
            commonService.openTextForm('申请条件', conFields).result
                .then(function (data) {
                    data.pid = $scope.selectedSp.pid;

                    $.ajax({
                        url: '/Profession/addApplicationAdvice',
                        type: 'POST',
                        data: data,
                        success: function (resp) {
                            $scope.conList.push(resp);
                            showMess('success', '添加成功');
                        },
                        error: function (err) {
                            console.log(err);
                            showMess('danger', '添加失败');
                        }
                    });

                });
        };

        // 修改申请条件
        $scope.modCon = function (item, pos) {
            commonService.openTextForm('申请条件', conFields, item).result
                .then(function (data) {
                    data.pid = item.pid;
                    data.id = item.id;

                    $.ajax({
                        url: '/Profession/updateApplicationAdvice',
                        type: 'PUT',
                        data: data,
                        success: function (resp) {
                            $scope.conList[pos] = resp;
                            showMess('success', '修改成功');
                        },
                        error: function (err) {
                            console.log(err);
                            showMess('danger', '修改失败');
                        }
                    });

                });
        };

        // 删除申请条件
        $scope.delCon = function (item, pos) {
            commonService.confirm('就业领域：' + item.item).result
                .then(function (resp) {
                    if (resp) {
                        $.ajax({
                            url: '/Profession/deleteApplicationAdvice?id=' + item.id,
                            type: 'DELETE',
                            success: function () {
                                $scope.conList.splice(pos, 1);
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

        var salaryFields = [
            {id: 'item', label: '所属领域'},
            {id: 'salary', label: '薪资水平', placeholder: '整数或小数'}
        ];

        // 增加薪资水平
        $scope.addSalary = function () {
            commonService.openTextForm('薪资水平', salaryFields).result
                .then(function (data) {
                    data.pid = $scope.selectedSp.pid;

                    $.ajax({
                        url: '/Profession/addSalary',
                        type: 'POST',
                        data: data,
                        success: function (resp) {
                            $scope.salaryList.push(resp);
                            showMess('success', '添加成功');
                        },
                        error: function (err) {
                            console.log(err);
                            showMess('danger', '添加失败');
                        }
                    });

                });
        };

        // 修改薪资水平
        $scope.modSalary = function (item, pos) {
            commonService.openTextForm('薪资水平', salaryFields, item).result
                .then(function (data) {
                    data.pid = item.pid;
                    data.id = item.id;

                    $.ajax({
                        url: '/Profession/updateSalary',
                        type: 'PUT',
                        data: data,
                        success: function (resp) {
                            $scope.salaryList[pos] = resp;
                            showMess('success', '修改成功');
                        },
                        error: function (err) {
                            console.log(err);
                            showMess('danger', '修改失败');
                        }
                    });

                });
        };

        // 删除薪资水平
        $scope.delSalary = function (item, pos) {
            commonService.confirm('薪资水平：' + item.item).result
                .then(function (resp) {
                    if (resp) {
                        $.ajax({
                            url: '/Profession/deleteSalary?id=' + item.id,
                            type: 'DELETE',
                            success: function () {
                                $scope.salaryList.splice(pos, 1);
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
    backendModule.controller('specialInfoCtrl', specialInfoCtrl);
});
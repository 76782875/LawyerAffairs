// 将新增的弹出层放入内存中
var addhtml = $("#add_dialog").html();
// 基本路径
var basePath = $("#basePath").val();
// 初始化列表
var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/rolelist.htm?isPaging=1',
                data: dataTableAjaxData(data, $("#queryform").serializeArray()),
                success: function (res) {
                    callback(res);
                }
            });
        },
        "columns": [
            {
                "name": "id", "data": function (row) {
                return '<input type="checkbox" value="' + row.id + '" name="id">';
            }
            },
            {"name": "roleName", "data": "roleName"},
            {
                "data": function (row) {
                    var operator = '<div class="operation_box"><button class="btn btn-primary btn-xs edit_btn" onclick="editRole(' + row.id + ',' + "'" + row.roleName + "'" + ')"><i class="icon-pencil"></i> 编辑</button>';
                    operator += '<button class="btn btn-danger btn-xs del_btn" onclick="deleteRole(' + row.id + ')"><i class="icon-trash"></i> 删除</button></div>';
                    return operator;
                }
            }
        ],
        ordering: false
    });
};

// 初始化列表
initDataTable();

// 刷新列表
var refreshDataTable = function () {
    $('#dataTable').DataTable().ajax.reload();
};

/* 下面是关于树形菜单 */
//添加-setting, onCheckForBusiness
var setting = {
    check: {
        enable: true,
        chkStyle: "checkbox",
        chkboxType: {"Y": "ps", "N": "ps"}
    },
    callback: {
        onCheck: onCheckForBusiness
    },
    data: {
        simpleData: {
            enable: true,
            pIdKey: "pid"
        }
    },
    view: {
        showIcon: false
    }
};

//添加-获取数据，根据父id做级联
function onCheckForBusiness() {
    var zTree = $.fn.zTree.getZTreeObj("add_treeDemo");
    var nodes = zTree.getCheckedNodes(true);
    var ids = [];
    nodes.sort(function compare(a, b) {
        return a.id - b.id;
    });
    for (var i = 0, l = nodes.length; i < l; i++) {
        if (nodes[i].id !== 0) {
            ids.push(nodes[i].id);
        }
    }
    $("#addIds").val(ids);
}

//添加按钮点击事件
function addBtn() {
    $("#add_dialog").html(addhtml);
    $('#add_dialog').modal('show');
    loadMenu();
}

//添加-加载菜单
function loadMenu() {
    LSFetch({
        url: basePath + '/roleauthmenu.htm',
        success: function (data) {
            var dataMenu = [];
            var all = {
                checked: false,
                id: 0,
                name: "全部",
                open: true,
                pid: -1
            };
            dataMenu.push(all);
            data = $.grep(data, function (val) {
                if (val.id !== 1)
                    return true;
            }, false);
            $.merge(dataMenu, data);
            $.fn.zTree.init($("#add_treeDemo"), setting, dataMenu);
        }
    });
}

//添加角色的保存事件
function addRoleSaveBtn() {
    if (!$("#addForm").valid()) {
        return;
    }
    LSFetch({
        url: basePath + '/addroleandauth.htm',
        data: {
            roleName: $("#roleName").val(),
            authIds: $("#addIds").val("1," + $("#addIds").val()).val()
        },
        success: function (res) {
            if (res === -2) {
                showerror("请选择权限");
                return;
            }
            if (res === -1) {
                showerror("角色名称已存在");
                return;
            }
            if (res >= 1) {
                refreshDataTable();
                $('#add_dialog').modal('hide');
            } else {
                showerror("添加失败");

            }
        }
    });
}

//编辑按钮点击时间
function editRole(id, roleName) {
    $("#editRoleName").val(roleName);
    LSFetch({
        url: basePath + '/roleauthmenu.htm',
        success: function (data) {
            LSFetch({
                url: basePath + '/queryauthidbyroleid.htm',
                data: {
                    roleId: id
                },
                success: function (res) {
                    var ids = [];
                    var isCheck = true;
                    var checkNum = 0;
                    for (var i = 0; i < data.length; i++) {
                        for (var j = 0; j < res.length; j++) {
                            if (res[j] === data[i].id) {
                                data[i].checked = true;
                                if (res[j] !== 0) {
                                    ids.push(res[j]);
                                }
                            }
                        }
                        if (!data[i].checked) {
                            checkNum = checkNum + 1;
                        }
                    }
                    if (checkNum > 0 && checkNum === data.length) {
                        isCheck = false;
                    }
                    ids = $.grep(ids, function (val) {
                        if (val !== 1)
                            return true;
                    }, false);
                    $("#editIds").val(ids.sort(sortNumber));
                    var dataMenu = [];
                    var all = {
                        checked: isCheck,
                        id: 0,
                        name: "全部",
                        open: true,
                        pid: -1
                    };
                    data = $.grep(data, function (val) {
                        if (val.id !== 1)
                            return true;
                    }, false);
                    dataMenu.push(all);
                    $.merge(dataMenu, data);
                    $.fn.zTree.init($("#edit_treeDemo"), editSetting, dataMenu);
                }
            });
        }
    });
    $("#roleId").val(id);
    $("#roleNameHidden").val(roleName);
    $("#edit_dialog").modal('show');
}

//排序函数
function sortNumber(a, b) {
    return a - b;
}

//编辑-editSetting,editOnCheckForBusiness
var editSetting = {
    check: {
        enable: true,
        chkStyle: "checkbox",
        chkboxType: {"Y": "ps", "N": "ps"}
    },
    callback: {
        onCheck: editOnCheckForBusiness
    },
    data: {
        simpleData: {
            enable: true,
            pIdKey: "pid"
        }
    },
    view: {
        showIcon: false
    }
};

//编辑-获取数据，根据父id做级联
function editOnCheckForBusiness() {
    var zTree = $.fn.zTree.getZTreeObj("edit_treeDemo");
    var nodes = zTree.getCheckedNodes(true);
    var ids = [];
    nodes.sort(function compare(a, b) {
        return a.id - b.id;
    });
    for (var i = 0, l = nodes.length; i < l; i++) {
        if (nodes[i].id !== 0) {
            ids.push(nodes[i].id);
        }
    }
    $("#editIds").val(ids);
}

//编辑保存事件
function editRoleSaveBtn() {
    if (!$("#editForm").valid()) {
        return;
    }
    var roleName;
    if ($("#roleNameHidden").val() === $("#editRoleName").val()) {
        roleName = '';
    } else {
        roleName = $("#editRoleName").val();
    }
    LSFetch({
        url: basePath + '/editroleandauth.htm',
        data: {
            roleId: $("#roleId").val(),
            roleName: roleName,
            authIds: $("#editIds").val("1," + $("#editIds").val()).val()
        },
        success: function (res) {
            if (res === -2) {
                showerror("请选择权限");
                return;
            }
            if (res === -1) {
                showerror("角色名称已存在");
                return;
            }
            if (res >= 1) {
                refreshDataTable();
                $('#edit_dialog').modal('hide');
            } else {
                showerror("编辑失败");

            }
        }
    });
}

//删除按钮点击事件
function deleteRole(id) {
    $("#deleteId").val(id);
    $('#del_dialog').modal('show');
}

//删除单个管理员 保存事件
function deleteRoleSaveBtn() {
    LSFetch({
        url: basePath + '/deleteroleandauth.htm',
        data: {
            roleIds: $("#deleteId").val()
        },
        success: function (data) {
            if (data === -1) {
                showerror("请至少选择一条记录");
                return;
            }
            if (data >= 1) {
                $('#del_dialog').modal('hide');
                refreshDataTable();
            }
        }
    });
}

// 准备批量删除
function toDeleteAll() {
    // 选中的id
    var ids = getSelectIds("id");
    if (ids.length === 0) {
        showerror('请至少选择一条记录!');
        return;
    }
    showdeleteconfirm("确定要删除选择的角色?", ids);
}

// 批量删除
function deleteAll(ids) {
    LSFetch({
        url: basePath + '/deleteroleandauth.htm',
        data: {
            roleIds: ids
        },
        success: function (data) {
            if (data === -1) {
                showerror("请至少选择一条记录");
                return;
            }
            if (data >= 1) {
                $('#confirm').modal('hide');
                refreshDataTable();
            }
        }
    });
}
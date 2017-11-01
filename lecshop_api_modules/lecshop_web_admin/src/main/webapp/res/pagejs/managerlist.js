// 将新增的弹出层放入内存中
var addhtml = $("#add_dialog").html();
// 基本路径
var basePath = $("#basePath").val();
// 初始化列表
var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/managerlist.htm',
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
            {"name": "name", "data": "username"},
            {
                "name": "createTime", "data": function (row) {
                if (row.createTime == '' || row.createTime == null) {
                    return '暂无创建信息';
                } else {
                    return row.createTime;
                }
            }
            },
            {
                "data": function (row) {
                    var operator = '';
                    if (row.isUse == 0) {
                        operator = '<span class="label label-success">是</span>'
                    } else {
                        operator = '<span class="label label-default">否</span>';
                    }
                    return operator;
                }
            },
            {
                "data": function (row) {
                    var operator = '<div class="operation_box"><button class="btn btn-primary btn-xs edit_btn" onclick="editManager(' + row.id + ')"><i class="icon-pencil"></i> 编辑</button>';
                    operator += '<button class="btn btn-danger btn-xs del_btn" onclick="deleteManager(' + row.id + ')"><i class="icon-trash"></i> 删除</button></div>';
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

//加载角色下拉菜单
function addManagerBtn() {
    $("#add_dialog").html(addhtml);
    $('#add_dialog').modal('show');
    LSFetch({
        url: basePath + '/rolelist.htm?isPaging=0',
        success: function (res) {
            var html = '';
            if (res.data.length > 0) {
                for (var i = 0; i < res.data.length; i++) {
                    html = html + '<option value="' + res.data[i].id + '">' + res.data[i].roleName + '</option>';
                }
            } else {
                html = html + '<option value="">暂无角色</option>';
            }
            $("#roleList").append(html);
        }
    });
}

//添加管理员保存按钮点击事件
function addSaveBtn() {
    if (!$("#addForm").valid()) {
        return;
    }
    var manager = {
        username: $("#addForm input[name='username']").val(),
        password: $("#addForm input[name='password']").val(),
        isUse: $("#addForm select[name='isUse']").val()
    };
    LSFetch({
        url: basePath + '/addmanager.htm?roleId=' + $("#roleList").val(),
        data: JSON.stringify(manager),
        contentType: "application/json;charset=utf-8",
        success: function (res) {
            if (res == -1) {
                showerror("添加失败,管理员名称重复");
                return;
            }
            if (res == 1) {
                $('#add_dialog').modal('hide');
                showerror("添加成功");
                refreshDataTable();
            } else {
                showerror("添加失败");
            }
        }
    });
}

//管理员编辑按钮点击事件
function editManager(id) {
    $("#managerIdHidden").val(id);
    LSFetch({
        url: basePath + '/queryroleid.htm',
        data: {
            managerId: id
        },
        success: function (result) {
            $("#editForm input[name='username']").val(result.userName);
            $("#editIsUse").val(result.isUse);
            LSFetch({
                url: basePath + '/rolelist.htm?isPaging=0',
                success: function (res) {
                    var html = '';
                    if (res.data.length > 0) {
                        for (var i = 0; i < res.data.length; i++) {
                            html += '<option value="' + res.data[i].id + '"';
                            html += res.data[i].id == result.roleId ? ' selected="true"' : '';
                            html += '>' + res.data[i].roleName + '</option>';
                        }
                    } else {
                        html = html + '<option value="">暂无角色</option>';
                    }
                    $("#editRoleList").html(html);
                }
            });
        }
    });
    $('#edit_dialog').modal('show');
}

//管理员编辑保存事件
function editSaveBtn() {
    if (!$("#editForm").valid()) {
        return;
    }
    var roleAndManager = {
        userName: $("#editForm input[name='username']").val(),
        roleId: $("#editForm #editRoleList").val(),
        isUse: $("#editForm #editIsUse").val(),
        managerId: $("#managerIdHidden").val()
    };
    LSFetch({
        url: basePath + '/editmanager.htm',
        data: JSON.stringify(roleAndManager),
        contentType: "application/json;charset=utf-8",
        success: function (data) {
            if (data === 1) {
                $('#edit_dialog').modal('hide');
                refreshDataTable();
            } else if (data === -1) {
                showerror("用户名已存在");
            } else {
                showerror("编辑失败");
            }
        }
    });
}

//删除单个管理员
function deleteManager(id) {
    $("#deleteId").val(id);
    $('#del_dialog').modal('show');
}

//删除单个管理员 保存事件
function deleteManagerSaveBtn() {
    LSFetch({
        url: basePath + '/deletemanager.htm',
        data: {
            managerIds: $("#deleteId").val()
        },
        success: function (data) {
            if (data === -1) {
                showerror("请至少选择一条记录");
                return;
            }
            $('#del_dialog').modal('hide');
            refreshDataTable();
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
    showdeleteconfirm("确定要删除选择的管理员?", ids);
}

// 批量删除
function deleteAll(ids) {
    LSFetch({
        url: basePath + '/deletemanager.htm',
        data: {
            managerIds: ids
        },
        success: function (data) {
            if (data === -1) {
                showerror("请至少选择一条记录");
                return;
            }
            $('#confirm').modal('hide');
            refreshDataTable();
        }
    });
}
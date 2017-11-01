// 将新增的弹出层放入内存中
var addhtml = $("#add_dialog").html();

//新增时候防止重复提交
var typeacount = 0;

// 修改时候防止重复提交
var typeucount = 0;

function clearCount() {
    typeacount = 0;
    typeucount = 0;
}

// 基本路径
var basePath = $("#basePath").val();

var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/queryaffairstypes.htm',
                data: dataTableAjaxData(data),
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

            {
                "name": "name", "data": "name"
            },

            {
                "name": "sort", "data": "sort"
            },

            {
                "data": function (row) {
                    var operator = '<div class="operation_box"><button type="button" class="btn btn-primary btn-xs edit_btn" onclick="toupdate(' + row.id + ')" ><i class="icon-pencil"></i> 修改</button>';
                    operator += '<button  type="button"  class="btn btn-danger btn-xs del_btn" onclick="todelete(' + row.id + ')"><i class="icon-trash"></i> 删除</button> </div>';
                    return operator;
                }
            }
        ],
        ordering: false
    })
};

// 初始化列表
initDataTable();

// 刷新列表
var refreshDataTable = function () {
    $('#dataTable').DataTable().ajax.reload();
};

// 新增事务咨询类型
function saveAffairsType() {
    if (typeacount == 1) {
        return;
    }
    if (!$("#addAffairsType").valid()) {
        return;
    }
    var affairsType = {
        name: $("#addTypeName").val(),
        sort: $("#addTypeSort").val()
    };
    typeacount = 1;
    LSFetch({
        url: basePath + '/addaffairstype.htm',
        data: JSON.stringify(affairsType),
        contentType: "application/json;charset=utf-8",
        success: function (res) {
            typeacount = 0;
            if (res == 1) {
                $('#add_dialog').modal('hide');
                refreshDataTable();
            } else if (res == 0) {
                $('#add_dialog').modal('hide');
                showerror('该事务咨询类型已存在');
            }
        }
    })
}

// 跳转到删除事务咨询类型页面
function todelete(id) {
    $("#deleteId").val(id);
    $('#del_dialog').modal('show');
}

// 删除事务咨询类型
function deleteAffairsType() {
    LSFetch({
        url: basePath + '/deleteaffairstype.htm?id=' + $("#deleteId").val(),
        success: function () {
            $('#del_dialog').modal('hide');
            refreshDataTable();
        }
    })
}

// 弹出修改事务咨询类型页面
function toupdate(id) {
    clearCount();
    LSFetch({
        url: basePath + '/queryaffairstypebyid.htm?id=' + id,
        success: function (res) {
            $("#affairsTypeId").val(res.id);
            $("#affairsTypeName").val(res.name);
            $("#affairsTypeSort").val(res.sort);
            $('#edit_dialog').modal('show');
        }
    })
}

// 修改事务咨询类型
function update() {
    if (typeucount == 1) {
        return;
    }
    if (!$("#updateAffairsType").valid()) {
        return;
    }
    var affairsType = {
        id: $("#affairsTypeId").val(),
        name: $("#affairsTypeName").val(),
        sort: $("#affairsTypeSort").val(),
    };
    typeucount = 1;
    LSFetch({
        url: basePath + '/updateaffairstype.htm',
        data: JSON.stringify(affairsType),
        contentType: "application/json;charset=utf-8",
        success: function () {
            $('#edit_dialog').modal('hide');
            refreshDataTable();
        }
    });
}

// 准备删除所有已选中的事务咨询类型
function toDeleteAll() {
    // 选中的id
    var ids = getSelectIds("id");
    if (ids.length == 0) {
        showerror('请至少选择一条记录!');
        return;
    }
    showdeleteconfirm("确定要删除选择的事务咨询类型?", ids);
}

// 删除已选中的事务咨询类型
function deleteAll(ids) {
    LSFetch({
        url: basePath + '/batchdeleteaffairstype.htm',
        data: {
            ids: ids
        },
        success: function (data) {
            if(data >= 1){
                $('#confirm').modal('hide');
                refreshDataTable();
            }
        }
    });
}
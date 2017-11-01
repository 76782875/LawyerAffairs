// 将新增的弹出层放入内存中
var addhtml = $("#add_dialog").html();

//新增时候防止重复提交
var typeacount = 0;

// 修改时候防止重复提交
var templateucount = 0;

function clearCount() {
    typeacount = 0;
    templateucount = 0;
}

// 基本路径
var basePath = $("#basePath").val();

var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/querytemplatelist.htm',
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

            {
                "name": "name", "data": "name"
            },
            {
                "typename": "name", "data": function (row) {
                return row.templateType.name
            }
            },

            {
                "data": function (row) {
                    var operator = '<div class="operation_box"><button type="button" class="btn btn-primary btn-xs" onclick="toupdate(' + row.id + ')" ><i class="icon-pencil"></i> 修改</button>';
                    operator += '<button  type="button"  class="btn btn-danger btn-xs" onclick="todelete(' + row.id + ')"><i class="icon-trash"></i> 删除</button> </div>';
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

// 跳转到删除合同模板页面
function todelete(id) {
    $("#deleteId").val(id);
    $('#del_dialog').modal('show');
}

// 删除合同模板
function deleteTemplate() {
    LSFetch({
        url: basePath + '/deletetemplate.htm?id=' + $("#deleteId").val(),
        success: function () {
            $('#del_dialog').modal('hide');
            refreshDataTable();
        }
    })
}

// 弹出修改合同模板页面
function toupdate(id) {
    clearCount();
    LSFetch({
        url: basePath + '/querytemplatebyid.htm?id=' + id,
        success: function (data) {
            $("#templateId").val(data.id);
            $("#templateName").val(data.name);
            LSFetch({
                url: basePath + '/querytemplatetype.htm', //查询所有合同模板类型
                success: function (res) {
                    var option = '';
                    if (res.length > 0) {
                        for (var i = 0; i < res.length; i++) {
                            option += '<option value="' + res[i].id + '"';
                            if (data.typeId == res[i].id) {
                                option += ' selected';
                                if (res[i].grade == 1) {
                                    option += '>' + res[i].name + '</option>';
                                } else {
                                    for (var j = 0; j < res.length; j++) {
                                        if (i > 0 && res[i].grade == 2 && res[i].parentId == res[j].id) {
                                            option += '>&nbsp;&nbsp;&nbsp;&nbsp;' + res[i].name + '</option>';
                                        } else if (i > 0 && res[i].grade == 3 && res[i].parentId == res[j].id) {
                                            option += '>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + res[i].name + '</option>';
                                        }
                                    }
                                }
                            } else {
                                if (res[i].grade == 1) {
                                    option += '>' + res[i].name + '</option>';
                                } else {
                                    for (var j = 0; j < res.length; j++) {
                                        if (i > 0 && res[i].grade == 2 && res[i].parentId == res[j].id) {
                                            option += '>&nbsp;&nbsp;&nbsp;&nbsp;' + res[i].name + '</option>';
                                        } else if (i > 0 && res[i].grade == 3 && res[i].parentId == res[j].id) {
                                            option += '>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + res[i].name + '</option>';
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        option += '<option value="' + 0 + '">暂无模板类型</option>';
                    }
                    $("#templateType").html(option);
                }
            });
        }
    });
    $('#edit_dialog').modal('show');
}

// 修改合同模板
function update() {
    if (templateucount == 1) {
        return;
    }
    if (!$("#updateTemplateForm").valid()) {
        return;
    }
    var template = {
        id: $("#templateId").val(),
        name: $("#templateName").val(),
        typeId: $("#templateType").val()
    };
    templateucount = 1;
    LSFetch({
        url: basePath + '/updatetemplate.htm',
        data: JSON.stringify(template),
        contentType: "application/json;charset=utf-8",
        success: function () {
            $('#edit_dialog').modal('hide');
            refreshDataTable();
        }
    });
}

// 准备删除所有已选中的合同模板
function toDeleteAll() {
    // 选中的id
    var ids = getSelectIds("id");
    if (ids.length == 0) {
        showerror('请至少选择一条记录!');
        return;
    }
    showdeleteconfirm("确定要删除选择的合同模板?", ids);
}

// 删除已选中的合同模板
function deleteAll(ids) {
    LSFetch({
        url: basePath + '/batchdeletetemplate.htm',
        data: {
            ids: ids
        },
        success: function (data) {
            if (data >= 1) {
                $('#confirm').modal('hide');
                refreshDataTable();
            }
        }
    });
}

//添加模板点击事件
function addTemplate() {
    LSFetch({
        url: basePath + '/querytemplatetype.htm', //查询所有合同模板类型
        success: function (res) {
            var option = '';
            if (res.length > 0) {
                for (var i = 0; i < res.length; i++) {
                    if (res[i].grade == 1) {
                        option += '<option value="' + res[i].id + '">' + res[i].name + '</option>';
                    } else {
                        for (var j = 0; j < res.length; j++) {
                            if (i > 0 && res[i].grade == 2 && res[i].parentId == res[j].id) {
                                option += '<option value="' + res[i].id + '">&nbsp;&nbsp;&nbsp;&nbsp;' + res[i].name + '</option>';
                            } else if (i > 0 && res[i].grade == 3 && res[i].parentId == res[j].id) {
                                option += '<option value="' + res[i].id + '">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + res[i].name + '</option>';
                            }
                        }
                    }
                }
            } else {
                option += '<option value="' + 0 + '">暂无模板类型</option>';
            }
            $("select[name='typeId']").html(option);
        }
    });
    $('#add_dialog').html(addhtml).modal('show');
}

//添加模板保存事件
function toSubmit() {
    if (!$("#fileForm").valid()) {
        return;
    }
    if ($("select[name='typeId']").val() == 0) {
        showerror("请先添加模板类型");
        return;
    }
    if ($("input[type='file']").val() == "" || $("input[type='file']").val() == null) {
        showerror("请先上传文件");
        return;
    }
    $("#fileForm").submit();
    $('iframe').on('load', function () {
        var responseText = $('iframe')[0].contentDocument.body.textContent;
        var responseData = JSON.parse(responseText) || {};
        if (responseData == 1) {
            $('#add_dialog').modal('hide');
            showerror("上传成功");
            refreshDataTable();
            return;
        }
        if (responseData == 2) {
            showerror("该合同模板已存在");
            return;
        }
        showerror("添加失败");
    });
}

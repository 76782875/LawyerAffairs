// 基本路径
var basePath = $("#basePath").val();
var addHtml = $('#add_dialog').html();
var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/querylawyerlist.htm',
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
                "name": "mobile", "data": "mobile"
            },
            {
                "name": "code", "data": "code"
            },
            {
                "name": "lawyersPlace", "data": "lawyersPlace"
            },
            {
                "data": function (row) {
                    if (row.status == '0' || row.status == '1') {
                        if (row.status == 0) {
                            return '<span class="label label-success">已通过</span>&nbsp;&nbsp;&nbsp;&nbsp;<span class="label label-success">启用</span>';
                        } else {
                            return '<span class="label label-success">已通过</span>&nbsp;&nbsp;&nbsp;&nbsp;<span class="label label-warning">禁用</span>';
                        }

                    } else if (row.status == '2') {
                        return '<span class="label label-default">待审核</span>';
                    } else if (row.status == '3') {
                        return '<span class="label label-danger">已拒绝</span>';
                    }
                }
            },
            {
                "data": function (row) {
                    if (row.type == '1') {
                        return '<span class="label label-success">平台律师</span>';
                    } else if (row.type == '2') {
                        return '<span class="label label-default">非平台律师</span>';
                    }
                }
            },
            {
                "data": function (row) {
                    if (row.searchForbid == 0) {
                        return '<span class="label label-success">启用</span>';
                    } else if (row.searchForbid == 1) {
                        return '<span class="label label-default">禁用</span>';
                    }
                }
            },
            {
                "data": function (row) {
                    var operator = '<div class="operation_box"><button type="button" class="btn btn-primary btn-xs" onclick="showDetail(' + row.id + ')" ><i class="icon-eye-open"></i> 查看</button>';
                    if (row.status == "2") {
                        operator += '<button type="button" class="btn btn-warning btn-xs review_btn" onclick="toAudit(' + row.id + ')"><i class="icon-cog"></i> 审核</button>';
                    } else if (row.status != "3") {
                        operator += '<button type="button" class="btn btn-warning btn-xs" onclick="toupdate(' + row.id + ')" ><i class="icon-pencil"></i> 编辑</button>';
                    }
                    operator += '<button type="button" class="btn btn-danger btn-xs" onclick="todelete(' + row.id + ')"><i class="icon-trash"></i> 删除</button></div>';
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

// 弹出审核对话框
function toAudit(id) {
    $("#auditId").val(id);
    $("#aud_dialog").modal("show");
}

// 保存审核
function auditSave() {
    var lawyer = {
        id: $("#auditId").val(),
        status: $("#auditStatus").val()
    };
    LSFetch({
        url: basePath + '/auditlawyer.htm',
        data: JSON.stringify(lawyer),
        contentType: "application/json;charset=utf-8",
        success: function (res) {
            if (res == 1) {
                $("#aud_dialog").modal("hide");
                refreshDataTable();
            }
        }
    });
}

// 弹出删除确认窗口
function todelete(id) {
    $("#deleteId").val(id);
    $("#del_dialog").modal("show");
}

// 删除律师会员
function deleteLawyer() {
    LSFetch({
        url: basePath + '/deletelawyer.htm',
        data: {
            id: $("#deleteId").val()
        },
        success: function (data) {
            if (data == 1) {
                $('#del_dialog').modal('hide');
                showerror("删除成功");
                refreshDataTable();
            } else {
                showerror("删除失败");
            }

        }
    })
}

// 批量删除律师会员
function toDeleteAll() {
    // 选中的id
    var ids = getSelectIds("id");
    if (ids.length == 0) {
        showerror('请至少选择一条记录!');
        return;
    }
    showdeleteconfirm("确定要删除选择的律师会员?", ids);
}

function deleteAll(ids) {
    LSFetch({
        url: basePath + '/batchdeletelawyer.htm',
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

function selectStatus() {
    $("input[name='status']").val($("select[name='status']").val());
    // 初始化列表
    refreshDataTable();
}

function addLawyer() {
    $('#add_dialog').html(addHtml).modal('show');
}

// 添加律师会员
function addLawyerSave() {
    if (!$("#addFormOne").valid() | !$("#addFormTwo").valid()) {
        return;
    }
    var lawyer = {
        mobile: $("#lawMobile").val(),
        name: $("#lawName").val(),
        code: $("#lawCode").val(),
        lawyerPic: $("input[data-type='addCodePic']").val(),
        address: $("#lawAddress").val(),
        lawyersPlace: $("#lawPlace").val(),
        status: $("#lawStatus").val(),
        type: $("#lawType").val(),
        searchForbid: $("#searchForbid").val(),
        qq: $("#lawQQ").val(),
        email: $("#lawEmail").val(),
        pic: $("input[data-type='addHeadPic']").val()
    };
    LSFetch({
        url: basePath + '/addlawyer.htm',
        data: JSON.stringify(lawyer),
        contentType: "application/json;charset=utf-8",
        async: false,
        success: function (data) {
            if (data === 1) {
                $("#add_dialog").modal("hide");
                refreshDataTable();
            } else if (data === -1) {
                showerror("手机号码已注册");
            }
        }
    });
}

// 显示
function showDetail(id) {
    LSFetch({
        url: basePath + '/querylawyerbyid.htm?id=' + id,
        success: function (res) {
            $('#show_dialog input[name="lawyerName"]').val(res.name);
            $('#show_dialog input[name="lawyerCode"]').val(res.code);
            $('#show_dialog img[name="LawyerPicture"]').attr("src", res.lawyerPic);
            $('#show_dialog input[name="lawyerAddress"]').val(res.address);
            $('#show_dialog input[name="lawyersPlace"]').val(res.lawyersPlace);
            $('#show_dialog select[name="lawyerStatus"]').val(res.status);
            $('#show_dialog select[name="lawyerType"]').val(res.type);
            $('#show_dialog select[name="searchForbid"]').val(res.searchForbid);
            $('#show_dialog input[name="lawyerQQ"]').val(res.qq);
            $('#show_dialog input[name="lawyerEmail"]').val(res.email);
            $('#show_dialog img[name="lawyerHeadPic"]').attr("src", res.pic);
        }
    });
    $('#show_dialog').modal('show');
}

// 弹出修改律师会员窗口
function toupdate(id) {
    LSFetch({
        url: basePath + '/querylawyerbyid.htm?id=' + id,
        success: function (res) {
            $("#lawyerId").val(res.id);
            $('#edit_dialog input[name="lawyerName"]').val(res.name);
            $('#edit_dialog input[name="lawyerCode"]').val(res.code);
            $('#edit_dialog img[name="LawyerPicture"]').attr("src", res.lawyerPic);
            $('#edit_dialog input[data-type="editCodePic"]').val(res.lawyerPic);
            $('#edit_dialog input[name="lawyerAddress"]').val(res.address);
            $('#edit_dialog input[name="lawyersPlace"]').val(res.lawyersPlace);
            $('#edit_dialog select[name="lawyerStatus"]').val(res.status);
            $('#edit_dialog select[name="lawyerType"]').val(res.type);
            $('#edit_dialog select[name="searchForbid"]').val(res.searchForbid);
            $('#edit_dialog input[name="lawyerQQ"]').val(res.qq);
            $('#edit_dialog input[name="lawyerEmail"]').val(res.email);
            $('#edit_dialog img[name="lawyerHeadPic"]').attr("src", res.pic);
            $('#edit_dialog input[data-type="editHeadPic"]').val(res.pic);
        }
    });
    $('#edit_dialog').modal('show');
}

// 修改律师
function updateLawyer() {
    if (!$("#editForm1").valid() | !$("#editForm2").valid()) {
        return;
    }
    var lawyer = {
        id: $("#lawyerId").val(),
        name: $('#edit_dialog input[name="lawyerName"]').val(),
        code: $('#edit_dialog input[name="lawyerCode"]').val(),
        lawyerPic: $('#edit_dialog input[data-type="editCodePic"]').val(),
        address: $('#edit_dialog input[name="lawyerAddress"]').val(),
        lawyersPlace: $('#edit_dialog input[name="lawyersPlace"]').val(),
        status: $('#edit_dialog select[name="lawyerStatus"]').val(),
        type: $('#edit_dialog select[name="lawyerType"]').val(),
        searchForbid: $('#edit_dialog select[name="searchForbid"]').val(),
        qq: $('#edit_dialog input[name="lawyerQQ"]').val(),
        email: $('#edit_dialog input[name="lawyerEmail"]').val(),
        pic: $('#edit_dialog input[data-type="editHeadPic"]').val()
    };
    LSFetch({
        url: basePath + '/updatelawyer.htm',
        data: JSON.stringify(lawyer),
        contentType: "application/json;charset=utf-8",
        async: false,
        success: function () {
            $('#edit_dialog').modal('hide');
            refreshDataTable();
        }
    });
}

// 上传律师证、律师头像
function uploadPic(obj) {
    $.ajax({
        url: basePath + 'uploadtoupyun.htm',
        type: 'POST',
        cache: false,
        data: new FormData($('#' + obj)[0]),
        processData: false,
        contentType: false,
        async: false
    }).done(function (res) {
        $("input[data-type=" + "'" + obj + "'" + " ]").val(res);
    }).fail(function (res) {
    });
}
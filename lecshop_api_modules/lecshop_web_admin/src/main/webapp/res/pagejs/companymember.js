// 基本路径
var basePath = $("#basePath").val();
//将新增的弹出层放入内存中
var addHtml = $('#add_dialog').html();
//将站内信弹出层放入内存中
var smsHtml = $("#sms_dialog").html();

var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/querycompanymemberinfos.htm',
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
                "data": function (row) {
                    return row.companyInfo.name;
                }
            },
            {
                "data": function (row) {
                    if (row.status === '0') {
                        return '<span class="label label-success">启用</span>';
                    } else if (row.status === '1') {
                        return '<span class="label label-default">禁用</span>';
                    }
                }
            },

            {
                "data": function (row) {
                    var operator = '<div class="operation_box"><button type="button" class="btn btn-primary btn-xs" onclick="showDetail(' + row.companyInfo.id + "," + row.id + ')" ><i class="icon-eye-open"></i> 查看</button>';
                    operator += '<button type="button" class="btn btn-primary btn-xs" onclick="editVip(' + row.companyInfo.id + ')" ><i class="icon-pencil"></i> VIP等级</button>';
                    operator += '<button type="button" class="btn btn-primary btn-xs" onclick="toupdate(' + row.companyInfo.id + "," + row.id + ')" ><i class="icon-pencil"></i> 编辑</button>';
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

// 弹出删除确认窗口
function todelete(id) {
    $("#deleteId").val(id);
    $("#del_dialog").modal("show");
}

// 删除公司会员
function deleteCompanyMember() {
    LSFetch({
        url: basePath + '/deletecompanymember.htm',
        data: {
            id: $("#deleteId").val()
        },
        success: function (data) {
            if (data === 1) {
                $('#del_dialog').modal('hide');
                showerror("删除成功");
                refreshDataTable();
            } else {
                showerror("删除失败");
            }

        }
    });
}

// 批量删除公司会员
function toDeleteAll() {
    // 选中的id
    var ids = getSelectIds("id");
    if (ids.length == 0) {
        showerror('请至少选择一条记录!');
        return;
    }
    showdeleteconfirm("确定要删除选择的公司会员?", ids);
}

function deleteAll(ids) {
    LSFetch({
        url: basePath + '/batchdeletecompanymember.htm',
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

// 显示明细
function showDetail(companyId, userId) {
    LSFetch({
        url: basePath + '/querycompanyinfobyid.htm',
        data: {
            companyId: companyId,
            userId: userId
        },
        success: function (res) {
            $('.nickname').val(res.name);
            $(".status").val(res.status);
            $(".companyname").val(res.companyInfo.name);
            $(".code").val(res.companyInfo.code);
            $(".industry").val(res.companyInfo.industry);
            $(".address").val(res.companyInfo.address);
            $(".fax").val(res.companyInfo.fax);
            $(".businessLicence img").attr('src', res.companyInfo.businessLicence);
        }
    });
    $('#show_dialog').modal('show');
}

function adduser() {
    $('#add_dialog').html(addHtml).modal('show');
}

// 上传营业执照图片
function uploadPic(obj) {
    $.ajax({
        url: basePath + 'uploadtoupyun.htm',
        type: 'POST',
        cache: false,
        data: new FormData($("#" + obj)[0]),
        processData: false,
        contentType: false,
        async: false
    }).done(function (res) {
        if (obj === 'updateBusinessLicence') {
            $("#editLicenceImg").attr("src", res);
        } else if (obj === 'addForm') {
            $("#addLicenceImg").attr("src", res);
        }
    }).fail(function (res) {
    });
}

// 添加公司会员
function addCompany() {
    if (!$("#addForm").valid()) {
        return;
    }
    var user = {
        mobile: $("#addMobile").val(),
        password: $("#addNickName").val(),
        status: $("#addStatus").val()
    };
    var companyInfo = {
        name: $("#addName").val(),
        code: $("#addCode").val(),
        industry: $("#addIndustry").val(),
        address: $("#addAddress").val(),
        fax: $("#addFax").val(),
        businessLicence: $("#addLicenceImg").attr("src"),
        user: user
    };
    LSFetch({
        url: basePath + '/addcompanymember.htm',
        data: JSON.stringify(companyInfo),
        contentType: "application/json;charset=utf-8",
        success: function (data) {
            if (data === -1) {
                showerror("手机号码已存在");
                return;
            }
            if (data === -2) {
                showerror("公司名称已存在");
                return;
            }
            if (data === 1) {
                $("#add_dialog").modal("hide");
                refreshDataTable();
            }
        }
    });
}

// 弹出修改公司会员窗口
function toupdate(companyId, userId) {
    $("#userIdHidden").val(userId);
    LSFetch({
        url: basePath + '/querycompanyinfobyid.htm',
        data: {
            companyId: companyId,
            userId: userId
        },
        success: function (res) {
            $("#companyMemberId").val(res.companyId);
            $('.nickname').val(res.name);
            $(".status").val(res.status);
            $(".companyname").val(res.companyInfo.name);
            $(".code").val(res.companyInfo.code);
            $(".industry").val(res.companyInfo.industry);
            $(".address").val(res.companyInfo.address);
            $(".fax").val(res.companyInfo.fax);
            $(".businessLicence img").attr('src', res.companyInfo.businessLicence);
        }
    });
    $('#edit_dialog').modal('show');
}

// 修改公司会员
function updateCompanyMember() {
    if (!$("#updateBusinessLicence").valid()) {
        return;
    }
    uploadPic('updateBusinessLicence');
    var user = {
        id: $("#userIdHidden").val(),
        name: $("#userNickName").val(),
        status: $("#userStatus").val()
    };
    var companyInfo = {
        id: $("#companyMemberId").val(),
        name: $("#editCompanyMemberName").val(),
        code: $("#editCompanyMemberCode").val(),
        industry: $("#editCompanyMemberIndustry").val(),
        address: $("#editCompanyMemberAddress").val(),
        fax: $("#editCompanyMemberFax").val(),
        businessLicence: $("#editLicenceImg").attr("src"),
        user: user
    };
    LSFetch({
        url: basePath + '/updatecompanymember.htm',
        data: JSON.stringify(companyInfo),
        contentType: "application/json;charset=utf-8",
        async: false,
        success: function () {
            $('#edit_dialog').modal('hide');
            refreshDataTable();
        }
    });
}

// 弹出站内信窗口
function showLetterStation() {
    // 选中的id
    var ids = getSelectIds("id");
    if (ids.length == 0) {
        showerror('请至少选择一条记录!');
        return;
    }
    $("#sms_dialog").html(smsHtml);
    $("#ids").val(ids);
    $("#sms_dialog").modal("show");
}

// 发送站内信
function sendLetterStation() {
    if (!$("#letterStationForm").valid()) {
        return;
    }
    LSFetch({
        url: basePath + '/sendletterstation.htm',
        data: {
            ids: $("#ids").val(),
            title: $("#title").val(),
            content: $("#content").val()
        },
        success: function (data) {
            if (data >= 1) {
                $('#sms_dialog').modal('hide');
                refreshDataTable();
            }
        }
    });
}

function editVip(id) {
    $("input[name='companyId']").val(id);
    $("textarea[name='remark']").val("");
    $("#vip_dialog").modal("show");
    LSFetch({
        url: basePath + '/querycompanyinfobycompanyid.htm',
        data: {
            companyId: id
        },
        success: function (data) {
            $("select[name='vipTyp']").val(data.vipType);
        }
    });
}

function editVipSave() {
    LSFetch({
        url: basePath + '/editcompanyinfovip.htm',
        data: {
            companyId: $("input[name='companyId']").val(),
            newVip: $("select[name='vipTyp']").val(),
            remark: $("textarea[name='remark']").val()
        },
        success: function (data) {
            if (data === 1) {
                $("#vip_dialog").modal("hide");
                showerror("编辑成功");
            }
        }
    });
}
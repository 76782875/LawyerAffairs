// 基本路径
var basePath = $("#basePath").val();

var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/querywithdraws.htm',
                data: dataTableAjaxData(data, $("#queryform").serializeArray()),
                success: function (res) {
                    callback(res);
                }
            });
        },
        "columns": [

            {
                "name": "tradeno", "data": "tradeNo"
            },

            {
                "name": "mobile", "data": "lawyer.mobile"
            },

            {
                "name": "name", "data": "lawyer.name"
            },
            {
                "name": "money", "data": "money"
            },
            {
                "name": "account", "data": "withdrawSet.account"
            },
            {
                "name": "accountname", "data": "withdrawSet.name"
            },

            {
                "name": "status", "data": function (row) {
                if (row.status == '0') {
                    return '<span class="label label-success" style="cursor: default;">申请中</span>';
                } else if (row.status == '1') {
                    return '<span class="label label-success" style="cursor: default;">审核通过</span>';
                } else if (row.status == '3') {
                    return '<span class="label label-success" style="cursor: default;">提现完成</span>';
                } else if (row.status == '2') {
                    return '<span class="label label-success" style="cursor: default;">审核不通过</span>';
                } else {
                    return '';
                }
            }
            },

            {
                "name": "createTime", "data": "createTime"
            },

            {
                "name": "status", "data": function (row) {
                if (row.status == '0') {
                    var operator = '<div class="operation_box"><button type="button" class="btn btn-primary btn-xs pass_btn" onclick="passReady(' + row.id + ')">' +
                        '<i class="icon-ok"></i> 通过</button> ';
                    operator += '<button type="button" class="btn btn-danger btn-xs refuse_btn" onclick="refuseReady(' + row.id + ')">'
                        + '<i class="icon-minus-sign"></i> 拒绝 </button></div>';
                    return operator;

                } else if (row.status == '1') {
                    var opr = '<div class="operation_box"><button class="btn btn-success btn-xs view_btn" type="button" onclick="tograntMoney(' + row.id + ')">' +
                        '<i class="icon-eye-open"></i> 发放 </button> ';
                    opr += '&nbsp<button class="btn btn-success btn-xs view_btn" type="button" onclick="rejectReady(' + row.id + ')">' +
                        '<i class="icon-eye-open"></i> 驳回 </button></div>';
                    return opr;
                } else {
                    return '';
                }
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

// 弹出拒绝申请对话框
function refuseReady(id) {
    $("#refuseIdHidden").val(id);
    $("#refuse_dialog").modal("show");
}

// 拒绝申请
function refuse() {
    LSFetch({
        url: basePath + '/refuseapply.htm',
        data: {id: $("#refuseIdHidden").val()},
        success: function () {
            $('#refuse_dialog').modal('hide');
            refreshDataTable();
        }
    })
}

// 发放提现
function tograntMoney(id) {
    $("#withdraw_id").val(id);
    $("#withdraw_dialog").modal("show");
}

// 公共返回
var commonError = {
    "10000": "提现成功",
    "20000": "服务不可用",
    "20001": "授权权限不足",
    "40001": "缺少必选参数",
    "40002": "非法的参数",
    "40006": "权限不足",
    "-10001": "系统繁忙"
};

var businessError = {
    "INVALID_PARAMETER": "参数有误",
    "SYSTEM_ERROR": "系统繁忙",
    "EXCEED_LIMIT_SM_AMOUNT": "单笔转账给个人支付宝账号最多5万元，转账给企业支付宝账号最多10万元，单笔最低转账金额0.1元",
    "EXCEED_LIMIT_DM_AMOUNT": "默认付款方转账单日最多可转100万元；或非实名收款方单日最多可收款1000元",
    "PERMIT_CHECK_PERM_LIMITED": "根据监管部门的要求，请补全您的身份信息解除限制",
    "PAYCARD_UNABLE_PAYMENT": "付款账户余额支付功能不可用",
    "PAYEE_NOT_EXIST": "收款账号不存在",
    "PAYER_DATA_INCOMPLETE": "根据监管部门的要求，需要付款用户补充身份信息才能继续操作",
    "PERM_AML_NOT_REALNAME_REV": "根据监管部门的要求，需要收款用户补充身份信息才能继续操作",
    "PAYER_STATUS_ERROR": "付款账号状态异常",
    "PAYEE_USER_INFO_ERROR": "支付宝账号和姓名不匹配，请确认姓名是否正确",
    "PAYER_BALANCE_NOT_ENOUGH": "付款方余额不足",
    "CERT_MISS_TRANS_LIMIT": "您的付款金额已达单笔1万元或月累计5万元，根据监管部门的要求，需要付款用户补充身份信息才能继续操作",
    "CERT_MISS_ACC_LIMIT": "您连续10天余额账户的资金都超过5000元，根据监管部门的要求，需要付款用户补充身份信息才能继续操作",
    "PAYEE_ACC_OCUPIED": "该手机号对应多个支付宝账户，请传入收款方姓名确定正确的收款账号",
    "MEMO_REQUIRED_IN_TRANSFER_ERROR": "根据监管部门的要求，单笔转账金额达到50000元时，需要填写付款理由",
    "PERMIT_NON_BANK_LIMIT_PAYEE": "根据监管部门的要求，对方未完善身份信息，无法收款",
    "PERMIT_PAYER_LOWEST_FORBIDDEN": "根据监管部门要求，付款方身份信息完整程度较低，余额支付额度受限",
    "PERMIT_PAYER_FORBIDDE": "根据监管部门要求，付款方余额支付额度受限",
    "PERMIT_CHECK_PERM_IDENTITY_THEFT": "您的账户存在身份冒用风险，请进行身份核实解除限制",
    "REMARK_HAS_SENSITIVE_WORD": "转账备注包含敏感词，请修改备注文案后重试",
    "ACCOUNT_NOT_EXIST": "根据监管部门的要求，请补全你的身份信息，开立余额账户",
    "PAYER_CERT_EXPIRED": "根据监管部门的要求，需要付款用户更新身份信息才能继续操作"
}

var count = 0;

// 提现
function grantMoney() {

    if (count == 1) {
        return;
    }

    count++;

    LSFetch({
        url: basePath + '/releasemoney.htm',
        data: {id: $("#withdraw_id").val()},
        success: function (res) {
            $('#withdraw_dialog').modal('hide');

            var msg = "";

            if (res.code != "40004") {
                // 公共返回
                msg = commonError[res.code];
            } else {
                // 业务返回
                msg = businessError[res.subCode];
            }

            $("#withdraw_result").html(msg);
            $('#result_dialog').modal('show');
        }
    })
}

// 关闭提示
function resultClose() {
    count = 0;
    $('#result_dialog').modal('hide');
    refreshDataTable();
}

// 准备驳回提现申请
function rejectReady(id) {
    $("#rejectIdHidden").val(id);
    $("#reject_dialog").modal("show");
}

// 驳回提现申请
function reject() {

    LSFetch({
        url: basePath + '/refuseapply.htm',
        data: {id: $("#rejectIdHidden").val()},
        success: function () {
            $('#reject_dialog').modal('hide');
            refreshDataTable();
        }
    })
}

// 准备通过提现申请
function passReady(id) {
    $("#passIdHidden").val(id);
    $("#pass_dialog").modal("show");
}

// 通过提现申请
function pass() {
    $("#pass_dialog").modal("hide");
    LSFetch({
        url: basePath + '/passapply.htm',
        data: {id: $("#passIdHidden").val()},
        success: function () {
            $('#refuse_dialog').modal('hide');
            refreshDataTable();
        }
    })
}

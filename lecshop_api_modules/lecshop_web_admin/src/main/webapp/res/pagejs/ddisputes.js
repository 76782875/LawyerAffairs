// 基本路径
var basePath = $("#basePath").val();
// 初始化列表
var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/querydisputes.htm',
                data: dataTableAjaxData(data, $("#queryform").serializeArray()),
                success: function (res) {
                    callback(res);
                }
            });
        },
        "columns": [
            {"name": "companyInfo.name", "data": "companyInfo.name"},
            {"name": "user.name", "data": "user.name"},
            {"name": "lawyer.name", "data": "lawyer.name"},
            {
                "data": function (row) {
                    var operator = '';
                    if (row.status === "0") {
                        operator = '<span class="label label-danger">待律师接单</span>'
                    }
                    if (row.status === "1") {
                        operator = '<span class="label label-warning">律师已拒绝</span>';
                    }
                    if (row.status === "2") {
                        operator = '<span class="label label-primary">待用户付费</span>';
                    }
                    if (row.status === "3") {
                        operator = '<span class="label label-info">事务进行中</span>';
                    }
                    if (row.status === "4") {
                        operator = '<span class="label label-default">待用户确认</span>';
                    }
                    if (row.status === "5") {
                        operator = '<span class="label label-success">事务已完成</span>';
                    }
                    return operator;
                }
            },
            {"name": "price", "data": "price"},
            {"name": "desc", "data": "desc"},
            {"name": "consultType", "data": "consultType"},
            {
                "name": "score", "data": function (row) {
                if (row.score === 0) {
                    return "未评价";
                } else {
                    return row.score;
                }
            }
            },
            {"name": "createTime", "data": "createTime"},
            {
                "data": function (row) {
                    var htmls = '<div class="operation_box">';
                    if (row.status === "3") {
                        htmls += '<button class="btn btn-success btn-xs" onclick="toDisputesDetail(' + row.id + ')"><i class="icon-eye-open"></i>查看</button>';
                    }
                    if (row.status === "4") {
                        htmls += '<button class="btn btn-primary btn-xs" onclick="toConfirmDisputes(' + row.id + ')"><i class="icon-credit-card"></i>确认</button>';
                    }
                    return htmls + '</div>';
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

function toConfirmDisputes(id) {
    $("#confirmDisputeId").val(id);
    $("#confirm_dialog").modal("show");
}

function confirmDisputes() {
    LSFetch({
        url: basePath + 'confirmdisputes.htm?id=' + $("#confirmDisputeId").val(),
        success: function (res) {
            if (res === 1) {
                $("#confirm_dialog").modal("hide");
                refreshDataTable();
            }
        }
    })
}

function changeTable(i) {
    if (i === 0) {
        window.location.href = "tolawyerletter.htm";
    }
    if (i === 1) {
        window.location.href = "tocompanytelconsultation.htm";
    }
    if (i === 2) {
        window.location.href = location.href;
    }
    if (i === 3) {
        window.location.href = "toimportanttelconsultation.htm";
    }
}

function changeTables(j) {
    if (j === 3) {
        window.location.href = "todisputestelconsultation.htm";
    }
    if (j === 2) {
        window.location.href = location.href;
    }
    if (j === 1) {
        window.location.href = "todisputesappointmeet.htm";
    }
}

function toDisputesDetail(id) {
    window.location.href = "todisputesdetail.htm?disputeId=" + id;
}

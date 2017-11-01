// 基本路径
var basePath = $("#basePath").val();
// 初始化列表
var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/querytelconsultation.htm?type=' + $("input[name='type']").val(),
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
                        operator += '<span class="label label-default">待用户确认</span>'
                    }
                    if (row.status === "1") {
                        operator += '<span class="label label-primary">催用户确认</span>';
                    }
                    if (row.status === "2") {
                        operator += '<span class="label label-success">事务已完成</span>';
                    }
                    return operator;
                }
            },
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
                    if (row.status === "1") {
                        htmls += '<button class="btn btn-success btn-xs" onclick=toConfirm(' + row.id + ',' + row.lawyerId + ')><i class="icon-ok"></i>确认</button>';
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

// 弹出确认电话咨询层
function toConfirm(id, lawyerId) {
    $("#telId").val(id);
    $("#lawyerId").val(lawyerId);
    $("#confirm_dialog").modal("show");
}

// 确认电话咨询
function confirm() {
    LSFetch({
        url: basePath + 'confirmtelconsultation.htm',
        data: {
            telId: $("#telId").val(),
            lawyerId: $("#lawyerId").val()
        },
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
        window.location.href = location.href;
    }
    if (i === 2) {
        window.location.href = "todisputestelconsultation.htm";
    }
    if (i === 3) {
        window.location.href = "toimportanttelconsultation.htm";
    }

}

function changeTables(j) {
    if (j === 0) {
        window.location.href = location.href;
    }
    window.location.href = "tocompanyappointmeet.htm?meetType=" + j;
}

// 基本路径
var basePath = $("#basePath").val();
// 初始化列表
var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/queryimportantmatter.htm',
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
                        operator = '<span class="label label-danger">待律师修改</span>'
                    }
                    if (row.status === "1") {
                        operator = '<span class="label label-primary">律师修改中</span>';
                    }
                    if (row.status === "2") {
                        operator = '<span class="label label-warning">待用户确认</span>';
                    }
                    if (row.status === "3") {
                        operator = '<span class="label label-success">事务已完成</span>';
                    }
                    return operator;
                }
            },
            {"name": "price", "data": "price"},
            {"name": "desc", "data": "desc"},
            {"name": "consultType", "data": "consultType"},
            {"name": "score", "data": function (row) {
                if (row.score === 0) {
                    return "未评价";
                } else {
                    return row.score;
                }
            }},
            {"name": "createTime", "data": "createTime"},
            {
                "data": function (row) {
                    htmls = '<div class="operation_box">';
                    if (row.status === "2") {
                        htmls += '<button class="btn btn-success btn-xs" onclick="toConfirmMatter(' + row.id + ')"><i class="icon-ok"></i>确认</button>';
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

function toConfirmMatter(id) {
    $("#confirmMatterId").val(id);
    $("#confirm_dialog").modal("show");
}
function confirmMatter() {
    LSFetch({
        url: basePath + 'confirmimportantmatter.htm?id=' + $("#confirmMatterId").val(),
        success: function (res) {
            if (res == 1) {
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
        window.location.href = "todisputestelconsultation.htm";
    }
    if (i === 3) {
        window.location.href = location.href;
    }
}

function changeTables(j) {
    if (j === 3) {
        window.location.href = "toimportanttelconsultation.htm";
    }
    if (j === 2) {
        window.location.href = location.href;
    }
    if (j === 1) {
        window.location.href = "toimportantappointmeet.htm";
    }
}

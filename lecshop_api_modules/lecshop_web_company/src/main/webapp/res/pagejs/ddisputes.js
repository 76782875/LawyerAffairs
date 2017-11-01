// 基本路径
var basePath = $("#basePath").val();
// 初始化列表
var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/c_querydisputes.htm',
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
                    if (row.status === "2") {
                        htmls += '<button class="btn btn-primary btn-xs" onclick="downPayments(' + row.id + ')"><i class="icon-credit-card"></i>付款</button>';
                    }
                    if (row.status === "3") {
                        htmls += '<button class="btn btn-success btn-xs" onclick="toDisputesDetail(' + row.id + ')"><i class="icon-eye-open"></i>查看</button>';
                    }
                    if (row.status === "4") {
                        htmls += '<button class="btn btn-primary btn-xs" onclick="toConfirmDoc(' + row.id + ')"><i class="icon-ok"></i>确认</button>';
                    }
                    if (row.status === "5" && row.score === 0) {
                        htmls += '<button class="btn btn-primary btn-xs" onclick="toScore(' + row.id + ')"><i class="icon-smile"></i>评分</button>';
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

function downPayments(id) {
    $("#affairPayForm input[name='id']").val(id);
    $("#affairPayForm").submit();
}

function toConfirmDoc(id) {
    $("#confirmDisputeId").val(id);
    $("#confirm_dialog").modal("show");
}

function confirmDoc() {
    LSFetch({
        url: basePath + 'c_confirmdisputes.htm?id=' + $("#confirmDisputeId").val(),
        success: function (res) {
            if (res === 1) {
                $("#confirm_dialog").modal("hide");
                refreshDataTable();
            }
        }
    });
}

function toScore(id) {
    $("#disputesId").val(id);
    $('.score_box a').siblings('a').removeClass().addClass('score_no');
    $("#score_dialog").modal("show");
}

function score() {
    LSFetch({
        url: basePath + 'c_gradefordisputes.htm',
        data: {
            id: $("#disputesId").val(),
            score: $("#score").val()
        },
        success: function (res) {
            if (res === 1) {
                $("#score_dialog").modal("hide");
                refreshDataTable();
            }
        }
    })
}

function changeTable(i) {
    if (i === 0) {
        window.location.href = "c_tolawyerletters.htm";
    }
    if (i === 1) {
        window.location.href = "c_tocompanytelconsultation.htm";
    }
    if (i === 2) {
        window.location.href = location.href;
    }
    if (i === 3) {
        window.location.href = "c_toimportanttelconsultation.htm";
    }
}

function changeTables(j) {
    if (j === 3) {
        window.location.href = "c_todisputestelconsultation.htm";
    }
    if (j === 2) {
        window.location.href = location.href;
    }
    if (j === 1) {
        window.location.href = "c_todisputesappointmeet.htm";
    }
}

$('.score_box a').click(function () {
    $(this).siblings('a').removeClass().addClass('score_no');
    $(this).removeClass().addClass('score_on');
    $(this).prevAll('a').removeClass().addClass('score_on');
    $("#score").val($(this).prevAll().length + 1);
});

function toDisputesDetail(id) {
    window.location.href = "c_todisputesdetail.htm?disputeId=" + id;
}
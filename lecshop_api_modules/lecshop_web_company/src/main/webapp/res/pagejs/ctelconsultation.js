// 基本路径
var basePath = $("#basePath").val();
// 初始化列表
var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/c_querytelconsultation.htm?type=' + $("input[name='type']").val(),
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
                "data": function (row) {
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
                    if (row.status === "0" || row.status === "1") {
                        htmls += '<button class="btn btn-success btn-xs" onclick="toConfirm(' + row.id + ',' + row.lawyerId +')"><i class="icon-ok"></i>确认</button>';
                    }
                    if (row.status === "2" && (row.score === 0 || row.score === null)) {
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

function toScore(id) {
    $("#telconId").val(id);
    $('.score_box a').siblings('a').removeClass().addClass('score_no');
    $("#score_dialog").modal("show");
}

function score() {
    LSFetch({
        url: basePath + 'c_gradefortelconsultation.htm',
        data: {
            id: $("#telconId").val(),
            score: $("#score").val()
        },
        success: function (res) {
            if (res == 1) {
                $("#score_dialog").modal("hide");
                refreshDataTable();
            }
        }
    });
}

// 弹出确认电话咨询层
function toConfirm(id, lawyerId) {
    $("#telId").val(id);
    $("#lawyerId").val(lawyerId);
    $("#confirm_dialog").modal("show");
}

// 确认电话咨询
function confirm() {
    LSFetch({
        url: basePath + 'c_confirmtelconsultation.htm',
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
        window.location.href = "c_tolawyerletters.htm";
    }
    if (i === 1) {
        window.location.href = location.href;
    }
    if (i === 2) {
        window.location.href = "c_todisputestelconsultation.htm";
    }
    if (i === 3) {
        window.location.href = "c_toimportanttelconsultation.htm";
    }
}

function changeTables(j) {
    if (j === 0) {
        window.location.href = location.href;
    }
    window.location.href = "c_tocompanyappointmeet.htm?meetType=" + j;
}

$('.score_box a').click(function () {
    $(this).siblings('a').removeClass().addClass('score_no');
    $(this).removeClass().addClass('score_on');
    $(this).prevAll('a').removeClass().addClass('score_on');
    $("#score").val($(this).prevAll().length + 1);
});
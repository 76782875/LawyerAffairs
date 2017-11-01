// 基本路径
var basePath = $("#basePath").val();
// 初始化列表
var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/c_queryappointmeet.htm?type=' + $("input[name='type']").val() + "&meetType=" + $("input[name='meetType']").val(),
                data: dataTableAjaxData(data, $("#queryform").serializeArray()),
                success: function (res) {
                    callback(res);
                }
            });
        },
        "columns": [
            {"name": "companyInfo.name", "data": "companyInfo.name"},
            {"name": "user.name", "data": "user.name"},
            {
                "data": function (row) {
                    if (row.lawyer === null) {
                        return "未有律师接单";
                    } else {
                        return row.lawyer.name
                    }
                }
            },
            {
                "data": function (row) {
                    var operator = '';
                    if (row.status === "0") {
                        operator += (row.meetType === "2") ? '<span class="label label-danger"> 待律师接单 </span>' : '<span class="label label-danger">待律师确认</span>';
                    }
                    if (row.status === "1") {
                        operator += '<span class="label label-warning">律师已拒绝</span>';
                    }
                    if (row.status === "2") {
                        operator += (row.meetType === "2") ? '<span class="label label-info">律师已接单</span>' : '<span class="label label-info">律师已确认</span>';
                    }
                    if (row.status === "3") {
                        operator += '<span class="label label-default">用户已取消</span>';
                    }
                    if (row.status === "4") {
                        operator += '<span class="label label-primary">催用户确认</span>';
                    }
                    if (row.status === "5") {
                        operator += '<span class="label label-success">事务已完成</span>';
                    }
                    return operator;
                }
            },
            {"name": "meetTime", "data": "meetTime"},
            {"name": "meetAddress", "data": "meetAddress"},
            {"name": "createTime", "data": "createTime"},
            {
                "data": function (row) {
                    if (row.score === 0) {
                        return "未评价";
                    } else {
                        return row.score;
                    }
                }
            },
            {
                "data": function (row) {
                    var html = '<div class="operation_box">';
                    if (row.status === "0" || row.status === "2") {
                        html += '<button class="btn btn-success btn-xs" onclick="cancelBtn(' + row.id + ')"><i class="icon-ok"></i>取消</button>';
                    }
                    if (row.status === "2" || row.status === "4") {
                        html += '<button class="btn btn-success btn-xs" onclick="toConfirmDoc(' + row.id + ',' + row.lawyerId + ')"><i class="icon-ok"></i>确认</button>';
                    }
                    if (row.status === "5" && row.score === 0) {
                        html += '<button class="btn btn-primary btn-xs" onclick="toScore(' + row.id + ')"><i class="icon-smile"></i>评分</button>';
                    }
                    return html + '</div>';
                }
            }
        ],
        ordering: false
    });
    initActive();
};

// 初始化列表
initDataTable();

// 刷新列表
var refreshDataTable = function () {
    $('#dataTable').DataTable().ajax.reload();
};

function initActive() {
    $(".nav-tabs li").removeClass("active");
    if ($("input[name='meetType']").val() === "1") {
        $($(".nav.nav-tabs").children('li')[2]).addClass("active")
    }
    if ($("input[name='meetType']").val() === "2") {
        $($(".nav.nav-tabs").children('li')[1]).addClass("active")
    }
}

function cancelBtn(id) {
    LSFetch({
        url: basePath + 'c_cancelappointmeet.htm',
        data: {
            id: id
        },
        success: function (res) {
            if (res === 1) {
                showerror("取消成功");
                refreshDataTable();
            }
            if (res === -1) {
                showerror("超过两小时无法取消");
            }
        }
    });
}

function toConfirmDoc(meetId, lawyerId) {
    $("#confirmMeetId").val(meetId);
    $("#confirmLawyerId").val(lawyerId);
    $("#confirm_dialog").modal("show");
}

function confirmDoc() {
    LSFetch({
        url: basePath + 'c_confirmappointmeetting.htm',
        data: {
            meetId: $("#confirmMeetId").val(),
            lawyerId: $("#confirmLawyerId").val(),
            meetType: $("input[name='meetType']").val()
        },
        success: function (res) {
            if (res === 1) {
                $("#confirm_dialog").modal("hide");
                refreshDataTable();
            }
        }
    });
}

function toScore(id) {
    $("#meetId").val(id);
    $('.score_box a').siblings('a').removeClass().addClass('score_no');
    $("#score_dialog").modal("show");
}

function score() {
    LSFetch({
        url: basePath + 'c_gradeforappointmeeting.htm',
        data: {
            id: $("#meetId").val(),
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
        location.href = "c_tolawyerletters.htm";
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

function changeTables(j, obj) {
    if (j === 3) {
        location.href = "c_tocompanytelconsultation.htm";
        return;
    }
    $(".nav-tabs li").removeClass("active");
    $(obj).parent("li").addClass("active");
    $("input[name='meetType']").val(j);
    refreshDataTable();
}

$('.score_box a').click(function () {
    $(this).siblings('a').removeClass().addClass('score_no');
    $(this).removeClass().addClass('score_on');
    $(this).prevAll('a').removeClass().addClass('score_on');
    $("#score").val($(this).prevAll().length + 1);
});
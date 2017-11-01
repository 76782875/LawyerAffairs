// 基本路径
var basePath = $("#basePath").val();

// 将交流的弹出层放入内存中
var communionhtml = $("#communion_dialog").html();

// 初始化列表
var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/c_querylawyerletter.htm',
                data: dataTableAjaxData(data, $("#queryform").serializeArray()),
                success: function (res) {
                    callback(res);
                }
            });
        },
        "columns": [
            {"name": "name", "data": "user.name"},
            {"name": "companyName", "data": "companyInfo.name"},
            {"name": "lawyerName", "data": "lawyer.name"},
            {"name": "lawyerName", "data": "mobile"},
            {"name": "lawyerName", "data": "address"},
            {
                "name": "score", "data": function (row) {
                if (row.score === 0) {
                    return "未评价";
                } else {
                    return row.score;
                }
            }
            },
            {
                "data": function (row) {
                    var operator = '';
                    if (row.status === "0") {
                        operator = '<span class="label label-danger">待律师修改</span>'
                    }
                    if (row.status === "1") {
                        operator = '<span class="label label-info">律师修改中</span>';
                    }
                    if (row.status === "2") {
                        operator = '<span class="label label-default">待用户确认</span>';
                    }
                    if (row.status === "3") {
                        operator = '<span class="label label-success">事务已完成</span>';
                    }
                    return operator;
                }
            },
            {
                "data": function (row) {
                    var htmls = '<div class="operation_box">';
                    htmls += '<button class="btn btn-primary btn-xs" onclick="toCall(' + row.lawyerId + "," + row.lawyer.mobile + ')"><i class="icon-phone"></i>电话</button>';
                    htmls += '<button class="btn btn-success btn-xs" onclick="toSeeRecord(' + row.id + ',' + "'" + row.companyInfo.name + "'" + ',' + "'" + row.lawyer.name + "'" + ')"><i class="icon-eye-open"></i>查看</button>';
                    if (row.status === "2") {
                        htmls += '<button class="btn btn-success btn-xs" onclick="toConfirmDoc(' + row.id + ',' + row.lawyerId + ')"><i class="icon-ok"></i>确认</button>';
                    }
                    if (row.status === "3" && row.score === 0) {
                        htmls += '<button class="btn btn-primary btn-xs" onclick="toScore(' + row.id + ')"><i class="icon-smile"></i>评分</button>';
                    }
                    if (row.status === "1" || row.status === "2") {
                        htmls += '<button class="btn btn-primary btn-xs" onclick="toCommunion(' + row.id + ')"><i class="icon-star"></i> 交流</button>';
                    }
                    htmls += '</div>';
                    return htmls;
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

function toCall(id, mobile) {
    if (id === 0 || mobile === null) {
        showerror("没有律师信息");
        return;
    }
    LSFetch({
        url: basePath + '/c_initiatecall.htm',
        data: {
            call: mobile,
            lawyerId: id,
            type: "11"
        },
        success: function (data) {
            var errorCode = {
                2: "您的手机号码格式错误",
                3: "律师手机号码格式错误",
                8: "手机号码相同",
                11: "运营商线路故障,请重试或联系我们",
                12: "短时间存在相同的呼叫,请勿重复发起"
            };
            if (data === -8) {
                showerror("当前VIP没有权限或次数已达上限");
                return;
            }
            if (data === -1) {
                showerror("短时间内发起大量无效呼叫,帐号被临时冻结,请稍候再试");
                return
            }
            if (data === 0) {
                showerror("正在为您接通双方通话,请耐心等待");
                return;
            }
            if (data === 2 || data === 3 || data === 8 || data === 11 || data === 12) {
                showerror(errorCode[data]);
            } else {
                showerror("系统错误");
            }
        }
    });
}

function toSeeRecord(id, companyName, lawyerName) {
    $("#detail").html('');
    LSFetch({
        url: basePath + '/c_querylawyerletterdetailbyid.htm',
        data: {
            letterId: id
        },
        success: function (data) {
            var html = '';
            if (data.length === 0) {
                html += '<tr style="text-align: center"><td><p>' + '暂无记录' + '</p></td></tr>';
                $("#detail").html(html);
            } else {
                var name = '';
                for (var i = 0; i < data.length; i++) {
                    if (data[i].type === "1") {
                        name = companyName;
                    }
                    if (data[i].type === "2") {
                        name = lawyerName + '律师';
                    }
                    html += '<tr><td><p>' + data[i].desc + '</p>';
                    if (data[i].fileUrl !== "" && data[i].fileUrl !== null) {
                        html += '<p onclick="toDownFile(this)" style="color: #00a8b3;cursor: pointer;">下载附件<input type="hidden" value="' + data[i].fileUrl + '"></p>';
                    }
                    html += '<p>' + data[i].createTime + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;操作人:' + name + '</p></td></tr>';
                }
                $("#detail").html(html);
            }
            $("#record_dialog").modal('show');
        }
    });
}

function toConfirmDoc(letterId, lawyerId) {
    $("#letterId").val(letterId);
    $("#lawyerId").val(lawyerId);
    $("#confirm_dialog").modal("show");
}

function confirmDoc() {
    LSFetch({
        url: basePath + 'c_confirmlawyerletter.htm',
        data: {
            letterId: $("#letterId").val(),
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

function toDownFile(obj) {
    $("#downForm input[name='name']").val($(obj).children("input").val());
    $("#downForm").submit();
}

function toScore(id) {
    $("#scoreLetterId").val(id);
    $('.score_box a').siblings('a').removeClass().addClass('score_no');
    $("#score_dialog").modal("show");
}

function score() {
    LSFetch({
        url: basePath + 'c_gradeforlawyerletter.htm',
        data: {
            letterId: $("#scoreLetterId").val(),
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

function toCommunion(id) {
    $('#communion_dialog').html(communionhtml);
    $("input[name='lawyerLetterId']").val(id);
    $('#communion_dialog').modal('show');
}

function communion() {
    if (!$("#fileForm").valid()) {
        return;
    }
    var formData = new FormData($("#fileForm")[0]);
    LSFetch({
        url: basePath + "c_lawyerlettercommunion.htm",
        data: formData,
        cache: false,
        contentType: false,
        processData: false,
        success: function (res) {
            $('#communion_dialog').modal('hide');
            if (res == 1) {
                showerror("提交成功");
            } else {
                showerror("提交失败");
            }
        }
    });
}

function changeTable(num) {
    if (num === 0) {
        window.location.href = location.href;
    }
    if (num === 1) {
        window.location.href = "c_tocompanytelconsultation.htm";
    }
    if (num === 2) {
        window.location.href = "c_todisputestelconsultation.htm";
    }
    if (num === 3) {
        window.location.href = "c_toimportanttelconsultation.htm";
    }
}

function changeTables(num) {
    if (num === 0) {
        window.location.href = location.href;
    }
    var url = window.location.search !== "" ? window.location.search + '&' : url = '?';
    if (num === 1) {
        window.location.href = "c_tomodifyanddraftdoc.htm" + url + 'url=c_querydraftdoc';
    }
    if (num === 2) {
        window.location.href = "c_tomodifyanddraftdoc.htm" + url + 'url=c_querymodifydoc';
    }
}

$('.score_box a').click(function () {
    $(this).siblings('a').removeClass().addClass('score_no');
    $(this).removeClass().addClass('score_on');
    $(this).prevAll('a').removeClass().addClass('score_on');
    $("#score").val($(this).prevAll().length + 1);
});
// 基本路径
var basePath = $("#basePath").val();
// 初始化列表
var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/querylawyerletter.htm',
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
            {
                "data": function (row) {
                    var htmls = '<div class="operation_box">';
                    htmls +=
                        '<button class="btn btn-success btn-xs" onclick="toSeeRecord(' + row.id + ',' + "'" + row.companyInfo.name + "'" + ',' + "'" + row.lawyer.name + "'" + ')">' +
                        '     <i class="icon-eye-open"></i>查看' +
                        '</button>';
                    if (row.status === "2") {
                        htmls += '<button class="btn btn-success btn-xs" onclick="toConfirmDoc(' + row.id + ',' + row.lawyerId + ')"><i class="icon-ok"></i>确认</button></div>';
                    }
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

function toSeeRecord(id, companyName, lawyerName) {
    $("#detail").html('');
    LSFetch({
        url: basePath + '/querylawyerletterdetailbyletterid.htm',
        data: {
            letterId: id
        },
        success: function (data) {
            var html = '';
            if (data.length === 0) {
                html += '<tr style="text-align: center"><td><p>' + '暂无记录' + '</p></td></tr>';
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
                    html += '<p>' + data[i].createTime + '  操作人:' + name + '</p></td></tr>';
                }
            }
            $("#detail").html(html);
            $("#record_dialog").modal('show');
        }
    });
}

function toDownFile(obj) {
    $("#downForm input[name='name']").val($(obj).children("input").val());
    $("#downForm").submit();
}

function toConfirmDoc(letterId, lawyerId) {
    $("#letterId").val(letterId);
    $("#lawyerId").val(lawyerId);
    $("#confirm_dialog").modal("show");
}

function confirmDoc() {
    LSFetch({
        url: basePath + '/confirmlawyerletter.htm',
        data: {
            letterId: $("#letterId").val(),
            lawyerId: $("#lawyerId").val()
        },
        success: function (res) {
            if (res == 1) {
                $("#confirm_dialog").modal("hide");
                refreshDataTable();
            }
        }
    })
}

function changeTable(num) {
    if (num === 0) {
        window.location.href = location.href;
    }
    if (num === 1) {
        window.location.href = "tocompanytelconsultation.htm";
    }
    if (num === 2) {
        window.location.href = "todisputestelconsultation.htm";
    }
    if (num === 3) {
        window.location.href = "toimportanttelconsultation.htm";
    }
}

function changeTables(num) {
    if (num === 0) {
        window.location.href = location.href;
    }
    var url = window.location.search !== "" ? window.location.search + '&' : url = '?';
    if (num === 1) {
        window.location.href = "tomodifyanddraftdoc.htm" + url + 'url=querydraftdoc';
    }
    if (num === 2) {
        window.location.href = "tomodifyanddraftdoc.htm" + url + 'url=querymodifydoc';
    }
}
// 基本路径
var basePath = $("#basePath").val();

var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/c_querytemplatelist.htm',
                data: dataTableAjaxData(data, $("#queryform").serializeArray()),
                success: function (res) {
                    callback(res);
                }
            });
        },
        "columns": [
            {
                "name": "name", "data": "name"
            },

            {
                "typename": "name", "data": function (row) {
                return row.templateType.name
            }
            },

            {
                "data": function (row) {
                    return '<div class="operation_box"><button class="btn btn-primary btn-xs" onclick="downloadTem(' + row.id + "," + "'" + row.url + "'" + ')"><i class="icon-arrow-down"></i> 下载</button></div>';
                }
            }
        ],
        ordering: false
    });
    LSFetch({
        url: basePath + '/c_querytemplatetypebygrade.htm',
        success: function (data) {
            var param = '<option value="0">全部</option>';
            for (var i = 0; i < data.length; i++) {
                param += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
            }
            $("#firstGrade").html(param);
        }
    });
};

// 模板下载
function downloadTem(id, name) {
    var html = '<iframe name="downIframe" style="display:none"></iframe>';
    $("#iframeDiv").html(html);
    $("#downForm").attr("action", 'c_templatedownload.htm');
    $("#downForm input[name='name']").val(name);
    $("#downForm input[name='id']").val(id);
    $("#downForm").submit();
    $('iframe').on('load', function () {
        var responseText = $('iframe')[0].contentDocument.body.textContent;
        var responseData = JSON.parse(responseText) || {};
        if (responseData === -8) {
            showerror("当前VIP没有权限或次数已达上限");
        }
    });
}

// 初始化列表
initDataTable();

// 刷新列表
var refreshDataTable = function () {
    $("input[name='typeIds']").val(checkSelect());
    $('#dataTable').DataTable().ajax.reload();
};

// 初始化下拉框
function secondOrThirdGrade(obj, idname) {
    if ($(obj).val() == 0) {
        if (idname == 'secondGrade') {
            $("#secondGrade").html('<option value="0">全部</option>');
        }
        $("#thirdGrade").html('<option value="0">全部</option>');
        $('input[name="typeId"]').val(0);
        return;
    } else {
        $("#thirdGrade").html('<option value="0">全部</option>');
    }
    LSFetch({
        url: basePath + '/c_querytemplatetypebyparentid.htm?parentId=' + $(obj).val(),
        async: false,
        success: function (data) {
            var param = '<option value="0">全部</option>';
            if (data.length != 0) {
                for (var i = 0; i < data.length; i++) {
                    param += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
                }
            } else {
                $("#thirdGrade").html('<option value="0">全部</option>');
            }
            $("#" + idname).html(param);
        }
    });
}

function checkSelect() {
    if ($("#firstGrade").val() == "0") {
        return "";
    }
    if ($("#firstGrade").val() != "0" && $("#secondGrade").val() == "0") {
        LSFetch({
            url: basePath + '/c_querytemplateidsbyfirstparentid.htm?parentId=' + $("#firstGrade").val(),
            async: false,
            success: function (data) {
                if (data.length == 0) {
                    $('input[name="typeIds"]').val(0);
                } else {
                    $('input[name="typeIds"]').val(data);
                }
            }
        });
        $("input[name='typeId']").val(0);
        return $('input[name="typeIds"]').val();
    }
    if ($("#firstGrade").val() != "0" && $("#secondGrade").val() != "0" && $("#thirdGrade").val() == "0") {
        LSFetch({
            url: basePath + '/c_querytemplatetypeidsbysecondparentid.htm?parentId=' + $("#secondGrade").val(),
            async: false,
            success: function (data) {
                if (data.length == 0) {
                    $('input[name="typeIds"]').val(0);
                } else {
                    $('input[name="typeIds"]').val(data);
                }
            }
        });
        $("input[name='typeId']").val(0);
        return $('input[name="typeIds"]').val();
    }
    if ($("#firstGrade").val() != "0" && $("#secondGrade").val() != "0" && $("#thirdGrade").val() != "0") {
        $("input[name='typeId']").val($("#thirdGrade").val());
    }
}
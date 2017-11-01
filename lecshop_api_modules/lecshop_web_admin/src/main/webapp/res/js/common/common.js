/**
 * 获得选择的id
 */
function getSelectIds(name) {
    var checkboxs = $("input[name=" + name + "]");
    var ids = new Array();
    for (var j = 0; j < checkboxs.length; j++) {
        if ($(checkboxs[j]).is(':checked') == true) {
            ids.push($(checkboxs[j]).val());
        }
    }
    return ids;
}

// ajax 请求
function LSFetch(params) {
    // 默认参数
    var _params = {
        type: 'post',
        dataType: 'json',
        timeout: 60000,
        error: function () {
            showerror('网络错误');
        }
    };
    $.extend(true, params, _params);
    $.ajax(params);
};

/**
 * 提示错误信息
 * @param msg
 */
function showerror(msg) {
    $("#error").remove();
    var diahtml = '<div id="error" class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myModalLabel"aria-hidden="true">';
    diahtml += '  <div class="modal-dialog">';
    diahtml += '  <div class="modal-content">';
    diahtml += '<div class="modal-header">';
    diahtml += '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button><h4 class="modal-title">系统提示</h4></div>';
    diahtml += ' <div class="modal-body errormsg">' + msg + '</div>';
    diahtml += '<div class="modal-footer">    <button data-dismiss="modal" class="btn btn-success" type="button">确定</button></div></div></div></div>';
    $(document.body).append(diahtml);
    $('#error').modal('show');
}

/**
 * 删除确认提示框
 */
function showdeleteconfirm(tips, ids) {
    $("#confirm").remove();
    var diahtml = '<div id="confirm" class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myModalLabel"aria-hidden="true">';
    diahtml += '  <div class="modal-dialog">';
    diahtml += '  <div class="modal-content">';
    diahtml += '<div class="modal-header">';
    diahtml += '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button><h4 class="modal-title">确认提示</h4></div>';
    diahtml += ' <div class="modal-body errormsg">' + tips + '</div>';
    diahtml += '<div class="modal-footer"> <button data-dismiss="modal" class="btn btn-default" type="button">取消</button> <button class="btn btn-success copybtn" onclick="deleteAll(\'' + ids + '\')" type="button">确定</button></div></div></div></div>';

    $(document.body).append(diahtml);
    $('#confirm').modal('show');
}


Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1, //month
        "d+": this.getDate(), //day
        "h+": this.getHours(), //hour
        "m+": this.getMinutes(), //minute
        "s+": this.getSeconds(), //second
        "q+": Math.floor((this.getMonth() + 3) / 3), //quarter
        "S": this.getMilliseconds() //millisecond
    };
    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
};

// 日期控件校验
function dataValid(name) {
    if ($("input[name='" + name + "']").val() == '' || $("input[name='" + name + "']").val() == null) {
        $("input[name='" + name + "']").parent().addClass("error");
        $("input[name='" + name + "']").parent().after('<label for="' + name + '" generated="true" class="error help-block">不能为空</label>');
        $("input[name='" + name + "']").parent().parent().parent().addClass("has-error");
        return false;
    }
    return true;
}

$(".search_datetime input").change(function () {
    if ($(this).val() != '') {
        $(this).parent().removeClass("error");
        $(this).parent().next().remove();
        $(this).parent().parent().parent().removeClass("has-error");
    }
});


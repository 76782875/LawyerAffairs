var basePath = $("#basePath").val();

$(".search_datetime").datetimepicker({
    format: 'yyyy-mm-dd hh:ii:00',
    weekStart: 1,
    autoclose: true,
    language: 'zh',
    todayBtn: true,
    viewSelect: 'hour'
});

loadOneType("firstTypes");

function loadOneType(name) {
    var html = "";
    LSFetch({
        url: basePath + '/c_queryfirstconsultationtype.htm',
        success: function (data) {
            if (data.length > 0) {
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i].code + '" data-id="' + data[i].id + '">' + data[i].name + '</option>';
                }
            }
            html += '<option value="1a" data-id="-1">自定义类型</option>';
            $("select[name='" + name + "']").html(html);
            if (name === "firstType") {
                loadTwoType($("select[name='" + name + "'] option:selected").attr("data-id"), "secondType");
            } else {
                loadTwoType($("select[name='" + name + "'] option:selected").attr("data-id"), "secondTypes");
            }
        }
    });
}

function changeType(obj, name) {
    loadTwoType($(obj).find("option:selected").attr("data-id"), name);
}

function loadTwoType(id, name) {
    var html = '';
    if (id === "-1") {
        html += '<input type="text" class="form-control">';
        $("#" + name).html(html);
    } else {
        LSFetch({
            url: basePath + '/c_querysecondconsultationtype.htm',
            data: {
                id: id
            },
            success: function (data) {
                html += '<select class="form-control" name="' + name + '">';
                if (data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        html += '<option value="' + data[i].code + '" data-id="' + data[i].id + '">' + data[i].name + '</option>';
                    }
                } else {
                    html += '<option value="" data-id="-2">暂无相关类型</option>';
                }
                html += '</select>';
                $("select[name='" + name + "']").html(html);
                $("#" + name).html(html);
            }
        });
    }
}

function appointMeetSave(obj) {
    if (!$("#appointForm").valid() | !dataValid("meetTime")) {
        return;
    }
    if ($(obj).attr("data-action") === "false") {
        showerror("您已提交,请等待结果,请勿短时间内重复操作");
        return;
    }
    $(obj).attr("data-action", "false");
    var consultType = '';
    var param = '';
    if ($("select[name='firstTypes'] option:selected").attr("data-id") === "-1") {
        consultType = $("#secondTypes input").val();
        param = '?casetype=' + $("select[name='firstTypes'] option:selected").val() + "&casetypefree=" + $("#secondTypes input").val();
    } else {
        consultType = ($("select[name='secondTypes'] option:selected").attr("data-id") === "-2") ? $("select[name='firstTypes'] option:selected").text() : $("select[name='secondTypes'] option:selected").text();
        var casetype = ($("select[name='secondTypes'] option:selected").attr("data-id") === "-2") ? $("select[name='firstTypes'] option:selected").val() : $("select[name='secondTypes'] option:selected").val();
        param = '?casetype=' + casetype + "&casetypefree=";
    }
    var appointMeet = {
        type: "1",
        consultType: consultType,
        desc: $("input[name='desc']").val(),
        meetTime: $("input[name='meetTime']").val(),
        meetAddress: $("input[name='meetAddress']").val(),
        lawyerId: -1
    };
    LSFetch({
        url: basePath + '/c_addappointmeet.htm' + param,
        data: JSON.stringify(appointMeet),
        contentType: "application/json;charset=utf-8",
        success: function (data) {
            if (data === -8) {
                showerror("当前VIP没有权限或次数已达上限");
                return;
            }
            $("#tip_dialog").modal('show');
            if (data === 1) {
                $(obj).attr("data-action", "true");
                $('#tip_dialog').modal('hide');
                showerror("预约成功,请等待律师回复");
                $("#appointForm")[0].reset();
            }
        }
    });
}
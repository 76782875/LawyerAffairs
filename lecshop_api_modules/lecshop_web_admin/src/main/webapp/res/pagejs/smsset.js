// 基本路径
var basePath = $("#basePath").val();

// 初始化列表
var initData = function () {
    LSFetch({
        url: basePath + '/querysmsset.htm',
        success: function (data) {
            $("input[name='id']").val(data.id);
            $("input[name='key']").val(data.key);
            $("input[name='secret']").val(data.secret);
            $("input[name='url']").val(data.url);
            $("input[name='sign']").val(data.sign);
            $("input[name='templateId']").val(data.templateId);
        }
    });
};

// 初始化列表
initData();

// 刷新列表
var refreshData = function () {
    initData();
};

//修改邮箱设置
function smsSaveBtn() {
    if (!$("#smsForm").valid()) {
        return;
    }
    var sms = {
        id: $("input[name='id']").val(),
        key: $("input[name='key']").val(),
        secret: $("input[name='secret']").val(),
        url: $("input[name='url']").val(),
        sign: $("input[name='sign']").val(),
        templateId: $("input[name='templateId']").val()
    };
    LSFetch({
        url: basePath + '/editsmsset.htm',
        data: JSON.stringify(sms),
        contentType: "application/json;charset=utf-8",
        success: function (data) {
            if (data === -1) {
                showerror("系统错误");
                return;
            }
            if (data === 1) {
                $('#success_dialog').modal('show');
                refreshData();
            } else {
                showerror("编辑修改出错");
            }
        }
    });
}
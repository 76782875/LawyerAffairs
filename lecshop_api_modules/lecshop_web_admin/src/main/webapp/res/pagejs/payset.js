// 基本路径
var basePath = $("#basePath").val();

// 初始化列表
var initData = function () {
    LSFetch({
        url: basePath + '/querypayset.htm',
        success: function (data) {
            $("#tab1 input[name='pid']").val(data.aliPaySet.pid);
            $("#tab1 input[name='key']").val(data.aliPaySet.key);
            $("#tab1 input[name='payee']").val(data.aliPaySet.payee);
            $("#tab1 input[name='backCallbackUrl']").val(data.aliPaySet.backCallbackUrl);
        }
    });
};

// 初始化列表
initData();

// 刷新列表
var refreshData = function () {
    initData();
};

//编辑支付设置
function editPaySetBtn(numForm) {
    if (!($("#paySetForm" + numForm).valid())) {
        return;
    }
    var paySetCommon = {
        aliPaySet: {
            pid: $("#tab1 input[name='pid']").val(),
            key: $("#tab1 input[name='key']").val(),
            payee: $("#tab1 input[name='payee']").val(),
            backCallbackUrl: $("#tab1 input[name='backCallbackUrl']").val(),
        }
    };
    LSFetch({
        url: basePath + '/editpayset.htm?codeType=' + numForm,
        data: JSON.stringify(paySetCommon),
        contentType: "application/json;charset=utf-8",
        success: function (data) {
            if (data === -1) {
                showerror("系统错误");
                return;
            }
            if (data >= 1) {
                refreshData();
                $('#success_dialog').modal('show');
            } else {
                showerror("编辑修改出错");
            }
        }
    });
}
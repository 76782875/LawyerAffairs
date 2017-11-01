// 基本路径
var basePath = $("#basePath").val();

// 初始化列表
var initData = function () {
    LSFetch({
        url: basePath + '/querywxsetting.htm',
        success: function (data) {
            $("input[name='id']").val(data.id);
            $("input[name='appId']").val(data.appId);
            $("input[name='appSecret']").val(data.appSecret);
            $("input[name='callBackUrl']").val(data.callBackUrl);
        }
    });
};

// 初始化列表
initData();

// 刷新列表
var refreshData = function () {
    initData();
};

//编辑微信设置
function saveSetting() {
    if (!($("#wxSettingForm").valid())) {
        return;
    }
    var wxSetting = {
        id: $("input[name='id']").val(),
        appId: $("input[name='appId']").val(),
        appSecret: $("input[name='appSecret']").val(),
        callBackUrl: $("input[name='callBackUrl']").val()
    };
    LSFetch({
        url: basePath + '/editwxsetting.htm',
        data: JSON.stringify(wxSetting),
        contentType: "application/json;charset=utf-8",
        success: function (data) {
            if (data == 1) {
                refreshData();
                $('#success_dialog').modal('show');
            }
        }
    });
}
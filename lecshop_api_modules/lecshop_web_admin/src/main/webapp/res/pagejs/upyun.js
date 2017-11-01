// 基本路径
var basePath = $("#basePath").val();

// 初始化列表
var initData = function () {
    LSFetch({
        url: basePath + '/queryupyun.htm',
        success: function (data) {
            $("input[name='id']").val(data.id);
            $("input[name='nameSpace']").val(data.nameSpace);
            $("input[name='username']").val(data.username);
            $("input[name='password']").val(data.password);
            $("input[name='address']").val(data.address);
            $("input[name='modifyTime']").val(data.modifyTime);
        }
    });
};

// 初始化列表
initData();

// 刷新列表
var refreshData = function () {
    initData();
};

//修改又拍云设置
function upyunSaveBtn() {
    if (!$("#upyunForm").valid()) {
        return;
    }
    var upyun = {
        id: $("input[name='id']").val(),
        nameSpace: $("input[name='nameSpace']").val(),
        username: $("input[name='username']").val(),
        password: $("input[name='password']").val(),
        address: $("input[name='address']").val()
    };
    LSFetch({
        url: basePath + '/updateupyunset.htm',
        data: JSON.stringify(upyun),
        contentType: "application/json;charset=utf-8",
        success: function (data) {
            if (data == -1) {
                showerror("系统错误");
                return;
            }
            if (data == 1) {
                $('#success_dialog').modal('show');
                refreshData();
            } else {
                showerror("编辑修改出错");
            }
        }
    });
}
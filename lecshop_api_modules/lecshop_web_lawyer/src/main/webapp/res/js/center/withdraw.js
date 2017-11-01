var basePath = $("#basePath").val();

var withdrawcoount = 0;

// 提现申请
function applywithdraw() {

    if (withdrawcoount == 1) {
        return;
    }


    if ($("#money").val() == "") {
        tip('请输入提现金额!');
        return;
    }


    if (!checkMoney($("#money").val())) {
        tip('请输入合法金额!');
        return;
    }


    withdrawcoount++;

    $.ajax({
        url: basePath + '/applywithdraw.htm',
        type: 'post',
        data: {
            money: $("#money").val()
        },
        success: function (result) {
            if (result == -1) {
                withdrawcoount = 0;
                tip('未到提现日期!');
            } else if (result == -2) {
                withdrawcoount = 0;
                tip('未设置提现账户信息!');
            } else if (result == -3) {
                withdrawcoount = 0;
                tip('参数错误!');
            } else if (result == 1) {
                tip('提现申请成功!');
                // 跳转到提现列表
                setTimeout(gotolist, 2000)
            } else if (result == -4) {
                withdrawcoount = 0;
                tip('账户余额不足!');
            } else {
                withdrawcoount = 0;
                tip('提现申请失败!');
            }
        }
    });

}

// 跳转到提现列表
function gotolist() {
    window.location.href = basePath + "/towithdrawlists.htm";
}


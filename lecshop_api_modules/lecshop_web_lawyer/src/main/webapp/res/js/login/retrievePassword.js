var basePath = $("#basePath").val();

// 发送验证码
function sendCode(obj) {

    if (!checkPhone($("#mobile").val())) {
        tip('请输入正确的手机号码!');
        return;
    }

    $.ajax({
        url: basePath + '/forget_sendvalidcode.htm',
        type: 'post',
        data: {
            mobile: $("#mobile").val()
        },
        success: function (result) {
            if (result === 1) {
                tip('手机验证码已发送');
                $(obj).attr("onclick", "");
                var timer = setTimeout(timeDown, 1000);
                var i = 60;

                function timeDown() {
                    $(obj).text("重新发送(" + i + "s)");
                    i--;
                    if (i == 0) {
                        $(obj).text("获取验证码");
                        $(obj).attr("onclick", "sendCode(this)");
                        clearTimeout(timer);
                    } else {
                        setTimeout(timeDown, 1000);
                    }
                }
            } else {
                tip('手机验证码发送失败!');
            }

        }
    });
}


// 修改密码
function updatePassword() {

    if (!checkPhone($("#mobile").val())) {
        tip('请输入正确的手机号码!');
        return;
    }

    if ($("#code").val() == '') {
        tip('请输入手机验证码!');
        return;
    }


    if ($("#password").val() == '') {
        tip('请输入新密码!');
        return;
    }

    if ($("#password").val() != $("#newpassword").val()) {
        tip('两次输入的密码不一致!');
        return;
    }


    $.ajax({
        url: basePath + '/updatepassword.htm',
        type: 'post',
        data: {
            mobile: $("#mobile").val(),
            code: $("#code").val(),
            password: $("#password").val()
        },
        success: function (result) {
            // 参数错误
            if (result === -3) {
                tip('参数错误');
            } else if (result === -2) {
                // 验证码错误
                tip('验证码错误');

            } else if (result === 1) {
                window.location.href = basePath + "/tologin.htm";
            } else {
                // 密码修改失败
                tip('密码修改失败');
            }
        }
    });
}

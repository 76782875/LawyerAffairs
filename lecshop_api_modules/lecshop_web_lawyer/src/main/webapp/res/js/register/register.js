var basePath = $("#basePath").val();


// 跳转到找回密码页面
function retrievePassword() {
    window.location.href = basePath + "/toretrievepassword.htm";
}

// 跳转到登录页面
function toLogin() {
    window.location.href = basePath + "/tologin.htm";
}

// 发送验证码
function sendCode(obj) {

    if (!checkPhone($("#mobile").val())) {
        tip('请输入正确的手机号码!');
        return;
    }

    $.ajax({
        url: basePath + '/sendvalidcode.htm',
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
            } else if (result == -1) {
                tip('手机号码已经存在!');
            } else {
                tip('手机验证码发送失败!');
            }

        }
    });
}

// 注册律师
function registerLawyer() {

    if (!checkPhone($("#mobile").val())) {
        tip('请输入正确的手机号码!');
        return;
    }

    if ($("#validateCode").val() == "") {
        tip('请输入验证码!');
        return;
    }

    if ($("#code").val() == "") {
        tip('请输入执业证号!');
        return;
    }

    if ($("#password").val() == "") {
        tip('请输入密码!');
        return;
    }

    if ($("#password").val() != $("#newpassword").val()) {
        tip('两次密码不一致!');
        return;
    }

    if ($("#lawyersPlace").val() == "") {
        tip('请输入律所!');
        return;
    }

    if ($("#address").val() == "") {
        tip('请输入联系地址!');
        return;
    }

    if ($("#picurl").val() == "") {
        tip('请上传律师证!');
        return;
    }


    $.ajax({
        url: basePath + '/register.htm',
        type: 'post',
        data: {
            validateCode: $("#validateCode").val(),
            mobile: $("#mobile").val(),
            code: $("#code").val(),
            password: $("#password").val(),
            lawyersPlace: $("#lawyersPlace").val(),
            address: $("#address").val(),
            lawyerPic: $("#picurl").val()
        },
        success: function (result) {
            if (result == -3) {
                tip('参数错误!');
            } else if (result == -2) {
                tip('验证码错误!');
            } else if (result == -1) {
                tip('手机号码已经存在!');
            } else if (result == 1) {
                window.location.href = basePath + "/totip.htm?msg=律师正在审核中!";
            } else {
                tip('注册失败!');
            }
        }
    });

}


// 上传律师证
function uploadPic() {
    $.ajax({
        url: basePath + '/uploadtoupyun.htm',
        type: 'POST',
        cache: false,
        data: new FormData($('#registerLawyer')[0]),
        processData: false,
        contentType: false
    }).done(function (res) {
        $("#picurl").val(res);
    });
}

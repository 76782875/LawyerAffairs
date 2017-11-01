var basePath = $("#basePath").val();

// 律师绑定
function lawyerBind() {


    if (!checkPhone($("#mobile").val())) {
        tip('请输入正确的手机号码!');
        return;
    }


    if ($("#password").val() == "") {
        tip('请输入密码!');
        return;
    }


    $.ajax({
        url: basePath + '/login.htm',
        type: 'post',
        data: {
            password: $("#password").val(),
            mobile: $("#mobile").val()
        },
        success: function (result) {
            // 登录成功跳转到首页
            if (result === 200) {
                window.location.href = basePath + "/index.htm";
            } else if (result === 1) {
                // 禁用
                window.location.href = basePath + "/totip.htm?msg=律师已被禁用,请与管理员联系!";
            } else if (result === 2) {
                window.location.href = basePath + "/totip.htm?msg=律师正在审核中!";
            } else if (result === 3) {
                window.location.href = basePath + "/totip.htm?msg=律师审核失败,请与管理员联系!";
            } else if (result === -1) {
                tip('用户名密码错误!');
            } else {
                tip('系统错误!');
            }
        }
    });
}

// 跳转到找回密码页面
function retrievePassword() {
    window.location.href = basePath + "/toretrievepassword.htm";
}

// 跳转到注册页面
function toRegister() {
    window.location.href = basePath + "/toregister.htm";
}

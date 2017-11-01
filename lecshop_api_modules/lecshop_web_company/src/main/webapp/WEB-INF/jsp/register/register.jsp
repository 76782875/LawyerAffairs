<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>注册 - 律师弟弟</title>
    <!-- Bootstrap core CSS -->
    <link href="<%=basePath %>/res/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath %>/res/css/bootstrap-reset.css" rel="stylesheet">
    <!--external css-->
    <link href="<%=basePath %>/res/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <!-- Custom styles for this template -->
    <link href="<%=basePath %>/res/css/style.css" rel="stylesheet">
    <link href="<%=basePath %>/res/css/style-responsive.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/res/assets/bootstrap-fileupload/bootstrap-fileupload.css">
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 tooltipss and media queries -->
    <!--[if lt IE 9]>
    <script src="<%=basePath %>/res/js/html5shiv.js"></script>
    <script src="<%=basePath %>/res/js/respond.min.js"></script>
    <![endif]-->
</head>

<body class="login-body" style="background:url(<%=basePath %>/res/img/login-bg.jpg) no-repeat; background-size: cover">
<div class="container form-signin">
    <h2 class="form-signin-heading"><img src="<%=basePath %>/res/img/logo.png" alt=""/></h2>
    <div class="login-wrap">
        <div class="input-group m-bot15">
            <input type="text" placeholder="手机号码" name="phone" class="form-control requireds" style="margin:0">
            <span class="input-group-btn">
                <button class="btn btn-white" style="height:39px" type="button" onclick="sendCode(this)">发送验证码</button>
            </span>
        </div>
        <input type="text" class="form-control requireds" name="phoneCode" maxlength="6" placeholder="手机验证码">
        <input type="password" class="form-control requireds" name="password" placeholder="设置密码">
        <input type="password" class="form-control requireds" name="rePassword" placeholder="再次输入密码">
        <input type="text" class="form-control requireds" name="companyName" placeholder="公司名称">
        <input type="text" class="form-control requireds" name="code" placeholder="社会信用代码号">
        <div style="font-size:14px;text-align: center; padding-bottom:10px">营业执照</div>
        <div class="fileupload fileupload-new" data-provides="fileupload" style="text-align: center;">
            <form id="businessLicence" enctype="multipart/form-data" method="post">
                <div class="fileupload-new thumbnail" style="height: 60px;">
                    <img src="<%=basePath %>/res/img/no_img.png" alt="" name="businessLicence"/>
                </div>
                <div class="fileupload-preview fileupload-exists thumbnail" style="max-width: 200px; max-height: 80px; line-height: 20px;"></div>
                <div>
                    <span class="btn btn-white btn-file tooltips" data-placement="right" data-original-title="建议100*40px">
                        <span class="fileupload-new"><i class="icon-paper-clip"></i> 选择本地图片</span>
                        <span class="fileupload-exists"><i class="icon-undo"></i> 重新上传</span>
                        <input type="file" class="default" name="image" onchange="uploadPic('businessLicence')"/>
                    </span>
                </div>
            </form>
        </div>
        <div class="alert alert-block alert-danger fade in" style="display: none;">验证码错误</div>
        <button class="btn btn-lg btn-login btn-block" type="button" onclick="register()">注册</button>
        <div class="registration">已经有账号?<a class="" href="c_tologin.htm">立即登录</a></div>
    </div>
</div>
<!-- js placed at the end of the document so the pages load faster -->
<!--common script for all pages-->
<jsp:include page="../common/includejs.jsp"></jsp:include>
<script>
    function sendCode(obj) {
        if ($("input[name='phone']").val() === '') {
            $(".alert-block").text("手机号码不能为空");
            $(".alert-block").show();
            return;
        }
        $.ajax({
            url: 'c_tosendregistercode.htm',
            type: 'POST',
            data: {
                phone: $("input[name='phone']").val()
            },
            success: function (data) {
                if (data === -1) {
                    $(".alert-block").text('手机号码不正确');
                    $(".alert-block").show();
                    return;
                }
                if (data === 0) {
                    $(".alert-block").text('验证码发送失败');
                    $(".alert-block").show();
                    return;
                }
                if (data === 1) {
                    $(obj).attr("onclick", "");
                    var timer = setTimeout(timeDown, 1000);
                    var i = 60;

                    function timeDown() {
                        $(obj).text("重新发送(" + i + "s)");
                        i--;
                        if (i === 0) {
                            $(obj).text("发送验证码");
                            $(obj).attr("onclick", "sendCode(this)");
                            clearTimeout(timer);
                        } else {
                            setTimeout(timeDown, 1000);
                        }
                    }
                }
            }
        });
    }

    function register() {
        if (valid()) {
            if ($("input[name='password']").val() != $("input[name='rePassword']").val()) {
                $(".alert-block").text("两次密码不一致");
                $(".alert-block").show();
                return;
            }
            if ($("img[name='businessLicence']").attr("src") == '' || $("img[name='businessLicence']").attr("src").indexOf("no_img") > 1) {
                $(".alert-block").text("上传营业执照");
                $(".alert-block").show();
                return;
            }
            $.ajax({
                url: 'c_register.htm',
                type: 'POST',
                data: {
                    phone: $("input[name='phone']").val(),
                    phoneCode: $("input[name='phoneCode']").val(),
                    password: $("input[name='password']").val(),
                    rePassword: $("input[name='rePassword']").val(),
                    companyName: $("input[name='companyName']").val(),
                    code: $("input[name='code']").val(),
                    businessLicence: $("img[name='businessLicence']").attr("src")
                },
                success: function (data) {
                    if (data === 1) {
                        location.href = "c_tologin.htm";
                        return;
                    }
                    if (data === -1) {
                        showerror("短信验证码错误");
                        return;
                    }
                    if (data === -2) {
                        showerror("手机号不正确");
                        return;
                    }
                    if (data === -3) {
                        showerror("两次密码不一致");
                        return;
                    }
                    if (data === -4) {
                        showerror("手机号码已被使用");
                        return;
                    }
                    if (data === -5) {
                        showerror("公司名称已被注册");
                        return;
                    }
                    showerror("网络错误");
                }
            });
        }
    }

    function valid() {
        var result = true;
        $(".requireds").each(function () {
            if ($(this).val() === '') {
                $(".alert-block").text($(this).attr("placeholder"));
                $(".alert-block").show();
                return result = false;
            }
        });
        return result;
    }

    // 上传商品图片
    function uploadPic(obj) {
        $.ajax({
            url: 'c_uploadtoupyun.htm',
            type: 'POST',
            cache: false,
            data: new FormData($('#' + obj)[0]),
            processData: false,
            contentType: false
        }).done(function (res) {
            $("img[name='" + obj + "']").attr("src", res);
        }).fail(function () {
        });
    }
</script>
</body>
</html>

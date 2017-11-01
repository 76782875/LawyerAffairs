<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="">
    <title>登录 - 律师弟弟</title>
    <!-- Bootstrap core CSS -->
    <link href="<%=basePath %>/res/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath %>/res/css/bootstrap-reset.css" rel="stylesheet">
    <!--external css-->
    <link href="<%=basePath %>/res/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <!-- Custom styles for this template -->
    <link href="<%=basePath %>/res/css/style.css" rel="stylesheet">
    <link href="<%=basePath %>/res/css/style-responsive.css" rel="stylesheet"/>
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 tooltipss and media queries -->
    <!--[if lt IE 9]>
    <script src="<%=basePath %>/res/js/html5shiv.js"></script>
    <script src="<%=basePath %>/res/js/respond.min.js"></script>
    <![endif]-->
</head>

<body class="login-body" style="background:url(<%=basePath %>/res/img/login-bg.jpg) no-repeat; background-size: cover">
<input type="hidden" value="<%=basePath%>" id="basePath">
<div class="container">
    <form class="form-signin" id="loginForm">
        <h2 class="form-signin-heading"><img src="<%=basePath %>/res/img/logo.png" alt=""/></h2>
        <div class="login-wrap">
            <input type="text" class="form-control" placeholder="用户名" name="username" autofocus>
            <input type="password" class="form-control" placeholder="密码" name="password">
            <div class="clearfix">
                <input type="text" class="form-control" placeholder="验证码" maxlength="4" name="code" style="width:200px; float:left">
                <a href="javascript:;" style="float:left">
                    <img src="createkaptcha.htm" width="90" height="39" alt="" onclick="this.src=this.src+'?'+Math.random();"/>
                </a>
            </div>
            <div class="alert alert-block alert-danger fade in" style="display:none;">
                <button data-dismiss="alert" class="close close-sm" type="button"><i class="icon-remove"></i></button>
                验证码错误！
            </div>
            <button class="btn btn-lg btn-login btn-block" type="button" onclick="loginBtn()">登录</button>
        </div>
    </form>
</div>

<!-- js placed at the end of the document so the pages load faster -->
<script src="<%=basePath %>/res/js/jquery.js"></script>
<script src="<%=basePath %>/res/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/common/common.js"></script>
<script>
    $(function () {
        $("body").keydown(function (e) {
            if (e.keyCode == 13) {
                loginBtn();
            }
        });
    });
    function loginBtn() {
        if ($("input[name='username']").val() == '') {
            $(".alert-block").text('请输入用户名');
            $(".alert-block").show();
            return;
        }
        if ($("input[name='password']").val() == '') {
            $(".alert-block").text('请输入密码');
            $(".alert-block").show();
            return;
        }
        if ($("input[name='code']").val() == '') {
            $(".alert-block").text('请输入验证码');
            $(".alert-block").show();
            return;
        }
        if ($("input[name='code']").val().length != 4) {
            $(".alert-block").text('验证码格式有误');
            $(".alert-block").show();
            return;
        }
        $.ajax({
            url: 'login.htm',
            type: 'POST',
            data: $("#loginForm").serialize(),
            success: function (data) {
                //-1 参数不对  -2 验证码不对 -3 用户名或者密码错误 -4该用户被禁用 0成功
                if (data == -1) {
                    $(".alert-block").text('参数不对');
                    $(".alert-block").show();
                } else if (data == -2) {
                    $(".alert-block").text('验证码不对');
                    $(".alert-block").show();
                } else if (data == -3) {
                    $(".alert-block").text('用户名或者密码错误');
                    $(".alert-block").show();
                } else if (data == -4) {
                    $(".alert-block").text('该用户被禁用请联系平台');
                    $(".alert-block").show();
                } else {
                    window.location.href = "index.htm";
                }
            }
        });
    }
</script>
</body>
</html>

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
<div class="container">
    <form class="form-signin" id="loginForm">
        <h2 class="form-signin-heading"><img src="<%=basePath %>/res/img/logo.png" alt=""/></h2>
        <div class="login-wrap">
            <input type="text" class="form-control" placeholder="公司名称/手机号码" autofocus name="mobileOrCompanyName">
            <input type="password" class="form-control" placeholder="密码" name="password">
            <div class="clearfix">
                <input type="text" class="form-control" placeholder="验证码" style="width:200px; float:left" name="code" maxlength="4">
                <a href="javascript:;" style="float:left">
                    <img src="c_createkaptcha.htm" width="90" height="39" alt="" onclick="this.src=this.src+'?'+Math.random();"/>
                </a>
            </div>
            <div class="clearfix">
                <div class="registration" style="margin-bottom: 5px;">还没有账号?<a href="c_toregister.htm">注册一个账号</a>
                    <span class="pull-right">
                    <a data-toggle="modal" href="#myModal"> 忘记密码?</a>
                </span>
                </div>
            </div>
            <div class="alert alert-block alert-danger fade in" style="display:none;margin-bottom: 12px!important;">
                <button data-dismiss="alert" class="close close-sm" type="button"><i class="icon-remove"></i></button>
                验证码错误！
            </div>
            <button class="btn btn-lg btn-login btn-block" type="button" onclick="loginBtn()">登录</button>
        </div>
    </form>
</div>
<!-- Modal -->
<div class="modal fade" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">忘记密码?</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal tasi-form" id="forgetPasswordForm">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">手机号码</label>
                        <div class="col-lg-9">
                            <div class="input-group">
                                <input type="text" placeholder="手机号码" name="phone" class="form-control required">
                                <span class="input-group-btn">
                                    <button class="btn btn-white" type="button" onclick="sendCode(this)">发送验证码</button>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">验证码</label>
                        <div class="col-lg-9">
                            <input type="text" name="code" placeholder="验证码" autocomplete="off" class="form-control required placeholder-no-fix">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">新密码</label>
                        <div class="col-lg-9">
                            <input type="password" name="password" placeholder="新密码" autocomplete="off" class="form-control required placeholder-no-fix">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">再次输入密码</label>
                        <div class="col-lg-9">
                            <input type="password" name="rePassword" placeholder="再次输入密码" autocomplete="off" class="form-control required placeholder-no-fix">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                <button class="btn btn-success" type="button" onclick="forgetPassword()">提交</button>
            </div>
        </div>
    </div>
</div>
<!-- Modal -->
<!-- js placed at the end of the document so the pages load faster -->
<!--common script for all pages-->
<jsp:include page="../common/includejs.jsp"></jsp:include>
<script>
    $(function () {
        $("body").keydown(function (e) {
            if (e.keyCode == 13) {
                loginBtn();
            }
        });
    });
    function loginBtn() {
        if ($("input[name='mobileOrCompanyName']").val() == '') {
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
            url: 'c_login.htm',
            type: 'POST',
            data: $("#loginForm").serialize(),
            success: function (data) {
                if (data == 0) {
                    $(".alert-block").text('输入不能为空');
                    $(".alert-block").show();
                } else if (data == 2) {
                    $(".alert-block").text('验证码错误');
                    $(".alert-block").show();
                } else if (data == 3) {
                    $(".alert-block").text('账号不存在');
                    $(".alert-block").show();
                } else if (data == 4) {
                    $(".alert-block").text('账号被禁用');
                    $(".alert-block").show();
                } else if (data == 5) {
                    $(".alert-block").text('账号或密码错误');
                    $(".alert-block").show();
                } else {
                    window.location.href = "c_index.htm";
                }
            }
        });
    }
    function sendCode(obj) {
        if ($("input[name='phone']").val() == '') {
            showerror("手机号码不能为空");
            return;
        }
        $.ajax({
            url: 'c_tosendforgetcode.htm',
            type: 'POST',
            data: {
                phone: $("input[name='phone']").val()
            },
            success: function (data) {
                if (data == -1) {
                    showerror('手机号码不正确')
                    return;
                }
                if (data == 0) {
                    showerror('验证码发送失败');
                }
                if (data == 1) {
                    $(obj).attr("onclick", "");
                    var timer = setTimeout(timeDown, 1000);
                    var i = 60;

                    function timeDown() {
                        $(obj).text("重新发送(" + i + "s)");
                        i--;
                        if (i == 0) {
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
    function forgetPassword() {
        if (!$("#forgetPasswordForm").valid()) {
            return;
        }
        $.ajax({
            url: 'c_forgetpassword.htm',
            type: 'POST',
            data: $("#forgetPasswordForm").serialize(),
            success: function (data) {//-1 两次密码不一致 -2 验证码错误 -3 手机号码不正确 -4 不存在该用户 1成功
                if (data == 1) {
                    showerror("修改成功");
                    return;
                }
                if (data == -1) {
                    showerror("两次密码不一致");
                } else if (data == -2) {
                    showerror("验证码错误");
                } else if (data == -3) {
                    showerror("手机号码不正确");
                } else if (data == -4) {
                    showerror("不存在该用户");
                } else {
                    showerror("网络错误");
                }
            }
        });
    }
</script>
</body>
</html>

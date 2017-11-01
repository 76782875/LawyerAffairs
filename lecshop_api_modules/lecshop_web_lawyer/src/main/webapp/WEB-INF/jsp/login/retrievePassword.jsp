<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <title>忘记密码</title>
  <link rel="stylesheet" href="<%=basePath %>/res/dist/css/framework7.ios.min.css">
  <link rel="stylesheet" href="<%=basePath %>/res/dist/css/framework7.ios.colors.min.css">
  <link rel="stylesheet" href="<%=basePath %>/res/dist/css/framework7-icons.css">
  <link rel="stylesheet" href="<%=basePath %>/res/css/my-app.css">
</head>

<body>
<div class="views">
  <input type="hidden" id="basePath" value="<%=basePath %>">
  <div class="page">
    <div class="page-content">
      <div class="login-screen-title">忘记密码</div>
      <form>
        <div class="list-block">
          <ul>
            <li class="item-content">
              <div class="item-inner">
                <div class="item-title label">手机号码</div>
                <div class="item-input" style="display:flex;justify-content:space-between">
                  <input style="display:flex; flex:1" type="number" pattern="[0-9]*" id="mobile" placeholder="请输入手机号码">
                  <a class="phone_code" onclick="sendCode(this)" style="display:flex;line-height:43px" href="javascript:;">获取验证码</a>
                </div>
              </div>
            </li>
            <li class="item-content">
              <div class="item-inner">
                <div class="item-title label">手机验证码</div>
                <div class="item-input">
                  <input id="code" type="text" pattern="[0-9]*" style="display:flex; flex:1" placeholder="请输入手机验证码">
                </div>
              </div>
            </li>
            <li class="item-content">
              <div class="item-inner">
                <div class="item-title label">新密码</div>
                <div class="item-input">
                  <input id="password" type="password"  placeholder="请输入密码">
                </div>
              </div>
            </li>
            <li class="item-content">
              <div class="item-inner">
                <div class="item-title label">确认新密码</div>
                <div class="item-input">
                  <input id="newpassword" type="password"   placeholder="请再次输入密码">
                </div>
              </div>
            </li>
          </ul>
        </div>
        <div class="list-block inset">
          <ul>
            <li><a href="#" onclick="updatePassword()" class="list-button item-link color-red">提 交</a></li>
          </ul>
        </div>
      </form>
    </div>
  </div>
</div>
</body>
<script type="text/javascript" src="<%=basePath %>/res/dist/js/framework7.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/login/retrievePassword.js"></script>
</html>
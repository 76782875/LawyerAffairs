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
  <title>绑定账号</title>
  <link rel="stylesheet" href="<%=basePath %>/res/dist/css/framework7.ios.min.css">
  <link rel="stylesheet" href="<%=basePath %>/res/dist/css/framework7.ios.colors.min.css">
  <link rel="stylesheet" href="<%=basePath %>/res/dist/css/framework7-icons.css">
  <link rel="stylesheet" href="<%=basePath %>/res/css/my-app.css">
</head>

<body>
<div class="views">
  <input type="hidden" id="basePath" value="<%=basePath %>">
  <div class="toolbar">
    <div class="toolbar-inner"><a href="#" onclick="retrievePassword()" class="link">忘记密码?</a><a href="#" onclick="toRegister()" class="open-popover link">完善信息</a></div>
  </div>
  <div class="page">
    <div class="page-content">
      <div class="login-screen-title">绑定账号</div>
      <form id="loginForm">
        <div class="list-block">
          <ul>
            <li class="item-content">
              <div class="item-inner">
                <div class="item-title label">手机号码</div>
                <div class="item-input">
                  <input id="mobile" type="number" pattern="[0-9]*"  placeholder="请输入手机号码">
                </div>
              </div>
            </li>
            <li class="item-content">
              <div class="item-inner">
                <div class="item-title label">密码</div>
                <div class="item-input">
                  <input id="password"  type="password"  placeholder="请输入密码">
                </div>
              </div>
            </li>
          </ul>
        </div>
        <div class="list-block inset">
          <ul>
            <li><a href="#" onclick="lawyerBind()" class="list-button item-link color-red">绑 定</a></li>
          </ul>
        </div>
      </form>
    </div>
  </div>
</div>
</body>
<script type="text/javascript" src="<%=basePath %>/res/dist/js/framework7.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/js/common.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/js/login/login.js"></script>
</html>
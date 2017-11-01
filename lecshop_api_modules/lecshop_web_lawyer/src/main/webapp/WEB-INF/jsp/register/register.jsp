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
  <title>注册</title>
  <link rel="stylesheet" href="<%=basePath %>/res/dist/css/framework7.ios.min.css">
  <link rel="stylesheet" href="<%=basePath %>/res/dist/css/framework7.ios.colors.min.css">
  <link rel="stylesheet" href="<%=basePath %>/res/dist/css/framework7-icons.css">
  <link rel="stylesheet" href="<%=basePath %>/res/css/my-app.css">
</head>

<body>
<div class="views">
  <input type="hidden" id="basePath" value="<%=basePath %>">
  <div class="toolbar">
    <div class="toolbar-inner"><a href="#" onclick="retrievePassword()" class="link">忘记密码?</a><a href="#" onclick="toLogin()" class="open-popover link">去绑定</a></div>
  </div>
  <div class="page">
    <div class="page-content">
      <div class="login-screen-title">完善信息</div>
      <form enctype="multipart/form-data"  method="post" id="registerLawyer">
        <div class="list-block">
          <ul>
            <li class="item-content">
              <div class="item-inner">
                <div class="item-title label">手机号码</div>
                <div class="item-input" style="display:flex;justify-content:space-between">
                  <input style="display:flex; flex:1" type="number" pattern="[0-9]*" id="mobile" placeholder="请输入手机号码">
                  <a class="phone_code" onclick="sendCode(this)"  style="display:flex;line-height:43px" href="javascript:;">获取验证码</a>
                </div>
              </div>
            </li>
            <li class="item-content">
              <div class="item-inner">
                <div class="item-title label">手机验证码</div>
                <div class="item-input">
                  <input type="number" pattern="[0-9]*" id="validateCode" placeholder="请输入手机验证码">
                </div>
              </div>
            </li>
            <li class="item-content">
              <div class="item-inner">
                <div class="item-title label">执业证号</div>
                <div class="item-input">
                  <input type="number" pattern="[0-9]*" id="code" placeholder="请输入执业证号">
                </div>
              </div>
            </li>
            <li class="item-content">
              <div class="item-inner">
                <div class="item-title label">密码</div>
                <div class="item-input">
                  <input type="password" id="password" placeholder="请输入密码">
                </div>
              </div>
            </li>
            <li class="item-content">
              <div class="item-inner">
                <div class="item-title label">确认密码</div>
                <div class="item-input">
                  <input type="password" id="newpassword" placeholder="请再次输入密码">
                </div>
              </div>
            </li>
            <li class="item-content">
              <div class="item-inner">
                <div class="item-title label">所在律所</div>
                <div class="item-input">
                  <input type="text" id="lawyersPlace" placeholder="请输入所在律所">
                </div>
              </div>
            </li>
            <li class="item-content">
              <div class="item-inner">
                <div class="item-title label">联系地址</div>
                <div class="item-input">
                  <input type="text" id="address" placeholder="请输入联系地址">
                </div>
              </div>
            </li>
            <li class="item-content">
              <div class="item-inner">
                <div class="item-title label">律师证</div>
                <div class="item-input">
                  <input type="hidden" id="picurl">
                  <input onchange="uploadPic()" type="file" accept="image/*;capture=camera" name="image">
                </div>
              </div>
            </li>
          </ul>
        </div>
        <div class="list-block inset">
          <ul>
            <li><a href="#"  onclick="registerLawyer()" class="list-button item-link color-red">立即完善绑定</a></li>
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
<script type="text/javascript" src="<%=basePath %>/res/js/register/register.js"></script>
</html>
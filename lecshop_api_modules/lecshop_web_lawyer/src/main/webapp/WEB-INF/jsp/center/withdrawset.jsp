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
  <title>提现设置</title>
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
      <div class="content-block-title">设置提现支付宝账号及姓名</div>
        <form class="list-block">
          <ul>
            <li>
              <div class="item-content">
                <div class="item-inner">
                  <div class="item-input">
                    <input id="account" type="text" placeholder="支付宝账号" value="${withdrawset.account}">
                  </div>
                </div>
              </div>
            </li>
            <li>
              <div class="item-content">
                <div class="item-inner">
                  <div class="item-input">
                    <input id="name" type="text" placeholder="姓名" value="${withdrawset.name}">
                  </div>
                </div>
              </div>
            </li>
          </ul>
        </form>
      <div class="list-block inset">
        <ul>
          <li><a href="#" onclick="updatewitidrawset()" class="list-button item-link color-red">保 存</a></li>
        </ul>
      </div>
    </div>
  </div>
  <jsp:include page="../common/foot.jsp">
    <jsp:param name="from" value="center"/>
  </jsp:include>
</div>
</body>
<script type="text/javascript" src="<%=basePath %>/res/dist/js/framework7.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/js/common.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/js/center/userinfo.js"></script>
</html>
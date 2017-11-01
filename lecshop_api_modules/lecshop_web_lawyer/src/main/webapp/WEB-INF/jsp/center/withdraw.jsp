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
  <title>提现</title>
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
      <div class="content-block-title">提示:提现日期为每月${profitSet.withdrawTime}号</div>
      <form>
        <div class="list-block">
          <ul>
            <li class="item-content">
              <div class="item-inner">
                <div class="item-title label">提现支付宝账号</div>
                <div class="item-input">${withdrawset.account}</div>
              </div>
            </li>
            <li class="item-content">
              <div class="item-inner">
                <div class="item-title label">提现姓名</div>
                <div class="item-input">${withdrawset.name}</div>
              </div>
            </li>
            <li class="item-content">
              <div class="item-inner">
                <div class="item-title label">提现金额</div>
                <div class="item-input">
                  <input type="text"  id="money" placeholder="请输入提现金额">
                </div>
              </div>
            </li>
          </ul>
        </div>
        <div class="list-block inset">
          <ul>
            <li><a id="tx_btn" href="#" onclick="applywithdraw()" class="list-button item-link color-red">去提现</a></li>
          </ul>
        </div>
      </form>
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
<script type="text/javascript" src="<%=basePath %>/res/js/center/withdraw.js"></script>

</html>
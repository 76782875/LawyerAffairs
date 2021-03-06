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
  <title>提现记录</title>
  <link rel="stylesheet" href="<%=basePath %>/res/dist/css/framework7.ios.min.css">
  <link rel="stylesheet" href="<%=basePath %>/res/dist/css/framework7.ios.colors.min.css">
  <link rel="stylesheet" href="<%=basePath %>/res/dist/css/framework7-icons.css">
  <link rel="stylesheet" href="<%=basePath %>/res/css/my-app.css">
</head>

<body>
<div class="views toolbar-through">
  <input type="hidden" id="basePath" value="<%=basePath %>">

  <div class="view view-main">
  <div class="page">
    <div class="page-content infinite-scroll" data-distance="100">
      <div class="timeline">
        <div class="timeline-item">
          <div class="timeline-item-divider"></div>
          <div class="timeline-item-content"></div>
        </div>
      </div>
      <div class="infinite-scroll-preloader">
        <div class="preloader"></div>
      </div>
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
<script type="text/javascript" src="<%=basePath %>/res/js/center/withdrawlists.js"></script>

</html>
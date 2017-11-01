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
  <title>修改文书详情</title>
  <link rel="stylesheet" href="<%=basePath %>/res/dist/css/framework7.ios.min.css">
  <link rel="stylesheet" href="<%=basePath %>/res/dist/css/framework7.ios.colors.min.css">
  <link rel="stylesheet" href="<%=basePath %>/res/dist/css/framework7-icons.css">
  <link rel="stylesheet" href="<%=basePath %>/res/css/my-app.css">
</head>

<body>
<div class="views toolbar-through">
  <input type="hidden" id="docId" value="${docId}">
  <input type="hidden" id="basePath" value="<%=basePath %>">
  <div class="view view-main">
    <div class="page">
      <div class="page-content">
        <div class="timeline">
        </div>
      </div>
    </div>
  </div>
  <jsp:include page="../common/foot.jsp">
    <jsp:param name="from" value="affairs"/>
  </jsp:include>
</div>
</body>
<script type="text/javascript" src="<%=basePath %>/res/dist/js/framework7.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/js/common.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/js/affairs/modifydocdetail.js"></script>

</html>
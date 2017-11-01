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
  <title>编辑</title>
  <link rel="stylesheet" href="<%=basePath %>/res/dist/css/framework7.ios.min.css">
  <link rel="stylesheet" href="<%=basePath %>/res/dist/css/framework7.ios.colors.min.css">
  <link rel="stylesheet" href="<%=basePath %>/res/dist/css/framework7-icons.css">
  <link rel="stylesheet" href="<%=basePath %>/res/css/my-app.css">
</head>

<body>
<div class="views">
  <input type="hidden" id="disputeId" value="${disputeId}">
  <input type="hidden" id="basePath" value="<%=basePath %>">
  <input type="hidden" id="code" value="${code}">

  <div class="view view-main">
    <div class="pages">
      <div class="page">
        <div class="page-content">
          <form   id="disputeform"  enctype="multipart/form-data"  method="post" class="list-block">
            <div class="content-block-title" id="xFileText"></div>
            <div class="content-block">
              <div class="row">
                <div class="col-50"><label style="display:block" for="xFile"><div class="button button-big active" style="background:#1fadc5">上传</div><input id="xFile" style="position:absolute;z-index:-9;opacity:0" type="file" accept="*/*;capture=camera" name="image" onchange="uploadFile()"></label></div>
                <div class="col-50"><a href="#" onclick="todownload()" class="button button-big">下载</a></div>
              </div>
            </div>
          </form>
          <p id="as"></p>
          <div class="list-block inset">
            <ul>
              <li><a href="javascript" id="save_btn" onclick="save()" class="list-button item-link color-red">保 存</a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <jsp:include page="../common/foot.jsp">
      <jsp:param name="from" value="affairs"/>
    </jsp:include>
  </div>
</div>
</body>
<script type="text/javascript" src="<%=basePath %>/res/js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/dist/js/framework7.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/js/common.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/js/affairs/editdisputeone.js"></script>
</html>
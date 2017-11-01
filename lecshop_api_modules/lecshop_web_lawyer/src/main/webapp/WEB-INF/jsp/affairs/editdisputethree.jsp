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
  <title>案件详情</title>
  <link rel="stylesheet" href="<%=basePath %>/res/dist/css/framework7.ios.min.css">
  <link rel="stylesheet" href="<%=basePath %>/res/dist/css/framework7.ios.colors.min.css">
  <link rel="stylesheet" href="<%=basePath %>/res/dist/css/framework7-icons.css">
  <link rel="stylesheet" href="<%=basePath %>/res/css/kitchen-sink.css">
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
          <form class="list-block">
            <div class="content-block">
              <div style="padding:0; margin-right:-15px; width:auto" class="content-block-inner">
                <div style="margin:0" class="list-block">
                  <ul style="border-top:none">
                    <li>
                      <div class="item-content">
                        <div class="item-inner">
                          <div class="item-input">
                            <input type="text" placeholder="Date Time" readonly="readonly" id="ks-picker-date"/>
                          </div>
                        </div>
                      </div>
                    </li>
                  </ul>
                </div>
                <div id="ks-picker-date-container">
                </div>
              </div>
            </div>
          </form>
          <p id="as"></p>
          <div class="list-block inset">
            <ul>
              <li><a href="javascript" onclick="save()" id="save_btn" class="list-button item-link color-red">保 存</a></li>
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
<script type="text/javascript" src="<%=basePath %>/res/js/kitchen-sink.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/js/affairs/editdisputethree.js"></script>
</html>
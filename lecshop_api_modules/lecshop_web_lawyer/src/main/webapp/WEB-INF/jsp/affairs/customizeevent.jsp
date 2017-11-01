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
  <title>重大事项自定义</title>
  <link rel="stylesheet" href="<%=basePath %>/res/dist/css/framework7.ios.min.css">
  <link rel="stylesheet" href="<%=basePath %>/res/dist/css/framework7.ios.colors.min.css">
  <link rel="stylesheet" href="<%=basePath %>/res/dist/css/framework7-icons.css">
  <link rel="stylesheet" href="<%=basePath %>/res/css/my-app.css">
</head>

<body>
<input type="hidden" id="eventId" value="${eventId}">
<input type="hidden" id="basePath" value="<%=basePath %>">
<div class="views toolbar-through">
  <div class="toolbar">
    <div class="toolbar-inner"><a href="<%=basePath %>/toimportantmatter.htm" class="link">取消</a><a href="#" onclick="save()" class="open-popover link">保存</a></div>
  </div>
  <div class="page">
    <div class="page-content">
      <div class="list-block accordion-list">
        <ul>
          <li class="accordion-item">
            <div class="item-content">
              <div class="item-inner">
                <div class="item-title"><input type="text" name="" placeholder="请输入阶段"></div>
              </div>
              <div class="list_toolbar one">
                <a href="javasrcipt:;" onclick="add(this)"><i class="f7-icons">add_round</i></a>
                <a href="javasrcipt:;" onclick="cut(this)"><i class="f7-icons">trash</i></a>
              </div>
            </div>
            <div class="list-block">
              <ul>
                <li class="two_box">
                  <div class="item-content">
                    <div class="item-media"></div>
                    <div class="item-inner">
                      <div class="item-title"><input type="text" name="" placeholder="请输入事项"></div>
                    </div>
                  </div>
                  <div class="list_toolbar two">
                    <a href="javasrcipt:;" onclick="add(this)"><i class="f7-icons">add_round</i></a>
                    <a href="javasrcipt:;" onclick="cut(this)"><i class="f7-icons">trash</i></a>
                  </div>
                </li>
              </ul>
            </div>
          </li>
        </ul>
      </div>
    </div>
  </div>
</div>
</body>
<script type="text/javascript" src="<%=basePath %>/res/js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/js/common.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/dist/js/framework7.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/js/affairs/customizeevent.js"></script>
</html>
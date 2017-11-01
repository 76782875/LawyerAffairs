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
  <title>编辑重大事项详情</title>
  <link rel="stylesheet" href="<%=basePath %>/res/dist/css/framework7.ios.min.css">
  <link rel="stylesheet" href="<%=basePath %>/res/dist/css/framework7.ios.colors.min.css">
  <link rel="stylesheet" href="<%=basePath %>/res/dist/css/framework7-icons.css">
  <link rel="stylesheet" href="<%=basePath %>/res/css/kitchen-sink.css">
  <link rel="stylesheet" href="<%=basePath %>/res/css/my-app.css">
</head>

<body>
<div class="views">
  <input type="hidden" id="matterId" value="${matterId}">
  <input type="hidden" id="detailId" value="${detailId}">
  <input type="hidden" id="basePath" value="<%=basePath %>">
  <div class="view view-main">
    <div class="pages">
      <div class="page">
        <div class="page-content">
          <form  id="eventform"  enctype="multipart/form-data"  method="post"  class="list-block">
            <ul>
              <li>
                <div class="item-content">
                  <div class="item-inner">
                    <div class="item-input">
                      <input id="mytext" type="text" placeholder="请输入">
                    </div>
                  </div>
                </div>
              </li>
            </ul>
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
            <div class="content-block-title" id="xFileText"></div>
            <div class="content-block">
              <div class="row">
                <div class="col-50"><label style="display:block" for="xFile"><div class="button button-big active" style="background:#1fadc5">上传</div><input id="xFile" onchange="uploadFile()" style="position:absolute;z-index:-9;opacity:0" type="file" accept="*/*" name="image"></label></div>
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
    <div class="toolbar tabbar tabbar-labels">
      <div class="toolbar-inner">
        <a href="#" class="tab-link"><i class="f7-icons">home</i><span class="tabbar-label">首页</span></a>
        <a href="#" class="tab-link"><i class="f7-icons">document_text</i><span class="tabbar-label">抢单</span></a>
        <a href="#" class="tab-link active"><i class="f7-icons">folder</i><span class="tabbar-label">我的事务</span></a>
        <a href="#" class="tab-link"><i class="f7-icons">person</i><span class="tabbar-label">个人中心</span></a>
      </div>
    </div>
  </div>
</div>
</body>
<script type="text/javascript" src="<%=basePath %>/res/js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/dist/js/framework7.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/js/kitchen-sink.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/js/common.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/js/affairs/editeventdetail.js"></script>

</html>
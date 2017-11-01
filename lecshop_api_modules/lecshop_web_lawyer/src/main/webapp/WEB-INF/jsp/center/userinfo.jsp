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
  <title>基本信息</title>
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
      <form id="userform" enctype="multipart/form-data"  method="post">
        <div class="list-block">
          <ul>
            <li>
              <div class="item-link smart-select">
                <div class="item-content">
                  <label class="item-inner" for="xFile">
                    <div class="item-title">头像</div>
                    <div class="item-after" style="max-height:none">
                      <img style="width:3em; height:3em" id="url" class="profile-pic"
                           src=""/>
                    </div>
                    <input id="xFile" onchange="uploadPic()" style="position:absolute;z-index:-9;opacity:0" type="file"
                           accept="image/*;capture=camera" name="image">
                  </label>
                </div>
              </div>
            </li>
            <li>
              <div class="item-link smart-select">
                <div class="item-content">
                  <div class="item-inner">
                    <div class="item-title">手机号码</div>
                    <div class="item-after" id="mobile"></div>
                  </div>
                </div>
              </div>
            </li>
            <li>
              <a href="<%=basePath %>/toupdatename.htm" class="item-link smart-select">
                <div class="item-content">
                  <div class="item-inner">
                    <div class="item-title">姓名</div>
                    <div class="item-after" id="name"></div>
                  </div>
                </div>
              </a>
            </li>
            <li>
              <a href="<%=basePath %>/toupdateqq.htm" class="item-link smart-select">
                <div class="item-content">
                  <div class="item-inner">
                    <div class="item-title">QQ</div>
                    <div class="item-after" id="qq"></div>
                  </div>
                </div>
              </a>
            </li>
            <li>
              <a href="<%=basePath %>/toupdateemail.htm" class="item-link smart-select">
                <div class="item-content">
                  <div class="item-inner">
                    <div class="item-title">邮箱</div>
                    <div class="item-after" id="email">54874559785</div>
                  </div>
                </div>
              </a>
            </li>
            <li>
              <a href="<%=basePath %>/toupdatelawyerplace.htm" class="item-link smart-select">
                <div class="item-content">
                  <div class="item-inner">
                    <div class="item-title">律所</div>
                    <div class="item-after" id="lawyersPlace"></div>
                  </div>
                </div>
              </a>
            </li>
            <li>
              <a href="<%=basePath %>/toupdateaddress.htm" class="item-link smart-select">
                <div class="item-content">
                  <div class="item-inner">
                    <div class="item-title">地址</div>
                    <div class="item-after" id="address"></div>
                  </div>
                </div>
              </a>
            </li>
            <li>
              <div class="item-link smart-select">
                <div class="item-content">
                  <label class="item-inner">
                    <div class="item-title">律师证</div>
                    <div class="item-after" style="max-height:none">
                      <img style="width:3em; height:3em" id="lawyerPic">
                    </div>
                  </label>
                </div>
              </div>
            </li>
            <li>
              <a href="#" class="item-link smart-select">
                <div class="item-content">
                  <div class="item-inner">
                    <div class="item-title">执业证号</div>
                    <div class="item-after" id="code"></div>
                  </div>
                </div>
              </a>
            </li>
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
<script type="text/javascript" src="<%=basePath %>/res/js/center/userinfo.js"></script>

</html>
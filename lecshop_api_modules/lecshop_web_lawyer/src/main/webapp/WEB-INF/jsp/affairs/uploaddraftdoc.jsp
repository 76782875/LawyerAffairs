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
  <title>上传文书</title>
  <link rel="stylesheet" href="<%=basePath %>/res/dist/css/framework7.ios.min.css">
  <link rel="stylesheet" href="<%=basePath %>/res/dist/css/framework7.ios.colors.min.css">
  <link rel="stylesheet" href="<%=basePath %>/res/dist/css/framework7-icons.css">
  <link rel="stylesheet" href="<%=basePath %>/res/css/my-app.css">
</head>

<body>
<div class="views">
  <input type="hidden" value="${docId}" id="docId">
  <div class="view view-main">
    <div class="pages">
      <div class="page">
        <div class="page-content">
          <form class="list-block" id="letterform"  enctype="multipart/form-data"  method="post">
            <ul>
              <li>
                <div class="item-content">
                  <div class="item-inner">
                    <div class="item-input">
                      <input type="text" id="desc" placeholder="请输入">
                    </div>
                  </div>
                </div>
              </li>
            </ul>
            <div class="content-block-title" id="xFileText"></div>
            <div class="content-block">
              <div class="row">
                <div class="col-50"><label style="display:block" for="xFile"><div class="button button-big active" style="background:#1fadc5">上传</div><input id="xFile" style="position:absolute;z-index:-9;opacity:0" onchange="uploadFile()" type="file" accept="*/*" name="image"></label></div>
              </div>
            </div>
          </form>
          <p id="as"></p>
          <div class="list-block inset">
            <ul>
              <li><a href="#" onclick="uploadLetter()" id="save_btn" class="list-button item-link color-red">保 存</a></li>
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
<script>

  function uploadLetter() {

    if ($("#desc").val() == "" && $("#xFileText").html() == "") {
      window.location.href = "<%=basePath %>/todraftdocdetails.htm?docId=" + $("#docId").val();
      return;
    }

    $.ajax({
      url: '<%=basePath %>/adddaraftdetail.htm',
      type: 'post',
      data: {
        'docId': $("#docId").val(),
        'desc': $("#desc").val(),
        'docUrl': $("#xFileText").html()
      },
      success: function (res) {
        window.location.href = "<%=basePath %>/todraftdocdetails.htm?docId=" + $("#docId").val()
      }
    });
  }

  // 上传文件
  function uploadFile() {
      $.ajax({
          url: '<%=basePath %>/uploadtolocal.htm',
          type: 'POST',
          cache: false,
          data: new FormData($('#letterform')[0]),
          processData: false,
          contentType: false
      }).done(function (res) {
          $("#xFileText").html(res.res);
      });
  }
</script>
</html>
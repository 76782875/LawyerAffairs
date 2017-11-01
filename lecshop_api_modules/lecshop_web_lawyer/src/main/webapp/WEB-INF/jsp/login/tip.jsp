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
  <title>待审核</title>
  <link rel="stylesheet" href="<%=basePath %>/res/dist/css/framework7.ios.min.css">
  <link rel="stylesheet" href="<%=basePath %>/res/dist/css/framework7.ios.colors.min.css">
  <link rel="stylesheet" href="<%=basePath %>/res/dist/css/framework7-icons.css">
  <link rel="stylesheet" href="<%=basePath %>/res/css/my-app.css">
</head>

<body>
<div class="views">
  <div class="page">
    <div class="page-content">
      <svg style="width: 8em;height: 8em;display:block;color:#ccc;margin:30% auto 10% auto;vertical-align: middle;fill: currentColor;overflow: hidden;" viewBox="0 0 1024 1024"><path d="M277.4784 190.3104c-27.84256 0-50.2784 22.51264-50.2784 50.46784 0 27.94496 22.43584 50.4576 50.2784 50.4576h440.71936c27.648 0 50.28352-22.51264 50.28352-50.4576 0-27.95008-22.43584-50.46784-50.28352-50.46784h-86.13888V80.72704c0-44.43648-35.86048-80.42496-80.13312-80.42496H443.7504c-44.27264 0-80.128 35.98848-80.128 80.42496V190.3104H277.4784zM497.83296 45.53728c29.25056 0 53.0944 23.92576 53.0944 53.28896 0 29.35296-23.84896 53.27872-53.0944 53.27872-29.24544 0-53.07904-23.92576-53.07904-53.27872 0-29.35808 23.8336-53.28896 53.07904-53.28896zM259.2768 374.67648h477.12768v78.43328H259.2768z" fill="" p-id="2005"></path><path d="M833.14688 192.16384v393.96864h78.14144V192.16384c0-53.90848-43.6992-97.7664-97.408-97.7664h-126.32576v78.43328h126.33088c10.61888-0.00512 19.26144 8.67328 19.26144 19.33312zM162.52928 896.2048V192.16384a19.32288 19.32288 0 0 1 19.26656-19.33824H310.6304V94.39744H181.79584c-53.7088 0-97.408 43.85792-97.408 97.7664V896.2048c0 53.90848 43.6992 97.76128 97.408 97.76128h316.04736v-78.43328H181.79584a19.31776 19.31776 0 0 1-19.26656-19.328z" fill="" p-id="2006"></path><path d="M259.2768 574.59712h281.1904v78.43328H259.2768zM756.48 613.54496c-113.34144 0-205.22496 91.88352-205.22496 205.22496 0 113.34144 91.88352 205.22496 205.22496 205.22496 113.34144 0 205.22496-91.88352 205.22496-205.22496 0-113.34144-91.88352-205.22496-205.22496-205.22496z m124.5184 260.27008h-146.72384a14.08 14.08 0 0 1-14.08-14.08c0-0.0768 0.02048-0.14336 0.02048-0.22016 0-0.0768-0.02048-0.14336-0.02048-0.22016v-132.24448a14.52032 14.52032 0 0 1 29.04064 0v118.59968h131.76832a14.08 14.08 0 1 1-0.00512 28.16512z"></path></svg>
      <div class="login-screen-title">${msg}</div>
    </div>
  </div>
</div>
</body>
<script type="text/javascript" src="<%=basePath %>/res/dist/js/framework7.min.js"></script>
</html>
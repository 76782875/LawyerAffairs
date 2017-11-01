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
  <title>错误</title>
  <link rel="stylesheet" href="<%=basePath %>/res/dist/css/framework7.ios.min.css">
  <link rel="stylesheet" href="<%=basePath %>/res/dist/css/framework7.ios.colors.min.css">
  <link rel="stylesheet" href="<%=basePath %>/res/dist/css/framework7-icons.css">
  <link rel="stylesheet" href="<%=basePath %>/res/css/my-app.css">
</head>

<body>
<div class="views">
  <div class="page">
    <div class="page-content">
      <svg class="icon" style="width: 12em;height: 12em;display:block;color:#666;margin:30% auto 0 auto;vertical-align: middle;fill: currentColor;overflow: hidden;" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3149"><path d="M351.529 797.708s163.833-30.917 278.5 4.5l-141.283-140.25-137.217 135.75z" fill="" p-id="3150"></path><path d="M450.324 706.278c-19.75 0-35.76 16.01-35.76 35.76s16.01 35.76 35.76 35.76 35.76-16.01 35.76-35.76-16.01-35.76-35.76-35.76z m0 45.521c-5.391 0-9.76-4.37-9.76-9.76 0-5.391 4.37-9.76 9.76-9.76s9.76 4.37 9.76 9.76-4.37 9.76-9.76 9.76zM525.574 706.278c-19.75 0-35.76 16.01-35.76 35.76s16.01 35.76 35.76 35.76 35.76-16.01 35.76-35.76-16.01-35.76-35.76-35.76z m0 45.521c-5.391 0-9.76-4.37-9.76-9.76 0-5.391 4.37-9.76 9.76-9.76s9.76 4.37 9.76 9.76-4.37 9.76-9.76 9.76z" fill="#FFFFFF" p-id="3151"></path><path d="M489.746 656.479L349.654 794.443l-281.873-46.5 107.646-78.375z" fill="#F3CD9E" p-id="3152"></path><path d="M499.946 223.792l-11.202 433.703-314.319 13.088 57.063-338.5z" fill="#E7C295" p-id="3153"></path><path d="M488.744 657.495L630.91 799.911l325.25-13-106.254-82.286-361.137-48.126z" fill="#E7AA7E" p-id="3154"></path><path d="M499.946 223.792l316.339 169.166 33.834 311.834-361.375-47.297z" fill="#D9A573" p-id="3155"></path><path d="M282.529 578.958l104-15-4 41.5-104 10z" fill="" p-id="3156"></path><path d="M630.896 799.897l-142.15-142.418v4.479l141.283 140.25 326.131-13v-2.297zM349.654 794.443l-281.873-46.5v2.639l283.748 47.126 137.217-135.75-0.002-4.463z" fill="#DDAA72" p-id="3157"></path><path d="M655.029 533.458l45.333 15.834-1.333 55.666 29.5 6.5-49.5 36-60-60.5 37.667 8.5zM602.529 399.125v-17.833l-18.5-7.833v18l-12.5-5.333v12.833l12.5 5.333v77.667l59.667-52.333v-11.833l-41.167-18.668z m-2.667 54.958v-43.25l31.542 13.25-31.542 30zM752.961 463.209v-15.442l-16.019-6.783v15.586l-10.823-4.618v11.112l10.823 4.618v67.25l51.664-45.314v-10.246l-35.645-16.163z m-2.309 47.587v-37.449l27.311 11.473-27.311 25.976zM713.588 449.017c-7.983-24.391-26.913-40.086-42.282-35.056-15.369 5.03-21.356 28.88-13.373 53.271 7.983 24.391 26.913 40.086 42.282 35.056s21.356-28.88 13.373-53.271z m-19.484 39.814c-7.497 1.89-17.041-10.325-21.317-27.283s-1.664-32.238 5.833-34.128c7.497-1.89 17.041 10.325 21.317 27.283s1.665 32.237-5.833 34.128z" fill="" p-id="3158"></path></svg>
      <div class="login-screen-title" style="font-size:1.5em; color:#666">意外错误</div>
    </div>
  </div>
</div>
</body>
<script type="text/javascript" src="<%=basePath %>/res/dist/js/framework7.min.js"></script>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
</head>

<div class="toolbar tabbar tabbar-labels">
    <% String a = request.getParameter("from");
        request.setAttribute("from", a);
    %>
    <div class="toolbar-inner">
        <a href="<%=basePath %>/index.htm" class="tab-link <c:if test="${from=='index'}"> active </c:if>"><i class="f7-icons">home</i><span class="tabbar-label">首页</span></a>
        <a href="<%=basePath %>/toappointmeet.htm" class="tab-link <c:if test="${from=='meet'}"> active </c:if>"><i class="f7-icons">document_text</i><span class="tabbar-label">抢单</span></a>
        <a href="<%=basePath %>/tobusinessaffairs.htm" class="tab-link <c:if test="${from=='affairs'}"> active </c:if>"><i class="f7-icons">folder</i><span class="tabbar-label">我的事务</span></a>
        <a href="<%=basePath %>/tocenter.htm" class="tab-link <c:if test="${from=='center'}"> active </c:if>"><i class="f7-icons">person</i><span class="tabbar-label">个人中心</span></a>
    </div>
</div>
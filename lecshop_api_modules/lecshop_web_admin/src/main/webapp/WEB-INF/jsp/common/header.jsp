<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<header class="header white-bg">
    <c:if test="${firstMenus!=1}">
        <div class="sidebar-toggle-box">
            <div data-original-title="Toggle Navigation" data-placement="right" class="icon-reorder tooltips"></div>
        </div>
    </c:if>
    <a href="index.htm" class="logo"><img src="<%=basePath %>/res/img/logo.png" style="max-height: 35px"> </a>
    <ul class="nav_ace">
        <c:forEach items="${adminMenu}" var="firstMenu">
            <c:choose>
                <c:when test="${firstMenu.childMenu!=null}">
                    <c:forEach items="${firstMenu.childMenu}" var="secondMenu" varStatus="i">
                        <c:choose>
                            <c:when test="${secondMenu.childMenu!=null}">
                                <c:if test="${i.index==0 }">
                                    <c:forEach items="${secondMenu.childMenu}" var="thirdMenu" varStatus="j">
                                        <c:if test="${j.index==0 }">
                                            <c:set var="url" value="${thirdMenu.url}?firstMenus=${firstMenu.authorityId}&secondMenus=${secondMenu.authorityId}&thirdMenus=${thirdMenu.authorityId}"> </c:set>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                            </c:when>
                            <c:otherwise>
                                <c:set var="url" value="${secondMenu.url}?firstMenus=${firstMenu.authorityId}&secondMenus=${secondMenu.authorityId}"> </c:set>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:set var="url" value="${firstMenu.url}?firstMenus=1"> </c:set>
                </c:otherwise>
            </c:choose>
            <li <c:if test="${firstMenu.authorityId ==1}"> style="display: none" </c:if> >
                <a nid="${firstMenu.authorityId}"<c:if test="${firstMenu.authorityId==firstMenus}"> class="cur" </c:if> href="${url}">
                        ${firstMenu.name}
                </a>
            </li>
        </c:forEach>
    </ul>
    <div class="top-nav">
        <ul class="nav pull-right top-menu">
            <li class="dropdown">
                <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                    <img alt="" src="<%=basePath %>/res/img/avatar1_small.jpg">
                    <span class="username">${ADMIN_LOGIN_SESSION_KEY.username}</span>
                    <b class="caret"></b>
                </a>
                <ul class="dropdown-menu extended logout">
                    <div class="log-arrow-up"></div>
                    <li><a href="javascript:;" onclick="updatePassword()"><i class="icon-refresh"></i>修改密码</a></li>
                    <li><a href="javascript:;" onclick="signOut()"><i class="icon-key"></i>退出</a></li>
                </ul>
            </li>
        </ul>
    </div>
</header>

<div class="modal fade" id="out_dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">系统提示</h4>
            </div>
            <div class="modal-body">确定退出系统吗！</div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-success" type="button" onclick="signOutConfirm()">确定</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="update_pwd_dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">修改密码</h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal tasi-form set-form">
                    <form id="updatePwdForm">
                        <div class="form-group">
                            <label class="col-sm-3 control-label"><span class="label_red">*</span>原密码: </label>
                            <div class="col-sm-6">
                                <input type="password" class="form-control required" value="" name="oldPassword">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label"><span class="label_red">*</span>新密码: </label>
                            <div class="col-sm-6">
                                <input type="password" class="form-control required" value="" name="newPassword" id="newPassword">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label"><span class="label_red">*</span>重新输入: </label>
                            <div class="col-sm-6">
                                <input type="password" class="form-control required" value="" name="reNewPassword" equalto="#newPassword">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                <button class="btn btn-success" type="button" onclick="updatePasswordConfirm()">确定</button>
            </div>
        </div>
    </div>
</div>
<script src="<%=basePath %>/res/js/jquery.js"></script>
<script type="text/javascript">
    $(function () {
        var params = '<li><a href="#">' + $(".cur").text() + '</a></li> <li><a href="#">' + $(".sub-menu .active>span").text() + '</a></li> <li class="active">' + $(".sub-menu .sub .active>a").text() + '</li>';
        $(".breadcrumb").html(params);
    });

    //退出按钮点击事件
    function signOut() {
        $("#out_dialog").modal('show');
    }

    //退出确认按钮点击事件
    function signOutConfirm() {
        window.location.href = "tologin.htm";
    }

    //修改密码点击事件
    function updatePassword() {
        $("#update_pwd_dialog").modal('show');
    }

    //修改密码确认按钮点击事件
    function updatePasswordConfirm() {
        if (!$("#updatePwdForm").valid()) {
            return;
        }
        var oldPassword = transform($("input[name='oldPassword']").val());
        var newPassword = transform($("input[name='newPassword']").val());
        var reNewPassword = transform($("input[name='reNewPassword']").val());
        LSFetch({
            url: '<%=basePath%>/updatemanagerpassword.htm',
            data: {
                oldPassword: transform($("input[name='oldPassword']").val()),
                newPassword: transform($("input[name='newPassword']").val()),
                reNewPassword: transform($("input[name='reNewPassword']").val())
            },
            success: function (data) {//修改返回码 0 没有登录 -1 输入不能为空 1 修改成功 2 两次输入密码不一致 3 原始密码不正确
                if (data == 0) {
                    showerror("修改失败,请先登录");
                    return;
                }
                if (data == -1) {
                    showerror("修改失败,输入不能为空");
                    return;
                }
                if (data == 1) {
                    $("#update_pwd_dialog").modal('hide');
                    showerror("修改成功");
                    return;
                }
                if (data == 2) {
                    showerror("修改失败,两次输入密码不一致");
                    return;
                }
                if (data == 3) {
                    showerror("修改失败,原密码不正确");
                } else {
                    showerror("修改失败,异常");
                }
            }
        });

    }

    //处理密码特殊字符
    function transform(key) {
        const dataDictionary = {
            '+': '%2B',
            ' ': '%20',
            '/': '%2F',
            '?': '%3F',
            '%': '%25',
            '#': '%23',
            '&': '%26',
            '=': '%3D',
        };
        key = key.replace(" ", "");
        var newString = '';
        for (var a in key) {
            if (dataDictionary[key[a]] !== undefined && dataDictionary[key[a]] !== '') {
                newString = newString + dataDictionary[key[a]];
            } else {
                newString = newString + key[a];
            }
        }
        return newString;
    }
</script>

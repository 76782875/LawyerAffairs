<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/res/assets/bootstrap-fileupload/bootstrap-fileupload.css">
<header class="header white-bg">
    <c:if test="${firstMenus!=1}">
        <div class="sidebar-toggle-box">
            <div data-original-title="Toggle Navigation" data-placement="right" class="icon-reorder tooltips"></div>
        </div>
    </c:if>
    <a href="javascript:window.location.href=window.location.href" class="logo"><img
            src="<%=basePath %>/res/img/logo.png" style="max-height: 35px"> </a>
    <ul class="nav_ace">
        <c:forEach items="${companyMenu}" var="firstMenu">
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
            <li>
                <a nid="${firstMenu.authorityId}"
                   <c:if test="${firstMenu.authorityId==firstMenus}">class="cur"</c:if>
                   href="${url}"> ${firstMenu.name}</a>
            </li>
        </c:forEach>
    </ul>
    <div class="top-nav">
        <ul class="nav pull-right top-menu">
            <li class="dropdown">
                <a data-toggle="dropdown" class="dropdown-toggle">
                    <img alt="" src="<%=basePath %>/res/img/avatar1_small.jpg"/>
                    <span class="username">${COMPANY_LOGIN_SESSION_KEY.name}</span>
                    <b class="caret"></b>
                </a>
                <ul class="dropdown-menu extended logout">
                    <div class="log-arrow-up"></div>
                    <li><a href="javascript:;" onclick="updatePassword()"><i class="icon-refresh"></i>修改密码</a></li>
                    <li><a href="javascript:;" onclick="showPersonCentre()"><i class="icon-user"></i>个人中心</a></li>
                    <li><a href="javascript:;" onclick="signOut()"><i class="icon-key"></i>退出</a></li>
                </ul>
            </li>
        </ul>
    </div>
    <div class="nav notify-row" style="float:right">
        <ul class="nav top-menunav top-menu" id="letterStationClickId">
            <li>
                <a class="dropdown-toggle" href="#">
                    <i class="icon-envelope-alt"></i>
                    <span class="badge bg-important" id="unreadLetterStationCount"></span>
                </a>
            </li>
        </ul>
    </div>
</header>
<div class="modal fade" id="letter_station">
    <div class="modal-dialog" style="width:950px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">站内信</h4>
            </div>
            <div class="modal-body">
                <div class="adv-table clearfix">
                    <table class="display table table-bordered table-striped" id="stationdatatables">
                        <thead>
                        <tr>
                            <th>内容</th>
                            <th width="130px" style="text-align: center">创建时间</th>
                            <th width="140px" style="text-align: center">操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-success" type="button">确认</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="out_dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">系统提示</h4>
            </div>
            <div class="modal-body">确定退出系统吗！</div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-success" type="button" onclick="signOutConfirm()">确定
                </button>
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
                                <input type="password" class="form-control required" value="" name="newPassword"
                                       id="newPassword">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label"><span class="label_red">*</span>重新输入: </label>
                            <div class="col-sm-6">
                                <input type="password" class="form-control required" value="" name="reNewPassword"
                                       equalto="#newPassword">
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
<div class="modal fade" id="info_dialog">
    <input type="hidden" id="disputesTypeId"/>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">个人中心</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal tasi-form" id="updateLicence" enctype="multipart/form-data" method="post">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">营业执照：</label>
                        <div class="col-sm-5">
                            <div class="fileupload fileupload-new" data-provides="fileupload">
                                <div class="fileupload-new thumbnail" style="height: 60px;">
                                    <img id="licence" src="<%=basePath %>/res/img/no_img.png" alt=""/>
                                </div>
                                <div class="fileupload-preview fileupload-exists thumbnail"
                                     style="max-width: 200px; max-height: 80px; line-height: 20px;"></div>
                                <div>
                                    <span class="btn btn-white btn-file tooltips" data-placement="right"
                                          data-original-title="建议100*40px">
                                        <span class="fileupload-new"><i class="icon-paper-clip"></i> 选择本地图片</span>
                                        <span class="fileupload-exists"><i class="icon-undo"></i> 重新上传</span>
                                        <input type="file" name="image" class="default" onchange="uploadPic()"/>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
                <form class="form-horizontal tasi-form set-form" id="updateForm">
                    <input type="hidden" name="businessLicence"/>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">公司名称：</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" name="name"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">电话：</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" name="mobile"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">地址：</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" name="address"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">传真：</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" name="fax"/>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                <button class="btn btn-success" type="button" onclick="updateInfo()">保存</button>
            </div>
        </div>
    </div>
</div>
<script src="<%=basePath %>/res/js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/assets/bootstrap-fileupload/bootstrap-fileupload.js"></script>
<script type="text/javascript">
    // 将新增的弹出层放入内存中
    var html = $("#update_pwd_dialog").html();
    $(document).ready(function () {
        LSFetch({
            url: '<%=basePath%>/c_queryunreadletterstationcount.htm',
            success: function (res) {
                $("#unreadLetterStationCount").text(res);
            }
        });
    });

    //退出按钮点击事件
    function signOut() {
        $("#out_dialog").modal('show');
    }

    //退出确认按钮点击事件
    function signOutConfirm() {
        window.location.href = "c_tologin.htm";
    }

    function updatePassword() {
        $("#update_pwd_dialog").html(html);
        $("#update_pwd_dialog").modal('show');
    }

    // 上传营业执照图片
    function uploadPic() {
        $.ajax({
            url: '<%=basePath%>/c_uploadtoupyun.htm',
            type: 'POST',
            cache: false,
            data: new FormData($("#updateLicence")[0]),
            processData: false,
            contentType: false
        }).done(function (res) {
            $("#licence").attr("src", res);
            $('input[name="businessLicence"]').val(res);
        }).fail(function (res) {
        });
    }

    function showPersonCentre() {
        LSFetch({
            url: '<%=basePath%>/c_personcentre.htm',
            success: function (res) {
                $('input[name="name"]').val(res.name);
                $('input[name="mobile"]').val(res.mobile);
                $('input[name="address"]').val(res.companyInfo.address);
                $('input[name="fax"]').val(res.companyInfo.fax);
                $('#licence').attr("src", res.companyInfo.businessLicence);
                $('input[name="businessLicence"]').val(res.companyInfo.businessLicence);
                $("#info_dialog").modal("show");
            }
        });
    }

    function updateInfo() {
        LSFetch({
            url: '<%=basePath%>/c_savepersoncentre.htm',
            data: $("#updateForm").serialize(),
            success: function (data) {
                if (data == 1) {
                    $("#info_dialog").modal('hide');
                    showerror("保存成功");
                }
            }
        });
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
            url: '<%=basePath%>/c_updatepassword.htm',
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

    function loadLetterStation() {
        $("#stationdatatables").lsDataTable({
            "ajax": function (data, callback) {
                LSFetch({
                    url: '<%=basePath%>/c_queryletterstation.htm',
                    data: dataTableAjaxData(data),
                    success: function (res) {
                        callback(res);
                    }
                });
            },
            "columns": [
                {
                    "name": "content", "data": "content"
                },
                {
                    "name": "createTime", "data": "createTime"
                },
                {
                    "data": function (row) {
                        var htmls = '<div class="operation_box">';
                        if (row.status == '0') {
                            htmls += '<button class="btn btn-primary btn-xs edit_btn" onclick="setReaded(' + row.id + ')"><i class="icon-eye-open"></i> 设为已读</button>';
                        }
                        htmls += '<button class="btn btn-danger btn-xs del_btn" onclick="deleteLetterStation(' + row.id + ')"><i class="icon-trash"></i> 删除</button></div>';
                        return htmls;
                    }
                }
            ],
            ordering: false
        });
    }

    // 刷新列表
    var refresh = function () {
        $('#stationdatatables').DataTable().ajax.reload();
    };
    var num = 0;
    $("#letterStationClickId").click(function () {
        if (num === 0) {
            loadLetterStation();
            num++;
        } else {
            refresh();
        }
        $("#letter_station").modal('show');
    });

    function deleteLetterStation(id) {
        LSFetch({
            url: '<%=basePath%>/c_deleteletterstation.htm',
            data: {
                id: id
            },
            success: function (res) {
                if (res === 1) {
                    refresh();
                }
            }
        });
    }

    function setReaded(id) {
        LSFetch({
            url: '<%=basePath%>/c_setletterstationasreaded.htm',
            data: {
                id: id
            },
            success: function (res) {
                if (res == 1) {
                    refresh();
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
            '=': '%3D'
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

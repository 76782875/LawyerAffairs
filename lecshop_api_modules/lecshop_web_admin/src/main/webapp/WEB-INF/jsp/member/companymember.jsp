<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>公司会员 - 律师弟弟</title>
    <!-- Bootstrap core CSS -->
    <link href="<%=basePath %>/res/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath %>/res/css/bootstrap-reset.css" rel="stylesheet">
    <!--external css-->
    <link href="<%=basePath %>/res/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link href="<%=basePath %>/res/assets/advanced-datatable/media/css/demo_table.css" rel="stylesheet"/>
    <link href="<%=basePath%>/res/css/dataTables/dataTables.bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/res/assets/bootstrap-datetimepicker/css/datetimepicker.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/res/assets/bootstrap-fileupload/bootstrap-fileupload.css">
    <!-- Custom styles for this template -->
    <link href="<%=basePath %>/res/css/style.css" rel="stylesheet">
    <link href="<%=basePath %>/res/css/style-responsive.css" rel="stylesheet"/>
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 tooltipss and media queries -->
    <!--[if lt IE 9]>
    <script src="<%=basePath %>/res/js/html5shiv.js"></script>
    <script src="<%=basePath %>/res/js/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<section id="container">
    <input type="hidden" value="<%=basePath%>" id="basePath">
    <!-- 引用上侧 -->
    <jsp:include page="../common/header.jsp"></jsp:include>
    <!-- 引用左侧 -->
    <jsp:include page="../common/left.jsp"></jsp:include>
    <!--main content start-->
    <section id="main-content">
        <section class="wrapper site-min-height">
            <div class="row">
                <div class="col-lg-12">
                    <!--breadcrumbs start -->
                    <ul class="breadcrumb">
                        <li><a href="#">会员</a></li>
                        <li class="active">公司会员</li>
                    </ul>
                    <!--breadcrumbs end -->
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <section class="panel">
                        <header class="panel-heading"> 公司会员</header>
                        <div class="panel-body">
                            <div class="form-inline m-bot15 clearfix search-form">
                                <form id="queryform">
                                    <div class="form-group">
                                        <label class="control-label">公司名称</label>
                                        <div class="clearfix">
                                            <input type="text" class="form-control" name="name"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">用户名</label>
                                        <div class="clearfix">
                                            <input type="text" class="form-control" name="userName"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">手机号码</label>
                                        <div class="clearfix">
                                            <input type="text" class="form-control" name="mobile"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">&nbsp;</label>
                                        <div class="clearfix">
                                            <button type="button" class="btn btn-info" onclick="refreshDataTable()">搜索</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="clearfix m-bot15" style="padding-bottom: 30px; border-bottom: solid 1px #eee;">
                                <button type="button" onclick="adduser()" class="btn btn-primary"><i class="icon-plus-sign"></i> 添加</button>
                                <button type="button" class="btn btn-danger" onclick="toDeleteAll()"><i class="icon-trash"></i> 删除</button>
                                <button type="button" onclick="showLetterStation()" class="btn btn-info"><i class="icon-comments"></i> 发送通知</button>
                            </div>
                            <div class="adv-table clearfix">
                                <table class="display table table-bordered table-striped" id="dataTable">
                                    <thead>
                                    <tr>
                                        <th width="35"><input type="checkbox"></th>
                                        <th>用户名</th>
                                        <th>手机号码</th>
                                        <th>公司名称</th>
                                        <th>状态</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </section>
    </section>
    <!--main content end-->
    <!--footer start-->
    <jsp:include page="../common/foot.jsp"></jsp:include>
    <!--footer end-->
</section>
<!-- Modal -->
<div class="modal fade" id="del_dialog">
    <input type="hidden" id="deleteId"/>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">确认提示</h4>
            </div>
            <div class="modal-body">确定要删除此会员吗？</div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                <button class="btn btn-success" type="button" onclick="deleteCompanyMember()">确定</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="add_dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">添加会员</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal tasi-form" id="addForm" enctype="multipart/form-data" method="post">
                    <div class="form-group">
                        <label class="col-sm-3 control-label"><span class="label_red">*</span>手机号码：</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control required digits" id="addMobile" oninput='this.value=this.value.replace(/\D/gi,"")' maxlength="11"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label"><span class="label_red">*</span>密码：</label>
                        <div class="col-sm-9">
                            <input type="password" class="form-control required" id="addNickName"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">状态：</label>
                        <div class="col-sm-2">
                            <select class="form-control" id="addStatus">
                                <option value="0">启用</option>
                                <option value="1">禁用</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label"><span class="label_red">*</span>公司名称：</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control required specstr" id="addName"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">社会信用代码：</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="addCode"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">公司行业：</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="addIndustry"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">公司地址：</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="addAddress"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">传真：</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="addFax"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">营业执照：</label>
                        <div class="col-sm-9">
                            <div class="fileupload fileupload-new" data-provides="fileupload">
                                <div class="fileupload-new thumbnail" style="height: 60px;">
                                    <img id="addLicenceImg" src="<%=basePath %>/res/img/no_img.png" alt=""/></div>
                                <div class="fileupload-preview fileupload-exists thumbnail" style="max-width: 200px; max-height: 80px; line-height: 20px;"></div>
                                <div>
                                    <span class="btn btn-white btn-file tooltips" data-placement="right" data-original-title="建议100*40px">
                                        <span class="fileupload-new"><i class="icon-paper-clip"></i> 选择本地图片</span>
                                        <span class="fileupload-exists"><i class="icon-undo"></i> 重新上传</span>
                                        <input type="file" class="default" name="image" onchange="uploadPic('addForm')"/>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                <button class="btn btn-success" type="button" onclick="addCompany()">保存</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="show_dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">查看会员</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal tasi-form">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">用户名：</label>
                        <div class="col-sm-9">
                            <input type="text" class="nickname form-control" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">状态：</label>
                        <div class="col-sm-2">
                            <select class="status form-control" disabled="disabled">
                                <option value="0">启用</option>
                                <option value="1">禁用</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">公司名称：</label>
                        <div class="col-sm-9">
                            <input type="text" class="companyname form-control" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">社会信用代码：</label>
                        <div class="col-sm-9">
                            <input type="text" class="code form-control" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">公司行业：</label>
                        <div class="col-sm-9">
                            <input type="text" class="industry form-control" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">公司地址：</label>
                        <div class="col-sm-9">
                            <input type="text" class="address form-control" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">传真：</label>
                        <div class="col-sm-9">
                            <input type="text" class="fax form-control" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">营业执照：</label>
                        <div class="col-sm-9">
                            <div class="businessLicence fileupload fileupload-new" data-provides="fileupload">
                                <div class="fileupload-new thumbnail" style="height: 60px;">
                                    <img src="<%=basePath %>/res/img/no_img.png" alt=""/>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-success" type="button">确定</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="edit_dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">编辑会员</h4>
                <input type="hidden" value="" id="userIdHidden">
            </div>
            <div class="modal-body">
                <form class="form-horizontal tasi-form" id="updateBusinessLicence" enctype="multipart/form-data" method="post">
                    <div class="form-group">
                        <label class="col-sm-3 control-label"><span class="label_red">*</span>用户名：</label>
                        <div class="col-sm-9">
                            <input type="text" class="nickname form-control required" id="userNickName"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">状态：</label>
                        <div class="col-sm-2">
                            <select class="form-control status" id="userStatus">
                                <option value="0">启用</option>
                                <option value="1">禁用</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label"><span class="label_red">*</span>公司名称：</label>
                        <div class="col-sm-9">
                            <input type="text" class="companyname form-control required" id="editCompanyMemberName"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">社会信用代码：</label>
                        <div class="col-sm-9">
                            <input type="text" class="code form-control" id="editCompanyMemberCode"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">公司行业：</label>
                        <div class="col-sm-9">
                            <input type="text" class="industry form-control" id="editCompanyMemberIndustry"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">公司地址：</label>
                        <div class="col-sm-9">
                            <input type="text" class="address form-control" id="editCompanyMemberAddress"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">传真：</label>
                        <div class="col-sm-9">
                            <input type="text" class="fax form-control" id="editCompanyMemberFax"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <input type="hidden" id="companyMemberId"/>
                        <label class="col-sm-3 control-label">营业执照：</label>
                        <div class="col-sm-9">
                            <div class="businessLicence fileupload fileupload-new" data-provides="fileupload">
                                <div class="fileupload-new thumbnail" style="height: 60px;">
                                    <img id="editLicenceImg" src="<%=basePath %>/res/img/no_img.png" alt=""/>
                                </div>
                                <div class="fileupload-preview fileupload-exists thumbnail" style="max-width: 200px; max-height: 80px; line-height: 20px;"></div>
                                <div>
                                    <span class="btn btn-white btn-file tooltips" data-placement="right" data-original-title="建议100*40px">
                                        <span class="fileupload-new"><i class="icon-paper-clip"></i> 选择本地图片</span>
                                        <span class="fileupload-exists"><i class="icon-undo"></i> 重新上传</span>
                                        <input type="file" class="default" name="image"/>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                <button class="btn btn-success" type="button" onclick="updateCompanyMember()">保存</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="sms_dialog">
    <input type="hidden" id="ids"/>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">发送通知</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal tasi-form" id="letterStationForm">
                    <div class="form-group">
                        <label class="col-sm-3 control-label"><span class="label_red">*</span>标题：</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control required" id="title"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label"><span class="label_red">*</span>内容：</label>
                        <div class="col-sm-9">
                            <textarea type="text" class="form-control required" id="content"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                <button class="btn btn-success" type="button" onclick="sendLetterStation()">确定</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="vip_dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">修改VIP等级</h4>
                <input type="hidden" name="companyId">
            </div>
            <div class="modal-body form-horizontal tasi-form">
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="label_red">*</span>VIP等级：</label>
                    <div class="col-sm-9">
                        <select class="form-control" title="" name="vipTyp">
                            <option value="0">试用期法律服务</option>
                            <option value="1">创业期法律服务</option>
                            <option value="2">经营期法律服务</option>
                            <option value="3">爆发期法律服务</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><span class="label_red">*</span>修改说明：</label>
                    <div class="col-sm-9">
                        <textarea type="text" class="form-control required" title="" name="remark"></textarea>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                <button class="btn btn-success" type="button" onclick="editVipSave()">确定</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<!-- js placed at the end of the document so the pages load faster -->
<script src="<%=basePath %>/res/js/jquery.js"></script>
<script src="<%=basePath %>/res/js/bootstrap.min.js"></script>
<script class="include" type="text/javascript" src="<%=basePath %>/res/js/jquery.dcjqaccordion.2.7.js"></script>
<script src="<%=basePath %>/res/js/jquery.scrollTo.min.js"></script>
<script src="<%=basePath %>/res/js/jquery.nicescroll.js"></script>
<script src="<%=basePath %>/res/js/respond.min.js"></script>
<!--common script for all pages-->
<script src="<%=basePath %>/res/js/common/common-scripts.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/underscore-min.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/assets/datatables/jquery.dataTables.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/assets/datatables/dataTables.bootstrap.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/common/ls.datatables.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/common/common.js"></script>
<!--script for this page only-->
<script type="text/javascript" src="<%=basePath %>/res/assets/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/assets/bootstrap-fileupload/bootstrap-fileupload.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/pagejs/companymember.js"></script>
</body>
</html>

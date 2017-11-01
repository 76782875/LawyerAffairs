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
    <title>律师会员 - 乐商商城</title>
    <!-- Bootstrap core CSS -->
    <link href="<%=basePath %>/res/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath %>/res/css/bootstrap-reset.css" rel="stylesheet">
    <!--external css-->
    <link href="<%=basePath %>/res/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link href="<%=basePath %>/res/assets/advanced-datatable/media/css/demo_table.css" rel="stylesheet"/>
    <link href="<%=basePath%>/res/css/dataTables/dataTables.bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css"
          href="<%=basePath %>/res/assets/bootstrap-datetimepicker/css/datetimepicker.css"/>
    <link rel="stylesheet" type="text/css"
          href="<%=basePath %>/res/assets/bootstrap-fileupload/bootstrap-fileupload.css">
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
                        <li><a href="#">会员管理</a></li>
                        <li class="active">律师会员</li>
                    </ul>
                    <!--breadcrumbs end -->
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <section class="panel">
                        <header class="panel-heading"> 律师会员</header>
                        <div class="panel-body">
                            <div class="form-inline m-bot15 clearfix search-form">
                                <form id="queryform">
                                    <input type="hidden" value="" name="status">
                                    <div class="form-group">
                                        <label class="control-label">姓名</label>
                                        <div class="clearfix">
                                            <input type="text" class="form-control" name="name">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">手机号码</label>
                                        <div class="clearfix">
                                            <input type="text" class="form-control" name="mobile">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">&nbsp;</label>
                                        <div class="clearfix">
                                            <button type="button" class="btn btn-info" onclick="refreshDataTable()">搜索
                                            </button>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">选择律师状态：</label>
                                        <div class="clearfix">
                                            <select class="form-control" name="status" onchange="selectStatus()">
                                                <option value="">全部</option>
                                                <option value="2">待审核</option>
                                                <option value="0">审核通过</option>
                                                <option value="3">审核未通过</option>
                                            </select>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="clearfix m-bot15" style="padding-bottom: 30px; border-bottom: solid 1px #eee;">
                                <button type="button" onclick="addLawyer()" class="btn btn-primary"><i
                                        class="icon-plus-sign"></i> 添加
                                </button>
                                <button type="button" class="btn btn-danger" onclick="toDeleteAll()"><i
                                        class="icon-trash"></i> 删除
                                </button>
                            </div>
                            <div class="adv-table clearfix">
                                <table class="display table table-bordered table-striped" id="dataTable">
                                    <thead>
                                    <tr>
                                        <th width="35"><input type="checkbox"></th>
                                        <th>姓名</th>
                                        <th>手机号码</th>
                                        <th>执业证号</th>
                                        <th>所在地</th>
                                        <th>状态</th>
                                        <th>类型</th>
                                        <th>搜索</th>
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
                <button class="btn btn-success" type="button" onclick="deleteLawyer()">确定</button>
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
            <div class="modal-body form-horizontal tasi-form">
                <form id="addFormOne">
                    <div class="form-group">
                        <label class="col-sm-3 control-label"><span class="label_red">*</span>手机号码：</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control required digits specstr" id="lawMobile" oninput='this.value=this.value.replace(/\D/gi,"")' maxlength="11"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label"><span class="label_red">*</span>姓名：</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control required specstr" id="lawName"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">执业证号：</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="lawCode"/>
                        </div>
                    </div>
                </form>
                <div class="form-group"></div>
                <div class="form-group">
                    <form class="form-horizontal tasi-form" id="addCodePic" enctype="multipart/form-data" method="post">
                        <label class="col-sm-3 control-label">律师证：</label>
                        <div class="col-sm-9">
                            <div class="fileupload fileupload-new" data-provides="fileupload">
                                <div class="fileupload-new thumbnail" style="height: 60px;">
                                    <img src="<%=basePath %>/res/img/no_img.png" alt="" name="lawyerPic"/>
                                </div>
                                <div class="fileupload-preview fileupload-exists thumbnail" style="max-width: 200px; max-height: 80px; line-height: 20px;"></div>
                                <div>
                                    <span class="btn btn-white btn-file tooltips" data-placement="right" data-original-title="建议100*40px">
                                        <span class="fileupload-new"><i class="icon-paper-clip"></i> 选择本地图片</span>
                                        <span class="fileupload-exists"><i class="icon-undo"></i> 重新上传</span>
                                        <input type="file" class="default" name="image" onchange="uploadPic('addCodePic')"/>
                                        <input type="hidden" data-type="addCodePic" value="">
                                    </span>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <form id="addFormTwo">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">联系地址：</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="lawAddress"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">所在地：</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="lawPlace"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">状态：</label>
                        <div class="col-sm-2">
                            <select class="form-control" id="lawStatus">
                                <option value="0">启用</option>
                                <option value="1">禁用</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">类型：</label>
                        <div class="col-sm-2">
                            <select class="form-control" id="lawType">
                                <option value="1">平台律师</option>
                                <option value="2">非平台律师</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">搜索：</label>
                        <div class="col-sm-2">
                            <select class="form-control" id="searchForbid">
                                <option value="0">启用</option>
                                <option value="1">禁用</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">QQ：</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control digits" id="lawQQ"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">邮箱：</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control email" id="lawEmail"/>
                        </div>
                    </div>
                </form>
                <div class="form-group"></div>
                <div class="form-group">
                    <form class="form-horizontal tasi-form" id="addHeadPic" enctype="multipart/form-data" method="post">
                        <label class="col-sm-3 control-label">头像：</label>
                        <div class="col-sm-9">
                            <div class="fileupload fileupload-new" data-provides="fileupload">
                                <div class="fileupload-new thumbnail" style="height: 60px;">
                                    <img src="<%=basePath %>/res/img/no_img.png" alt="" name="lawyerHead"/>
                                </div>
                                <div class="fileupload-preview fileupload-exists thumbnail" style="max-width: 200px; max-height: 80px; line-height: 20px;"></div>
                                <div>
                                <span class="btn btn-white btn-file tooltips" data-placement="right" data-original-title="建议100*40px">
                                    <span class="fileupload-new"><i class="icon-paper-clip"></i> 选择本地图片</span>
                                    <span class="fileupload-exists"><i class="icon-undo"></i> 重新上传</span>
                                    <input type="file" class="default" name="image" onchange="uploadPic('addHeadPic')"/>
                                    <input type="hidden" data-type="addHeadPic" value="">
                                </span>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-success" type="button" onclick="addLawyerSave()">保存</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="show_dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">查看明细</h4>
            </div>
            <div class="modal-body form-horizontal tasi-form">
                <div class="form-group">
                    <label class="col-sm-3 control-label">姓名：</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" name="lawyerName" readonly="readonly"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">执业证号：</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" name="lawyerCode" readonly="readonly"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">律师证：</label>
                    <div class="col-sm-9">
                        <div class="fileupload fileupload-new" data-provides="fileupload">
                            <div class="fileupload-new thumbnail" style="height: 60px;">
                                <img id="showLawPicImage" src="<%=basePath %>/res/img/no_img.png" name="LawyerPicture"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">联系地址：</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" name="lawyerAddress" readonly="readonly"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">所在地：</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" name="lawyersPlace" readonly="readonly"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">状态：</label>
                    <div class="col-sm-2">
                        <select class="form-control" name="lawyerStatus" disabled="true">
                            <option value="0">启用</option>
                            <option value="1">禁用</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">类型：</label>
                    <div class="col-sm-2">
                        <select class="form-control" name="lawyerType" disabled="true">
                            <option value="1">平台律师</option>
                            <option value="2">非平台律师</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">搜索：</label>
                    <div class="col-sm-2">
                        <select class="form-control" name="searchForbid" disabled="true">
                            <option value="0">启用</option>
                            <option value="1">禁用</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">QQ：</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" name="lawyerQQ" readonly="readonly"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">邮箱：</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" name="lawyerEmail" readonly="readonly"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">头像：</label>
                    <div class="col-sm-9">
                        <div class="fileupload fileupload-new" data-provides="fileupload">
                            <div class="fileupload-new thumbnail" style="height: 60px;">
                                <img name="lawyerHeadPic" src="<%=basePath %>/res/img/no_img.png"/>
                            </div>
                        </div>
                    </div>
                </div>
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
                <input type="hidden" id="lawyerId"/>
            </div>
            <div class="modal-body form-horizontal tasi-form">
                <form id="editForm1">
                    <div class="form-group">
                        <label class="col-sm-3 control-label"><span class="label_red">*</span>姓名：</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control required" id="lawyerName" name="lawyerName"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">执业证号：</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="lawyerCode" id="lawyerCode"/>
                        </div>
                    </div>
                </form>
                <div class="form-group"></div>
                <div class="form-group">
                    <form id="editCodePic" enctype="multipart/form-data" method="post">
                        <label class="col-sm-3 control-label">律师证：</label>
                        <div class="col-sm-9">
                            <div class="fileupload fileupload-new" data-provides="fileupload">
                                <div class="fileupload-new thumbnail" style="height: 60px;">
                                    <img id="lawPicImage" src="<%=basePath %>/res/img/no_img.png" alt="" name="LawyerPicture"/>
                                </div>
                                <div class="fileupload-preview fileupload-exists thumbnail" style="max-width: 200px; max-height: 80px; line-height: 20px;"></div>
                                <div>
                                    <span class="btn btn-white btn-file tooltips" data-placement="right" data-original-title="建议100*40px">
                                        <span class="fileupload-new"><i class="icon-paper-clip"></i> 选择本地图片</span>
                                        <span class="fileupload-exists"><i class="icon-undo"></i> 重新上传</span>
                                        <input type="file" class="default" name="image" onchange="uploadPic('editCodePic')"/>
                                        <input type="hidden" value="" data-type="editCodePic">
                                    </span>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <form id="editForm2">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">联系地址：</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="lawyerAddress" id="lawyerAddress"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">所在地：</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="lawyersPlace" id="lawyersPlace"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">状态：</label>
                        <div class="col-sm-2">
                            <select class="form-control" name="lawyerStatus" id="lawyerStatus">
                                <option value="0">启用</option>
                                <option value="1">禁用</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">类型：</label>
                        <div class="col-sm-2">
                            <select class="form-control" id="lawyerType" name="lawyerType">
                                <option value="1">平台律师</option>
                                <option value="2">非平台律师</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">搜索：</label>
                        <div class="col-sm-2">
                            <select class="form-control" id="lawyerSearchForbid" name="searchForbid">
                                <option value="0">启用</option>
                                <option value="1">禁用</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">QQ：</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control digits" name="lawyerQQ" id="lawyerQQ"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">邮箱：</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control email" name="lawyerEmail" id="lawyerEmail"/>
                        </div>
                    </div>
                </form>
                <div class="form-group"></div>
                <div class="form-group">
                    <form class="form-horizontal tasi-form" id="editHeadPic" enctype="multipart/form-data" method="post">
                        <label class="col-sm-3 control-label">头像：</label>
                        <div class="col-sm-9">
                            <div class="fileupload fileupload-new" data-provides="fileupload">
                                <div class="fileupload-new thumbnail" style="height: 60px;">
                                    <img id="lawHeadPic" name="lawyerHeadPic" src="<%=basePath %>/res/img/no_img.png" alt=""/>
                                </div>
                                <div class="fileupload-preview fileupload-exists thumbnail" style="max-width: 200px; max-height: 80px; line-height: 20px;"></div>
                                <div>
                                    <span class="btn btn-white btn-file tooltips" data-placement="right" data-original-title="建议100*40px">
                                        <span class="fileupload-new"><i class="icon-paper-clip"></i> 选择本地图片</span>
                                        <span class="fileupload-exists"><i class="icon-undo"></i> 重新上传</span>
                                        <input type="file" class="default" name="image" onchange="uploadPic('editHeadPic')"/>
                                        <input type="hidden" value="" data-type="editHeadPic"/>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                <button class="btn btn-success" type="button" onclick="updateLawyer()">保存</button>
            </div>
        </div>
    </div>
</div>
<!-- 审核律师 -->
<div class="modal fade" id="aud_dialog">
    <input type="hidden" id="auditId"/>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">审核律师</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal tasi-form" id="submitform">
                    <input type="hidden" name="storeId" id="storeInfoId" value="1148">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">审核：</label>
                        <div class="col-sm-2">
                            <select class="form-control" id="auditStatus">
                                <option value="0">通过</option>
                                <option value="3">打回</option>
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                <button class="btn btn-success" type="button" onclick="auditSave()">保存</button>
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
<script type="text/javascript" src="<%=basePath %>/res/assets/bootstrap-fileupload/bootstrap-fileupload.js"></script>
<script src="<%=basePath %>/res/pagejs/lawyermember.js"></script>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>公司纠纷 - 律师弟弟</title>
    <!-- Bootstrap core CSS -->
    <link href="<%=basePath %>/res/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath %>/res/css/bootstrap-reset.css" rel="stylesheet">
    <!--external css-->
    <link href="<%=basePath %>/res/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link href="<%=basePath %>/res/assets/advanced-datatable/media/css/demo_table.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/res/assets/bootstrap-fileupload/bootstrap-fileupload.css"/>
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
                        <li><a href="#">我的事务</a></li>
                        <li class="active">事务列表</li>
                        <input type="hidden" name="disputeIdHidden" value="${disputeId}">
                    </ul>
                    <!--breadcrumbs end -->
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <section class="panel">
                        <header class="panel-heading" style="text-align:left;">委托案件</header>
                        <div class="panel-body form-horizontal">
                            <div class="tab-content">
                                <div class="tab-pane active">
                                    <div class="panel-group " id="accordion1">
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion1" href="#collapseOne1-2-1">代理合同
                                                        <span style="font-size:12px;">[律师提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne1-2-1" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <a href="#" class="btn btn-success btn-primary" onclick="disputesDownLoad('001')">下载文件</a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion1" href="#collapseOne1-3-2">谈话笔录
                                                        <span style="font-size:12px;">[律师提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne1-3-2" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <a href="#" class="btn btn-success btn-primary" onclick="disputesDownLoad('002')">下载文件</a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion1" href="#collapseOne1-4-3">风险告知书
                                                        <span style="font-size:12px;">[律师提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne1-4-3" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <a href="#" class="btn btn-success btn-primary" onclick="disputesDownLoad('003')">下载文件</a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion1" href="#collapseOne1-5-4">授权委托书
                                                        <span style="font-size:12px;">[律师提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne1-5-4" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <a href="#" class="btn btn-success btn-primary" onclick="disputesDownLoad('004')">下载文件</a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion1" href="#collapseOne1-6-5">原告身份证
                                                        <span style="font-size:12px;">[客户提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne1-6-5" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <div class="fileupload fileupload-new" data-provides="fileupload">
                                                        <form action="c_disputesdetailupload.htm" enctype="multipart/form-data" method="post" id="005Form" target="fileIframe">
                                                            <div class="fileupload-preview fileupload-exists thumbnail fileClass" data-code="005" style="max-width: 200px; max-height: 80px; line-height: 20px;"></div>
                                                            <div>
                                                            <span class="btn btn-white btn-file">
                                                                <span class="fileupload-new"><i class="icon-paper-clip"></i> 选择本地文件</span>
                                                                <span class="fileupload-exists"><i class="icon-undo"></i> 重新上传</span>
                                                                <input type="file" class="default" name="fileUpload"/>
                                                                <input type="hidden" name="code" value="005">
                                                                <input type="hidden" name="disputeId" value="${disputeId}">
                                                            </span>
                                                            </div>
                                                        </form>
                                                    </div>
                                                    <button type="button" class="btn btn-primary" onclick="disputesDetailSave('005')">保存</button>
                                                    <a href="#" class="btn btn-success btn-primary" onclick="disputesDownLoad('005')">下载文件</a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion1" href="#collapseOne1-7-6">被告身份信息
                                                        <span style="font-size:12px;">[客户提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne1-7-6" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <div class="fileupload fileupload-new" data-provides="fileupload">
                                                        <form action="c_disputesdetailupload.htm" enctype="multipart/form-data" method="post" id="006Form" target="fileIframe">
                                                            <div class="fileupload-preview fileupload-exists thumbnail fileClass" data-code="006" style="max-width: 200px; max-height: 80px; line-height: 20px;"></div>
                                                            <div>
                                                            <span class="btn btn-white btn-file">
                                                                <span class="fileupload-new"><i class="icon-paper-clip"></i> 选择本地文件</span>
                                                                <span class="fileupload-exists"><i class="icon-undo"></i> 重新上传</span>
                                                                <input type="file" class="default" name="fileUpload"/>
                                                                <input type="hidden" name="code" value="006">
                                                                <input type="hidden" name="disputeId" value="${disputeId}">
                                                            </span>
                                                            </div>
                                                        </form>
                                                    </div>
                                                    <button type="button" class="btn btn-primary" onclick="disputesDetailSave('006')">保存</button>
                                                    <a href="#" class="btn btn-success btn-primary" onclick="disputesDownLoad('006')">下载文件</a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion1" href="#collapseOne1-8-7">证据材料
                                                        <span style="font-size:12px;">[客户提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne1-8-7" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <div class="fileupload fileupload-new" data-provides="fileupload">
                                                        <form action="c_disputesdetailupload.htm" enctype="multipart/form-data" method="post" id="007Form" target="fileIframe">
                                                            <div class="fileupload-preview fileupload-exists thumbnail fileClass" data-code="007" style="max-width: 200px; max-height: 80px; line-height: 20px;"></div>
                                                            <div>
                                                            <span class="btn btn-white btn-file">
                                                                <span class="fileupload-new"><i class="icon-paper-clip"></i> 选择本地文件</span>
                                                                <span class="fileupload-exists"><i class="icon-undo"></i> 重新上传</span>
                                                                <input type="file" class="default" name="fileUpload"/>
                                                                 <input type="hidden" name="code" value="007">
                                                                <input type="hidden" name="disputeId" value="${disputeId}">
                                                            </span>
                                                            </div>
                                                        </form>
                                                    </div>
                                                    <button type="button" class="btn btn-primary" onclick="disputesDetailSave('007')">保存</button>
                                                    <a href="#" class="btn btn-success btn-primary" onclick="disputesDownLoad('007')">下载文件</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
                <div class="col-md-12">
                    <section class="panel">
                        <header class="panel-heading" style="text-align:left;">立案</header>
                        <div class="panel-body form-horizontal">
                            <div class="tab-content">
                                <div class="tab-pane active">
                                    <div class="panel-group " id="accordion9">
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion9" href="#collapseOne9-10-1">诉状
                                                        <span style="font-size:12px;">[律师助理提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne9-10-1" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <a href="#" class="btn btn-success btn-primary" onclick="disputesDownLoad('011')">下载文件</a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion9" href="#collapseOne9-96-2">诉讼费金额
                                                        <span style="font-size:12px;">[律师助理提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne9-96-2" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <!--文本类型-->
                                                    <input class="form-control form-control-inline input-medium " readonly id="txt96" size="16" type="text" name="text" data-code="012" value="" placeholder="请输入诉讼费金额">
                                                    <!--文本类型-->
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
                <div class="col-md-12">
                    <section class="panel">
                        <header class="panel-heading" style="text-align:left;">查询立案</header>
                        <div class="panel-body form-horizontal">
                            <div class="tab-content">
                                <div class="tab-pane active">
                                    <div class="panel-group " id="accordion13">
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion13" href="#collapseOne13-14-1">经办人员电话
                                                        <span style="font-size:12px;">[律师助理提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne13-14-1" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <!--文本类型-->
                                                    <input class="form-control form-control-inline input-medium " name="text" data-code="021" readonly id="txt14" size="16" type="text" value="" placeholder="请输入经办人员电话">
                                                    <!--文本类型-->
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
                <div class="col-md-12">
                    <section class="panel">
                        <header class="panel-heading" style="text-align:left;">保全程序(如有)</header>
                        <div class="panel-body form-horizontal">
                            <div class="tab-content">
                                <div class="tab-pane active">
                                    <div class="panel-group " id="accordion15">
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion15" href="#collapseOne15-16-1">经办法官电话
                                                        <span style="font-size:12px;">[律师助理提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne15-16-1" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <!--文本类型-->
                                                    <input class="form-control form-control-inline input-medium " name="text" data-code="031" readonly id="txt16" size="16" type="text" value="" placeholder="请输入经办法官电话">
                                                    <!--文本类型-->
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion15" href="#collapseOne15-17-2">保全申请书
                                                        <span style="font-size:12px;">[律师助理提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne15-17-2" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <a href="#" class="btn btn-success btn-primary" onclick="disputesDownLoad('032')">下载文件</a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion15" href="#collapseOne15-18-3">担保书
                                                        <span style="font-size:12px;">[律师助理提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne15-18-3" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <a href="#" class="btn btn-success btn-primary" onclick="disputesDownLoad('033')">下载文件</a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion15" href="#collapseOne15-19-4">担保材料
                                                        <span style="font-size:12px;">[客户提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne15-19-4" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <div class="fileupload fileupload-new" data-provides="fileupload">
                                                        <form action="c_disputesdetailupload.htm" enctype="multipart/form-data" method="post" id="034Form" target="fileIframe">
                                                            <div class="fileupload-preview fileupload-exists thumbnail fileClass" data-code="034" style="max-width: 200px; max-height: 80px; line-height: 20px;"></div>
                                                            <div>
                                                            <span class="btn btn-white btn-file">
                                                                <span class="fileupload-new"><i class="icon-paper-clip"></i> 选择本地文件</span>
                                                                <span class="fileupload-exists"><i class="icon-undo"></i> 重新上传</span>
                                                                <input type="file" class="default" name="fileUpload"/>
                                                                <input type="hidden" name="code" value="034">
                                                                <input type="hidden" name="disputeId" value="${disputeId}">
                                                            </span>
                                                            </div>
                                                        </form>
                                                    </div>
                                                    <button type="button" class="btn btn-primary" onclick="disputesDetailSave('034')">保存</button>
                                                    <a href="#" class="btn btn-success btn-primary" onclick="disputesDownLoad('034')">下载文件</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
                <div class="col-md-12">
                    <section class="panel">
                        <header class="panel-heading" style="text-align:left;">查询办案法官</header>
                        <div class="panel-body form-horizontal">
                            <div class="tab-content">
                                <div class="tab-pane active">
                                    <div class="panel-group " id="accordion20">
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion20" href="#collapseOne20-21-1">姓名
                                                        <span style="font-size:12px;">[律师助理提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne20-21-1" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <!--文本类型-->
                                                    <input class="form-control form-control-inline input-medium " name="text" data-code="041" readonly id="txt21" size="16" type="text" value="" placeholder="请输入姓名">
                                                    <!--文本类型-->
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
                <div class="col-md-12">
                    <section class="panel">
                        <header class="panel-heading" style="text-align:left;">查询开庭情况</header>
                        <div class="panel-body form-horizontal">
                            <div class="tab-content">
                                <div class="tab-pane active">
                                    <div class="panel-group " id="accordion23">
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion23" href="#collapseOne23-24-1">开庭时间
                                                        <span style="font-size:12px;">[律师助理提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne23-24-1" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <!--文本类型-->
                                                    <input class="form-control form-control-inline input-medium " name="text" data-code="051" readonly id="txt24" size="16" type="text" value="" placeholder="请输入开庭时间">
                                                    <!--文本类型-->
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion23" href="#collapseOne23-25-2">电话
                                                        <span style="font-size:12px;">[律师助理提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne23-25-2" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <!--文本类型-->
                                                    <input class="form-control form-control-inline input-medium " name="text" data-code="052" readonly id="txt25" size="16" type="text" value="" placeholder="请输入电话">
                                                    <!--文本类型-->
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion23" href="#collapseOne23-26-3">传票
                                                        <span style="font-size:12px;">[律师助理提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne23-26-3" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <a href="#" class="btn btn-success btn-primary" onclick="disputesDownLoad('053')">下载文件</a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion23" href="#collapseOne23-27-4">对方证据材料
                                                        <span style="font-size:12px;">[客户提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne23-27-4" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <div class="fileupload fileupload-new" data-provides="fileupload">
                                                        <form action="c_disputesdetailupload.htm" enctype="multipart/form-data" method="post" id="054Form" target="fileIframe">
                                                            <div class="fileupload-preview fileupload-exists thumbnail fileClass" data-code="054" style="max-width: 200px; max-height: 80px; line-height: 20px;"></div>
                                                            <div>
                                                            <span class="btn btn-white btn-file">
                                                                <span class="fileupload-new"><i class="icon-paper-clip"></i> 选择本地文件</span>
                                                                <span class="fileupload-exists"><i class="icon-undo"></i> 重新上传</span>
                                                                <input type="file" class="default" name="fileUpload"/>
                                                                <input type="hidden" name="code" value="054">
                                                                <input type="hidden" name="disputeId" value="${disputeId}">
                                                            </span>
                                                            </div>
                                                        </form>
                                                    </div>
                                                    <button type="button" class="btn btn-primary" onclick="disputesDetailSave('054')">保存</button>
                                                    <a href="#" class="btn btn-success btn-primary" onclick="disputesDownLoad('054')">下载文件</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </section>
                </div>
                <div class="col-md-12">
                    <section class="panel">
                        <header class="panel-heading" style="text-align:left;">庭前准备</header>
                        <div class="panel-body form-horizontal">
                            <div class="tab-content">
                                <div class="tab-pane active">
                                    <div class="panel-group " id="accordion28">
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion28" href="#collapseOne28-29-1">庭前约见时间
                                                        <span style="font-size:12px;">[律师提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne28-29-1" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <!--文本类型-->
                                                    <input class="form-control form-control-inline input-medium " name="text" data-code="061" readonly id="txt29" size="16" type="text" value="" placeholder="请输入庭前约见时间">
                                                    <!--文本类型-->
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion28" href="#collapseOne28-30-2">注意要点
                                                        <span style="font-size:12px;">[律师提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne28-30-2" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <a href="#" class="btn btn-success btn-primary" onclick="disputesDownLoad('062')">下载文件</a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion28" href="#collapseOne28-31-3">证据原件准备
                                                        <span style="font-size:12px;">[客户提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne28-31-3" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <div class="fileupload fileupload-new" data-provides="fileupload">
                                                        <form action="c_disputesdetailupload.htm" enctype="multipart/form-data" method="post" id="063Form" target="fileIframe">
                                                            <div class="fileupload-preview fileupload-exists thumbnail fileClass" data-code="054" style="max-width: 200px; max-height: 80px; line-height: 20px;"></div>
                                                            <div>
                                                            <span class="btn btn-white btn-file">
                                                                <span class="fileupload-new"><i class="icon-paper-clip"></i> 选择本地文件</span>
                                                                <span class="fileupload-exists"><i class="icon-undo"></i> 重新上传</span>
                                                                <input type="file" class="default" name="fileUpload"/>
                                                                <input type="hidden" name="code" value="063">
                                                                <input type="hidden" name="disputeId" value="${disputeId}">
                                                            </span>
                                                            </div>
                                                        </form>
                                                    </div>
                                                    <button type="button" class="btn btn-primary" onclick="disputesDetailSave('063')">保存</button>
                                                    <a href="#" class="btn btn-success btn-primary" onclick="disputesDownLoad('063')">下载文件</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
                <div class="col-md-12">
                    <section class="panel">
                        <header class="panel-heading" style="text-align:left;">庭审</header>
                        <div class="panel-body form-horizontal">
                            <div class="tab-content">
                                <div class="tab-pane active">
                                    <div class="panel-group " id="accordion32">
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion32" href="#collapseOne32-33-1">庭审笔录
                                                        <span style="font-size:12px;">[律师助理提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne32-33-1" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <a href="#" class="btn btn-success btn-primary" onclick="disputesDownLoad('071')">下载文件</a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion32" href="#collapseOne32-34-2">要点提示
                                                        <span style="font-size:12px;">[律师助理提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne32-34-2" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <a href="#" class="btn btn-success btn-primary" onclick="disputesDownLoad('072')">下载文件</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
                <div class="col-md-12">
                    <section class="panel">
                        <header class="panel-heading" style="text-align:left;">庭后初步调解(如有)</header>
                        <div class="panel-body form-horizontal">
                            <div class="tab-content">
                                <div class="tab-pane active">
                                    <div class="panel-group " id="accordion35">
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion35" href="#collapseOne35-36-1">法官电话
                                                        <span style="font-size:12px;">[律师助理提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne35-36-1" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <!--文本类型-->
                                                    <input class="form-control form-control-inline input-medium " name="text" data-code="081" readonly id="txt36" size="16" type="text" value="" placeholder="请输入法官电话">
                                                    <!--文本类型-->
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion35" href="#collapseOne35-37-2">初步调解方案
                                                        <span style="font-size:12px;">[律师助理提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne35-37-2" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <a href="#" class="btn btn-success btn-primary" onclick="disputesDownLoad('082')">下载文件</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
                <div class="col-md-12">
                    <section class="panel">
                        <header class="panel-heading" style="text-align:left;">调解(如有)</header>
                        <div class="panel-body form-horizontal">
                            <div class="tab-content">
                                <div class="tab-pane active">
                                    <div class="panel-group " id="accordion38">
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion38" href="#collapseOne38-39-1">调解时间
                                                        <span style="font-size:12px;">[律师提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne38-39-1" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <!--文本类型-->
                                                    <input class="form-control form-control-inline input-medium " name="text" data-code="091" readonly id="txt39" size="16" type="text" value="" placeholder="请输入调解时间">
                                                    <!--文本类型-->
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion38" href="#collapseOne38-40-2">调解最终方案
                                                        <span style="font-size:12px;">[律师提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne38-40-2" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <a href="#" class="btn btn-success btn-primary" onclick="disputesDownLoad('092')">下载文件</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
                <div class="col-md-12">
                    <section class="panel">
                        <header class="panel-heading" style="text-align:left;">退诉讼费(如有)</header>
                        <div class="panel-body form-horizontal">
                            <div class="tab-content">
                                <div class="tab-pane active">
                                    <div class="panel-group " id="accordion41">
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion41" href="#collapseOne41-42-1">经办人员电话
                                                        <span style="font-size:12px;">[律师助理提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne41-42-1" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <!--文本类型-->
                                                    <input class="form-control form-control-inline input-medium " name="text" data-code="101" readonly id="txt42" size="16" type="text" value="" placeholder="请输入经办人员电话">
                                                    <!--文本类型-->
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion41" href="#collapseOne41-43-2">银行账户
                                                        <span style="font-size:12px;">[客户提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne41-43-2" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <!--文本类型-->
                                                    <input class="form-control form-control-inline input-medium " name="text" data-code="102" readonly id="txt43" size="16" type="text" value="" placeholder="请输入银行账户">
                                                    <!--文本类型-->
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
                <div class="col-md-12">
                    <section class="panel">
                        <header class="panel-heading" style="text-align:left;">一审判决</header>
                        <div class="panel-body form-horizontal">
                            <div class="tab-content">
                                <div class="tab-pane active">
                                    <div class="panel-group " id="accordion44">
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion44" href="#collapseOne44-45-1">一审判决书或调解书
                                                        <span style="font-size:12px;">[律师助理提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne44-45-1" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <a href="#" class="btn btn-success btn-primary" onclick="disputesDownLoad('111')">下载文件</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
                <div class="col-md-12">
                    <section class="panel">
                        <header class="panel-heading" style="text-align:left;">不上诉</header>
                        <div class="panel-body form-horizontal">
                            <div class="tab-content">
                                <div class="tab-pane active">
                                    <div class="panel-group " id="accordion46">
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion46" href="#collapseOne46-47-1">判决生效时间
                                                        <span style="font-size:12px;">[律师助理提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne46-47-1" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <!--文本类型-->
                                                    <input class="form-control form-control-inline input-medium " name="text" data-code="121" readonly id="txt47" size="16" type="text" value="" placeholder="请输入判决生效时间">
                                                    <!--文本类型-->
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
                <div class="col-md-12">
                    <section class="panel">
                        <header class="panel-heading" style="text-align:left;">上诉</header>
                        <div class="panel-body form-horizontal">
                            <div class="tab-content">
                                <div class="tab-pane active">
                                    <div class="panel-group " id="accordion48">
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion48" href="#collapseOne48-49-1">上诉状
                                                        <span style="font-size:12px;">[律师助理提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne48-49-1" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <a href="#" class="btn btn-success btn-primary" onclick="disputesDownLoad('131')">下载文件</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
                <div class="col-md-12">
                    <section class="panel">
                        <header class="panel-heading" style="text-align:left;">上诉费(如有)</header>
                        <div class="panel-body form-horizontal">
                            <div class="tab-content">
                                <div class="tab-pane active">
                                    <div class="panel-group " id="accordion50">
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion50" href="#collapseOne50-51-1">上诉须知
                                                        <span style="font-size:12px;">[律师助理提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne50-51-1" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <a href="#" class="btn btn-success btn-primary" onclick="disputesDownLoad('141')">下载文件</a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion50" href="#collapseOne50-52-2">上诉费缴纳凭证
                                                        <span style="font-size:12px;">[客户提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne50-52-2" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <div class="fileupload fileupload-new" data-provides="fileupload">
                                                        <form action="c_disputesdetailupload.htm" enctype="multipart/form-data" method="post" id="142Form" target="fileIframe">
                                                            <div class="fileupload-preview fileupload-exists thumbnail fileClass" data-code="142" style="max-width: 200px; max-height: 80px; line-height: 20px;"></div>
                                                            <div>
                                                            <span class="btn btn-white btn-file">
                                                                <span class="fileupload-new"><i class="icon-paper-clip"></i> 选择本地文件</span>
                                                                <span class="fileupload-exists"><i class="icon-undo"></i> 重新上传</span>
                                                                <input type="file" class="default" name="fileUpload"/>
                                                                <input type="hidden" name="code" value="142">
                                                                <input type="hidden" name="disputeId" value="${disputeId}">
                                                            </span>
                                                            </div>
                                                        </form>
                                                    </div>
                                                    <button type="button" class="btn btn-primary" onclick="disputesDetailSave('142')">保存</button>
                                                    <a href="#" class="btn btn-success btn-primary" onclick="disputesDownLoad('142')">下载文件</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
                <div class="col-md-12">
                    <section class="panel">
                        <header class="panel-heading" style="text-align:left;">查询二审承办法官</header>
                        <div class="panel-body form-horizontal">
                            <div class="tab-content">
                                <div class="tab-pane active">
                                    <div class="panel-group " id="accordion53">
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion53" href="#collapseOne53-54-1">承办法官
                                                        <span style="font-size:12px;">[律师助理提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne53-54-1" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <!--文本类型-->
                                                    <input class="form-control form-control-inline input-medium " name="text" data-code="151" readonly id="txt54" size="16" type="text" value="" placeholder="请输入承办法官">
                                                    <!--文本类型-->
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion53" href="#collapseOne53-55-2">电话
                                                        <span style="font-size:12px;">[律师助理提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne53-55-2" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <!--文本类型-->
                                                    <input class="form-control form-control-inline input-medium " name="text" data-code="152" readonly id="txt55" size="16" type="text" value="" placeholder="请输入电话">
                                                    <!--文本类型-->
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion53" href="#collapseOne53-56-3">传票
                                                        <span style="font-size:12px;">[律师助理提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne53-56-3" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <a href="#" class="btn btn-success btn-primary" onclick="disputesDownLoad('153')">下载文件</a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion53" href="#collapseOne53-57-4">对方证据材料
                                                        <span style="font-size:12px;">[律师助理提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne53-57-4" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <a href="#" class="btn btn-success btn-primary" onclick="disputesDownLoad('154')">下载文件</a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion53" href="#collapseOne53-58-5">查询开庭情况
                                                        <span style="font-size:12px;">[律师助理提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne53-58-5" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <a href="#" class="btn btn-success btn-primary" onclick="disputesDownLoad('155')">下载文件</a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion53" href="#collapseOne53-59-6">新证据
                                                        <span style="font-size:12px;">[客户提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne53-59-6" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <div class="fileupload fileupload-new" data-provides="fileupload">
                                                        <form action="c_disputesdetailupload.htm" enctype="multipart/form-data" method="post" id="156Form" target="fileIframe">
                                                            <div class="fileupload-preview fileupload-exists thumbnail fileClass" data-code="156" style="max-width: 200px; max-height: 80px; line-height: 20px;"></div>
                                                            <div>
                                                            <span class="btn btn-white btn-file">
                                                                <span class="fileupload-new"><i class="icon-paper-clip"></i> 选择本地文件</span>
                                                                <span class="fileupload-exists"><i class="icon-undo"></i> 重新上传</span>
                                                                <input type="file" class="default" name="fileUpload"/>
                                                                <input type="hidden" name="code" value="156">
                                                                <input type="hidden" name="disputeId" value="${disputeId}">
                                                            </span>
                                                            </div>
                                                        </form>
                                                    </div>
                                                    <button type="button" class="btn btn-primary" onclick="disputesDetailSave('156')">保存</button>
                                                    <a href="#" class="btn btn-success btn-primary" onclick="disputesDownLoad('156')">下载文件</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
                <div class="col-md-12">
                    <section class="panel">
                        <header class="panel-heading" style="text-align:left;">二审庭前准备</header>
                        <div class="panel-body form-horizontal">
                            <div class="tab-content">
                                <div class="tab-pane active">
                                    <div class="panel-group " id="accordion60">
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion60" href="#collapseOne60-72-1">庭前约见时间
                                                        <span style="font-size:12px;">[律师提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne60-72-1" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <!--文本类型-->
                                                    <input class="form-control form-control-inline input-medium " name="text" data-code="161" readonly id="txt72" size="16" type="text" value="" placeholder="请输入庭前约见时间">
                                                    <!--文本类型-->
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion60" href="#collapseOne60-73-2">注意要点
                                                        <span style="font-size:12px;">[律师提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne60-73-2" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <a href="#" class="btn btn-success btn-primary" onclick="disputesDownLoad('162')">下载文件</a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion60" href="#collapseOne60-74-3">证据原件准备
                                                        <span style="font-size:12px;">[客户提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne60-74-3" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <div class="fileupload fileupload-new" data-provides="fileupload">
                                                        <form action="c_disputesdetailupload.htm" enctype="multipart/form-data" method="post" id="163Form" target="fileIframe">
                                                            <div class="fileupload-preview fileupload-exists thumbnail fileClass" data-code="163" style="max-width: 200px; max-height: 80px; line-height: 20px;"></div>
                                                            <div>
                                                            <span class="btn btn-white btn-file">
                                                                <span class="fileupload-new"><i class="icon-paper-clip"></i> 选择本地文件</span>
                                                                <span class="fileupload-exists"><i class="icon-undo"></i> 重新上传</span>
                                                                <input type="file" class="default" name="fileUpload"/>
                                                                <input type="hidden" name="code" value="163">
                                                                <input type="hidden" name="disputeId" value="${disputeId}">
                                                            </span>
                                                            </div>
                                                        </form>
                                                    </div>
                                                    <button type="button" class="btn btn-primary" onclick="disputesDetailSave('163')">保存</button>
                                                    <a href="#" class="btn btn-success btn-primary" onclick="disputesDownLoad('163')">下载文件</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
                <div class="col-md-12">
                    <section class="panel">
                        <header class="panel-heading" style="text-align:left;">二审庭审</header>
                        <div class="panel-body form-horizontal">
                            <div class="tab-content">
                                <div class="tab-pane active">
                                    <div class="panel-group " id="accordion61">
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion61" href="#collapseOne61-75-1">庭审笔录
                                                        <span style="font-size:12px;">[律师提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne61-75-1" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <a href="#" class="btn btn-success btn-primary" onclick="disputesDownLoad('171')">下载文件</a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion61" href="#collapseOne61-76-2">要点提示
                                                        <span style="font-size:12px;">[律师提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne61-76-2" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <a href="#" class="btn btn-success btn-primary" onclick="disputesDownLoad('172')">下载文件</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
                <div class="col-md-12">
                    <section class="panel">
                        <header class="panel-heading" style="text-align:left;">二审庭后了解初步调解(如有)</header>
                        <div class="panel-body form-horizontal">
                            <div class="tab-content">
                                <div class="tab-pane active">
                                    <div class="panel-group " id="accordion62">
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion62" href="#collapseOne62-77-1">法官电话
                                                        <span style="font-size:12px;">[律师助理提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne62-77-1" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <!--文本类型-->
                                                    <input class="form-control form-control-inline input-medium " name="text" data-code="181" readonly id="txt77" size="16" type="text" value="" placeholder="请输入法官电话">
                                                    <!--文本类型-->
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion62" href="#collapseOne62-78-2">初步调解方案
                                                        <span style="font-size:12px;">[律师助理提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne62-78-2" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <a href="#" class="btn btn-success btn-primary" onclick="disputesDownLoad('182')">下载文件</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
                <div class="col-md-12">
                    <section class="panel">
                        <header class="panel-heading" style="text-align:left;">二审调解(如有)</header>
                        <div class="panel-body form-horizontal">
                            <div class="tab-content">
                                <div class="tab-pane active">
                                    <div class="panel-group " id="accordion63">
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion63" href="#collapseOne63-79-1">调解时间
                                                        <span style="font-size:12px;">[律师提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne63-79-1" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <!--文本类型-->
                                                    <input class="form-control form-control-inline input-medium " name="text" data-code="191" readonly id="txt79" size="16" type="text" value="" placeholder="请输入调解时间">
                                                    <!--文本类型-->
                                                </div>
                                            </div>
                                        </div>

                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion63" href="#collapseOne63-80-2">调解最终方案
                                                        <span style="font-size:12px;">[律师提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne63-80-2" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <a href="#" class="btn btn-success btn-primary" onclick="disputesDownLoad('192')">下载文件</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
                <div class="col-md-12">
                    <section class="panel">
                        <header class="panel-heading" style="text-align:left;">二审判决</header>
                        <div class="panel-body form-horizontal">
                            <div class="tab-content">
                                <div class="tab-pane active">
                                    <div class="panel-group " id="accordion64">
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion64" href="#collapseOne64-81-1">二审判决书或调解书
                                                        <span style="font-size:12px;">[律师助理提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne64-81-1" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <a href="#" class="btn btn-success btn-primary" onclick="disputesDownLoad('201')">下载文件</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
                <div class="col-md-12">
                    <section class="panel">
                        <header class="panel-heading" style="text-align:left;">了解判决</header>
                        <div class="panel-body form-horizontal">
                            <div class="tab-content">
                                <div class="tab-pane active">
                                    <div class="panel-group " id="accordion65">
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion65" href="#collapseOne65-82-1">调解履行情况
                                                        <span style="font-size:12px;">[律师助理提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne65-82-1" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <a href="#" class="btn btn-success btn-primary" onclick="disputesDownLoad('211')">下载文件</a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion65" href="#collapseOne65-83-2">诉讼退费情况
                                                        <span style="font-size:12px;">[律师助理提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne65-83-2" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <a href="#" class="btn btn-success btn-primary" onclick="disputesDownLoad('212')">下载文件</a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion65" href="#collapseOne65-84-3">原审法官电话
                                                        <span style="font-size:12px;">[律师助理提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne65-84-3" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <!--文本类型-->
                                                    <input class="form-control form-control-inline input-medium " name="text" data-code="213" readonly id="txt84" size="16" type="text" value="" placeholder="请输入原审法官电话">
                                                    <!--文本类型-->
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
                <div class="col-md-12">
                    <section class="panel">
                        <header class="panel-heading" style="text-align:left;">是否符合执行条件</header>
                        <div class="panel-body form-horizontal">
                            <div class="tab-content">
                                <div class="tab-pane active">
                                    <div class="panel-group " id="accordion66">
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion66" href="#collapseOne66-85-1">判决或调解书
                                                        <span style="font-size:12px;">[律师助理提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne66-85-1" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <a href="#" class="btn btn-success btn-primary" onclick="disputesDownLoad('221')">下载文件</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
                <div class="col-md-12">
                    <section class="panel">
                        <header class="panel-heading" style="text-align:left;">申请执行立案</header>
                        <div class="panel-body form-horizontal">
                            <div class="tab-content">
                                <div class="tab-pane active">
                                    <div class="panel-group " id="accordion67">
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion67" href="#collapseOne67-86-1">申请执行申请书
                                                        <span style="font-size:12px;">[律师助理提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne67-86-1" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <a href="#" class="btn btn-success btn-primary" onclick="disputesDownLoad('231')">下载文件</a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion67" href="#collapseOne67-87-2">授权委托书
                                                        <span style="font-size:12px;">[律师助理提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne67-87-2" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <a href="#" class="btn btn-success btn-primary" onclick="disputesDownLoad('232')">下载文件</a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion67" href="#collapseOne67-88-3">判决书或调解书原件
                                                        <span style="font-size:12px;">[律师助理提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne67-88-3" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <a href="#" class="btn btn-success btn-primary" onclick="disputesDownLoad('233')">下载文件</a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion67" href="#collapseOne67-89-4">身份证复印件
                                                        <span style="font-size:12px;">[律师助理提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne67-89-4" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <a href="#" class="btn btn-success btn-primary" onclick="disputesDownLoad('234')">下载文件</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
                <div class="col-md-12">
                    <section class="panel">
                        <header class="panel-heading" style="text-align:left;">承办执行法官</header>
                        <div class="panel-body form-horizontal">
                            <div class="tab-content">
                                <div class="tab-pane active">
                                    <div class="panel-group " id="accordion68">
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion68" href="#collapseOne68-90-1">执行法官
                                                        <span style="font-size:12px;">[律师助理提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne68-90-1" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <!--文本类型-->
                                                    <input class="form-control form-control-inline input-medium " name="text" data-code="241" readonly id="txt90" size="16" type="text" value="" placeholder="请输入执行法官">
                                                    <!--文本类型-->
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion68" href="#collapseOne68-91-2">电话
                                                        <span style="font-size:12px;">[律师助理提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne68-91-2" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <!--文本类型-->
                                                    <input class="form-control form-control-inline input-medium " name="text" data-code="242" readonly id="txt91" size="16" type="text" value="" placeholder="请输入电话">
                                                    <!--文本类型-->
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
                <div class="col-md-12">
                    <section class="panel">
                        <header class="panel-heading" style="text-align:left;">执行情况</header>
                        <div class="panel-body form-horizontal">
                            <div class="tab-content">
                                <div class="tab-pane active">
                                    <div class="panel-group " id="accordion69">
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion69" href="#collapseOne69-92-1">保全及执行情况描述
                                                        <span style="font-size:12px;">[律师助理提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne69-92-1" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <a href="#" class="btn btn-success btn-primary" onclick="disputesDownLoad('251')">下载文件</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
                <div class="col-md-12">
                    <section class="panel">
                        <header class="panel-heading" style="text-align:left;">是否需要执行终结后</header>
                        <div class="panel-body form-horizontal">
                            <div class="tab-content">
                                <div class="tab-pane active">
                                    <div class="panel-group " id="accordion70">
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion70" href="#collapseOne70-93-1">恢复执行
                                                        <span style="font-size:12px;">[律师提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne70-93-1" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <a href="#" class="btn btn-success btn-primary" onclick="disputesDownLoad('261')">下载文件</a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion70"
                                                       href="#collapseOne70-94-2">执行笔录 <span style="font-size:12px;">[律师提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne70-94-2" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <a href="#" class="btn btn-success btn-primary" onclick="disputesDownLoad('262')">下载文件</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
                <div class="col-md-12">
                    <section class="panel">
                        <header class="panel-heading" style="text-align:left;">执行完结</header>
                        <div class="panel-body form-horizontal">
                            <div class="tab-content">
                                <div class="tab-pane active">
                                    <div class="panel-group " id="accordion71">
                                        <div class="panel">
                                            <div class="panel-heading">
                                                <h4 class="panel-title" style="text-align:left;">
                                                    <a class="push accordion-toggle" data-toggle="collapse" data-parent="#accordion71" href="#collapseOne71-95-1">执行终结裁定书
                                                        <span style="font-size:12px;">[律师助理提交]</span>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne71-95-1" class="panel-collapse collapse " style="text-align:left;">
                                                <div class="panel-body">
                                                    <a href="#" class="btn btn-success btn-primary" onclick="disputesDownLoad('271')">下载文件</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </section>
    </section>
    <form id="queryForm"></form>
    <form id="downForm" action="c_disputesdetaildownload.htm">
        <input name="name" value="">
    </form>
    <iframe name="fileIframe" style="display:none"></iframe>
    <!--main content end-->
    <!--footer start-->
    <jsp:include page="../common/foot.jsp"></jsp:include>
    <!--footer end-->
</section>
<!-- Modal -->
<!-- Modal -->
<!-- js placed at the end of the document so the pages load faster -->
<!--common script for all pages-->
<jsp:include page="../common/includejs.jsp"></jsp:include>
<!--script for this page only-->
<script type="text/javascript" src="<%=basePath %>/res/pagejs/disputesdetail.js"></script>
</body>
</html>

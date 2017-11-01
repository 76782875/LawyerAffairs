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
    <title>电话咨询 - 律师弟弟</title>
    <!-- Bootstrap core CSS -->
    <link href="<%=basePath %>/res/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath %>/res/css/bootstrap-reset.css" rel="stylesheet">
    <!--external css-->
    <link href="<%=basePath %>/res/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link href="<%=basePath%>/res/css/dataTables/dataTables.bootstrap.css" rel="stylesheet">
    <link href="<%=basePath %>/res/assets/advanced-datatable/media/css/demo_table.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/res/assets/bootstrap-datetimepicker/css/datetimepicker.css">
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
                        <li><a href="#">公司事务咨询</a></li>
                        <li class="active">电话咨询</li>
                    </ul>
                    <!--breadcrumbs end -->
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <section class="panel">
                        <header class="panel-heading"> 事务列表</header>
                        <div class="panel-body">
                            <div class="form-inline m-bot15 clearfix search-form">
                                <div class="form-group">
                                    <label class="control-label">律师名称</label>
                                    <div class="clearfix">
                                        <input type="text" class="form-control" name="lawyerName">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label">咨询类型</label>
                                    <div class="clearfix">
                                        <select class="form-control" onchange="changeType(this,'secondType')" name="firstType"></select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label">&nbsp;</label>
                                    <div class="clearfix" id="secondType">

                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label">&nbsp;</label>
                                    <div class="clearfix">
                                        <button type="button" class="btn btn-info" onclick="toSearch()">搜索</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
            <div id="bodyDiv"></div>
            <div class="mt20 clearfix">
                <div class="flr" id="page"></div>
            </div>
        </section>
    </section>
    <!--main content end-->
    <!--footer start-->
    <jsp:include page="../common/foot.jsp"></jsp:include>
    <!--footer end-->
</section>
<!-- Modal -->
<div class="modal fade" id="order_dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">预约会面</h4>
                <input type="hidden" name="lawyerId" value="">
            </div>
            <div class="modal-body">
                <form class="form-horizontal tasi-form" id="appointForm">
                    <div class="form-group">
                        <label class="col-sm-3 control-label"><span class="label_red">*</span>约见时间：</label>
                        <div class="col-sm-9">
                            <div class="input-group date search_datetime">
                                <input size="16" type="text" value="" readonly class="form-control" name="meetTime">
                                <span class="input-group-btn">
                                    <button type="button" class="btn btn-info date-set"><i class="icon-calendar"></i></button>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label"><span class="label_red">*</span>约见地址：</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control required" name="meetAddress">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label"><span class="label_red">*</span>咨询描述：</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control required" name="desc">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">咨询类型：</label>
                        <div class="col-sm-4">
                            <select class="form-control" onchange="changeType(this,'secondTypes')" name="firstTypes"></select>
                        </div>
                        <div class="col-sm-4" id="secondTypes">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                <button class="btn btn-success" type="button" onclick="appointMeetSave()">保存</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="disputes_dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">发起委托</h4>
                <input type="hidden" name="lawyerId" value="">
            </div>
            <div class="modal-body">
                <form class="form-horizontal tasi-form" id="disputesForm">
                    <div class="form-group">
                        <label class="col-sm-3 control-label"><span class="label_red">*</span>咨询描述：</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control required" name="desc">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">咨询类型：</label>
                        <div class="col-sm-4">
                            <select class="form-control" onchange="changeType(this,'secondTypez')" name="firstTypez"></select>
                        </div>
                        <div class="col-sm-4" id="secondTypez">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                <button class="btn btn-success" type="button" onclick="importantMatterSave()">保存</button>
            </div>
        </div>
    </div>
</div>
<!-- Modal -->
<!-- js placed at the end of the document so the pages load faster -->
<!--common script for all pages-->
<jsp:include page="../common/includejs.jsp"></jsp:include>
<!--common script for all pages-->
<script type="text/javascript" src="<%=basePath %>/res/js/echarts.common.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/js/layui/layui.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/pagejs/importantaffair.js"></script>
<!--script for this page only-->
</body>
</html>


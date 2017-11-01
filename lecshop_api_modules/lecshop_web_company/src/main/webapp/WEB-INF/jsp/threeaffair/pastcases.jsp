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
    <link href="<%=basePath %>/res/assets/advanced-datatable/media/css/demo_table.css" rel="stylesheet"/>
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
    <input type="hidden" name="type" value="${type}">
    <input type="hidden" name="code" value="${code}">
    <input type="hidden" name="name" value="${name}">
    <input type="hidden" name="institution" value="${institution}">
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
                        <li><a href="#">电话咨询</a></li>
                        <li class="active">过往案例</li>
                    </ul>
                    <!--breadcrumbs end -->
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <section class="panel">
                        <header class="panel-heading"> 过往案例</header>
                        <div class="panel-body">
                            <div style="width:100%; margin:20px auto">
                                <a class="btn btn-default btn-lg" onclick="goBack()">返回列表</a>
                            </div>
                            <div class="adv-table clearfix">
                                <table class="display table table-bordered table-striped dataTable">
                                    <thead>
                                    <tr>
                                        <th>裁判文书名称</th>
                                        <th>审判时间</th>
                                        <th style="text-align: center;width: 400px">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody id="tbodycontainer">
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
<div class="modal fade" id="show_dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">案例详情</h4>
            </div>
            <div class="modal-body form-horizontal tasi-form">
                <div class="form-group">
                    <label class="col-sm-3 control-label">审判文书标题：</label>
                    <div class="col-sm-9">
                        <p class="form-control-static" data-date="title"></p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">审判时间：</label>
                    <div class="col-sm-9">
                        <p class="form-control-static" data-date="time"></p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">审判法庭：</label>
                    <div class="col-sm-9">
                        <p class="form-control-static" data-date="court"></p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">审判文书编号：</label>
                    <div class="col-sm-9">
                        <p class="form-control-static" data-date="number"></p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">审判文书详情：</label>
                    <div class="col-sm-9">
                        <p class="form-control-static" data-date="mainBody" style="overflow-y: auto;max-height: 200px;"></p>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-success" data-dismiss="modal" type="button">确认</button>
            </div>
        </div>
    </div>
</div>
<!-- Modal -->
<!-- js placed at the end of the document so the pages load faster -->
<!--common script for all pages-->
<jsp:include page="../common/includejs.jsp"></jsp:include>
<!--script for this page only-->
<script type="text/javascript" src="<%=basePath %>/res/pagejs/pastcases.js"></script>
</body>
</html>

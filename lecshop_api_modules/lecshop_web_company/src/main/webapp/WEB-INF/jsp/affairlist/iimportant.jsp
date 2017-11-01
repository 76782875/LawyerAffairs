<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>事务列表 - 律师弟弟</title>
    <!-- Bootstrap core CSS -->
    <link href="<%=basePath %>/res/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath %>/res/css/bootstrap-reset.css" rel="stylesheet">
    <link href="<%=basePath%>/res/css/dataTables/dataTables.bootstrap.css" rel="stylesheet">
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
                        <li><a href="#">事务</a></li>
                        <li class="active">事务列表</li>
                    </ul>
                    <!--breadcrumbs end -->
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <section class="panel">
                        <header class="panel-heading"> 事务列表</header>
                        <div class="panel-body">
                            <div class="btn-group btn-group-justified list_tab" style="margin-bottom:30px">
                                <a class="btn btn-lg btn-default" onclick="changeTable(0)">文书事务</a>
                                <a class="btn btn-lg btn-default" onclick="changeTable(1)">公司事务</a>
                                <a class="btn btn-lg btn-default" onclick="changeTable(2)">纠纷事务</a>
                                <a class="btn btn-lg btn-default btn-primary" onclick="changeTable(3)">重大事项</a>
                            </div>
                            <div class="form-inline m-bot15 clearfix search-form">
                                <form id="queryform">
                                    <div class="form-group">
                                        <label class="control-label">律师名称</label>
                                        <div class="clearfix">
                                            <input type="text" class="form-control" name="lawyerName">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">公司名称</label>
                                        <div class="clearfix">
                                            <input type="text" class="form-control" name="companyName">
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
                            <header class="panel-heading tab-bg-dark-navy-blue ">
                                <ul class="nav nav-tabs">
                                    <li><a onclick="changeTables(3)" style="cursor: pointer;">电话咨询</a></li>
                                    <li class="active"><a onclick="changeTables(2)" style="cursor: pointer;">重大事项</a></li>
                                    <li><a onclick="changeTables(1)" style="cursor: pointer;">预约会面（指定律师）</a></li>
                                </ul>
                            </header>
                            <div class="panel-body">
                                <div class="tab-content">
                                    <div class="tab-pane active">
                                        <div class="adv-table clearfix">
                                            <table class="display table table-bordered table-striped" id="dataTable">
                                                <thead>
                                                <tr>
                                                    <th>公司名称</th>
                                                    <th>提交人</th>
                                                    <th>律师名称</th>
                                                    <th>状态</th>
                                                    <th>金额</th>
                                                    <th>描述</th>
                                                    <th>纠纷类型</th>
                                                    <th>评分</th>
                                                    <th>提交时间</th>
                                                    <th>操作</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                </tbody>
                                            </table>
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
    <!--main content end-->
    <!--footer start-->
    <jsp:include page="../common/foot.jsp"></jsp:include>
    <!--footer end-->
</section>
<form id="affairPayForm" action="c_payforaffair.htm">
    <input type="hidden" value="" name="id">
    <input type="hidden" value="2" name="type">
</form>
<div class="modal fade" id="confirm_dialog">
    <input type="hidden" id="importantId"/>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">确认提示</h4>
            </div>
            <div class="modal-body">确定要确认该重大事项吗？</div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                <button class="btn btn-success" type="button" onclick="confirmDoc()">确定</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="score_dialog">
    <input type="hidden" id="scoreImportantId"/>
    <input type="hidden" id="score"/>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">评价</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal tasi-form">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">评分：</label>
                        <div class="col-sm-9 score_box" style="padding-top:3px">
                            <a href="javascript:;" class="score_no"></a>
                            <a href="javascript:;" class="score_no"></a>
                            <a href="javascript:;" class="score_no"></a>
                            <a href="javascript:;" class="score_no"></a>
                            <a href="javascript:;" class="score_no"></a>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                <button class="btn btn-success" type="button" onclick="score()">保存</button>
            </div>
        </div>
    </div>
</div>
<!-- js placed at the end of the document so the pages load faster -->
<!--common script for all pages-->
<jsp:include page="../common/includejs.jsp"></jsp:include>
<!--script for this page only-->
<script type="text/javascript" src="<%=basePath %>/res/pagejs/iimportant.js"></script>
</body>
</html>

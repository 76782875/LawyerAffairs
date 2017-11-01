<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="<%=basePath %>/res/img/favicon.png">
    <title>提现列表 - 乐商商城</title>
    <!-- Bootstrap core CSS -->
    <link href="<%=basePath %>/res/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath %>/res/css/bootstrap-reset.css" rel="stylesheet">
    <!--external css-->
    <link href="<%=basePath %>/res/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link href="<%=basePath %>/res/assets/advanced-datatable/media/css/demo_table.css" rel="stylesheet"/>
    <link href="<%=basePath%>/res/css/dataTables/dataTables.bootstrap.css" rel="stylesheet">

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
                        <li><a href="#">分销</a></li>
                        <li><a href="#">分销管理</a></li>
                        <li class="active">提现列表</li>
                    </ul>
                    <!--breadcrumbs end -->
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <section class="panel">
                        <header class="panel-heading"> 分销列表</header>
                        <div class="panel-body">
                            <div class="form-inline m-bot15 clearfix search-form">
                            <form role="form" class="form-inline" id="queryform">
                                <div class="form-group">
                                    <label class="control-label">交易流水号</label>
                                    <div class="clearfix">
                                        <input type="text" class="form-control" name="tradeNo">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label">律师姓名</label>
                                    <div class="clearfix">
                                        <input type="text" class="form-control" name="name">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label">律师手机号</label>
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
                            </form>
                            </div>
                            <div class="adv-table clearfix">
                                <table class="display table table-bordered table-striped" id="dataTable">
                                    <thead>
                                    <tr>
                                        <th>流水号</th>
                                        <th>律师电话</th>
                                        <th>律师姓名</th>
                                        <th>提现金额</th>
                                        <th>支付宝账号</th>
                                        <th>支付宝姓名</th>
                                        <th>状态</th>
                                        <th>申请日期</th>
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
<!-- 通过申请 -->
<div class="modal fade" id="pass_dialog">
    <input type="hidden" id="passid">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">确认提示</h4>
                <input type="hidden" id="passIdHidden" value="">
            </div>
            <div class="modal-body">确定要通过此提现申请吗？</div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                <button class="btn btn-success" type="button" onclick="pass()">确定</button>
            </div>
        </div>
    </div>
</div>

<!-- 拒绝申请 -->
<div class="modal fade" id="refuse_dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">提现拒绝</h4>
                <input type="hidden" id="refuseIdHidden" value="">
            </div>
            <div class="modal-body">确定要拒绝此提现申请吗？</div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                <button class="btn btn-success" type="button" onclick="refuse()">保存</button>
            </div>
        </div>
    </div>
</div>

<! --发放提现 -- >
<div class="modal fade" id="withdraw_dialog">
    <input type="hidden" id="withdraw_id">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">确认提示</h4>
            </div>
            <div class="modal-body">确定发放金额吗？</div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                <button class="btn btn-success" type="button" onclick="grantMoney()">确定</button>
            </div>
        </div>
    </div>
</div>

<! --驳回提现申请-- >
<div class="modal fade" id="reject_dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">确认提示</h4>
                <input type="hidden" id="rejectIdHidden" value="">
            </div>
            <div  class="modal-body">确定驳回申请吗？</div>
            <div class="modal-footer">
                <button class="btn btn-success" type="button" onclick="reject()">确定</button>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="result_dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">确认提示</h4>
            </div>
            <div id="withdraw_result" class="modal-body"></div>
            <div class="modal-footer">
                <button class="btn btn-success" type="button" onclick="resultClose()">确定</button>
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

<!--script for this page only-->
<script type="text/javascript" src="<%=basePath%>/res/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/underscore-min.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/assets/datatables/jquery.dataTables.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/assets/datatables/dataTables.bootstrap.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/common/ls.datatables.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/common/common.js"></script>
<!--script for this page only-->
<script type="text/javascript" src="<%=basePath%>/res/pagejs/withdraws.js"></script>
<script>

</script>
</body>
</html>

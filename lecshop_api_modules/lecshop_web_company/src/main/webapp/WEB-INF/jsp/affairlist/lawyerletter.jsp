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
                                <a class="btn btn-lg btn-default btn-primary" onclick="changeTable(0)">文书事务</a>
                                <a class="btn btn-lg btn-default" onclick="changeTable(1)">公司事务</a>
                                <a class="btn btn-lg btn-default" onclick="changeTable(2)">纠纷事务</a>
                                <a class="btn btn-lg btn-default" onclick="changeTable(3)">重大事项</a>
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
                                    <li class="active"><a onclick="changeTables(0)" style="cursor: pointer;">律师函</a></li>
                                    <li><a onclick="changeTables(1)" style="cursor: pointer;">草拟文书</a></li>
                                    <li><a onclick="changeTables(2)" style="cursor: pointer;">修改文书</a></li>
                                </ul>
                            </header>
                            <div class="panel-body">
                                <div class="tab-content">
                                    <div class="tab-pane active">
                                        <div class="adv-table clearfix">
                                            <table class="display table table-bordered table-striped" id="dataTable">
                                                <thead>
                                                <tr>
                                                    <th>用户名</th>
                                                    <th>公司名称</th>
                                                    <th>律师名称</th>
                                                    <th>收件人电话</th>
                                                    <th>收件人地址</th>
                                                    <th>评分</th>
                                                    <th>状态</th>
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
<form id="downForm" action="c_docdownload.htm">
    <input type="hidden" name="name" value="">
</form>
<!-- Modal -->
<div class="modal fade" id="record_dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">历史记录</h4>
            </div>
            <div class="modal-body">
                <table class="table table-hover">
                    <tbody id="detail">

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="confirm_dialog">
    <input type="hidden" id="letterId"/>
    <input type="hidden" id="lawyerId"/>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">确认提示</h4>
            </div>
            <div class="modal-body">确定要确认该律师函吗？</div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                <button class="btn btn-success" type="button" onclick="confirmDoc()">确定</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="score_dialog">
    <input type="hidden" id="scoreLetterId"/>
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
<div class="modal fade" id="communion_dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">律师函交流</h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal tasi-form">
                    <iframe name="fileIframe" style="display:none"></iframe>
                    <form action="c_lawyerlettercommunion.htm" enctype="multipart/form-data" method="post" id="fileForm" target="fileIframe">
                        <input type="hidden" name="lawyerLetterId"/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">详情描述：</label>
                            <div class="col-sm-9">
                                <textarea type="text" class="form-control required" name="desc"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">律师函上传：</label>
                            <div class="col-sm-9">
                                <div class="fileupload fileupload-new" data-provides="fileupload">
                                    <span class="btn btn-white btn-file">
                                        <span class="fileupload-new"><i class="icon-paper-clip"></i> 选择文件</span>
                                        <span class="fileupload-exists"><i class="icon-undo"></i> 重新选择</span>
                                        <input type="file" class="default" name="fileUpload"/>
                                    </span>
                                    <span class="fileupload-preview" style="margin-left:5px;"></span>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                <button class="btn btn-success" type="button" onclick="communion()">确定</button>
            </div>
        </div>
    </div>
</div>
<!-- Modal -->
<!-- js placed at the end of the document so the pages load faster -->
<!--common script for all pages-->
<jsp:include page="../common/includejs.jsp"></jsp:include>
<!--script for this page only-->
<script type="text/javascript" src="<%=basePath%>/res/pagejs/lawyerletters.js"></script>
</body>
</html>

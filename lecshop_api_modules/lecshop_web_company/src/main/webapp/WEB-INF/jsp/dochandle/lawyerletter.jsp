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
    <title>律师函 - 律师弟弟</title>
    <!-- Bootstrap core CSS -->
    <link href="<%=basePath %>/res/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath %>/res/css/bootstrap-reset.css" rel="stylesheet">
    <!--external css-->
    <link href="<%=basePath %>/res/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link href="<%=basePath %>/res/assets/advanced-datatable/media/css/demo_table.css" rel="stylesheet"/>
    <link href="<%=basePath%>/res/css/dataTables/dataTables.bootstrap.css" rel="stylesheet">
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
                        <li><a href="#">公司文书处理</a></li>
                        <li class="active">律师函</li>
                    </ul>
                    <!--breadcrumbs end -->
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <section class="panel">
                        <header class="panel-heading"> 律师函</header>
                        <div class="panel-body">
                            <div class="form-horizontal tasi-form set-form">
                                <iframe name="fileIframe" style="display:none"></iframe>
                                <form action="c_addlawyerletter.htm" enctype="multipart/form-data" method="post" id="fileForm" target="fileIframe">
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">情况描述：</label>
                                        <div class="col-sm-5">
                                            <textarea class="form-control required" style="min-height:100px" name="desc"></textarea>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">联系电话：</label>
                                        <div class="col-sm-5">
                                            <input type="text" class="form-control required digits" name="mobile"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">收件地址：</label>
                                        <div class="col-sm-5">
                                            <input type="text" class="form-control required" name="address"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">上传证据：</label>
                                        <div class="col-sm-5">
                                            <div class="fileupload fileupload-new" data-provides="fileupload">
                                                  <span class="btn btn-white btn-file">
                                                    <span class="fileupload-new"><i class="icon-paper-clip"></i> 选择文件</span>
                                                    <span class="fileupload-exists"><i class="icon-undo"></i> 重新选择</span>
                                                    <input type="file" class="default" name="fileUpload"/>
                                                  </span>
                                                  <span class="fileupload-preview" style="margin-left:5px;"></span>
                                            </div>
                                            <p class="help-block">选择本地压缩包上传(zip,rar)</p>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label"></label>
                                        <div class="col-sm-5">
                                            <button type="button" class="btn btn-success btn-lg" onclick="addLawyerLetter()">提交</button>
                                        </div>
                                    </div>
                                </form>
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
<!--common script for all pages-->
<jsp:include page="../common/includejs.jsp"></jsp:include>
<!--script for this page only-->
<script src="<%=basePath %>/res/pagejs/lawyerletter.js"></script>
</body>
</html>

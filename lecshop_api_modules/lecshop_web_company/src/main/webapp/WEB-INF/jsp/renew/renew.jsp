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
    <title>续费 - 律师弟弟</title>
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
    <form id="form" method="post" action="<%=basePath %>/c_pay.htm">
        <input type="hidden" name="type" value="">
        <input type="hidden" name="money" value="">
    </form>
    <!--main content start-->
    <section id="main-content">
        <section class="wrapper site-min-height">
            <div class="row">
                <div class="col-lg-12">
                    <!--breadcrumbs start -->
                    <ul class="breadcrumb">
                        <li><a href="#">会员续费</a></li>
                        <li class="active">续费</li>
                    </ul>
                    <!--breadcrumbs end -->
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <section class="panel">
                        <header class="panel-heading"> 续费</header>
                        <div class="panel-body">
                            <div class="form-horizontal tasi-form set-form">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">支付方式：</label>
                                    <div class="col-sm-5">
                                        <p class="form-control-static">支付宝</p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">付费方式：</label>
                                    <div class="col-sm-1">
                                        <select class="form-control sale_type">
                                            <option value="0" selected>充值</option>
                                            <option value="1">年费</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">充值金额：</label>
                                    <div class="col-sm-9">
                                        <div class="bio-desk price_desk active" type="3" money="100">
                                            <h4 class="terques">100元</h4>
                                        </div>
                                        <div class="bio-desk price_desk" type="4" money="500">
                                            <h4 class="terques">500元</h4>
                                        </div>
                                        <div class="bio-desk price_desk" type="5" money="2000">
                                            <h4 class="terques">2000元</h4>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group year_group" style="display:none">
                                    <label class="col-sm-2 control-label">年费金额：</label>
                                    <div class="col-sm-9">
                                        <div class="bio-desk sale_desk active" type="0" money="3000">
                                            <h4 class="terques">3000元/年</h4>
                                            <p>黄金会员：无预约咨询</p>
                                        </div>
                                        <div class="bio-desk sale_desk" type="1" money="6000">
                                            <h4 class="terques">6000元/年</h4>
                                            <p>铂金会员：会面咨询不超过5次</p>
                                        </div>
                                        <div class="bio-desk sale_desk" type="2" money="20000">
                                            <h4 class="terques">20000元/年</h4>
                                            <p>钻石会员：无限制</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"></label>
                                    <div class="col-sm-5">
                                        <button type="button" class="btn btn-success btn-lg save_btn" onclick="toPay()">去支付</button>
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
<!-- js placed at the end of the document so the pages load faster -->
<!--common script for all pages-->
<jsp:include page="../common/includejs.jsp"></jsp:include>
<!--script for this page only-->
<script src="<%=basePath %>/res/pagejs/renew.js"></script>
</body>
</html>

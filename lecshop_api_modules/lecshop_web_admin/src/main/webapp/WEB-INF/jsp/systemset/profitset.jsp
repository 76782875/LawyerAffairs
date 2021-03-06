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
    <title>律师收益设置 - 律师弟弟</title>
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
                        <li><a href="#">系统设置</a></li>
                        <li class="active">律师收益设置</li>
                    </ul>
                    <!--breadcrumbs end -->
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <section class="panel">
                        <header class="panel-heading"> 律师收益设置</header>
                        <div class="panel-body">
                            <div class="panel">
                                <header class="panel-heading tab-bg-dark-navy-blue ">
                                    <ul class="nav nav-tabs">
                                        <li class="active"><a data-toggle="tab" href="#tab1">电话咨询</a></li>
                                        <li><a data-toggle="tab" href="#tab2">预约会面</a></li>
                                        <li><a data-toggle="tab" href="#tab3">预约会面（指定律师)</a></li>
                                        <li><a data-toggle="tab" href="#tab4">提现设置</a></li>
                                        <li><a data-toggle="tab" href="#tab5">修改文书</a></li>
                                        <li><a data-toggle="tab" href="#tab6">草拟文书</a></li>
                                        <li><a data-toggle="tab" href="#tab7">律师函</a></li>
                                    </ul>
                                </header>
                                <div class="panel-body">
                                    <div class="tab-content">
                                        <div id="tab1" class="tab-pane active">
                                            <div class="form-horizontal tasi-form set-form">
                                                <form id="form1">
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label"><span class="label_red">*</span>付费金额：</label>
                                                        <div class="col-sm-10">
                                                            <input style="width:100px" name="telConMoney" type="text" class="form-control fll required money" oninput='this.value=this.value.replace(/\D/gi,"")'/>
                                                            <p class="fll" style="line-height:34px; margin:0 0 0 10px">元/次</p>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label"><span class="label_red">*</span>佣金：</label>
                                                        <div class="col-sm-10">
                                                            <input style="width:100px" name="telConCommission" type="text" class="form-control fll required  moneyRange" oninput='this.value=this.value.replace(/\D/gi,"")'/>
                                                            <p class="fll" style="line-height:34px; margin:0 0 0 10px">%</p>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label"></label>
                                                        <div class="col-sm-5">
                                                            <button type="button" class="btn btn-success btn-lg save_btn" onclick="saveBtn(1)">保存设置</button>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                        <div id="tab2" class="tab-pane">
                                            <div class="form-horizontal tasi-form set-form">
                                                <form id="form2">
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label"><span class="label_red">*</span>付费金额：</label>
                                                        <div class="col-sm-10">
                                                            <input style="width:100px" name="meetAllMoney" type="text" class="form-control fll required money" oninput='this.value=this.value.replace(/\D/gi,"")'/>
                                                            <p class="fll" style="line-height:34px; margin:0 0 0 10px">元/次</p>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label"><span class="label_red">*</span>佣金：</label>
                                                        <div class="col-sm-10">
                                                            <input style="width:100px" name="meetAllCommisson" type="text" class="form-control fll required  moneyRange" oninput='this.value=this.value.replace(/\D/gi,"")'/>
                                                            <p class="fll" style="line-height:34px; margin:0 0 0 10px">%</p>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label"></label>
                                                        <div class="col-sm-5">
                                                            <button type="button" class="btn btn-success btn-lg save_btn" onclick="saveBtn(2)">保存设置</button>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                        <div id="tab3" class="tab-pane">
                                            <div class="form-horizontal tasi-form set-form">
                                                <form id="form3">
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label"><span class="label_red">*</span>付费金额：</label>
                                                        <div class="col-sm-10">
                                                            <input style="width:100px" name="meetMoney" type="text" class="form-control fll required money" oninput='this.value=this.value.replace(/\D/gi,"")'/>
                                                            <p class="fll" style="line-height:34px; margin:0 0 0 10px">元/次</p>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label"><span class="label_red">*</span>佣金：</label>
                                                        <div class="col-sm-10">
                                                            <input style="width:100px" name="meetCommission" type="text" class="form-control fll required  moneyRange" oninput='this.value=this.value.replace(/\D/gi,"")'/>
                                                            <p class="fll" style="line-height:34px; margin:0 0 0 10px">%</p>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label"></label>
                                                        <div class="col-sm-5">
                                                            <button type="button" class="btn btn-success btn-lg" onclick="saveBtn(3)">保存设置</button>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                        <div id="tab4" class="tab-pane">
                                            <div class="form-horizontal tasi-form set-form">
                                                <form id="form4">
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label"><span class="label_red">*</span>提现日期：</label>
                                                        <div class="col-sm-10">
                                                            <input style="width:100px" name="withdrawTime" type="text" class="form-control fll required" value="" oninput='this.value=this.value.replace(/\D/gi,"")'/>
                                                            <p class="fll" style="line-height:34px; margin:0 0 0 10px">日/每月</p>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label"></label>
                                                        <div class="col-sm-5">
                                                            <button type="button" class="btn btn-success btn-lg" onclick="saveBtn(4)">保存设置</button>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                        <div id="tab5" class="tab-pane">
                                            <div class="form-horizontal tasi-form set-form">
                                                <form id="form5">
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label"><span class="label_red">*</span>付费金额：</label>
                                                        <div class="col-sm-10">
                                                            <input style="width:100px" name="modifyDocMoney" type="text" class="form-control fll required money" oninput='this.value=this.value.replace(/\D/gi,"")'/>
                                                            <p class="fll" style="line-height:34px; margin:0 0 0 10px">元/次</p>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label"><span class="label_red">*</span>佣金：</label>
                                                        <div class="col-sm-10">
                                                            <input style="width:100px" name="modifyDocCommission" type="text" class="form-control fll required  moneyRange" oninput='this.value=this.value.replace(/\D/gi,"")'/>
                                                            <p class="fll" style="line-height:34px; margin:0 0 0 10px">%</p>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label"></label>
                                                        <div class="col-sm-5">
                                                            <button type="button" class="btn btn-success btn-lg" onclick="saveBtn(5)">保存设置</button>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                        <div id="tab6" class="tab-pane">
                                            <div class="form-horizontal tasi-form set-form">
                                                <form id="form6">
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label"><span class="label_red">*</span>付费金额：</label>
                                                        <div class="col-sm-10">
                                                            <input style="width:100px" name="draftDocMoney" type="text" class="form-control fll required money" oninput='this.value=this.value.replace(/\D/gi,"")'/>
                                                            <p class="fll" style="line-height:34px; margin:0 0 0 10px">元/次</p>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label"><span class="label_red">*</span>佣金：</label>
                                                        <div class="col-sm-10">
                                                            <input style="width:100px" name="draftDocCommission" type="text" class="form-control fll required  moneyRange" oninput='this.value=this.value.replace(/\D/gi,"")'/>
                                                            <p class="fll" style="line-height:34px; margin:0 0 0 10px">%</p>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label"></label>
                                                        <div class="col-sm-5">
                                                            <button type="button" class="btn btn-success btn-lg" onclick="saveBtn(6)">保存设置</button>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                        <div id="tab7" class="tab-pane">
                                            <div class="form-horizontal tasi-form set-form">
                                                <form id="form7">
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label"><span class="label_red">*</span>付费金额：</label>
                                                        <div class="col-sm-10">
                                                            <input style="width:100px" name="lawyerLetterMoney" type="text" class="form-control fll required money" oninput='this.value=this.value.replace(/\D/gi,"")'/>
                                                            <p class="fll" style="line-height:34px; margin:0 0 0 10px">元/次</p>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label"><span class="label_red">*</span>佣金：</label>
                                                        <div class="col-sm-10">
                                                            <input style="width:100px" name="lawyerLetterCommission" type="text" class="form-control fll required  moneyRange" oninput='this.value=this.value.replace(/\D/gi,"")'/>
                                                            <p class="fll" style="line-height:34px; margin:0 0 0 10px">%</p>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label"></label>
                                                        <div class="col-sm-5">
                                                            <button type="button" class="btn btn-success btn-lg" onclick="saveBtn(7)">保存设置</button>
                                                        </div>
                                                    </div>
                                                </form>
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
    <!--main content end-->
    <!--footer start-->
    <jsp:include page="../common/foot.jsp"></jsp:include>
    <!--footer end-->
</section>
<!-- Modal -->
<div class="modal fade" id="success_dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">系统提示</h4>
            </div>
            <div class="modal-body">设置保存成功！</div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-success" type="button">确定</button>
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
<script type="text/javascript" language="javascript" src="<%=basePath %>/res/assets/advanced-datatable/media/js/jquery.dataTables.js"></script>
<script src="<%=basePath %>/res/js/respond.min.js"></script>
<!--common script for all pages-->
<script src="<%=basePath %>/res/js/common-scripts.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/common/common.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/jquery.validate.js"></script>
<!--script for this page only-->
<script type="text/javascript" src="<%=basePath %>/res/pagejs/profitset.js"></script>
</body>
</html>

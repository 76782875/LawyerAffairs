<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <title>纠纷详情</title>
  <link rel="stylesheet" href="<%=basePath %>/res/dist/css/framework7.ios.min.css">
  <link rel="stylesheet" href="<%=basePath %>/res/dist/css/framework7.ios.colors.min.css">
  <link rel="stylesheet" href="<%=basePath %>/res/dist/css/framework7-icons.css">
  <link rel="stylesheet" href="<%=basePath %>/res/css/my-app.css">
</head>

<body class="framework7-root">
<input type="hidden" id="disputeId" value="${disputeId}">
<input type="hidden" id="basePath" value="<%=basePath %>">
<div class="views toolbar-through">
  <div class="page">
    <div class="page-content">
      <div class="list-block accordion-list inset">
        <ul>
          <li class="accordion-item"><a href="#" class="item-link item-content">
            <div class="item-inner">
              <div class="item-title">委托案件</div>
            </div></a>
            <div class="accordion-item-content">
              <div class="list-block">
                <ul>
                  <li><a onclick="todetailtypeone('001')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">代理合同 [律师提交]</div>
                      </div>
                    </div></a></li>
                  <li><a onclick="todetailtypeone('002')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">谈话笔录 [律师提交]</div>
                      </div>
                    </div></a></li>
                  <li><a onclick="todetailtypeone('003')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">风险告知书 [律师提交]</div>
                      </div>
                    </div></a></li>
                  <li><a onclick="todetailtypeone('004')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">授权委托书 [律师提交]</div>
                      </div>
                    </div></a></li>
                  <li><a onclick="todetailtypeone('005')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">原告身份证 [客户提交]</div>
                      </div>
                    </div></a></li>
                  <li><a onclick="todetailtypeone('006')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">被告身份信息 [客户提交]</div>
                      </div>
                    </div></a></li>
                  <li><a onclick="todetailtypeone('007')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">证据材料 [客户提交]</div>
                      </div>
                    </div></a></li>
                </ul>
              </div>
            </div>
          </li>
          <li class="accordion-item"><a href="#" class="item-link item-content">
            <div class="item-inner">
              <div class="item-title">立案</div>
            </div></a>
            <div class="accordion-item-content">
              <div class="list-block">
                <ul>
                  <li><a onclick="todetailtypeone('011')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">诉状 [律师提交]</div>
                      </div>
                    </div></a></li>
                  <li><a onclick="todetailtypetwo('012','请输入诉讼费金额')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">诉讼费金额 [律师助理提交]</div>
                      </div>
                    </div></a></li>
                </ul>
              </div>
            </div>
          </li>
          <li class="accordion-item"><a href="#" class="item-link item-content">
            <div class="item-inner">
              <div class="item-title">查询立案</div>
            </div></a>
            <div class="accordion-item-content">
              <div class="list-block">
                <ul>
                  <li><a onclick="todetailtypetwo('021','请输入经办人员电话')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">经办人员电话 [律师提交]</div>
                      </div>
                    </div></a></li>
                </ul>
              </div>
            </div>
          </li>
          <li class="accordion-item"><a href="#" class="item-link item-content">
            <div class="item-inner">
              <div class="item-title">保全程序(如有)</div>
            </div></a>
            <div class="accordion-item-content">
              <div class="list-block">
                <ul>
                  <li><a onclick="todetailtypetwo('031','请输入经办法官电话')" href="#" class="item-link">
                  <div class="item-content">
                    <div class="item-media"></div>
                    <div class="item-inner">
                      <div class="item-title">经办法官电话 [律师提交]</div>
                    </div>
                  </div></a></li>
                  <li><a onclick="todetailtypeone('032')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">保全申请书 [律师提交]</div>
                      </div>
                    </div></a></li>
                  <li><a onclick="todetailtypeone('033')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">担保书 [律师提交]</div>
                      </div>
                    </div></a></li>
                  <li><a onclick="todetailtypeone('034')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">担保材料 [客户提交]</div>
                      </div>
                    </div></a></li>
                </ul>
              </div>
            </div>
          </li>
          <li class="accordion-item"><a href="#" class="item-link item-content">
            <div class="item-inner">
              <div class="item-title">查询办案法官</div>
            </div></a>
            <div class="accordion-item-content">
              <div class="list-block">
                <ul>
                  <li><a onclick="todetailtypetwo('041','请输入姓名')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">姓名 [律师提交]</div>
                      </div>
                    </div></a></li>
                </ul>
              </div>
            </div>
          </li>
          <li class="accordion-item"><a href="#" class="item-link item-content">
            <div class="item-inner">
              <div class="item-title">查询开庭情况</div>
            </div></a>
            <div class="accordion-item-content">
              <div class="list-block">
                <ul>
                  <li><a onclick="todetailtypethree('051')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">开庭时间 [律师提交]</div>
                      </div>
                    </div></a></li>
                  <li><a onclick="todetailtypetwo('052','请输入电话')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">电话 [律师提交]</div>
                      </div>
                    </div></a></li>
                  <li><a onclick="todetailtypeone('053')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">传票 [律师提交]</div>
                      </div>
                    </div></a></li>
                  <li><a onclick="todetailtypeone('054')"  href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">对方证据材料 [客户提交]</div>
                      </div>
                    </div></a></li>
                </ul>
              </div>
            </div>
          </li>
          <li class="accordion-item"><a href="#" class="item-link item-content">
            <div class="item-inner">
              <div class="item-title">庭前准备</div>
            </div></a>
            <div class="accordion-item-content">
              <div class="list-block">
                <ul>
                  <li><a onclick="todetailtypethree('061')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">庭前约见时间 [律师提交]</div>
                      </div>
                    </div></a></li>
                  <li><a onclick="todetailtypeone('062')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">注意要点 [律师提交]</div>
                      </div>
                    </div></a></li>
                  <li><a onclick="todetailtypeone('063')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">证据原件准备 [客户提交]</div>
                      </div>
                    </div></a></li>
                </ul>
              </div>
            </div>
          </li>
          <li class="accordion-item"><a href="#" class="item-link item-content">
            <div class="item-inner">
              <div class="item-title">庭审</div>
            </div></a>
            <div class="accordion-item-content">
              <div class="list-block">
                <ul>
                  <li><a onclick="todetailtypeone('071')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">庭审笔录 [律师提交]</div>
                      </div>
                    </div></a></li>
                  <li><a onclick="todetailtypeone('072')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">要点提示 [律师提交]</div>
                      </div>
                    </div></a></li>
                </ul>
              </div>
            </div>
          </li>
          <li class="accordion-item"><a href="#" class="item-link item-content">
            <div class="item-inner">
              <div class="item-title">庭后初步调解(如有)</div>
            </div></a>
            <div class="accordion-item-content">
              <div class="list-block">
                <ul>
                  <li><a onclick="todetailtypetwo('081','请输入电话')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">法官电话 [律师提交]</div>
                      </div>
                    </div></a></li>
                  <li><a onclick="todetailtypeone('082')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">初步调解方案 [律师提交]</div>
                      </div>
                    </div></a></li>
                </ul>
              </div>
            </div>
          </li>
          <li class="accordion-item"><a href="#" class="item-link item-content">
            <div class="item-inner">
              <div class="item-title">调解(如有)</div>
            </div></a>
            <div class="accordion-item-content">
              <div class="list-block">
                <ul>
                  <li><a onclick="todetailtypethree('091')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">调解时间 [律师提交]</div>
                      </div>
                    </div></a></li>
                  <li><a onclick="todetailtypeone('092')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">调解最终方案 [律师提交]</div>
                      </div>
                    </div></a></li>
                </ul>
              </div>
            </div>
          </li>
          <li class="accordion-item"><a href="#" class="item-link item-content">
            <div class="item-inner">
              <div class="item-title">退诉讼费(如有)</div>
            </div></a>
            <div class="accordion-item-content">
              <div class="list-block">
                <ul>
                  <li><a onclick="todetailtypetwo('101','请输入电话')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">经办人员电话 [律师提交]</div>
                      </div>
                    </div></a></li>
                  <li><a onclick="todetailtypetwo('102','请输入银行账户')"  href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">银行账户 [客户提交]</div>
                      </div>
                    </div></a></li>
                </ul>
              </div>
            </div>
          </li>
          <li class="accordion-item"><a href="#" class="item-link item-content">
            <div class="item-inner">
              <div class="item-title">一审判决或调解</div>
            </div></a>
            <div class="accordion-item-content">
              <div class="list-block">
                <ul>
                  <li><a onclick="todetailtypeone('111')"  href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">一审判决书或调解书 [律师提交]</div>
                      </div>
                    </div></a></li>
                </ul>
              </div>
            </div>
          </li>
          <li class="accordion-item"><a href="#" class="item-link item-content">
            <div class="item-inner">
              <div class="item-title">不上诉</div>
            </div></a>
            <div class="accordion-item-content">
              <div class="list-block">
                <ul>
                  <li><a onclick="todetailtypethree('121')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">判决生效时间 [律师提交]</div>
                      </div>
                    </div></a></li>
                </ul>
              </div>
            </div>
          </li>
          <li class="accordion-item"><a href="#" class="item-link item-content">
            <div class="item-inner">
              <div class="item-title">上诉</div>
            </div></a>
            <div class="accordion-item-content">
              <div class="list-block">
                <ul>
                  <li><a onclick="todetailtypeone('131')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">上诉状 [律师提交]</div>
                      </div>
                    </div></a></li>
                </ul>
              </div>
            </div>
          </li>
          <li class="accordion-item"><a href="#" class="item-link item-content">
            <div class="item-inner">
              <div class="item-title">上诉费(如有)</div>
            </div></a>
            <div class="accordion-item-content">
              <div class="list-block">
                <ul>
                  <li><a  onclick="todetailtypeone('141')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">上诉须知 [律师提交]</div>
                      </div>
                    </div></a></li>
                  <li><a onclick="todetailtypeone('142')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">上诉费缴纳凭证 [客户提交]</div>
                      </div>
                    </div></a></li>
                </ul>
              </div>
            </div>
          </li>
          <li class="accordion-item"><a href="#" class="item-link item-content">
            <div class="item-inner">
              <div class="item-title">查询二审承办法官</div>
            </div></a>
            <div class="accordion-item-content">
              <div class="list-block">
                <ul>
                  <li><a onclick="todetailtypetwo('151','请输入承办法官')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">承办法官 [律师提交]</div>
                      </div>
                    </div></a></li>
                  <li><a  onclick="todetailtypetwo('152','请输入电话')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">电话 [律师提交]</div>
                      </div>
                    </div></a></li>
                  <li><a onclick="todetailtypeone('153')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">传票 [律师提交]</div>
                      </div>
                    </div></a></li>
                  <li><a  onclick="todetailtypeone('154')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">对方证据材料 [律师提交]</div>
                      </div>
                    </div></a></li>
                  <li><a  onclick="todetailtypeone('155')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">查询开庭情况 [律师提交]</div>
                      </div>
                    </div></a></li>
                  <li><a  onclick="todetailtypeone('156')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">新证据 [客户提交]</div>
                      </div>
                    </div></a></li>
                </ul>
              </div>
            </div>
          </li>
          <li class="accordion-item"><a href="#" class="item-link item-content">
            <div class="item-inner">
              <div class="item-title">二审庭前准备</div>
            </div></a>
            <div class="accordion-item-content">
              <div class="list-block">
                <ul>
                  <li><a onclick="todetailtypethree('161')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">庭前约见时间 [律师提交]</div>
                      </div>
                    </div></a></li>
                  <li><a onclick="todetailtypeone('162')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">注意要点 [律师提交]</div>
                      </div>
                    </div></a></li>
                  <li><a onclick="todetailtypeone('163')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">证据原件准备 [客户提交]</div>
                      </div>
                    </div></a></li>
                </ul>
              </div>
            </div>
          </li>
          <li class="accordion-item"><a href="#" class="item-link item-content">
            <div class="item-inner">
              <div class="item-title">二审庭审</div>
            </div></a>
            <div class="accordion-item-content">
              <div class="list-block">
                <ul>
                  <li><a onclick="todetailtypeone('171')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">庭审笔录 [律师提交]</div>
                      </div>
                    </div></a></li>
                  <li><a onclick="todetailtypeone('172')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">要点提示 [律师提交]</div>
                      </div>
                    </div></a></li>
                </ul>
              </div>
            </div>
          </li>
          <li class="accordion-item"><a href="#" class="item-link item-content">
            <div class="item-inner">
              <div class="item-title">二审庭后了解初步调解(如有)</div>
            </div></a>
            <div class="accordion-item-content">
              <div class="list-block">
                <ul>
                  <li><a onclick="todetailtypetwo('181','请输入电话')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">法官电话 [律师提交]</div>
                      </div>
                    </div></a></li>
                  <li><a  onclick="todetailtypeone('182')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">初步调解方案 [律师提交]</div>
                      </div>
                    </div></a></li>
                </ul>
              </div>
            </div>
          </li>
          <li class="accordion-item"><a href="#" class="item-link item-content">
            <div class="item-inner">
              <div class="item-title">二审调解(如有)</div>
            </div></a>
            <div class="accordion-item-content">
              <div class="list-block">
                <ul>
                  <li><a  onclick="todetailtypethree('191')"  href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">调解时间 [律师提交]</div>
                      </div>
                    </div></a></li>
                  <li><a onclick="todetailtypeone('192')"  href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">调解最终方案 [律师提交]</div>
                      </div>
                    </div></a></li>
                </ul>
              </div>
            </div>
          </li>
          <li class="accordion-item"><a href="#" class="item-link item-content">
            <div class="item-inner">
              <div class="item-title">二审判决</div>
            </div></a>
            <div class="accordion-item-content">
              <div class="list-block">
                <ul>
                  <li><a onclick="todetailtypeone('201')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">二审判决书或调解书 [律师提交]</div>
                      </div>
                    </div></a></li>
                </ul>
              </div>
            </div>
          </li>
          <li class="accordion-item"><a href="#" class="item-link item-content">
            <div class="item-inner">
              <div class="item-title">了解判决</div>
            </div></a>
            <div class="accordion-item-content">
              <div class="list-block">
                <ul>
                  <li><a onclick="todetailtypeone('211')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">调解履行情况 [律师提交]</div>
                      </div>
                    </div></a></li>
                  <li><a onclick="todetailtypeone('212')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">诉讼退费情况 [律师提交]</div>
                      </div>
                    </div></a></li>
                  <li><a  onclick="todetailtypetwo('213','请输入原审法官电话')"  href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">原审法官电话 [律师提交]</div>
                      </div>
                    </div></a></li>
                </ul>
              </div>
            </div>
          </li>
          <li class="accordion-item"><a href="#" class="item-link item-content">
            <div class="item-inner">
              <div class="item-title">是否符合执行条件</div>
            </div></a>
            <div class="accordion-item-content">
              <div class="list-block">
                <ul>
                  <li><a onclick="todetailtypeone('221')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">判决或调解书 [律师提交]</div>
                      </div>
                    </div></a></li>
                </ul>
              </div>
            </div>
          </li>
          <li class="accordion-item"><a href="#" class="item-link item-content">
            <div class="item-inner">
              <div class="item-title">申请执行立案</div>
            </div></a>
            <div class="accordion-item-content">
              <div class="list-block">
                <ul>
                  <li><a onclick="todetailtypeone('231')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">申请执行申请书 [律师提交]</div>
                      </div>
                    </div></a></li>
                  <li><a onclick="todetailtypeone('232')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">授权委托书 [律师提交]</div>
                      </div>
                    </div></a></li>
                  <li><a onclick="todetailtypeone('233')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">判决书或调解书原件 [律师提交]</div>
                      </div>
                    </div></a></li>
                  <li><a onclick="todetailtypeone('234')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">身份证复印件 [律师提交]</div>
                      </div>
                    </div></a></li>
                </ul>
              </div>
            </div>
          </li>
          <li class="accordion-item"><a href="#" class="item-link item-content">
            <div class="item-inner">
              <div class="item-title">承办执行法官</div>
            </div></a>
            <div class="accordion-item-content">
              <div class="list-block">
                <ul>
                  <li><a onclick="todetailtypetwo('241','请输入执行法官')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">执行法官 [律师提交]</div>
                      </div>
                    </div></a></li>
                  <li><a onclick="todetailtypetwo('242','请输入电话')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">电话 [律师提交]</div>
                      </div>
                    </div></a></li>
                </ul>
              </div>
            </div>
          </li>
          <li class="accordion-item"><a href="#" class="item-link item-content">
            <div class="item-inner">
              <div class="item-title">执行情况</div>
            </div></a>
            <div class="accordion-item-content">
              <div class="list-block">
                <ul>
                  <li><a  onclick="todetailtypeone('251')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">保全及执行情况描述 [律师提交]</div>
                      </div>
                    </div></a></li>
                </ul>
              </div>
            </div>
          </li>
          <li class="accordion-item"><a href="#" class="item-link item-content">
            <div class="item-inner">
              <div class="item-title">是否需要执行终结后</div>
            </div></a>
            <div class="accordion-item-content">
              <div class="list-block">
                <ul>
                  <li><a onclick="todetailtypeone('261')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">恢复执行 [律师提交]</div>
                      </div>
                    </div></a></li>
                  <li><a onclick="todetailtypeone('262')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">执行笔录 [律师提交]</div>
                      </div>
                    </div></a></li>
                </ul>
              </div>
            </div>
          </li>
          <li class="accordion-item"><a href="#" class="item-link item-content">
            <div class="item-inner">
              <div class="item-title">执行完结</div>
            </div></a>
            <div class="accordion-item-content">
              <div class="list-block">
                <ul>
                  <li><a onclick="todetailtypeone('271')" href="#" class="item-link">
                    <div class="item-content">
                      <div class="item-media"></div>
                      <div class="item-inner">
                        <div class="item-title">执行终结裁定书 [律师提交]</div>
                      </div>
                    </div></a></li>
                </ul>
              </div>
            </div>
          </li>
        </ul>
      </div>
    </div>
  </div>
  <jsp:include page="../common/foot.jsp">
    <jsp:param name="from" value="affairs"/>
  </jsp:include>
</div>
</body>
<script type="text/javascript" src="<%=basePath %>/res/dist/js/framework7.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/js/common.js"></script>
<script type="text/javascript" src="<%=basePath %>/res/js/affairs/disputedetail.js"></script>

<script>
  var myApp = new Framework7();
</script>
</html>
// 基本路径
var lawyerPage;
var basePath = $("#basePath").val();
layui.use(['laypage'], function () {
    lawyerPage = layui.laypage;
    // 引入分页组件后执行查询
    queryLawyer(0);
});
var mycartone = '';
var mycarttwo = '';
var mycartthree = '';
var modal = $("#order_dialog").html();

var keywords = "";
var casetype = "";
var casetypefree = "";

function queryLawyer(pageNum) {
    LSFetch({
        url: basePath + '/c_querysearchlawyer.htm',
        data: {
            pageNum: pageNum,
            pageSize: 9,
            keywords: keywords,
            casetype: casetype,
            casetypefree: casetypefree
        },
        success: function (res) {
            var html = '';
            var htmls = '';
            if (res.data.length > 0) {
                for (var i = 0; i < res.data.length; i++) {
                    var code = res.data[i].code;
                    var year = code === null || code === "" ? 0 : new Date().getFullYear() - parseInt(code.substr(5, 4));
                    var name = res.data[i].name;
                    var photo = res.data[i].photo === null || res.data[i].photo === "" ? basePath + "res/img/profile-avatar.jpg" : res.data[i].photo;
                    var experience = res.data[i].experience;
                    var score = getScore(res.data[i].score);
                    var institution = res.data[i].institution;
                    var defendant = getDefendantMapValue(res.data[i].experience.roleCount);
                    var mobile = res.data[i].mobile;
                    var lawyerId = res.data[i].lawyerId;
                    if (pageNum === 0 && (i === 0 || i === 1 || i === 2)) {
                        html += '<div class="panel lawyer_mian"> ' +
                            '                    <div class="panel-body"> ' +
                            '                        <div class="lawyer_info clearfix"> ' +
                            '                            <div class="lawyer_img"><img class="user_img" src="' + photo + '"></div> ' +
                            '                            <ul class="lawyer_user_info">' +
                            '                                <li style="font-size:18px">' + name + '律师</li> ' +
                            '                                <li>高级律师顾问</li> ' +
                            '                                <li><img src="' + score + '" height="16"></li> ' +
                            '                                <li><span style="color:orange">极力推荐</span></li> ' +
                            '                            </ul> ' +
                            '                            <ul class="lawyer_user_info"> ' +
                            '                                <li>' + institution + '</li> ' +
                            '                                <li>执业<span style="color:orange;padding:0 5px">' + year + '</span>年</li> ' +
                            '                                <li>为被告方辩护<span style="color:orange;padding:0 5px">' + defendant + '</span>起</li> ' +
                            '                            </ul> ' +
                            '                            <div class="flr" style="margin-top:50px"> ' +
                            '                                <button type="button" class="btn btn-primary" onclick="toPastCases(' + "'" + code + "','" + name + "','" + institution + "'" + ')"><i class="icon-book"></i> 过往案例</button> ' +
                            '                                <button type="button" class="btn btn-success" onclick="toCall(' + lawyerId + "," + mobile + ')"><i class="icon-phone"></i> 电话咨询</button> ' +
                            '                                <button type="button" class="btn btn-info" onclick="appointMeet(' + lawyerId + ')"><i class="icon-calendar"></i> 预约会面 </button> ' +
                            '                                <button type="button" class="btn btn-info" onclick="importantMatter(' + lawyerId + ')"><i class="icon-external-link"></i> 发起委托 </button>' +
                            '                            </div> ' +
                            '                        </div> ' +
                            '                        <div class="clearfix" style="text-align: center;"> ' +
                            '                            <div id="main' + (1 + i * 3 ) + '" style="width:24%;height:150px;display:inline-block"></div> ' +
                            '                            <div id="main' + (2 + i * 3) + '" style="width:24%;height:150px;display:inline-block"></div> ' +
                            '                            <div id="main' + (3 + i * 3) + '" style="width:24%;height:150px;display:inline-block"></div> ' +
                            '                        </div>' +
                            '                    </div>' +
                            '                </div>';
                        getWhoExperience(i, experience);
                    } else {
                        htmls += '<div class="col-md-4 col-sm-6">' +
                            '                        <div class="panel">' +
                            '                            <div class="panel-body">' +
                            '                                <div class="lawyer_info clearfix">' +
                            '                                    <h4>' + name + '律师<span>高级律师顾问</span><img src="' + score + '" height="12"></h4>' +
                            '                                    <div class="lawyer_img"><img class="user_img" src="' + photo + '"></div>' +
                            '                                    <ul class="lawyer_user_info">' +
                            '                                        <li>' + institution + '</li>' +
                            '                                        <li>执业<span style="color:orange;padding:0 5px">' + year + '</span>年</li>' +
                            '                                        <li>为被告方辩护<span style="color:orange;padding:0 5px">' + defendant + '</span>起</li>' +
                            '                                    </ul>' +
                            '                                    <div class="lawyer_toolbar">' +
                            '                                        <button type="button" class="btn btn-primary" onclick="toPastCases(' + "'" + code + "','" + name + "','" + institution + "'" + ')"><i class="icon-book"></i> 案例</button>' +
                            '                                        <button type="button" class="btn btn-success" onclick="toCall(' + lawyerId + "," + mobile + ')"><i class="icon-phone"></i> 咨询</button>' +
                            '                                        <button type="button" class="btn btn-info" onclick="appointMeet(' + lawyerId + ')"><iclass="icon-calendar"></i> 预约</button>' +
                            '                                        <button type="button" class="btn btn-info" onclick="importantMatter(' + lawyerId + ')"><i class="icon-external-link"></i> 委托</button>' +
                            '                                    </div>' +
                            '                                </div>' +
                            '                            </div>' +
                            '                        </div>' +
                            '      </div>';
                    }
                }
                html += '<div class="clearfix" style="margin:0 -15px">';
                html += htmls;
                html += '</div>';
            } else {
                html += '<div style="text-align:center;padding:200px 0;font-size:16px">搜索律师结果为空！</div>';
            }
            $("#bodyDiv").html(html);
            if (pageNum === 0) {
                if (res.data.length >= 3) {
                    loadMyChart(0, mycartone);
                    loadMyChart(1, mycarttwo);
                    loadMyChart(2, mycartthree);
                }
                if (res.data.length >= 2) {
                    loadMyChart(0, mycartone);
                    loadMyChart(1, mycarttwo);
                }
                if (res.data.length >= 1) {
                    loadMyChart(0, mycartone);
                }
            }
            lawyerPage({
                cont: 'page',
                pages: res.totalPage,
                skin: '#5FB878',
                curr: pageNum + 1,
                jump: function (obj, first) {
                    if (!first) {
                        queryLawyer(obj.curr - 1);
                    }
                }
            });
        }
    });
}

loadOneType("firstType");

function toSearch() {
    keywords = $("input[name='lawyerName']").val();
    if ($("select[name='firstType']").val() === "1a") {
        casetypefree = $("#secondType input").val();
    }
    if ($("select[name='secondType'] option:selected").attr("data-id") === "-2") {
        casetype = $("select[name='firstType'] option:selected").val();
    } else {
        casetype = $("select[name='secondType'] option:selected").val();
    }
    queryLawyer(0);
}

function loadOneType(name) {
    var html = "";
    LSFetch({
        url: basePath + '/c_queryfirstdisputestype.htm',
        success: function (data) {
            if (name === "firstType") {
                html += "<option value=''> 全部 </option>";
            }
            if (data.length > 0) {
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i].code + '" data-id="' + data[i].id + '">' + data[i].name + '</option>';
                }
            }
            html += '<option value="1a" data-id="-1">自定义类型</option>';
            $("select[name='" + name + "']").html(html);
            if (name === "firstType") {
                loadTwoType("-2", "secondType");
            } else if (name === "firstTypes") {
                loadTwoType($("select[name='" + name + "'] option:selected").attr("data-id"), "secondTypes");
            } else if (name === "firstTypez") {
                loadTwoType($("select[name='" + name + "'] option:selected").attr("data-id"), "secondTypez");
            }
        }
    });
}

function changeType(obj, name) {
    if ($(obj).find("option:selected").val() !== "") {
        loadTwoType($(obj).find("option:selected").attr("data-id"), name);
    } else {
        loadTwoType("-2", name);
    }
}

function loadTwoType(id, name) {
    var html = '';
    if (id === "-1") {
        html += '<input type="text" class="form-control">';
        $("#" + name).html(html);
    } else if (id === "-2") {
        html += '<select class="form-control" name="' + name + '">';
        html += '<option value="">全部</option>';
        html += '</select>';
        $("select[name='" + name + "']").html(html);
        $("#" + name).html(html);
    } else {
        LSFetch({
            url: basePath + '/c_queryseconddisputestype.htm',
            data: {
                id: id
            },
            success: function (data) {
                html += '<select class="form-control" name="' + name + '">';
                if (data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        html += '<option value="' + data[i].code + '" data-id="' + data[i].id + '">' + data[i].name + '</option>';
                    }
                } else {
                    html += '<option value="" data-id="-2">暂无相关类型</option>';
                }
                html += '</select>';
                $("select[name='" + name + "']").html(html);
                $("#" + name).html(html);
            }
        });
    }
}

function toPastCases(id, name, institution) {
    var code = id === null ? "" : id;
    window.location.href = basePath + "c_topastcases.htm" + "?code=" + id + "&name=" + name + "&institution=" + institution + "&type=" + 3;
}

function appointMeet(id) {
    if (id === 0) {
        showerror("没有律师信息");
        return;
    }
    $('#order_dialog').html(modal);
    $("#order_dialog input[name='lawyerId']").val(id);
    $('#order_dialog').modal('show');
    loadOneType("firstTypes");
    $(".search_datetime").datetimepicker({
        format: 'yyyy-mm-dd hh:ii:00',
        weekStart: 1,
        autoclose: true,
        language: 'zh',
        todayBtn: true,
        viewSelect: 'hour'
    });
}

function appointMeetSave() {
    if (!$("#appointForm").valid() | !dataValid("meetTime")) {
        return;
    }
    var consultType = '';
    if ($("select[name='firstTypes'] option:selected").attr("data-id") === "-1") {
        consultType = $("#secondTypes input").val();
    } else {
        consultType = ($("select[name='secondTypes'] option:selected").attr("data-id") === "-2") ? $("select[name='firstTypes'] option:selected").text() : $("select[name='secondTypes'] option:selected").text();
    }
    var appointMeet = {
        type: "3",
        consultType: consultType,
        desc: $("#order_dialog input[name='desc']").val(),
        meetTime: $("#order_dialog input[name='meetTime']").val(),
        meetAddress: $("#order_dialog input[name='meetAddress']").val(),
        lawyerId: $("#order_dialog input[name='lawyerId']").val()
    };
    LSFetch({
        url: basePath + '/c_addappointmeet.htm',
        data: JSON.stringify(appointMeet),
        contentType: "application/json;charset=utf-8",
        success: function (data) {
            if (data === -8) {
                showerror("当前VIP没有权限或次数已达上限");
                return;
            }
            if (data === 1) {
                $('#order_dialog').modal('hide');
                showerror("预约成功,请等待律师回复");
            }
        }
    });
}

function toCall(lawyerId, mobile) {
    if (lawyerId === 0 || mobile === null) {
        showerror("没有律师信息");
        return;
    }
    LSFetch({
        url: basePath + '/c_initiatecall.htm',
        data: {
            call: mobile,
            lawyerId: lawyerId,
            type: "3"
        },
        success: function (data) {
            var errorCode = {
                2: "您的手机号码格式错误",
                3: "律师手机号码格式错误",
                8: "手机号码相同",
                11: "运营商线路故障,请重试或联系我们",
                12: "短时间存在相同的呼叫,请勿重复发起"
            };
            if (data === -8) {
                showerror("当前VIP没有权限或次数已达上限");
                return;
            }
            if (data === -1) {
                showerror("短时间内发起大量无效呼叫,帐号被临时冻结,请稍候再试");
                return
            }
            if (data === 0) {
                showerror("正在为您接通双方通话,请耐心等待");
                return;
            }
            if (data === 2 || data === 3 || data === 8 || data === 11 || data === 12) {
                showerror(errorCode[data]);
            } else {
                showerror("系统错误");
            }
        }
    });
}

function importantMatter(id) {
    $("#disputes_dialog input[name='lawyerId']").val(id);
    $("#disputes_dialog").modal('show');
    loadOneType("firstTypez");
}

function importantMatterSave() {
    if (!$("#disputesForm").valid()) {
        return;
    }
    var consultType = '';
    if ($("select[name='firstTypez'] option:selected").attr("data-id") === "-1") {
        consultType = $("#secondTypez input").val();
    } else {
        consultType = ($("select[name='secondTypez'] option:selected").attr("data-id") === "-2") ?
            $("select[name='firstTypez'] option:selected").text() : $("select[name='secondTypez'] option:selected").text();
    }
    var importantMatter = {
        consultType: consultType,
        desc: $("#disputes_dialog input[name='desc']").val(),
        lawyerId: $("#disputes_dialog input[name='lawyerId']").val()
    };
    LSFetch({
        url: basePath + '/c_addimportantmatter.htm',
        data: JSON.stringify(importantMatter),
        contentType: "application/json;charset=utf-8",
        success: function (data) {
            if (data === -8) {
                showerror("当前VIP没有权限或次数已达上限");
                return;
            }
            if (data >= 1) {
                $('#disputes_dialog').modal('hide');
                showerror("提交成功,请等待律师回复");
            }
        }
    });
}


function getWhoExperience(i, data) {
    switch (i) {
        case 0:
            mycartone = data;
            break;
        case 1:
            mycarttwo = data;
            break;
        case 2:
            mycartthree = data;
            break;
        default:
            showerror("网络错误");
            break;
    }
}

function getDefendantMapValue(map) {
    var defendant = "";
    $.each(map, function (key, value) {
        if (key === "被告") {
            defendant = value;
        }
    });
    return defendant === "" ? 0 : defendant;
}

var scoreUrl = {
    "0": basePath + "/res/img/star0.png",
    "1": basePath + "/res/img/star1.png",
    "2": basePath + "/res/img/star2.png",
    "3": basePath + "/res/img/star3.png",
    "4": basePath + "/res/img/star4.png",
    "5": basePath + "/res/img/star5.png"
};

function getScore(score) {
    return scoreUrl[score];
}

function loadMyChart(i, data) {
    var resultData = {
        1: data.courtCount,
        2: data.roleCount,
        3: data.casetypeCount
    };
    for (var j = 1; j <= 3; j++) {
        myChart(i, j, resultData[j]);
    }
}

function myChart(i, j, data) {
    var text = {
        1: "法院分布图",
        2: "原告/被告分布图",
        3: "案件类型分布图"
    };
    var resultData = [];
    var oneValue = {};
    $.each(data, function (key, value) {
        oneValue = {value: value, name: key};
        resultData.push(oneValue);
    });
    var id = "main" + (j + i * 3);
    var myChart = echarts.init(document.getElementById(id));
    var option = {
        title: {
            text: text[j],
            x: 'center',
            textStyle: {fontSize: 14, color: '#666'}
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        series: [
            {
                name: '访问来源',
                type: 'pie',
                radius: '55%',
                center: ['50%', '60%'],
                data: resultData,
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    myChart.setOption(option);
}
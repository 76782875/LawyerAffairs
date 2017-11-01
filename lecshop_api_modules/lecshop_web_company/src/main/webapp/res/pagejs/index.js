// 基本路径
var basePath = $("#basePath").val();

var initDataTable = function () {
    LSFetch({
        url: basePath + '/c_queryindexinfo.htm',
        success: function (data) {
            data.consumptionToday === null || data.consumptionToday === "" ? countUp(0, "count3") : countUp(data.consumptionToday, "count3");
            data.totalConsumption === null || data.totalConsumption === "" ? countUp(0, "count4") : countUp(data.totalConsumption, "count4");
            var address = data.user.companyInfo.address === "" || data.user.companyInfo.address === null ? "" : data.user.companyInfo.address;
            var fax = data.user.companyInfo.fax === "" || data.user.companyInfo.fax === null ? "" : data.user.companyInfo.fax;
            var vip = {
                "0": "试用期法律服务",
                "1": "创业期法律服务",
                "2": "经营期法律服务",
                "3": "爆发期法律服务",
                "4": "法律服务已过期"
            };
            var html = '<p>昵称:　' + data.user.name + '</p><p>公司:　' + data.user.companyInfo.name + '（' + vip[data.user.companyInfo.vipType] + '）</p>' +
                '<p>电话:　' + data.user.mobile + '</p><p>地址:　' + address + '</p><p>传真:　' + fax + '</p>';
            $(".home_user_info").html(html);
            $("#num").text("您有" + data.toDoAffairs.length + "条未处理事务");
            var list = [];
            if (data.toDoAffairs.length > 0) {
                for (var i = 0; i < data.toDoAffairs.length; i++) {
                    list.push(data.toDoAffairs[i]);
                }
            }
            $("#tbody").html(dealData(list));
            $('.dataTable').dataTable({
                "bSort": false,
                "iDisplayLength": 8
            });
            $('.dataTables_length,.dataTables_filter').remove();
        }
    });
};

// 初始化列表
initDataTable();

function countUp(count, className) {
    var div_by = 100,
        speed = Math.round(count / div_by),
        $display = $('.' + className),
        run_count = 1,
        int_speed = 24;
    var int = setInterval(function () {
        if (run_count < div_by) {
            $display.text(speed * run_count);
            run_count++;
        } else if (parseInt($display.text()) < count) {
            var curr_count = parseInt($display.text()) + 1;
            $display.text(curr_count);
        } else {
            clearInterval(int);
        }
    }, int_speed);
}

function dealData(list) {
    var html = "";
    if (list.length > 0) {
        $.each(list, function (index, toDoAffair) {
            html += '<tr><td>' + toDoAffair.lawyerName + '</td><td>' + affairType[toDoAffair.type][toDoAffair.status] + '</td><td>' + toDoAffair.time + '</td>' +
                '<td>' + type[toDoAffair.type] + '</td><' +
                'td><button type="button" class="btn btn-info" onclick="toDoAffair(' + toDoAffair.type + "," + toDoAffair.meetType + "," + toDoAffair.affairType + ')">去处理</button>' +
                '</td></tr>';
        });
    }
    return html;
}


function toDoAffair(type, meetType, affairType) {
    var urlParam = 'firstMenus=3&secondMenus=51&thirdMenus=52';
    if (type !== 5) {
        url = url[type] + ((type === 3 || type === 4 || type === 6) ? "?" : "&") + urlParam;
    } else {
        url = (affairType === 1 ? (url2[meetType] + "&" ) : (url1[affairType] + "?")) + urlParam;
    }
    window.location.href = basePath + url;
}

var url = {
    1: "c_tomodifyanddraftdoc.htm?url=c_querydraftdoc",
    2: "c_tomodifyanddraftdoc.htm?url=c_querymodifydoc",
    3: "c_tolawyerletters.htm",
    4: "c_todisputesservice.htm",
    6: "c_toimportantmatter.htm"
};

var url1 = {
    2: "c_todisputesappointmeet.htm",
    3: "c_toimportantappointmeet.htm"
};

var url2 = {
    1: "c_tocompanyappointmeet.htm?meetType=1",
    2: "c_tocompanyappointmeet.htm?meetType=2"
};

var type = {
    "1": "草拟文书",
    "2": "修改文书",
    "3": "律师函",
    "4": "公司纠纷",
    "5": "预约会面",
    "6": "重大事项"
};

// 0 刚提交 待律师草拟 1 律师草拟文书中  2 待用户确认 3 已完成
var affairStatus1 = {
    "0": "待律师草拟",
    "1": "律师草拟中",
    "2": "待用户确认",
    "3": "事务已完成"
};
// 0 刚提交 待律师修改 1 律师修改文书中  2 待用户确认  3已完成',
var affairStatus2 = {
    "0": "待律师修改",
    "1": "律师修改中",
    "2": "待用户确认",
    "3": "事务已完成"
};
// 0 刚提交 待律师修改 1 律师修改文书中  2 待用户确认  3 已完成
var affairStatus3 = {
    "0": "待律师修改",
    "1": "律师修改中",
    "2": "待用户确认",
    "3": "事务已完成"
};
// 0 待律师接单  1 拒绝接单 2 待用户付费 3 进行中  4 待用户确认  5 已完成
var affairStatus4 = {
    "0": "待律师接单",
    "1": "律师已拒绝",
    "2": "待用户付费",
    "3": "事务进行中",
    "4": "待用户确认",
    "5": "事务已完成"
};
// 状态 0 待律师确认 1 律师拒绝 2 律师接单 3 取消订单 4 律师催促 5 已完成
var affairStatus5 = {
    "0": "待律师确认",
    "1": "律师已拒绝",
    "2": "律师已确认",
    "3": "用户已取消",
    "4": "催用户确认",
    "5": "事务已完成"
};
// 0 待律师接单 1 拒绝接单 2 待用户付费 3 进行中  4 待用户确认  5 已完成
var affairStatus6 = {
    "0": "待律师接单",
    "1": "律师已拒绝",
    "2": "待用户付费",
    "3": "事务进行中",
    "4": "待用户确认",
    "5": "事务已完成"
};
var affairType = {
    "1": affairStatus1,
    "2": affairStatus2,
    "3": affairStatus3,
    "4": affairStatus4,
    "5": affairStatus5,
    "6": affairStatus6
};

var basePath = $("#basePath").val();
var myApp = new Framework7();

var $$ = Dom7;

// 总页数
var totalPage = 0;

// 当前页
var pageNum = 0;

var loading = false;

fetchData();


var resutlstatus = {
    "0": "申请中",
    "1": "审核通过",
    "2": "拒绝",
    "3": "已打款"
};

$$('.infinite-scroll').on('infinite', function () {

    if (loading) return;

    loading = true;

    fetchData();
})

var meettype = {
    "1": "公司事务",
    "2": "公司纠纷",
    "3": "公司重大事项"
}

// 获取数据
function fetchData() {
    $.ajax({
        url: basePath + '/queryappointmeets.htm',
        type: 'post',
        data: {'pageNum': pageNum},
        success: function (res) {
            console.log(res);
            totalPage = res.totalPage;
            pageNum++;

            loading = false;

            var htmls = '';

            for (var i = 0; i < res.data.length; i++) {
                var appointmeet = res.data[i];
                htmls += '<div class="card ks-card-header-pic">' +
                    '<div class="card-content">' +
                    '<div class="card-content-inner">' +
                    '<p class="color-gray">' + appointmeet.createTime + '</p>' +
                    '<p>业务类型：' + meettype[appointmeet.type] + '</p>' +
                    '<p>类型：' + appointmeet.consultType + '</p>' +
                    '<p>公司名称：' + appointmeet.companyInfo.name + '</p>' +
                    '<p>预约时间：' + appointmeet.meetTime + '</p>' +
                    '<p>预约地点：' + appointmeet.meetAddress + '</p>' +
                    '<p>描述：' + appointmeet.desc + '</p>' +
                    '</div>' +
                    '</div>' +
                    '<div class="card-footer" style="justify-content:flex-end"><a href="#" onclick="grabAppointMeet(' + appointmeet.id + ')" class="button active qd_btn">抢单</a></div> </div>';
            }

            $(".page-content").append(htmls);

            // 没有数据 或者只有一页的数据 或者数据到了最后一页
            if (totalPage < 2 || pageNum == totalPage) {
                myApp.detachInfiniteScroll($$('.infinite-scroll'));
                $$('.infinite-scroll-preloader').remove();
            }
        }
    });
}

// 抢单
function grabAppointMeet(id) {
    $.ajax({
        url: basePath + '/grabappointmeet.htm',
        data: {id: id},
        type: 'post',
        success: function (res) {
            if (res == 0) {
                tip('单子已被抢,抢单失败!');
                setTimeout(refreshAppointmeet, 1000);
            } else {
                tip('抢单成功!');
                // 跳转到我的预约会面页面
                setTimeout(refreshAppointmeet, 1000);
            }
        }
    });
}
function refreshAppointmeet() {
    location.reload();
}
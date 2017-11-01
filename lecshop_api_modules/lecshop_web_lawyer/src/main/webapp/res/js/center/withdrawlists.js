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

// 获取数据
function fetchData() {
    $.ajax({
        url: basePath + '/querywithdraws.htm',
        type: 'post',
        data: {'pageNum': pageNum},
        success: function (res) {
            totalPage = res.totalPage;
            pageNum++;

            loading = false;

            var htmls = '';

            for (var i = 0; i < res.data.length; i++) {
                htmls += '<div class="timeline-item"> <div class="timeline-item-divider"></div> <div class="timeline-item-content" style="width:100%"> <div class="timeline-item-inner"> <div class="timeline-item-time">' + res.data[i].createTime + '</div> <p>提现¥' + (Math.round(res.data[i].money * 100) / 100).toFixed(2) + '</p> <p>状态：' + resutlstatus[res.data[i].status] + '</p> </div> </div> </div>';
            }

            $(".timeline .timeline-item:last-child").before(htmls);

            // 没有数据 或者只有一页的数据 或者数据到了最后一页
            if (totalPage < 2 || pageNum == totalPage) {
                myApp.detachInfiniteScroll($$('.infinite-scroll'));
                $$('.infinite-scroll-preloader').remove();
            }
        }
    });
}
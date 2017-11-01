var basePath = $("#basePath").val();
var myApp = new Framework7();

var $$ = Dom7;

// 总页数
var totalPage = 0;

// 当前页
var pageNum = 0;

var loading = false;

fetchData();


$$('.infinite-scroll').on('infinite', function () {

    if (loading) return;

    loading = true;

    fetchData();
})

disputetelstatus = {'0': '待用户确认', '1': '律师催促(待用户确认)', '2': '已完成'};

// 获取数据
function fetchData() {
    $.ajax({
        url: basePath + '/querydisputetels.htm',
        type: 'post',
        data: {'pageNum': pageNum},
        success: function (res) {
            totalPage = res.totalPage;
            pageNum++;

            loading = false;

            var htmls = '';

            for (var i = 0; i < res.data.length; i++) {

                var applybtn = '';

                if (res.data[i].status == '0') {
                    applybtn = '<div class="card-footer" style="justify-content:flex-end"><a href="#" onclick="toapply(' + res.data[i].id + ')" class="button active qd_btn">申请确定</a></div>';
                }

                htmls += '<div class="card ks-card-header-pic">' +
                    '<div class="card-content">' +
                    '<div class="card-content-inner">' +
                    '<p class="color-gray">' + res.data[i].createTime + '</p>' +
                    '<p>公司名称：' + res.data[i].companyInfo.name + '</p>' +
                    '<p>状态：' + disputetelstatus[res.data[i].status] + '</p>'+
                    '</div></div>'+applybtn+' </div>';
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

/**
 * 律师催促
 * @param id
 */
function toapply(id) {
    myApp.confirm('确定?', "提示",
        function () {
            $.ajax({
                url: basePath + '/remindertelconstatus.htm?id=' + id,
                type: 'post',
                success: function () {
                    window.location.reload();
                }
            });
        }
    );
}


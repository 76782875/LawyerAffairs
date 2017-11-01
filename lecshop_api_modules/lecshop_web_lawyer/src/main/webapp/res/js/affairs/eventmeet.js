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

letterStatus = {
    '0': '待律师确认',
    '1': '拒绝',
    '2': '律师确认,会面中',
    '3': '取消订单',
    '4': '待用户确认(律师催促)',
    '5': '已完成'
};

// 获取数据
function fetchData() {
    $.ajax({
        url: basePath + '/queryeventmeets.htm',
        type: 'post',
        data: {'pageNum': pageNum},
        success: function (res) {
            totalPage = res.totalPage;
            pageNum++;

            loading = false;

            var htmls = '';

            for (var i = 0; i < res.data.length; i++) {


                var confirmhtml = '';

                if (res.data[i].status == '0') {
                    confirmhtml = '<a href="#" onclick="torefuse(' + res.data[i].id + ')" style="margin-right: 5px;" class="button active qd_btn">拒绝受理</a><a href="#" onclick="toconfirm(' + res.data[i].id + ')" class="button active qd_btn">确认受理</a>';
                }

                var applyhtml = '';

                if (res.data[i].status == '2') {
                    applyhtml = '<a href="#" onclick="toapply(' + res.data[i].id + ')" style="margin-right: 5px;" class="button active qd_btn">申请确定</a>';
                }

                htmls += '<div class="card ks-card-header-pic">' +
                    '<div class="card-content">' +
                    '<div class="card-content-inner">' +
                    '<p class="color-gray">' + res.data[i].createTime + '</p>' +
                    '<p>公司名称：' + res.data[i].companyInfo.name + '</p>' +
                    '<p>会面地点：' + res.data[i].meetAddress + '</p>' +
                    '<p>会面时间：' + res.data[i].meetTime + '</p>' +
                    '<p>咨询类型：' + res.data[i].consultType + '</p>' +
                    '<p>描述：' + res.data[i].desc + '</p>' +
                    '<p>状态：' + letterStatus[res.data[i].status] + '</p> </div> </div><div class="card-footer" style="justify-content:flex-end">' + confirmhtml +applyhtml+
                    '</div> </div>';
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
                url: basePath + '/remindermeet.htm?id=' + id,
                type: 'post',
                success: function () {
                    window.location.reload();
                }
            });
        }
    );
}



function torefuse(id) {
    myApp.confirm('确定?', "提示",
        function () {
            $.ajax({
                url: basePath + '/refuseeventmeet.htm?id=' + id,
                type: 'post',
                data: {'pageNum': pageNum},
                success: function () {
                    window.location.reload();
                }
            });
        }
    );
}

function toconfirm(id) {
    myApp.confirm('确定?', "提示",
        function () {
            $.ajax({
                url: basePath + '/confirmeventmeet.htm?id=' + id,
                type: 'post',
                data: {'pageNum': pageNum},
                success: function () {
                    window.location.reload();
                }
            });
        }
    );
}
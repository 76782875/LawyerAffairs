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

disputeStatus = {
    '0': '待律师填写金额',
    '1': '拒绝',
    '2': '待用户付费',
    '3': '进行中',
    '4': '流程结束待用户付余款',
    '5': '已完成'
};

// 获取数据
function fetchData() {
    $.ajax({
        url: basePath + '/querydisputes.htm',
        type: 'post',
        data: {'pageNum': pageNum},
        success: function (res) {
            totalPage = res.totalPage;
            pageNum++;

            loading = false;

            var htmls = '';

            for (var i = 0; i < res.data.length; i++) {


                var detailhtml = '';

                if (res.data[i].status != '0' && res.data[i].status != '1' && res.data[i].status != '2') {
                    detailhtml = '<a href="#" onclick="todetal(' + res.data[i].id + ')"  style="margin-right: 5px;"  class="button active qd_btn">查询详情</a>';
                }

                var toendHtml = '';

                if (res.data[i].status == '3') {
                    toendHtml = '<a href="#" onclick="tosetend(' + res.data[i].id + ')"  class="button active qd_btn">设置已完结</a>';
                }


                var confirmhtml = '';

                if (res.data[i].status == '0') {
                    confirmhtml = '<a href="#" onclick="torefuse(' + res.data[i].id + ')" style="margin-right: 5px;" class="button active qd_btn">拒绝受理</a><a href="#" onclick="toconfirm(' + res.data[i].id + ')" class="button active qd_btn">确认受理</a>';
                }

                htmls += '<div class="card ks-card-header-pic">' +
                    '<div class="card-content">' +
                    '<div class="card-content-inner">' +
                    '<p class="color-gray">' + res.data[i].createTime + '</p>' +
                    '<p>咨询类型：' + res.data[i].consultType + '</p>' +
                    '<p>描述：' + res.data[i].desc + '</p>' +
                    '<p>价格：' + res.data[i].price + '</p>' +
                    '<p>状态：' + disputeStatus[res.data[i].status] + '</p> </div> </div><div class="card-footer" style="justify-content:flex-end">' + detailhtml + toendHtml + confirmhtml +
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

function todetal(id) {
    window.location.href = basePath + '/todisputedetail.htm?disputeId=' + id;
}

function torefuse(id) {
    myApp.confirm('确定?', "提示",
        function () {
            $.ajax({
                url: basePath + '/refusedispute.htm?id=' + id,
                type: 'post',
                success: function () {
                    window.location.reload();
                }
            });
        }
    );
}

// 设置已完结
function tosetend(id) {
    myApp.confirm('确定?', "提示",
        function () {
            $.ajax({
                url: basePath + '/setdisputeend.htm?id=' + id,
                type: 'post',
                success: function () {
                    window.location.reload();
                }
            });
        }
    );
}

function toconfirm(id) {
    myApp.prompt('确定受理?', "提示", function (data) {

        if (data == "") {
            tip("请输入金额!");
            return;
        }

        if (!checkMoney(data)) {
            tip("请输入合法金额!");
            return;
        }

        $.ajax({
            url: basePath + '/confirmdispute.htm',
            type: 'post',
            data: {'id': id, 'money': data},
            success: function () {
                window.location.reload();
            }
        });
    });
}
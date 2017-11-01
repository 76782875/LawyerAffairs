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
eventStatus = {
    '0': '待律师填写金额',
    '1': '拒绝',
    '2': '待律师填写事务流程',
    '3': '待用户付费',
    '4': '进行中',
    '5': '流程结束待用户付余款',
    '6': '已完成'
};

// 获取数据
function fetchData() {
    $.ajax({
        url: basePath + '/queryimportantmatters.htm',
        type: 'post',
        data: {'pageNum': pageNum},
        success: function (res) {
            totalPage = res.totalPage;
            pageNum++;

            loading = false;

            var htmls = '';

            for (var i = 0; i < res.data.length; i++) {

                var detailhtml = '';

                if (res.data[i].status != '0' && res.data[i].status != '1' && res.data[i].status != '2' && res.data[i].status != '3') {
                    detailhtml = '<a href="#" onclick="todetal(' + res.data[i].id + ')"  style="margin-right: 5px;"  class="button active qd_btn">查询详情</a>';
                }

                var confirmhtml = '';

                if (res.data[i].status == '0') {
                    confirmhtml = '<a href="#" onclick="torefuse(' + res.data[i].id + ')" style="margin-right: 5px;" class="button active qd_btn">拒绝受理</a><a href="#" onclick="toconfirm(' + res.data[i].id + ')" class="button active qd_btn">确认受理</a>';
                }

                var edithtml = '';

                if (res.data[i].status == '2') {
                    edithtml = '<a href="#" onclick="toedit(' + res.data[i].id + ')" style="margin-right: 5px;" class="button active qd_btn">编辑流程</a>';
                }

                var toendhtml = '';
                if (res.data[i].status == '4') {
                    toendhtml = '<a href="#" onclick="toendset(' + res.data[i].id + ')"   class="button active qd_btn">设置已完结</a>';
                }

                htmls += '<div class="card ks-card-header-pic">' +
                    '<div class="card-content">' +
                    '<div class="card-content-inner">' +
                    '<p class="color-gray">' + res.data[i].createTime + '</p>' +
                    '<p>咨询类型：' + res.data[i].consultType + '</p>' +
                    '<p>描述：' + res.data[i].desc + '</p>' +
                    '<p>价格：' + res.data[i].price + '</p>' +
                    '<p>状态：' + eventStatus[res.data[i].status] + '</p> </div> </div><div class="card-footer" style="justify-content:flex-end">' +edithtml+detailhtml+toendhtml+ confirmhtml +
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
    window.location.href = basePath + '/toimportantmatterdetail.htm?matterId=' + id;
}

function toedit(id) {
    window.location.href = basePath + '/tocustomizeevent.htm?eventId=' + id;
}

function torefuse(id) {
    myApp.confirm('确定?', "提示",
        function () {
            $.ajax({
                url: basePath + '/refuseevent.htm?id=' + id,
                type: 'post',
                success: function () {
                    window.location.reload();
                }
            });
        }
    );
}

function toendset(id) {
    myApp.confirm('确定?', "提示",
        function () {
            $.ajax({
                url: basePath + '/seteventend.htm?id=' + id,
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
            url: basePath + '/confirmevent.htm',
            type: 'post',
            data: {'id': id, 'money': data},
            success: function () {
                window.location.reload();
            }
        });
    });
}

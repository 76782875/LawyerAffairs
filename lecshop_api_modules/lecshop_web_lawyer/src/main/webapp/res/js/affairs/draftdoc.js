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
    '0': '待律师修改',
    '1': '律师修改文书中',
    '2': '待用户确认',
    '3': '已完成'
};

// 获取数据
function fetchData() {
    $.ajax({
        url: basePath + '/querydraftdocs.htm',
        type: 'post',
        data: {'pageNum': pageNum},
        success: function (res) {
            totalPage = res.totalPage;
            pageNum++;

            loading = false;

            var htmls = '';

            for (var i = 0; i < res.data.length; i++) {

                var uploadhtml = '';

                if (res.data[i].status != '0') {
                    uploadhtml = '<a href="#" onclick="toupload(' + res.data[i].id + ')" class="button active qd_btn">上传文书</a>';
                }

                var confirmhtml = '';

                if (res.data[i].status == '0') {
                    confirmhtml = '<a href="#" onclick="toconfirm(' + res.data[i].id + ')" class="button active qd_btn">确认受理</a>';
                }

                htmls += '<div class="card ks-card-header-pic">' +
                    '<div class="card-content">' +
                    '<div class="card-content-inner">' +
                    '<p class="color-gray">' + res.data[i].createTime + '</p>' +
                    '<p>公司名称：' + res.data[i].companyInfo.name + '</p>' +
                    '<p>文书名称：' + res.data[i].name + '</p>' +
                    '<p>状态：' + letterStatus[res.data[i].status] + '</p> </div> </div><div class="card-footer" style="justify-content:flex-end">' +
                    '<a href="#" style="margin-right: 5px;" onclick="todetail(' + res.data[i].id + ')" class="button active qd_btn">查看详情</a>' +
                    uploadhtml + confirmhtml +
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

function toconfirm(id) {
    myApp.confirm('确定?', "提示",
        function () {
            $.ajax({
                url: basePath + '/confirmdraftdoc.htm?docId=' + id,
                type: 'post',
                success: function () {
                    window.location.reload();
                }
            });
        }
    );
}
// 跳转到上传文书
function toupload(id) {
    window.location.href = basePath + "/touploaddraftdoc.htm?docId=" + id
}

// 跳转到详情页
function todetail(id) {
    window.location.href = basePath + "/todraftdocdetails.htm?docId=" + id
}
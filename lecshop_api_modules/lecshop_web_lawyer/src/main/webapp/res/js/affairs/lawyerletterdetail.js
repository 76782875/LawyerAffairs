var basePath = $("#basePath").val();

typemap = {
    '1': '客户',
    '2': '我'
}

fetchDetail();

// 获取律师函详情
function fetchDetail() {
    $.ajax({
        url: basePath + '/querylawyerletterdetails.htm',
        type: 'post',
        data: {'letterId': $("#letterId").val()},
        success: function (res) {
            var htmls = '';

            var downhtml = '';

            for (var i = 0; i < res.length; i++) {

                if (res[i].fileUrl != null && res[i].fileUrl != "") {
                    downhtml = '<p><a href="#" onclick="todownload(\'' + res[i].fileUrl + '\')" class="button active">下载附件</a></p>';
                }else {
                    downhtml='';
                }
                htmls += '<div class="timeline-item">' +
                    '<div class="timeline-item-date">' + typemap[res[i].type] + '</div>' +
                    '<div class="timeline-item-divider"></div>' +
                    '<div class="timeline-item-content" style="width: 100%">' +
                    '<div class="timeline-item-inner">' +
                    '<div class="timeline-item-time">' + res[i].createTime + '</div>' +
                    '<div>' + res[i].desc + '</div> ' +
                    downhtml
                    + '</div> </div> </div>';
            }

            $(".timeline").html(htmls);
        }
    });
}

/**
 * 跳转到下载页面
 */
function todownload(url) {
    window.location.href = basePath + "/todownload.htm?name=" + url;
}



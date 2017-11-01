var basePath = $("#basePath").val();

var myApp = new Framework7();

fetchDetail();

function fetchDetail() {

    $.ajax({
        url: basePath + '/querybydisputeidandcode.htm',
        type: 'post',
        data: {'disputeId': $("#disputeId").val(), 'code': $("#code").val()},
        success: function (res) {
            $("#xFileText").html(res.url);
        }
    });
}

/**
 * 跳转到下载页面
 */
function todownload() {

    var url = $("#xFileText").html();

    if (url == "") {
        tip('没有文件可以下载!');
        return;
    }
    window.location.href = basePath + "/todownload.htm?name=" + url;
}



// 上传文件
function uploadFile() {
    $.ajax({
        url: basePath+'/uploadtolocal.htm',
        type: 'POST',
        cache: false,
        data: new FormData($('#disputeform')[0]),
        processData: false,
        contentType: false
    }).done(function (res) {
        $("#xFileText").html(res.res);
    });
}

// 保存
function save() {
    $.ajax({
        url: basePath + '/updatedisputedetail.htm',
        type: 'post',
        data: {'disputeId': $("#disputeId").val(), 'code': $("#code").val(), 'url': $("#xFileText").html()},
        success: function (res) {
            window.location.href = basePath + 'todisputedetail.htm?disputeId=' + $("#disputeId").val();
        }
    });
}


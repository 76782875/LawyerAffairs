// 基本路径
var basePath = $("#basePath").val();

// 修改文书
function updateModifyDoc() {
    if (!$("#fileForm").valid()) {
        return;
    }
    if ($(".fileupload-preview").text() == "" || $(".fileupload-preview").text() == null) {
        showerror("请先上传文件");
        return;
    }
    $("#fileForm").submit();
    $('iframe').on('load', function () {
        var responseText = $('iframe')[0].contentDocument.body.textContent;
        var responseData = JSON.parse(responseText) || {};
        if (responseData === -8) {
            showerror("当前VIP没有权限或次数已达上限");
            return;
        }
        if (responseData === 1) {
            window.location.href = 'c_tomodifyanddraftdoc.htm?url=c_querymodifydoc';
        }
        showerror("添加失败");
    });
}
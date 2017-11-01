var basePath = $("#basePath").val();

var queryDisputesDetail = function () {
    LSFetch({
        url: basePath + '/c_querydisputesdetails.htm',
        data: {
            disputesId: $("input[name='disputeIdHidden']").val()
        },
        success: function (data) {
            var html = '';
            for (var i = 0; i < data.length; i++) {
                var result = data[i];
                html += '<input type="hidden" ';
                if (result.type === "1") {
                    html += 'name="user"';
                } else {
                    html += 'name="lawyer"';
                }
                html += ' data-id="' + result.id + '" data-type="' + result.type + '" data-desc="' + result.desc + '" data-url="' + result.url + '" data-status="' + result.status + '" data-code="' + result.code + '">'
            }
            $("#queryForm").html(html);
            inputTextValue();
            fileValue();
        }
    });
};

queryDisputesDetail();


function inputTextValue() {
    $("input[name='text']").each(function () {
        $(this).val($("#queryForm input[data-code='" + $(this).attr("data-code") + "']").attr("data-desc"));
    });
}

function fileValue() {
    $(".fileClass").each(function (index, obj) {
        $(obj).text($("#queryForm input[data-code='" + $(obj).attr("data-code") + "']").attr("data-url"));
        if ($(obj).text() === "" || $(obj).text() === null) {
            $(obj).parent().parent("div").removeClass("fileupload-exists").addClass("fileupload-new");
        } else {
            $(obj).parent().parent("div").removeClass("fileupload-new").addClass("fileupload-exists");
        }
    });
}

function disputesDetailSave(code) {
    if ($("#" + code + "Form .fileClass").text() === "" || $("#" + code + "Form .fileClass").text() === null) {
        showerror("请先选择文件");
        return;
    }
    if ($("#" + code + "Form .fileClass").text() === $("#queryForm input[data-code='" + code + "']").attr("data-url")) {
        showerror("请先选择文件!");
        return;
    }
    $("#" + code + "Form").submit();
    $('iframe').on('load', function () {
        var responseText = $('iframe')[0].contentDocument.body.textContent;
        var responseData = JSON.parse(responseText) || {};
        if (responseData === 1) {
            showerror("保存成功");
            queryDisputesDetail();
        } else {
            showerror("保存失败");
        }
    });
}

function disputesDownLoad(code) {
    var url = $("input[data-code='" + code + "']").attr("data-url");
    if (url === "" || url === null) {
        showerror("尚未提交相关文件,无法操作下载");
    } else {
        $("#downForm input[name='name']").val(url);
        $("#downForm").submit();
    }
}
// 基本路径
var basePath = $("#basePath").val();

// 提交草拟文书
function addDraftDoc() {
    if (!$("#draftDocForm").valid()) {
        return;
    }
    LSFetch({
        url: basePath + '/c_adddraftdoc.htm',
        data: {
            name: $("#name").val(),
            desc: $("#desc").val()
        },
        success: function (res) {
            clearInput();
            if (res === -8) {
                showerror("当前VIP没有权限或次数已达上限");
                return;
            }
            if (res === 1) {
                window.location.href = 'c_tomodifyanddraftdoc.htm?url=c_querydraftdoc';
            } else if (res === 0) {
                showerror("添加失败");
            }
        }
    })
}

function clearInput() {
    $("#name").val("");
    $("#desc").val("");
}
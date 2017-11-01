var basePath = $("#basePath").val();


queryLawyer();

// 查询律师信息
function queryLawyer() {
    $.ajax({
        url: basePath + '/querylawyer.htm',
        type: 'get',
        success: function (res) {
        }
    });
}

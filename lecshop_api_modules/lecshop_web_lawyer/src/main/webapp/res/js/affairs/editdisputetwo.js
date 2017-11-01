var basePath = $("#basePath").val();

var myApp = new Framework7();

fetchDetail();

function fetchDetail() {

    $.ajax({
        url: basePath + '/querybydisputeidandcode.htm',
        type: 'post',
        data: {'disputeId': $("#disputeId").val(), 'code': $("#code").val()},
        success: function (res) {
            $("#mytext").val(res.desc);
        }
    });
}


// 保存
function save() {
    $.ajax({
        url: basePath + '/updatedisputedetail.htm',
        type: 'post',
        data: {'disputeId': $("#disputeId").val(), 'code': $("#code").val(), 'desc': $("#mytext").val()},
        success: function (res) {
            window.location.href = basePath + 'todisputedetail.htm?disputeId=' + $("#disputeId").val();
        }
    });
}


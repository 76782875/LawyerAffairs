var basePath = $("#basePath").val();

queryLawyer();

// 查询律师信息
function queryLawyer() {

    $.ajax({
        url: basePath + '/querylawyer.htm',
        type: 'get',
        success: function (res) {
            $("#name").html(res.name);
            $("#lawyersPlace").html(res.lawyersPlace);
            $("#mobile").html(res.mobile);
            $("#address").html(res.address);
            $("#todayIncome").html(res.todayIncome);
            $("#allIncome").html(res.allIncome);

            if (res.pic != "" && res.pic != null) {
                $("#url").attr("src", res.pic);
            } else {
                $("#url").attr("src", basePath + '/res/images/profile-avatar.jpg');
            }
        }
    });
}

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
            if (res.pic != "" && res.pic != null) {
                $("#url").attr("src", res.pic);
            } else {
                $("#url").attr("src", basePath + '/res/images/profile-avatar.jpg');
            }
            $("#qq").html(res.qq);
            $("#email").html(res.email);
            $("#lawyerPic").attr("src", res.lawyerPic);
            $("#code").html(res.code);
        }
    });
}

// 上传头像
function uploadPic() {
    $.ajax({
        url: basePath + '/uploadtoupyun.htm',
        type: 'POST',
        cache: false,
        data: new FormData($('#userform')[0]),
        processData: false,
        contentType: false
    }).done(function (res) {
        $.ajax({
            url: basePath + '/updatelawyer.htm',
            type: 'post',
            data: {
                pic: res
            },
            success: function (result) {
                if (result == 1) {
                    tip('头像修改成功!');
                    $("#url").attr("src", res);
                }
            }
        });
    });
}

// 修改姓名
function updateName() {
    $.ajax({
        url: basePath + '/updatelawyer.htm',
        type: 'post',
        data: {
            name: $("#name").val()
        },
        success: function (result) {
            if (result == 1) {
                window.location.href = basePath + "/touserinfo.htm"
            }
        }
    });
}

// 修改qq
function upateqq() {
    $.ajax({
        url: basePath + '/updatelawyer.htm',
        type: 'post',
        data: {
            qq: $("#qq").val()
        },
        success: function (result) {
            if (result == 1) {
                window.location.href = basePath + "/touserinfo.htm"
            }
        }
    });
}

// 修改邮箱
function updateemail() {

    if (!checkEmail($("#email").val())) {
        tip('请输入正确的邮箱!');
        return;
    }

    $.ajax({
        url: basePath + '/updatelawyer.htm',
        type: 'post',
        data: {
            email: $("#email").val()
        },
        success: function (result) {
            if (result == 1) {
                window.location.href = basePath + "/touserinfo.htm"
            }
        }
    });
}

// 修改律师律所
function updatelawyerplace() {
    $.ajax({
        url: basePath + '/updatelawyer.htm',
        type: 'post',
        data: {
            lawyersPlace: $("#lawyerplace").val()
        },
        success: function (result) {
            if (result == 1) {
                window.location.href = basePath + "/touserinfo.htm"
            }
        }
    });
}

// 修改地址
function updateaddress() {
    $.ajax({
        url: basePath + '/updatelawyer.htm',
        type: 'post',
        data: {
            address: $("#address").val()
        },
        success: function (result) {
            if (result == 1) {
                window.location.href = basePath + "/touserinfo.htm"
            }
        }
    });
}

// 修改提现设置
function updatewitidrawset() {
    $.ajax({
        url: basePath + '/updatewithdrawset.htm',
        type: 'post',
        data: {
            account: $("#account").val(),
            name: $("#name").val()
        },
        success: function (result) {
            if (result == 1) {
                window.location.href = basePath + "/tocenter.htm"
            }
        }
    });
}
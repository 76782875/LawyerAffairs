function tip(msg) {
    $('.views').append('<div class="errorBox"><span class="errorTip">' + msg + '</span></div>');
    $('.errorTip').animate({
        opacity: 1
    }, 500, function () {
        $(this).delay(2000).animate({
            opacity: 0
        }, 500, function () {
            $('.errorBox').remove()
        })
    })
}

/**
 * 校验是否是手机号码
 */
function checkPhone(value) {
    return (/^1(3|4|5|7|8)\d{9}$/.test(value));
}

/**
 * 校验是否是金额
 */
function checkMoney(value) {
    return (/^([0-9][0-9]*(\.[0-9]{1,2})?|0\.(?!0+$)[0-9]{1,2})$/.test(value));
}

/**
 * 校验邮箱
 */
function checkEmail(value) {
    if (value == "") {
        return true;
    }

    return (/^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/.test(value));
}
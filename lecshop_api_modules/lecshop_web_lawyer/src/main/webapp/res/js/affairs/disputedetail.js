var basePath = $("#basePath").val();

/**
 * 下载详情页面
 * @param code
 */
function todetailtypeone(code) {
    window.location.href = basePath + 'toeditdipusteone.htm?disputeId=' + $("#disputeId").val() + "&code=" + code;
}

/**
 * 输入框页面
 */
function todetailtypetwo(code, tip) {
    window.location.href = basePath + 'toeditdisputetwo.htm?disputeId=' + $("#disputeId").val() + "&code=" + code + "&tip=" + tip;
}

/**
 * 日期页面
 * @param code
 */
function todetailtypethree(code) {
    window.location.href = basePath + 'toeditdisputethree.htm?disputeId=' + $("#disputeId").val() + "&code=" + code;
}
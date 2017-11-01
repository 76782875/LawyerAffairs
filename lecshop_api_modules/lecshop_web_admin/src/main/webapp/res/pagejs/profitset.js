// 基本路径
var basePath = $("#basePath").val();

// 初始化列表
var initData = function () {
    $.ajax({
        url: basePath + '/queryprofitset.htm',
        success: function (data) {
            if (data !== "" && data !== null) {
                $("#tab1 input[name='telConMoney']").val(data.telConMoney);
                $("#tab1 input[name='telConCommission']").val(decimalToInt(data.telConCommission));
                $("#tab2 input[name='meetAllMoney']").val(data.meetAllMoney);
                $("#tab2 input[name='meetAllCommisson']").val(decimalToInt(data.meetAllCommisson));
                $("#tab3 input[name='meetMoney']").val(data.meetMoney);
                $("#tab3 input[name='meetCommission']").val(decimalToInt(data.meetCommission));
                $("#tab4 input[name='withdrawTime']").val(data.withdrawTime);
                $("#tab5 input[name='modifyDocMoney']").val(data.modifyDocMoney);
                $("#tab5 input[name='modifyDocCommission']").val(decimalToInt(data.modifyDocCommission));
                $("#tab6 input[name='draftDocMoney']").val(data.draftDocMoney);
                $("#tab6 input[name='draftDocCommission']").val(decimalToInt(data.draftDocCommission));
                $("#tab7 input[name='lawyerLetterMoney']").val(data.lawyerLetterMoney);
                $("#tab7 input[name='lawyerLetterCommission']").val(decimalToInt(data.lawyerLetterCommission));
            }
        }
    });
};

// 初始化列表
initData();

// 刷新列表
var refreshData = function () {
    initData();
};

function saveBtn(num) {
    if (!$("#form" + num).valid()) {
        return;
    }
    var commission = $("#form" + num).find(".moneyRange").val();
    if (commission > 100 | commission < 0) {
        showerror("佣金百分比应在0至100之间");
        return;
    }
    var profitSet = {
        telConMoney: $("#tab1 input[name='telConMoney']").val(),
        telConCommission: intToDecimal($("#tab1 input[name='telConCommission']").val()),
        meetAllMoney: $("#tab2 input[name='meetAllMoney']").val(),
        meetAllCommisson: intToDecimal($("#tab2 input[name='meetAllCommisson']").val()),
        meetMoney: $("#tab3 input[name='meetMoney']").val(),
        meetCommission: intToDecimal($("#tab3 input[name='meetCommission']").val()),
        withdrawTime: $("#tab4 input[name='withdrawTime']").val(),
        modifyDocMoney: $("#tab5 input[name='modifyDocMoney']").val(),
        modifyDocCommission: intToDecimal($("#tab5 input[name='modifyDocCommission']").val()),
        draftDocMoney: $("#tab6 input[name='draftDocMoney']").val(),
        draftDocCommission: intToDecimal($("#tab6 input[name='draftDocCommission']").val()),
        lawyerLetterMoney: $("#tab7 input[name='lawyerLetterMoney']").val(),
        lawyerLetterCommission: intToDecimal($("#tab7 input[name='lawyerLetterCommission']").val())
    };
    LSFetch({
        url: basePath + '/editprofitset.htm?type=' + num,
        data: JSON.stringify(profitSet),
        contentType: "application/json;charset=utf-8",
        success: function (data) {
            if (data === 1) {
                $('#success_dialog').modal('show');
                refreshData();
            } else {
                showerror("编辑出错");
            }
        }
    });
}

//小数转整数
function decimalToInt(value) {
    return parseFloat(value) * 100
}

//整数转小数
function intToDecimal(value) {
    return parseFloat(value) / 100
}

// 基本路径
var basePath = $("#basePath").val();

var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/c_queryrenewrecord.htm',
                data: dataTableAjaxData(data),
                success: function (res) {
                    callback(res);
                }
            });
        },
        "columns": [
            {
                "name": "orderNo", "data": "orderNo"
            },
            {
                "name": "renewMoney", "data": "renewMoney"
            },
            {
                "name": "orderName", "data": "orderName"
            },
            {
                "data": function (row) {
                    if (row.status === '0') {
                        return '<span class="label label-default">未支付</span>';
                    } else {
                        return '<span class="label label-success">已支付</span>';
                    }
                }
            },
            {
                "name": "createTime", "data": "createTime"
            }, {
                "data": function (row) {
                    var param = '';
                    if (row.status === '0') {
                        param = '<div class="operation_box"><button class="btn btn-primary btn-xs" onclick="toPay(' + row.orderNo + ')"><i class="icon-credit-card"></i>支付</button></div>';
                    }
                    return param;
                }
            }
        ],
        ordering: false
    })
};

// 初始化列表
initDataTable();

function toPay(orderNo) {
    $("#goOnPayForm input[name='orderNo']").val(orderNo);
    $("#goOnPayForm").submit();
}
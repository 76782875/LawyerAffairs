// 基本路径
var basePath = $("#basePath").val();

var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/queryrenewrecordlist.htm',
                data: dataTableAjaxData(data, $("#queryform").serializeArray()),
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
                "name": "type", "data": "orderName"
            },

            {
                "name": "name", "data": "companyName"
            },

            {
                "name": "renewname", "data": "renewName"
            },

            {
                "name": "renewMoney", "data": "renewMoney"
            },

            {
                "name": "createTime", "data": "createTime"
            },

            {
                "name": "status", "data": function (row) {
                    if (row.status == 0) {
                        return '<span class="label label-default">未支付</span>';
                    } else if (row.status == 1) {
                        return '<span class="label label-success">已支付</span>';
                    }
                }
            }
        ],
        ordering: false
    })
};

// 初始化列表
initDataTable();

// 刷新列表
var refreshDataTable = function () {
    $('#dataTable').DataTable().ajax.reload();
};

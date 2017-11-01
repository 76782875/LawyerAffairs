// 基本路径
var basePath = $("#basePath").val();

var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/querytransactionrecord.htm',
                data: dataTableAjaxData(data, $("#queryform").serializeArray()),
                success: function (res) {
                    callback(res);
                }
            });
        },
        "columns": [
            {
                "name": "lawyerName", "data": function (row) {
                    return row.lawyer.name
                }
            },

            {
                "name": "money", "data": function (row) {
                    if (row.type == 1) {
                        return '<span style="color: green;">+'+ row.money + '</span>';
                    } else if (row.type == 2) {
                        return '<span style="color: red;">-' + row.money + '</span>';
                    }
                }
            },

            {
                "name": "type", "data": function (row) {
                    if (row.type == 1) {
                        return '<span class="label label-success">收入</span>';
                    } else if (row.type == 2) {
                        return '<span class="label label-default">支出</span>';
                    }
                }
            },

            {
                "name": "createTime", "data": "createTime"
            },

            {
                "name": "desc", "data": function (row) {
                    return row.desc;
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

// 基本路径
var basePath = $("#basePath").val();
var initDataTable = function () {
    $("#dataTable").lsDataTable({
        "ajax": function (data, callback) {
            LSFetch({
                url: basePath + '/queryvipmodifyrecord.htm',
                data: dataTableAjaxData(data, $("#queryform").serializeArray()),
                success: function (res) {
                    callback(res);
                }
            });
        },
        "columns": [
            {
                "name": "companyName", "data": "companyName"
            },
            {
                "name": "managerName", "data": "managerName"
            },
            {
                "data": function (row) {
                    if (row.oldVip === "0") {
                        return "试用期法律服务";
                    }
                    if (row.oldVip === "1") {
                        return "创业期法律服务";
                    }
                    if (row.oldVip === "2") {
                        return "经营期法律服务";
                    }
                    if (row.oldVip === "3") {
                        return "爆发期法律服务";
                    }
                    if (row.oldVip === "4") {
                        return "法律服务已过期";
                    }
                }
            },
            {
                "data": function (row) {
                    if (row.newVip === "0") {
                        return "试用期法律服务";
                    }
                    if (row.newVip === "1") {
                        return "创业期法律服务";
                    }
                    if (row.newVip === "2") {
                        return "经营期法律服务";
                    }
                    if (row.newVip === "3") {
                        return "爆发期法律服务";
                    }
                    if (row.newVip === "4") {
                        return "法律服务已过期";
                    }
                }
            },
            {
                "name": "remark", "data": "remark"
            },
            {
                "name": "createTime", "data": "createTime"
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

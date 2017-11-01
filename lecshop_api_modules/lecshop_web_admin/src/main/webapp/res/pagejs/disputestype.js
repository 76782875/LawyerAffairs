// 将新增的弹出层放入内存中
var addhtml = $("#add_dialog").html();

//新增时候防止重复提交
var typeacount = 0;
// 修改时候防止重复提交
var typeucount = 0;

function clearCount() {
    typeacount = 0;
    typeucount = 0;
}

// 基本路径
var basePath = $("#basePath").val();
var initDataTable = function () {
    var html = "";
    LSFetch({
        url: basePath + '/querydisputestype.htm',
        success: function (res) {
            if (res.length > 0) {
                for (var i = 0; i < res.length; i++) {
                    html += '<tr class="even';
                    if (res[i].parentId !== 0) {
                        html += ' collapsed"';
                    } else {
                        html += '"';
                    }
                    html += '><input type="hidden" value="' + res[i].parentId + '" name="parentId">';
                    html += '<td>&nbsp;&nbsp;' + res[i].name + '</td><td>' + res[i].code + '</td><td>' + res[i].sort + '</td>';
                    html += '<td width="140" class="operation_box"> ' +
                        '<button class="btn btn-primary btn-xs" onclick="toupdate(' + res[i].id + ',' + "'" + res[i].name + "'" + ',' + res[i].parentId + ',' + res[i].sort + ',' + res[i].code + ')"><i class="icon-pencil"></i> 编辑</button> ' +
                        '<button class="btn btn-danger btn-xs del_btn" onclick="todelete(' + res[i].id + ',' + res[i].parentId + ')"><i class="icon-trash"></i> 删除</button> </td> </tr>';
                    $("#treetable").html(html);
                    var maps = new Array();
                    var num = "";
                    $("#treetable input[name='parentId']").each(function (i) {
                        if ($(this).val() == "0") {
                            maps.push($(this).val());
                            num = i + 1;
                        } else {
                            maps.push(num + "");
                        }
                    });
                    $("#treetable").jqTreeTable(maps, {
                        openImg: basePath + "res/img/TreeTable/tv-collapsable.gif",
                        shutImg: basePath + "res/img/TreeTable/tv-expandable.gif",
                        leafImg: basePath + "res/img/TreeTable/tv-item.gif",
                        lastOpenImg: basePath + "res/img/TreeTable/tv-collapsable.gif",
                        lastShutImg: basePath + "res/img/TreeTable/tv-expandable.gif",
                        lastLeafImg: basePath + "res/img/TreeTable/tv-item-last.gif",
                        vertLineImg: basePath + "res/img/TreeTable/vertline.gif",
                        blankImg: basePath + "res/img/TreeTable/blank.gif",
                        collapse: false,
                        column: 0,
                        striped: true,
                        highlight: true,
                        state: false
                    });
                }
            } else {
                $("#treetable").html('<tr><td colspan="4" style="text-align: center">暂无数据</td></tr>');
            }
        }
    });
};

// 初始化列表
initDataTable();

function addtype() {
    $('#add_dialog').html(addhtml);
    $('#add_dialog').modal('show');
}

// 新增公司纠纷类型
function addDisuptesType() {
    if (typeacount === 1) {
        return;
    }
    if (!$("#addDisputesType").valid()) {
        return;
    }
    var grade = $("#grade").val() === "1" ? 1 : 2;
    var parentId = $("#grade").val() === "1" ? 0 : $("#parentId").val();
    var disputesType = {
        name: $("#addTypeName").val(),
        sort: $("#addTypeSort").val(),
        grade: grade,
        parentId: parentId,
        code: $("#addTypeCode").val()
    };
    typeacount = 1;
    LSFetch({
        url: basePath + '/adddisputestype.htm',
        data: JSON.stringify(disputesType),
        contentType: "application/json;charset=utf-8",
        success: function (res) {
            typeacount = 0;
            if (res === 1) {
                $('#add_dialog').modal('hide');
                initDataTable();
                showerror("添加成功");
            } else if (res === 0) {
                $('#add_dialog').modal('hide');
                showerror('该事务咨询类型已存在');
            }
        }
    });
}

// 跳转到删除公司纠纷类型页面
function todelete(id, parentId) {
    $("#deleteId").val(id);
    $("#deleteParentId").val(parentId);
    $('#del_dialog').modal('show');
}

// 删除公司纠纷类型
function deleteAffairsType() {
    var disputes = {
        id: $("#deleteId").val(),
        parentId: $("#deleteParentId").val()
    };
    LSFetch({
        url: basePath + '/deletedisputetype.htm?flag=' + false,
        data: JSON.stringify(disputes),
        contentType: "application/json;charset=utf-8",
        success: function (res) {
            if (res === -1) {
                $("#confirm").remove();
                var diahtml = '<div id="confirm" class="modal fade " tabindex="-1" role="dialog" aria-labelledby="myModalLabel"aria-hidden="true">';
                diahtml += '  <div class="modal-dialog">';
                diahtml += '  <div class="modal-content">';
                diahtml += '<div class="modal-header">';
                diahtml += '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button><h4 class="modal-title">确认提示</h4></div>';
                diahtml += ' <div class="modal-body errormsg">该类型下存在子类型，是否删除？</div>';
                diahtml += '<div class="modal-footer"> <button data-dismiss="modal" class="btn btn-default" type="button">取消</button> <button class="btn btn-success copybtn" onclick="deleteType()" type="button">确定</button></div></div></div></div>';

                $(document.body).append(diahtml);
                $('#confirm').modal('show');
            }
            $('#del_dialog').modal('hide');
            initDataTable();
        }
    })
}

// 删除父类型及其子类型
function deleteType() {
    var disputes = {
        id: $("#deleteId").val(),
        parentId: $("#deleteParentId").val()
    };
    LSFetch({
        url: basePath + '/deletedisputetype.htm?flag=' + true,
        data: JSON.stringify(disputes),
        contentType: "application/json;charset=utf-8",
        success: function (res) {
            if (res >= 1) {
                $("#confirm").modal("hide");
                initDataTable();
            }
        }
    });
}

// 弹出修改公司纠纷类型页面
function toupdate(id, name, parentId, sort, code) {
    if (parentId == 0) {
        $("#existParent").hide();
    } else {
        $("#existParent").show();
        LSFetch({
            url: basePath + '/queryfirstgradedisputestype.htm',
            success: function (data) {
                var param = '<option value="0" style="ce">设为一级类型</option>';
                for (var i = 0; i < data.length; i++) {
                    if (data[i].id == parentId) {
                        param += '<option value="' + data[i].id + '" selected>' + data[i].name + '</option>';
                    } else {
                        param += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
                    }
                }
                $("#columnsSelectEdit").html(param);
            }
        });
    }
    $("#disputesTypeId").val(id);
    $("#disputesTypeName").val(name);
    $("#disputesTypeSort").val(sort);
    $("#disputesTypeCode").val(code);
    $('#edit_dialog').modal('show');
}

// 修改公司纠纷类型
function update() {
    if (typeucount === 1) {
        return;
    }
    if (!$("#updateDisputesTypeForm").valid()) {
        return;
    }
    var disputesType = {
        id: $("#disputesTypeId").val(),
        name: $("#disputesTypeName").val(),
        parentId: $("#columnsSelectEdit").val(),
        sort: $("#disputesTypeSort").val(),
        code: $("#disputesTypeCode").val()
    };
    typeucount = 1;
    LSFetch({
        url: basePath + '/updatedisputestype.htm',
        data: JSON.stringify(disputesType),
        contentType: "application/json;charset=utf-8",
        success: function (res) {
            if (res === 1) {
                clearCount();
                $('#edit_dialog').modal('hide');
                initDataTable();
            }
        }
    });
}

function changeGrade(obj) {
    if ($(obj).val() === "1") {
        $(obj).parents('.form-group').next('.form-group').hide();
    } else if ($(obj).val() === "2") {
        $(obj).parents('.form-group').next('.form-group').show();
        LSFetch({
            url: basePath + '/queryfirstgradedisputestype.htm',
            success: function (data) {
                var param = '';
                for (var i = 0; i < data.length; i++) {
                    param += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
                }
                $("#parentId").html(param);
            }
        });
    }
}
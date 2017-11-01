//新增时候防止重复提交
var typeacount = 0;

// 修改时候防止重复提交
var typeucount = 0;

function clearCount() {
    typeacount = 0;
    typeucount = 0;
}

var addHtml = $('#add_dialog').html();

// 基本路径
var basePath = $("#basePath").val();

var initDataTable = function () {
    LSFetch({
        url: basePath + '/queryconsultationtype.htm',
        success: function (res) {
            var html = '';
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
                        '<button class="btn btn-primary btn-xs" onclick="toupdate(' + res[i].id + ',' + "'" + res[i].name + "'" + ',' + res[i].parentId + ',' + res[i].sort + ',' + res[i].code + ',' + res[i].grade + ')">' +
                        '<i class="icon-pencil"></i> 编辑</button> ' +
                        '<button class="btn btn-danger btn-xs del_btn" onclick="todelete(' + res[i].id + ',' + res[i].parentId + ')"><i class="icon-trash"></i> 删除</button> </td> </tr>';
                }
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
            } else {
                $("#treetable").html('<tr><td colspan="4" style="text-align: center">暂无数据</td></tr>');
            }
        }
    });
};

// 初始化列表
initDataTable();

// 弹出添加窗口
function addtype() {
    $('#add_dialog').html(addhtml).modal('show');
}

function addtype() {
    $('#add_dialog').html(addHtml);
    $('#add_dialog').modal('show');
}

// 新增公司咨询类型
function saveConsultationType() {
    if (typeacount === 1) {
        return;
    }
    if (!$("#addForm").valid()) {
        return;
    }
    var grade = $("#grade").val() === "1" ? 1 : 2;
    var parentId = $("#grade").val() === "1" ? 0 : $("#parentId").val();
    var consultationType = {
        name: $("#addConsultationTypeName").val(),
        sort: $("#addConsultationTypeSort").val(),
        grade: grade,
        parentId: parentId,
        code: $("#addConsultationTypeCode").val()
    };
    typeacount = 1;
    LSFetch({
        url: basePath + '/addconsultationtype.htm',
        data: JSON.stringify(consultationType),
        contentType: "application/json;charset=utf-8",
        success: function (res) {
            typeacount = 0;
            if (res === 1) {
                $('#add_dialog').modal('hide');
                initDataTable();
            } else if (res === 0) {
                $('#add_dialog').modal('hide');
                showerror('该公司咨询类型已存在');
            }
        }
    });
}

// 跳转到删除公司咨询类型页面
function todelete(id, parentId) {
    $("#deleteId").val(id);
    $("#deleteParentId").val(parentId);
    $('#del_dialog').modal('show');
}

// 删除公司咨询类型
function deleteAffairsType() {
    var consultationType = {
        id: $("#deleteId").val(),
        parentId: $("#deleteParentId").val()
    };
    LSFetch({
        url: basePath + '/deleteconsultationtypebyid.htm?flag=' + false,
        data: JSON.stringify(consultationType),
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
    });
}

// 删除父类型及其子类型
function deleteType() {
    var consultationType = {
        id: $("#deleteId").val(),
        parentId: $("#deleteParentId").val()
    };
    LSFetch({
        url: basePath + '/deleteconsultationtypebyid.htm?flag=' + true,
        data: JSON.stringify(consultationType),
        contentType: "application/json;charset=utf-8",
        success: function (res) {
            if (res >= 1) {
                $("#confirm").modal("hide");
                initDataTable();
            }
        }
    });
}

// 弹出修改公司咨询类型页面
function toupdate(id, name, parentId, sort, code, grade) {
    if (parentId == 0) {
        $("#existParent").hide();
    } else {
        $("#existParent").show();
        LSFetch({
            url: basePath + '/queryfirstgradeconsultationtype.htm',
            success: function (data) {
                var param = '<option value="0">设为一级类型</option>';
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
    $("#consultationTypeId").val(id);
    $("#consultationTypeName").val(name);
    $("#consultationTypeSort").val(sort);
    $("#consultationTypeCode").val(code);
    $("#consultationTypeGrade").val(grade);
    $('#edit_dialog').modal('show');
}

// 修改公司咨询类型
function update() {
    if (typeucount === 1) {
        return;
    }
    if (!$("#editForm").valid()) {
        return;
    }
    var grade = $("#consultationTypeGrade").val();
    if ($("#columnsSelectEdit").val() == 0) {
        grade = 1;
    }
    var consultationType = {
        id: $("#consultationTypeId").val(),
        parentId: $("#columnsSelectEdit").val(),
        name: $("#consultationTypeName").val(),
        sort: $("#consultationTypeSort").val(),
        code: $("#consultationTypeCode").val(),
        grade: grade
    };
    typeucount = 1;
    LSFetch({
        url: basePath + '/updateconsultationtype.htm',
        data: JSON.stringify(consultationType),
        contentType: "application/json;charset=utf-8",
        success: function (res) {
            clearCount();
            if (res === 1) {
                $('#edit_dialog').modal('hide');
                initDataTable();
            }
        }
    });
}

function selectGrade(obj) {
    if ($(obj).val() === "1") {
        $(obj).parents(".form-group").next(".form-group").hide();
    }
    if ($(obj).val() === "2") {
        $(obj).parents(".form-group").next(".form-group").show();
        LSFetch({
            url: basePath + '/queryfirstgradeconsultationtype.htm',
            success: function (data) {
                var param = '';
                if (data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        param += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
                    }
                } else {
                    param += '<option value="-1">暂无一级</option>';
                }
                $("#parentId").html(param);
            }
        });
    }
}
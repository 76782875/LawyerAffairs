// 将新增的弹出层放入内存中
var addhtml = $("#add_dialog").html();
var addSonTypeHtml = $("#addSonType_dialog").html();

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
        url: basePath + '/querytemplatetype.htm',
        success: function (res) {
            for (var i = 0; i < res.length; i++) {
                html += '<tr class="even';
                if (res[i].grade !== 1) {
                    html += ' collapsed"';
                } else {
                    html += '"';
                }
                html += '><input type="hidden" value="' + res[i].grade + '" name="parentId">';
                html += '<td>&nbsp;&nbsp;' + res[i].name + '</td><td>' + res[i].code + '</td><td>' + res[i].sort + '</td>';
                if (res[i].grade == 3) {
                    html += '<td width="230" class="operation_box"> ' +
                        '<button class="btn btn-primary btn-xs" onclick="toupdate(' + res[i].id + ')"><i class="icon-pencil"></i> 编辑</button> ' +
                        '<button class="btn btn-danger btn-xs del_btn" onclick="todelete(' + res[i].id + ')"><i class="icon-trash"></i> 删除</button> </td> </tr>';
                } else {
                    html += '<td width="230" class="operation_box"> ' +
                        '<button class="btn btn-primary btn-xs" onclick="toupdate(' + res[i].id + ')"><i class="icon-pencil"></i> 编辑</button> ' +
                        '<button class="btn btn-primary btn-xs add_class" onclick="addClassify(' + res[i].id + ',' + res[i].grade + ')"><i class="icon-plus-sign"></i> 添加子分类</button> ' +
                        '<button class="btn btn-danger btn-xs del_btn" onclick="todelete(' + res[i].id + ',' + res[i].parentId + ')"><i class="icon-trash"></i> 删除</button> </td> </tr>';
                }
            }
            $("#treetable").html(html);
            var maps = new Array();
            var one = 0;
            var two = 0;
            $("#treetable input[name='parentId']").each(function (i) {
                if ($(this).val() === "1") {
                    maps.push(0);
                    one = i + 1;
                } else if ($(this).val() === "2") {
                    maps.push(one);
                    two = i + 1;
                } else {
                    maps.push(two);
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
    });
};

// 初始化列表
initDataTable();

function addtype() {
    $('#add_dialog').html(addhtml).modal('show');
}

// 新增一级合同模板类型
function addTemplateType() {
    if (!$("#addTypeForm").valid()) {
        return;
    }
    var templateType = {
        name: $("#addTypeName").val(),
        code: $("#addTypeCode").val(),
        sort: $("#addTypeSort").val(),
        parentId: 0,
        grade: 1
    };
    typeacount = 1;
    LSFetch({
        url: basePath + '/addditemplatetype.htm',
        data: JSON.stringify(templateType),
        contentType: "application/json;charset=utf-8",
        success: function (res) {
            typeacount = 0;
            if (res === 1) {
                $('#add_dialog').modal('hide');
                initDataTable();
            } else if (res === -1) {
                $('#add_dialog').modal('hide');
                showerror('该合同模板类型已存在');
            }
        }
    })
}

// 跳转到删除合同模板类型页面
function todelete(id, parentId) {
    $("#deleteId").val(id);
    $("#deleteParentId").val(parentId);
    $('#del_dialog').modal('show');
}

// 删除合同模板类型
function deleteTemplateType() {
    var templateType = {
        id: $("#deleteId").val(),
        parentId: $("#deleteParentId").val()
    };
    LSFetch({
        url: basePath + '/deletetemplatetype.htm?flag=' + false,
        data: JSON.stringify(templateType),
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
    var templateType = {
        id: $("#deleteId").val(),
        parentId: $("#deleteParentId").val()
    };
    LSFetch({
        url: basePath + '/deletetemplatetype.htm?flag=' + true,
        data: JSON.stringify(templateType),
        contentType: "application/json;charset=utf-8",
        success: function (res) {
            if (res >= 1) {
                $("#confirm").modal("hide");
                initDataTable();
            }
        }
    });
}

// 弹出修改合同模板类型页面
function toupdate(id) {
    clearCount();
    LSFetch({
        url: basePath + '/querytemplatetypebyid.htm?id=' + id,
        success: function (res) {
            $("#editTypeId").val(res.id);
            $("#editParentId").val(res.parentId);
            $("#editGradeId").val(res.grade);
            $("#typeName").val(res.name);
            $("#typeCode").val(res.code);
            $("#typeSort").val(res.sort);
            $('#edit_dialog').modal('show');
        }
    })
}

// 修改合同模板类型
function update() {
    if (typeucount === 1) {
        return;
    }
    if (!$("#updateTemplateTypeForm").valid()) {
        return;
    }
    var templateType = {
        id: $("#editTypeId").val(),
        parentId: $("#editParentId").val(),
        grade: $("#editGradeId").val(),
        name: $("#typeName").val(),
        code: $("#typeCode").val(),
        sort: $("#typeSort").val()
    };
    typeucount = 1;
    LSFetch({
        url: basePath + '/updatetemplatetype.htm',
        data: JSON.stringify(templateType),
        contentType: "application/json;charset=utf-8",
        success: function (res) {
            if (res === 1) {
                $('#edit_dialog').modal('hide');
                initDataTable();
            } else if (res === -1) {
                $('#edit_dialog').modal('hide');
                showerror('该合同模板类型已存在');
            }

        }
    });
}

function changeGrade() {
    if ($("#grade").val() === "1") {
        $("#firstGradeId").attr("disabled", "disabled");
        $("#secondGradeId").attr("disabled", "disabled");
        $("#firstGradeId").html("");
        $("#secondGradeId").html("");
    } else if ($("#grade").val() === "2") {
        $("#secondGradeId").attr("disabled", "disabled");
        $("#firstGradeId").removeAttr("disabled");
        $("#secondGradeId").html("");
        LSFetch({
            url: basePath + '/querytemplatetypebygrade.htm?grade=1',
            success: function (data) {
                var param = '';
                for (var i = 0; i < data.length; i++) {
                    param += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
                }
                $("#firstGradeId").html(param);
            }
        });
    } else if ($("#grade").val() === "3") {
        $("#firstGradeId").attr("disabled", "disabled");
        $("#secondGradeId").removeAttr("disabled");
        $("#firstGradeId").html("");
        LSFetch({
            url: basePath + '/querytemplatetypebygrade.htm?grade=2',
            success: function (data) {
                var param = '';
                for (var i = 0; i < data.length; i++) {
                    param += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
                }
                $("#secondGradeId").html(param);
            }
        });
    }
}

// 添加二级或三级模板类型
function addClassify(id, grade) {
    $("#addSonType_dialog").html(addSonTypeHtml);
    $("#sonTypeId").val(id);
    $("#sonTypeGrade").val(grade + 1);
    $('#addSonType_dialog').modal('show');
}

function saveSonType() {
    var templateType = {
        name: $("#addSonTypeName").val(),
        code: $("#addSonTypeCode").val(),
        sort: $("#addSonTypeSort").val(),
        parentId: $("#sonTypeId").val(),
        grade: $("#sonTypeGrade").val()
    };
    typeacount = 1;
    LSFetch({
        url: basePath + '/addditemplatetype.htm',
        data: JSON.stringify(templateType),
        contentType: "application/json;charset=utf-8",
        success: function (res) {
            typeacount = 0;
            if (res == 1) {
                $('#addSonType_dialog').modal('hide');
                initDataTable();
            } else if (res == -1) {
                $('#addSonType_dialog').modal('hide');
                showerror('该合同模板类型已存在');
            }
        }
    })
}
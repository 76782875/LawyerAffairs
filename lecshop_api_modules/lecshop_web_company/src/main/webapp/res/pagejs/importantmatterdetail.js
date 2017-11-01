// 基本路径
var basePath = $("#basePath").val();

var initData = function () {
    LSFetch({
        url: basePath + '/c_queryimportantmatterdetails.htm',
        data: {
            matterId: $("input[name='id']").val()
        },
        success: function (data) {
            var html = '';
            if (data.length > 0) {
                for (var i = 0; i < data.length; i++) {
                    var datas = data[i];
                    var id = "accordion" + i
                    if (datas.parentId === 0) {
                        html += '<div class="col-md-12">' +
                            '        <section class="panel">' +
                            '           <header class="panel-heading" style="text-align:left;">' + datas.name + '</header>' +
                            '           <div class="panel-body form-horizontal">' +
                            '              <div class="tab-content">' +
                            '                  <div class="tab-pane active">' +
                            '                       <div class="panel-group " id="' + datas.id + '">';
                        if (datas.childs.length > 0) {
                            for (var j = 0; j < datas.childs.length; j++) {
                                var time = "";
                                if (datas.childs[j].time !== "" && datas.childs[j].time !== null) {
                                    var date = datas.childs[j].time.split(",");
                                    var money = date[1] <= 9 ? ('0' + date[1]) : date[1];
                                    var day = date[2] <= 9 ? ('0' + date[2]) : date[2];
                                    var hour = date[3] <= 9 ? ('0' + date[3]) : date[3];
                                    var min = date[4] <= 9 ? ('0' + date[4]) : date[4];
                                    time = date[0] + '-' + money + '-' + day + " " + hour + ":" + min + ":00";
                                }
                                var file = (datas.childs[j].url === null || datas.childs[j].url === "") ? "" : datas.childs[j].url;
                                var className = (datas.childs[j].url === null || datas.childs[j].url === "") ? "fileupload-new" : "fileupload-exists";
                                var desc = (datas.childs[j].desc === null || datas.childs[j].desc === "") ? "" : datas.childs[j].desc;
                                html += ' <div class="panel">' +
                                    '           <div class="panel-heading">' +
                                    '                <h4 class="panel-title" style="text-align:left;">' +
                                    '                   <a class="push accordion-toggle" data-toggle="collapse" data-parent="' + datas.id + '" href="#' + datas.childs[j].id + '">' + datas.childs[j].name + '</a>' +
                                    '                </h4>' +
                                    '           </div>';
                                html += '       <div id="' + datas.childs[j].id + '" class="panel-collapse collapse " style="text-align:left;">' +
                                    '               <div class="panel-body">' +
                                    '                  <div class="form-horizontal tasi-form" style="width: 20%">' +
                                    '                  <div class="form-group"><input class="form-control form-control-inline input-medium" name="descs" size="16" type="text" name="text" value="' + desc + '"></div>' +
                                    '                  <div class="form-group">' +
                                    '                      <div class="input-group date search_datetime">' +
                                    '                          <input size="16" type="text" value="' + time + '" name="times" readonly class="form-control">' +
                                    '                          <span class="input-group-btn">' +
                                    '                               <button type="button" class="btn btn-info date-set"><i class="icon-calendar"></i></button>' +
                                    '                          </span>' +
                                    '                      </div>' +
                                    '                  </div>' +
                                    '                  <div class="form-group">' +
                                    '                       <div class="fileupload ' + className + '" data-provides="fileupload">' +
                                    '                           <form id="' + datas.childs[j].id + 'Form" action="c_importantmatterdetaildesc.htm" enctype="multipart/form-data" method="post" target="fileIframe">' +
                                    '                                 <div class="fileupload-preview fileupload-exists thumbnail" style="max-width: 200px; max-height: 80px; line-height: 20px;">' + file + '</div>' +
                                    '                                 <div>' +
                                    '                                     <span class="btn btn-white btn-file">' +
                                    '                                         <span class="fileupload-new"><i class="icon-paper-clip"></i> 选择本地文件</span>' +
                                    '                                         <span class="fileupload-exists"><i class="icon-undo"></i> 重新上传</span>' +
                                    '                                         <input type="file" class="default" name="fileUpload" value="" onchange="fileChange(this)"/>' +
                                    '                                         <input type="hidden" name="desc">' +
                                    '                                         <input type="hidden" name="time">' +
                                    '                                         <input type="hidden" name="id">' +
                                    '                                         <input type="hidden" name="matterId">' +
                                    '                                      </span>' +
                                    '                                      <a href="#" class="btn btn-success btn-primary" onclick="importantDownLoad(' + "'" + file + "'" + ')">下载文件</a>' +
                                    '                                 </div>' +
                                    '                            </form>' +
                                    '                       </div>' +
                                    '                  </div>' +
                                    '                   <div class="form-group">' +
                                    '                       <button type="button" class="btn btn-primary" data-action="false" onclick="SaveBtn(' + datas.childs[j].matterId + "," + datas.childs[j].id + "," + 'this)">保存</button>' +
                                    '                  </div>' +
                                    '             </div>' +
                                    '        </div>' +
                                    '    </div>';
                                html += '   </div>';
                            }
                        }
                        html += '                    </div>' +
                            '                   </div>' +
                            '               </div>' +
                            '           </div>' +
                            '       </section>' +
                            '</div>'
                    }
                }
                $("#divContainer").html(html);
            }
            $(".search_datetime").datetimepicker({
                format: 'yyyy-mm-dd hh:ii:00',
                weekStart: 1,
                autoclose: true,
                language: 'zh',
                todayBtn: true,
                viewSelect: 'hour'
            });
        }
    });
};

initData();

function importantDownLoad(file) {
    if (file === "" || file === null) {
        showerror("尚未提交相关文件,无法操作下载");
    } else {
        $("#downForm input[name='name']").val(file);
        $("#downForm").submit();
    }
}

function fileChange(obj) {
    $(obj).parents(".form-group").next(".form-group").children("button").attr("data-action", true)
}

function SaveBtn(matterId, id, obj) {
    if ($("#" + id + " input[name='descs']").val() !== "") {
        $("#" + id + " input[name='desc']").val($("#" + id + " input[name='descs']").val());
    }
    if ($("#" + id + " input[name='times']").val() !== "") {
        var year = $("#" + id + " input[name='times']").val().split("-")[0];
        var month = parseInt($("#" + id + " input[name='times']").val().split("-")[1]);
        var day = parseInt($("#" + id + " input[name='times']").val().split("-")[2].split(" ")[0]);
        var hour = parseInt($("#" + id + " input[name='times']").val().split("-")[2].split(" ")[1].split(":")[0]);
        var min = parseInt($("#" + id + " input[name='times']").val().split("-")[2].split(" ")[1].split(":")[1])
        $("#" + id + " input[name='time']").val(year + "," + month + "," + "," + day + "," + hour + "," + min);
    }
    if ($(obj).attr("data-action") === "false") {
        $("#" + id + " input[name='fileUpload']").val("");
    }
    if ($("#" + id + " input[name='desc']").val() === "" && $("#" + id + " input[name='time']").val() === "" && $(obj).attr("data-action") === "false") {
        showerror("不能全空");
        return;
    }
    $("#" + id + " input[name='id']").val(id);
    $("#" + id + " input[name='matterId']").val(matterId);
    $("#" + id + "Form").submit();
    $('iframe').on('load', function () {
        var responseText = $('iframe')[0].contentDocument.body.textContent;
        var responseData = JSON.parse(responseText) || {};
        if (responseData === 1) {
            showerror("保存成功");
            queryDisputesDetail();
        } else {
            showerror("保存失败");
        }
    });
}

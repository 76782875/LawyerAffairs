var basePath = $("#basePath").val();
var loadPastCases = function () {
    LSFetch({
        url: basePath + '/c_querypastcases.htm',
        data: {
            id: $("input[name='code']").val(),
            name: $("input[name='name']").val(),
            institution: $("input[name='institution']").val()
        },
        success: function (data) {
            var html = '';
            if (data.a_detail.allJudgments.length > 0) {
                for (var i = 0; i < data.a_detail.allJudgments.length; i++) {
                    var time = data.a_detail.allJudgments[i].time;
                    var title = data.a_detail.allJudgments[i].title;
                    html += '<tr><td>' + title + '</td><td>' + time + '</td>';
                    html += '<td class="operation_box" style="text-align: center;">';
                    html += '<button class="btn btn-primary btn-xs" onclick="showDetail(' + "'" + data.a_detail.allJudgments[i].id + "'" + ')"><i class="icon-eye-open"></i> 查看详情</button>';
                    html += '</td></tr>';
                }
            }
            $("#tbodycontainer").html(html);
            $('.dataTable').dataTable({
                "bSort": false,
                "iDisplayLength": 10
            });
            $('.dataTables_length,.dataTables_filter').remove();
        }
    });
};
loadPastCases();

function goBack() {
    var url = window.location.search.substr(0, window.location.search.lastIndexOf("&"));
    url = url.substr(0, url.lastIndexOf("&"));
    if ($("input[name='type']").val() === "1") {
        window.location.href = basePath + "c_tocompanyaffair.htm" + url;
    }
    if ($("input[name='type']").val() === "2") {
        window.location.href = basePath + "c_todisputesaffair.htm" + url;
    }
    if ($("input[name='type']").val() === "3") {
        window.location.href = basePath + "c_toimportantaffair.htm" + url;
    }
}

function showDetail(id) {
    LSFetch({
        url: basePath + '/c_querycasedetail.htm',
        data: {
            id: id
        },
        success: function (data) {
            $("#show_dialog p[data-date='title']").text(data.a_detail.title);
            $("#show_dialog p[data-date='time']").text(data.a_detail.time);
            $("#show_dialog p[data-date='court']").text(data.a_detail.court);
            $("#show_dialog p[data-date='number']").text(data.a_detail.number);
            $("#show_dialog p[data-date='mainBody']").text(data.a_detail.mainBody);
            $("#show_dialog").modal('show');
        }
    });
}
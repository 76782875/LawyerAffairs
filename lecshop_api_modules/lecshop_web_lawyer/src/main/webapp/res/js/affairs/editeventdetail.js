var basePath = $("#basePath").val();

var myApp = new Framework7();

var mytimer;

fetchDetail();

function fetchDetail() {

    $.ajax({
        url: basePath + '/queryimportantmatterdetailbyid.htm',
        type: 'post',
        data: {'detailId': $("#detailId").val()},
        success: function (res) {

            $("#xFileText").html(res.url);
            $("#mytext").val(res.desc);
            if (res.time == '' || res.time == null) {
                initdate(new Date());
            } else {
                var valuedate = res.time.split(',');
                initdate(new Date(valuedate[0], parseInt(valuedate[1]) - 1, valuedate[2], valuedate[3], valuedate[4]));
            }
        }
    });
}


/**
 * 跳转到下载页面
 */
function todownload() {

    var url = $("#xFileText").html();

    if (url == "") {
        tip('没有文件可以下载!');
        return;
    }
    window.location.href = basePath + "/todownload.htm?name=" + url;
}


// 上传文件
function uploadFile() {
    $.ajax({
        url: basePath + '/uploadtolocal.htm',
        type: 'POST',
        cache: false,
        data: new FormData($('#eventform')[0]),
        processData: false,
        contentType: false
    }).done(function (res) {
        $("#xFileText").html(res.res);
    });
}


function initdate(today) {

    mytimer = myApp.picker({
        input: '#ks-picker-date',
        container: '#ks-picker-date-container',
        toolbar: false,
        rotateEffect: true,
        value: [today.getFullYear(), today.getMonth(), today.getDate(), today.getHours(), (today.getMinutes() < 10 ? '0' + today.getMinutes() : today.getMinutes())],
        onChange: function (picker, values, displayValues) {
            var daysInMonth = new Date(picker.value[2], picker.value[0] * 1 + 1, 0).getDate();
            if (values[1] > daysInMonth) {
                picker.cols[1].setValue(daysInMonth);
            }
        },
        formatValue: function (p, values, displayValues) {
            return values[0] + ', ' + ' ' + displayValues[1] + ' ' + values[2] + ' ' + values[3] + ':' + values[4];
        },
        cols: [
            // Years
            {
                values: (function () {
                    var arr = [];
                    for (var i = 1950; i <= 2030; i++) {
                        arr.push(i);
                    }
                    return arr;
                })(),
            },
            // Months
            {
                values: ('0 1 2 3 4 5 6 7 8 9 10 11').split(' '),
                displayValues: ('一月 二月 三月 四月 五月 六月 七月 八月 九月 十月 十一月 十二月').split(' '),
                textAlign: 'left'
            },
            // Days
            {
                values: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31],
            },

            // Space divider
            {
                divider: true,
                content: '&nbsp;&nbsp;'
            },
            // Hours
            {
                values: (function () {
                    var arr = [];
                    for (var i = 0; i <= 23; i++) {
                        arr.push(i);
                    }
                    return arr;
                })(),
            },
            // Divider
            {
                divider: true,
                content: ':'
            },
            // Minutes
            {
                values: (function () {
                    var arr = [];
                    for (var i = 0; i <= 59; i++) {
                        arr.push(i < 10 ? '0' + i : i);
                    }
                    return arr;
                })(),
            }
        ]
    });
}

// 保存
function save() {
    var mydate = mytimer.value;
    mydate[1] = parseInt(mydate[1]) + 1;
    $.ajax({
        url: basePath + '/updateimportantmatterdetail.htm',
        type: 'post',
        data: {
            'id': $("#detailId").val(),
            'matterId': $("#matterId").val(),
            'desc': $("#mytext").val(),
            'url': $("#xFileText").html(),
            'time': mydate.join(",")
        },
        success: function (res) {
            window.location.href = basePath + 'toimportantmatterdetail.htm?matterId=' + $("#matterId").val();
        }
    });
}


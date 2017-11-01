var basePath = $("#basePath").val();
var myApp = new Framework7();

fetchdetail();

function fetchdetail() {
    $.ajax({
        url: basePath + '/queryimportantmatterdetails.htm?matterId=' + $("#matterId").val(),
        type: 'post',
        success: function (res) {
            var allhtmls = '';
            for (var i = 0; i < res.length; i++) {
                var htmls = '<li class="accordion-item"><a href="#" class="item-link item-content"><div class="item-inner"><div class="item-title">' + res[i].name + '</div></div></a>';
                htmls += '<div class="accordion-item-content"> <div class="list-block"><ul>';
                for (var k = 0; k < res[i].childs.length; k++) {
                    htmls += '<li><a onclick="toeditdetail(' + res[i].childs[k].id + ')" href="#" class="item-link"> <div class="item-content"> <div class="item-media"></div> <div class="item-inner"> <div class="item-title">' + res[i].childs[k].name + '</div> </div> </div></a></li>';
                }

                htmls += '</ul></div></div></li>';

                allhtmls += htmls;
            }

            $("#detailhtmls").html(allhtmls);
        }
    });
}

function toeditdetail(detailId) {
    window.location.href = basePath + '/toeditmatterdetail.htm?matterId=' + $("#matterId").val() + "&detailId=" + detailId;
}
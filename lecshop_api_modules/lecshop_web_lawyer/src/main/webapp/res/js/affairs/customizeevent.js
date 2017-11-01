var basePath = $("#basePath").val();

function add(obj) {
    if ($(obj).parent().hasClass('one') == true) {
        $(obj).parents('li.accordion-item').after('<li class="accordion-item"> <div class="item-content"> <div class="item-inner"> <div class="item-title"><input type="text" name="" placeholder="请输入阶段"></div> </div> <div class="list_toolbar one"> <a href="javasrcipt:;" onclick="add(this)"><i class="f7-icons">add_round</i></a> <a href="javasrcipt:;" onclick="cut(this)"><i class="f7-icons">trash</i></a> </div> </div> <div class="list-block"> <ul> <li class="two_box"> <div class="item-content"> <div class="item-media"></div> <div class="item-inner"> <div class="item-title"><input type="text" name="" placeholder="请输入事项"></div> </div> </div> <div class="list_toolbar two"> <a href="javasrcipt:;" onclick="add(this)"><i class="f7-icons">add_round</i></a> <a href="javasrcipt:;" onclick="cut(this)"><i class="f7-icons">trash</i></a> </div> </li> </ul> </div> </li>')
    }
    if ($(obj).parent().hasClass('two') == true) {
        $(obj).parents('li.two_box').after('<li class="two_box"> <div class="item-content"> <div class="item-media"></div> <div class="item-inner"> <div class="item-title"><input type="text" name="" placeholder="请输入事项"></div> </div> </div> <div class="list_toolbar two"> <a href="javasrcipt:;" onclick="add(this)"><i class="f7-icons">add_round</i></a> <a href="javasrcipt:;" onclick="cut(this)"><i class="f7-icons">trash</i></a> </div> </li>')
    }
}
function cut(obj) {
    if ($(obj).parent().hasClass('one') == true && $(obj).parents('li.accordion-item').siblings().length > 0) {
        $(obj).parents('li.accordion-item').remove();
    }
    if ($(obj).parent().hasClass('two') == true && $(obj).parents('li.two_box').siblings().length > 0) {
        $(obj).parents('li.two_box').remove();
    }
}

// 保存模版
function save() {


    var validatFlag = false;

    var importMatters = new Array();

    for (var i = 0; i < $(".accordion-item").length; i++) {
        var obj = $(".accordion-item")[i];
        var importMatter = {};
        importMatter.childs = new Array();
        importMatter.matterId = $("#eventId").val();
        importMatter.name = $(obj).find('.item-content input').val();
        importMatter.sort = i;
        importMatter.grade = 1;
        var k = 0;
        if (importMatter.name == '') {
            continue;
        }
        $(obj).find('.list-block li').each(function () {
            if ($(this).find('input').val() != '') {
                validatFlag = true;
                var child = {};
                child.name = $(this).find('input').val();
                child.sort = k;
                child.grade = 2;
                child.matterId = $("#eventId").val();
                importMatter.childs.push(child);
                k++
            }
        });
        importMatters.push(importMatter);
    }

    if (!validatFlag) {
        tip('请填写流程步骤!');
        return;
    }

    $.ajax({
        url: basePath + '/addimportmattertemplates.htm',
        type: 'post',
        data: JSON.stringify(importMatters),
        contentType: "application/json;charset=utf-8",
        success: function () {
            window.location.href = basePath + '/toimportantmatter.htm';
        }
    });
}

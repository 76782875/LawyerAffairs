$(document).ready(function () {

  var domObj = '';

  $('.sortable_box').sortable();

  function hasEdit() {
    var flag = false;
    $('.layer_box').each(function () {
      if ($(this).css('display') == 'block') {
        flag = true;
      }
    })
    return flag;
  }

  $('.edit_item').mouseenter(function () {

    if  (hasEdit())
    {
        return ;
    }

    $('body').off('mouseleave', '.categorys', cleave);
    var p_l = $(this).css('padding-left');
    var p_r = $(this).css('padding-right');
    var p_t = $(this).css('padding-top');
    var p_b = $(this).css('padding-bottom');
    var w = $(this).width() - 4;
    var h = $(this).height() - 4;
    var t = $(this).offset().top;
    var l = $(this).offset().left;

    domObj = $(this);

    $('.edit_layer').show();
    $('.edit_layer').css({'width': w,'height': h,'top': t,'left': l,'padding-left': p_l,'padding-right': p_r,'padding-top': p_t,'padding-bottom': p_b});
    if( h < 40 ){
      $('.edit_layer').css('min-height', h);
      $('.edit_layer').find('.edit_btn').css({'padding': '2px 10px','margin': '-13px 0 0 -28px'})
    }
    else if($(this).hasClass('module_edit') == true){
      $('.edit_layer').css({'height': h-20,'top': t+10});
    }
  });
  $('.edit_layer').mouseleave(function () {
    $('.edit_layer').hide();
    $('body').on('mouseleave','.categorys',cleave);
  });

  function cleave () {
    $(this).find('.categorys_item').removeClass('hover');
    $(this).find('.categorys_layer').hide();
  }
  
  //编辑按钮
  $('.edit_btn').click(function () {
    $('.layer_box').animate({
      left: '0'
    },300);
    $('.page_box').animate({
      margin: '0 0 0 315px'
    },300);
    
    // 弹出层对象
    var editObj = $("#"+domObj.attr("edit_type"));
    editObj.show();

    switch (domObj.attr("edit_type"))
    {
      case "hot_searchs_edit"://热门搜索
        var html = '';
        domObj.find('a').each(function () {
          html+='<div class="link_input"><input class="layer_input fll" value="'+$(this).text()+'" type="text"><a class="input_btn del_btn" href="javascript:;"><i class="icon-trash"></i></a></div>';
        });
        editObj.find('.edit_box').html('').append(html);
        break;
      case "top_banner_edit"://顶部广告
        var backcolor = domObj.css('background-color');
        var imgsrc = domObj.find('img').attr('src');
        var url = domObj.find('a').attr('href');
        editObj.find('.tip_size').html('图片建议尺寸:' + domObj.find('img').css('width') + ' * ' + domObj.find('img').css('height'));
        editObj.find('.edit_box').find('.thumbnail').css('background-image','url('+imgsrc+')');
        editObj.find('.edit_box').find('input.form-control').val(backcolor);
        editObj.find('.edit_box').find('.colorpicker-default').attr('data-color',backcolor);
        editObj.find('.edit_box').find('.btn-white i').css('background-color',backcolor);
        editObj.find('.edit_box').find('.thumbnail input[type="hidden"]').val(imgsrc);
        editObj.find('.edit_box').find('.img_bar input[type="hidden"]').val(url);
        break;
      case "logo_edit"://logo+二维码
        var imgsrc = domObj.find('img').attr('src');
        editObj.find('.tip_size').html('图片建议尺寸:' + domObj.attr('imgwidth') + 'px' + ' * ' + domObj.attr('imgheight') + 'px');
        editObj.find('.edit_box').find('.thumbnail').css('background-image','url('+imgsrc+')');
        break;
      case "multi_word_edit"://多文字链接
        var html = '';
        domObj.find('a').each(function () {
          html+='<div class="link_input"><input class="layer_input fll" value="'+$(this).text()+'" type="text"><a class="input_btn link_btn" href="javascript:;"><i class="icon-link"></i></a><a class="input_btn del_btn" href="javascript:;"><i class="icon-trash"></i></a><input type="hidden" value="'+$(this).attr('href')+'"></div>'
        });
        editObj.find('.edit_box').html('').append(html);
        break;
      case "word_edit"://单文字
        var text = domObj.text();
        editObj.find('.layer_input').val(text);
        break;
      case "single_pic_edit"://单图片链接
        var imgsrc = domObj.find('img').attr('src');
        var url = domObj.attr('href');
        editObj.find('.tip_size').html('图片建议尺寸:' + domObj.find('img').css('width') + ' * ' + domObj.find('img').css('height'));
        editObj.find('.thumbnail').css('background-image','url('+imgsrc+')');
        editObj.find('.thumbnail input[type="hidden"]').val(imgsrc);
        editObj.find('.img_bar input[type="hidden"]').val(url);
        break;
      case "word_link_edit"://单文字链接
        var text = domObj.find('a').text();
        var url = domObj.find('a').attr('href');
        editObj.find('.layer_input').val(text);
        editObj.find('input[type="hidden"]').val(url);
        break;
      case "slides_edit"://焦点大图
        var html = '';
        domObj.find('li').each(function () {
          var imgsrc = $(this).find('img').attr('src');
          var url = $(this).find('a').attr('href');
          html+='<div class="img_box clearfix"><div class="thumbnail" style="background-image:url('+imgsrc+')"><input type="file" class="file_default" name=""><input type="hidden" value="'+imgsrc+'"></div><div class="img_bar clearfix"><input type="hidden" value="'+url+'"><a class="input_btn link_btn" href="javascript:;"><i class="icon-link"></i></a><a class="input_btn del_btn" href="javascript:;"><i class="icon-trash"></i></a></div></div>'
        });
        editObj.find('.edit_box').html('').append(html);
        break;
      case "goods_slides"://商城特惠轮播
        var html = '';
        domObj.find('.sk_item').each(function () {
          var imgsrc = $(this).find('img').attr('src');
          var url = $(this).find('a').attr('href');
          var name = $(this).find('.sk_item_name').text();
          var price = $(this).find('.mod_price span').text();
          html+='<div class="img_box clearfix"><div class="thumbnail" style="background-image:url('+imgsrc+')"><input type="file" class="file_default" name=""><input type="hidden" value="'+imgsrc+'"></div><div class="goods_text"><input class="layer_input" placeholder="请输入标题" value="'+name+'" type="text"><input class="layer_input" style="text-align:center" placeholder="请输入价格" value="'+price+'" type="text"></div><div class="img_bar clearfix"><input type="hidden" value="'+url+'"><a class="input_btn link_btn" href="javascript:;"><i class="icon-link"></i></a><a class="input_btn del_btn" href="javascript:;"><i class="icon-trash"></i></a></div></div>'
        });
        editObj.find('.edit_box').html('').append(html);
        break;
    }

  });
  
  //关闭编辑框
  $('.layer_close').click(function () {
    $(this).parents('.layer_box').hide();
    $('.page_box').animate({
      margin: '0'
    },300)
  });
  
  //保存编辑框
  $('.layer_save').click(function () {

    var haserror = false;

    $(this).parents('.layer_box').find('.link_input').each(function () {
      if ($(this).find('input').val() == ""){
        $(this).addClass('has_error');
        haserror = true;
      }
      else{
        $(this).removeClass('has_error');
      }
    });

    if (haserror)
    {
      return ;
    }


    $(this).parents('.layer_box').hide();
    $('.page_box').animate({
      margin: '0'
    },300);

    // 弹出层对象
    var editObj = $("#"+domObj.attr("edit_type"));
    switch (domObj.attr("edit_type"))
    {
      case "hot_searchs_edit"://热门搜索
        var html ='';
        editObj.find('.edit_box').find('.layer_input').each(function () {
          html+='<a href="#" target="_blank">'+$(this).val()+'</a>';
        });
        domObj.html(html);
        break;
      case "top_banner_edit"://顶部广告
        var backcolor = editObj.find('.colorpicker-default input').val();
        var imgsrc = editObj.find('.thumbnail input[type="hidden"]').val();
        var url = editObj.find('.img_bar input[type="hidden"]').val();
        domObj.css('background',backcolor);
        domObj.find('img').attr('src',imgsrc);
        domObj.find('a').attr('href',url);
        break;
      case "multi_word_edit"://多文字链接
        var html ='';
        editObj.find('.edit_box').find('.layer_input').each(function () {
          html+='<a href="'+$(this).nextAll('input[type="hidden"]').val()+'" target="_blank">'+$(this).val()+'</a>';
        });
        domObj.html(html);
        break;
      case "word_edit"://单文字
        var text = editObj.find('.layer_input').val();
        domObj.text(text);
        break;
      case "single_pic_edit"://单图片链接
        var imgsrc = editObj.find('.thumbnail input[type="hidden"]').val();
        var url = editObj.find('.img_bar input[type="hidden"]').val();
        domObj.find('img').attr('src',imgsrc);
        domObj.attr('href',url);
        break;
      case "slides_edit"://焦点大图
        var html ='';
        var index ='';
        editObj.find('.edit_box').find('.img_box').each(function () {
          var imgsrc = $(this).find('.thumbnail input[type="hidden"]').val();
          var url = $(this).find('.img_bar input[type="hidden"]').val();
          var num = $(this).prevAll().length + 1;
          if($(this).prevAll().length == 0){
            index = 2;
          }
          else{
            index = 1;
          }
          html+='<li style="z-index:'+index+'; display: list-item;" index="'+num+'"><a href="'+url+'" target="_blank"><img src="'+imgsrc+'" width="790" height="340"></a></li>'
        });
        domObj.html(html);
        //生成下部小按钮
        $('.slideshow_footbar').html('');
        var length = $('.slideshow_photo li').length;
        for(var i = 0; i < length; i++)
        {
          $('.slideshow_footbar').prepend('<a class="slideshow-bt" index="'+(length-i)+'"></a>');
        }
        $('.slideshow_footbar .slideshow-bt:first').addClass('bt-on');
        break;
      case "goods_slides"://商城特惠轮播
        var html ='';
        editObj.find('.edit_box').find('.img_box').each(function () {
          var imgsrc = $(this).find('.thumbnail input[type="hidden"]').val();
          var url = $(this).find('.img_bar input[type="hidden"]').val();
          var name = $(this).find('.goods_text input:first-child').val();
          var price = $(this).find('.goods_text input:last-child').val();
          html+='<div class="sk_item"><div class="sk_item_pic"><a href="'+url+'" target="_blank" class="sk_item_pic_lk"><img src="'+imgsrc+'" alt="'+name+'" title="'+name+'" class="sk_item_img"><p class="sk_item_name">'+name+'</p></a><span class="sk_item_shadow"></span></div><p class="sk_item_price"><span class="mod_price"><i>¥</i><span>'+price+'</span></span></p></div>'
          domObj.find('.clearfix').html(html);
        });
        break;
    }
  });
  
  $(document).on('click','.del_btn',function () {
    if($(this).parent().hasClass('link_input') == true && $(this).parents('.link_input').siblings().length == 0 ){
      return ;
    }
    if($(this).parent().parent().hasClass('img_box') == true && $(this).parents('.img_box').siblings().length == 0 ){
      return ;
    }
    $(this).parents('.link_input').remove();
    $(this).parents('.img_box').remove();
  });
  $('.add_link').click(function () {
    if ($(this).hasClass('no_link') == true) {
      $(this).prev('.edit_box').append('<div class="link_input"><input class="layer_input fll" type="text"><a class="input_btn del_btn" href="javascript:;"><i class="icon-trash"></i></a></div>')
    }
    else {
      $(this).prev('.edit_box').append('<div class="link_input"><input class="layer_input fll" type="text"><a class="input_btn link_btn" href="javascript:;"><i class="icon-link"></i></a><a class="input_btn del_btn" href="javascript:;"><i class="icon-trash"></i></a><input type="hidden" value=""></div>')
    }
  });
  $('.add_img').click(function () {
    $(this).prev('.edit_box').append('<div class="img_box clearfix"><div class="thumbnail" style="background-image:url(img/no_img.png)"><input type="file" class="file_default" name=""><input type="hidden" value="img/no_img.png"></div><div class="img_bar clearfix"><input type="hidden" value=""><a class="input_btn link_btn" href="javascript:;"><i class="icon-link"></i></a><a class="input_btn del_btn" href="javascript:;"><i class="icon-trash"></i></a></div></div>')
  });
  $('.add_goods').click(function () {
    $(this).prev('.edit_box').append('<div class="img_box clearfix"><div class="thumbnail" style="background-image:url(img/no_img.png)"><input type="file" class="file_default" name=""><input type="hidden" value="img/no_img.png"></div><div class="goods_text"><input class="layer_input" placeholder="请输入标题" type="text"><input class="layer_input" style="text-align:center" placeholder="请输入价格" type="text"></div><div class="img_bar clearfix"><input type="hidden" value=""><a class="input_btn link_btn" href="javascript:;"><i class="icon-link"></i></a><a class="input_btn del_btn" href="javascript:;"><i class="icon-trash"></i></a></div></div>')
  });

  //tab切换
  function tab(tab,on,type){
    $(tab).find('.tabsNav').children().on(type,(function(){
      var index = $(this).prevAll().length;
      $(this).siblings().removeClass(on);
      $(this).addClass(on);
      $(this).parents('.tabs').find('.tabsContent').children().hide();
      $(this).parents('.tabs').find('.tabsContent').children().eq(index).show();
    }));
  }
  $(function(){
    tab(".tabs","cur","click")
  });

  $(document).on('click','.link_btn',function () {
    $('#link_dialog').modal('show');
  });
  $(document).on('click','.add_module',function () {
    $('#add_dialog').modal('show');
  });
  
  $('.module_edit').mouseenter(function () {
    $(this).append('<div class="module_toolbar"><a class="add_module" href="javascript:;" target="_blank"><i class="icon-plus-sign"></i> 添加模块</a><a class="del_module" href="javascript:;" target="_blank"><i class="icon-trash"></i> 删除</a></div><div class="module_tip">拖拽模块以挪动位置</div>');
  });
  $('.module_edit').mouseleave(function () {
    $(this).find('.module_toolbar').remove();
    $(this).find('.module_tip').remove();
  });

  $(document).on('click','.del_module',function () {
    $(this).parents('.module_edit').remove();
  })

});
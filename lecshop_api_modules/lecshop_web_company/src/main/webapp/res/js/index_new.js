//首页轮播图
$(function () {
	$(".floor_goods_slides").each(function () {
		var landscape = $(this).find(".landscape_slides"),
			portrait = $(this).find(".portrait_slides");
		landscape.slidesjs({
			width: 439,
			height: 240,
			navigation: false,
			play: {
				auto: true,
				pauseOnHover: true
			}
		});
		portrait.slidesjs({
			width: 219,
			height: 480,
			navigation: false,
			play: {
				auto: true,
				pauseOnHover: true
			}
		});
	});
	$('#slides1').slidesjs({
		width: 1000,
		height: 225,
		navigation: false,
		pagination: {
			active: false
		},
		play: {
			auto: false,
			pauseOnHover: true
		}
	});
});

$(document).ready(function () {
  //排行榜tab切换
  $('.tab_list li').mouseover(function(){
    var liindex = $('.tab_list li').index(this);
    $('.product_wrap div.clearfix').eq(liindex).fadeIn(150).siblings('div.clearfix').hide();
    var liWidth = $('.tab_list li').width();
    $('.top_tab .tab_list p').stop(false,true).animate({'left' : liindex * liWidth + 'px'},300);
  });
	//热门推荐
    $("#change_one li:gt(5)").hide();
    //换一组
    $("#replace-btn").click(function(){
        var $num = $("#change_one li:visible:last").index();
        if($("#change_one li:visible").length == 6 && $("#change_one li:visible:last").next("li").length > 0) {
            for(var i=$num+1;i<$num+7;i++) {
                $("#change_one li:lt("+($num+1)+")").hide();
                $("#change_one li:eq("+i+")").fadeIn();
            };
        } else {
            $("#change_one li:gt(5)").hide();
            $("#change_one li:lt(6)").fadeIn();
        };
    });
    //商品指向特效
    $('.goods_recommend li').hover(
        function () {
            $(this).find('.goods_img').stop().animate({
                top: '-10px'
            });
        },
        function () {
            $(this).find('.goods_img').stop().animate({
                top: '0'
            });
        });
    //首页楼层tab
    $('.goods_tab li').hover(function () {
        $(this).addClass('tab_selected').siblings().removeClass('tab_selected');
        var goods_tab_length = $(this).prevAll().length;
        $(this).parents('.main_title').siblings('.floor_content').find('.goods_recommend').eq(goods_tab_length).css("z-index", "2").siblings().css("z-index", "1");
    });
    //侧边栏咨询
    $('.customer_service').click(function () {
        $(this).parent().nextAll('.customer_box').toggle();
    });
    $('.close_cs').click(function () {
        $(this).parents('.customer_box').hide();
    });
    //侧边栏
    var offset = $('.fbt_inner:eq(0)').offset();
    $(window).scroll(function () {
        var scrollTop = $(document).scrollTop();
        if (scrollTop > offset.top) {
            $('.sideBar').show();
        }
        if (scrollTop < offset.top) {
            $('.sideBar').hide();
        }
    });
    $('.backtop_btn').click(function () {
        $('body,html').stop().animate({
            scrollTop: 0
        });
    });

  var scrollfloor = function () {
    var scrollTop = $(document).scrollTop();
    var floorItems = $('.fbt_inner');
    var sideItem = "";
    floorItems.each(function () {
      var contentItem = $(this);
      var offsetTop = $(this).offset().top;
      if (scrollTop > offsetTop - 100) {
        sideItem = "#" + contentItem.attr("id");
      }
    });
    if (sideItem != $(".sideBar").find(".cur").attr("href") && sideItem != "") {
      $(".sideBar").find(".cur").removeClass("cur");
      $(".sideBar").find("[href=" + sideItem + "]").addClass("cur");
    } else if (sideItem == "") {
      $(".sideBar").find(".cur").removeClass("cur");
    }
  };

    $(document).on('click','.sideBar .sideItem',function () {
        $(this).addClass("cur").parent().siblings().find('.sideItem').removeClass("cur");
        var $floor_eq = $(this).attr("href"),
            offset = $($floor_eq).offset();
        $("html, body").stop().animate({
            scrollTop: offset.top
        }, function () {
          $(window).scroll(scrollfloor);
        });
        return false;
    });
    $(window).scroll(function () {
      scrollfloor();
    });

});
//分类导航
$(document).ready(function () {
    $('.categorys_item').mouseenter(function () {
        $(this).addClass('hover').siblings().removeClass('hover');
        var item_length = $(this).prevAll().length;
        $('.categorys_layer').eq(item_length).show().siblings('.categorys_layer').hide();
    });
});
//首页轮播图
$(function()
{
	//生成下部小按钮
	var length = $('.slideshow_photo li').length;
	for(var i = 0; i < length; i++)
	{
		$('.slideshow_footbar').prepend('<a class="slideshow-bt" index="'+(length-i)+'"></a>');
  }
    $('.slideshow_footbar .slideshow-bt:first').addClass('bt-on');
	  $(document).on('mousedown','.slideshow_footbar .slideshow-bt',function () {
			slideTo(this);
		});
	
    var indexAllowAutoSlide = true;
    $('.slideshow_wrapper').mousedown(function()
    {
		indexAllowAutoSlide = false;
    }).mouseleave(function()
    {
		indexAllowAutoSlide = true;
    });

	//滚动
    setInterval(function()
    {
		if (indexAllowAutoSlide) slideDown(); 
    },3000);

});

function slideDown()
{
	var currentBt = $('.slideshow_footbar .slideshow-bt.bt-on');
    if (currentBt.length <= 0) return;
    var nxt = currentBt.get(0).nextSibling;
    slideTo(nxt?nxt:$('.slideshow_footbar .slideshow-bt:first').get(0));
}

function slideTo(o)
{
	if (!o) return;
	var currentIndex = $('.slideshow_footbar .slideshow-bt.bt-on').attr('index'),
		current = $('.slideshow_photo li[index='+currentIndex+']');
	var nxt = $('.slideshow_photo li[index='+$(o).attr('index')+']');
	if (currentIndex == $(o).attr('index')) { return; }
		
	$('.slideshow_footbar .slideshow-bt.bt-on').removeClass('bt-on');
	$(o).addClass('bt-on');
	
	nxt.css('z-index',2);
	
	current.css('z-index',3).stop().fadeOut(500,function()
	{
		$(this).css('z-index','1').show();
	});
}
//slideshow end

$(document).ready(function () {
	//迷你购物车删除
	$('.shopping_cart').hover(function () {
		$('.shopping_cart_layer').show();
	}, function () {
		$('.shopping_cart_layer').hide();
	});
	$('.sc_cost a').click(function () {
		$(this).parents('ul.scorder').remove();
	});
});
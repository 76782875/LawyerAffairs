// 基本路径
var basePath = $("#basePath").val();
$('.sale_type').change(function () {
    if ($(this).find('option:selected').val() == 0) {
        $(this).parents('.form-group').next('.form-group').show();
        $(this).parents('.form-group').nextAll('.year_group').hide();
    } else {
        $(this).parents('.form-group').next('.form-group').hide();
        $(this).parents('.form-group').nextAll('.year_group').show();
    }
});
$('.bio-desk').click(function () {
    $(this).addClass('active').siblings().removeClass('active')
});

function toPay() {
    var type;
    var money;
    if ($('.sale_type').val() == 0) {
        type = $(".bio-desk.price_desk.active").attr("type");
        money = $(".bio-desk.price_desk.active").attr("money");
    } else {
        type = $(".bio-desk.sale_desk.active").attr("type");
        money = $(".bio-desk.sale_desk.active").attr("money");
    }
    $("input[name='type']").val(type);
    $("input[name='money']").val(money);
    $("#form").submit();
}
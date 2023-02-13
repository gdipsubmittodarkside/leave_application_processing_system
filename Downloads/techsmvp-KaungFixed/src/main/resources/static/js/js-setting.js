$('#cadastroid').hide();
$('#bb').hide();

$("#tab1").click(function() {
    $('#filtroid').show();
    $('#cadastroid').hide();
    $('#aa').show();
    $('#bb').hide();
});

$("#tab2").click(function() {
    $('#filtroid').hide();
    $('#cadastroid').show();
    $('#aa').hide();
    $('#bb').show();
});

$('#LoadingFilter').click(function () {
    var btn = $(this)
    btn.button('loading')
});
(function ($) {
    $('#submitpost').on('click', function () {
        nullcheck();
    })

    function nullcheck() {
        var title = $('#title').val();
        var content = $('#mycontent').val();

        if(title == '') {
            alert("제목을 채워주세요.");

        }else if (content == ''){
            alert("본문을 채워주세요.");
        }else {
            $('#postform').submit();
        }
    }


})(jQuery);
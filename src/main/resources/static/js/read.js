(function ($) {


    $('#beforeBtn').on('click', function () {
        window.location.href=Cookies.get('before');
        return false;
    })

})(jQuery);
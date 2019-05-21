(function ($) {

    $('.readNoticeBtn').on('click', function () {
        Cookies.set('before', document.location.href);
    })
})(jQuery)
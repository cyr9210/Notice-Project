(function ($) {
    $('#registerSubmit').on('click', function () {
        nullCheck();
        confirmRegisterForm();
    })

    function confirmRegisterForm() {
        var input = $('#InputPassword').val();
        var repeat = $('#RepeatPassword').val();

        if(input != repeat){
            alert("비밀번호 확인 에러발생");
            location.href="/register";
        }else if (input == ''){
            alert("비밀번호를 입력해주세요.")
        } else{
            $('.user').submit();
        }
    }

    function nullCheck() {
        var name = $('#name').val();
        var email = $('#email').val();

        if(name == '') {
            alert("이름을 채워주세요.");

        };
        if (email == ''){
            alert("이메일을 채워주세요.");
        };
    }
})(jQuery);
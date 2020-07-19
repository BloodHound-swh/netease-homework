$(function () {
    var loginUrl = "/homework/useradmin/userlogin";

    $('#submit').click(function() {
        var user = {};
        var formData = new FormData();
        // 获取表单里的数据
        user.userName = $('#user-name').val();
        user.passWord = $('#password').val();
        formData.append('userStr', JSON.stringify(user));
        $.ajax({
            url : loginUrl,
            type : 'POST',
            data : formData,
            contentType : false,
            processData : false,
            cache : false,
            success : function(data) {
                if (data.success) {
                    var userId = data.userId;
                    if (userId == 2) {
                        window.location.href = "/homework/useradmin/buyerhome?userId=" + userId;
                    } else {
                        window.location.href = "/homework/useradmin/sellerhome?userId=" + userId;
                    }
                } else {
                    $.toast('登录失败！' + data.errMsg);
                }
            }
        });
    });

})
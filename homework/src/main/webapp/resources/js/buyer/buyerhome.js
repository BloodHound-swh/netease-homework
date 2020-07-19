$(function() {
    getlist();
    var userId = getQueryString('userId');
    var notBuyed = false;
    var allProductUrl = "/homework/productadmin/listproduct";
    var notBuyedProdcutUrl = "/homework/productadmin/listnopurchased";
    getlist();
    function getlist() {
        $.ajax({
            url : notBuyed ? notBuyedProdcutUrl : allProductUrl,
            type : "get",
            dataType : "json",
            success : function(data) {
                if (data.success) {
                    handleList(data.productList);
                    handleUser();
                }
            }
        });
    }

    function handleUser() {
        $.ajax({
            url : "/homework/useradmin/getuser?userId=" + userId,
            type : "get",
            dataType : "json",
            success : function(data) {
                if (data.success) {
                    $('#user-name').text(data.user.userName);
                } else {
                    $('#user-name').text("获取用户名失败");
                }
            }
        });
    }

    function handleList(data) {
        var html = '';
        data.map(function(item, index) {
            var desc = "未购买";
            if (item.buyerId == userId) {
                desc = "已购买";
            }
            var productUrl = "/homework/productadmin/productdetail?productId=" + item.productId;
            html += '<div class="row row-product"><div class="col-40">'
                + item.productName + '</div><div class="col-20">'
                + item.productPrice
                + '</div><div class="col-20">'
                + desc
                + '</div><div class="col-20">'
                + '<a href="' + productUrl + '">' + "查看详情" + '</a>' + '</div></div>';
        });
        $('.product-wrap').html(html);
    }

    $('#purchasedProduct').click(function() {
        $('.product-wrap').empty();
        notBuyed = true;
        getlist();
    });

    $('#allProduct').click(function() {
        $('.product-wrap').empty();
        notBuyed = false;
        getlist();
    });
});
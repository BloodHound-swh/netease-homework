$(function() {
    getlist();
    function getlist(e) {
        $.ajax({
            url : "/homework/productadmin/listproduct",
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

    function handleUser(data) {
        $('#user-name').text("请登录");
    }

    function handleList(data) {
        var html = '';
        data.map(function(item, index) {
            html += '<div class="row row-product"><div class="col-40">'
                + item.productName + '</div><div class="col-40">'
                + item.productPrice
                + '</div><div class="col-20">'
                + "加入购物车" + '</div></div>';
        });
        $('.product-wrap').html(html);
    }

    function goProduct() {
        if (status == 1) {
            return '<a href="/o2o/shopadmin/shopmanagement?shopId=' + id
                + '">进入</a>';
        } else {
            return '';
        }
    }
});
$(function() {
    // 从地址栏的URL里获取productId
    var productId = getQueryString('productId');
    // 获取商品信息的URL
    var productUrl = '/homework/productadmin/getproduct?productId=' + productId;
    // 访问后台获取该商品的信息并渲染
    $.getJSON(
        productUrl,
        function(data) {
            if (data.success) {
                // 获取商品信息
                var product = data.product;
                // 给商品信息相关HTML控件赋值

                // 商品缩略图
                $('#product-img').attr('src', product.imgAddr);
                // 商品更新时间
                // $('#product-time').text(
                //     new Date(product.lastEditTime).Format("yyyy-MM-dd"));
                // 商品名称
                $('#product-name').text(product.productName);
                // 商品简介
                $('#product-desc').text(product.productDesc);
                // 商品价格
                $('#product-price').text('￥' + product.productPrice);
            }
        });
    $.init();
});

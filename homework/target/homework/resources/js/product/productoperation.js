$(function() {
    // 从URL里获取productId参数的值
    var productId = getQueryString('productId');
    // 通过productId获取商品信息的URL
    var infoUrl = '/homework/productadmin/getproduct?productId=' + productId;
    // 更新商品信息的URL
    var productPostUrl = '/homework/productadmin/modifyproduct';
    // 由于商品添加和编辑使用的是同一个页面，
    // 该标识符用来标明本次是添加还是编辑操作
    var isEdit = false;
    if (productId) {
        // 若有productId则为编辑操作
        getInfo(productId);
        isEdit = true;
    } else {
        productPostUrl = '/homework/productadmin/addproduct';
    }

    // 获取需要编辑的商品的商品信息，并赋值给表单
    function getInfo(id) {
        $.getJSON(
                infoUrl,
                function(data) {
                    if (data.success) {
                        // 从返回的JSON当中获取product对象的信息，并赋值给表单
                        var product = data.product;
                        $('#product-name').val(product.productName);
                        $('#product-desc').val(product.productDesc);
                        $('#product-price').val(product.productPrice);
                    }
                });
    }

    // 提交按钮的事件响应，分别对商品添加和编辑操作做不同响应
    $('#submit').click(
        function() {
            // 创建商品json对象，并从表单里面获取对应的属性值
            var product = {};
            product.productName = $('#product-name').val();
            product.productDesc = $('#product-desc').val();
            product.productPrice = $('#product-price').val();
            product.productId = productId;
            // 获取缩略图文件流
            var thumbnail = $('#small-img')[0].files[0];
            // 生成表单对象，用于接收参数并传递给后台
            var formData = new FormData();
            formData.append('thumbnail', thumbnail);
            // 将product json对象转成字符流保存至表单对象key为productStr的的键值对里
            formData.append('productStr', JSON.stringify(product));
            // 将数据提交至后台处理相关操作
            $.ajax({
                url : productPostUrl,
                type : 'POST',
                data : formData,
                contentType : false,
                processData : false,
                cache : false,
                success : function(data) {
                    if (data.success) {
                        $.toast('提交成功！');
                        $('#captcha_img').click();
                        window.location.href = '/homework/useradmin/sellerhome';
                    } else {
                        $.toast('提交失败！');
                        $('#captcha_img').click();
                    }
                }
            });
        });

});
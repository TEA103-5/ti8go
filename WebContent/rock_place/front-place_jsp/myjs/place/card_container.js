$(function () {

    // 調整看是否能將點擊的該張card貼齊上方?
    $(".card").on("click", function () {


        alert("777")

        var id = $(this).attr("data-id");
        console.log(id)

        // 取得目標區塊的y座標
        var target_top = $(this).offset().top;
        console.log(target_top)


        // 取得body物件 這種寫法在各瀏覽器上最保險
        let card_container = $("#card-container");
        // var $body = (window.opera) ? (document.compatMode == "CSS1Compat" ? $('html') : $('body')) : $('html,body');

        card_container.scrollTop(target_top);
        // 移動捲軸無動畫效果
        //$body.scrollTop(target_top);

        // 移動捲軸有動畫效果
        // $body.animate({
        //     scrollTop: target_top
        // }, 800);

    })

    // 取消地點詳情 加入收藏點擊時的冒泡事件
    $(".btn.btn-secondary").on("click", function (e) {
        e.stopPropagation();
    })


})


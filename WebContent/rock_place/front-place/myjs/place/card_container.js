$(function () {

    // 每張card高度為416.75, 找出第幾張card後, 將高度貼齊上方
    $(".card").on("click", function () {

        // 因為card會因文字大小而改變大小, offset跟position都沒辦法取到
        // 合適高度 , 找到第幾張卡後 , 把每個的height()累加後再移動?

        // 之後要用經緯度找出第幾張card
        let id = $(this).attr("data-id");
        console.log(id)

        let card_container = $("#card-container");

        // 移動捲軸無動畫效果
        // card_container.scrollTop((id - 1) * 416.750);

        // 移動捲軸有動畫效果
        card_container.animate({
            scrollTop: ((id - 1) * 416.75)
        }, 800);

    })

    // 取消地點詳情 加入收藏點擊時的冒泡事件
    $(".btn.btn-secondary").on("click", function (e) {
        e.stopPropagation();
    })


})


$(function () {

    // 每張card高度為416.75, 找出第幾張card後, 將高度貼齊上方
    $(".card").on("click", function () {

        // 因為card會因文字大小而改變大小, offset跟position都沒辦法取到
        // 合適高度 , 找到第幾張卡後 , 把每個的height()累加後再移動?

        // 之後要用經緯度找出第幾張card
        // let id = $(this).attr("data-id");
        // console.log(id)
        let card_index = $(this).index("div.card");
  console.log($(this).offset().top)
  console.log("第" + card_index + "個card")

        let card_container = $("#card-container");
        let card_total_height = 0;
        
        // height()沒有計算到card的上下各1px的border ,補上 
        for (let i = 0; i < card_index; i++) {
            card_total_height += $("div.card").eq(i).height() + 2;
        };

  console.log("card距離頂端總長度為" + card_total_height);



        // 移動捲軸無動畫效果
        // card_container.scrollTop((id - 1) * 416.750);

        // 移動捲軸有動畫效果
        card_container.animate({
            // scrollTop: ((id - 1) * 416.75)
            scrollTop: card_total_height
        }, 800);

        // 點擊card時將地圖中心移到該card對應的marker
        map.panTo(markers[card_index].getPosition());

        // 將當前點擊的索引當作參數呼叫DisplayInfoWindow
        DisplayInfoWindow(card_index)
//console.log("測試1")
        // 取得點擊的marker座標 , 並繪製路線
        route_destination = markers[card_index].getPosition()
        addDirectionsRoute(route_origin, route_destination)

        // 先處理地址字串, 取出區的部分, 再呼叫天氣api ,
        let district_index = $(".card").eq(card_index).find(".card-text").text().indexOf("區")
        // console.log("district_index" + district_index)
        let district = $(".card").eq(card_index).find(".card-text").text().substring(district_index - 2, district_index + 1)
        // console.log("district" + district)
        call_weather_api(district);
//console.log("測試2")
    })

    // 取消"地點詳情" "加入收藏"兩個按鈕點擊時的冒泡事件
    $(".btn.btn-secondary").on("click", function (e) {
        e.stopPropagation();
    })





})


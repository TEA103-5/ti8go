// let navbar = document.getElementById("search-bar");

// navbar.addEventListener("click", function (e) {
//     alert("777")
// })


// 宣告將地址選單套件初始化的函數
function init() {
    new TwCitySelector();
}
init();




// 綁定選擇交通方式欄位的事件 , 選擇完交通工具後,再次產生新路線
$(".custom-select").on("change", function (e) {
    if (route_origin == null || route_destination == null) {
        alert("尚未選擇地點")
    } else {
        addDirectionsRoute(route_origin, route_destination);
    }

})




// var map;

// let marker;


// function initMap() {
//     map = new google.maps.Map(document.getElementById('map'), {
//         center: { lat: 25.152780, lng: 121.490510 },
//         zoom: 15,
//     })




//     let myLatlng = new google.maps.LatLng(25.152780, 121.490510);
//     // let myLatlng2 = new google.maps.LatLng(25.152820, 121.492510);

//     // let mapOptions = {
//     //     zoom: 18,
//     //     center: myLatlng
//     // }
//     // map = new google.maps.Map(document.getElementById("map"), mapOptions);

//     marker = new google.maps.Marker({
//         position: myLatlng,
//         title: "Hello World!",
//         map: map
//     });

//     // To add the marker to the map, call setMap();
//     // marker.setMap(map);


//     marker.addListener("click", function (e) {
//         alert(marker.getPosition().lat())
//         console.log(marker.getPosition().lat());
//     })


// }




let currentInfoWindow  // 供DisplayInfoWindow()方法使用

let map;
let markers = [];
// position陣列改成jsp版時要拉出來放到jsp本體裡,以便初始化時用EL定義
//let position = [
//    { label: 'A', lat: 25.0336962, lng: 121.5643673 },
//    { label: 'B', lat: 25.0333698, lng: 121.5641564 },
//    { label: 'C', lat: 25.033899, lng: 121.564329 },
//    { label: 'D', lat: 25.0338407, lng: 121.5645269 },
//    { label: 'E', lat: 25.03361, lng: 121.56083 }
//];

function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 19,
        center: {
            // lat: 25.0336962,
            // lng: 121.5643673
            lat: position[0].lat,
            lng: position[0].lng
        }
    });

    for (var i = 0; i < position.length; i++) {
        addMarker(i);
    }
}

function addMarker(n) {
    markers[n] = new google.maps.Marker({
        position: {
            lat: position[n].lat,
            lng: position[n].lng
        },
        map: map,
        label: position[n].label
    });





    // 新增完marker同時綁定點擊事件 , 點擊時顯示marker經緯度
    markers[n].addListener("click", function (e) {


        // if (currentInfoWindow != null) {

        //     currentInfoWindow.close();
        //     currentInfoWindow = "";

        // }


        // console.log(n)
        // alert(e)
        console.log(markers[n].getPosition().lat())
        console.log(markers[n].getPosition().lng())

        // 以下測試中----------------

        let target_lat = markers[n].getPosition().lat();
        let target_lng = markers[n].getPosition().lng();

        let card = $(".card");
        console.log(card.length);

        // for (let i = 0; i < card.length; i++) {
        //     let card_lng = card.eq(i).attr("data-longitude");
        //     if (card_lng == target_lng) {
        //         console.log("target-data-id=" + card.eq(i).attr("data-id"));
        //     }
        // }

        // 依據點擊的marker的經緯度找到對應的card,再把該card貼齊上方
        for (let i = 0; i < card.length; i++) {
            let card_lng = card.eq(i).attr("data-longitude");
            let card_lat = card.eq(i).attr("data-latitude");
            if (card_lng == target_lng && card_lat == target_lat) {
                console.log("點到的是對應第" + i + "個card的marker")
                // console.log("target-data-id=" + card.eq(i).attr("data-id"));

                let card_container = $("#card-container");
                let card_total_height = 0;
                for (let k = 0; k < i; k++) {
                    card_total_height += $("div.card").eq(k).height();
                };

                card_container.animate({
                    // scrollTop: ((id - 1) * 416.75)
                    scrollTop: card_total_height
                }, 800);


                // 將地圖中心移動到點擊的該游標上
                map.panTo(this.getPosition());

                DisplayInfoWindow(i);

            }
        }



    })

}


// 當前有InfoWindow時將他關閉, 並在指定marker產生新的InfoWindow
function DisplayInfoWindow(count) {

    console.log("DisplayInfoWindow成功")

    if (currentInfoWindow != null) {

        currentInfoWindow.close();
        currentInfoWindow = "";

    }

    infowindow = new google.maps.InfoWindow({
        content: $("div.card").eq(count).find(".card-title").text(),
    });

    infowindow.open(map, markers[count]);
    currentInfoWindow = infowindow;
}

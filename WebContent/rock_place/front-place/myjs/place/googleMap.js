
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

// -----------------0415新增
let self_lat;
let self_lng;
let self_marker;

let directionsService; //用來儲存路線服務
let directionsDisplay; //用來儲存路線顯示圖

let route_origin; //　用來擺放路線起點經緯度
let route_destination; // 用來擺放路線終點經緯度

let route_markers = []
let route_infowindows = []

if (navigator.geolocation) {
    // 執行要權限的function , 非https無法取得使用者位置, 如失敗先將地點定位在教室

    // 使用者不提供權限，或是發生其它錯誤
    function error() {
        alert('無法取得你的位置');
    }

    // 使用者允許抓目前位置，回傳經緯度
    function success(position) {
        console.log(position.coords.latitude, position.coords.longitude);
        self_lat = position.coords.latitude
        self_lng = position.coords.longitude

        route_origin = { lat: self_lat, lng: self_lng }; // 定義路線起點經緯度
    }

    // 跟使用者拿所在位置的權限
    navigator.geolocation.getCurrentPosition(success, error);

} else {
    console.log("不支援取得位置");
    self_lat = 25.033952;
    self_lng = 121.564360;
}
// -----------------0415新增




// position陣列改成jsp版時要拉出來放到jsp本體裡,以便初始化時用EL定義
let position = [
    { label: 'A', lat: 25.0336962, lng: 121.5643673 },
    { label: 'B', lat: 25.0333698, lng: 121.5641564 },
    { label: 'C', lat: 25.033899, lng: 121.564329 },
    { label: 'D', lat: 25.0338407, lng: 121.5645269 },
    { label: 'E', lat: 25.03361, lng: 121.56083 }
];

function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 19,
        center: {
            // lat: position[0].lat,
            // lng: position[0].lng
            lat: self_lat,
            lng: self_lng
        }
    });

    add_self_marker(); // 0415新增 , 標上目前所在位置

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

                // 顯示標示地名的marker視窗
                DisplayInfoWindow(i);

                // 取得點擊的marker座標 , 並繪製路線
                route_destination = this.getPosition()
                addDirectionsRoute(route_origin, route_destination)

                // 先處理地址字串, 取出區的部分, 再呼叫天氣api ,
                let district_index = $(".card").eq(i).find(".card-text").text().indexOf("區")
                // console.log("district_index" + district_index)
                let district = $(".card").eq(i).find(".card-text").text().substring(district_index - 2, district_index + 1)
                // console.log("district" + district)
                call_weather_api(district);

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


// ------------0415新增
function add_self_marker() {  // 用來新增目前位置的marker
    self_marker = new google.maps.Marker({
        position: {
            lat: self_lat,
            lng: self_lng
        },
        map: map,
        label: "目前位置"
    });
}

function addDirectionsRoute(route_origin, route_destination) {

    // 取得目前選取的交通方式
    let route_travelMode = $(".custom-select").val();

    // 如果圖上已經有路線的marker, 就先清掉 , 並把陣列清空
    if (route_markers.length != 0) {
        for (let k = 0; k < route_markers.length; k++) {
            route_markers[k].setMap(null);
        }
        route_markers = [];
    }

    // 如果圖上已經有路線的話 先清掉
    if (directionsDisplay != null) {
        directionsDisplay.setMap(null);
        directionsDisplay = null;
    }

    // 載入路線服務與路線顯示圖層
    directionsService = new google.maps.DirectionsService();
    directionsDisplay = new google.maps.DirectionsRenderer();

    // 放置路線圖層
    directionsDisplay.setMap(map);

    // 路線相關設定
    let request = {
        origin: route_origin,
        destination: route_destination,
        travelMode: route_travelMode
    };

    // 繪製路線
    directionsService.route(request, function (result, status) {
        if (status == 'OK') {
            // 回傳路線上每個步驟的細節
            let steps = result.routes[0].legs[0].steps;
            steps.forEach((e, i) => {
                console.log(steps);
                // 加入地圖標記
                route_markers[i] = new google.maps.Marker({
                    position: { lat: e.start_location.lat(), lng: e.start_location.lng() },
                    map: map,
                    label: { text: i + '', color: "#fff" }
                });
                // 加入資訊視窗
                route_infowindows[i] = new google.maps.InfoWindow({
                    content: e.instructions
                });
                // 加入地圖標記點擊事件
                route_markers[i].addListener('click', function () {
                    if (route_infowindows[i].anchor) {
                        route_infowindows[i].close();
                    } else {
                        route_infowindows[i].open(map, route_markers[i]);
                    }
                });
            });
            directionsDisplay.setDirections(result);
        } else {
            console.log(status);
        }
    });
}

function call_weather_api(district) {

    let target_place = district;


    // 鄉鎮天氣預報-臺北市未來2天天氣預報 , https://opendata.cwb.gov.tw/dist/opendata-swagger.html#/
    $.ajax({
        url: "https://opendata.cwb.gov.tw/api/v1/rest/datastore/F-D0047-061?Authorization=" + weather_api_key + "&elementName=WeatherDescription",           // 資料請求的網址
        type: "GET",                  // GET | POST | PUT | DELETE | PATCH
        // data: "資料",               // 傳送資料到指定的 url
        dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
        success: function (data) {      // request 成功取得回應後執行
            console.log(data);
            console.log(data.records.locations[0].location);

            let location_array = data.records.locations[0].location; // 裝的是含有天氣資料的各個區

            for (let i = 0; i < location_array.length; i++) {
                if (location_array[i].locationName == target_place) {
                    console.log(location_array[i].locationName);
                    console.log(i)

                    console.log(location_array[i].weatherElement[0].time) // 裝的是有接下來三天各時段天氣描述的陣列

                    let first_interval_startTime = location_array[i].weatherElement[0].time[0].startTime;
                    let first_interval_endTime = location_array[i].weatherElement[0].time[0].endTime;
                    let first_interval_elementValue = location_array[i].weatherElement[0].time[0].elementValue[0].value;
                    let second_interval_startTime = location_array[i].weatherElement[0].time[1].startTime;
                    let second_interval_endTime = location_array[i].weatherElement[0].time[1].endTime;
                    let second_interval_elementValue = location_array[i].weatherElement[0].time[1].elementValue[0].value;
                    console.log(first_interval_startTime)
                    console.log(first_interval_endTime)
                    console.log(first_interval_elementValue)

                    // alert(target_place + "\n" + "時間:" + first_interval_startTime + "到" + first_interval_endTime + "\n" + "天氣為" + first_interval_elementValue +
                    //     "\n" + "時間:" + second_interval_startTime + "到" + second_interval_endTime + "\n" + "天氣為" + second_interval_elementValue)

                    let alert_msg = target_place + "\n" + "時間:" + first_interval_startTime + "到" + first_interval_endTime + "\n" + "天氣為" + first_interval_elementValue +
                        "\n" + "時間:" + second_interval_startTime + "到" + second_interval_endTime + "\n" + "天氣為" + second_interval_elementValue

                    // function async_alert() {
                    // alert(alert_msg);
                    // }

                    // $('#exampleModal').modal({
                    //     keyboard: false
                    // })
                    swal(alert_msg)
                }
            }
        }
    });
}


// ------------0415新增
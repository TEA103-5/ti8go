
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



let map;
let markers = [];
let position = [
    { label: 'A', lat: 25.0336962, lng: 121.5643673 },
    { label: 'B', lat: 25.0333698, lng: 121.5641564 },
    { label: 'C', lat: 25.033899, lng: 121.564329 },
    { label: 'D', lat: 25.0338407, lng: 121.5645269 },
    { label: 'E', lat: 25.0336377, lng: 121.5645727 }
];

function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 19,
        center: {
            lat: 25.0336962,
            lng: 121.5643673
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
        // console.log(n)
        // alert(e)
        console.log(markers[n].getPosition().lat())
    })
}



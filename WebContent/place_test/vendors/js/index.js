/* Set the width of the side navigation to 250px */
function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
}

/* Set the width of the side navigation to 0 */
function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
}

// let test = document.getElementsByClassName('test');

// test.addEventListener("click", function (e) {
//     alert("777");
// })


let placeList_toggle = $("#placeList_toggle");


// 初始化地圖的部分  開始

let map;

function initMap() {
    map = new google.maps.Map(document.getElementById("map"), {
        center: { lat: -34.397, lng: -34.397 },
        zoom: 8,
    });
}


// 初始化地圖的部分  結束
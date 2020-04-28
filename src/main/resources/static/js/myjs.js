$('document').ready(function () {
    $("body").css('visibility', 'visible');
    var localData = [$('#teacher').val(), $('#start').val() + '/' + $('#end').val(), $('#leader').val()]
    localStorage.setItem("data", localData);

})


//本地启动改成localhost
//var webSocket = new WebSocket("ws://172.16.29.107:18080/websocket");
var webSocket = new WebSocket("ws://localhost:18080/websocket");

//
webSocket.onerror = function (event) {

    console.log("error")

};


webSocket.onopen = function (event) {

    console.log("Connect Success");

};

//
webSocket.onmessage = function (event) {

    makeDataOnWeb(event.data);


};


webSocket.onclose = function (event) {

    console.log("Connect Close");

};
//维护一个长度为LENGTH的数组，存储最近的消息

var monitor_top_ten_messages = []
var over_speed_car_messages = []
var LENGTH = 10
var areas = ["京", "深", "沪", "鲁"]
var analyze_car_by_license_data = [
    {value: 0, name: '京'},
    {value: 0, name: '深'},
    {value: 0, name: '沪'},
    {value: 0, name: '鲁'},
]
//近一小时车流量
var recent_num = 0;
//总车流量
var total_num = 0;
var monitor_flow_data = [0, 0, 0, 0, 0, 0, 0, 0, 0]
var area_flow_data = [

    {name: '昌平区', value: 0},
    {name: '顺义区', value: 0},
    {name: '海淀区', value: 0},
    {name: '石景山区', value: 0},
    {name: '西城区', value: 0},
    {name: '东城区', value: 0},
    {name: '朝阳区', value: 0},
    {name: '大兴区', value: 0}
]

function updateTable(data) {
    var json = JSON.parse(data);


    //载入超速数据

    if (json.hasOwnProperty("overSpeedCar")) {
        //如果有数据
        var over_speed_car = json.overSpeedCar;
        for (var key in over_speed_car) {
            if (over_speed_car_messages.length == LENGTH) {
                //数组满了，把最旧的数据移除
                over_speed_car_messages.shift();
            }
            over_speed_car_messages.push(over_speed_car[key]);
        }
    }
    //载入卡口分类数据

    if (json.hasOwnProperty("monitorFlow")) {
        var monitor_flow = json.monitorFlow;
        for (var i = 0; i < 9; i++) {

            if (monitor_flow.hasOwnProperty(String(i))) {
                monitor_flow_data[i] = monitor_flow[String(i)];
            }


        }
        monitor_flow_histogram(monitor_flow_data);
    }

    //载入区域分类数据

    if (json.hasOwnProperty("areaFlow")) {

        var area_flow = json.areaFlow;
        for (var i = 0; i < 8; i++) {
            if (area_flow.hasOwnProperty(String(i))) {
                area_flow_data[i].value = area_flow[String(i)] ;
            }


        }
        map(area_flow_data);
    }
    //载入速度前十信息
    if (json.hasOwnProperty("monitorTopTen")) {
        var monitor_top_ten = json.monitorTopTen;
        for (var key in monitor_top_ten) {
            if (monitor_top_ten_messages.length == LENGTH) {
                monitor_top_ten_messages.shift();
            }
            monitor_top_ten_messages.push(monitor_top_ten[key]);
        }


    }

    //载入牌照分类信息

    if (json.hasOwnProperty("analyzeCarByLicense")) {
        var analyze_car_by_license = json.analyzeCarByLicense;
        for (var i = 0; i < 4; i++) {
            if (analyze_car_by_license.hasOwnProperty(areas[i])) {

                analyze_car_by_license_data[i].value = analyze_car_by_license[areas[i]]
            }


        }

    }
    //绘制饼图
    analyze_by_license_pie(analyze_car_by_license_data)

    //载入近一小时车流量
    if (json.hasOwnProperty("carFlow")) {
        recent_num = json["carFlow"];
    }
    //载入总车流量
    if (json.hasOwnProperty("totalCarFlow")) {
        total_num = json["totalCarFlow"];
    }

    //转换超速信息
    var over_speed_str = ""
    for (var i = 0; i < over_speed_car_messages.length; i++) {
        var temp = "<tr>"
        temp += "<td>" + over_speed_car_messages[i].monitor_id + "</td>"
            + "<td>" + over_speed_car_messages[i].camera_id + "</td>"
            + "<td>" + over_speed_car_messages[i].license + "</td>"
            + "<td>" + over_speed_car_messages[i].speed + "</td>"
            + "<td>" + over_speed_car_messages[i].area_id + "</td>"
            + "<td>" + over_speed_car_messages[i].road_id + "</td>"
            + "<td>" + over_speed_car_messages[i].monitor_time + "</td>"
        temp += "</tr>";
        over_speed_str += temp;

    }
    // 转换top10信息
    var top_str = ""
    for (var i = 0; i < monitor_top_ten_messages.length; i++) {
        var temp = "<tr>"
        temp += "<td>" + monitor_top_ten_messages[i].monitor_id + "</td>"
            + "<td>" + monitor_top_ten_messages[i].license + "</td>"
            + "<td>" + monitor_top_ten_messages[i].speed + "</td>"
            + "<td>" + monitor_top_ten_messages[i].monitor_time + "</td>"
        temp += "</tr>";
        top_str += temp;

    }


    document.getElementById("overSpeedCarList").innerHTML = over_speed_str;
    document.getElementById("top10List").innerHTML = top_str;
    document.getElementById("recentCarFlow").innerText = recent_num;
    document.getElementById("totalCarFlow").innerText = String(total_num);

//绘制图表


}

function makeDataOnWeb(data) {
    //每次有新的信息就更新图表
    updateTable(data)

};


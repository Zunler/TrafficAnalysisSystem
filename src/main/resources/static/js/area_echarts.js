function map(map_data) {
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('map_1'));



    var geoCoordMap = {

        '昌平区': [116.237832, 40.226854],
        '顺义区': [116.663242, 40.1362],
        '海淀区': [116.304872, 39.96553],
        '石景山区': [116.229612, 39.912017],
        '西城区': [116.372397, 39.918561],
        '东城区': [116.42272, 39.934579],
        '朝阳区': [116.449767, 39.927254],
        '大兴区': [116.34805, 39.732833]
    };
    var convertData = function (data) {
        var res = [];
        for (var i = 0; i < data.length; i++) {
            var geoCoord = geoCoordMap[data[i].name];
            if (geoCoord) {
                res.push({
                    name: data[i].name,
                    value: geoCoord.concat(data[i].value)
                });
            }
        }
        return res;
    };


    option = {
        // backgroundColor: '#404a59',


        tooltip: {
            trigger: 'item',
            formatter: function (params) {
                if (typeof (params.value)[2] == "undefined") {
                    return params.name + ' : ' + params.value;
                } else {
                    return params.name + ' : ' + params.value[2];
                }
            }
        },
        title: {
            text: '北京各区车流量',
            textStyle: {color: '#fff'},
            //subtext: '纯属虚构',
            x: 'center'
        },

        geo: {
            map: '北京',
            mapType: 'province',
            label: {
                emphasis: {
                    show: false
                }
            },
            roam: false,//禁止其放大缩小
            itemStyle: {
                normal: {
                    areaColor: '#4c60ff',
                    borderColor: '#002097'
                },
                emphasis: {
                    areaColor: '#ff2d23'
                }
            }
        },
        series: [
            {
                name: '消费金额',
                type: 'scatter',
                coordinateSystem: 'geo',
                data: convertData(map_data),
                symbolSize: function (val) {
                    return val[2]*2 ;
                },
                label: {
                    normal: {
                        formatter: '{b}',
                        position: 'right',
                        show: false
                    },
                    emphasis: {
                        show: true
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#ffeb7b'
                    }
                }
            }


        ]
    };

    myChart.setOption(option);
    window.addEventListener("resize", function () {
        myChart.resize();
    });
}



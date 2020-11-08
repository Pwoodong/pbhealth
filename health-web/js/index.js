$(function() {
    /** 获取当天日期 */
    (function() {
        const now = new Date();
        const year = now.getFullYear();
        const month = now.getMonth()+1;
        const day = now.getDate();
        $("#nowDate").html(year+"年"+month+"月"+day+"日");
    })();

    /** 获取统计数据 */
    $.ajax({
        url: "data/work-order.json",
        dataType: "json"
    }).done(function(data) {
        rollNum("total", 0, data.statistics.total);
        rollNum("send", 0, data.statistics.send);
        rollNum("take", 0, data.statistics.take);
        rollNum("complete", 0, data.statistics.complete);
        rollNum("timelyAcceptanceRate", 0, data.statistics.timelyAcceptanceRate, 2);
        rollNum("timelyCompletionRate", 0, data.statistics.timelyCompletionRate, 2);
    }).fail(function(jqXHR, textStatus) {
        console.log("Ajax Error: ", textStatus);
    });

    /** 获取排行数据 */
    const rankChart = echarts.init(document.getElementById("rankChart"), "shine");
    const rankChartOpt = {
        tooltip: {
            trigger: "axis",
            axisPointer: {
                type: "shadow"
            },
            formatter: function(params) {
                const param = params[0];
                const marker = '<span style="display:inline-block;margin-right:5px;border-radius:10px;width:10px;height:10px;background-color:#e6b600;"></span>';
                const suffix = '<span style="margin-left:5px;font-size:12px;"></span>';
                return param.name + "<br />" +
                    marker + "排名：" + (param.dataIndex+1) + "<br />" +
                    marker + "跑量：" + param.value + suffix;
            }
        },
        grid: {
            top: 10,
            bottom: 10,
            left: 60
        },
        xAxis: {
            show: false,
            type: "value"
        },
        yAxis: {
            type: "category",
            inverse: true,
            axisLine: {show: false},
            axisTick: {show: false},
            axisLabel: {
                fontSize: 12,
                color: "#b0c2f9"
            }
        },
        series: [{
            name: "跑量排行",
            type: "bar",
            barCategoryGap: "60%",
            label: {
                show: true,
                position: "right",
                fontSize: 12,
                color: "#188df0",
                emphasis: {
                    color: "#e6b600"
                }
            },
            itemStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(
                        0, 1, 1, 1,
                        [
                            {offset: 0, color: '#b0c2f9'},
                            {offset: 0.5, color: '#188df0'},
                            {offset: 1, color: '#185bff'}
                        ]
                    )
                },
                emphasis: {
                    color: new echarts.graphic.LinearGradient(
                        0, 1, 1, 1,
                        [
                            {offset: 0, color: '#b0c2f9'},
                            {offset: 0.7, color: '#e6b600'},
                            {offset: 1, color: '#ceac09'}
                        ]
                    )
                }
            }
        }]
    };
    rankChart.setOption(rankChartOpt);
    $.ajax({
        url: "data/work-order.json",
        dataType: "json"
    }).done(function() {
        $("#rankChart").addClass("chart-done");
    }).done(function(data) {
        const xData = [];
        const yData = [];
        for(let i in data.type) {
            xData.push(data.type[i].CNT);
            yData.push(data.type[i].WTYPENAME);
        }
        rankChart.setOption({
            yAxis: {
                data: yData
            },
            series: [{
                name: "跑量排名",
                data: xData
            }]
        });
    }).fail(function(jqXHR) {
        console.log("Ajax Fail: ", jqXHR.status, jqXHR.statusText);
    });

    /** 工单环比 */
    const mapChart = echarts.init(document.getElementById("mapChart"), "shine");

    var mapChartOpt = {
        color: ['#ff7f50', '#87cefa'],
        title : {
            text : ''
        },
        tooltip : {
            trigger : 'item',
            axisPointer : {
                type : 'shadow'
            },
            formatter : "{a}<br/> 类型: {b}<br/>跑量: {c}"
        },
        legend : {
            data : "",
            left : 'center',
            top : '10%'
        },
        grid : {
            top:'20%',
            left : '2%',
            right : '6%',
            bottom : '3%',
            containLabel : true
        },
        toolbox : {
            show : true,
            feature : {
                mark : {
                    show : true
                },
                dataZoom : {
                    show : true
                },
                dataView : {
                    show : true,
                    readOnly : true
                },
                magicType : {
                    show : true,
                    type : [ 'line'  ]
                },
                restore : {
                    show : true
                },
                saveAsImage : {
                    show : true
                }
            }
        },
        calculable : true,
        xAxis : [ {
            type : 'category',
            data : ""
        } ],
        yAxis : [ {
            type : 'value'
        } ],
        series : ""
    };

    mapChart.setOption(mapChartOpt);
    $.ajax({
        url: "data/work-order.json",
        dataType: "json"
    }).done(function() {
        $("#mapChart").addClass("chart-done");
    }).done(function(data) {
        const chartData = [];
        const data1 = new Array();
        const data2 = new Array();
        const data3 = new Array();
        const data4 = new Array();
        const data5 = new Array();

        data1[0] = data.link[0].AMONTH.substr(0,4)+'年'+data.link[0].AMONTH.substr(4)+'月' ;
        data1[1] = data.link[0].BMONTH.substr(0,4)+'年'+data.link[0].BMONTH.substr(4)+'月' ;
        for(let i in data.link) {
            data2[i] = data.link[i].WTYPENAME;
            data4[i] = data.link[i].AVALUE;
            data5[i] = data.link[i].BVALUE;

            chartData.push({
                name: data.link[i].WTYPENAME,
                value: data.link[i].BVALUE
            });
        }
        data3[0] = {
            name : data1[0],
            type : 'bar',
            data : data4,
            markPoint : {
                data : [ {
                    type : 'max',
                    name : '最大值'
                }, {
                    type : 'min',
                    name : '最小值'
                } ]
            },
            markLine : {
                data : [ {
                    type : 'average',
                    name : '平均值'
                } ]
            }
        };
        data3[1] = {
            name : data1[1],
            type : 'bar',
            data : data5,
            markPoint : {
                data : [ {
                    type : 'max',
                    name : '最大值'
                }, {
                    type : 'min',
                    name : '最小值'
                } ]
            },
            markLine : {
                data : [ {
                    type : 'average',
                    name : '平均值'
                } ]
            }
        };
        mapChartOpt.legend.data = data1;
        mapChartOpt.xAxis[0].data = data2;
        mapChartOpt.series = data3;
        mapChart.setOption(mapChartOpt,true);
    }).fail(function(jqXHR) {
        console.log("Ajax Fail: ", jqXHR.status, jqXHR.statusText);
    });

    /** 跑量 */
    const trendChart = echarts.init(document.getElementById("trendChart"), "shine");
    const trendChartOpt = {
        tooltip: {
            trigger: "axis",
            axisPointer: {
                type: "none"
            }
        },
        legend: {
            left: "center",
            bottom: 3,
            itemWidth: 15,
            itemHeight: 10,
            textStyle: {
                fontSize: 12,
                color: "#b0c2f9"
            },
            data: ["跑量"]
        },
        grid: {
            top: 40,
            bottom: 50,
            left: 60,
            right: 60
        },
        xAxis: {
            type: "category",
            axisLine: {
                lineStyle: {color: "#b0c2f9"}
            },
            axisTick: {show: false},
            axisLabel: {
                fontSize: 12,
                color: "#b0c2f9"
            }
        },
        yAxis: [{
            name: "",
            type: "value",
            splitNumber: 5,
            axisLine: {
                lineStyle: {color: "#b0c2f9"}
            },
            splitLine: {show: false},
            axisTick: {color: "#b0c2f9"},
            axisLabel: {
                fontSize: 12,
                color: "#b0c2f9",
                formatter: (value, index) => {
                    return value;
                }
            }
        }],
        visualMap: {
            show: false,
            seriesIndex: 2,
            dimension: 0,
            pieces: [{
                lte: 9,
                color: "rgba(176, 58, 91, 1)"
            }, {
                gt: 9,
                lte: 11,
                color: "rgba(176, 58, 91, 0.5)"
            }]
        },
        series: [{
            name: "距离",
            type: "pictorialBar",
            symbol: 'path://d="M150 50 L130 130 L170 130  Z"',
            barCategoryGap: "40%",
            itemStyle: {
                color: function(params) {
                    if(params.dataIndex >= 10) {
                        return "rgba(119, 96, 246, 0.5)";
                    }
                    return "rgba(119, 96, 246, 1)";
                }
            },
            markPoint: {
                itemStyle: {
                    color: "rgba(119, 96, 246, 1)"
                },
                data: [{
                    name: "最大值",
                    type: "max"
                }]
            },
            markLine: {
                lineStyle: {
                    color: "rgba(119, 96, 246, 1)"
                },
                label: {
                    position: "middle",
                    formatter: "平均跑量：{c}"
                },
                data: [{
                    name: "平均值",
                    type: "average"
                }]
            }
        }]
    };
    trendChart.setOption(trendChartOpt);
    $.ajax({
        url: "data/work-order.json",
        dataType: "json"
    }).done(function() {
        $("#trendChart").addClass("chart-done");
    }).done(function(data) {
        const xData = [];
        const yData1 = [];
        for(let i in data.month) {
            xData.push(data.month[i].CTIEM);
            yData1.push(data.month[i].CNT);
        }
        trendChart.setOption({
            xAxis: {
                data: xData,
            },
            series: [{
                name: "跑量",
                data: yData1
            }]
        });
    }).fail(function(jqXHR) {
        console.log("Ajax Fail: ", jqXHR.status, jqXHR.statusText);
    });

    /** 工单状态 */
    const csrcChart = echarts.init(document.getElementById("csrcChart"), "shine");
    const csrcChartOpt = {
        tooltip: {
            trigger: "item",
            formatter: "{b0}<br />跑量：{c0}<br />占比：{d0}%"
        },
        legend: {
            type: "scroll",
            orient: "vertical",
            padding: 0,
            top: 15,
            right: 0,
            itemGap: 5,
            itemWidth: 10,
            itemHeight: 10,
            textStyle: {
                fontSize: 10,
                color: "#b0c2f9"
            }
        },
        series: [{
            name: "健康状态",
            type: "pie",
            center: ["47%", "55%"],
            radius: ["30%", "85%"]
        }]
    };
    csrcChart.setOption(csrcChartOpt);
    $.ajax({
        url: "data/work-order.json",
        dataType: "json"
    }).done(function() {
        $("#csrcChart").addClass("chart-done");
    }).done(function(data) {
        const chartData = [];
        for(let i in data.status) {
            chartData.push({
                name: data.status[i].WSTATUS,
                value: data.status[i].CNT
            });
        }
        csrcChart.setOption({
            series: [{
                name: "健康状态",
                data: chartData
            }]
        });
    }).fail(function(jqXHR) {
        console.log("Ajax Fail: ", jqXHR.status, jqXHR.statusText);
    });

    /** 浏览器窗口大小变化时，重置报表大小 */
    $(window).resize(function() {
        rankChart.resize();
        mapChart.resize();
        trendChart.resize();
        csrcChart.resize();
    });
});

/** 数字变化特效 */
function rollNum(elId, startVal, endVal, decimalNum) {
    let n = decimalNum || 0;
    let countUp = new CountUp(elId, startVal, endVal, n, 2.5, {
        useEasing: true,
        useGrouping: true,
        separator: ',',
        decimal: '.'
    });
    if(!countUp.error) {
        countUp.start();
    }else {
        console.error(countUp.error);
    }
}
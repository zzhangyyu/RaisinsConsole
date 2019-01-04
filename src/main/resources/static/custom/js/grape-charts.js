function showMounthDatas(urlStr) {
    // 初始化echarts实例
    var myChart = echarts.init(document.getElementById("mounthDatasChart"));
    // 指定图表的配置项和数据
    var initOption = {
        title: {
            text: '就诊人数-月视图',
            x: 'center'
        },
        tooltip: {},
        legend: {
            data: ['就诊人数'],
            x: 'right'
        },
        xAxis: {
            data: []
        },
        yAxis: {},
        series: [{
            name: '就诊人数',
            type: 'bar',
            data: []
        }]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(initOption);
    myChart.showLoading();

    var xdatas = [];    //类别数组（实际用来盛放X轴坐标值）
    var ydatas = [];    //销量数组（实际用来盛放Y坐标值）
    $.ajax({
        type: "post",
        async: "true",
        url: urlStr,
        data: "12",
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            console.log(data);
            // var resultJson = JSON.parse(data);
            var mounthDatas = data.content;
            for (var i = 0; i < mounthDatas.length; i++) {
                xdatas.push(mounthDatas[i].visitingDate);
                ydatas.push(parseInt(mounthDatas[i].patientCnt));
            }
            myChart.hideLoading();    //隐藏加载动画
            myChart.setOption({        //加载数据图表
                xAxis: {
                    data: xdatas
                },
                series: [{
                    name: '就诊人数',
                    type: 'bar',
                    data: ydatas
                }]
            });

        },
        error: function (errorMsg) {
            alert("加载失败");
            myChart.hideLoading();    //隐藏加载动画
        }
    })

}
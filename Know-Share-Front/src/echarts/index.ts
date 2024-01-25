import initECharts from "./init";

const toolBox = {
  feature: {
    dataView: { show: true, readOnly: false },
    magicType: { show: true, type: ["line", "bar"] },
    restore: { show: true },
    saveAsImage: { show: true },
  },
};

const toolTip = {
  trigger: "axis",
  axisPointer: {
    type: "cross",
    crossStyle: {
      color: "#999",
    },
  },
};

export const drawKnowledgeContentOutput = (
  id: string,
  months: string[],
  knowData: number[],
  registerData: number[]
) => {
  const echarts = initECharts(document.getElementById(id) as HTMLElement);
  var option = {
    tooltip: toolTip,
    toolbox: toolBox,
    legend: {
      data: ["注册量", "发布量"],
    },
    xAxis: {
      type: "category",
      data: months,
      axisPointer: {
        type: "shadow",
      },
    },
    yAxis: [
      {
        type: "value",
        name: "注册量",
        min: 0,
        max: 50,
        axisLabel: {
          formatter: "{value} 人",
        },
      },
      {
        type: "value",
        name: "发布量",
        min: 0,
        max: 100,
        axisLabel: {
          formatter: "{value} 篇",
        },
      },
    ],
    series: [
      {
        name: "发布量",
        type: "bar",
        tooltip: {
          valueFormatter: function (value: any) {
            return value + " 篇";
          },
        },
        data: knowData,
      },
      {
        name: "注册量",
        type: "line",
        tooltio: {
          valueFormatter: function (value: any) {
            return value + " 人";
          },
        },
        data: registerData,
      },
    ],
  };
  echarts.setOption(option);
};

export const drawHotSearch = (id: string) => {
  const echarts = initECharts(document.getElementById(id) as HTMLElement);
  const option = {
    legend: {
      top: "bottom",
    },
    tooltip: toolTip,
    toolbox: toolBox,
    series: [
      {
        name: "Hot Search",
        type: "pie",
        raddius: [50, 250],
        center: ["50%", "50%"],
        roseType: "area",
        itemStyle: {
          borderRadois: 8,
        },
        data: [
          {
            value: 40,
            name: "遥遥领先",
          },
          {
            value: 20,
            name: "是什么在阻止我访问谷歌？",
          },
          {
            value: 10,
            name: "玩偶姐姐",
          },
        ],
      },
    ],
  };
  echarts.setOption(option);
};

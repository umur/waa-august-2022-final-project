import React, { useEffect, useState } from "react";
import ReactEcharts from "echarts-for-react";
import axios from "axios";
function Chart() {
  const [state, setter] = useState({ axis: [], data: [] });
  const [statePie1, setterPie1] = useState([]);
  const [statePie2, setterPie2] = useState([]);
  const [stateLine, setterLine] = useState({ axis: [], data: [] });

  useEffect(() => {
    axios.get("charts/tags").then((e) => {
      setter(e.data);
    });
    axios.get("charts/jobByLocation").then((e) => {
      setterPie1(e.data.data);
    });
    axios.get("charts/studentsPerState").then((e) => {
      setterPie2(e.data.data);
    });
    axios.get("charts/studentsPerCity").then((e) => {
      setterLine(e.data);
    });
  }, []);
  const option = {
    title: {
      text: "Tags",
      left: "center",
    },
    xAxis: {
      type: "category",
      data: state.axis, //["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
    },
    yAxis: {
      type: "value",
    },
    series: [
      {
        data: state.data, //[120, 200, 150, 80, 70, 110, 130],
        type: "bar",
      },
    ],
  };
  const optionLine = {
    title: {
      text: "Students Per City",
      left: "center",
    },
    xAxis: {
      type: "category",
      data: stateLine.axis, //["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
    },
    yAxis: {
      type: "value",
    },
    series: [
      {
        data: stateLine.data, //[150, 230, 224, 218, 135, 147, 260],
        type: "line",
      },
    ],
  };
  const optionPie1 = {
    title: {
      text: "Job by Location",
      left: "center",
    },
    tooltip: {
      trigger: "item",
    },
    legend: {
      orient: "vertical",
      left: "left",
    },
    series: [
      {
        name: "Access From",
        type: "pie",
        radius: "50%",
        data: statePie1,
        // data: [
        //   { value: 1048, name: "Search Engine" },
        //   { value: 735, name: "Direct" },
        //   { value: 580, name: "Email" },
        //   { value: 484, name: "Union Ads" },
        //   { value: 300, name: "Video Ads" },
        // ],
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: "rgba(0, 0, 0, 0.5)",
          },
        },
      },
    ],
  };
  const optionPie2 = {
    title: {
      text: "Students Per State",
      left: "center",
    },
    tooltip: {
      trigger: "item",
    },
    legend: {
      top: "5%",
      left: "center",
    },
    series: [
      {
        name: "Access From",
        type: "pie",
        radius: ["40%", "70%"],
        avoidLabelOverlap: false,
        itemStyle: {
          borderRadius: 10,
          borderColor: "#fff",
          borderWidth: 2,
        },
        label: {
          show: false,
          position: "center",
        },
        emphasis: {
          label: {
            show: true,
            fontSize: "40",
            fontWeight: "bold",
          },
        },
        labelLine: {
          show: false,
        },
        data: statePie2,
        // data: [
        //   { value: 1048, name: "Search Engine" },
        //   { value: 735, name: "Direct" },
        //   { value: 580, name: "Email" },
        //   { value: 484, name: "Union Ads" },
        //   { value: 300, name: "Video Ads" },
        // ],
      },
    ],
  };
  return (
    <div>
      <ReactEcharts option={option} />
      <ReactEcharts option={optionLine} />
      <ReactEcharts option={optionPie1} />
      <ReactEcharts option={optionPie2} />
    </div>
  );
}
export default Chart;

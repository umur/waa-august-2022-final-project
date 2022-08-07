import { Table, Tag } from "antd";
import React from "react";

const columns = [
  {
    title: "ID",
    dataIndex: "id",
    defaultSortOrder: "descend",
    sorter: (a, b) => a.id - b.id,
  },
  {
    title: "Company Name",
    dataIndex: "companyName",
    filters: [
      {
        text: "Joe",
        value: "Joe",
      },
      {
        text: "Jim",
        value: "Jim",
      },
      {
        text: "Submenu",
        value: "Submenu",
        children: [
          {
            text: "Green",
            value: "Green",
          },
          {
            text: "Black",
            value: "Black",
          },
        ],
      },
    ],
    onFilter: (value, record) => record.companyName.indexOf(value) === 0,
  },
  {
    title: "Description",
    dataIndex: "description",
  },
  {
    title: "Benefits",
    dataIndex: "benefits",
  },
  {
    title: "City",
    dataIndex: "city",
    filters: [
      {
        text: "London",
        value: "London",
      },
      {
        text: "New York",
        value: "New York",
      },
    ],
    onFilter: (value, record) => record.city.indexOf(value) === 0,
  },
  {
    title: "State",
    dataIndex: "state",
    filters: [
      {
        text: "Iowa",
        value: "Iowa",
      },
      {
        text: "California",
        value: "California",
      },
    ],
    onFilter: (value, record) => record.state.indexOf(value) === 0,
  },
  {
    title: "Tags",
    key: "tags",
    dataIndex: "tags",
    filters: [
      {
        text: "loser",
        value: "loser",
      },
      {
        text: "nice",
        value: "nice",
      },
    ],
    render: (_, { tags }) => (
      <>
        {tags.map((tag) => {
          let color = tag.length > 5 ? "geekblue" : "green";

          if (tag === "loser") {
            color = "volcano";
          }

          return (
            <Tag color={color} key={tag}>
              {tag.toUpperCase()}
            </Tag>
          );
        })}
      </>
    ),
    onFilter: (value, record) => {
      console.log(record);
      return record.tags.filter((tag) => tag === value);
    },
  },
];

const data = [
  {
    key: "1",
    id: "1",
    companyName: "John Brown",
    description: "jfnajbnsdfajbfjandf",
    benefits: "wjvwnrjvnjnbjwr",
    city: "Fairfield",
    state: "Iowa",
    tags: ["science", "developer"],
  },
  {
    key: "2",
    id: "2",
    companyName: "Jim Green",
    description: "jfnajbnsdfajbfjandf",
    benefits: "wjvwnrjvnjnbjwr",
    city: "Fairfield",
    state: "Iowa",
    tags: ["nice", "developer"],
  },
  {
    key: "3",
    id: "3",
    companyName: "Joe Black",
    description: "jfnajbnsdfajbfjandf",
    benefits: "wjvwnrjvnjnbjwr",
    city: "London",
    state: "Florida",
    tags: ["loser"],
  },
  {
    key: "4",
    id: "4",
    companyName: "Jim Red",
    description: "jfnajbnsdfajbfjandf",
    benefits: "wjvwnrjvnjnbjwr",
    city: "Harwich",
    state: "Massachusests",
    tags: ["cool", "teacher"],
  },
];

const onChange = (pagination, filters, sorter, extra) => {
  console.log("params", pagination, filters, sorter, extra);
};

const JobAdvertisment = () => (
  <Table columns={columns} dataSource={data} onChange={onChange} />
);

export default JobAdvertisment;

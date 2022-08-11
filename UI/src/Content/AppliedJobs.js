import { Table, Tag } from "antd";
import axios from "axios";
import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux";
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
        text: "Boston",
        value: "Boston",
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
        text: "Tag 1",
        value: "Tag 1",
      },
      {
        text: "Tag 2",
        value: "Tag 2",
      },
      {
        text: "Tag 3",
        value: "Tag 3",
      },
      {
        text: "Tag 4",
        value: "Tag 4",
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
    onFilter: (value, record) => record.tags.includes(value),
  },
];

const onChange = (pagination, filters, sorter, extra) => {
  console.log("params", pagination, filters, sorter, extra);
};

export default function AppliedJobs() {
  const [appliedJobState, setAppliedJobState] = useState([]);
  const userState = useSelector((state) => {
    return state.userReducer;
  });
  const fetchAppliedJobs = async () => {
    try {
      debugger
      const response = await axios.get("/students/get-applied-jobs/" + userState.user.id);
      setAppliedJobState(
        response.data.map((d) => {
          return {
            key: d.id,
            id: d.id,
            companyName: d.companyName,
            state: d.state,
            city: d.city,
            benefits: d.benefits,
            description: d.description,
            tags: d.tagList.map((t) => t.tagName),
          };
        })
      );
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    fetchAppliedJobs();
  }, [userState.appliedJobs]);


  return (
    <>
      <h1>Applied Jobs</h1>
      <Table
        columns={columns}
        dataSource={appliedJobState}
        onChange={onChange}
      />
    </>
  );
}

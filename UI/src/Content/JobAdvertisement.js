import { useKeycloak } from "@react-keycloak/web";
import { Button, Table, Tag } from "antd";
import axios from "axios";
import React, { useEffect, useState } from "react";

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
  {
    key: "button",
    dataIndex: "button",
  }
];

const onChange = (pagination, filters, sorter, extra) => {
  console.log("params", pagination, filters, sorter, extra);
};


export default function JobAdvertisment() {
  const [jobAdvertismentState, setJobAdvertismentState] = useState([]);

  const {keycloak} = useKeycloak();

  const onApplyClick = async (event, advId) => {
      axios.post(`students/${keycloak.subject}/job-advertisements/${advId}`);
  }

  const fetchJobAdvertisement = async () => {
    try {
      const response = await axios.get("/jobsAdvertisment");
      setJobAdvertismentState(
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
            button: <Button onClick={(event) => onApplyClick(event, d.id)}>Apply</Button>
          };
        })
      );
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    fetchJobAdvertisement();
  }, []);

  return (
    <>
      <Table
        columns={columns}
        dataSource={jobAdvertismentState}
        onChange={onChange}
      />
    </>
  );
}
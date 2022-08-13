import React, { useEffect, useState } from "react";
import axios from "axios";
import { Button, Table } from "antd";
import { Link } from "react-router-dom";

const columns = [
  {
    title: "ID",
    dataIndex: "id",
    defaultSortOrder: "descend",
    sorter: (a, b) => a.id - b.id,
  },
  {
    title: "Firstname",
    dataIndex: "firstname",
    filters: [
      {
        text: "Yousef",
        value: "yousef",
      },
      {
        text: "Ozod",
        value: "Ozod",
      },
    ],
    onFilter: (value, record) => record.firstname.indexOf(value) === 0,
  },
  {
    title: "Lastname",
    dataIndex: "lastname",
    filters: [
      {
        text: "Salem",
        value: "Salem",
      },
      {
        text: "Tagoev",
        value: "Tagoev",
      },
    ],
    onFilter: (value, record) => record.lastname.indexOf(value) === 0,
  },
  {
    title: "Gpa",
    dataIndex: "gpa",
  },
  {
    title: "Major",
    dataIndex: "major",
    filters: [
      {
        text: "Science",
        value: "science",
      },
      {
        text: "Economics",
        value: "economics",
      },
    ],
    onFilter: (value, record) => record.major.indexOf(value) === 0,
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
    key: "CV",
    dataIndex: "cv",
  },
  {
    key: "button",
    dataIndex: "button",
  },
];

const onChange = (pagination, filters, sorter, extra) => {
  console.log("params", pagination, filters, sorter, extra);
};

function ViewStudents() {
  const [state, stateSetter] = useState([]);

  const getStudents = async () => {
    var result = await axios.get("students");
    console.log(result);
    stateSetter(
      result.data.map((d) => {
        console.log(d);
        return {
          key: d.id,
          id: d.id,
          firstname: d.profile.firstName,
          lastname: d.profile.lastName,
          gpa: d.gpa,
          major: d.major.name,
          city: d.city,
          state: d.state,
          cv: d.cv,
          button: (
            <Button>
              <Link to={"/StudentView/" + d.id}>View Profile</Link>
            </Button>
          ),
        };
      })
    );
  };

  useEffect(() => {
    getStudents();
  }, []);

  return (
    <>
      <Table columns={columns} dataSource={state} onChange={onChange} />
    </>
  );
}

export default ViewStudents;

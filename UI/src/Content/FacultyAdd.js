import { Button, Form, InputNumber, Input, Rate, Row, Select } from "antd";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import React, { useEffect, useState } from "react";
import { useKeycloak } from "@react-keycloak/web";

const { Option } = Select;
const formItemLayout = {
  labelCol: {
    span: 6,
  },
  wrapperCol: {
    span: 14,
  },
};

const FacultyAdd = () => {
  const onFinish = (values) => {
    console.log("Received values of form: ", values);
  };

  let formData = {
    department: null,
  };
  const { keycloak } = useKeycloak();
  const [formDataState, setFormDataState] = useState(formData);
  const [departmentState, setDepartmentState] = useState([]);

  let navigate = useNavigate();

  const getDepartments = async () => {
    try {
      var result = await axios.get("/departments");
      setDepartmentState(result.data);
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    getDepartments();
  }, []);

  const departmentChange = (elm) => {
    var copy = { ...formDataState };
    copy.department = { id: elm };
    setFormDataState(copy);
  };

  const addFaculty = async () => {
    var userInfo = await keycloak.loadUserInfo();

    formData = { ...formDataState };
    formData["profile"] = {
      profileKClockId: userInfo.sub,
      firstName: userInfo.given_name,
      lastName: userInfo.family_name,
      profileType: "FACULTY",
    };

    try {
      var result = await axios.post("/faculties", formData);
      navigate(0);
    } catch (err) {
      console.log(err);
    }
  };
  return (
    <>
      <Form
        name="validate_other"
        {...formItemLayout}
        onFinish={onFinish}
        initialValues={{
          "input-number": 3,
          "checkbox-group": ["A", "B"],
          rate: 3.5,
        }}
      >
        <Form.Item name="department" label="Department">
          <Select
            value={formDataState.department}
            placeholder="Please select department"
            onChange={departmentChange}
          >
            {departmentState.map((item) => {
              return (
                <Option key={item.id} value={item.id}>
                  {item.name}
                </Option>
              );
            })}
            {/* <Option value="red">Red</Option>
                    <Option value="green">Green</Option>
                    <Option value="blue">Blue</Option> */}
          </Select>
        </Form.Item>

        <Form.Item
          wrapperCol={{
            span: 12,
            offset: 6,
          }}
        >
          <Button type="primary" htmlType="submit" onClick={addFaculty}>
            Submit
          </Button>
        </Form.Item>
      </Form>
    </>
  );
};

export default FacultyAdd;

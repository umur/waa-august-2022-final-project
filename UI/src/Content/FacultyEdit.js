import { Button, Form, InputNumber, Input, Rate, Row, Select } from "antd";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";
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

const FacultyEdit = () => {
  let { id } = useParams();
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
      console.log(result);
      setDepartmentState(result.data);
    } catch (err) {
      console.log(err);
    }
  };
  const [form] = Form.useForm();
  const getFaculty = async (id) => {
    var result = await axios.get("faculties/" + id);
    console.log(result);

    if (result.data.department != null) {
      result.data.department = result.data.department.id;
    }

    setFormDataState(result.data);

    form.setFieldsValue(result.data);
  };
  useEffect(() => {
    getFaculty(id);
    getDepartments();
  }, [id]);

  const departmentChange = (elm) => {
    var copy = { ...formDataState };
    copy.department = elm;
    setFormDataState(copy);
    console.log(elm);
  };

  const updateFaculty = async () => {
    var userInfo = await keycloak.loadUserInfo();

    formData = { ...formDataState };
    formData["profile"] = {
      profileKClockId: userInfo.sub,
      firstName: userInfo.given_name,
      lastName: userInfo.family_name,
      profileType: "FACULTY",
    };
    formData.department = { id: formData.department };

    try {
      console.log(formData);
      var result = await axios.put("/faculties/" + formDataState.id, formData);
      navigate("/");
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
        form={form}
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
          <Button type="primary" htmlType="submit" onClick={updateFaculty}>
            Submit
          </Button>
        </Form.Item>
      </Form>
    </>
  );
};

export default FacultyEdit;

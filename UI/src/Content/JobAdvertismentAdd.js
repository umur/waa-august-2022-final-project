import { InboxOutlined, UploadOutlined } from "@ant-design/icons";
import {
  Button,
  Checkbox,
  Col,
  Form,
  InputNumber,
  Input,
  Radio,
  Rate,
  Row,
  Select,
  Slider,
  Switch,
  Upload,
} from "antd";
import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const { Option } = Select;
const formItemLayout = {
  labelCol: {
    span: 6,
  },
  wrapperCol: {
    span: 14,
  },
};

const normFile = (e) => {
  console.log("Upload event:", e);

  if (Array.isArray(e)) {
    return e;
  }

  return e?.fileList;
};

export default function App() {
  let navigate = useNavigate();

  let formData = {
    tagList: [],
    fileList: [],
  };

  const tagSelect = (val) => {
    formData.tagList.push(val);
  };

  const tagDeselect = (val) => {
    let index = formData.tagList.findIndex(val);
    if (index > -1) {
      formData.tagList.splice(index, 1);
    }
  };

  const inputChange = (event) => {
    console.log(event);
    formData[event.target.name] = event.target.value;
    console.log(formData);
  };

  const addJob = async () => {
    formData.tagList = formData.tagList.map((d) => {
      return { id: d };
    });
    console.log(formData);
    try {
      var result = await axios.post("/jobsAdvertisment", formData);
      navigate("/");
    } catch (err) {
      console.log(err);
    }
  };
  const [tagState, setTagState] = useState([]);

  const onFinish = (values) => {
    console.log("Received values of form: ", values);
  };
  const getTags = async () => {
    var result = await axios.get("/tags");
    console.log(result);
    setTagState(result.data);
  };

  const onChangeFileUpload = (file) => {
    console.log("In Data hook");
    console.log(file);
  };

  const addFileReponse = (res) => {
    formData.fileList.push(res.data);
  };

  const fileRemoved = (file) => {
    var index = formData.fileList.findIndex((elm) => elm.uid == file.uid);
    if (index > -1) {
      formData.fileList.splice(index, 1);
    }
  };

  const uploadImage = async (options) => {
    const { onSuccess, onError, file, onProgress } = options;

    const fmData = new FormData();
    const config = {
      headers: { "content-type": "multipart/form-data" },
      // onUploadProgress: event => {
      //     const percent = Math.floor((event.loaded / event.total) * 100);
      //     setProgress(percent);
      //     if (percent === 100) {
      //         setTimeout(() => setProgress(0), 1000);
      //     }
      //     onProgress({ percent: (event.loaded / event.total) * 100 });
      // }
    };
    fmData.append("file", file);
    try {
      const res = await axios.post("/files/uploadFile", fmData, config);

      onSuccess("Ok");

      res.data["uid"] = file.uid;
      addFileReponse(res);
      console.log("server res: ", res);
    } catch (err) {
      console.log("Eroor: ", err);
      const error = new Error("Some error");
      onError({ err });
    }
  };

  useEffect(() => {
    getTags();
  }, []);
  return (
    <>
      <h1>Add Job Advertisment</h1>
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
        <Form.Item
          label="companyName"
          name="companyName"
          rules={[
            {
              required: true,
              message: "Please enter the companyName!",
            },
          ]}
        >
          <Input name="companyName" onChange={inputChange} />
        </Form.Item>

        <Form.Item
          label="description"
          name="description"
          rules={[
            {
              required: true,
              message: "Please enter the description!",
            },
          ]}
        >
          <Input name="description" onChange={inputChange} />
        </Form.Item>

        <Form.Item
          label="benefits"
          name="benefits"
          rules={[
            {
              required: true,
              message: "Please enter the benefits!",
            },
          ]}
        >
          <Input name="benefits" onChange={inputChange} />
        </Form.Item>

        <Form.Item
          label="city"
          name="city"
          rules={[
            {
              required: true,
              message: "Please enter the city!",
            },
          ]}
        >
          <Input name="city" onChange={inputChange} />
        </Form.Item>

        <Form.Item
          label="state"
          name="state"
          rules={[
            {
              required: true,
              message: "Please enter the state!",
            },
          ]}
        >
          <Input name="state" onChange={inputChange} />
        </Form.Item>

        <Form.Item
          name="select-tag"
          label="tags"
          rules={[
            {
              required: true,
              message: "Please select tags!",
              type: "array",
            },
          ]}
        >
          <Select
            mode="multiple"
            placeholder="Please select tags"
            onSelect={tagSelect}
            onDeselect={tagDeselect}
          >
            {tagState.map((item) => {
              return (
                <Option key={item.id} value={item.id}>
                  {item.tagName}
                </Option>
              );
            })}
            {/* <Option value="red">Red</Option>
                    <Option value="green">Green</Option>
                    <Option value="blue">Blue</Option> */}
          </Select>
        </Form.Item>

        <Form.Item
          name="upload"
          label="Upload"
          valuePropName="fileList"
          getValueFromEvent={normFile}
          extra="please select the files you want to upload"
        >
          <Upload
            name="logo"
            action="http://localhost:8081/api/v1/files/uploadFile"
            customRequest={uploadImage}
            onChange={onChangeFileUpload}
            onRemove={fileRemoved}
            listType="picture"
          >
            <Button icon={<UploadOutlined />}>Click to upload</Button>
          </Upload>
        </Form.Item>

        <Form.Item
          wrapperCol={{
            span: 12,
            offset: 6,
          }}
        >
          <Button type="primary" htmlType="submit" onClick={addJob}>
            Submit
          </Button>
        </Form.Item>
      </Form>
    </>
  );
}

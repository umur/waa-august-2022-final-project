import React, { useState, useEffect } from "react";
import { MinusCircleOutlined, PlusOutlined, UploadOutlined } from '@ant-design/icons';
import {
    Button,
    Form,
    InputNumber,
    Input,
    DatePicker,
    Rate,
    Row,
    Upload,
    Space,
    Select
} from 'antd';
import axios from 'axios';
import { useNavigate } from "react-router-dom";
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

const normFile = (e) => {
    console.log('Upload event:', e);

    if (Array.isArray(e)) {
        return e;
    }

    return e?.fileList;
};

const StudentAdd = () => {

    const onFinish = (values) => {
        console.log('Received values of form: ', values);
    };

    let formData = {
        major: null,
        fileList: []
    }

    const [form] = Form.useForm();

    const departmentChange = (elm) => {
        formData.major = { id: elm };
    }

    const [departmentState, setDepartmentState] = useState([]);
    const getDepartments = async () => {
        try {

            var result = await axios.get("/departments");
            console.log(result);
            setDepartmentState(result.data);
        }
        catch (err) {
            console.log(err);
        }

    }

    useEffect(() => {
        getDepartments();
    }, [])

    const inputChange = (event) => {
        console.log(event);
        formData[event.target.name] = event.target.value;
        console.log(formData);
    }
    const { keycloak } = useKeycloak();
    let navigate = useNavigate();

    const buildStudentRequestBody = async (data) => {
        let body = {};

        body['major'] = { id: data.major }
        body['gpa'] = parseInt(data.gpa)
        body['comments'] = data.comments
        if (data.jobHistoryList != null) {
            body['jobHistoryList'] = data.jobHistoryList.map(j => {
                j.endDate = j.endDate.toISOString();
                j.startDate = j.startDate.toISOString();
                return j;
            })

        }

        var userInfo = await keycloak.loadUserInfo()

        body['profile'] = {
            profileKClockId: userInfo.sub,
            firstName: userInfo.given_name,
            lastName: userInfo.family_name,
            profileType: 'STUDENT'
        };
        return body;
    }
    const onSubmit = async (data) => {
        console.log(data);

        var body = await buildStudentRequestBody(data);
        console.log(body)
        if (formData.fileList.length > 0) {
            body['cv'] = formData.fileList[0];
        }
        try {
            var result = await axios.post('/students', body);
            navigate(0);
        } catch (err) {
            console.log(err);
        }
    };

    const addFileReponse = (res) => {
        formData.fileList.push(res.data)
    }

    const uploadImage = async options => {
        const { onSuccess, onError, file, onProgress } = options;

        const fmData = new FormData();
        const config = {
            headers: { "content-type": "multipart/form-data" },
        };
        fmData.append("file", file);
        try {
            const res = await axios.post(
                "/files/uploadFile",
                fmData,
                config
            );

            onSuccess("Ok");

            res.data['uid'] = file.uid;
            addFileReponse(res);
            console.log("server res: ", res);
        } catch (err) {
            console.log("Eroor: ", err);
            const error = new Error("Some error");
            onError({ err });
        }
    };
    const fileRemoved = (file) => {


        var index = formData.fileList.findIndex(elm => elm.uid == file.uid);
        if (index > -1) {
            formData.fileList.splice(index, 1);
        }

    }


    return (<>
        <Form
            name="validate_other"
            {...formItemLayout}
            onFinish={(e) => onSubmit(e)}

            form={form}
            initialValues={{
                'input-number': 3,
                'checkbox-group': ['A', 'B'],
                rate: 3.5,
            }}
        >

            <Form.Item
                label="gpa"
                name="gpa"
                rules={[
                    {
                        required: true,
                        message: 'Please enter the gpa!',
                    },
                ]}
            >
                <Input type='number' name='gpa' onChange={inputChange} />
            </Form.Item>




            <Form.Item
                name="major"
                label="major"
            // rules={[
            //     {
            //         required: false,
            //          message: 'Please select major!',
            //          type: 'array',
            //     },
            // ]}
            >
                <Select onChange={departmentChange} placeholder="Please select major" >
                    {
                        departmentState.map(item => {
                            return <Option key={item.id} value={item.id}>
                                {item.name}
                            </Option>
                        })}

                </Select>
            </Form.Item>



            <Form.List name="jobHistoryList">
                {(fields, { add, remove }) => (
                    <>
                        {fields.map(({ key, name, ...restField }) => (
                            <Space

                                key={key}
                                style={{
                                    display: 'flex',
                                    marginBottom: 8,
                                }}
                                align="baseline"
                            >
                                <Form.Item
                                    {...restField}
                                    name={[name, 'companyName']}
                                    rules={[
                                        {
                                            required: true,
                                            message: 'Missing companyName',
                                        },
                                    ]}
                                >
                                    <Input placeholder="companyName" />
                                </Form.Item>

                                <Form.Item
                                    {...restField}
                                    name={[name, 'startDate']}
                                    rules={[
                                        {
                                            required: true,
                                            message: 'Missing startDate',
                                        },
                                    ]}
                                >
                                    <DatePicker placeholder="Start Date" />
                                </Form.Item>

                                <Form.Item
                                    {...restField}
                                    name={[name, 'endDate']}
                                    rules={[
                                        {
                                            required: true,
                                            message: 'Missing End Date',
                                        },
                                    ]}
                                >
                                    <DatePicker placeholder="End Date" />
                                </Form.Item>

                                <Form.Item
                                    {...restField}
                                    name={[name, 'reasonToLeave']}
                                    rules={[
                                        {
                                            required: true,
                                            message: 'Missing reasonToLeave',
                                        },
                                    ]}
                                >
                                    <Input placeholder="Reason To Leave" />
                                </Form.Item>

                                <Form.Item
                                    {...restField}
                                    name={[name, 'comments']}
                                    rules={[
                                        {
                                            required: true,
                                            message: 'Missing comments',
                                        },
                                    ]}
                                >
                                    <Input placeholder="comments" />
                                </Form.Item>
                                <MinusCircleOutlined onClick={() => remove(name)} />
                            </Space>
                        ))}
                        <Form.Item>
                            <Button type="dashed" onClick={() => add()} block icon={<PlusOutlined />}>
                                Add Job History
                            </Button>
                        </Form.Item>
                    </>
                )}
            </Form.List>

            <Form.Item
                name="cv"
                label="Upload"
                valuePropName="fileList"

                getValueFromEvent={normFile}
                extra="please select the files you want to upload"
            >
                <Upload multiple='false' name="logo"
                    customRequest={uploadImage}
                    maxCount="1"
                    onRemove={fileRemoved}
                    listType="picture" >
                    <Button icon={<UploadOutlined />}>Click to upload</Button>
                </Upload>
            </Form.Item>

            <Form.Item
                wrapperCol={{
                    span: 12,
                    offset: 6,
                }}
            >
                <Button type="primary" htmlType="submit" >
                    Submit
                </Button>
            </Form.Item>
        </Form>
    </>);
}


export default StudentAdd;
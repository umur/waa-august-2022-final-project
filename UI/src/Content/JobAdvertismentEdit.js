import { UploadOutlined } from '@ant-design/icons';
import {
    Button,
    Form,
    Input,
    Select,
    Upload
} from 'antd';
import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from "react-router-dom";

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



export default function App() {

    let { id } = useParams();
    let navigate = useNavigate();


    const [form] = Form.useForm();

    const [tagState, setTagState] = useState([]);
    const [jobState, setJobState] = useState({});

    const tagSelect = (val) => {
        var copy={...jobState};
        copy.tagList.push(val);
        setJobState(copy);
    }

    const tagDeselect = (val) => {
        var copy={...jobState};
        let index = copy.tagList.findIndex(val);
        if (index > -1) {
            copy.tagList.splice(index, 1);
            setJobState(copy);
        }
    }

    const inputChange = (event) => {
        console.log(event);
        var copy={...jobState};
        copy[event.target.name] = event.target.value;
        setJobState(copy);
        console.log(copy);
    }

    const addJob = async () => {
        var copy={...jobState};
        copy.tagList = copy.tagList.map(d => { return { id: d } });
        console.log(copy);
        try {
            var result = await axios.put('/jobsAdvertisment/'+id, copy);
            console.log(result);
            navigate('/');
        } catch (err) {
            console.log(err);
        }


    }



    const onFinish = (values) => {
        console.log('Received values of form: ', values);
    };

    const getTags = async () => {
        var result = await axios.get("/tags");
        setTagState(result.data);
    }

    const onChangeFileUpload = (file) => {
        console.log('In Data hook')
        console.log(file);
    }

    const addFileReponse = (res) => {
        var copy={...jobState};
        copy.fileList.push(res.data)
        setJobState(copy);
    }

    const fileRemoved = (file) => {

        var copy={...jobState};
        var index = copy.fileList.findIndex(elm => elm.uid == file.uid || elm.id == file.id);
        if (index > -1) {
            copy.fileList.splice(index, 1);
            setJobState(copy);
        }

    }

    const uploadImage = async options => {
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

    const getJobAdvertisment = async (id) => {
        var result = await axios.get('jobsAdvertisment/' + id);
        console.log(result);
        result.data.tagList=result.data.tagList.map(i => i.id);

  

        setJobState(result.data);
        
       
        form.setFieldsValue(result.data);
    }

    useEffect(() => {
        getJobAdvertisment(id);
        getTags();
    }, [id])
    return (
        <>
            <h1>Edit Job Advertisment</h1>
            <Form
                name="validate_other"
                {...formItemLayout}
                onFinish={onFinish}
                form={form}
                initialValues={{
                    'input-number': 3,
                    'checkbox-group': ['A', 'B'],
                    rate: 3.5,
                }}
            >

                <Form.Item
                    label="companyName"
                    name="companyName"
                    rules={[
                        {
                            required: true,
                            message: 'Please enter the companyName!',
                        },
                    ]}
                >
                    <Input name='companyName' value={jobState.companyName} onChange={inputChange} />
                </Form.Item>


                <Form.Item
                    label="description"
                    name="description"
                    rules={[
                        {
                            required: true,
                            message: 'Please enter the description!',
                        },
                    ]}
                >
                    <Input name='description' onChange={inputChange} />
                </Form.Item>


                <Form.Item
                    label="benefits"
                    name="benefits"
                    rules={[
                        {
                            required: true,
                            message: 'Please enter the benefits!',
                        },
                    ]}
                >
                    <Input name='benefits' onChange={inputChange} />
                </Form.Item>

                <Form.Item
                    label="city"
                    name="city"
                    rules={[
                        {
                            required: true,
                            message: 'Please enter the city!',
                        },
                    ]}
                >
                    <Input name='city' onChange={inputChange} />
                </Form.Item>

                <Form.Item
                    label="state"
                    name="state"
                    rules={[
                        {
                            required: true,
                            message: 'Please enter the state!',
                        },
                    ]}
                >
                    <Input name='state' onChange={inputChange} />
                </Form.Item>



                <Form.Item
                    name="tagList"
                    label="tags"
                    rules={[
                        {
                            required: true,
                            message: 'Please select tags!',
                            type: 'array',
                        },
                    ]}
                >
                    <Select mode="multiple" placeholder="Please select tags" onSelect={tagSelect} onDeselect={tagDeselect}>
                        {
                            tagState.map(item => {
                                return <Option key={item.id} value={item.id}>
                                    {item.tagName}
                                </Option>
                            })}
                        {/* <Option value="red">Red</Option>
                    <Option value="green">Green</Option>
                    <Option value="blue">Blue</Option> */}
                    </Select>
                </Form.Item>




                <Form.Item
                    name="fileList"
                    label="Upload"
                    valuePropName="fileList"
                    getValueFromEvent={normFile}
                    extra="please select the files you want to upload"
                >
                    <Upload name="logo"
                        action="http://localhost:8081/api/v1/files/uploadFile"
                        customRequest={uploadImage}
                        onChange={onChangeFileUpload}
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
                    <Button type="primary" htmlType="submit" onClick={addJob}>
                        Submit
                    </Button>
                </Form.Item>
            </Form>
        </>
    );
};


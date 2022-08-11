import React, { useState, useEffect } from "react";
import { MinusCircleOutlined, PlusOutlined, UploadOutlined } from '@ant-design/icons';
import moment from 'moment'
import {
    Button,
    Form,
    Comment,
    InputNumber,
    Upload,
    Input,
    DatePicker,
    Tooltip,
    Rate,
    Row,
    Space,
    Select
} from 'antd';
import axios from 'axios';
import { useNavigate, useParams } from "react-router-dom";
import { useKeycloak } from "@react-keycloak/web";
import { useSelector } from "react-redux";

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

const StudentView = () => {

    const onFinish = (values) => {
        console.log('Received values of form: ', values);
    };

    const [commentsState, setCommentsState] = useState([]);


    let { id } = useParams();

    const user = useSelector((state) => {
        return state.userReducer.user;
    });

    const [form] = Form.useForm();

    const isFormDisabled = true;

    const departmentChange = (elm) => {
        var copy = { ...studentState };
        copy.major = { id: elm };
        setStudentState(copy);
    }

    const [departmentState, setDepartmentState] = useState([]);
    const [studentState, setStudentState] = useState({});

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

    const formatJobHistoryDate = (data) => {

        data.jobHistoryList = data.jobHistoryList.map(j => {
            j.startDate = moment(j.startDate)
            j.endDate = moment(j.endDate)
            return j;
        })
        return data;
    }

    const getStudent = async (id) => {
        var result = await axios.get('students/' + id);

        var data = result.data;


        data = formatJobHistoryDate(data);
        if (data.major != null) {
            data.major = data.major.id;
        }
        debugger


        if (data.cv != null) {
            data.fileList = [data.cv]
        }


        setStudentState(data);
        form.setFieldsValue(data);
    }

    const getComments = async () => {
        debugger
        try {


            var result = await axios.get('comments/by-student-id/' + id);

            setCommentsState(result.data);
        } catch (error) {
            console.log(error);
        }

    }
    const isFaculty = (user) => {
        return user.profileType === "FACULTY";
    };

    useEffect(() => {
        getStudent(id);
        getDepartments();
        if (isFaculty(user)) {
            getComments();
        }

    }, [id])

    const inputChange = (event) => {
        console.log(event);
        var copy = { ...studentState };
        copy[event.target.name] = event.target.value;
        console.log(copy);
        setStudentState(copy);
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

    const addFileReponse = (res) => {
        var copy = { ...studentState };
        copy['cv'] = res.data;
        setStudentState(copy);
    }

    const fileRemoved = (file) => {
        var copy = { ...studentState };

        if (copy.cv != null) {
            copy.cv = null;
            setStudentState(copy);
        }

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
    const onSubmit = async (data) => {
        console.log(data);

        var body = await buildStudentRequestBody(data);
        body.id = id;

        body['cv'] = studentState.cv;

        console.log(body)
        try {
            var result = await axios.put('/students/' + id, body);
            navigate('/');
        } catch (err) {
            console.log(err);
        }
    };

    const { TextArea } = Input;



    let commentValue = '';

    const onCommentChange = async (data) => {
        console.log(data);
        commentValue = data.target.value;
    }

    const onCommentSubmit = async (data) => {
        console.log(data);
        debugger
        let body = {
            commentDetails: commentValue,
            student: { id: id },
            faculty: { id: user.id }
        }

        var result = await axios.post('/comments', body)
        getComments();
    }


    const filePreview = (file) => {
        console.log(file);
        axios({
            url: 'files/downloadFile/' + file.fileCode, //your url
            method: 'GET',
            responseType: 'blob', // important
        }).then((response) => {
            const url = window.URL.createObjectURL(new Blob([response.data]));
            const link = document.createElement('a');
            link.href = url;
            link.setAttribute('download', file.name); //or any other extension
            document.body.appendChild(link);
            link.click();
        });
    }

    const onChangeFileUpload = (file) => {
        console.log('In Data hook')
        console.log(file);
    }


    return (<>
        <Form
            name="validate_other"
            {...formItemLayout}
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
                <Input disabled={isFormDisabled} type='number' name='gpa' onChange={inputChange} />
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
                <Select disabled={isFormDisabled} onChange={departmentChange} placeholder="Please select major" >
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
                                    <Input disabled={isFormDisabled} placeholder="companyName" />
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
                                    <DatePicker disabled={isFormDisabled} placeholder="Start Date" />
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
                                    <DatePicker disabled={isFormDisabled} placeholder="End Date" />
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
                                    <Input disabled={isFormDisabled} placeholder="Reason To Leave" />
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
                                    <Input disabled={isFormDisabled} placeholder="comments" />
                                </Form.Item>
                                <MinusCircleOutlined onClick={() => remove(name)} />
                            </Space>
                        ))}
                        <Form.Item>
                            <Button disabled={isFormDisabled} type="dashed" onClick={() => add()} block icon={<PlusOutlined />}>
                                Add Job History
                            </Button>
                        </Form.Item>
                    </>
                )}
            </Form.List>

            <Form.Item

                name="fileList"
                label="Upload"
                valuePropName="fileList"
                getValueFromEvent={normFile}
                extra="please select the files you want to upload"
            >
                <Upload name="logo"
                    disabled={isFormDisabled}
                    multiple={false}
                    maxCount="1"
                    onPreview={filePreview}
                    customRequest={uploadImage}
                    onChange={onChangeFileUpload}
                    onRemove={fileRemoved}
                    listType="picture" >
                    <Button icon={<UploadOutlined />}>Click to upload</Button>
                </Upload>
            </Form.Item>

        </Form>

        <Form>

            <h1>Comments</h1>

            {commentsState.length > 0 && (
                commentsState.map(c => {
                    return (<>
                        <Form.Item
                            label={'Faculty ' + c.faculty.profile.firstName}

                        >
                            <Input disabled={true} value={c.commentDetails} />
                        </Form.Item>
                    </>)
                })

            )

            }
            {
                isFaculty(user) > 0 && (
                    <>
                        <Form.Item>
                            <TextArea rows={4} onChange={onCommentChange} />
                        </Form.Item>
                        <Form.Item>
                            <Button htmlType="submit" onClick={onCommentSubmit} type="primary">
                                Add Comment
                            </Button>
                        </Form.Item>
                    </>
                )
            }
        </Form>
    </>);
}


export default StudentView;
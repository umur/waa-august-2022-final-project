import "./App.css";
import { Button } from "antd";
import { Layout } from "antd";
import React from "react";
import MyHeader from "./Header/Header";
import MyContent from "./Content/Content";
import { Route, Routes } from "react-router";
import JobAdvertismentAdd from "./Content/JobAdvertismentAdd";
import JobAdvertismentEdit from "./Content/JobAdvertismentEdit";
import JobAdvertismentView from "./Content/JobAdvertismentView";
import PrivateRoute from "./helpers/PrivateRoute";
import axios from "axios";
import { useKeycloak } from "@react-keycloak/web";
import ViewStudents from "./Content/ViewStudents";
import ProfileAdd from "./Content/ProfileAdd";
import FacultyEdit from './Content/FacultyEdit'

const { Header, Footer, Sider, Content } = Layout;

const App = () => {
  const { keycloak } = useKeycloak();
  axios.defaults.baseURL = "http://localhost:8081/api/v1";
  console.log(keycloak.token);
  axios.defaults.headers.common["Authorization"] = "Bearer " + keycloak.token;

  return (
    <>
      <PrivateRoute>
        <Layout>
          <Header>
            <MyHeader></MyHeader>
          </Header>
          <Content>
            <Routes>
              <Route path="/JobAdd" element={<JobAdvertismentAdd />} />
              <Route path="/JobEdit/:id" element={<JobAdvertismentEdit />} />
              <Route path="/JobView/:id" element={<JobAdvertismentView />} />
              
              <Route path="/ViewStudents" element={<ViewStudents />} />

              <Route path="/ProfileAdd" element={<ProfileAdd />} />
              
              <Route path="/FacultyEdit/:id" element={<FacultyEdit />} />

              <Route path="/" element={<MyContent />} />
            </Routes>
          </Content>
          <Footer>Footer</Footer>
        </Layout>
      </PrivateRoute>
    </>
  );
};
export default App;

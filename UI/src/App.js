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
import SecuredPage from "./pages/Securedpage";
import PrivateRoute from "./helpers/PrivateRoute";
import Dashboard from "./Content/Dashboard";

import axios from 'axios';
import { useKeycloak } from "@react-keycloak/web";

const { Header, Footer, Sider, Content } = Layout;

const App = () => {
  const { keycloak } = useKeycloak();
  axios.defaults.baseURL = 'http://localhost:8081/api/v1';
  console.log(keycloak.token)
  axios.defaults.headers.common['Authorization'] ='Bearer ' + keycloak.token;


  return (

    <>

      <PrivateRoute>
      
      <Layout>
        <Header>
          <MyHeader></MyHeader>
        </Header>
        <Content>
          <Routes>

            <Route path='/JobAdd' element={<JobAdvertismentAdd />} />
            <Route path='/JobEdit/:id' element={<JobAdvertismentEdit />} />
            <Route path='/JobView/:id' element={<JobAdvertismentView />} />
            <Route path="/Dashboard" element={<Dashboard />} />
            <Route path='/' element={<MyContent />} />
            {/* <Route
              path="/secured"
              element={
                <PrivateRoute>
                  <SecuredPage />
                </PrivateRoute>
              }
            /> */}

          </Routes>
        </Content>
        <Footer>Footer</Footer>
      
      </Layout>
      </PrivateRoute>
    </>

  )
};
export default App;

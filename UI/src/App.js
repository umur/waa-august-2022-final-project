
import './App.css';
import { Button } from 'antd';
import { Layout } from 'antd';
import React from 'react';
import MyHeader from './Header/Header';
import MyContent from './Content/Content';
import { Route, Routes } from 'react-router';
import JobAdvertismentAdd from './Content/JobAdvertismentAdd'


const { Header, Footer, Sider, Content } = Layout;


const App = () => (
  <>
    <Layout>
      <Header>
        <MyHeader></MyHeader>
      </Header>
      <Content>
        <Routes>
          <Route path='/JobAdd' element={<JobAdvertismentAdd />} />

          <Route path='/' element={<MyContent />} />
        </Routes>
      </Content>
      <Footer>Footer</Footer>
    </Layout>





  </>
);
export default App;

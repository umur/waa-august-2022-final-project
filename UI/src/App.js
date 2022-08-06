
import './App.css';
import { Button } from 'antd';
import { Layout } from 'antd';
import React from 'react';
import MyHeader from './Header/Header';
import MyContent from './Content/Content';
import { Route, Routes } from 'react-router';
import JobAdvertismentAdd from './Content/JobAdvertismentAdd'
import JobAdvertismentEdit from './Content/JobAdvertismentEdit'
import JobAdvertismentView from './Content/JobAdvertismentView'


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
          <Route path='/JobEdit/:id' element={<JobAdvertismentEdit />} />
          <Route path='/JobView/:id' element={<JobAdvertismentView />} />

          <Route path='/' element={<MyContent />} />
        </Routes>
      </Content>
      <Footer>Footer</Footer>
    </Layout>





  </>
);
export default App;

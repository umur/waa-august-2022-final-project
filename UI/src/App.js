
import './App.css';
import { Button } from 'antd';
import { Layout } from 'antd';
import React from 'react';
import MyHeader from './Header/Header';
import MyContent from './Content/Content';
const { Header, Footer, Sider, Content } = Layout;



const App = () => (
  <>
    <Layout>
      <Header>
         <MyHeader></MyHeader> 
      </Header>
      <Content>
        <MyContent></MyContent>
      </Content>
      <Footer>Footer</Footer>
    </Layout>
  </>
);
export default App;

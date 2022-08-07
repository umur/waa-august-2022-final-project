import {  Tabs } from 'antd';
import React, { useState } from 'react';
import FacultyAdd from './FacultyAdd';
import StudentAdd from './StudentAdd';
const { TabPane } = Tabs;

const ProfileAdd = () => {


  return (
    <div>
   
     
      <Tabs defaultActiveKey="1" type="card" >
        <TabPane tab="Register As Faculty" key="1">
        Register As Faculty
        <FacultyAdd />
        </TabPane>
        <TabPane tab="Register As Student" key="2">
            Register As Student
            <StudentAdd/>
        </TabPane>
        
      </Tabs>
    </div>
  );
};

export default ProfileAdd;
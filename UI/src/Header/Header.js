import { Button } from 'antd';
import React from 'react';
import { Link } from 'react-router-dom';
import logo from './../logo.svg';

export default function Header(){

    return (<>
    <div className='navBar'>

    
    
    <div className="site-button-ghost-wrapper">
    {/* <img src={logo} width='90px' height='40px' alt="logo" /> */}
     <Button ghost>Profile</Button>
     <Button ghost><Link to='/JobAdd'> Add Job Advertisment </Link></Button>
     <Button ghost>Student List</Button>
     </div>
     </div>
    </>);
}
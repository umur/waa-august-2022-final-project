import { Button } from 'antd';
import React from 'react';

import logo from './../logo.svg';

import { useSelector, useDispatch } from 'react-redux'
import { updateUser } from './../Redux/UserSlice'

export default function Content(){

  const user = useSelector((state) =>{console.log(state); return state.userReducer.user});
  console.log(user);
  const dispatch = useDispatch();


    return (<>
  <div className="App">
      <header className="">
        <div>
          User :
          {user.name}
          <input type='button' onClick={ () => dispatch(updateUser({name:'Updated '}))} value='Update User Name Value '/>
        </div>
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
    </>);
}
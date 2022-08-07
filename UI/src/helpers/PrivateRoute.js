import { useKeycloak } from "@react-keycloak/web";
import RedirectToLogin from "./../RedirectToLogin";
import React, { useEffect, useState } from "react";
import axios from "axios";
import { useSelector, useDispatch } from 'react-redux'
import { updateUser } from '../Redux/UserSlice'

const PrivateRoute = ({ children }) => {
  const { keycloak } = useKeycloak();
  const isLoggedIn = keycloak.authenticated;

  const user = useSelector((state) =>{console.log(state); return state.userReducer.user});
  const dispatch = useDispatch();

  const [state = { notLoaded: true }, setState] = useState(null);

  useEffect(() => {
    axios
      .get("http://localhost:8081/identity", {
        headers: {
          Authorization: "bearer " + keycloak.token,
        },
      })
      .then((e) => {
        debugger;
        console.log(e);
        setState(e.data);
      })
      .catch((e) => {
        debugger;
        console.log("Error: " + e);
      });
  }, [children]);

  if (isLoggedIn == null) {
    return null;
  }
  if (!isLoggedIn) {
    return <RedirectToLogin />;
  }

  if (!state) {
    return <div>complete your profile page</div>;
  }
  if (state.notLoaded) {
    return <div/>;
  }

  dispatch(updateUser(state))
  return children;
};

export default PrivateRoute;

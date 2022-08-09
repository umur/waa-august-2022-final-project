import { useKeycloak } from "@react-keycloak/web";
import RedirectToLogin from "./../RedirectToLogin";
import React, { useEffect, useState } from "react";
import axios from "axios";
import { useSelector, useDispatch } from 'react-redux'
import { updateUser } from '../Redux/UserSlice'
import ProfileAdd from "../Content/ProfileAdd";
import SocketContext from "../Socket/socket"

const PrivateRoute = ({ children }) => {
  const { keycloak } = useKeycloak();
  const isLoggedIn = keycloak.authenticated;

  const user = useSelector((state) =>{console.log(state); return state.userReducer.user});
  const dispatch = useDispatch();
  const socket = React.useContext(SocketContext);

  const [state = { notLoaded: true }, setState] = useState(null);

  useEffect(() => {
    axios
      .get("/identity")
      .then((e) => {
        console.log(e);
        setState(e.data);
      })
      .catch((e) => {
        console.log(+ e);
      });
  }, [children]);

  if (isLoggedIn == null) {
    return null;
  }
  if (!isLoggedIn) {
    return <RedirectToLogin />;
  }

  if (!state) {
    return <ProfileAdd />;
  }
  if (state.notLoaded) {
    return <div/>;
  }

  dispatch(updateUser(state))
  socket?.emit("newUser", keycloak.subject)
  return children;
};

export default PrivateRoute;

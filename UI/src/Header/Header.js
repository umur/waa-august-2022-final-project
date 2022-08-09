import { Button } from "antd";
import React from "react";
import { Link } from "react-router-dom";
import logo from "./../logo.svg";
import { useKeycloak } from "@react-keycloak/web";
import { useSelector } from "react-redux";
import SocketContext from "../Socket/socket";
export default function Header() {
  const socket = React.useContext(SocketContext);

  const { keycloak, initialized } = useKeycloak();
  const user = useSelector((state) => {
    return state.userReducer.user;
  });

  const isFaculty = (user) => {
    return user.profileType === "FACULTY";
  };

  const isStudent = (user) => {
    return user.profileType === "STUDENT";
  };
  const socketTest = () => {
    socket.emit("sendNotification", {
      senderName: user.id,
      receiverName: 1,
      type: 0,
    });
  };
  return (
    <>
      <div className="navBar">
        <div className="site-button-ghost-wrapper">

          {/* <img src={logo} width='90px' height='40px' alt="logo" /> */}
          <Button ghost>
            <Link to="/Dashboard">Dashboard</Link>
          </Button>
          {/* Edit Profile Based on the Type */}
          {
            <Button
              ghost
              type="button"
              className="text-blue-800"
              onClick={() => socketTest()}
            >
              socket test
            </Button>
          }
          {isFaculty(user) && (
            <Button ghost>
              <Link to={"/FacultyEdit/" + user.id}> Edit Profile</Link>
            </Button>
          )}
          {isStudent(user) && (
            <Button ghost>
              <Link to={"/StudentEdit/" + user.id}> Edit Profile</Link>
            </Button>
          )}

          {/* Only Student can Add Job */}
          {isStudent(user) && (
            <Button ghost>
              <Link to="/JobAdd"> Add Job Advertisment </Link>
            </Button>
          )}

          {/* Only  Faculty can view student list */}
          {isFaculty(user) && <Button ghost>Student List</Button>}

          {!keycloak.authenticated && (
            <Button
              ghost
              type="button"
              className="text-blue-800"
              onClick={() => keycloak.login()}
            >
              Login
            </Button>
          )}

          {!!keycloak.authenticated && (
            <Button
              ghost
              type="button"
              className="text-blue-800"
              onClick={() => keycloak.logout()}
            >
              Logout ({keycloak.tokenParsed.preferred_username})
            </Button>
          )}
        </div>
      </div>
    </>
  );
}

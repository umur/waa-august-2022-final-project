import { Button } from "antd";
import React from "react";
import { Link } from "react-router-dom";
import logo from "./../logo.svg";
import { useKeycloak } from "@react-keycloak/web";
import { useSelector } from 'react-redux'
export default function Header() {
  const { keycloak, initialized } = useKeycloak();
  const user = useSelector((state) => { return state.userReducer.user });

  const isFaculty = (user) => {
    return user.profileType == "FACULTY";
  }

  const isStudent = (user) => {
    return user.profileType == "STUDENT";
  }
  return (
    <>
      <div className="navBar">
        <div className="site-button-ghost-wrapper">

          {/* Edit Profile Based on the Type */}
          {
            isFaculty(user) && (
              <Button ghost>
                <Link to={"/FacultyEdit/" + user.id}> Edit Profile</Link>
              </Button>
            )

          }
          {
            isStudent(user) && (
              <Button ghost>
                <Link to={"/StudentEdit/" + user.id}> Edit Profile</Link>
              </Button>
            )
          }

          {/* Only Student can Add Job */}
          {
            isStudent(user) && (
              <Button ghost>
                <Link to="/JobAdd"> Add Job Advertisment </Link>
              </Button>
            )
          }

          {/* Only  Faculty can view student list */}
          {
            isFaculty(user) && (<Button ghost>Student List</Button>)
          }

          {!keycloak.authenticated && (
            <Button ghost
              type="button"
              className="text-blue-800"
              onClick={() => keycloak.login()}
            >
              Login
            </Button>
          )}

          {!!keycloak.authenticated && (
            <Button ghost
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

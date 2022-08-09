import { Button } from "antd";
import React from "react";
import { Link } from "react-router-dom";
import logo from "./../logo.svg";
import { useKeycloak } from "@react-keycloak/web";

export default function Header() {
  const { keycloak, initialized } = useKeycloak();

  return (
    <>
      <div className="navBar">
        <div className="site-button-ghost-wrapper">
          {/* <img src={logo} width='90px' height='40px' alt="logo" /> */}
          <Button ghost>
            <Link to="/Dashboard">Dashboard</Link>
          </Button>
          <Button ghost>
            <Link to="/AppliedJobs">AppliedJobs</Link>
          </Button>
          <Button ghost>Profile</Button>
          <Button ghost>
            <Link to="/JobAdd"> Add Job Advertisment </Link>
          </Button>
          <Button ghost>Student List</Button>
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

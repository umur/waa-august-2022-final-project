import React, { useEffect } from "react";
import axios from "axios";
import { useKeycloak } from "@react-keycloak/web";

const Secured = () => {
  const { keycloak } = useKeycloak();
  useEffect(() => {
    axios
      .get("http://localhost:8081/users",{
        headers: {
          'Authorization': 'bearer ' + keycloak.token
        }
      })
      .then((e) => console.log(e))
      .catch((e) => console.log("Error: " + e));
  }, []);
  return (
    <div>
      <h1 className="text-black text-4xl">Welcome to the Protected Page.</h1>
    </div>
  );
};

export default Secured;

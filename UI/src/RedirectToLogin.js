import React,{ useEffect } from "react"
import { useKeycloak } from "@react-keycloak/web";

export default function RedirectToLogin(){
    const { keycloak } = useKeycloak();

    useEffect(() => {
          keycloak.login();
    }, [])
    return (<>
    <div>

    </div>
    </>);
};
import React from "react";

import JobAdvertisment from "./JobAdvertisement";
import AppliedJobs from "./AppliedJobs";
import { useSelector } from "react-redux";
import { useKeycloak } from "@react-keycloak/web";

export default function Content() {
  const { keycloak, initialized } = useKeycloak();
  const user = useSelector((state) => {
    return state.userReducer.user;
  });

  const isStudent = (user) => {
    return user.profileType === "STUDENT";
  };

  return (
    <>
      <div>
        {isStudent(user) && (
          <div>
            <JobAdvertisment></JobAdvertisment>
            <br></br>
            <br></br>
            <AppliedJobs></AppliedJobs>
          </div>
        )}
      </div>
    </>
  );
}

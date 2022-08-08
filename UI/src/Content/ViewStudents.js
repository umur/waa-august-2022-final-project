import React, { useEffect, useState } from "react";
import axios from "axios";
import { Button } from "antd";

function ViewStudents() {
  const [state, stateSetter] = useState([]);

  const getStudents = async () => {
    var result = await axios.get("students");
    console.log(result);
    stateSetter(result.data);
  };

  useEffect(() => {
    getStudents();
  }, []);

  return (
    <div>
      <table>
        <tr>
          <th>name</th>
          <th>gpa</th>
          <th>actions</th>
        </tr>
        {state.map((item) => {
          return (
            <tr key={item.studentKClockId}>
              <td>
                {item.profile.firstname} {item.profile.lastname}
              </td>
              <td>{item.profile.gpa}</td>
              <td><Button>view profile</Button></td>
            </tr>
          );
        })}
      </table>
    </div>
  );
}

export default ViewStudents;

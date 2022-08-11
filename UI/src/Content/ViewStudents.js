import React, { useEffect, useState } from "react";
import axios from "axios";
import { Button } from "antd";
import { Link } from "react-router-dom";

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
      <thead>
        <tr>
          <th>name</th>
          <th>gpa</th>
          <th>actions</th>
        </tr>
        </thead>
        <tbody>
        {state.map((item) => {
          return (
            <tr key={item.id}>
              <td>
                {item.profile.firstName} {item.profile.lastName}
              </td>
              <td>{item.gpa}</td>
              <td>
                <Button ghost>
                  <Link to={"/StudentView/" + item.id}> View Profile</Link>
                </Button>
              </td>
            </tr>
          );
        })}
        </tbody>
      </table>
    </div>
  );
}

export default ViewStudents;

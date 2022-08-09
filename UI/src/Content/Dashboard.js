import Chart from "./Chart";
import JobAdvertisment from "./JobAdvertisement";
import AppliedJobs from "./AppliedJobs";

export default function Dashboard() {
  return (
    <>
      <Chart></Chart>
      <br></br>
      <br></br>
      <JobAdvertisment></JobAdvertisment>
      <br></br>
      <br></br>
      <AppliedJobs></AppliedJobs>
    </>
  );
}

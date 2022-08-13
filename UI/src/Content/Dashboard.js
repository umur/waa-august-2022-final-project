import AppliedJobs from "./AppliedJobs";
import Chart from "./Chart";
import JobAdvertisment from "./JobAdvertisement";

export default function Dashboard() {
  return (
    <>
      <Chart></Chart>
      <JobAdvertisment></JobAdvertisment>
      <AppliedJobs></AppliedJobs>
    </>
  );
}

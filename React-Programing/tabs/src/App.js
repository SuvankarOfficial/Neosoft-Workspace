import React, { useState, useEffect } from "react";

const url = "https://course-api.com/react-tabs-project";
function App() {
  const [jobs, setjobs] = useState([]);
  const [index, setIndex] = useState(0);
  const fetchJob = async () => {
    const response = await fetch(url);
    const jobsData = await response.json();
    console.log(jobsData);
    setjobs(jobsData);
  };

  useEffect(() => {
    fetchJob();
  }, []);

  return (
    <div>
      <div className="title">
        <h2>Experience</h2>
      </div>
      <div className="bottom-box">
        <div className="bottom-left-box">
          <div className="bottom-left-box-elements">
            {jobs.map((job, value) => {
              return (
                <div>
                  <button
                    type="button"
                    class="btn btn-primary job-button"
                    onClick={() => setIndex(value)}
                  >
                    {job.company}
                  </button>
                </div>
              );
            })}
          </div>
        </div>
        <div className="bottom-right-box">
          {jobs.length > 0 && (
            <div>
              <h1>{jobs[index].title}</h1>
              <h2>{jobs[index].company}</h2>
              <h3>{jobs[index].dates}</h3>
              <ul>
                {jobs[index].duties.map((detail) => {
                  return <li>{detail}</li>;
                })}
              </ul>
            </div>
          )}
        </div>
      </div>
    </div>
  );
}

export default App;

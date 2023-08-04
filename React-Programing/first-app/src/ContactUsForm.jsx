import React, { useState } from "react";
import "./ContactUsForm.css";

// const formData = {
//   firstName : "" ,lastName : "" ,email : "" ,phone : "" 
// }

export default function ContactUsForm() {

  const [ formData, setFormData ] = useState({firstName:"",lastName:"",email:"",phone:""})

  const handleChangeData = (event) =>{
    const { name , value } = event.target;
    console.log(name);
    setFormData((data) => ({...data, [name]:value}));
  }

  const handleSubmit = (event) => {
    event.preventDefault();
    alert(`Name: ${formData.firstName} ${formData.lastName}, Email: ${formData.email}, Phone: ${formData.phone}`);
  }
  return (
    <div>
      <div className="container leftAlign">
        <div className="row mt-4">
          <div className="dravid col-md-6">
            <div> First Name </div>
            <div className="input-group mb-3">
              <input 
              name="firstName"
                onChange={handleChangeData}
                type="text"
                className="form-control"
                placeholder="First Name"
                aria-label="First Name"
                aria-describedby="basic-addon1"
                // value={formData?.firstName}
              />
            </div>
          </div>
          <div className="dravid col-md-6">
            <div> Last Name </div>
            <div className="input-group mb-3">
              <input
              name="lastName"
                onChange={handleChangeData}
                type="text"
                className="form-control"
                placeholder="Last Name"
                aria-label="Last Name"
                aria-describedby="basic-addon1"
                // value={formData?.lastName}
              />
            </div>
          </div>
        </div>
        <div className="row mt-4">
          <div className="dravid col-md-6">
            <div> Email </div>
            <div className="input-group mb-3">
              <input
              name="email"
                onChange={handleChangeData}
                type="email"
                className="form-control"
                placeholder="you@yoursite.com"
                aria-label="you@yoursite.com"
                aria-describedby="basic-addon1"
                // value={formData?.email}
              />
            </div>
          </div>
          <div className="dravid col-md-6">
            <div> Phone </div>
            <div className="input-group mb-3">
              <input
              name="phone"
                onChange={handleChangeData}
                type="number"
                className="form-control"
                placeholder="Phone"
                aria-label="Phone"
                aria-describedby="basic-addon1"
                // value={formData?.phone}
              />
            </div>
          </div>
        </div>
        <div className="mt-4">
          <input type="checkbox" name="NewsDetails" /> Do you like to received
          news about out product via e-mail?
        </div>
        <div>
        <input className="btn btn-primary mr-2 mt-4" type="submit" value="Submit" onClick={handleSubmit}/>
        <input className="btn btn-primary mr-2 mt-4" type="reset" value="Reset" />
      </div>
      </div>
    </div>
  );
}

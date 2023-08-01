import React from "react";
import "./ContactUsForm.css";

export default function ContactUsForm() {
  return (
    <div>
      <div className="container leftAlign">
        <div className="row mt-4">
          <div className="dravid col-md-6">
            <div> First Name </div>
            <div class="input-group mb-3">
              <input
                type="text"
                class="form-control"
                placeholder="First Name"
                aria-label="First Name"
                aria-describedby="basic-addon1"
              />
            </div>
          </div>
          <div className="dravid col-md-6">
            <div> Last Name </div>
            <div class="input-group mb-3">
              <input
                type="text"
                class="form-control"
                placeholder="Last Name"
                aria-label="Last Name"
                aria-describedby="basic-addon1"
              />
            </div>
          </div>
        </div>
        <div className="row mt-4">
          <div className="dravid col-md-6">
            <div> Email </div>
            <div class="input-group mb-3">
              <input
                type="text"
                class="form-control"
                placeholder="you@yoursite.com"
                aria-label="you@yoursite.com"
                aria-describedby="basic-addon1"
              />
            </div>
          </div>
          <div className="dravid col-md-6">
            <div> Phone </div>
            <div class="input-group mb-3">
              <input
                type="number"
                class="form-control"
                placeholder="Phone"
                aria-label="Phone"
                aria-describedby="basic-addon1"
              />
            </div>
          </div>
        </div>
        <div className="mt-4">
          <input type="checkbox" name="NewsDetails" /> Do you like to received
          news about out product via e-mail?
        </div>
        <div>
        <input class="btn btn-primary mr-2 mt-4" type="submit" value="Submit" />
        <input class="btn btn-primary mr-2 mt-4" type="reset" value="Reset" />
      </div>
      </div>
    </div>
  );
}

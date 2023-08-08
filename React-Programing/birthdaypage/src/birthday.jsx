import React from "react";
import data from "./data";
import List from "./List";

export default function birthday() {
  return (
    <div className="Container">
      <div className="theBox">
        <h2>{data.length} Birthday Today</h2>
        <List data={data}/>
        <button type="button" class="btn btn-primary ButtonSendWishes">Send Wishes</button>
      </div>
    </div>
  );
}

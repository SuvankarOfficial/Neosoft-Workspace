import React from "react";

export default function WhereOption(props) {
  return (
    <option value={props.row} key={props.row}>
      {props.row}
    </option>
  );
}

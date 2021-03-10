import React from "react";

const QueueTableComponent = ({ logs }) => {
  return (
    <table className="table col-10 mt-3 fixedTable">
      <thead>
        <tr>
          <th scope="col" style={{ width: "30px" }}>
            #
          </th>
          <th scope="col">Įvykiai</th>
        </tr>
      </thead>
      <tbody>
        {logs.map((message, index) => (
          <tr key={index}>
            <th scope="row">{index + 1}</th>
            <td>{message}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default QueueTableComponent;

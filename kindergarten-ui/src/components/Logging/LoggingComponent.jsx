import React from "react";

const QueueTableComponent = ({ logs }) => {
  return (
    <div className="templatemo-content-widget no-padding col-12 my-4 mx-0">
      <div className="panel panel-default table-responsive">
        <table className="table table-striped table-bordered templatemo-user-table">
          <thead>
            <tr>
              <th>#</th>
              <th>Įvykiai</th>
            </tr>
          </thead>
          <tbody>
            {logs.map((message, index) => (
              <tr key={index}>
                <th>{index + 1}</th>
                <td>{message}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default QueueTableComponent;

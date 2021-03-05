import React from "react";
import Proptypes from "prop-types";

const ESApprovedApplicationListComponent = ({ applications, recalculation }) => {
  let table = (
    <div className="col-12 mt-3">
      <div className="pb-5">
        <button type="submit" className="btn btn-yellow float-right" onClick={recalculation}>
          Perūšiuoti prašymus
        </button>
      </div>
      <table id="groupTable" className="table col-12">
        <thead>
          <tr>
            {/* <th scope='col' style={{ width: "30px" }}>#</th> */}
            <th scope="col" style={{ width: "60px" }}>
              Balas
            </th>
            <th scope="col">Vaikas</th>
            <th scope="col">Tėvelis</th>
            <th scope="col" style={{ width: "115px" }}>
              Data
            </th>
            <th scope="col">Statusas</th>
            <th scope="col">Darželis</th>
            <th scope="col" style={{ width: "115px" }}>
              Laukimo Nr
            </th>
          </tr>
        </thead>

        <tbody>
          {applications.map(
            (
              {
                id,
                score,
                childFirstName,
                childLastName,
                parentFirstName,
                parentLastName,
                date,
                status,
                approvedKindergartenTitle,
                waitingNumber,
              },
              index
            ) => {
              if (waitingNumber === null) {
                waitingNumber = "-";
              }

              if (approvedKindergartenTitle === null) {
                approvedKindergartenTitle = "-";
              }

              return (
                <tr key={id}>
                  {/* <th scope='row'>{index + 1}</th> */}
                  <td>{score}</td>
                  <td>{childFirstName + " " + childLastName}</td>
                  <td>{parentFirstName + " " + parentLastName}</td>
                  <td>{date}</td>
                  <td>{status}</td>
                  <td>{approvedKindergartenTitle}</td>
                  <td>{waitingNumber}</td>
                </tr>
              );
            }
          )}
        </tbody>
      </table>
    </div>
  );

  return applications.length === 0 ? <h6 className="text-center">Prašymų registracija nėra sustabdyta</h6> : table;
};

ESApprovedApplicationListComponent.propTypes = {
  applications: Proptypes.array,
};

export default ESApprovedApplicationListComponent;

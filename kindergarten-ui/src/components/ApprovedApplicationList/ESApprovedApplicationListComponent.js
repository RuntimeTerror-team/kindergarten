import React from "react";
import Proptypes from "prop-types";

const ESApprovedApplicationListComponent = ({
  applications,
  recalculation,
  currentPage,
  totalPages,
  firstPage,
  prevPage,
  lastPage,
  nextPage,
}) => {
  let table = (
    <div className="col-12 mt-3">
      <div className="pb-5">
        <button type="submit" className="btn btn-main float-right" onClick={recalculation}>
          Perrūšiuoti prašymus
        </button>
      </div>
      <table id="groupTable" className="table col-12 pt-4">
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

      <div className="btn-toolbar pt-5" role="toolbar" aria-label="Toolbar with button groups">
        <div className="btn-group mr-2" role="group" aria-label="First group">
          <div>
            <div style={{ float: "left" }}>
              Rodoma {currentPage} iš {totalPages} puslapių
            </div>
            <div style={{ float: "right" }}></div>
          </div>
        </div>
        <div className="btn-group mr-2" role="group" aria-label="First group">
          <button type="button mr-2" className="btn btn-main " onClick={firstPage}>
            Pirmas
          </button>
        </div>

        <div className="btn-group mr-2" role="group" aria-label="Second group">
          <button type="button mr-2" className="btn btn-main " onClick={prevPage}>
            &lt;&lt;
          </button>
        </div>

        <div className="btn-group mr-2" role="group" aria-label="Second group">
          <button type="button mr-2" className="btn btn-main " onClick={nextPage}>
            &gt;&gt;
          </button>
        </div>

        <div className="btn-group mr-2" role="group" aria-label="Second group">
          <button type="button" className="btn btn-main" onClick={lastPage}>
            Paskutinis
          </button>
        </div>
      </div>
    </div>
  );

  return applications.length === 0 ? <h6 className="text-center">Prašymų registracija nėra sustabdyta</h6> : table;
};

ESApprovedApplicationListComponent.propTypes = {
  applications: Proptypes.array,
};

export default ESApprovedApplicationListComponent;

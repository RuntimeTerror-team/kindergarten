import React from "react";
import Proptypes from "prop-types";
import { GrNext } from "react-icons/gr";
import { GrPrevious } from "react-icons/gr";
import {Modal, Button} from "react-bootstrap";
// import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
// import { faFilePdf } from "@fortawesome/free-solid-svg-icons";

const ESApprovedApplicationListComponent = ({
  applications,
  queues,
  recalculation,
  currentPage,
  totalPages,
  firstPage,
  prevPage,
  lastPage,
  nextPage,
  queueStatus,
  permission,
  noPDF,
  statusRejected,
  closeAlert,
  onStatusChange,
  onOpenPDF
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
            <th scope="col">Vaiko atstovas</th>
            <th scope="col" style={{ width: "115px" }}>
              Data
            </th>
            <th scope="col">Statusas</th>
            <th scope="col">Darželis</th>
            <th scope="col" style={{ width: "115px" }}>
              Laukimo Nr.
            </th>
            <th scope="col">Sveikatos pažyma</th>
            {permission && queueStatus === "LOCKED" ? <th scope="col">Redagavimas</th> : null}
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
                applicationId
                
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
                  <td>
                  <button className="btn btn-link" onClick={onOpenPDF} value={applicationId}>
                    Atsisiųsti
                  </button>
                  </td>

                  {/* <button className="btn btn-link" value={applicationId} onClick={onOpenPDF}>
                  <FontAwesomeIcon color="black" icon={faFilePdf} />
                    </button> */}
                  {permission && queueStatus === "LOCKED" ? (
                    <td>
                      {
                        status !== "Atmestas" ?
                      <button
                        className="btn btn-link"
                        value={applicationId}
                        onClick={onStatusChange}>
                        Atšaukti prašymą
                      </button>
                      : null
                      }
                    </td>
                  ) : null}
                </tr>
              );
            }
          )}
        </tbody>
      </table>

      <Modal show={noPDF} aria-labelledby="contained-modal-title-vcenter" centered>
        <Modal.Body>Vaiko atstovas nėra pridėjęs sveikatos pažymos.</Modal.Body>
        <Modal.Footer>
        <Button variant="secondary" onClick={closeAlert}>
            Uždaryti
        </Button>
        </Modal.Footer>
      </Modal>
      <Modal show={statusRejected} aria-labelledby="contained-modal-title-vcenter" centered>
        <Modal.Body>Prašymas sėkmingai atmestas.</Modal.Body>
        <Modal.Footer>
        <Button variant="secondary" onClick={closeAlert}>
            Uždaryti
        </Button>
        </Modal.Footer>
      </Modal>

      <div className="float-right btn-toolbar pt-5" role="toolbar" aria-label="Toolbar with button groups">
        <div className="btn-group mr-2" role="group" aria-label="First group">
          <div className="pr-3" style={{ color: "#AFAFAF", alignItems: "center" }}>
            <p>
              Rodoma {currentPage} iš {totalPages} puslapių{" "}
            </p>
          </div>
        </div>

        <div className="btn-group mr-2" role="group" aria-label="First group">
          <button type="button mr-2" className="btn btn-main " onClick={firstPage}>
            Pirmas
          </button>
        </div>

        <div className="btn-group mr-2" role="group" aria-label="Second group">
          <button type="button mr-2" className="btn btn-main " onClick={prevPage}>
            <GrPrevious />
          </button>
        </div>

        <div className="btn-group mr-2" role="group" aria-label="Second group">
          <button type="button mr-2" className="btn btn-main " onClick={nextPage}>
            <GrNext />
          </button>
        </div>

        <div className="btn-group" role="group" aria-label="Second group">
          <button type="button" className="btn btn-main" onClick={lastPage}>
            Paskutinis
          </button>
        </div>
      </div>
    </div>
  );

  
  return queues.find(queue => queue.status === "ACTIVE") ? <h6 className="text-center">Prašymų registracija nėra sustabdyta</h6> : table;

  
};

ESApprovedApplicationListComponent.propTypes = {
  applications: Proptypes.array,
};

export default ESApprovedApplicationListComponent;

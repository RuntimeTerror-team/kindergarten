import React from "react";
import Proptypes from "prop-types";
import { GrNext } from "react-icons/gr";
import { GrPrevious } from "react-icons/gr";
import { Modal, Button } from "react-bootstrap";
import { BsSearch } from "react-icons/bs";

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
  onOpenPDF,
  search,
  searchData,
  updateSearchInputValue
}) => {

  let table = (

    <div>
      <div className="row">
        <div className="col-10">
          <form className="form-inline" onSubmit={searchData}>
            <input className="form-control mr-sm-2" type="search" placeholder="Įveskite vardą ar pavardę" aria-label="Search" value={search} onChange={updateSearchInputValue}></input>
            <button className="btn btn-outline-primary my-2 my-sm-0" type="submit" >Ieškoti <BsSearch/> </button>
          </form>
        </div>
        <div className="col-2">
          <button type="submit" className="templatemo-blue-button" onClick={recalculation}>
            Perrūšiuoti prašymus
          </button>
        </div>
      </div>
      <div className="templatemo-content-widget no-padding col-12 my-4 mx-0">
        <div className="panel panel-default table-responsive">
          <table className="table table-striped table-bordered templatemo-user-table">
            <thead>
              <tr>
                {/* <th scope='col' style={{ width: "30px" }}>#</th> */}
                <th scope="col">Balas</th>
                <th scope="col">Vaikas</th>
                <th scope="col">Vaiko atstovas</th>
                <th scope="col">Data</th>
                <th scope="col">Statusas</th>
                <th scope="col">Darželis</th>
                <th scope="col">Laukimo Nr.</th>
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
        </div>
      </div>

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
          <button type="button mr-2" className="templatemo-blue-button " onClick={firstPage}>
            Pirmas
          </button>
        </div>

        <div className="btn-group mr-2" role="group" aria-label="Second group">
          <button type="button mr-2" className="templatemo-blue-button " onClick={prevPage}>
            <GrPrevious />
          </button>
        </div>

        <div className="btn-group mr-2" role="group" aria-label="Second group">
          <button type="button mr-2" className="templatemo-blue-button " onClick={nextPage}>
            <GrNext />
          </button>
        </div>

        <div className="btn-group" role="group" aria-label="Second group">
          <button type="button" className="templatemo-blue-button" onClick={lastPage}>
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

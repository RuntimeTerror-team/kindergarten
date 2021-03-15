import React from "react";
import Proptypes from "prop-types";
import AdminUserTableComponent from "../AdminUserTable/AdminUserTableComponent";
import { Modal, Button } from "react-bootstrap";

let AdminUserFormComponent = ({ currentPage, totalPages, firstPage, prevPage, lastPage, nextPage, updateSearchInputValue,
   handleChange, handleSubmit, changedPassword, closeAlert, downloadUserData, restoreOriginalPassword, ...otherProps }) => {
  const { firstname, lastname, firstnameLength, lastnameLength, createdUsername, isCreated, users } = otherProps;

  return (
    <div>
      <div className="templatemo-content-widget white-bg my-4 col-6 mx-auto">
        <h3 className="margin-bottom-10">Paskyros sukūrimas</h3>
        <form className="form" onSubmit={handleSubmit}>
          <div className="form-group">
            <input
              type="text"
              className={`form-control ${firstnameLength}`}
              id="firstname"
              value={firstname}
              onChange={handleChange}
              name="firstname"
              placeholder="Vardas"
            />
            <div className="invalid-feedback">Šis laukas privalomas. Vardas turi būti 2-30 simbolių ilgio.</div>
          </div>
          <div className="form-group">
            <input
              type="text"
              className={`form-control ${lastnameLength}`}
              id="lastname"
              value={lastname}
              onChange={handleChange}
              name="lastname"
              placeholder="Pavardė"
            />
            <div className="invalid-feedback">Šis laukas privalomas. Pavardė turi būti 2-30 simbolių ilgio.</div>
          </div>
          <div className="input-group mb-3">
            <div className="input-group-prepend">
              <label className="input-group-text" htmlFor="inputGroupSelect01" style={{ backgroundColor: "##EFEFEF" }}>
                Rolė
              </label>
            </div>
            <select className="custom-select" id="inputGroupSelect01" name="role">
              <option value="GUARDIAN" defaultValue>
                Tėvas/globėjas
              </option>
              <option value="EDUCATION_SPECIALIST">Švietimo specialistas</option>
            </select>
          </div>
          <div className="form-group col-12 text-right mb-0">
            <button className="templatemo-blue-button">Išsaugoti</button>
          </div>
        </form>
      </div>
      {isCreated && createdUsername.length <= 30 && (
        <div
          className="alert alert-success mt-4 col-12 text-center"
          role="alert">{`Naudotojas sukurtas. Prisijungimo vardas ir slaptažodis: ${createdUsername}`}</div>
      )}
      {isCreated && createdUsername.length > 30 && (
        <div className="alert alert-warning mt-4 col-12 text-center" role="alert">
          {createdUsername}
        </div>
      )}
      {<AdminUserTableComponent
        users={users}
        downloadUserData={downloadUserData}
        restoreOriginalPassword={restoreOriginalPassword}
        updateSearchInputValue={updateSearchInputValue}
        currentPage={currentPage}
        totalPages={totalPages}
        firstPage={firstPage}
        prevPage={prevPage}
        lastPage={lastPage}
        nextPage={nextPage}
      />}
      <Modal show={changedPassword} aria-labelledby="contained-modal-title-vcenter" centered>
        <Modal.Body>Sėkmingai atstatytas naudotojo pirminis slaptažodis.</Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={closeAlert}>
            Uždaryti
        </Button>
        </Modal.Footer>
      </Modal>
    </div>
  )
};

AdminUserFormComponent.propTypes = {
  firstname: Proptypes.string.isRequired,
  lastname: Proptypes.string.isRequired,
  role: Proptypes.string.isRequired,
  handleChange: Proptypes.func.isRequired,
  handleSubmit: Proptypes.func.isRequired,
  firstnameLength: Proptypes.string.isRequired,
  lastnameLength: Proptypes.string.isRequired,
  createdUsername: Proptypes.string.isRequired,
  isCreated: Proptypes.bool.isRequired,
  users: Proptypes.array.isRequired,
};

export default AdminUserFormComponent;

import React from "react";
import Proptypes from "prop-types";
import AdminUserTableComponent from "../AdminUserTable/AdminUserTableComponent";

let AdminUserFormComponent = ({ handleChange, handleSubmit, ...otherProps }) => {
  const { firstname, lastname, firstnameLength, lastnameLength, createdUsername, isCreated, users } = otherProps;

  return (
    <div className="row justify-content-center align-items-center">
      <form className="form" onSubmit={handleSubmit}>
        <div className="form-group">
          <input
            type="text"
            className={`largeInput form-control ${firstnameLength}`}
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
            className={`largeInput form-control ${lastnameLength}`}
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
            <label className="input-group-text" htmlFor="inputGroupSelect01" style={{ backgroundColor: "#e3f2fd" }}>
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
        <button className="btn btn-green float-right">Išsaugoti</button>
      </form>
      {isCreated && createdUsername.length <= 30 && (
        <div
          className="alert alert-success mt-4"
          role="alert">{`Naudotojas sukurtas. Prisijungimo vardas ir slaptažodis: ${createdUsername}`}</div>
      )}
      {isCreated && createdUsername.length > 30 && (
        <div className="alert alert-success mt-4" role="alert">
          {createdUsername}
        </div>
      )}
      {users.length > 0 && <AdminUserTableComponent
        users={users}
      />}
    </div>
  )
};

AdminUserFormComponent.propTypes = {
  firstname: Proptypes.string.isRequired,
  lastname: Proptypes.string.isRequired,
  role: Proptypes.string.isRequired,
  handleChange: Proptypes.func.isRequired,
  handleSubmit: Proptypes.func,
};

export default AdminUserFormComponent;

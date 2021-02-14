import React from "react";
import Proptypes from "prop-types";
import DistrictTableComponent from "../AdminDistrictTable/DistrictTableComponent";

import "../../styles/districtAdministration.css";

const AdminDistrictFormComponent = ({
  districts,
  addDistrict,
  titleValidation,
  districtName,
  onCreatingDistrictNameChange,
  ...props
}) => {
  return (
    <div id="districtAdministrationComponent" className="col-12 row">
      <form className="form-inline text-center" onSubmit={addDistrict}>
        <div className={`input-group mx-auto ${titleValidation}`}>
          <input
            type="text"
            className="form-control"
            id="inputDistrict"
            placeholder="Pavadinimas"
            name="districtName"
            value={districtName}
            onChange={onCreatingDistrictNameChange}
          />
          <div className="input-group-append">
            <button type="submit" className="btn btn-green">
              Pridėti
            </button>
          </div>
        </div>
        <div className="invalid-feedback">Pavadinimo ilgis turi būti 5-20 ženklų.</div>
      </form>
      <div className="row">
        {
          <span className={props.messageStyle} style={{ width: "23em" }}>
            {props.requestMessage}
          </span>
        }
      </div>
      {districts.length > 0 && <DistrictTableComponent districts={districts} {...props} />}
    </div>
  );
};

AdminDistrictFormComponent.propTypes = {
  addDistrict: Proptypes.func.isRequired,
  titleValidation: Proptypes.string.isRequired,
  districtName: Proptypes.string,
  onCreatingDistrictNameChange: Proptypes.func.isRequired,
  messageStyle: Proptypes.string.isRequired,
  requestMessage: Proptypes.string.isRequired,
  districts: Proptypes.array.isRequired
};

export default AdminDistrictFormComponent;

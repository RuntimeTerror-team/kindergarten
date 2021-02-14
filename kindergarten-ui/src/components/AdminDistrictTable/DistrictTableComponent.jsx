import React from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPencilAlt } from "@fortawesome/free-solid-svg-icons";
import Proptypes from "prop-types";

const DistrictTableComponent = ({
  updatingMessage,
  updatingMessageStyle,
  districts,
  updateDistrict,
  startUpdate,
  updatingId,
  onDistrictNameChange,
  updatingTitle,
  titleValidationInUpdate,
}) => {
  return (
    <div className="col-12 mt-3">
      <table id="districtTable" className="table col-8 mx-auto">
        <thead>
          <tr>
            <th scope="col" style={{ width: "30px" }}>
              #
            </th>
            <th scope="col">Pavadinimas</th>
          </tr>
        </thead>

        <tbody>
          {districts.map(({ id, title }, index) => (
            <tr key={id}>
              <th scope="row">{index + 1}</th>
              <td>
                {+updatingId !== +id && (
                  <button className="btn btn-link" id={id} onClick={startUpdate} value={title}>
                    {title} <FontAwesomeIcon color="#F1CC00" icon={faPencilAlt} />
                  </button>
                )}
                {+updatingId === +id && (
                  <form onSubmit={updateDistrict}>
                    <div className={`input-group ${titleValidationInUpdate} px-3`}>
                      <input
                        type="text"
                        className="form-control"
                        id={id}
                        defaultValue={title}
                        onChange={onDistrictNameChange}
                        name="districtTitle"
                        value={updatingTitle}
                      />
                      <div className="input-group-append">
                        <button type="submit" className="btn btn-info">
                          Išsaugoti
                        </button>
                      </div>
                      <span className={updatingMessageStyle} style={{ width: "23em" }}>
                        {updatingMessage}
                      </span>
                    </div>
                    <div className="invalid-feedback">Pavadinimo ilgis turi būti 5-20 ženklų.</div>
                  </form>
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

DistrictTableComponent.propTypes = {
  districts: Proptypes.array.isRequired,
  updatingMessage: Proptypes.string.isRequired,
  updatingMessageStyle: Proptypes.string.isRequired,
  updateDistrict: Proptypes.func.isRequired,
  startUpdate: Proptypes.func.isRequired,
  updatingId: Proptypes.string.isRequired,
  onDistrictNameChange: Proptypes.func.isRequired,
  updatingTitle: Proptypes.string,
  titleValidationInUpdate: Proptypes.string
};

export default DistrictTableComponent;

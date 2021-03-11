import React from "react";
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
    <div className="templatemo-content-widget no-padding col-12 my-4 mx-0">
      <div className="panel panel-default table-responsive">
        <table className="table table-striped table-bordered templatemo-user-table">
          <thead>
            <tr>
              <th>#</th>
              <th>Pavadinimas</th>
              <th>Veiksmai</th>
            </tr>
          </thead>
          <tbody>
            {districts.map(({ id, title }, index) => (
              <tr key={id}>
                <th scope="row">{index + 1}</th>
                <td>
                  {+updatingId !== +id && title}
                  {+updatingId === +id && (
                    <form onSubmit={updateDistrict}>
                      <div className={`input-group ${titleValidationInUpdate}`}>
                        <input
                          type="text"
                          className="form-control"
                          id={id}
                          defaultValue={title}
                          onChange={onDistrictNameChange}
                          name="districtTitle"
                          value={updatingTitle}
                        />
                        <span className={updatingMessageStyle}>
                          {updatingMessage}
                        </span>
                      </div>
                      <div className="invalid-feedback">Pavadinimo ilgis turi būti 5-20 ženklų.</div>
                    </form>
                  )}
                </td>
                <td>
                  {+updatingId !== +id && <button className="templatemo-edit-btn" id={id} onClick={startUpdate} value={title}>Redaguoti</button>}
                  {+updatingId === +id && <button type="submit" className="templatemo-edit-btn" value={updatingTitle} id={id} onClick={updateDistrict}>Išsaugoti</button>}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
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

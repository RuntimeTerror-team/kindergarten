import React from "react";
import Proptypes from "prop-types";
import AgeRangeTableComponent from "./AgeRangeTableComponent";

let AgeRangeFormComponent = (props) => {
  return (
    <div className="row justify-content-center align-items-center">
      <div>
        <form className="form ageRangeForm" onSubmit={props.onSubmit}>
          <div className="form-group">
            <select
              className={"form-control " + props.fromAgeFieldValidation}
              name="fromAge"
              style={{ width: "25em" }}
              value={props.fromAge}
              onChange={props.onFromAgeChange}>
              <option value="">Amžius nuo</option>
              <option value="1">1</option>
              <option value="2">2</option>
              <option value="3">3</option>
              <option value="4">4</option>
              <option value="5">5</option>
              <option value="6">6</option>
            </select>
            <div className="invalid-feedback">Prašome užpildyti amžių nuo</div>
          </div>
          <div className="form-group">
            <select
              className={"form-control " + props.toAgeFieldValidation}
              name="toAge"
              style={{ width: "25em" }}
              value={props.toAge}
              onChange={props.onToAgeChange}>
              <option value="">Amžius iki</option>
              <option value="2">2</option>
              <option value="3">3</option>
              <option value="4">4</option>
              <option value="5">5</option>
              <option value="6">6</option>
              <option value="7">7</option>
            </select>
            <div className="invalid-feedback">Prašome užpildyti amžių iki</div>
          </div>
          <div className="col-12 text-right">
            <button className="btn btn-green">Sukurti</button>
          </div>
          <div className="col-12 pt-3">
            {props.invalidInterval ? (
              <span className="alert alert-danger" style={{ width: "23em" }}>
                Amžius nuo negali būti lygus arba didesnis už amžių iki
              </span>
            ) : null}
          </div>
          <div className="row">
            {
              <span className={props.messageStyle} style={{ width: "23em" }}>
                {props.requestMessage}
              </span>
            }
          </div>
        </form>
        {props.groups.length > 0 ? <AgeRangeTableComponent groups={props.groups} onDelete={props.onDelete} /> : null}
      </div>
    </div>
  );
};

AgeRangeFormComponent.propTypes = {
  groups: Proptypes.array.isRequired,
  fromAge: Proptypes.string.isRequired,
  toAge: Proptypes.string.isRequired,
  fromAgeFieldValidation: Proptypes.string.isRequired,
  toAgeFieldValidation: Proptypes.string.isRequired,
  onFromAgeChange: Proptypes.func.isRequired,
  invalidInterval: Proptypes.bool.isRequired,
  requestMessage: Proptypes.string.isRequired,
  messageStyle: Proptypes.string.isRequired,
  onToAgeChange: Proptypes.func.isRequired,
  onSubmit: Proptypes.func.isRequired,
  onDelete: Proptypes.func.isRequired,
};

export default AgeRangeFormComponent;

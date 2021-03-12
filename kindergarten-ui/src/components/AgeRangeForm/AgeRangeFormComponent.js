import React from "react";
import Proptypes from "prop-types";
import AgeRangeTableComponent from "./AgeRangeTableComponent";

let AgeRangeFormComponent = (props) => {
  return (
    <div>
      <div className="templatemo-content-widget white-bg my-4 col-6 mx-auto">
        <form className="templatemo-login-form" onSubmit={props.onSubmit}>
          <div className="form-group">
            <select
              className={"form-control " + props.fromAgeFieldValidation}
              name="fromAge"
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
          {props.invalidInterval ? (
            <div className="alert alert-danger">
              Amžius nuo negali būti lygus arba didesnis už amžių iki
            </div>
          ) : null}
          <div className={props.messageStyle}>
            {props.requestMessage}
          </div>
          <div className="col-12 text-right">
            <button className="templatemo-blue-button">Sukurti</button>
          </div>
        </form>
      </div>
      {props.groups.length > 0 ? <AgeRangeTableComponent groups={props.groups} onDelete={props.onDelete} /> : null}
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

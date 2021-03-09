import React from "react";
import Proptypes from "prop-types";

let PasswordChangeComponent = ({ password, password2, oldPassword, passwordValidation, password2Validation, oldPasswordValidation, notMatchingMessage, notMatchingMessageStyle,
  successMessage, successMessageStyle, wrongOldPasswordMessage, wrongOldPasswordMessageStyle, onSubmit, onPasswordChange, onPassword2Change, onOldPasswordChange }) => {

  return (
    <div className="col-6 justify-content-center align-items-center">
      <div style={{ width: "300px" }}>
        <h2 className="mb-3  mt-5 text-center">Pakeisti slaptažodį</h2>
        <form className="form row" onSubmit={onSubmit}>
          <div className="form-group">
            <input className={`largeInput form-control  ${oldPasswordValidation}`} type="password" id="oldPassword" value={oldPassword} onChange={onOldPasswordChange} name="oldPassword" placeholder="Senas slaptažodis"></input>
            <div className="invalid-feedback">
              Šis laukas privalomas.
                        </div>
          </div>
          <div className="form-group">
            <input className={`largeInput form-control  ${passwordValidation}`} type="password" id="password" value={password} onChange={onPasswordChange} name="password" placeholder="Naujas slaptažodis"></input>
            <div className="invalid-feedback">
              Šis laukas privalomas. Mažiausiai 8 simbolių ilgio, bent viena didžioji raidė, bent viena mažioji raidė ir bent vienas skaičius.
                        </div>
          </div>
          <div className="form-group">
            <input className={`largeInput form-control ${password2Validation}`} type="password" id="password2" value={password2} onChange={onPassword2Change} name="password2" placeholder="Pakartoti slaptažodį"></input>
            <div className="invalid-feedback">
              Šis laukas privalomas. Mažiausiai 8 simbolių ilgio, bent viena didžioji raidė, bent viena mažioji raidė ir bent vienas skaičius.
                        </div>
          </div>
          <div className="col-12 text-right p-0">
            <button className="btn btn-info mb-4">Išsaugoti</button>
          </div>
        </form>

        <div className={notMatchingMessageStyle + " mt-2"}>
          <span>{notMatchingMessage}</span>
        </div>

        <div className={successMessageStyle + " mt-2"}>
          <span>{successMessage}</span>
        </div>

        <div className={wrongOldPasswordMessageStyle + " mt-2"}>
          <span>{wrongOldPasswordMessage}</span>
        </div>

      </div>

    </div>


  )

}

PasswordChangeComponent.propTypes = {

  password: Proptypes.string.isRequired,
  password2: Proptypes.string.isRequired,
  oldPassword: Proptypes.string.isRequired,
  passwordValidation: Proptypes.string.isRequired,
  password2Validation: Proptypes.string.isRequired,
  oldPasswordValidation: Proptypes.string.isRequired,
  notMatchingMessage: Proptypes.string.isRequired,
  notMatchingMessageStyle: Proptypes.string.isRequired,
  successMessage: Proptypes.string.isRequired,
  successMessageStyle: Proptypes.string.isRequired,
  wrongOldPasswordMessage: Proptypes.string.isRequired,
  wrongOldPasswordMessageStyle: Proptypes.string.isRequired,
  onPasswordChange: Proptypes.func.isRequired,
  onPassword2Change: Proptypes.func.isRequired,
  onOldPasswordChange: Proptypes.func.isRequired,
  onSubmit: Proptypes.func.isRequired

}

export default PasswordChangeComponent;

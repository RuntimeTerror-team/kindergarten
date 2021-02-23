import React from "react";
import Proptypes from "prop-types";

let PasswordChangeComponent = ({ password, password2, passwordValidation, password2Validation,
  onSubmit, onPasswordChange, onPassword2Change }) => {

  return (

    <div className="row justify-content-center align-items-center">
      <div>
        <h1 >Slaptažodžio keitimas</h1>
        <form className="form" onSubmit={onSubmit}>
          <div className="form-group">
            <label htmlFor="password" className="control-label" >Naujas slaptažodis: </label>
            <input className={`largeInput form-control  ${passwordValidation}`} type="password" id="password" value={password} onChange={onPasswordChange} name="password"></input>
            <div className="invalid-feedback">
              Šis laukas privalomas.
                        </div>
          </div>
          <div className="form-group">
            <label htmlFor="password2" className="control-label" >Pakartoti naują slaptažodį: </label>
            <input className={`largeInput form-control ${password2Validation}`} type="password" id="password2" value={password2} onChange={onPassword2Change} name="password2"></input>
            <div className="invalid-feedback">
              Šis laukas privalomas.
                        </div>
          </div>
          <button className="btn btn-info mb-4">Keisti</button>
        </form>

        </div>
        
      </div>

    
  )

}

PasswordChangeComponent.propTypes = {

  password: Proptypes.string.isRequired,
  password2: Proptypes.string.isRequired,
  passwordValidation: Proptypes.string.isRequired,
  password2Validation: Proptypes.string.isRequired,
  onPasswordChange: Proptypes.func.isRequired,
  onPassword2Change: Proptypes.func.isRequired,
  onSubmit: Proptypes.func.isRequired

}

export default PasswordChangeComponent;

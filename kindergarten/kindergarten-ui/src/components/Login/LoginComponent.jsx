import React from "react";
import Proptypes from "prop-types";

let LoginComponent = ({
  username,
  password,
  usernameValidation,
  passwordValidation,
  areCredentialsIncorrect,
  onSubmit,
  onUsernameChange,
  onPasswordChange,
}) => {
  return (
    <div className="row" id="loginForm">
      <div>
        <h1 id="loginh1">Prisijungimas</h1>
        <form className="form" onSubmit={onSubmit}>
          <div className="form-group">
            <label htmlFor="username">Prisijungimo vardas: </label>
            <input
              className={`largeInput form-control ${usernameValidation}`}
              id="username"
              value={username}
              onChange={onUsernameChange}
              name="username"></input>
            <div className="invalid-feedback">Šis laukas privalomas.</div>
          </div>
          <div className="form-group">
            <label htmlFor="password" className="control-label">
              Slaptažodis:{" "}
            </label>
            <input
              className={`largeInput form-control ${passwordValidation}`}
              type="password"
              id="password"
              value={password}
              onChange={onPasswordChange}
              name="password"></input>
            <div className="invalid-feedback">Šis laukas privalomas.</div>
          </div>
          <button className="btn btn-primary mb-4" id="loginButton">
            Prisijungti
          </button>
        </form>
        {areCredentialsIncorrect && (
          <div className="alert alert-danger col-12" role="alert">
            Prisijungimo duomenys neteisingi
          </div>
        )}
      </div>
    </div>
  );
};

LoginComponent.propTypes = {
  username: Proptypes.string.isRequired,
  password: Proptypes.string.isRequired,
  onUsernameChange: Proptypes.func.isRequired,
  onPasswordChange: Proptypes.func.isRequired,
  onSubmit: Proptypes.func.isRequired,
};

export default LoginComponent;
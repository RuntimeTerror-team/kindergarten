import React from 'react';
import Proptypes from 'prop-types';

let LoginComponent = (props) => {

    let CredentialsAlert = () => {

        return(

            <div className="alert alert-danger row my-3" role="alert">
                Neteisingas naudotojo vardas ar slaptažodis
            </div>  
        )
    }

let MultipleAlerts = (props) => {


    let alerts = props.validationErrors.map((message, index) => 

    <div key={index}className="row">
        <div className="alert alert-danger my-3" role="alert">
                {message}
        </div>
    </div>

    )

    return(

        <div>{alerts}</div>
    )
}

    return (
        <div className="row" id="loginForm">
            <div>
                <h1 id="loginh1">Prisijungimas</h1>
                <form className="form"  onSubmit={props.onSubmit}>
                    <div className="form-group">
                        <label htmlFor="username">Prisijungimo vardas: </label>
                        <input className={"form-control " + props.invalidUsername} id="username" value={props.username} onChange={props.onUsernameChange} name="username" placeholder="Prisijungimo vardas"></input>
                    </div>
                    <div className="form-group">
                        <label htmlFor="password" className="control-label" >Slaptažodis: </label>
                        <input className={"form-control " + props.invalidPassword} type="password" id="password" value={props.password} onChange={props.onPasswordChange} name="password" placeholder="Slaptažodis"></input>
                    </div>
                    <button className="btn btn-primary" id="loginButton">Prisijungti</button>
                </form>
                { props.incorrectCredentials ? <CredentialsAlert /> : null}
                {<MultipleAlerts validationErrors = {props.validationErrors}/>}
            </div>
        </div>
    )

}

LoginComponent.propTypes = {

    username: Proptypes.string.isRequired,
    password: Proptypes.string.isRequired,
    invalidUsername: Proptypes.string.isRequired,
    invalidPassword: Proptypes.string.isRequired,
    validationErrors: Proptypes.array.isRequired,
    incorrectCredentials: Proptypes.bool.isRequired,
    onUsernameChange: Proptypes.func.isRequired,
    onPasswordChange: Proptypes.func.isRequired,
    onSubmit: Proptypes.func.isRequired

}


export default LoginComponent;

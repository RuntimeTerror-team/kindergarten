import React from 'react';
import Proptypes from 'prop-types';

let AdminComponent = (props) => {

    return(

        <div className ="row h-100 justify-content-center align-items-center">
           <div>
            <h1>Prisijungimas</h1>
            <form className="form">
                <div className="form-group">
                    <label for="username">Prisijungimo vardas: </label>
                    <input className="form-control " id="username" value={props.username} onChange={props.onUsernameChange} placeholder="Prisijungimo vardas"></input>
                </div>
                <div className="form-group">
                    <label for="password" className="control-label" >Slaptažodis: </label>
                    <input className="form-control" type="password" id="password" value={props.password} onChange={props.onPasswordChange} placeholder="Slaptažodis"></input>
                </div>  
            <button className="btn btn-primary" onClick={props.onSubmit}>Prisijungti</button>
            </form>
        </div>
        </div>
    )

}

AdminComponent.propTypes = {

    username: Proptypes.string.isRequired,
    password: Proptypes.string.isRequired,
    onUsernameChange: Proptypes.func.isRequired,
    onPasswordChange: Proptypes.func.isRequired,
    onSubmit: Proptypes.func.isRequired

}
 

export default AdminComponent
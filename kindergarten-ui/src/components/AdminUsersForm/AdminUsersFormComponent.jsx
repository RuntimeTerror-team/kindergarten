import React from 'react';
import Proptypes from 'prop-types';

let AdminUsersFormComponent = ({ handleChange, handleSubmit, ...otherProps }) => {
    const { firstname, lastname } = otherProps;

    return (
        <div className="row h-100 justify-content-center align-items-center">
            <div>
                <h1 className="mb-4">Paskyros sukūrimas</h1>
                <form className="form" onSubmit={handleSubmit}>
                    <div className="form-group">
                        <input className="form-control " id="firstname" value={firstname} onChange={handleChange} name="firstname" placeholder="Vardas"></input>
                    </div>
                    <div className="form-group">
                        <input className="form-control " id="lastname" value={lastname} onChange={handleChange} name="lastname" placeholder="Pavardė"></input>
                    </div>
                    <div className="input-group mb-3">
                        <div className="input-group-prepend">
                            <label className="input-group-text" htmlFor="inputGroupSelect01"  style={{ backgroundColor: '#e3f2fd' }}>Rolė</label>
                        </div>
                        <select className="custom-select" id="inputGroupSelect01" name="role">
                            <option value="GUARDIAN" defaultValue>Tėvas/globėjas</option>
                            <option value="EDUCATION_SPECIALIST">Švietimo specialistas</option>
                        </select>
                    </div>
                    <button className="btn btn-info float-right">Išsaugoti</button>
                </form>
            </div>
        </div>
    )

}

AdminUsersFormComponent.propTypes = {

    firstname: Proptypes.string.isRequired,
    lastname: Proptypes.string.isRequired,
    role: Proptypes.string.isRequired,
    handleChange: Proptypes.func.isRequired,
    handleSubmit: Proptypes.func

}


export default AdminUsersFormComponent;
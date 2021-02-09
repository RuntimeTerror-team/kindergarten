import React from 'react';
import Proptypes from 'prop-types';

let ChildrenListComponent = (props) =>{

    

    return(

    <div className="w-50 ml-5">

        <div className="form-group">
            <input
              className="form-control" name="name" value={props.name} onChange={props.onChildDetailsChange} placeholder="Vardas"></input>
        </div>
        <div className="form-group">
            <input
              className="form-control" name="surname" value={props.surname} onChange={props.onChildDetailsChange} placeholder="Pavardė"></input>
        </div>
        <div className="form-group">
            <input
              className="form-control" name="birthDate" value={props.birthDate} onChange={props.onChildDetailsChange} placeholder="Gimimo data"></input>
        </div>
        <div className="form-group">
            <input
              className="form-control" name="id" value={props.id} onChange={props.onChildDetailsChange} placeholder="Asmens kodas"></input>
        </div>
        <div className="form-group">
            <input
              className="form-control" name="street" value={props.street} onChange={props.onChildDetailsChange} placeholder="Gatvė"></input>
        </div>
        <div className="form-group">
            <input
              className="form-control" name="city" value={props.city} onChange={props.onChildDetailsChange} placeholder="Miestas"></input>
        </div>

    </div>
    )


}

ChildrenListComponent.propTypes = {

    name: Proptypes.string.isRequired,
    surname: Proptypes.string.isRequired,
    birthDate: Proptypes.string.isRequired,
    id: Proptypes.string.isRequired,
    street: Proptypes.string.isRequired,
    city: Proptypes.string.isRequired,
    onChildDetailsChange: Proptypes.func.isRequired
}

export default ChildrenListComponent
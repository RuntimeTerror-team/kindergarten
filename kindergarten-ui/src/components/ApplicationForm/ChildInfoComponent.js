import React from 'react';
import Proptypes from 'prop-types';

let ChildInfoComponent = (props) =>{

  if (props.currentStep !== 2) {
    return null
  }

    return(

    <div className="w-50 ml-5">

        <h2>Vaiko informacija</h2> 

        <div className="form-group">
            <input
              className="form-control" name="childName" value={props.name} onChange={props.onDetailsChange} placeholder="Vardas"></input>
        </div>
        <div className="form-group">
            <input
              className="form-control" name="childSurname" value={props.surname} onChange={props.onDetailsChange} placeholder="Pavardė"></input>
        </div>
        <div className="form-group">
            <input
              className="form-control" name="childBirthDate" value={props.birthDate} onChange={props.onDetailsChange} placeholder="Gimimo data"></input>
        </div>
        <div className="form-group">
            <input
              className="form-control" name="childId" value={props.id} onChange={props.onDetailsChange} placeholder="Asmens kodas"></input>
        </div>
        <div className="form-group">
            <input
              className="form-control" name="childStreet" value={props.street} onChange={props.onDetailsChange} placeholder="Gatvė"></input>
        </div>
        <div className="form-group">
            <input
              className="form-control" name="childCity" value={props.city} onChange={props.onDetailsChange} placeholder="Miestas"></input>
        </div>

        {props.previousButton()}
        {props.nextButton()}

    </div>
    )


}

ChildInfoComponent.propTypes = {

    currentStep: Proptypes.number.isRequired,
    name: Proptypes.string.isRequired,
    surname: Proptypes.string.isRequired,
    birthDate: Proptypes.string.isRequired,
    id: Proptypes.string.isRequired,
    street: Proptypes.string.isRequired,
    city: Proptypes.string.isRequired,
    onDetailsChange: Proptypes.func.isRequired,
    prev: Proptypes.func.isRequired,
    next: Proptypes.func.isRequired,
    previousButton: Proptypes.func.isRequired,
    nextButton: Proptypes.func.isRequired
}

export default ChildInfoComponent
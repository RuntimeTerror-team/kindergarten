import React from 'react';
import Proptypes from 'prop-types';

let GuardianInfoComponent = (props) =>{

  if (props.currentStep !== 1) {
    return null
  }

  let secondGuardinaForm = () => {

    return(

        <div className="col-6">

        <h2>Antrojo tevėlio informacija</h2>


        <div className="form-group">
            <input
              className="form-control" name="secondGuardianName" value={props.secondGuardianName} onChange={props.onDetailsChange} placeholder="Vardas"></input>
        </div>
        <div className="form-group">
            <input
              className="form-control" name="secondGuardianSurname" value={props.secondGuardianSurname} onChange={props.onDetailsChange} placeholder="Pavardė"></input>
        </div>
        <div className="form-group">
            <input
              className="form-control" name="secondGuardianId" value={props.secondGuardianId} onChange={props.onDetailsChange} placeholder="Asmens kodas"></input>
        </div>
        <div className="form-group">
            <input
              className="form-control" name="secondGuardianPhone" value={props.secondGuardianPhone} onChange={props.onDetailsChange} placeholder="Tel. numeris"></input>
        </div>
        <div className="form-group">
            <input
              className="form-control" name="secondGuardianAddress" value={props.secondGuardianAddress} onChange={props.onDetailsChange} placeholder="Adresas"></input>
        </div>
        <div className="form-group">
            <input
              className="form-control" name="secondGuardianCity" value={props.secondGuardianCity} onChange={props.onDetailsChange} placeholder="Miestas"></input>
        </div>
        <div className="form-group">
            <input
              className="form-control" name="secondGuardianPostalCode" value={props.secondGuardianPostalCode} onChange={props.onDetailsChange} placeholder="Pašto kodas"></input>
        </div>
        <div className="form-group">
            <input
              className="form-control" name="secondGuardianEmail" value={props.secondGuardianEmail} onChange={props.onDetailsChange} placeholder="El. paštas"></input>
        </div>

        </div>


    )


  }

    return(

        <div>

            <div className="row">
                

                <div className="col-lg-6">
                <h2>Tevėlio informacija</h2>

                <div className="form-group">
                    <input
                        className="form-control" name="guardianName" value={props.name} onChange={props.onDetailsChange} placeholder="Vardas"></input>
                </div>
                <div className="form-group">
                    <input
                        className="form-control" name="guardianSurname" value={props.surname} onChange={props.onDetailsChange} placeholder="Pavardė"></input>
                </div>
                <div className="form-group">
                    <input
                        className="form-control" name="guardianId" value={props.id} onChange={props.onDetailsChange} placeholder="Asmens kodas"></input>
                </div>
                <div className="form-group">
                    <input
                        className="form-control" name="guardianPhone" value={props.phone} onChange={props.onDetailsChange} placeholder="Tel. numeris"></input>
                </div>
                <div className="form-group">
                    <input
                        className="form-control" name="guardianAddress" value={props.address} onChange={props.onDetailsChange} placeholder="Adresas"></input>
                </div>
                <div className="form-group">
                    <input
                        className="form-control" name="guardianCity" value={props.city} onChange={props.onDetailsChange} placeholder="Miestas"></input>
                </div>
                <div className="form-group">
                    <input
                        className="form-control" name="guardianPostalCode" value={props.postalCode} onChange={props.onDetailsChange} placeholder="Pašto kodas"></input>
                </div>
                <div className="form-group">
                    <input
                        className="form-control" name="guardianEmail" value={props.email} onChange={props.onDetailsChange} placeholder="El. paštas"></input>
                </div>
                </div>

            </div>

            <div className="col-lg-6">

            {props.showSecondGuardianForm ? secondGuardinaForm() : null}

            </div>



        <div className="row">
        
        <button className="btn btn-info d-block mr-2" onClick={props.fillSecondGuardian}>Pridėti antrąjį tevėlį</button>

        {props.previousButton()}
        {props.nextButton()}

        </div>

        </div>

    
    
    )

    


}

GuardianInfoComponent.propTypes = {

    currentStep: Proptypes.number.isRequired,
    name: Proptypes.string.isRequired,
    surname: Proptypes.string.isRequired,
    id: Proptypes.string.isRequired,
    phone: Proptypes.string.isRequired,
    address: Proptypes.string.isRequired,
    city: Proptypes.string.isRequired,
    postalCode: Proptypes.string.isRequired,
    email: Proptypes.string.isRequired,
    secondGuardianName: Proptypes.string.isRequired,
    secondGuardianSurname: Proptypes.string.isRequired,
    secondGuardianId: Proptypes.string.isRequired,
    secondGuardianPhone: Proptypes.string.isRequired,
    secondGuardianAddress: Proptypes.string.isRequired,
    secondGuardianCity: Proptypes.string.isRequired,
    secondguardianPostalCode: Proptypes.string.isRequired,
    secondGuardianEmail: Proptypes.string.isRequired,
    showSecondGuardianForm: Proptypes.bool.isRequired,
    onDetailsChange: Proptypes.func.isRequired,
    fillSecondGuardian: Proptypes.func.isRequired,
    prev: Proptypes.func.isRequired,
    next: Proptypes.func.isRequired,
    previousButton: Proptypes.func.isRequired,
    nextButton: Proptypes.func.isRequired
}

export default GuardianInfoComponent
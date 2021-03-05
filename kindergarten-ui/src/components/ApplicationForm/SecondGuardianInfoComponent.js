import React from 'react';
import Proptypes from 'prop-types';

let SecondGuardianInfoComponent = (props) => {

  if (props.currentStep !== 1 || !props.showSecondGuardianForm) {
    return null
  }

  return (

    <div style={{ width: "12em" }}>

      <h3>Vaiko atstovas</h3>


      <div className="form-group">
        <input
          className={"form-control " + props.secondGuardianNameValidation} name="secondGuardianName" value={props.secondGuardianName} onChange={props.onDetailsChange} placeholder="Vardas"></input>
        <div className="invalid-feedback">Vardas turi būti 3-20 raidžių ilgio.</div>

      </div>
      <div className="form-group">
        <input
          className={"form-control " + props.secondGuardianSurnameValidation} name="secondGuardianSurname" value={props.secondGuardianSurname} onChange={props.onDetailsChange} placeholder="Pavardė"></input>
        <div className="invalid-feedback">Pavardė turi būti 3-30 raidžių ilgio.</div>
      </div>
      <div className="form-group">
        <input
          className={"form-control " + props.secondGuardianIdValidation} name="secondGuardianId" value={props.secondGuardianId} onChange={props.onDetailsChange} placeholder="Asmens kodas"></input>
        <div className="invalid-feedback">Asmens kodas turi būti 11 skaitmenų ilgio.</div>
      </div>
      <div className="form-group">
        <input
          className={"form-control " + props.secondGuardianPhoneValidation} name="secondGuardianPhone" value={props.secondGuardianPhone} onChange={props.onDetailsChange} placeholder="Tel. numeris"></input>
        <div className="invalid-feedback">Numeris turi būti 12 simbolių ilgio ir prasidėti nuo +370.</div>
      </div>
      <div className="form-group">
        <input
          className={"form-control " + props.secondGuardianAddressValidation} name="secondGuardianAddress" value={props.secondGuardianAddress} onChange={props.onDetailsChange} placeholder="Adresas"></input>
        <div className="invalid-feedback">Adresas turi būti 8-50 simbolių ilgio.</div>
      </div>
      <div className="form-group">
        <input
          className={"form-control " + props.secondGuardianCityValidation} name="secondGuardianCity" value={props.secondGuardianCity} onChange={props.onDetailsChange} placeholder="Miestas"></input>
        <div className="invalid-feedback">Miesto pavadinimas turi būti 4-19 simbolių ilgio.</div>
      </div>
      <div className="form-group">
        <input
          className={"form-control " + props.secondGuardianPostalCodeValidation} name="secondGuardianPostalCode" value={props.secondGuardianPostalCode} onChange={props.onDetailsChange} placeholder="Pašto kodas"></input>
        <div className="invalid-feedback">Pašto kodas turi būti 5 simbolių ilgio, tik skaitmenys.</div>
      </div>
      <div className="form-group">
        <input
          className={"form-control " + props.secondGuardianEmailValidation} name="secondGuardianEmail" value={props.secondGuardianEmail} onChange={props.onDetailsChange} placeholder="El. paštas"></input>
        <div className="invalid-feedback">E.paštas privalo turėti @ simbolį.</div>
      </div>
      <div className="col-12 text-right p-0">
        <button className="btn btn-info" onClick={props.saveSecondGuardian}>Išsaugoti</button>
      </div>
      <div className={props.messageStyle + " mt-2"}>
        <span>{props.message}</span>
      </div>

      <div className={props.emptyGuardianInputsMessageStyle + " mt-2"}>
        <span>{props.emptyGuardianInputsMessage}</span>
      </div>

    </div>



  )




}

SecondGuardianInfoComponent.propTypes = {

  currentStep: Proptypes.number.isRequired,
  secondGuardianName: Proptypes.string.isRequired,
  secondGuardianSurname: Proptypes.string.isRequired,
  secondGuardianId: Proptypes.string.isRequired,
  secondGuardianPhone: Proptypes.string.isRequired,
  secondGuardianAddress: Proptypes.string.isRequired,
  secondGuardianCity: Proptypes.string.isRequired,
  secondGuardianPostalCode: Proptypes.string.isRequired,
  secondGuardianEmail: Proptypes.string.isRequired,
  message: Proptypes.string.isRequired,
  messageStyle: Proptypes.string.isRequired,
  onDetailsChange: Proptypes.func.isRequired,
  saveSecondGuardian: Proptypes.func.isRequired
}

export default SecondGuardianInfoComponent
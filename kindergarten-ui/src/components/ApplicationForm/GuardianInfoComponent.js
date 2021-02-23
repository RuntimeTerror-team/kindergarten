import React from 'react';
import Proptypes from 'prop-types';

let GuardianInfoComponent = (props) =>{

  if (props.currentStep !== 1) {
    return null
  }


    return(

        <div>
                
                
                <div style={{width: "12em"}}>
                <h3>Vaiko atstovas</h3>

                <div className="form-group">
                    <input
                        className={"form-control " + props.guardianNameValidation} name="guardianName" value={props.name} onChange={props.onDetailsChange} placeholder="Vardas" disabled={props.isDisabled}></input>
                        <div className="invalid-feedback">Šis laukas privalomas. Vardas turi būti 3-20 raidžių ilgio.</div>
                </div>
                <div className="form-group">
                    <input
                        className={"form-control " + props.guardianSurnameValidation} name="guardianSurname" value={props.surname} onChange={props.onDetailsChange} placeholder="Pavardė" disabled={props.isDisabled}></input>
                        <div className="invalid-feedback">Šis laukas privalomas. Pavardė turi būti 3-30 raidžių ilgio.</div>
                </div>
                <div className="form-group">
                    <input
                        className={"form-control " + props.guardianIdValidation} name="guardianId" value={props.id} onChange={props.onDetailsChange} placeholder="Asmens kodas" disabled={true}></input>
                        <div className="invalid-feedback">Šis laukas privalomas. Asmens kodas turi būti 11 skaitmenų ilgio.</div>
                </div>
                <div className="form-group">
                    <input
                        className={"form-control " + props.guardianPhoneValidation} name="guardianPhone" value={props.phone} onChange={props.onDetailsChange} placeholder="Tel. numeris" disabled={props.isDisabled}></input>
                        <div className="invalid-feedback">Šis laukas privalomas. Numeris turi būti 12 simbolių ilgio ir prasidėti nuo +370.</div>
                </div>
                <div className="form-group">
                    <input
                        className={"form-control " + props.guardianAddressValidation} name="guardianAddress" value={props.address} onChange={props.onDetailsChange} placeholder="Adresas" disabled={props.isDisabled}></input>
                        <div className="invalid-feedback">Šis laukas privalomas. Adresas turi būti 8-50 simbolių ilgio.</div>
                </div>
                <div className="form-group">
                    <input
                        className={"form-control " + props.guardianCityValidation} name="guardianCity" value={props.city} onChange={props.onDetailsChange} placeholder="Miestas" disabled={props.isDisabled}></input>
                        <div className="invalid-feedback">Šis laukas privalomas. Miesto pavadinimas turi būti 4-19 simbolių ilgio.</div>
                </div>
                <div className="form-group">
                    <input
                        className={"form-control " + props.guardianPostalCodeValidation} name="guardianPostalCode" value={props.postalCode} onChange={props.onDetailsChange} placeholder="Pašto kodas" disabled={props.isDisabled}></input>
                        <div className="invalid-feedback">Šis laukas privalomas. Pašto kodas turi būti 5 simbolių ilgio, tik skaitmenys</div>
                </div>
                <div className="form-group">
                    <input
                        className={"form-control " + props.guardianEmailValidation} name="guardianEmail" value={props.email} onChange={props.onDetailsChange} placeholder="El. paštas" disabled={props.isDisabled}></input>
                        <div className="invalid-feedback">Šis laukas privalomas. E.paštas privalo turėti @ simbolį.</div>
                </div>
                <button className={props.isDisabled === true ? "btn btn-info" : "btn btn-info"} onClick={props.saveGuardian}>{props.guardianButtonText}</button>
                </div>

               <div className={props.messageStyle + " mt-2"}>
               <span>{props.message}</span>
               </div>

               <div className={props.emptyInputsMessageStyle + " mt-2"}>
               <span>{props.emptyInputsMessage}</span>
               </div>


                <div className="row my-3">
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
    message: Proptypes.string.isRequired,
    messageStyle: Proptypes.string.isRequired,
    guardianButtonText: Proptypes.string.isRequired,
    isDisabled: Proptypes.bool.isRequired,
    onDetailsChange: Proptypes.func.isRequired,
    saveGuardian: Proptypes.func.isRequired,
    prev: Proptypes.func.isRequired,
    next: Proptypes.func.isRequired,
    previousButton: Proptypes.func.isRequired,
    nextButton: Proptypes.func.isRequired
}

export default GuardianInfoComponent
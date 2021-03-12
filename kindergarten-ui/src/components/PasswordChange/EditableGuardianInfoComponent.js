import React from 'react';
import Proptypes from 'prop-types';

let GuardianInfoComponent = (props) => {

    return (
        <div>
            <div className="templatemo-content-widget white-bg">
                <h3 className="margin-bottom-10">Duomenų redagavimas</h3>

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
                <div className="text-right">
                    <button className={props.isDisabled === true ? "templatemo-blue-button" : "templatemo-blue-button"} onClick={props.saveGuardian}>{props.guardianButtonText}</button>
                </div>
            </div>

            <div className={props.messageStyle + " mt-2"}>
                <span>{props.message}</span>
            </div>

            <div className={props.emptyInputsMessageStyle + " mt-2"}>
                <span>{props.emptyInputsMessage}</span>
            </div>

        </div>

    )




}

GuardianInfoComponent.propTypes = {

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
}

export default GuardianInfoComponent
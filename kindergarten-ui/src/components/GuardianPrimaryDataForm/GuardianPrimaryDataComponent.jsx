import React from 'react';
import Proptypes from 'prop-types'

const GuardianPrimaryDataFormComponent = ({
    firstname,
    lastname,
    personalCode,
    phoneNo,
    address,
    postalCode,
    email,
    firstnameValidation,
    lastnameValidation,
    personalCodeValidation,
    phoneNoValidation,
    addressValidation,
    cityValidation,
    postalCodeValidation,
    emailValidation,
    handleSaveGuardianInfo,
    handleChange,
    message,
    messageStyle
}) => {
    return (
        <div className="col-12 clearfix mb-3">
            <form onSubmit={handleSaveGuardianInfo}>
                <div className="form-group row">
                    <label htmlFor="firstname" className="col-3 pt-2 text-right">
                        Vardas <span className="mandatory">*</span>
                    </label>
                    <input
                        type="text"
                        className={`form-control col-9 ${firstnameValidation}`}
                        placeholder="Pvz.: Ona Janina"
                        id="firstname"
                        value={firstname}
                        name="firstname"
                        onChange={handleChange}
                    />
                    <div className="invalid-feedback offset-3 col-9">Šis laukas privalomas. Vardo ilgis turi būti 2-30 ženklų. Atskirus vardus atskirkite tarpu</div>
                </div>
                <div className="form-group row">
                    <label htmlFor="lastname" className="col-3 pt-2 text-right">
                        Pavardė <span className="mandatory">*</span>
                    </label>
                    <input
                        type="text"
                        className={`form-control col-9 ${lastnameValidation}`}
                        placeholder="Pvz.: Petraitytė-Jonaitienė"
                        id="lastname"
                        value={lastname}
                        name="lastname"
                        onChange={handleChange}
                    />
                    <div className="invalid-feedback offset-3 col-9">Šis laukas privalomas. Vardo ilgis turi būti 2-30 ženklų. Atskiras pavardes atskirkite brūkšniu</div>
                </div>
                <div className="form-group row">
                    <label htmlFor="personalCode" className="col-3 pt-2 text-right">
                        Asmens kodas <span className="mandatory">*</span>
                    </label>
                    <input
                        type="number"
                        className={`form-control col-9 ${personalCodeValidation}`}
                        placeholder="Pvz.: 00000000000"
                        id="personalCode"
                        value={personalCode}
                        name="personalCode"
                        onChange={handleChange}
                    />
                    <div className="invalid-feedback offset-3 col-9">Šis laukas privalomas. Asmens kodas turi būti 11 skaitmenų.</div>
                </div>
                <div className="form-group row">
                    <label htmlFor="address" className="col-3 pt-2 text-right">
                        Adresas <span className="mandatory">*</span>
                    </label>
                    <input
                        type="text"
                        className={`form-control col-9 ${addressValidation}`}
                        placeholder="Pvz.: Kalvarijų g. 128"
                        id="address"
                        value={address}
                        name="address"
                        onChange={handleChange}
                    />
                    <div className="invalid-feedback offset-3 col-9">Šis laukas privalomas. Adreso ilgis turi būti 8-50 ženklų.</div>
                </div>
                <div className="form-group row">
                    <label htmlFor="city" className="col-3 pt-2 text-right">
                        Miestas <span className="mandatory">*</span>
                    </label>
                    <select
                        className={`form-control col-9 ${cityValidation}`}
                        id="city"
                        name="city"
                        onChange={handleChange}>
                        <option defaultValue >Pasirinkti...</option>
                        <option value="VILNIUS">Vilnius</option>
                        <option value="OTHER">Kita</option>
                    </select>
                    <div className="invalid-feedback offset-3 col-9">Šis laukas privalomas. Pasirinkite miestą.</div>
                </div>
                <div className="form-group row">
                    <label htmlFor="postalCode" className="col-3 pt-2 text-right">
                        Pašto kodas <span className="mandatory">*</span>
                    </label>
                    <input type="text" className="form-control col-1" placeholder="LT-" readOnly />
                    <input
                        type="number"
                        className={`form-control col-8 ${postalCodeValidation}`}
                        id="postalCode"
                        placeholder="00000"
                        value={postalCode}
                        name="postalCode"
                        onChange={handleChange}
                    />
                    <div className="invalid-feedback offset-3 col-9">Šis laukas privalomas. Pašto kodo ilgis turi būti 5 skaitmenų.</div>
                </div>
                <div className="form-group row">
                    <label htmlFor="phoneNo" className="col-3 pt-3 text-right">
                        Tel. numeris <span className="mandatory">*</span>
                    </label>
                    <input type="text" className="form-control col-1 p-1" placeholder="+370" readOnly />
                    <input
                        type="number"
                        className={`form-control col-8 ${phoneNoValidation}`}
                        id="phoneNo"
                        placeholder="60000000"
                        value={phoneNo}
                        name="phoneNo"
                        onChange={handleChange}
                    />
                    <div className="invalid-feedback offset-3 col-9">Šis laukas privalomas. Telefono numeris ilgis turi būti 8 skaitmenų.</div>
                </div>
                <div className="form-group row">
                    <label htmlFor="email" className="col-3 pt-2 text-right">
                        El. paštas <span className="mandatory">*</span>
                    </label>
                    <input
                        type="text"
                        className={`form-control col-9 ${emailValidation}`}
                        id="email"
                        placeholder="pavyzdys@pvz.lt"
                        value={email}
                        name="email"
                        onChange={handleChange}
                    />
                    <div className="invalid-feedback offset-3 col-9">Įveskite elektroninį paštą arba palikite tuščią.</div>
                </div>
                <div className="col-12 row">
                    <div className="offset-3 col-9"><span className="mandatory">*</span> pažymėti laukai privalomi.</div>
                    {<div className={`${messageStyle} offset-4 col-8 mt-4`}>{message}</div>}
                </div>
                <button type="submit" className="btn btn-green float-right">Išsaugoti</button>
            </form>
        </div>
    )
}

GuardianPrimaryDataFormComponent.propTypes = {
    firstname: Proptypes.string.isRequired,
    lastname: Proptypes.string.isRequired,
    personalCode: Proptypes.string.isRequired,
    phoneNo: Proptypes.string.isRequired,
    address: Proptypes.string.isRequired,
    postalCode: Proptypes.string.isRequired,
    email: Proptypes.string.isRequired,
    firstnameValidation: Proptypes.string.isRequired,
    lastnameValidation: Proptypes.string.isRequired,
    personalCodeValidation: Proptypes.string.isRequired,
    phoneNoValidation: Proptypes.string.isRequired,
    addressValidation: Proptypes.string.isRequired,
    cityValidation: Proptypes.string.isRequired,
    postalCodeValidation: Proptypes.string.isRequired,
    emailValidation: Proptypes.string.isRequired,
    handleSaveGuardianInfo: Proptypes.func.isRequired,
    handleChange: Proptypes.func.isRequired,
    message: Proptypes.string.isRequired,
    messageStyle: Proptypes.string.isRequired,
}

export default GuardianPrimaryDataFormComponent;
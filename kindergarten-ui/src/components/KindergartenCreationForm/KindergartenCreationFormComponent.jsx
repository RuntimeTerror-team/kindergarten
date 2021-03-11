import React from "react";
import Proptypes from "prop-types";
import { useHistory } from "react-router-dom";
import urls from "../../constants/urls";

const KindergartenCreationFormComponent = ({
    address,
    email,
    phoneNo,
    postalCode,
    title,
    website,
    districts,
    handleChange,
    companyCode,
    handleSubmit,
    message,
    messageStyle,
    titleValidation,
    companyCodeValidation,
    addressValidation,
    districtValidation,
    postalCodeValidation,
    phoneNoValidation,
    emailValidation,
    websiteValidation
}) => {
    const history = useHistory();
    const goToKindergartenList = () => history.push(`${urls.educationSpecialist.kindergartenBase}`);

    return (
        <div className="templatemo-content-widget white-bg my-4 col-8 mx-auto">
            <form className="templatemo-login-form" onSubmit={handleSubmit}>
                <div className="form-group col-12 px-0">
                    <label className="label-form" htmlFor="title">
                        Pavadinimas <span className="mandatory">*</span></label>
                    <input
                        type="text"
                        className={`form-control ${titleValidation}`}
                        id="title"
                        placeholder="Pvz.: Lopšelis-darželis Voverytė"
                        value={title}
                        name="title"
                        onChange={handleChange}
                    />
                    <div className="invalid-feedback col-12">
                        Šis laukas privalomas. Pavadinimo ilgis turi būti 8-35 ženklų.</div>
                </div>
                <div className="form-group col-12 px-0">
                    <label className="label-form" htmlFor="companyCode">
                        Įmonės kodas <span className="mandatory">*</span></label>
                    <input
                        type="number"
                        className={`form-control ${companyCodeValidation}`}
                        id="companyCode"
                        placeholder="Pvz.: 123456789"
                        value={companyCode}
                        name="companyCode"
                        onChange={handleChange}
                    />
                    <div className="invalid-feedback">
                        Šis laukas privalomas. Įmonės kodo ilgis turi būti 7 arba 9 skaitmenų.</div>
                </div>
                <div className="form-group col-12 px-0">
                    <label className="label-form" htmlFor="address">
                        Adresas <span className="mandatory">*</span></label>
                    <input
                        type="text"
                        className={`form-control ${addressValidation}`}
                        id="address"
                        placeholder="Pvz.: Kalvarijų g. 128"
                        value={address}
                        name="address"
                        onChange={handleChange}
                    />
                    <div className="invalid-feedback">
                        Šis laukas privalomas. Adreso ilgis turi būti 8-50 ženklų.</div>
                </div>
                <div className="row">
                    <div className="form-group col-6">
                        <label className="label-form" htmlFor="postalCode">
                            Pašto kodas <span className="mandatory">*</span></label>
                        <div className="row col-12 px-0 mx-0">
                            <input type="text" className="form-control col-2" placeholder="LT-" readOnly />
                            <input
                                type="number"
                                className={`form-control col ${postalCodeValidation}`}
                                id="postalCode"
                                placeholder="00000"
                                value={postalCode}
                                name="postalCode"
                                onChange={handleChange}
                            />
                            <div className="invalid-feedback">
                                Šis laukas privalomas. Pašto kodo ilgis turi būti 5 skaitmenų.</div>
                        </div>
                    </div>
                    <div className="form-group col-6">
                        <label className="label-form" htmlFor="city">
                            Miestas</label>
                        <input
                            type="text"
                            className="form-control"
                            id="city"
                            value="Vilnius"
                            name="city"
                            readOnly
                        />
                    </div>
                </div>
                <div className="form-group col-12 px-0">
                    <label className="label-form" htmlFor="district">
                        Rajonas <span className="mandatory">*</span></label>
                    <select
                        id="district"
                        className={`form-control ${districtValidation}`}
                        name="district"
                        onChange={handleChange}>
                        <option defaultValue>Pasirinkti...</option>
                        {districts.map(({ title, id }) => {
                            return (
                                <option key={id} value={`${title},${id}`}>
                                    {title}
                                </option>
                            );
                        })}
                    </select>
                    <div className="invalid-feedback">
                        Šis laukas privalomas. Pasirinkite rajoną.</div>
                </div>
                <div className="form-group col-12 px-0">
                    <label className="label-form" htmlFor="phoneNo">
                        Tel. numeris <span className="mandatory">*</span></label>
                    <div className="row col-12 px-0 mx-0">
                        <input type="text" className="form-control col-1 p-1" placeholder="+370" readOnly />
                        <input
                            type="number"
                            className={`form-control col ${phoneNoValidation}`}
                            id="phoneNo"
                            placeholder="60000000"
                            value={phoneNo}
                            name="phoneNo"
                            onChange={handleChange}
                        />
                        <div className="invalid-feedback">Šis laukas privalomas. Telefono numeris ilgis turi būti 8 skaitmenų.</div>
                    </div>
                </div>
                <div className="form-group col-12 px-0">
                    <label className="label-form" htmlFor="email">
                        El. paštas</label>
                    <input
                        type="text"
                        className={`form-control ${emailValidation}`}
                        id="email"
                        placeholder="pavyzdys@pvz.lt"
                        value={email}
                        name="email"
                        onChange={handleChange}
                    />
                    <div className="invalid-feedback">Įveskite elektroninį paštą arba palikite tuščią.</div>
                </div>
                <div className="form-group col-12 px-0">
                    <label className="label-form" htmlFor="website">
                        Interneto svetainė</label>
                    <input
                        type="text"
                        className={`form-control ${websiteValidation}`}
                        id="website"
                        placeholder="www.pavyzdys.lt"
                        value={website}
                        name="website"
                        onChange={handleChange}
                    />
                    <div className="invalid-feedback">Įveskite svetainės adresą arba palikite tuščią.</div>
                </div>
                <div className="form-group"><span className="mandatory">*</span> pažymėti laukai privalomi.</div>
                <div className={`${messageStyle}`}>
                    {message}
                </div>
                <div className="form-group text-right">
                    <button type="button" className="templatemo-blue-button mr-2" onClick={goToKindergartenList}>
                        Grįžti į darželių sąrašą</button>
                    <button type="submit" className="templatemo-blue-button">
                        Išsaugoti</button>
                </div>
            </form>
        </div>
    );
};

KindergartenCreationFormComponent.propsTypes = {
    address: Proptypes.string.required,
    email: Proptypes.string.required,
    postalCode: Proptypes.string.required,
    title: Proptypes.string.required,
    website: Proptypes.string.required,
    companyCode: Proptypes.string.required,
    district: Proptypes.object.required,
    phoneNo: Proptypes.string.required,
    districts: Proptypes.array.isRequired,
    handleChange: Proptypes.func.isRequired,
    handleSubmit: Proptypes.func.isRequired,
    message: Proptypes.string.isRequired,
    messageStyle: Proptypes.string.isRequired,
    titleValidation: Proptypes.string.isRequired,
    companyCodeValidation: Proptypes.string.isRequired,
    addressValidation: Proptypes.string.isRequired,
    districtValidation: Proptypes.string.isRequired,
    postalCodeValidation: Proptypes.string.isRequired,
    phoneNoValidation: Proptypes.string.isRequired,
    emailValidation: Proptypes.string.isRequired,
    websiteValidation: Proptypes.string.isRequired,
}

export default KindergartenCreationFormComponent;

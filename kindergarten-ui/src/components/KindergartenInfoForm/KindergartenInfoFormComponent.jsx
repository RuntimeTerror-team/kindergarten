import React from "react";
import Proptypes from "prop-types";
import { useHistory } from "react-router-dom";
import urls from "../../constants/urls";
import Input from "../common/Input";

const KindergartenInfoFormComponent = ({
  districts,
  kindergarten,
  errors,
  handleChange,
  handleSubmit,
  message,
  messageStyle,
  isDisabled,
  toggleDisabled
}) => {
  const history = useHistory();
  const goToKindergartenList = () => history.push(`${urls.educationSpecialist.kindergartenBase}`);

  let { address, email, phoneNumber, postalCode, title, website, companyCode, district, id } = kindergarten;

  let labelStyle = "col-3 pt-2 text-right";
  let inputStyle = "col-9";
  let invalidStyle = "offset-3 col-9";
  let type = "text"

  return (
    <div className="templatemo-content-widget white-bg my-4 col-10 mx-auto">
      <form id={id} className="templatemo-login-form" onSubmit={handleSubmit}>
        <Input
          name="title"
          value={title}
          label="Pavadinimas"
          mandatory={true}
          type={type}
          error={errors.title}
          placeholder="Pvz.: Lopšelis-darželis Voverytė"
          onChange={handleChange}
          errorMessage="Šis laukas privalomas. Pavadinimo ilgis turi būti 8-35 ženklų."
          labelStyle={labelStyle}
          inputStyle={inputStyle}
          invalidStyle={invalidStyle}
          disabled={isDisabled}
        />
        <Input
          name="companyCode"
          value={companyCode}
          label="Įmonės kodas"
          mandatory={true}
          type="number"
          placeholder="Pvz.: 123456789"
          onChange={handleChange}
          errorMessage="Šis laukas privalomas. Įmonės kodo ilgis turi būti 7 arba 9 skaitmenų."
          labelStyle={labelStyle}
          inputStyle={inputStyle}
          invalidStyle={invalidStyle}
          disabled={true}
        />
        <Input
          name="address"
          value={address}
          label="Adresas"
          mandatory={true}
          type={type}
          error={errors.address}
          placeholder="Pvz.: Kalvarijų g. 128"
          onChange={handleChange}
          errorMessage="Šis laukas privalomas. Adreso ilgis turi būti 8-50 ženklų."
          labelStyle={labelStyle}
          inputStyle={inputStyle}
          invalidStyle={invalidStyle}
          disabled={isDisabled}
        />
        <div className="form-group row">
          <label htmlFor="district" className="col-3 pt-2 text-right">
            Rajonas {!isDisabled && <span className="mandatory">*</span>}
          </label>
          <select
            id="district"
            className={`form-control ${inputStyle} ${errors.district}`}
            name="district"
            disabled={isDisabled}
            onChange={handleChange}>
            <option>{district.title}</option>
            {districts.filter(dis => dis.title !== district.title).map(dis => {
              return (
                <option key={dis.id} value={`${dis.title}+${dis.id}`}>
                  {dis.title}
                </option>

              );
            })}
          </select>
          <div className={`invalid-feedback ${invalidStyle}`}>Šis laukas privalomas. Pasirinkite rajoną.</div>
        </div>
        <div className="form-group row">
          <label htmlFor="city" className="col-3 pt-2 text-right">
            Miestas
                    </label>
          <input type="text" className="form-control col-9" id="city" value="Vilnius" name="city" disabled />
        </div>
        <div className="form-group row">
          <label htmlFor="postalCode" className="col-3 pt-2 text-right">
            Pašto kodas {!isDisabled && <span className="mandatory">*</span>}
          </label>
          <input type="text" className="form-control col-1" placeholder="LT-" readOnly />
          <input
            type="number"
            className={`form-control col-8 ${errors.postalCode}`}
            id="postalCode"
            placeholder="00000"
            value={postalCode}
            name="postalCode"
            onChange={handleChange}
            disabled={isDisabled}
          />
          <div className={`invalid-feedback ${invalidStyle}`}>Šis laukas privalomas. Pašto kodo ilgis turi būti 5 skaitmenų.</div>
        </div>
        <div className="form-group row">
          <label htmlFor="phoneNumber" className="col-3 pt-2 text-right">
            Tel. numeris {!isDisabled && <span className="mandatory">*</span>}
          </label>
          <input type="text" className="form-control col-1 p-1" placeholder="+370" readOnly />
          <input
            type="number"
            className={`form-control col-8 ${errors.phoneNumber}`}
            id="phoneNumber"
            placeholder="60000000"
            value={phoneNumber}
            name="phoneNumber"
            onChange={handleChange}
            disabled={isDisabled}
          />
          <div className="col-3"></div>
          <div className="invalid-feedback col-9">Šis laukas privalomas. Telefono numeris ilgis turi būti 8 skaitmenų.</div>
        </div>
        <Input
          name="email"
          value={email}
          label="El. paštas"
          mandatory={false}
          type={type}
          error={errors.email}
          placeholder="pavyzdys@pvz.lt"
          onChange={handleChange}
          errorMessage="Įveskite elektroninį paštą arba palikite tuščią."
          labelStyle={labelStyle}
          inputStyle={inputStyle}
          invalidStyle={invalidStyle}
          disabled={isDisabled}
        />
        <Input
          name="website"
          value={website}
          label="Interneto svetainė"
          mandatory={false}
          type={type}
          error={errors.website}
          placeholder="www.pavyzdys.lt"
          onChange={handleChange}
          errorMessage="Įveskite svetainės adresą arba palikite tuščią."
          labelStyle={labelStyle}
          inputStyle={inputStyle}
          invalidStyle={invalidStyle}
          disabled={isDisabled}
        />
        {!isDisabled
          && <div className="form-group offset-3"><span className="mandatory">*</span> pažymėti laukai privalomi.</div>}
        <div className={`${messageStyle} offset-3 col-9`}>
          {message}
        </div>
      </form>
      <div className="form-group text-right">
        <button type="button" className="templatemo-blue-button mr-2" onClick={goToKindergartenList}>
          Eiti į darželių sąrašą</button>
        {isDisabled
          ? <button type="button" className="templatemo-blue-button" onClick={toggleDisabled} >
            Redaguoti</button>
          : <button type="submit" className="templatemo-blue-button" onClick={handleSubmit}>
            Išsaugoti</button>}
      </div>
    </div>
  );
};

KindergartenInfoFormComponent.propsTypes = {
  kindergarten: Proptypes.object.required,
  district: Proptypes.object.required,
  districts: Proptypes.array.isRequired,
  handleChange: Proptypes.func.isRequired,
  handleSubmit: Proptypes.func.isRequired,
  message: Proptypes.string.isRequired,
  messageStyle: Proptypes.string.isRequired,
  errors: Proptypes.object.isRequired
}

export default KindergartenInfoFormComponent;

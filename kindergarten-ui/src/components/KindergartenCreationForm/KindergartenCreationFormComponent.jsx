import React from 'react';

const KindergartenCreationFormComponent = ({ address, email, phoneNo, postalCode, title, website, districts, handleChange, companyCode, handleSubmit, stopCreatingKindergarten, failMessage, failMessageStyle, titleValidation, companyCodeValidation, addressValidation, districtValidation, postalCodeValidation, phoneNoValidation, emailValidation,websiteValidation }) => {
    return (
        <div className="col-12">
            <form onSubmit={handleSubmit}>
                <div className="form-group row">
                    <label htmlFor="title" className="col-3 pt-2 text-right">Pavadinimas <span className="mandatory">*</span></label>
                    <input type="text" className={`form-control col-9 ${titleValidation}`} id="title" placeholder="Pvz.: Lopšelis-darželis Voverytė" value={title} name="title" onChange={handleChange} />
                    <div className="col-3"></div>
                    <div className="invalid-feedback col-9">
                        Šis laukas privalomas. Pavadinimo ilgis turi būti 8-35 ženklų.
                    </div>
                </div>
                <div className="form-group row">
                    <label htmlFor="companyCode" className="col-3 pt-2 text-right">Įmonės kodas <span className="mandatory">*</span></label>
                    <input type="number" className={`form-control col-9 ${companyCodeValidation}`} id="companyCode" placeholder="Pvz.: 123456789" value={companyCode} name="companyCode" onChange={handleChange} />
                    <div className="col-3"></div>
                    <div className="invalid-feedback col-9">
                        Šis laukas privalomas. Įmonės kodo ilgis turi būti 7 arba 9 ženklų.
                    </div>
                </div>
                <div className="form-group row">
                    <label htmlFor="address" className="col-3 pt-2 text-right">Adresas <span className="mandatory">*</span></label>
                    <input type="text" className={`form-control col-9 ${addressValidation}`} id="address" placeholder="Pvz.: Kalvarijų g. 128" value={address} name="address" onChange={handleChange} />
                    <div className="col-3"></div>
                    <div className="invalid-feedback col-9">
                        Šis laukas privalomas. Adreso ilgis turi būti 8-50 ženklų.
                    </div>
                </div>
                <div className="form-group row">
                    <label htmlFor="district" className="col-3 pt-2 text-right">Rajonas <span className="mandatory">*</span></label>
                    <select id="district" className={`form-control col-9 ${districtValidation}`} name="district" onChange={handleChange} >
                        <option defaultValue>Pasirinkti...</option>
                        {districts.map(({ title, id }) => {
                            return (
                                <option key={id} value={`${title},${id}`}>{title}</option>
                            )
                        })}
                    </select>
                    <div className="col-3"></div>
                    <div className="invalid-feedback col-9">
                        Šis laukas privalomas. Pasirinkite rajoną.
                    </div>
                </div>
                <div className="form-group row">
                    <label htmlFor="city" className="col-3 pt-2 text-right">Miestas</label>
                    <input type="text" className="form-control col-9" id="city" value="Vilnius" name="city" readOnly />
                </div>
                <div className="form-group row">
                    <label htmlFor="postalCode" className="col-3 pt-2 text-right">Pašto kodas <span className="mandatory">*</span></label>
                    <input type="text" className="form-control col-1" placeholder="LT-" readOnly />
                    <input type="number" className={`form-control col-8 ${postalCodeValidation}`} id="postalCode" placeholder="00000" value={postalCode} name="postalCode" onChange={handleChange} />
                    <div className="col-3"></div>
                    <div className="invalid-feedback col-9">
                        Šis laukas privalomas. Pašto kodo ilgis turi būti 5 ženklų.
                    </div>
                </div>
                <div className="form-group row">
                    <label htmlFor="phoneNo" className="col-3 pt-3 text-right">Tel. numeris <span className="mandatory">*</span></label>
                    <input type="text" className="form-control col-1 p-1" placeholder="+370" readOnly />
                    <input type="text" className={`form-control col-8 ${phoneNoValidation}`} id="phoneNo" placeholder="60000000" value={phoneNo} name="phoneNo" onChange={handleChange} />
                    <div className="col-3"></div>
                    <div className="invalid-feedback col-9">
                        Šis laukas privalomas. Įveskite 8 skaitmenis.
                    </div>
                </div>
                <div className="form-group row">
                    <label htmlFor="email" className="col-3 pt-2 text-right">El. paštas</label>
                    <input type="email" className={`form-control col-9 ${emailValidation}`} id="email" placeholder="pavyzdys@pvz.lt" value={email} name="email" onChange={handleChange} />
                    <div className="col-3"></div>
                    <div className="invalid-feedback col-9">
                        Įveskite elektroninį paštą arba palikite tuščią.
                    </div>
                </div>
                <div className="form-group row">
                    <label htmlFor="website" className="col-3 pt-2 text-right">Interneto svetainė</label>
                    <input type="url" className={`form-control col-9 ${websiteValidation}`} id="website" placeholder="www.pavyzdys.lt" value={website} name="website" onChange={handleChange} />
                    <div className="col-3"></div>
                    <div className="invalid-feedback col-9">
                        Įveskite svetainės adresą arba palikite tuščią.
                    </div>
                </div>
                <button type="submit" className="btn btn-info float-right">Išsaugoti</button>
            </form>
            <button className="btn btn-warning float-right mx-2" onClick={stopCreatingKindergarten}>Atšaukti</button>
            <div className="col-3"></div>
            <div className="col-9"><span  className="mandatory">*</span> pažymėti laukai privalomi.</div>
            {<span className={`${failMessageStyle} alertTop`} style={{ width: "30em" }}>{failMessage}</span>}
        </div>
    )
}

export default KindergartenCreationFormComponent;
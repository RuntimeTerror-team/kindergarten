import React from 'react';

const KindergartenInfoFormComponent = ({ kindergarten, resetWantsInfo }) => {
    let {address, email, phoneNumber, postalCode, title, website, companyCode, district} = kindergarten;
    return (
        <div className="col-12 clearfix">
            <h6 className="my-3">Darželio informacija</h6>
            <form>
                <div className="form-group row">
                    <label htmlFor="title" className="col-3 pt-2 text-right">Pavadinimas</label>
                    <input type="text" className="form-control col-9" id="title" name="title" value={title} readOnly />
                </div>
                <div className="form-group row">
                    <label htmlFor="companyCode" className="col-3 pt-2 text-right">Įmonės kodas</label>
                    <input type="number" className="form-control col-9" id="companyCode" name="companyCode" value={companyCode} readOnly/>
                </div>
                <div className="form-group row">
                    <label htmlFor="address" className="col-3 pt-2 text-right">Adresas</label>
                    <input type="text" className="form-control col-9" id="address" value={address} name="address" readOnly/>
                </div>
                <div className="form-group row">
                    <label htmlFor="district" className="col-3 pt-2 text-right">Rajonas</label>
                    <input type="text" className="form-control col-9" id="district" value={district.title} readOnly/>
                </div>
                <div className="form-group row">
                    <label htmlFor="city" className="col-3 pt-2 text-right">Miestas</label>
                    <input type="text" className="form-control col-9" id="city" value="Vilnius" name="city" readOnly />
                </div>
                <div className="form-group row">
                    <label htmlFor="postalCode" className="col-3 pt-2 text-right">Pašto kodas</label>
                    <input type="text" className="form-control col-1" value="LT-" readOnly/>
                    <input type="text" className="form-control col-8" id="postalCode" value={postalCode} readOnly/>
                </div>
                <div className="form-group row">
                    <label htmlFor="phoneNumber" className="col-3 pt-2 text-right">Telefono numeris</label>
                    <input type="text" className="form-control col-9" id="phoneNumber" value={phoneNumber} readOnly/>
                </div>
                <div className="form-group row">
                    <label htmlFor="email" className="col-3 pt-2 text-right">El. paštas</label>
                    <input type="email" className="form-control col-9" id="email" value={email} name="email" readOnly />
                </div>
                <div className="form-group row">
                    <label htmlFor="website" className="col-3 pt-2 text-right">Interneto svetainė</label>
                    <input type="url" className="form-control col-9" id="website" value={website?website:"-"} name="website" readOnly />
                </div>
            </form>
            <button className="btn btn-warning float-right mx-2" onClick={resetWantsInfo}>Atgal</button>
        </div>
    )
}

export default KindergartenInfoFormComponent;
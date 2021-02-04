import React from 'react';

const KindergartenInfoFormComponent = ({resetWantsInfo, street, buildingNo, email, phoneNo, postalCode, title, website, districts, handleChange, companyCode, handleUpdate }) => {
    return (
        <div className="col-12 clearfix">
            <h6 className="my-3">Darželio informacija</h6>
            <form onSubmit={handleUpdate}>
                <div className="form-group row">
                    <label htmlFor="title" className="col-3 pt-2 text-right">Pavadinimas</label>
                    <input type="text" className="form-control col-9" id="title" placeholder="Pvz.: Lopšelis-darželis Voverytė" value={title} name="title" onChange={handleChange} />
                </div>
                <div className="form-group row">
                    <label htmlFor="companyCode" className="col-3 pt-2 text-right">Įmonės kodas</label>
                    <input type="number" className="form-control col-9" id="companyCode" placeholder="Pvz.: 123456789" value={companyCode} name="companyCode" onChange={handleChange} />
                </div>
                <div className="form-group row">
                    <label htmlFor="address" className="col-3 pt-2 text-right">Adresas</label>
                    <input type="text" className="form-control col-7" id="address" placeholder="Pvz.: Kalvarijų g." value={street} name="street" onChange={handleChange} />
                    <input type="number" min="1" className="form-control col-2" placeholder="Namo nr." value={buildingNo} name="buildingNo" onChange={handleChange} />
                </div>
                <div className="form-group row">
                    <label htmlFor="district" className="col-3 pt-2 text-right">Rajonas</label>
                    <select id="district" className="form-control col-9" name="district" onChange={handleChange} >
                        <option defaultValue>Pasirinkti...</option>
                        {districts.map(({ title, id }) => {
                            return (
                                <option key={id} value={`${title},${id}`}>{title}</option>
                            )
                        })}
                    </select>
                </div>
                <div className="form-group row">
                    <label htmlFor="city" className="col-3 pt-2 text-right">Miestas</label>
                    <input type="text" className="form-control col-9" id="city" value="Vilnius" name="city" readOnly />
                </div>
                <div className="form-group row">
                    <label htmlFor="postalCode" className="col-3 pt-2 text-right">Pašto kodas</label>
                    <input type="text" className="form-control col-1" placeholder="LT-" readOnly />
                    <input type="number" className="form-control col-8" id="postalCode" placeholder="00000" value={postalCode} name="postalCode" onChange={handleChange} />
                </div>
                <div className="form-group row">
                    <label htmlFor="phoneNo" className="col-3 pt-3 text-right">Tel. numeris</label>
                    <input type="text" className="form-control col-1 p-1" placeholder="+370" readOnly />
                    <input type="text" className="form-control col-8" id="phoneNo" placeholder="60000000" value={phoneNo} name="phoneNo" onChange={handleChange} />
                </div>
                <div className="form-group row">
                    <label htmlFor="email" className="col-3 pt-2 text-right">El. paštas</label>
                    <input type="email" className="form-control col-9" id="email" placeholder="pavyzdys@pvz.lt" value={email} name="email" onChange={handleChange} />
                </div>
                <div className="form-group row">
                    <label htmlFor="website" className="col-3 pt-2 text-right">Interneto svetainė</label>
                    <input type="url" className="form-control col-9" id="website" placeholder="www.pavyzdys.lt" value={website} name="website" onChange={handleChange} />
                </div>
                <div class="text-right">
                <button type="submit" className="btn btn-info float-right">Išsaugoti</button>
                </div>
            </form>
            <button className="btn btn-warning float-right mx-2" onClick={resetWantsInfo}>Atšaukti</button>
        </div>
    )
}

export default KindergartenInfoFormComponent;
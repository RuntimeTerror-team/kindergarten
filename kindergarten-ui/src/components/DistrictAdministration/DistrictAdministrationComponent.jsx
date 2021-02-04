import React from "react";
import DistrictTableComponent from '../DistrictTable/DistrictTableComponent'

import '../../styles/districtAdministration.css'

const DistrictAdministrationComponent = ({ districts, addDistrict, titleValidation, districtName, onCreatingDistrictNameChange, ...props }) => {
    return (
        <div id="districtAdministrationComponent">
            <h1>Rajonų administravimas</h1>
            <form className="form-inline mt-4 text-center" onSubmit={addDistrict}>
                <div className={`input-group mx-auto ${titleValidation}`}>
                    <input type="text" className="form-control" id="inputDistrict" placeholder="Pavadinimas" name="districtName" value={districtName} onChange={onCreatingDistrictNameChange} />
                    <div class="input-group-append">
                        <button type="submit" className="btn btn-info">Pridėti</button>
                    </div>
                </div>
                <div className="invalid-feedback">
                    Pavadinimo ilgis turi būti 5-20 ženklų.
                </div>
            </form>
            <div className="row">
                    {<span className={props.messageStyle} style={{width:"23em"}}>{props.requestMessage}</span>}
                    </div>
            {districts.length > 0
                &&
                <DistrictTableComponent
                    districts={districts}
                    {...props}
                />}
        </div>
    )
}

export default DistrictAdministrationComponent;
import React from "react";
import DistrictTableComponent from '../DistrictTable/DistrictTableComponent'

import '../../styles/districtAdministration.css'

const DistrictAdministrationComponent = ({ districts, addDistrict, startUpdate }) => {
    return (
        <div id="districtAdministrationComponent">
            <h1>Rajonų administravimas</h1>
            <form class="form-inline mt-4" onSubmit={addDistrict}>
                <label htmlFor="inputDistrict" className="sr-only">Pavadinimas</label>
                <input type="text" className="form-control" id="inputDistrict" placeholder="Pavadinimas" name="districtName" />
                <button type="submit" className="btn btn-info float-right ml-1">Pridėti</button>
            </form>
            {districts.length > 0 && <DistrictTableComponent districts={districts} startUpdate={startUpdate} />}
        </div>
    )
}

export default DistrictAdministrationComponent;
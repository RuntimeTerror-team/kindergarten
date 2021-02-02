import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPencilAlt } from '@fortawesome/free-solid-svg-icons';
import Proptypes from 'prop-types';

const DistrictTableComponent = ({ districts, updateDistrict, startUpdate, updatingId, onDistrictNameChange, updatingTitle, titleValidationInUpdate }) => {

    return (
        <div>
            <h2 className="my-4">Rajonai</h2>

            <table className='table'>
                <thead>
                    <tr>
                        <th scope='col'>#</th>
                        <th scope='col'>Pavadinimas</th>
                    </tr>
                </thead>

                <tbody>
                    {districts.map(({ id, title }, index) =>
                        <tr key={id}>
                            <th scope='row'>{index + 1}</th>
                            <td>
                                {+updatingId !== +id && <button href="#" className='btn btn-link mr-3' id={id} onClick={startUpdate} value={title}>{title} <FontAwesomeIcon icon={faPencilAlt} />
                                </button>}
                                {+updatingId === +id
                                    && <form onSubmit={updateDistrict}>
                                        <div className={`input-group ${titleValidationInUpdate}`}>
                                            <input type="text" className="form-control" id={id} defaultValue={title} onChange={onDistrictNameChange} name='districtTitle' value={updatingTitle} />
                                            <button type="submit" className="btn btn-info float-right ml-1">Išsaugoti</button>
                                        </div>
                                        <div className="invalid-feedback">
                                            Pavadinimo ilgis turi būti 5-20 ženklų.
                                        </div>
                                    </form>}
                            </td>
                        </tr>
                    )}
                </tbody>
            </table>
        </div>
    )
}

DistrictTableComponent.propTypes = {
    districts: Proptypes.array.isRequired
}

export default DistrictTableComponent;
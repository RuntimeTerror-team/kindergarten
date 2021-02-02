import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPencilAlt } from '@fortawesome/free-solid-svg-icons';

const DistrictTableComponent = ({ districts, startUpdate }) => {
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
                    {districts.map(({ id, title }, index) => {
                        return (
                            <tr key={id}>
                                <th scope='row'>{index + 1}</th>
                                <td>
                                    <button href="#" className='btn btn-link mr-3' name={id} onClick={startUpdate}>{title} <FontAwesomeIcon icon={faPencilAlt} />
                                    </button>
                                </td>
                            </tr>
                        )
                    })}
                </tbody>
            </table>
        </div>
    )
}

export default DistrictTableComponent;
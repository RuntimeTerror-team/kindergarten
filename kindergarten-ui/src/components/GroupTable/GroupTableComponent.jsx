import React from 'react'
import Proptypes from 'prop-types';
import { Link } from 'react-router-dom';
import urls from '../../constants/urls';

const GroupTableComponent = ({ groups, kindergartenId, buttonStatus }) => {
    return (
        <div className="col-12 mt-3 text-center">
            <table id="groupTable" className='table col-12'>
                <thead>
                    <tr>
                        <th scope='col' style={{ width: "30px" }}>#</th>
                        <th scope='col'>Amžiaus intervalas</th>
                        <th scope='col'>Vaikų skaičius</th>
                        <th scope='col'>Veiksmai</th>
                    </tr>
                </thead>

                <tbody>
                    {groups.map(({ id, childrenCount, ageRange }, index) =>
                        <tr key={id}>
                            <th scope='row'>{index + 1}</th>
                            <td>{ageRange.ageMin} - {ageRange.ageMax}</td>
                            <td>{childrenCount}</td>
                            <td>
                                {buttonStatus !== "Negalima keisti dydžio"
                                    ? <Link className="btn btn-main"
                                        to={`${urls.educationSpecialist.kindergartenBase}/${kindergartenId}/groups/${id}`}>
                                        {buttonStatus}
                                    </Link>
                                    : <button className="btn btn-main" disabled>{buttonStatus}</button>}
                            </td>
                        </tr>
                    )}
                </tbody>
            </table>
        </div>
    )
}

GroupTableComponent.propTypes = {
    groups: Proptypes.array
}

export default GroupTableComponent
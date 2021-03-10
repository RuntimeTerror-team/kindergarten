import React from 'react'
import Proptypes from 'prop-types';
import { Link } from 'react-router-dom';
import urls from '../../constants/urls';

const GroupTableComponent = ({ groups, kindergartenId, buttonStatus }) => {
    return (
        <div className="templatemo-content-widget no-padding col-12 my-4 mx-0">
            <div className="panel panel-default table-responsive">
                <table className="table table-striped table-bordered templatemo-user-table">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Amžiaus intervalas</th>
                            <th>Vaikų skaičius</th>
                            <th>Veiksmai</th>
                        </tr>
                    </thead>
                    <tbody>
                        {groups.map(({ id, childrenCount, ageRange }, index) =>
                            <tr key={id}>
                                <th>{index + 1}</th>
                                <td>{ageRange.ageMin} - {ageRange.ageMax}</td>
                                <td>{childrenCount}</td>
                                <td>
                                    {buttonStatus !== "Negalima keisti dydžio"
                                        ? <Link className="templatemo-blue-button"
                                            to={`${urls.educationSpecialist.kindergartenBase}/${kindergartenId}/groups/${id}`}>
                                            {buttonStatus}
                                        </Link>
                                        : <button className="templatemo-blue-button-disabled" disabled>{buttonStatus}</button>}
                                </td>
                            </tr>
                        )}
                    </tbody>
                </table>
            </div>
        </div>
    )
}

GroupTableComponent.propTypes = {
    groups: Proptypes.array
}

export default GroupTableComponent
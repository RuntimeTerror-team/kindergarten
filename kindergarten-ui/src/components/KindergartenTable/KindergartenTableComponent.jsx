import React from 'react';
import Proptypes from "prop-types";
import { Link } from 'react-router-dom';
import urls from '../../constants/urls'

const KindergartenTableComponent = ({ kindergartens }) => {
    return (
        <div className="templatemo-content-widget no-padding col-12 my-4 mx-0">
            <div className="panel panel-default table-responsive">
                <table className="table table-striped table-bordered templatemo-user-table">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Pavadinimas</th>
                            <th>Adresas</th>
                            <th>Informacija</th>
                        </tr>
                    </thead>
                    <tbody>
                        {kindergartens.map(({ id, title, address }, index) =>
                            <tr key={id}>
                                <th scope='row'>{index + 1}</th>
                                <td>{title}</td>
                                <td>{address}</td>
                                <td>
                                    <Link to={`${urls.educationSpecialist.kindergartenBase}/${id}`} className="templatemo-edit-btn mr-2">
                                        Peržiūrėti
                                </Link>
                                    <Link to={`${urls.educationSpecialist.kindergartenBase}/${id}/groups`} className="templatemo-edit-btn" >Grupės</Link>
                                </td>
                            </tr>
                        )}
                    </tbody>
                </table>
            </div>
        </div>
    )
}

KindergartenTableComponent.propTypes = {
    kindergartens: Proptypes.array.isRequired
}

export default KindergartenTableComponent;
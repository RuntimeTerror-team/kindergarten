import React from 'react';
import Proptypes from "prop-types";
import { Link } from 'react-router-dom';
import urls from '../../constants/urls'

const KindergartenTableComponent = ({ kindergartens }) => {
    return (
        <div className="col-12 mt-3">
            <table id="kindergartenTable" className='table col-12'>
                <thead>
                    <tr>
                        <th scope='col' style={{ width: "30px" }}>#</th>
                        <th scope='col'>Pavadinimas</th>
                        <th scope='col'>Adresas</th>
                        <th scope='col'>Informacija</th>
                    </tr>
                </thead>

                <tbody>
                    {kindergartens.map(({ id, title, address }, index) =>
                        <tr key={id}>
                            <th scope='row'>{index + 1}</th>
                            <td>{title}</td>
                            <td>{address}</td>
                            <td>
                                <Link to={`${urls.educationSpecialist.kindergartenBase}/${id}`} className="btn btn-info mr-2">
                                    Peržiūrėti
                                </Link>
                                <Link to={`${urls.educationSpecialist.kindergartenBase}/${id}/groups`} className="btn btn-info" >Grupės</Link>
                            </td>
                        </tr>
                    )}
                </tbody>
            </table>
        </div>
    )
}

KindergartenTableComponent.propTypes = {
    kindergartens: Proptypes.array.isRequired
}

export default KindergartenTableComponent;
import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faInfo } from '@fortawesome/free-solid-svg-icons';

const KindergartenTableComponent = ({ kindergartens }) => {
    return (
        <div className="col-12 mt-3">
            <table id="kindergartenTable" className='table col-12'>
                <thead>
                    <tr>
                        <th scope='col' style={{ width: "30px" }}>#</th>
                        <th scope='col'>Pavadinimas</th>
                        <th scope='col'>Adresas</th>
                        <th scope='col'></th>
                    </tr>
                </thead>

                <tbody>
                    {kindergartens.map(({ id, title, address }, index) =>
                        <tr key={id}>
                            <th scope='row'>{index + 1}</th>
                            <td>{title}</td>
                            <td>{address}</td>
                            <td><button className="btn btn-info rounded-circle"><FontAwesomeIcon icon={faInfo} /></button></td>
                        </tr>
                    )}
                </tbody>
            </table>
        </div>
    )
}

export default KindergartenTableComponent;
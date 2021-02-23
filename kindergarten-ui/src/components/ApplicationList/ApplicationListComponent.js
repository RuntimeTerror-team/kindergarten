import React from 'react'
import Proptypes from 'prop-types';

const ApplicationListComponent = ({ applications }) => {

    let table = (
        <div className="col-12 mt-3">
            <table id="groupTable" className='table col-12'>
                <thead>
                    <tr>
                        <th scope='col' style={{ width: "30px" }}>#</th>
                        <th scope='col'>Vaiko vardas ir pavardė</th>
                        <th scope='col'>Data</th>
                        <th scope='col'>Statusas</th>
                    </tr>
                </thead>

                <tbody>
                    {applications.map(({ id, childFirstName, childLastName, applicationCreationDate, applicationStatus }, index) =>
                        <tr key={id}>
                            <th scope='row'>{index + 1}</th>
                            <td>{childFirstName + " " + childLastName}</td>
                            <td>{applicationCreationDate}</td>
                            <td>{applicationStatus}</td>
                        </tr>
                    )}
                </tbody>
            </table>
        </div>

    )


    return (

        applications.length === 0 ? <h6 className="text-center">Šiuo metu nesate pateikė jokio prašymo</h6> : table
    )

     
}

ApplicationListComponent.propTypes = {
    groups: Proptypes.array
}

export default ApplicationListComponent
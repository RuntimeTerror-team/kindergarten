import React from 'react'
import Proptypes from 'prop-types';

const ESApprovedApplicationListComponent = ({ applications }) => {

    let table = (
        <div className="col-12 mt-3">
            <table id="groupTable" className='table col-12'>
                <thead>
                    <tr>
                        <th scope='col' style={{ width: "30px" }}>#</th>
                        <th scope='col'>Balas</th>
                        <th scope='col'>Vaiko vardas ir pavardė</th>
                        <th scope='col'>Tevėlio vardas ir pavardė</th>
                        <th scope='col'>Data</th>
                        <th scope='col'>Statusas</th>
                    </tr>
                </thead>

                <tbody>
                    {applications.map(({ id, score, childFirstName, childLastName, parentFirstName, parentLastName, date, status }, index) =>
                        <tr key={id}>
                            <th scope='row'>{index + 1}</th>
                            <td>{score}</td>
                            <td>{childFirstName + " " + childLastName}</td>
                            <td>{parentFirstName + " " + parentLastName}</td>
                            <td>{date}</td>
                            <td>{status}</td>
                        </tr>
                    )}
                </tbody>
            </table>
        </div>

    )


    return (

        applications.length === 0 ? <h6 className="text-center">Šiuo metu nėra surikiuotų prašymų</h6> : table
    )

     
}

ESApprovedApplicationListComponent.propTypes = {
    applications: Proptypes.array
}

export default ESApprovedApplicationListComponent
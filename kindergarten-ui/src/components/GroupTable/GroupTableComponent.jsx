import React from 'react'
import Proptypes from 'prop-types';

const GroupTableComponent = ({ groups }) => {
    return (

        <div className="col-12 mt-3">
            <table id="groupTable" className='table col-12'>
                <thead>
                    <tr>
                        <th scope='col' style={{ width: "30px" }}>#</th>
                        <th scope='col'>Pavadinimas</th>
                        <th scope='col'>Amžiaus intervalas</th>
                        <th scope='col'>Vaikų skaičius</th>
                    </tr>
                </thead>

                <tbody>
                    {groups.map(({ id, title, childrenCount, ageRange }, index) =>
                        <tr key={id}>
                            <th scope='row'>{index + 1}</th>
                            <td>{title}</td>
                            <td>{ageRange.ageMin} - {ageRange.ageMax}</td>
                            <td>{childrenCount}</td>
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
import React from 'react'
import Proptypes from 'prop-types';

const GroupsTableComponent = ({ groups }) => {
    return (

        <div className="col-12 mt-3">
            <h6 className="mt-3">Darželio informacija</h6>
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
                    {groups.map(({ id, title, childrenCount }, index) =>
                        <tr key={id}>
                            <th scope='row'>{index + 1}</th>
                            <td>{title}</td>
                            <td>amzius</td>
                            <td>{childrenCount}</td>
                        </tr>
                    )}
                </tbody>
            </table>
        </div>
    )
}

GroupsTableComponent.propTypes = {
    groups: Proptypes.array.isRequired
}
export default GroupsTableComponent
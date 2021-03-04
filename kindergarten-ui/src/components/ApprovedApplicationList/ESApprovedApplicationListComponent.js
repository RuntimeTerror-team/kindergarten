import React from 'react'
import Proptypes from 'prop-types';

const ESApprovedApplicationListComponent = ({ applications, permission, onStatusChange }) => {

    let table = (
        <div className="col-12 mt-3">
            <table id="groupTable" className='table col-12'>
                <thead>
                    <tr>
                        {/* <th scope='col' style={{ width: "30px" }}>#</th> */}
                        <th scope='col' style={{"width": "60px"}}>Balas</th>
                        <th scope='col' style={{"width": "115px"}}>Vaikas</th>
                        <th scope='col' style={{"width": "115px"}}>Tėvelis</th>
                        <th scope='col' style={{"width": "115px"}}>Data</th>
                        <th scope='col' style={{"width": "115px"}}>Statusas</th>
                        <th scope='col' style={{"width": "115px"}}>Darželis</th>
                        <th scope='col' style={{"width": "115px"}}>Laukimo Nr</th>
                        {permission ? <th scope='col'></th> : null}
                    </tr>
                </thead>

                <tbody>
                    {applications.map(({ id, score, childFirstName, childLastName, parentFirstName, parentLastName, date, status,
                     approvedKindergartenTitle, waitingNumber  }, index) => {

                        if(waitingNumber === null){
                            waitingNumber = "-"
                        }

                        if(approvedKindergartenTitle === null){
                            approvedKindergartenTitle = "-"
                        }
                        
                        return(

                            <tr key={id}>
                            {/* <th scope='row'>{index + 1}</th> */}
                            <td>{score}</td>
                            <td>{childFirstName + " " + childLastName}</td>
                            <td>{parentFirstName + " " + parentLastName}</td>
                            <td>{date}</td>
                            <td>{status}</td>
                            <td>{approvedKindergartenTitle}</td>
                            <td>{waitingNumber}</td>
                            {permission ? <td><button className="btn btn-info" value={id} onClick={onStatusChange}>Atšaukti</button></td> : null}
                        </tr>
                        )

                     }
                    )}
                </tbody>
            </table>
        </div>

    )


    return (

        applications.length === 0 ? <h6 className="text-center">Prašymų registracija nėra sustabdyta</h6> : table
    )

     
}

ESApprovedApplicationListComponent.propTypes = {
    applications: Proptypes.array
}

export default ESApprovedApplicationListComponent
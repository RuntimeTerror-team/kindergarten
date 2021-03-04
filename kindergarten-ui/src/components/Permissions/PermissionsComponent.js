import React from 'react'
import Proptypes from 'prop-types';

const PermissionsComponent = ({ permission, buttonText, changedPermissionMessage, changedPermissionMessageStyle, onPermissionChange }) => {


    // return (

    //     <div>
    //     <button onClick={onPermissionChange}>Change Permission</button><span>{permission ? "Leidimas suteiktas" : "Leidimas nesuteiktas"}</span>
    //     </div>
        
    // )

    return (

        <div className="col-12 mt-3">
            <table id="groupTable" className='table col-12' style={{width: "35em"}}>
                <thead>
                    <tr>
                        <th scope='col'>Pavadinimas</th>
                        <th scope='col'>Statusas</th>
                        <th scope='col'></th>
                    </tr>
                </thead>

                <tbody>
                        <td>{"Prašymų sąrašo redagavimas Švietimo specialistui"}</td>
                        <td>{permission ? "Suteiktas" : "Nesuteiktas"}</td>
                        <td><button className="btn btn-info" onClick={onPermissionChange}>{buttonText}</button></td>
                </tbody>
            </table>

            <div className={changedPermissionMessageStyle + " mt-2"} style={{width : "20em"}}>
            <span>{changedPermissionMessage}</span>
            </div>
        </div>
    )

     
}

export default PermissionsComponent
import React from 'react'

const PermissionsComponent = ({ permission, buttonText, changedPermissionMessage, changedPermissionMessageStyle, onPermissionChange }) => {


    // return (

    //     <div>
    //     <button onClick={onPermissionChange}>Change Permission</button><span>{permission ? "Leidimas suteiktas" : "Leidimas nesuteiktas"}</span>
    //     </div>

    // )

    return (

        <div className="templatemo-content-widget no-padding col-12 mb-4">
            <div className="panel panel-default table-responsive">
                <table className="table table-striped table-bordered templatemo-user-table">
                    <thead>
                        <tr>
                            <td>Pavadinimas</td>
                            <td>Statusas</td>
                            <td></td>
                        </tr>
                    </thead>

                    <tbody>
                        <td><span className="table-text">{"Prašymų sąrašo redagavimas Švietimo specialistui"}</span></td>
                        <td><span className="table-text">{permission ? "Suteiktas" : "Nesuteiktas"}</span></td>
                        <td><button className="templatemo-blue-button" onClick={onPermissionChange}>{buttonText}</button></td>
                    </tbody>
                </table>
            </div>

            <div className={changedPermissionMessageStyle + " mt-2"}>
                <span>{changedPermissionMessage}</span>
            </div>
        </div>
    )


}

export default PermissionsComponent
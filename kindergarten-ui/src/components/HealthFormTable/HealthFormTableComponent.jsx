import React from 'react';

const HealthFormTableComponent = ({ familyFiles, handleDownload }) => (
    <div className="templatemo-content-widget no-padding col-12 my-4 mx-0">
        <div className="panel panel-default table-responsive">
            <table className="table table-striped table-bordered templatemo-user-table">
                <thead>
                    <tr>
                        <th scope='col'>#</th>
                        <th scope='col'>Vaikas</th>
                        <th scope='col'>Data</th>
                        <th scope='col'>Failo pavadinimas</th>
                        <th scope='col'>Veiksmai</th>
                    </tr>
                </thead>

                <tbody>
                    {familyFiles.length > 0
                        && familyFiles.map(({ name, childFullName, date, url }, index) =>
                            <tr key={childFullName} >
                                <th scope='row'>{index + 1}</th>
                                <td>{childFullName}</td>
                                <td>{date}</td>
                                <td>{name}</td>
                                <td><button className="templatemo-edit-btn" value={url} onClick={handleDownload}>Atsisiųsti</button></td>
                            </tr>
                        )}
                </tbody>
            </table>
        </div>
    </div>
)

export default HealthFormTableComponent;
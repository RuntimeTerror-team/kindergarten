import React from 'react';

const HealthFormTableComponent = ({ files, handleDownload }) => (
    <div className="col-12 mt-3 text-center">
        <table id="groupTable" className='table col-12'>
            <thead>
                <tr>
                    <th scope='col' style={{ width: "30px" }}>#</th>
                    <th scope='col'>Vaikas</th>
                    <th scope='col'>Data</th>
                    <th scope='col'>Failo pavadinimas</th>
                    <th scope='col'>Veiksmai</th>
                </tr>
            </thead>

            <tbody>
                {files.map(({ id, name, childFullName, date, url }, index) =>
                    <tr key={id} >
                        <th scope='row'>{index + 1}</th>
                        <td>{childFullName}</td>
                        <td>{date}</td>
                        <td>{name}</td>
                        <td><button className="btn btn-link" value={url} onClick={handleDownload}>Atsisiųsti</button></td>
                    </tr>
                )}
            </tbody>
        </table>
    </div>
)

export default HealthFormTableComponent;
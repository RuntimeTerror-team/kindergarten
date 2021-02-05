import React from 'react';

const KindergartenTableComponent = ({ kindergartens, handleWantsInfo, handleWantsGroups }) => {
    return (
        <div className="col-12 mt-3">
            <table id="kindergartenTable" className='table col-12'>
                <thead>
                    <tr>
                        <th scope='col' style={{ width: "30px" }}>#</th>
                        <th scope='col'>Pavadinimas</th>
                        <th scope='col'>Adresas</th>
                        <th scope='col'>Informacija</th>
                    </tr>
                </thead>

                <tbody>
                    {kindergartens.map(({ id, title, address }, index) =>
                        <tr key={id}>
                            <th scope='row'>{index + 1}</th>
                            <td>{title}</td>
                            <td>{address}</td>
                            <td>
                                <button className="btn btn-info mr-2" id={id} onClick={handleWantsInfo}>Kontaktai</button>
                                <button className="btn btn-info" id={id} onClick={handleWantsGroups}>Grupės</button>
                            </td>
                        </tr>
                    )}
                </tbody>
            </table>
        </div>
    )
}

export default KindergartenTableComponent;
import React from 'react';

const QueueTableComponent = ({ queues }) => {
    console.log(queues);
    return (
        <table className="table col-12 mt-3 fixedTable text-center">
            <thead>
                <tr>
                    <th scope="col" style={{ width: "30px" }}>
                        #
                        </th>
                    <th scope="col">Eilės atidarymas</th>
                    <th scope="col">Registracijos stabdymas</th>
                    <th scope="col">Eilės uždarymas</th>
                    <th scope="col">Statusas</th>
                </tr>
            </thead>
            <tbody>
                {queues.map(({ openingDate, closingDate, registrationClosingDate, status }, index) => (
                    <tr key={index}>
                        <th scope="row">{index + 1}</th>
                        <td>
                            {openingDate ? new Date(openingDate).toLocaleDateString() : "-"}{"\n"}
                            {openingDate ? new Date(openingDate).toLocaleTimeString() : "-"}
                        </td>
                        <td>
                            {registrationClosingDate ? new Date(registrationClosingDate).toLocaleDateString() : "-"}{"\n"}
                            {registrationClosingDate ? new Date(registrationClosingDate).toLocaleTimeString() : "-"}
                        </td>
                        <td>
                            {closingDate ? new Date(closingDate).toLocaleDateString() : "-"}{"\n"}
                            {closingDate ? new Date(closingDate).toLocaleTimeString() : "-"}
                        </td>
                        <td>{status}</td>
                    </tr>
                ))}
            </tbody>
        </table>
    )
}

export default QueueTableComponent;
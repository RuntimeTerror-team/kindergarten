import React from 'react';

const QueueTableComponent = ({ queues }) => {
    console.log(queues);
    return (
        <table className="table col-10 mx-auto mt-3 fixedTable text-center">
            <thead>
                <tr>
                    <th scope="col" style={{ width: "30px" }}>
                        #
                        </th>
                    <th scope="col">Eilės atidarymas</th>
                    <th scope="col">Registracijos stabdymas</th>
                    <th scope="col">Eilės atidarymas</th>
                    <th scope="col">Būsena</th>
                </tr>
            </thead>
            <tbody>
                {queues.map(({ openingDate, closingDate, registrationClosingDate, status }, index) => (
                    <tr key={index}>
                        <th scope="row">{index + 1}</th>
                        <td>
                            {openingDate ? new Date(openingDate).toLocaleDateString() : "-"}<br />
                            {openingDate && new Date(openingDate).toLocaleTimeString()}
                        </td>
                        <td>
                            {registrationClosingDate ? new Date(registrationClosingDate).toLocaleDateString() : "-"}<br />
                            {registrationClosingDate && new Date(registrationClosingDate).toLocaleTimeString()}
                        </td>
                        <td>
                            {closingDate ? new Date(closingDate).toLocaleDateString() : "-"}<br />
                            {closingDate && new Date(closingDate).toLocaleTimeString()}
                        </td>
                        <td>{status === "ACTIVE" ? "Aktyvi" : ""}</td>
                    </tr>
                ))}
            </tbody>
        </table>
    )
}

export default QueueTableComponent;
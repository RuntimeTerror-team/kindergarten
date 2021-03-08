import React from 'react';

const QueueTableComponent = ({ queues }) => {
    return (
        <table className="table col-10 mt-3 fixedTable">
            <thead>
                <tr>
                    <th scope="col" style={{ width: "30px" }}>#</th>
                    <th scope="col">Eilės atidarymas</th>
                    <th scope="col">Registracijos stabdymas</th>
                    <th scope="col">Eilės uždarymas</th>
                    <th scope="col">Būsena</th>
                </tr>
            </thead>
            <tbody>
                {queues.map(({ openingDate, closingDate, registrationClosingDate, status }, index) => (
                    <tr key={index}>
                        <th scope="row">{index + 1}</th>
                        <td>
                            {openingDate ? new Intl.DateTimeFormat('lt-LT', { dateStyle: 'medium', timeStyle: 'short' }).format(new Date(openingDate)) : "-"}
                        </td>
                        <td>
                            {registrationClosingDate ? new Intl.DateTimeFormat('lt-LT', { dateStyle: 'medium', timeStyle: 'short' }).format(new Date(registrationClosingDate)) : "-"}
                        </td>
                        <td>
                            {closingDate ? new Intl.DateTimeFormat('lt-LT', { dateStyle: 'medium', timeStyle: 'short' }).format(new Date(closingDate)) : "-"}
                        </td>
                        <td>{(status === "ACTIVE")
                            ? "Aktyvi"
                            : (status === "LOCKED")
                                ? "Sustabdyta"
                                : "Neaktyvi"}
                        </td>
                    </tr>
                ))}
            </tbody>
        </table>
    )
}

export default QueueTableComponent;
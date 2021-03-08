import React from 'react';

const QueueTableComponent = ({ queues }) => {
    return (
        <div className="templatemo-content-widget no-padding col-12 mb-4 mx-0">
            <div className="panel panel-default table-responsive">
                <table className="table table-striped table-bordered templatemo-user-table">
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
            </div>
        </div>
    )
}

export default QueueTableComponent;
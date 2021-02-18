import React from 'react';
import { FaPencilAlt } from "react-icons/fa";

const EsQueueTableComponent = ({ queues, userRole, toggleUpdate, isUpdating }) => {
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
                    <th scope="col">Būsena</th>
                </tr>
            </thead>
            <tbody>
                {queues.map(({ openingDate, closingDate, registrationClosingDate, status, id }, index) => (
                    <tr key={index}>
                        <th scope="row">{index + 1}</th>
                        <td>
                            {openingDate ? new Date(openingDate).toLocaleDateString() : "-"}<br />
                            {openingDate && new Date(openingDate).toLocaleTimeString()}
                        </td>
                        {!isUpdating
                            && <td key={id} id={id}>
                                {(registrationClosingDate && status === "ACTIVE")
                                    ? <button className="btn btn-link text-primary" onClick={toggleUpdate}>{new Date(registrationClosingDate).toLocaleDateString()} <FaPencilAlt color="#F1CC00" size={20} /></button>
                                    : <button className="btn btn-link text-primary" onClick={toggleUpdate}><FaPencilAlt color="#F1CC00" size={20} /></button>}
                                <br />
                                {registrationClosingDate && new Date(registrationClosingDate).toLocaleTimeString()}
                            </td>}
                        { isUpdating
                            && <td>
                                <input />
                            </td>}
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

export default EsQueueTableComponent;
import React from "react";
import Proptypes from "prop-types";
import Input from "../common/Input";

const EsQueueListComponent = ({
    queues,
    handleChange,
    queue,
    errors,
    toggleUpdate,
    isUpdating,
    handleClosingRegistration,
    message,
    messageStyle,
    handleApprove
}) => {
    const { registrationClosingDt } = queue;
    return (
        <div className="col-12 clearfix mb-3">
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
                        {queues.length > 0
                            &&
                            queues.filter(q => q.status === "ACTIVE").length > 0
                            &&
                            <th scope="col">Veiksmai</th>}
                    </tr>
                </thead>

                {queues.length > 0 &&
                    <tbody>
                        {queues.map(({ openingDate, closingDate, registrationClosingDate, status, id }, index) => (
                            <tr key={id}>
                                <th scope="row">{index + 1}</th>
                                <td>
                                    {openingDate ? new Date(openingDate).toLocaleDateString() : "-"}<br />
                                    {openingDate && new Date(openingDate).toLocaleTimeString()}
                                </td>
                                {!isUpdating
                                    && <td>
                                        {registrationClosingDate ? new Date(registrationClosingDate).toLocaleDateString() : "-"}<br />
                                        {registrationClosingDate && new Date(registrationClosingDate).toLocaleTimeString()}
                                    </td>}
                                {(isUpdating && status === "ACTIVE")
                                    && <td>
                                        <Input
                                            type="datetime-local"
                                            onChange={handleChange}
                                            inputStyle="col-11"
                                            error={errors.registrationClosingDt}
                                            name="registrationClosingDt"
                                            value={registrationClosingDt}
                                            errorMessage="Šis laukas privalomas"
                                        />
                                    </td>}
                                {(isUpdating && status !== "ACTIVE")
                                    && <td>
                                        {registrationClosingDate ? new Date(registrationClosingDate).toLocaleDateString() : "-"}<br />
                                        {registrationClosingDate && new Date(registrationClosingDate).toLocaleTimeString()}
                                    </td>}
                                {closingDate
                                    ? <td>
                                        {closingDate && new Date(closingDate).toLocaleDateString()}<br />
                                        {closingDate && new Date(closingDate).toLocaleTimeString()}
                                    </td>
                                    : status === "LOCKED"
                                        ? <td>
                                            <button className="btn btn-red" id={id} onClick={handleApprove}>Uždaryti</button>
                                        </td>
                                        : <td>-</td>}
                                <td>{(status === "ACTIVE")
                                    ? "Aktyvi"
                                    : (status === "LOCKED")
                                        ? "Sustabdyta"
                                        : "Neaktyvi"}
                                </td>
                                {status === "ACTIVE"
                                    && !isUpdating
                                    && <td className="pl-1" id={id}>
                                        <button className="btn btn-small btn-green p-1" id={id} onClick={toggleUpdate}>Keisti</button>
                                    </td>}
                                {status === "ACTIVE"
                                    && isUpdating
                                    && <td className="pl-1 pt-1" id={id}>
                                        <button className="btn btn-small btn-yellow p-1 mb-1" id={id} onClick={toggleUpdate}>Atšaukti</button> <br />
                                        <button className="btn btn-small btn-green p-1" id={id} onClick={handleClosingRegistration}>Saugoti</button>
                                    </td>}
                            </tr>
                        ))}
                    </tbody>}
            </table>
            {message
                && <span className={`float-right mr-2 ${messageStyle}`} style={{ width: "23em" }}>
                    {message}
                </span>}
        </div >
    );
};

EsQueueListComponent.propTypes = {
    queues: Proptypes.array.isRequired,
    handleChange: Proptypes.func.isRequired,
    queue: Proptypes.object.isRequired,
    errors: Proptypes.object.isRequired
};

export default EsQueueListComponent;

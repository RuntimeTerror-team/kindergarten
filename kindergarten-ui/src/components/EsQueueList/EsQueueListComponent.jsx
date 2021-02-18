import React from "react";
import Proptypes from "prop-types";
import { FaPencilAlt } from "react-icons/fa";
import { FaSave } from "react-icons/fa";
import Input from "../common/Input";

const EsQueueListComponent = ({
    queues,
    handleChange,
    queue,
    errors,
    toggleUpdate,
    isUpdating,
    handleUpdate,
    message,
    messageStyle
}) => {
    const { registrationClosingDt, closingDt } = queue;
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
                        {queues.length > 0 && <th scope="col" style={{ width: "30px" }}></th>}
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
                                {!isUpdating
                                    && <td>
                                        {closingDate ? new Date(closingDate).toLocaleDateString() : "-"}<br />
                                        {closingDate && new Date(closingDate).toLocaleTimeString()}
                                    </td>}
                                {isUpdating
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
                                {isUpdating
                                    && <td>
                                        <Input
                                            type="datetime-local"
                                            onChange={handleChange}
                                            inputStyle="col-11"
                                            error={errors.closingDt}
                                            name="closingDt"
                                            value={closingDt}
                                            errorMessage="Šis laukas privalomas"
                                        />
                                    </td>}
                                <td>{status === "ACTIVE" ? "Aktyvi" : ""}</td>
                                {status === "ACTIVE" && !isUpdating && <td className="pl-1" id={id}><FaPencilAlt id={id} onClick={toggleUpdate} color="#F1CC00" size={20} /></td>}
                                {status === "ACTIVE" && isUpdating && <td className="pl-1" id={id}><FaSave id={id} onClick={handleUpdate} color="#F1CC00" size={20} /></td>}
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

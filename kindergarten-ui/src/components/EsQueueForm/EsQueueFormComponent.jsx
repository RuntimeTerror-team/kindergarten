import React from 'react';
import { FaPencilAlt } from "react-icons/fa";

const EsQueueFormComponent = ({ queues, userRole, toggleUpdate, isUpdating }) => {
    return (
        <form className="col-12 form-inline text-center" id={id} onSubmit={handleUpdate}>
            <div className={`col-8 input-group mx-auto ${errors.registrationClosingDate}`}>
                <input
                    type="datetime-local"
                    className="form-control"
                    id="registrationClosingDate"
                    name="registrationClosingDate"
                    value={registrationClosingDate}
                    onChange={handleChange}
                />
                <div className="input-group-append">
                    <button type="submit" className="btn btn-green">
                        Išsaugoti
                            </button>
                </div>
            </div>
            <div className="invalid-feedback">Šis laukas privalomas.</div>
            <span className={esMessageStyle} style={{ width: "23em" }}>
                {esMessage}
            </span>
        </form>
    )
}

export default EsQueueFormComponent;
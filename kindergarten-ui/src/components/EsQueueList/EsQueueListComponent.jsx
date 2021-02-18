import React from "react";
import Proptypes from "prop-types";
import Input from "../common/Input";
import EsQueueTableComponent from "../EsQueueTable/EsQueueTableComponent";
import EsQueueFormComponent from "../EsQueueForm/EsQueueFormComponent";

const EsQueueListComponent = ({
    queues,
    handleSubmit,
    handleChange,
    toggleCreation,
    isCreating,
    queue,
    errors,
    userRole,
    toggleUpdate,
    isUpdating,
    handleUpdate,
    esMessage,
    esMessageStyle,
    message,
    messageStyle
}) => {
    const { openingDate, registrationClosingDate, id } = queue;
    return (
        <div className="col-12 clearfix mb-3">
            {!isCreating
                && userRole === "ROLE_ADMIN"
                &&
                <div className="col text-center">
                    <button className="btn btn-green mx-auto" onClick={toggleCreation}>Sukurti naują eilę</button>
                </div>}
            {isCreating
                &&
                <div className="clearfix">
                    <form onSubmit={handleSubmit}>
                        <Input
                            name="openingDate"
                            value={openingDate}
                            label="Eilės atsidarymo data ir laikas"
                            mandatory={false}
                            type="datetime-local"
                            error={errors.openingDate}
                            placeholder=""
                            onChange={handleChange}
                            errorMessage="Šis laukas privalomas."
                            labelStyle="col-5 pt-2 text-right"
                            inputStyle="col-7"
                            invalidStyle="offset-3 col-9"
                        />
                        <span className={messageStyle} style={{ width: "23em" }}>
                            {message}
                        </span>
                        <button className="btn btn-green float-right">Išsaugoti</button>
                    </form>
                    <button className="btn btn-yellow float-right mr-2" onClick={toggleCreation}>Baigti kūrimą</button>
                </div>}
            {queues.length > 0
                && <EsQueueTableComponent
                    queues={queues}
                    userRole={userRole}
                    toggleUpdate={toggleUpdate}
                    isUpdating={isUpdating}
                />}
            {isUpdating
                &&
                <EsQueueFormComponent />
            }
        </div>
    );
};

EsQueueListComponent.propTypes = {
    queues: Proptypes.array.isRequired,
    handleChange: Proptypes.func.isRequired,
    queue: Proptypes.object.isRequired,
    errors: Proptypes.object.isRequired
};

export default EsQueueListComponent;

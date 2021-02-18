import React from "react";
import Proptypes from "prop-types";
import Input from "../common/Input";
import QueueTableComponent from "../QueueTable/QueueTableComponent";

const QueueListComponent = ({
    queues,
    handleSubmit,
    handleChange,
    toggleCreation,
    isCreating,
    queue,
    errors,
    message,
    messageStyle
}) => {
    const { openingDate } = queue;
    return (
        <div className="col-12 clearfix mb-3">
            {!isCreating &&
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
                        <button className="btn btn-green float-right">Išsaugoti</button>
                    </form>
                    <button className="btn btn-yellow float-right mr-2" onClick={toggleCreation}>Baigti kūrimą</button>
                    <span className={`float-right mr-2 ${messageStyle}`} style={{ width: "23em" }}>
                        {message}
                    </span>
                </div>}
            {queues.length > 0 && <QueueTableComponent queues={queues} />}
        </div>
    );
};

QueueListComponent.propTypes = {
    queues: Proptypes.array.isRequired,
    handleSubmit: Proptypes.func.isRequired,
    handleChange: Proptypes.func.isRequired,
    toggleCreation: Proptypes.func.isRequired,
    isCreating: Proptypes.bool.isRequired,
    queue: Proptypes.object.isRequired,
    errors: Proptypes.object.isRequired
};

export default QueueListComponent;

import React from "react";
import Proptypes from "prop-types";
import QueueTableComponent from "../QueueTable/QueueTableComponent";

const QueueListComponent = ({
    queues,
    handleSubmit,
    isActiveQueue,
    message,
    messageStyle
}) => {
    return (
        <div className="col-12 clearfix mb-3 row offset-1">
            {  !isActiveQueue &&
                <div className="offset-1 col-4">
                    <button className="btn btn-green mx-auto" onClick={handleSubmit}>Pradėti naują eilę</button>
                </div>}
            { isActiveQueue
                && <div className="alert alert-warning text-center col-10" role="alert">
                    Galite kurti naują eilę, kai eilės yra neaktyvios.
              </div>}
            {message
                && <div className={`float-right col-12 text-center ${messageStyle}`} style={{ width: "23em" }}>
                    {message}
                </div>}
            {queues.length > 0 && <QueueTableComponent queues={queues} />}
        </div>
    );
};

QueueListComponent.propTypes = {
    queues: Proptypes.array.isRequired,
    handleSubmit: Proptypes.func.isRequired,
    isActiveQueue: Proptypes.bool.isRequired
};

export default QueueListComponent;

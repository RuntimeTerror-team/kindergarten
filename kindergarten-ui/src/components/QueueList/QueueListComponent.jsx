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
        <div>
            {  !isActiveQueue &&
                <button className="templatemo-blue-button" onClick={handleSubmit}>Pradėti naują eilę</button>}
            { isActiveQueue
                && <div className="alert alert-warning text-center" role="alert">
                    Galite kurti naują eilę, kai eilės yra neaktyvios.
              </div>}
            {message
                && <div className={`text-center ${messageStyle}`}>
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

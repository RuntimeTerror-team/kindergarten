import React from 'react';

const PageTopComponent = ({ handleLogout, usersName }) => {
    return (
        <div className="container-fluid p-4">
            <div className="container top-line">
                <p className="lead">{usersName}</p>
                <button className="btn btn-info" onClick={handleLogout}>Atsijungti</button>
            </div>
        </div>
    )
}

export default PageTopComponent;


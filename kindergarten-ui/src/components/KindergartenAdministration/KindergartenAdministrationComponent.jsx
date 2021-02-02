import React from 'react';

const KindergartenAdministrationComponent = ({ kindergartens, isCreatingKindergarten, startCreatingKindergarten, stopCreatingKindergarten }) => {
    return (
        <div>
            <h1 className="mb-4">Darželių administravimas</h1>
            <button className="btn btn-info float-left" onClick={startCreatingKindergarten}>Pridėti</button>
            <button className="btn btn-info float-left" onClick={stopCreatingKindergarten}>Pridėti</button>
            {kindergartens.length > 0 && <p>List</p>}
            {isCreatingKindergarten && <p>Creating Form</p>}
        </div>
    )
}

export default KindergartenAdministrationComponent;
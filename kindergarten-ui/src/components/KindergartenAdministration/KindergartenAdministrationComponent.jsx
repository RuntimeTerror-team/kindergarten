import React from 'react';
import KindergartenCreationFormContainer from '../KindergartenCreationForm/KindergartenCreationFormContainer';

const KindergartenAdministrationComponent = ({ kindergartens, isCreatingKindergarten, startCreatingKindergarten, stopCreatingKindergarten }) => {
    return (
        <div className="col-12">
            <h1 className="mb-4">Darželių administravimas</h1>
            {!isCreatingKindergarten && <button className="btn btn-info text-left" onClick={startCreatingKindergarten}>Pridėti naują darželį</button>}
            {kindergartens.length > 0 && !isCreatingKindergarten && <p>List</p>}
            {isCreatingKindergarten && <KindergartenCreationFormContainer />}
            {isCreatingKindergarten && <button className="btn btn-info float-right" onClick={stopCreatingKindergarten}>Išsaugoti</button>}
        </div>
    )
}

export default KindergartenAdministrationComponent;
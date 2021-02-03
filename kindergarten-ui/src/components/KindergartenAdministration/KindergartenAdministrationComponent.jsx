import React from 'react';
import KindergartenCreationFormContainer from '../KindergartenCreationForm/KindergartenCreationFormContainer';
import KindergartenTableComponent from '../KindergartenTable/KindergartenTableComponent';

const KindergartenAdministrationComponent = ({ kindergartens, isCreatingKindergarten, startCreatingKindergarten, stopCreatingKindergarten }) => {
    return (
        <div className="col-12">
            <h1 className="mb-4">Darželių administravimas</h1>
            {!isCreatingKindergarten && <button className="btn btn-info text-left" onClick={startCreatingKindergarten}>Pridėti naują darželį</button>}
            {kindergartens.length > 0 && !isCreatingKindergarten && <KindergartenTableComponent
                    kindergartens={kindergartens}
                />}
            {isCreatingKindergarten && <KindergartenCreationFormContainer stopCreatingKindergarten={stopCreatingKindergarten} />}
        </div>
    )
}

export default KindergartenAdministrationComponent;
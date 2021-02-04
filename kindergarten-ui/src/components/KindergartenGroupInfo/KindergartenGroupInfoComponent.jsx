import React from 'react';
import KindergartenGroupCreationComponent from "../KindergartenGroupCreation/KindergartenGroupCreationComponent"

const KindergartenGroupInfoComponent = ({ ageRanges }) => {
    return (
        <div>


            <KindergartenGroupCreationComponent
                ageRanges={ageRanges}
            />
        </div>

    )
}

export default KindergartenGroupInfoComponent;
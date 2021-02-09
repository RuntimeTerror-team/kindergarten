import React from 'react';
import KindergartenGroupCreationComponent from "../KindergartenGroupCreation/KindergartenGroupCreationComponent"
import GroupsTableComponent from '../GroupsTable/GroupsTableComponent'

const KindergartenGroupInfoComponent = ({ ageRanges, groups, wantsCreate, toggleWantsCreate, ...otherProps }) => {
    return (
        <div>
            {!wantsCreate
            && <button className="btn btn-info mb-4" onClick={toggleWantsCreate}>Kurti grupę</button>}
            {wantsCreate
                && <KindergartenGroupCreationComponent
                    ageRanges={ageRanges}
                    {...otherProps}
                />}
                {groups.length > 0 
                && <GroupsTableComponent
                    groups={groups}
                />
                }
        </div>

    )
}

export default KindergartenGroupInfoComponent;
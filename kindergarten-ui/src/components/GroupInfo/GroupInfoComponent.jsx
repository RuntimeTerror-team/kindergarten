import React from 'react';
import GroupTableComponent from '../GroupTable/GroupTableComponent'
import Proptypes from "prop-types";
import { Link } from 'react-router-dom';

const GroupInfoComponent = ({ groups, kindergartenId }) => {
    return (
        <div>
            <div className="text-center"><Link to={`/education-specialist/kindergartens/${kindergartenId}/groups/new`}  className="btn btn-info mb-4">Kurti grupę</Link></div>
                {groups.length > 0 
                && <GroupTableComponent
                    groups={groups}
                />
                }
        </div>

    )
}

GroupInfoComponent.propTypes = {
    groups: Proptypes.array
}

export default GroupInfoComponent;
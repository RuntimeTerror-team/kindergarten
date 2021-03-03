import React from "react";
import GroupTableComponent from "../GroupTable/GroupTableComponent";
import Proptypes from "prop-types";
import { Link } from "react-router-dom";
import urls from "../../constants/urls";

const GroupInfoComponent = ({ groups, kindergartenId, buttonStatus }) => {
  return (
    <div>
      <div className="text-center">
        <Link
          to={`${urls.educationSpecialist.kindergartenBase}/${kindergartenId}/groups/new`}
          className="btn btn-info mb-4">
          Kurti grupę
        </Link>
      </div>

      {groups.length > 0 && <GroupTableComponent groups={groups} kindergartenId={kindergartenId} buttonStatus={buttonStatus} />}
      <Link to={`${urls.educationSpecialist.kindergartenBase}`} className="btn btn-yellow float-right mx-2">
        Atgal
      </Link>
    </div>
  );
};

GroupInfoComponent.propTypes = {
  groups: Proptypes.array.isRequired,
  kindergartenId: Proptypes.string.isRequired,
};

export default GroupInfoComponent;

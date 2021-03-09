import React from "react";
import GroupTableComponent from "../GroupTable/GroupTableComponent";
import Proptypes from "prop-types";
import { Link } from "react-router-dom";
import urls from "../../constants/urls";

const GroupInfoComponent = ({ groups, kindergartenId, buttonStatus }) => {
  return (
    <div>
      <Link
        to={`${urls.educationSpecialist.kindergartenBase}/${kindergartenId}/groups/new`}
        className={`btn btn-info mb-4 offset-1`}>
        Kurti grupę
        </Link>
      {groups.length > 0 && <GroupTableComponent groups={groups} kindergartenId={kindergartenId} buttonStatus={buttonStatus} />}
      <Link to={`${urls.educationSpecialist.kindergartenBase}`} className="btn btn-yellow offset-10 mr-5">
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

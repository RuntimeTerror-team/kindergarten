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
        className="templatemo-blue-button">
        Kurti grupę
        </Link>
      {groups.length > 0 && <GroupTableComponent groups={groups} kindergartenId={kindergartenId} buttonStatus={buttonStatus} />}
      <Link to={`${urls.educationSpecialist.kindergartenBase}`} className="templatemo-blue-button float-right">
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

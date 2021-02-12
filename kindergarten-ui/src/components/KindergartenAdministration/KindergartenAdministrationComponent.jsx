import React from "react";
import KindergartenTableComponent from "../KindergartenTable/KindergartenTableComponent";
import { Link } from "react-router-dom";

const KindergartenAdministrationComponent = ({
  kindergartens,
  handleWantsGroups,
  handleWantsInfo,
  startCreatingKindergarten
}) => {
  return (
    <div className="col-12 text-center">
        <Link to="/education-specialist/kindergartens/new" className="btn btn-yellow text-left" onClick={startCreatingKindergarten}>
          Pridėti naują darželį
        </Link>
      {kindergartens.length > 0 &&
        <KindergartenTableComponent
          kindergartens={kindergartens}
          handleWantsInfo={handleWantsInfo}
          handleWantsGroups={handleWantsGroups}
        />}
    </div>
  );
};

export default KindergartenAdministrationComponent;

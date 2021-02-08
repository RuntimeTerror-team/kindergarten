import React from "react";
import KindergartenCreationFormContainer from "../KindergartenCreationForm/KindergartenCreationFormContainer";
import KindergartenInfoFormContainer from "../KindergartenInfoForm/KindergartenInfoFormContainer";
import KindergartenTableComponent from "../KindergartenTable/KindergartenTableComponent";
import KindergartenGroupInfoContainer from "../KindergartenGroupInfo/KindergartenGroupInfoContainer";

const KindergartenAdministrationComponent = ({
  kindergartens,
  isCreatingKindergarten,
  resetWantsInfo,
  handleWantsGroups,
  handleWantsInfo,
  wantsInfo,
  wantsGroups,
  kindergartenInfoId,
  handleUpdateKindergartenList,
  startCreatingKindergarten,
  stopCreatingKindergarten,
  resetWantsGroups,
}) => {
  return (
    <div className="col-12">
      <h1 className="mb-4">Darželių administravimas</h1>
      {!isCreatingKindergarten && !wantsInfo && !wantsGroups && (
        <button className="btn btn-yellow text-left" onClick={startCreatingKindergarten}>
          Pridėti naują darželį
        </button>
      )}
      {kindergartens.length > 0 && !isCreatingKindergarten && !wantsInfo && !wantsGroups && (
        <KindergartenTableComponent
          kindergartens={kindergartens}
          handleWantsInfo={handleWantsInfo}
          handleWantsGroups={handleWantsGroups}
        />
      )}
      {isCreatingKindergarten && !wantsInfo && !wantsGroups && (
        <KindergartenCreationFormContainer
          handleUpdateKindergartenList={handleUpdateKindergartenList}
          stopCreatingKindergarten={stopCreatingKindergarten}
        />
      )}
      {wantsInfo && (
        <KindergartenInfoFormContainer kindergartenInfoId={kindergartenInfoId} resetWantsInfo={resetWantsInfo} />
      )}
      {wantsGroups && (
        <KindergartenGroupInfoContainer kindergartenInfoId={kindergartenInfoId} resetWantsGroups={resetWantsGroups} />
      )}
    </div>
  );
};

export default KindergartenAdministrationComponent;

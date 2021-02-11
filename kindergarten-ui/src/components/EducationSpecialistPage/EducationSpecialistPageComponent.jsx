import React from "react";
import HeaderComponent from "../Header/HeaderComponent";
import GroupFormContainer from "../AgeRangeForm/AgeRangeFormContainer";
import GreetingComponent from "../Greeting/GreetingComponent";
import KindergartenAdministrationContainer from "../KindergartenAdministration/KindergartenAdministrationContainer";
import Footer from "../Footer/Footer";

const EducationSpecialistPageComponent = ({ handleLogout, handleUserChoice, choice }) => {
  return (
    <div>
      <HeaderComponent handleLogout={handleLogout} />
      <div className="container p-4">
        <div className="row">
          <div className="admin-actions col-4">
            <button className="btn btn-main mb-2 w-100" onClick={handleUserChoice} name="kindergartenList">
              Darželių sąrašas
            </button>
            <button className="btn btn-main mb-2 w-100" onClick={handleUserChoice} name="3">
              Amžiaus grupės
            </button>
          </div>
          <div className="admin-action-placeholder col-8">
            {choice === "greeting" && <GreetingComponent />}
            {choice === "kindergartenList" && <KindergartenAdministrationContainer />}
            {choice === "3" && <GroupFormContainer />}
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
};

export default EducationSpecialistPageComponent;

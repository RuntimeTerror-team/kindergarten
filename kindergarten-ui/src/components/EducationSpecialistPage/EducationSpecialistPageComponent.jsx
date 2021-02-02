import React from "react";
import PageTopComponent from '../PageTop/PageTopComponent';
import GroupFormContainer from '../GroupForm/GroupFormContainer';
import GreetingComponent from "../Greeting/GreetingComponent";
import KindergartenAdministrationContainer from '../KindergartenAdministration/KindergartenAdministrationContainer';

const EducationSpecialistPageComponent = ({ handleLogout, handleUserChoice, choice }) => {
    return (
        <div>
            <PageTopComponent handleLogout={handleLogout} usersName={"Švietimo specialistas"} />
            <div className="container p-4">
                <div className="row">
                    <div className="admin-actions col-4">
                        <button className="btn btn-info mb-2 w-100" onClick={handleUserChoice} name="1">Kažkas</button>
                        <button className="btn btn-info mb-2 w-100" onClick={handleUserChoice} name="kindergartenList">Darželių sąrašas</button>
                        <button className="btn btn-info mb-2 w-100" onClick={handleUserChoice} name="3">Amžiaus grupės</button>
                    </div>
                    <div className="admin-action-placeholder col-8">
                        {choice === "greeting" && <GreetingComponent /> }
                        {choice === "1" && <h1>Kažkas</h1>}
                        {choice === "kindergartenList" && <KindergartenAdministrationContainer />}
                        {choice === "3" && <GroupFormContainer/>}
                    </div>
                </div>
            </div>
        </div>
    )
}

export default EducationSpecialistPageComponent;
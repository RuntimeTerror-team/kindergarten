import React from "react";
import PageTopComponent from '../PageTop/PageTopComponent';
import AdminUsersFormContainer from '../AdminUserForm/AdminUserFormContainer';
import DistrictAdministrationContainer from '../DistrictAdministration/DistrictAdministrationContainer'
import GreetingComponent from "../Greeting/GreetingComponent";

const AdminPageComponent = ({handleLogout, handleUserChoice, choice}) => {
    return (
        <div>
            <PageTopComponent handleLogout={handleLogout} usersName={"Administratoriaus paskyra"} />
            <div className="container p-4">
                <div className="row">
                    <div className="admin-actions col-4">
                        <button className="btn btn-info mb-2 w-100" onClick={handleUserChoice} name="adminUsers">Paskyrų administravimas</button>
                        <button className="btn btn-info mb-2 w-100" onClick={handleUserChoice} name="adminDistricts">Rajonų administravimas</button>
                        <button className="btn btn-info mb-2 w-100" onClick={handleUserChoice} name="3">Ir dar kažkas</button>
                    </div>
                    <div className="admin-action-placeholder col-8">
                        {choice === "greeting" && <GreetingComponent /> }
                        {choice === "adminUsers" && <AdminUsersFormContainer />}
                        {choice === "adminDistricts" && <DistrictAdministrationContainer />}
                        {choice === "3" && <h1>Ir dar kažkas</h1>}
                    </div>
                </div>
            </div>
        </div>
    )
}

export default AdminPageComponent;
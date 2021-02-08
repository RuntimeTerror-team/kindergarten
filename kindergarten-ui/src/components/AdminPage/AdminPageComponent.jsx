import React from "react";
import PageTopComponent from "../PageTop/PageTopComponent";
import AdminUsersFormContainer from "../AdminUserForm/AdminUserFormContainer";
import DistrictAdministrationContainer from "../DistrictAdministration/DistrictAdministrationContainer";
import GreetingComponent from "../Greeting/GreetingComponent";
import Footer from "../PageBottom/Footer";

const AdminPageComponent = ({ handleLogout, handleUserChoice, choice }) => {
  return (
    <div>
      <PageTopComponent handleLogout={handleLogout} usersName={"Administratoriaus paskyra"} />
      <div className="container p-4">
        <div className="row">
          <div className="admin-actions col-4">
            <button className="btn btn-main mb-2 w-100" onClick={handleUserChoice} name="adminUsers">
              Paskyros sukūrimas
            </button>
            <button className="btn btn-main mb-2 w-100" onClick={handleUserChoice} name="adminDistricts">
              Rajonų administravimas
            </button>
          </div>
          <div className="admin-action-placeholder col-8">
            {choice === "greeting" && <GreetingComponent />}
            {choice === "adminUsers" && <AdminUsersFormContainer />}
            {choice === "adminDistricts" && <DistrictAdministrationContainer />}
            {choice === "3" && <h1>Ir dar kažkas</h1>}
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
};

export default AdminPageComponent;

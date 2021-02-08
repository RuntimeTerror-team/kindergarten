import React from "react";
import PageTopComponent from "../PageTop/PageTopComponent";
import GreetingComponent from "../Greeting/GreetingComponent";
import Footer from "../PageBottom/Footer";

const GuardianPageComponent = ({
  handleLogout,
  handleUserChoice,
  choice,
  currentUserFirstname,
  currentUserLastname,
}) => {
  return (
    <div>
      <PageTopComponent handleLogout={handleLogout} usersName={`${currentUserFirstname} ${currentUserLastname}`} />
      <div className="container p-4">
        <div className="row">
          <div className="admin-actions col-4">
            <button className="btn btn-main mb-2 w-100" onClick={handleUserChoice} name="1">
              Kažkas
            </button>
            <button className="btn btn-main mb-2 w-100" onClick={handleUserChoice} name="2">
              Kažkas dar
            </button>
            <button className="btn btn-main mb-2 w-100" onClick={handleUserChoice} name="3">
              Ir dar kažkas
            </button>
          </div>
          <div className="admin-action-placeholder col-8">
            {choice === "greeting" && <GreetingComponent />}
            {choice === "1" && <h1>Kažkas</h1>}
            {choice === "2" && <h1>Kažkas dar</h1>}
            {choice === "3" && <h1>Ir dar kažkas</h1>}
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
};

export default GuardianPageComponent;

import React from "react";
import PageTopComponent from '../PageTop/PageTopComponent';
import AdminUsersFormContainer from '../AdminUserForm/AdminUserFormContainer';

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
                        {choice === "greeting" && <h1>Sveiki prisijungę į darželių informacinę sistemą</h1>}
                        {choice === "adminUsers" && <AdminUsersFormContainer />}
                        {choice === "adminDistricts" && <h1>Rajonų administravimas</h1>}
                        {choice === "3" && <h1>Ir dar kažkas</h1>}
                    </div>
                </div>
            </div>
        </div>
    )
}

export default AdminPageComponent;
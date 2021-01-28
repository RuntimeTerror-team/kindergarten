import Axios from 'axios';
import React, { Component } from 'react';
import ServicesContext from '../../context/ServicesContext';
import baseUrl from '../../AppConfig';

import '../../styles/adminPage.css';
import AdminUsersFormContainer from '../AdminUsersForm/AdminUsersFormContainer';

class AdminPage extends Component {
    constructor() {
        super();
        this.state = {
            adminChoice: "greeting",
            currentUserFirstame: "",
            currentUserLastname: ""
        }
    }

    componentDidMount = () => {
        let currentUser = this.context.userService.getCurrentUser();

        Axios
        .get(`${baseUrl}/api/users/${currentUser}`)
        .then(res => {
                this.setState({ currentUserFirstame: res.data.firstName });
                this.setState({ currentUserLastname: res.data.lastName });
        })
        .catch(err => console.log(err));
    }

    handleClick = (e) => {
        this.setState({ adminChoice: e.target.name });
    }

    render() {
        if (this.context.userService.getUserRole() === "ADMIN") {
            return (
                <div>
                    <div className="container-fluid p-4">
                        <div className="container top-line">
                            <p className="lead">{this.state.currentUserFirstame} {this.state.currentUserLastname}</p>
                            <button className="btn btn-info">Atsijungti</button>
                        </div>
                    </div>
                    <div className="container p-4">
                        <div className="row">
                            <div className="admin-actions col-4">
                                <button className="btn btn-info mb-2 w-100" onClick={this.handleClick} name="adminUsers">Paskyrų administravimas</button>
                                <button className="btn btn-info mb-2 w-100" onClick={this.handleClick} name="2">Kažkas dar</button>
                                <button className="btn btn-info mb-2 w-100" onClick={this.handleClick} name="3">Ir dar kažkas</button>
                            </div>
                            <div className="admin-action-placeholder col-8">
                                {this.state.adminChoice === "greeting" && <h1>Sveiki prisijungę į darželių informacinę sistemą</h1>}
                                {this.state.adminChoice === "adminUsers" && <AdminUsersFormContainer />}
                                {this.state.adminChoice === "2" && <h1>Kažkas dar</h1>}
                                {this.state.adminChoice === "3" && <h1>Ir dar kažkas</h1> }
                            </div>
                        </div>
                    </div>
                </div>
            )
        } else {
            <h1>Access denied</h1>
        }
    }
}

AdminPage.contextType = ServicesContext;

export default AdminPage;
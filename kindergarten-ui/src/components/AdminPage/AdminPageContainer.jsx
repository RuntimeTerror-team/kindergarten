import axios from 'axios';
import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import baseUrl from '../../AppConfig';
import ServicesContext from '../../context/ServicesContext';

import '../../styles/pages.css';
import AdminPageComponent from './AdminPageComponent';

class AdminPageContainer extends Component {
    constructor() {
        super();
        this.state = {
            choice: "greeting",
            userRole: ""
        }
    }

    componentDidMount = () => {
        axios
        .get(`${baseUrl}/loggedRole`)
        .then((res) => {
            this.setState({userRole : res.data})
        })
        .catch(err => console.log(err))
    }

    handleUserChoice = (e) => {
        this.setState({ choice: e.target.name });
    }


    render() {
        if (this.state.userRole === "ROLE_ADMIN") {
            return (
                <div className="pagesBackground">
                <AdminPageComponent
                    handleUserChoice={this.handleUserChoice}
                    handleLogout={this.handleLogout}
                    choice={this.state.choice}
                />
                </div>
            )
        } else {
            return (
                <div className="text-center p-5">
                <h1>Prieiga uždrausta</h1>
                <Link to="/" className="btn btn-primary">Prisijungti</Link>
                </div>
            )
        }
    }
}

AdminPageContainer.contextType = ServicesContext;

export default AdminPageContainer;
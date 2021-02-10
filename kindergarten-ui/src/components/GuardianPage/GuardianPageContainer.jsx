import axios from 'axios';
import React, { Component } from 'react';
import ServicesContext from '../../context/ServicesContext';
import baseUrl from '../../AppConfig';

import '../../styles/pages.css';
import GuardianPageComponent from './GuardianPageComponent';

class GuardianPageContainer extends Component {
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
                this.setState({ userRole: res.data })
            })
            .catch(err => console.log(err))
    }

    handleUserChoice = (e) => {
        this.setState({ choice: e.target.name });
    }

    render() {
        if (this.state.userRole === "ROLE_GUARDIAN") {
            return (
                <div>
                    <GuardianPageComponent
                        handleUserChoice={this.handleUserChoice}
                        handleLogout={this.handleLogout}
                        choice={this.state.choice}
                    />
                </div>
            )
        } else {
            return (
                <h1>Access denied</h1>
            )
        }
    }
}

GuardianPageContainer.contextType = ServicesContext;

export default GuardianPageContainer;
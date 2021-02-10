import axios from 'axios';
import React, { Component } from 'react';
import baseUrl from '../../AppConfig';
import ServicesContext from '../../context/ServicesContext';

import '../../styles/pages.css';
import EducationSpecialistPageComponent from './EducationSpecialistPageComponent';

class EducationSpecialistPageContainer extends Component {
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
        if (this.state.userRole === "ROLE_EDUCATION_SPECIALIST") {
            return (
                <EducationSpecialistPageComponent
                    handleUserChoice={this.handleUserChoice}
                    handleLogout={this.handleLogout}
                    choice={this.state.choice}
                />
            )
        } else {
            return (
                <h1>Access denied</h1>
            )
        }
    }
}

EducationSpecialistPageContainer.contextType = ServicesContext;

export default EducationSpecialistPageContainer;
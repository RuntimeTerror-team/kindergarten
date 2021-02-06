import React, { Component } from 'react';
import ServicesContext from '../../context/ServicesContext';

import '../../styles/pages.css';
import EducationSpecialistPageComponent from './EducationSpecialistPageComponent';

class EducationSpecialistPageContainer extends Component {
    constructor() {
        super();
        this.state = {
            choice: "greeting"
        }
    }

    handleUserChoice = (e) => {
        this.setState({ choice: e.target.name });
    }

    handleLogout = () => {
        this.context.userService.setCurrentUser("");
        this.context.userService.setUserRole("");
        this.context.userService.updateCurrentUser();
        this.context.userService.updateUserRole();

        this.props.history.push("/");
    }

    render() {
        if (this.context.userService.getUserRole() === "ROLE_EDUCATION_SPECIALIST") {
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
import Axios from 'axios';
import React, {Component} from 'react';
import { withRouter } from "react-router-dom";
import LoginComponent from './LoginComponent';
import baseUrl from "../../AppConfig";
import ServicesContext from "../../context/ServicesContext";


class LoginContainer extends Component {
    constructor(){

        super();
        this.state = {

            username : "",
            password: ""
        }
    }

    handleChangeUsername = (e) => {
        e.preventDefault();
        this.setState({username: e.target.value})
    }

    handleChangePassword = (e) => {
        e.preventDefault();
        this.setState({password: e.target.value})
    }
    
    handleSubmit = (e) => {
        e.preventDefault();

        let roleFromBack = "";
        let usernameFromUser = e.target.username.value;
        let passwordFromUser = e.target.password.value;
        console.log("Password from user: " + passwordFromUser);

        Axios
        .get(`${baseUrl}/api/users/${usernameFromUser}`)
        .then(res => {
            let passwordFromBack = res.data.password;
            roleFromBack = res.data.role;
            console.log("Password from back: " + passwordFromBack);
            console.log("Is equal?: " + (passwordFromBack === passwordFromUser));
            console.log("Role from back: " + roleFromBack);

            if (passwordFromUser === passwordFromBack) {
                this.context.userService.setCurrentUser(res.data.username);
                this.context.userService.setUserRole(roleFromBack);
                this.context.userService.updateCurrentUser();
                this.context.userService.updateUserRole();
            }
        })
        .then(() => {
            console.log("User's username: " +  this.context.userService.getCurrentUser());
            console.log("User's role: " +  this.context.userService.getUserRole());
    
            if (roleFromBack === "ADMIN") {
                this.props.history.push("/admin");
            }
        })
        .catch(err => console.log(err));

        this.setState({username: ""})
        this.setState({password: ""})
    }
    
        
    render(){

        return(

            <LoginComponent

            username={this.state.username}
            password={this.state.password}
            onSubmit={this.handleSubmit}
            onUsernameChange={this.handleChangeUsername}
            onPasswordChange={this.handleChangePassword}        
            />
 
        )

    }

}

LoginContainer.contextType = ServicesContext;

export default withRouter(LoginContainer);
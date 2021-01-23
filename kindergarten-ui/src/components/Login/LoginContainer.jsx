import React, {Component} from 'react';
import { withRouter } from "react-router-dom";
import LoginComponent from './LoginComponent';

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

        let username = e.target.username.value;
        let password = e.target.password.value;

        if (username === "administratorius" && password === "Administratorius1") {
            this.props.history.push("/admin");
        }

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

export default withRouter(LoginContainer);
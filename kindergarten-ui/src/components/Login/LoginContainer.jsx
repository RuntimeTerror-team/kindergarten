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
            password: "",
            invalidUsername: "",
            invalidPassword: "",
            incorrectCredentials: false,
            validationErrors: [],
            validationPassed: false
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

        this.setState({validationErrors : []})
        this.setState({invalidUsername: ""})
        this.setState({invalidPassword: ""})
        this.setState({incorrectCredentials: false})
        this.setState({validationPassed: false})

        let roleFromBack = "";
        let usernameFromUser = this.state.username;
        let passwordFromUser = this.state.password;

       this.doValidation(usernameFromUser, passwordFromUser)

       if(this.state.validationPassed === true){
        

        Axios
        .get(`${baseUrl}/api/users/${usernameFromUser}`)
        .then(res => {
            let passwordFromBack = res.data.password;
            roleFromBack = res.data.role;

            if (passwordFromUser === passwordFromBack) {
                this.context.userService.setCurrentUser(res.data.username);
                this.context.userService.setUserRole(roleFromBack);
                this.context.userService.updateCurrentUser();
                this.context.userService.updateUserRole();
            } else{

                this.setState({incorrectCredentials: true});
                
            }
        })
        .then(() => {
            if (this.context.userService.getUserRole() === "ADMIN") {
                this.props.history.push("/admin");
            }
        })
        .catch(err => console.log(err));
   }

        this.setState({username: ""})
        this.setState({password: ""})
    }

    //čia atlieku validaciją

    doValidation = (username, password) => {

        if(username.trim().length === 0){

              this.setState(prevState => ({
                validationErrors: [...prevState.validationErrors, "Užpildykite privalomą lauką"]
              }))
              this.setState({invalidUsername: "border border-danger"})
              this.setState({validationPassed: false})      
        }

        if(password.trim().length === 0){

            this.setState(prevState => ({
                validationErrors: [...prevState.validationErrors, "Užpildykite privalomą lauką"]
              }))
              this.setState({invalidPassword: "border border-danger"})
              this.setState({validationPassed: false}) 
        } 

        if(username.trim().length < 8){

              this.setState({validationPassed: false})
        }

        if(username.trim().length > 20){

              this.setState({validationPassed: false})
        }

        if(password.trim().length < 8){

              this.setState({validationPassed: false})
            
        } else{

            this.setState({validationPassed: true})
        }

        // if(new RegExp("^(?!(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.{8,}))").test(password)){

        //     this.setState({validationPassed: false})   
        // }
    }
        
    render(){

        return(

            <LoginComponent

            username={this.state.username}
            password={this.state.password}
            invalidUsername={this.state.invalidUsername}
            invalidPassword={this.state.invalidPassword}
            validationErrors = {this.state.validationErrors}
            incorrectCredentials={this.state.incorrectCredentials}
            onSubmit={this.handleSubmit}
            onUsernameChange={this.handleChangeUsername}
            onPasswordChange={this.handleChangePassword}        
            />
 
        )

    }

}

LoginContainer.contextType = ServicesContext;

export default withRouter(LoginContainer);
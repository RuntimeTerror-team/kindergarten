import axios from 'axios';
import React, { Component } from 'react';
import { withRouter } from "react-router-dom";
import PasswordChangeComponent from './PasswordChangeComponent';
import GuardianNavigationComponent from '../Navigation/GuardianNavigationComponent'
import HeaderComponent from '../Header/HeaderComponent';
import Footer from '../Footer/Footer';

import baseUrl from "../../AppConfig";
import urls from '../../constants/urls';

axios.defaults.withCredentials = true;

class PasswordChangeContainer extends Component {
    constructor() {
        super();
        this.state = {
            username:"",
            password: "",
            password2: "",
            role:"",
            passwordValidation: "",
            password2Validation: "",
            notMatchingMessage: "",
            notMatchingMessageStyle: "",
            successMessage: "",
            successMessageStyle: ""
        }
    }

    componentDidMount = () => {

        axios
          .get(`${baseUrl}/loggedUsername`)
          .then((res) => {
            this.setState({ username: res.data })
            
              })
              .catch((err) => console.log(err))

        axios
        .get(`${baseUrl}/loggedRole`)
        .then((res) => {
        this.setState({ role: res.data })
        
            })
            .catch((err) => console.log(err))
          
        
    }

    handleChange = (e) => {
        const { name, value } = e.target;

        this.setState({ [name]: value });

        if(this.state.passwordValidation !== "" && name === "password") {
            this.setState({ passwordValidation: "" });
        }

        if(this.state.password2Validation !== "" && name === "password2") {
            this.setState({ password2Validation: "" });
        }

        if(!/^(?=.*[a-ząčęėįšųū])(?=.*[A-ZĄČĘĖĮŠŲŪ])(?=.*\d)[a-ząčęėįšųūA-ZĄČĘĖĮŠŲŪ\d]{8,}$/.test(value) && name === "password"){
            this.setState({passwordValidation: "is-invalid"})
        }

        if(!/^(?=.*[a-ząčęėįšųū])(?=.*[A-ZĄČĘĖĮŠŲŪ])(?=.*\d)[a-ząčęėįšųūA-ZĄČĘĖĮŠŲŪ\d]{8,}$/.test(value) && name === "password2"){
            this.setState({password2Validation: "is-invalid"})
        }

    }

    resetState = () => {
        this.setState({ password: "" });
        this.setState({ password2: "" });
        this.setState({ passwordValidation: "" });
        this.setState({ password2Validation: "" });
    }

    handleSubmit = (e) => {
        e.preventDefault();

        this.setState({notMatchingMessage: ""})
        this.setState({notMatchingMessageStyle: ""})
        this.setState({successMessage: ""})
        this.setState({successMessageStyle: ""})
        let passwordFromUser = e.target.password.value;
        let password2FromUser = e.target.password2.value;

        this.doValidation(passwordFromUser, password2FromUser);

        if(passwordFromUser !== password2FromUser){

            console.log("Slaptazodziai nesutampa")
            this.setState({notMatchingMessage: "Slaptažodžiai nesutampa. Prašome bandyti vėl"})
            this.setState({notMatchingMessageStyle: "alert alert-danger mt-4"})
        }

        else if (passwordFromUser.trim().length !== 0 && password2FromUser.trim().length !== 0 && /^(?=.*[a-ząčęėįšųū])(?=.*[A-ZĄČĘĖĮŠŲŪ])(?=.*\d)[a-ząčęėįšųūA-ZĄČĘĖĮŠŲŪ\d]{8,}$/.test(passwordFromUser)) {
            let userDto = {
                username: this.state.username,
                password: this.state.password,
                role: this.state.role
            }

            axios
                .put(`${baseUrl}/api/users`,userDto)
                .then((res) => {

                    if(res.status === 200 ){

                    this.setState({successMessage: "Slaptažodis sėkmingai pakeistas"})
                    this.setState({successMessageStyle: "alert alert-success mt-4"})
                    this.timer = setTimeout(() => {
                        this.props.history.push(urls.guardian.applicationBase)
                    }, 3000);
                    }
                })
                .catch((e) => {console.log(e)});

        }

        
    }

    doValidation = (password, password2) => {
        if (password.trim().length === 0) {
            this.setState({ passwordValidation: "is-invalid" });
        }

        if (password2.trim().length === 0) {
            this.setState({ password2Validation: "is-invalid" });
        }
    }


    render() {

        return(
    
            <div className="footerBottom">
              <HeaderComponent
               userRole="ROLE_GUARDIAN"/>
              <div className="container py-4">
                <div className="row">
                  <GuardianNavigationComponent />
                  <div className="col-8">
                  <PasswordChangeComponent
                    password={this.state.password}
                    password2={this.state.password2}
                    passwordValidation={this.state.passwordValidation}
                    password2Validation={this.state.password2Validation}
                    notMatchingMessage={this.state.notMatchingMessage}
                    notMatchingMessageStyle={this.state.notMatchingMessageStyle}
                    successMessage={this.state.successMessage}
                    successMessageStyle={this.state.successMessageStyle}
                    onSubmit={this.handleSubmit}
                    onPasswordChange={this.handleChange}
                    onPassword2Change={this.handleChange}
                />
                  </div>
                </div>
              </div>
              <Footer />
            </div>
          )
        }    
}

export default withRouter(PasswordChangeContainer);
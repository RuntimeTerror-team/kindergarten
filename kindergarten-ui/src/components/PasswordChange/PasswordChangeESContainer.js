import axios from 'axios';
import React, { Component } from 'react';
import { withRouter } from "react-router-dom";
import PasswordChangeComponent from './PasswordChangeComponent';
import ESNavigationComponent from '../Navigation/ESNavigationComponent'
import HeaderComponent from '../Header/HeaderComponent';
import Footer from '../Footer/Footer';
import baseUrl from "../../AppConfig";

axios.defaults.withCredentials = true;

class PasswordChangeESContainer extends Component {
    constructor() {
        super();
        this.state = {
            username:"",
            password: "",
            password2: "",
            oldPassword: "",
            role:"",
            detailsGot: false,
            passwordValidation: "",
            password2Validation: "",
            oldPasswordValidation: "",
            notMatchingMessage: "",
            notMatchingMessageStyle: "",
            successMessage: "",
            successMessageStyle: "",
            wrongOldPasswordMessage: "",
            wrongOldPasswordMessageStyle: ""
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
        console.log(res.data)
        
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

        if(this.state.oldPasswordValidation !== "" && name === "oldPassword") {
            this.setState({ oldPasswordValidation: "" });
        }

        if(!/^(?=.*[a-ząčęėįšųū])(?=.*[A-ZĄČĘĖĮŠŲŪ])(?=.*\d)[a-ząčęėįšųūA-ZĄČĘĖĮŠŲŪ\d]{8,}$/.test(value) && name === "password"){
            this.setState({passwordValidation: "is-invalid"})
        }

        if(!/^(?=.*[a-ząčęėįšųū])(?=.*[A-ZĄČĘĖĮŠŲŪ])(?=.*\d)[a-ząčęėįšųūA-ZĄČĘĖĮŠŲŪ\d]{8,}$/.test(value) && name === "password2"){
            this.setState({password2Validation: "is-invalid"})
        }

        if(!/^(?=.*[a-ząčęėįšųū])(?=.*[A-ZĄČĘĖĮŠŲŪ])(?=.*\d)[a-ząčęėįšųūA-ZĄČĘĖĮŠŲŪ\d]{8,}$/.test(value) && name === "oldPassword"){
            this.setState({oldPasswordValidation: "is-invalid"})
        }

    }

    resetState = () => {
        this.setState({ password: "" });
        this.setState({ password2: "" });
        this.setState({oldPassword: ""})
        this.setState({ passwordValidation: "" });
        this.setState({ password2Validation: "" });
        this.setState({oldPasswordValidation: ""})
    }

    handleSubmit = (e) => {
        e.preventDefault();

        this.setState({notMatchingMessage: ""})
        this.setState({notMatchingMessageStyle: ""})
        this.setState({successMessage: ""})
        this.setState({successMessageStyle: ""})
        this.setState({wrongOldPasswordMessage: "" })
        this.setState({wrongOldPasswordMessageStyle: ""})
        let passwordFromUser = e.target.password.value;
        let password2FromUser = e.target.password2.value;
        let oldPasswordFromUser = e.target.oldPassword.value;

        this.doValidation(passwordFromUser, password2FromUser, oldPasswordFromUser);




        

        // if(wrongPassword){

        //     this.setState({wrongOldPasswordMessage: "Įvestas neteisingas senas slaptažodis" })
        //     this.setState({wrongOldPasswordMessageStyle: "alert alert-danger mt-4"})

        // }

        

        if (this.state.passwordValidation === "" && this.state.password2Validation === "" && this.state.oldPasswordValidation === ""
        && /^(?=.*[a-ząčęėįšųū])(?=.*[A-ZĄČĘĖĮŠŲŪ])(?=.*\d)[a-ząčęėįšųūA-ZĄČĘĖĮŠŲŪ\d]{8,}$/.test(passwordFromUser)
        && /^(?=.*[a-ząčęėįšųū])(?=.*[A-ZĄČĘĖĮŠŲŪ])(?=.*\d)[a-ząčęėįšųūA-ZĄČĘĖĮŠŲŪ\d]{8,}$/.test(password2FromUser)
        && /^(?=.*[a-ząčęėįšųū])(?=.*[A-ZĄČĘĖĮŠŲŪ])(?=.*\d)[a-ząčęėįšųūA-ZĄČĘĖĮŠŲŪ\d]{8,}$/.test(oldPasswordFromUser))
        {

            if(passwordFromUser !== password2FromUser){
                this.setState({notMatchingMessage: "Slaptažodžiai nesutampa. Prašome bandyti vėl"})
                this.setState({notMatchingMessageStyle: "alert alert-danger mt-4"})
                return;
            }

            let userData = new URLSearchParams();
            userData.append('username', this.state.username);
            userData.append('password', oldPasswordFromUser);

            this.setState({detailsGot: false})

            axios
                .post(`${baseUrl}/login`,
                    userData,
                    { headers: { 'Content-type': 'application/x-www-form-urlencoded' } })
                .then((res) => {

                    console.log("gets executed")
                    if(res.status === 200){

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
                                }
                            })
                            .catch((e) => {console.log(e)});
                    }
            
                })
                .catch((e) => {
                    if(e.response.status === 401){
                        this.setState({wrongOldPasswordMessage: "Įvestas neteisingas senas slaptažodis" })
                        this.setState({wrongOldPasswordMessageStyle: "alert alert-danger mt-4"})
                    }
                });


        }

    

        
    }

    doValidation = (password, password2, oldPassword) => {
        if (password.trim().length === 0 || !/^(?=.*[a-ząčęėįšųū])(?=.*[A-ZĄČĘĖĮŠŲŪ])(?=.*\d)[a-ząčęėįšųūA-ZĄČĘĖĮŠŲŪ\d]{8,}$/.test(password)) {
            this.setState({ passwordValidation: "is-invalid" });
           
        }

        if (password2.trim().length === 0 || !/^(?=.*[a-ząčęėįšųū])(?=.*[A-ZĄČĘĖĮŠŲŪ])(?=.*\d)[a-ząčęėįšųūA-ZĄČĘĖĮŠŲŪ\d]{8,}$/.test(password2)) {
            this.setState({ password2Validation: "is-invalid" });
            
        }

        if (oldPassword.trim().length === 0 || !/^(?=.*[a-ząčęėįšųū])(?=.*[A-ZĄČĘĖĮŠŲŪ])(?=.*\d)[a-ząčęėįšųūA-ZĄČĘĖĮŠŲŪ\d]{8,}$/.test(oldPassword)) {
            this.setState({ oldPasswordValidation: "is-invalid" });
            
        }

        
    }


    render() {

          return (
            <div className="footerBottom">
                <HeaderComponent userRole="ROLE_EDUCATION_SPECIALIST" />
                <div className="container py-4">
                    <div className="row">
                        <ESNavigationComponent />
                        <div className="col-8">
                  <PasswordChangeComponent
                    password={this.state.password}
                    password2={this.state.password2}
                    oldPassword={this.state.oldPassword}
                    passwordValidation={this.state.passwordValidation}
                    password2Validation={this.state.password2Validation}
                    oldPasswordValidation={this.state.oldPasswordValidation}
                    notMatchingMessage={this.state.notMatchingMessage}
                    notMatchingMessageStyle={this.state.notMatchingMessageStyle}
                    successMessage={this.state.successMessage}
                    successMessageStyle={this.state.successMessageStyle}
                    wrongOldPasswordMessage={this.state.wrongOldPasswordMessage}
                    wrongOldPasswordMessageStyle={this.state.wrongOldPasswordMessageStyle}
                    onSubmit={this.handleSubmit}
                    onPasswordChange={this.handleChange}
                    onPassword2Change={this.handleChange}
                    onOldPasswordChange={this.handleChange}
                />
                  </div>
                    </div>
                </div>
                <Footer />
            </div>
        )

          
        }    
}

export default withRouter(PasswordChangeESContainer);
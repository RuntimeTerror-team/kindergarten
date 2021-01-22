import React from 'react';
import AdminLoginComponent from './AdminLoginComponent';

class AdminLoginContainer extends React.Component{

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

        console.log(e.target.username.value);
        console.log(e.target.password.value);

        this.setState({username: ""})
        this.setState({password: ""})
    }
    
        
    render(){

        return(

            <AdminLoginComponent

            username={this.state.username}
            password={this.state.password}
            onSubmit={this.handleSubmit}
            onUsernameChange={this.handleChangeUsername}
            onPasswordChange={this.handleChangePassword}        
            />
 
        )

    }

}

export default AdminLoginContainer
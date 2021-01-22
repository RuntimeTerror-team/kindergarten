import React from 'react';
import AdminComponent from './AdminComponent';

class AdminContainer extends React.Component{

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

        this.setState({username: ""})
        this.setState({password: ""})
    }
    
        
    render(){

        return(

            <AdminComponent

            username={this.state.username}
            password={this.state.password}
            onSubmit={this.handleSubmit}
            onUsernameChange={this.handleChangeUsername}
            onPasswordChange={this.handleChangePassword}        
            />
 
        )

    }

}

export default AdminContainer
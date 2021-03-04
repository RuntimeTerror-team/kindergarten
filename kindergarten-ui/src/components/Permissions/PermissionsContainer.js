import Axios from 'axios';
import React, { Component } from 'react';
import baseUrl from '../../AppConfig'
import AdminNavigationComponent from '../Navigation/AdminNavigationComponent'
import HeaderComponent from '../Header/HeaderComponent';
import PermissionsComponent from './PermissionsComponent'
import Footer from '../Footer/Footer';

class PermissionsContainer extends Component{

    constructor(props) {
        super(props);
        this.state = {

            permission: false,
            buttonText: "",
            changedPermissionMessage: "",
            changedPermissionMessageStyle: ""
        }
}

componentDidMount(){

    Axios
    .get(baseUrl + "/api/users/ES/permission")
       .then(res => {
           this.setState({permission: res.data});
           console.log("statusas uzsikrovius puslapiui: " + this.state.permission)
           this.setState({buttonText: this.state.permission ? "Panaikinti leidimą" : "Suteikti leidimą"})
        })
       .catch(err => {console.log(err)})
}


handlePermissionChange = (e) => {

    e.preventDefault();

    //this.setState({permission: !this.state.permission})
    console.log("leidimas pries uzklausa: " + this.state.permission)

    let permissionDto = {
        isAllowed: !this.state.permission
    }

    Axios.post(baseUrl + "/api/users/ES/permission", permissionDto)
    .then(res => {

        Axios
    .get(baseUrl + "/api/users/ES/permission")
       .then(res => {
           this.setState({permission: res.data});
           this.setState({buttonText: this.state.permission ? "Panaikinti leidimą" : "Suteikti leidimą"})
           this.setState({changedPermissionMessage: this.state.permission ? "Leidimas sėkmingai suteiktas" : "Leidimas sėkmingai panaikintas"})
           this.setState({changedPermissionMessageStyle: "alert alert-success mt-4" })
           this.timer = setTimeout(() => {
            this.setState({changedPermissionMessage: ""})
           this.setState({changedPermissionMessageStyle: "" })
        }, 3000);
        })
       .catch(err => {console.log(err)})


    })
    .catch(e => console.log(e));


}

render(){

    return (
        <div className="footerBottom">
            <HeaderComponent userRole="ROLE_EDUCATION_SPECIALIST"/>
            <div className="container py-4">
                <div className="row">
                    <AdminNavigationComponent />
                    <div className="col-8">
                        <h1 className="mb-5 text-center">Leidimų suteikimas</h1>
                        <PermissionsComponent
                         permission={this.state.permission}
                         buttonText={this.state.buttonText}
                         changedPermissionMessage={this.state.changedPermissionMessage}
                         changedPermissionMessageStyle={this.state.changedPermissionMessageStyle}
                         onPermissionChange={this.handlePermissionChange}/>
                    </div>
                </div>
            </div>
            <Footer />
        </div>
    )
}

}

export default PermissionsContainer;
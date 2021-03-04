import Axios from 'axios';
import React, { Component } from 'react';
import baseUrl from '../../AppConfig'
import ESNavigationComponent from '../Navigation/ESNavigationComponent'
import HeaderComponent from '../Header/HeaderComponent';
import ESApprovedApplicationListComponent from './ESApprovedApplicationListComponent'
import Footer from '../Footer/Footer';

class ESApprovedApplicationListContainer extends Component{

    constructor(props) {
        super(props);
        this.state = {

            applications: [],
            permission: false,
            changeStatus: ""
        }
}

componentDidMount(){

    Axios
    .get(baseUrl + "/api/applications/sorted")
       .then(res => {
           this.setState({applications: res.data});
           this.translateStatus();
        })
       .catch(err => {console.log(err)})

       Axios
    .get(baseUrl + "/api/users/ES/permission")
       .then(res => {
           this.setState({permission: res.data});
        })
       .catch(err => {console.log(err)})
}

translateStatus(){

    this.state.applications.forEach(application => {

        if(application.status === "SUBMITTED"){
            application.status = 'Pateiktas'
            this.forceUpdate()
        }

        else if(application.status === "UNCOMFIRMED"){
            application.status = 'Nepatvirtintas'
            this.forceUpdate()
        }

        else if(application.status === "REJECTED"){
            application.status = 'Atmestas'
            this.forceUpdate()
        }

        else if(application.status === "APPROVED"){

            console.log("Approved")
            application.status = 'Patvirtintas'
            this.forceUpdate()
        }

        else if(application.status === "WAITING"){
            application.status = 'Eilėje'
            this.forceUpdate()
        }
    })
}

handleStatusChange = (e) =>{

    e.preventDefault();

    Axios.put(baseUrl + "/api/applications/" + e.target.value + "/REJECTED").then(

        Axios
        .get(baseUrl + "/api/applications/sorted")
           .then(res => {
               this.setState({applications: res.data});
               this.translateStatus();
            })
           .catch(err => {console.log(err)})
        

    ).catch(e => console.log(e));

}

render(){

    return (
        <div className="footerBottom">
            <HeaderComponent userRole="ROLE_EDUCATION_SPECIALIST"/>
            <div className="container py-4">
                <div className="row">
                    <ESNavigationComponent />
                    <div className="col-8">
                        <h1 className="mb-5 text-center">Prašymai</h1>
                        <ESApprovedApplicationListComponent
                         applications={this.state.applications}
                         permission={this.state.permission}
                         changeStatus={this.state.changeStatus}
                         onStatusChange={this.handleStatusChange}/>
                    </div>
                </div>
            </div>
            <Footer />
        </div>
    )
}

}

export default ESApprovedApplicationListContainer;
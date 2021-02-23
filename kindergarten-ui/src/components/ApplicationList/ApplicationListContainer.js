import Axios from 'axios';
import React, { Component } from 'react';
import baseUrl from '../../AppConfig'
import ApplicationListComponent from './ApplicationListComponent'

class ApplicationListContainer extends Component{

    constructor(props) {
        super(props);
        this.state = {
            username: "",
            applications: [],
        }
}

componentDidMount(){

    Axios
    .get(`${baseUrl}/loggedUsername`)
    .then((res) => {
      this.setState({ username: res.data })
      Axios.get(baseUrl + "/api/applications/info/" + this.state.username)
       .then(res => {
           this.setState({applications: res.data});
           this.translateStatus();
        })
       .catch(err => {console.log(err)})
    })

    .catch(err => console.log)
 

}

translateStatus(){

    this.state.applications.forEach(application => {

        if(application.applicationStatus === "SUBMITTED"){
            application.applicationStatus = 'Pateiktas'
            this.forceUpdate()
        }

        else if(application.applicationStatus === "REJECTED"){
            application.applicationStatus = 'Atmestas'
            this.forceUpdate()
        }

        else if(application.applicationStatus === "APPROVED"){
            application.applicationStatus = 'Patvirtintas'
            this.forceUpdate()
        }

        else if(application.applicationStatus === "WAITING"){
            application.applicationStatus = 'Eilėje'
            this.forceUpdate()
        }
    })
}

render(){

    return(

        <ApplicationListComponent
        applications={this.state.applications}/>
    )
}

}

export default ApplicationListContainer;
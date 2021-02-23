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
       .then(res => {this.setState({applications: res.data})})
       .catch(err => {console.log(err)})
    })

    .catch(err => console.log)
 

}

render(){

    return(

        <ApplicationListComponent
        applications={this.state.applications}/>
    )
}

}

export default ApplicationListContainer;
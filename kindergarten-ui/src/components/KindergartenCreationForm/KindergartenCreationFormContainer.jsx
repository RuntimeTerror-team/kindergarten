import Axios from 'axios';
import React, { Component } from 'react';
import KindergartenCreationFormComponent from './KindergartenCreationFormComponent';
import baseUrl from '../../AppConfig';

class KindergartenCreationFormContainer extends Component {
    constructor () {
        super();
        this.state= {
            districts: [],
            street: "", 
            buildingNo: "",
            city: "", 
            email: "", 
            phoneNo: "", 
            postalCode: "", 
            title: "", 
            website: "",
            districtId: ""
        }
    }

    componentDidMount = () => {
        Axios
            .get(`${baseUrl}/api/districts`)
            .then((res) => {
                this.setState({ districts: res.data })
            })
            .catch((err) => console.log(err));
    }

    handleChange = (e) => {
        const { name, value } = e.target;
        this.setState({ [name]: value });
    }

    render () {
        return(
            <div className="col-12">
                <KindergartenCreationFormComponent
                 districts={this.state.districts}
                 handleChange={this.handleChange}
                  />
            </div>
        )
    }
}

export default KindergartenCreationFormContainer;
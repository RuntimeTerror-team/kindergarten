import React, { Component } from 'react';
import axios from 'axios';
import baseUrl from '../../AppConfig';
import DistrictAdministrationComponent from './DistrictAdministrationComponent';

class DistrictAdministrationContainer extends Component {
    constructor() {
        super();
        this.state = {
            districts: [],
            isUpdating: false
        }
    }

    componentDidMount = () => {
        axios
            .get(`${baseUrl}/api/districts`)
            .then((res) =>{
                console.log(res)
                this.setState({ districts: res.data })
            })
            .catch((err) => console.log(err));
    }

    addDistrict = (e) => {
        e.preventDefault();

        let districtName = e.target.districtName.value;
        console.log(districtName);

        axios
            .post(`${baseUrl}/api/district`, {
                id: 0,
                title: districtName
            })
            .then(() =>{
                axios
                .get(`${baseUrl}/api/districts`)
                .then((res) =>{
                    console.log(res)
                    this.setState({ districts: res.data })
                })
                .catch((err) => console.log(err));
            })
            .catch((err) => console.log(err));
    }

    updateDistrict = (e) => {

    }

    startUpdate = (e) => {
        this.setState({isUpdating: true})
        console.log("Update");
    }


    render() {
        return (
            <div>
                <DistrictAdministrationComponent
                  districts={this.state.districts}
                  addDistrict={this.addDistrict}
                  startUpdate={this.startUpdate}
                />
            </div>
        );
    }
}

export default DistrictAdministrationContainer;
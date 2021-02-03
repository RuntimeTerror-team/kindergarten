import Axios from 'axios';
import React, { Component } from 'react';
import KindergartenCreationFormComponent from './KindergartenCreationFormComponent';
import baseUrl from '../../AppConfig';

class KindergartenCreationFormContainer extends Component {
    constructor(props) {
        super(props);
        this.state = {
            districts: [],
            title: "",
            companyCode: "",
            street: "",
            buildingNo: "",
            district: "",
            postalCode: "",
            phoneNo: "",
            email: "",
            website: ""
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

    handleSubmit = (e) => {
        e.preventDefault();
        console.log(e.target.district.value.split(",")[1]);
        console.log(e.target.district.value.split(",")[0]);
        this.props.stopCreatingKindergarten();
        Axios
            .post(`${baseUrl}/api/kindergartens`, {
                "address": `${e.target.street.value} ${e.target.buildingNo.value}`,
                "city": "VILNIUS",
                "companyCode": e.target.companyCode.value,
                "district": {
                    id: e.target.district.value.split(",")[1],
                    title: e.target.district.value.split(",")[0]
                },
                "email": e.target.email.value,
                "phoneNumber": "+370" + e.target.phoneNo.value,
                "postalCode": e.target.postalCode.value,
                "title": e.target.title.value,
                "website": e.target.website.value
            })
            .then(() => {
                this.props.handleUpdateKindergartenList();
             })
            .catch(err => console.log(err));
    }

    render() {
        return (
                <KindergartenCreationFormComponent
                    districts={this.state.districts}
                    handleChange={this.handleChange}
                    handleSubmit={this.handleSubmit}
                    stopCreatingKindergarten={this.props.stopCreatingKindergarten}
                    otherProps={this.state}
                />
        )
    }
}

export default KindergartenCreationFormContainer;
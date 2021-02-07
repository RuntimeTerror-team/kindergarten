import Axios from 'axios';
import React, { Component } from 'react';
import KindergartenInfoFormComponent from './KindergartenInfoFormComponent';
import baseUrl from '../../AppConfig';

class KindergartenInfoFormContainer extends Component {
    constructor(props) {
        super(props);
        this.state = {
            districts: [],
            kindergarten: null
        }
    }

    componentDidMount = () => {
            Axios
            .get(`${baseUrl}/api/kindergartens/${this.props.kindergartenInfoId}`)
            .then((res) => {
                this.setState({ kindergarten: res.data })
            })
            .catch((err) => console.log(err)); 
    }

    render() {
        if ( this.state.kindergarten !== null) {
            return (
                <KindergartenInfoFormComponent
                    districts={this.state.districts}
                    kindergarten={this.state.kindergarten}
                    resetWantsInfo={this.props.resetWantsInfo}
                    otherProps={this.state}
                />
        )} else {
            return (
                <div>Duomenys kraunasi...</div>
            )
        }

    }
}

export default KindergartenInfoFormContainer;
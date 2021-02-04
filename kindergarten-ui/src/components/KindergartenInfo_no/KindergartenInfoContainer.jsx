import Axios from 'axios';
import React, { Component } from 'react';
import baseUrl from '../../AppConfig';
import KindergartenInfoComponent from './KindergartenTableComponent';

class KindergartenInfoContainer extends Component {
    constructor(props) {
        super(props);
        this.state = {
            kindergarten: null
        }
    }

    componentDidMount = () => {
        console.log("KindergartenInfoContainer")
        Axios
        .get(`${baseUrl}/api/kindergartens/${this.props.kindergartenInfoId}`)
        .then((res) => {
            this.setState({ kindergarten: res.data });
        })
        .catch((err) => console.log(err));
    }

    render() {
        if (this.state.kindergarten !== null ) {
            return (
                <KindergartenInfoComponent
                    kindergarten={this.state.kindergarten}
                />
            )
        } else {
            return (
                <div>Duomenys kraunami</div>
            )

        }

    }
}

export default KindergartenInfoContainer;
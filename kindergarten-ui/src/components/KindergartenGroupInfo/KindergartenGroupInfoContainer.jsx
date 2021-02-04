import Axios from 'axios';
import React, { Component } from 'react';
import KindergartenGroupInfoComponent from '../KindergartenGroupInfo/KindergartenGroupInfoComponent'
import baseUrl  from '../../AppConfig'

class KindergartenGroupInfoContainer extends Component {
    constructor(props) {
        super(props);
        this.state = {
            kindergartenId: "",
            groups: [],
            ageRanges: []
        }
    }

    componentDidMount = () => {
        this.setState({kindergartenId: this.props.kindergartenInfoId})

        // Axios
        //     .get(`${baseUrl}/api/kindergartens/${this.props.kindergartenInfoId}`)
        //     .then((res) => {
        //         this.setState({ groups: res.data })
        //     })
        //     .catch((err) => console.log(err));

        Axios
        .get(`${baseUrl}/api/ageRanges`)
        .then((res) => {
            this.setState({ ageRanges: res.data })
        })
        .catch((err) => console.log(err));
    }

    render() {
        return (
            <KindergartenGroupInfoComponent 
                groups={this.state.groups}
                ageRanges={this.state.ageRanges}
            />
        )
    }

}

export default KindergartenGroupInfoContainer;
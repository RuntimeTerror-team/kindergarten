import Axios from 'axios';
import React, { Component } from 'react';
import KindergartenGroupInfoComponent from '../KindergartenGroupInfo/KindergartenGroupInfoComponent'
import baseUrl from '../../AppConfig'

class KindergartenGroupInfoContainer extends Component {
    constructor(props) {
        super(props);
        this.state = {
            kindergartenId: "",
            groups: [],
            ageRanges: [],
            title: "",
            ageRangeId: "",
            childrenCount: "",
            wantsCreate: false
        }
    }

    componentDidMount = () => {
        this.setState({ kindergartenId: this.props.kindergartenInfoId })

        Axios
            .get(`${baseUrl}/api/kindergartens/${this.props.kindergartenInfoId}/groups`)
            .then((res) => {
                this.setState({ groups: res.data })
            })
            .catch((err) => console.log(err));

        Axios
            .get(`${baseUrl}/api/ageRanges`)
            .then((res) => {
                this.setState({ ageRanges: res.data })
            })
            .catch((err) => console.log(err));
    }

    handleFormChange = (e) => {
        const { name, value } = e.target;
        this.setState({ [name]: value });
    }

    handleGroupCreation = (e) => {
        e.preventDefault();

        console.log(e.target.childrenCount.value);

        Axios
            .post(`${baseUrl}/api/kindergartens/${this.props.kindergartenInfoId}/groups/${e.target.ageRangeId.value}`, {
                childrenCount: e.target.childrenCount.value,
                id: 0,
                title: e.target.title.value
            })
            .then(() => {
                Axios
                    .get(`${baseUrl}/api/kindergartens/${this.props.kindergartenInfoId}/groups`)
                    .then((res) => {
                        this.setState({ groups: res.data })
                    })
                    .catch((err) => console.log(err));
            })
            .then(() => {
                this.setState({ title: "" })
                this.setState({ ageRangeId: "" })
                this.setState({ childrenCount: "" })
                this.setState({wantsCreate: !this.state.wantsCreate})
            })
            .catch((err) => console.log(err));
    }

    toggleWantsCreate = () => {
        console.log("cia");
        this.setState({wantsCreate: !this.state.wantsCreate})
    }

    render() {
        return (
            <KindergartenGroupInfoComponent
                groups={this.state.groups}
                ageRanges={this.state.ageRanges}
                otherProps={this.state}
                handleFormChange={this.handleFormChange}
                handleGroupCreation={this.handleGroupCreation}
                toggleWantsCreate={this.toggleWantsCreate}
                wantsCreate={this.state.wantsCreate}
            />
        )
    }

}

export default KindergartenGroupInfoContainer;
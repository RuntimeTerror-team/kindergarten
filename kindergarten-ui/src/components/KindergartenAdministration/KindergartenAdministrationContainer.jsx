import Axios from 'axios';
import React, {Component} from 'react';
import KindergartenAdministrationComponent from './KindergartenAdministrationComponent';
import baseUrl from '../../AppConfig';

class KindergartenAdministrationContainer extends Component {
    constructor(props) {

        super(props);
        this.state = {
            kindergartens: [],
            isCreatingKindergarten: false,
            wantsInfo: false
        }
    }

    componentDidMount = () => {
        Axios
            .get(`${baseUrl}/api/kindergartens`)
            .then((res) => {
                this.setState({ kindergartens: res.data })
            })
            .catch((err) => console.log(err));
    }

    startCreatingKindergarten = () => {
        this.setState({isCreatingKindergarten: true})
    }

    stopCreatingKindergarten = () => {
        this.setState({isCreatingKindergarten: false});
    }

    handleUpdateKindergartenList = () => {
        this.stopCreatingKindergarten();
        Axios
        .get(`${baseUrl}/api/kindergartens`)
        .then((res) => {
            this.setState({ kindergartens: res.data })
        })
        .catch((err) => console.log(err));
    }

    handleWantsInfo = (e) => {
        console.log("Kindergarten Id: " + e.target);
        console.log(e);
    }

    render () {
        return (
            <KindergartenAdministrationComponent
                kindergartens={this.state.kindergartens}
                isCreatingKindergarten={this.state.isCreatingKindergarten}
                startCreatingKindergarten={this.startCreatingKindergarten}
                stopCreatingKindergarten={this.stopCreatingKindergarten}
                handleUpdateKindergartenList={this.handleUpdateKindergartenList}
                handleWantsInfo={this.handleWantsInfo}
            />
        )
    }

}

export default KindergartenAdministrationContainer;
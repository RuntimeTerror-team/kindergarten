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
            wantsInfo: false,
            kindergartenInfoId: "",
            wantsGroups: false
        }
    }

    componentDidMount = () => {
        console.log("KindergartenAdministrationContainer - main")
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
        this.setState({kindergartenInfoId: e.target.id});
        this.setState({wantsInfo: true})
    }

    resetWantsInfo = () => {
        this.setState({kindergartenInfoId: ""});
        this.setState({wantsInfo: false}) 
    }

    handleWantsGroups = (e) => {
        this.setState({kindergartenInfoId: e.target.id});
        this.setState({wantsGroups: true})
        console.log(e.target.id)
    }

    resetWantsGroups = () => {
        this.setState({kindergartenInfoId: ""});
        this.setState({wantsGroups: false}) 
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
                wantsInfo={this.state.wantsInfo}
                kindergartenInfoId={this.state.kindergartenInfoId}
                handleWantsGroups={this.handleWantsGroups}
                wantsGroups={this.state.wantsGroups}
                resetWantsInfo={this.resetWantsInfo}
                resetWantsGroups={this.resetWantsGroups}
            />
        )
    }

}

export default KindergartenAdministrationContainer;
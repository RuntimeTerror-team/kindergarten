import Axios from 'axios';
import React, {Component} from 'react';
import KindergartenAdministrationComponent from './KindergartenAdministrationComponent';
import baseUrl from '../../AppConfig';

class KindergartenAdministrationContainer extends Component {
    constructor(props) {

        super(props);
        this.state = {
            kindergartens: [],
            isCreatingKindergarten: false
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

    componentDidUpdate = () => {
        console.log("Kindergartens number: " + this.state.kindergartens.length);
        console.log("isCreatingKindergarten: " + this.state.isCreatingKindergarten);
    }

    startCreatingKindergarten = () => {
        this.setState({isCreatingKindergarten: true})
    }

    stopCreatingKindergarten = () => {
        this.setState({isCreatingKindergarten: false})
    }

    render () {
        return (
            <KindergartenAdministrationComponent
                kindergartens={this.state.kindergartens}
                isCreatingKindergarten={this.state.isCreatingKindergarten}
                startCreatingKindergarten={this.startCreatingKindergarten}
                stopCreatingKindergarten={this.stopCreatingKindergarten}
            />
        )
    }

}

export default KindergartenAdministrationContainer;
import axios from 'axios';
import React, { Component } from 'react';
import baseUrl from '../../AppConfig';
import Footer from '../Footer/Footer';
import HeaderComponent from '../Header/HeaderComponent';
import GuardianNavigationComponent from '../Navigation/GuardianNavigationComponent'
import HealthFormListComponent from './HealthFormListComponent';

class HealthFormListContainer extends Component {
    constructor() {
        super();
        this.state = {
            tribeId: '',
            children: []
        }
    }

    componentDidMount = () => {
        axios
            .get(`${baseUrl}/api/loggedTribeId}`)
            .then((res) => {
                this.setState({ tribeId: res.tribeId });

                axios
                    .get(`${baseUrl}/api/persons/childrenOfTribe/${res.tribeId}`)
                    .then(res => {
                        this.setState({ children: res.children })
                    })
                    .catch(e => console.log(e))
            })
            .catch(e => console.log(e))

    }

    render() {
        return (
            <div className="footerBottom">
                <HeaderComponent userRole="ROLE_GUARDIAN" />
                <div className="container py-4">
                    <div className="row">
                        <GuardianNavigationComponent />
                        <div className="col-8">
                            <h1 className="mb-5 text-center">Sveikatos pažymos</h1>
                            <HealthFormListComponent />
                        </div>
                    </div>
                </div>
                <Footer />
            </div>
        )
    }
}

export default HealthFormListContainer;
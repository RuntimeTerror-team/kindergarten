import axios from 'axios';
import React, { Component } from 'react';
import baseUrl from '../../AppConfig';
import Footer from '../Footer/Footer';
import HeaderComponent from '../Header/HeaderComponent';
import HealthFormTableComponent from '../HealtFormTable/HealthFormTableComponent';
import GuardianNavigationComponent from '../Navigation/GuardianNavigationComponent'
import HealthFormListComponent from './HealthFormListComponent';

class HealthFormListContainer extends Component {
    constructor() {
        super();
        this.state = {
            children: [],
            files: []
        }
    }

    componentDidMount = () => {
        axios
            .get(`${baseUrl}/loggedUsername`)
            .then((res) => {
                console.log(res.data);
                axios
                    .get(`${baseUrl}/api/persons/childrenOfTribe/${res.data}`)
                    .then(res => {
                        this.setState({ children: res.data })
                    })
                    .catch(e => console.log(e))
            })
            .catch(e => console.log(e))

        axios
            .get(`${baseUrl}/api/health-forms`)
            .then((res) => {
                this.setState({ files: res.data })
            })
            .catch((err) => {
                console.log(err);
            })
    }

    updateForms = () => {
        axios
            .get(`${baseUrl}/api/health-forms`)
            .then((res) => {
                this.setState({ files: res.data })
            })
            .catch((err) => {
                console.log(err);
            })
    }

    handleDownload = (e) => {
        let url = e.target.value;
        window.open(url);

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
                            {this.state.children.length > 0
                                && <HealthFormListComponent
                                    children={this.state.children}
                                    handleSubmit={this.handleSubmit}
                                    updateForms={this.updateForms}
                                />}
                            {this.state.children.length === 0
                                && <div className="alert alert-warning text-center col-6 offset-3" role="alert">
                                    Sistemoje nėra išsaugotų vaikų
                             </div>}
                            {this.state.files.length > 0
                                && <HealthFormTableComponent
                                    files={this.state.files}
                                    handleDownload={this.handleDownload}
                                />}
                        </div>
                    </div>
                </div>
                <Footer />
            </div>
        )
    }
}

export default HealthFormListContainer;
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
            familyFiles: [],
            username: ""
        }
    }

    componentDidMount = () => {
        axios
            .get(`${baseUrl}/loggedUsername`)
            .then((res) => {
                this.setState({ username: res.data })
                this.updateForms(res.data)
                axios
                    .get(`${baseUrl}/api/persons/childrenOfTribe/${res.data}`)
                    .then(res => {
                        this.setState({ children: res.data })
                    })
                    .catch(e => console.log(e))
            })
            .catch(e => console.log(e))


    }

    updateForms = (username) => {
        username = typeof username !== 'undefined' ? username : this.state.username;
        axios
            .get(`${baseUrl}/api/health-forms/family/${username}`)
            .then((res) => {
                this.setState({ familyFiles: res.data })
                console.log(res);
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
                                && <div className="alert alert-warning text-center col-6 offset-3 my-1" role="alert">
                                    Galimybė įkelti pažymą turite tik pateikę prašymą į darželį.
                             </div>}
                            {this.state.familyFiles.length > 0
                                && <HealthFormTableComponent
                                    familyFiles={this.state.familyFiles}
                                    handleDownload={this.handleDownload}
                                />}
                            {this.state.familyFiles.length === 0
                                && <div className="alert alert-warning text-center col-6 offset-3 my-1" role="alert">
                                    Jūs dar nesate pridėję sveikatos pažymų.
                             </div>}
                        </div>
                    </div>
                </div>
                <Footer />
            </div>
        )
    }
}

export default HealthFormListContainer;
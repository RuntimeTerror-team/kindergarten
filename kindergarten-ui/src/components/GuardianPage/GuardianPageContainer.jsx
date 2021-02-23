import React, { Component } from 'react';
import GuardianNavigationComponent from '../Navigation/GuardianNavigationComponent'
import HeaderComponent from '../Header/HeaderComponent';
import ApplicationListContainer from './../ApplicationList/ApplicationListContainer'
import Footer from '../Footer/Footer';

import '../../styles/pages.css';

class GuardianPageContainer extends Component {
    render() {
        return (
            <div className="footerBottom">
                <HeaderComponent userRole="ROLE_GUARDIAN" />
                <div className="container py-4">
                    <div className="row">
                        <GuardianNavigationComponent />
                        <div className="col-8">
                            <h1 className="mb-5 text-center">Prašymai</h1>
                            <ApplicationListContainer/>
                        </div>
                    </div>
                </div>
                <Footer />
            </div>
        )
    }
}

export default GuardianPageContainer;
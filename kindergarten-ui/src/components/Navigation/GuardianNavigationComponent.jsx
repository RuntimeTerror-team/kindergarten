import axios from 'axios';
import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import baseUrl from '../../AppConfig';
import urls from '../../constants/urls';
import logo from '../../images/logo.png';

class ESNavigationComponent extends Component {
    constructor() {
        super();
        this.state = {
            isActiveQueue: false
        }
    }

    componentDidMount = () => {
        axios
            .get(`${baseUrl}/api/queues`)
            .then((res) => {
                this.setState({ queues: res.data })
            })
            .then(() => {
                if (this.state.queues.length > 0) {
                    let activeArr = this.state.queues.filter(q => q.status === "ACTIVE");
                    if (activeArr.length > 0) {
                        this.setState({ isActiveQueue: true })
                    }
                }
            })
            .catch((err) => console.log(err));

    }

    render() {
        return (
            <div className="templatemo-sidebar">
                <div className="text-center" style={{ height: "135px" }}>
                    <img src={logo} alt="logo" className="logo mx-auto" />
                </div>
                <nav className="templatemo-left-nav">
                    <Link to={`${urls.guardian.applicationBase}`}
                        className={`${window.location.pathname === `/kindergarten${urls.guardian.applicationBase}` ? "active" : ""} left-nav-link`}>
                        Prašymai</Link>
                    <Link to={`${urls.guardian.applicationBase}/new`}
                        className={`${window.location.pathname === `/kindergarten${urls.guardian.applicationBase}/new` ? "active" : ""} left-nav-link`}>
                        Prašymo pildymas</Link>
                    <Link to={`${urls.guardian.healthFormBase}`}
                        className={`${window.location.pathname === `/kindergarten${urls.guardian.healthFormBase}` ? "active" : ""} left-nav-link`}>
                        Sveikatos pažymos</Link>
                    <Link to={`${urls.guardian.applicationBase}/passwordChange`}
                        className={`${window.location.pathname === `/kindergarten${urls.guardian.applicationBase}/passwordChange` ? "active" : ""} left-nav-link`}>
                        Mano paskyra</Link>
                    <Link to={`${urls.guardian.statisticsBase}`}
                        className={`${window.location.pathname === `/kindergarten${urls.guardian.statisticsBase}` ? "active" : ""} left-nav-link`}>
                        Statistika</Link>
                </nav>
            </div >
        )
    }
}

export default ESNavigationComponent




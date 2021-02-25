import axios from 'axios';
import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import baseUrl from '../../AppConfig';
import urls from '../../constants/urls'

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
            <nav className="nav flex-column col-4">
                <Link to={`${urls.guardian.applicationBase}`} className={`btn btn-main mb-2 w-100 ${window.location.pathname === `/kindergarten${urls.guardian.applicationBase}` ? "btn-active" : ""}`} >
                    Prašymai
            </Link>
                {this.state.isActiveQueue
                    &&
                    <Link to={`${urls.guardian.applicationBase}/new`} className={`btn btn-main mb-2 w-100 ${window.location.pathname === `/kindergarten${urls.guardian.applicationBase}/new`
                        ? "btn-active" : ""}`} >
                        Prašymo pildymas
                </Link>}
                <Link to={`${urls.guardian.applicationBase}/passwordChange`} className={`btn btn-main mb-2 w-100 ${window.location.pathname === `/kindergarten${urls.guardian.applicationBase}/passwordChange` ? "btn-active" : ""}`} >
                    Mano paskyra
            </Link>
            </nav>
        )
    }
}

export default ESNavigationComponent




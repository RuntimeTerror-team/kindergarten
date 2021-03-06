import React, { Component } from 'react';
import axios from 'axios';
import baseUrl from '../../AppConfig';
import AdminNavigationComponent from '../Navigation/AdminNavigationComponent';
import HeaderComponent from '../Header/HeaderComponent';
import Footer from '../Footer/Footer';
import QueueListComponent from './QueueListComponent';
import positions from '../../constants/positions';

class QueueListContainer extends Component {
    constructor(props) {
        super(props);
        this.state = {
            queues: [],
            isActiveQueue: false,
            message: "",
            messageStyle: ""
        }
    }

    alertTimer = null;

    componentDidMount = () => {
        axios
            .get(`${baseUrl}/api/queues`)
            .then((res) => {
                this.setState({ queues: res.data })
            })
            .then(() => {
                if (this.state.queues.length > 0) {
                    let activeArr = this.state.queues.filter(q => q.status !== "INACTIVE");
                    if (activeArr.length > 0) {
                        this.setState({ isActiveQueue: true })
                    }
                }
            })
            .catch((err) => console.log(err));
    }

    componentWillUnmount = () => {
        if (this.alertTimer) {
            clearTimeout(this.alertTimer);
        }
    }

    handleSubmit = (e) => {
        e.preventDefault();

        axios
            .post(`${baseUrl}/api/queues`, {
                "openingDate": new Date().toISOString()
            })
            .then(() => {
                this.setState({ message: "Eilė sukurta sėkmingai" })
                this.setState({ messageStyle: "alert alert-success" })
                this.alertTimer = setTimeout(() => {
                    this.setState({ message: "" })
                    this.setState({ messageStyle: "" })
                }, 3000);
                axios
                    .get(`${baseUrl}/api/queues`)
                    .then((res) => {
                        this.setState({ queues: res.data })
                    })
                    .then(() => {
                        if (this.state.queues.length > 0) {
                            let activeArr = this.state.queues.filter(q => q.status !== "INACTIVE");
                            if (activeArr.length > 0) {
                                this.setState({ isActiveQueue: true, isCreating: false })
                            }
                        }
                    })
                    .catch((err) => console.log(err));
            })
            .catch((err) => {
                this.setState({ message: "Klaida. Eilės sukurti nepavyko" })
                this.setState({ messageStyle: "alert alert-danger" })
                this.alertTimer = setTimeout(() => {
                    this.setState({ message: "" })
                    this.setState({ messageStyle: "" })
                }, 3000);
                console.log(err);
            });
    }

    render() {
        return (
            <div className="footerBottom">
                <HeaderComponent userRole="ROLE_ADMIN" />
                <div className={`${positions.bodyContainer}`}>
                    <div className="row">
                        <AdminNavigationComponent />
                        <div className={`${positions.userPagePosition}`}>
                            <h1 className="mb-5 text-center">Eilių administravimas</h1>
                            {<QueueListComponent
                                queues={this.state.queues}
                                handleSubmit={this.handleSubmit}
                                isActiveQueue={this.state.isActiveQueue}
                                message={this.state.message}
                                messageStyle={this.state.messageStyle}
                            />}
                        </div>
                    </div>
                </div>
                <Footer />
            </div>
        )
    }
}

export default QueueListContainer;
import React, { Component } from 'react';
import axios from 'axios';
import baseUrl from '../../AppConfig';
import HeaderComponent from '../Header/HeaderComponent';
import Footer from '../Footer/Footer';
import EsQueueListComponent from './EsQueueListComponent';
import ESNavigationComponent from '../Navigation/ESNavigationComponent';

class EsQueueListContainer extends Component {
    constructor(props) {
        super(props);
        this.state = {
            queues: [],
            queue: {
                registrationClosingDt: "",
                closingDt: ""
            },
            errors: {},
            message: "",
            messageStyle: "",
            isUpdating: false,
            updatingId: ""
        }
    }

    alertTimer = null;
    refreshTimer = null;

    componentDidMount = () => {
        axios
            .get(`${baseUrl}/api/queues`)
            .then((res) => {
                this.setState({ queues: res.data })
            })
            .then(() => {
                let active = this.state.queues.filter(q => q.status === "ACTIVE" && q.registrationClosingDate);
                if (active.length > 0) {
                    this.refreshtTimer = setTimeout(() => {
                        this.refresh();
                    }, new Date(active[0].registrationClosingDate) - new Date());
                }
            })
            .catch((err) => console.log(err));
    }

    componentWillUnmount = () => {
        if (this.alertTimer) {
            clearTimeout(this.alertTimer);
        }
        if (this.refreshTimer) {
            clearTimeout(this.refreshTimer);
        }
    }

    toggleUpdate = (e) => {
        this.setState({})

        if (e.target.id) {
            this.setState({ updatingId: e.target.id })
            this.setState({ message: "" })
            this.setState({ messageStyle: "" })
            this.setState({ errors: {} })
        }
        this.setState({ isUpdating: !this.state.isUpdating })
    }

    handleChange = ({ target: input }) => {
        const errors = { ...this.state.errors };
        const errorMessage = this.validateProperty(input);
        if (errorMessage) errors[input.name] = errorMessage;
        else delete errors[input.name];

        const queue = { ...this.state.queue };
        queue[input.name] = input.value;
        this.setState({ queue, errors });

        this.setState({ message: "", messageStyle: "" });
    }

    validateProperty = ({ name, value }) => {
        if (name === "registrationClosingDt") {
            return "";
        }
    }

    validate = () => {
        const errors = {};

        const { queue } = this.state;

        if (queue.registrationClosingDt.trim() === '')
            errors.registrationClosingDt = "is-invalid"

        return Object.keys(errors).length === 0 ? null : errors;
    }

    handleClosingRegistration = (e) => {
        e.preventDefault();

        let { queue } = this.state;
        const errors = this.validate();

        this.setState({ errors: errors || {} });
        if (errors) {
            this.setState({ message: "Datos išsaugoti nepavyko" })
            this.setState({ messageStyle: "alert alert-danger" })
            return;
        }

        axios
            .put(`${baseUrl}/api/queues/${this.state.updatingId}`, {
                "registrationClosingDate": new Date(queue.registrationClosingDt).toISOString()
            })
            .then(() => {

                axios
                    .get(`${baseUrl}/api/queues`)
                    .then((res) => {
                        this.setState({ queues: res.data })
                        this.setState({ isUpdating: false });
                        this.setState({ message: "Data išsaugota sėkmingai" })
                        this.setState({ messageStyle: "alert alert-success" })
                        this.alertTimer = setTimeout(() => {
                            this.setState({ message: "" })
                            this.setState({ messageStyle: "" })
                        }, 3000);
                    })
                    .then(() => {
                        let active = this.state.queues.filter(q => q.status === "ACTIVE" && q.registrationClosingDate)[0].registrationClosingDate;

                        this.refreshtTimer = setTimeout(() => {
                            this.refresh();
                        }, new Date(active) - new Date());
                    })
                    .catch((err) => console.log(err));
            })
            .catch((err) => {
                if (err.response.status && err.response.status === 409) {
                    this.setState({ message: err.response.data })
                    this.setState({ messageStyle: "alert alert-danger" })
                    this.alertTimer = setTimeout(() => {
                        this.setState({ message: "" })
                        this.setState({ messageStyle: "" })
                    }, 3000);
                } else {
                    console.log(err);
                }
            });
    }

    refresh = () => {
        window.location.reload()
    }

    handleApprove = (e) => {
        axios
            .put(`${baseUrl}/api/queues/closing/${e.target.id}`, {
                "closingDate": new Date().toISOString()
            })
            .then(() => {
                axios
                    .get(`${baseUrl}/api/queues`)
                    .then((res) => {
                        this.setState({ queues: res.data })
                        this.setState({ isUpdating: false });
                        this.setState({ message: "Eilė uždaryta sėkmingai" })
                        this.setState({ messageStyle: "alert alert-success" })
                        this.alertTimer = setTimeout(() => {
                            this.setState({ message: "" })
                            this.setState({ messageStyle: "" })
                        }, 3000);
                    })
                    .catch((err) => console.log(err));
            })
            .catch((err) => {
                this.setState({ message: "Klaida. Eilės uždaryti nepavyko" })
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
                <HeaderComponent userRole="ROLE_EDUCATION_SPECIALIST" />
                <div className="container py-4">
                    <div className="row">
                        <ESNavigationComponent />
                        <div className="col-8">
                            <h1 className="mb-5 text-center">Eilių administravimas</h1>
                            {<EsQueueListComponent
                                queues={this.state.queues}
                                queue={this.state.queue}
                                errors={this.state.errors}
                                handleChange={this.handleChange}
                                toggleUpdate={this.toggleUpdate}
                                isUpdating={this.state.isUpdating}
                                handleClosingRegistration={this.handleClosingRegistration}
                                message={this.state.message}
                                messageStyle={this.state.messageStyle}
                                refresh={this.refresh}
                                handleApprove={this.handleApprove}
                            />}
                        </div>
                    </div>
                </div>
                <Footer />
            </div>
        )
    }
}

export default EsQueueListContainer;
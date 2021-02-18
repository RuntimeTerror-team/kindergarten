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

    timer = null;

    componentDidMount = () => {
        axios
            .get(`${baseUrl}/api/queues`)
            .then((res) => {
                this.setState({ queues: res.data })
            })
            .catch((err) => console.log(err));
    }

    componentWillUnmount = () => {
        if (this.timer) {
            clearTimeout(this.timer);
        }
    }

    toggleUpdate = (e) => {
        if (e.target.id) {
            this.setState({ isUpdating: !this.state.isUpdating })
            this.setState({ updatingId: e.target.id })
            this.setState({ message: "" })
            this.setState({ messageStyle: "" })
        }
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
        if (name === "closingDt") {
            return "";
        }
    }

    validate = () => {
        const errors = {};

        const { queue } = this.state;

        if (queue.registrationClosingDt.trim() === '')
            errors.registrationClosingDt = "is-invalid"

        if (queue.closingDt.trim() === '')
            errors.closingDt = "is-invalid"

        return Object.keys(errors).length === 0 ? null : errors;
    }

    handleUpdate = (e) => {
        e.preventDefault();

        let { queue } = this.state;
        const errors = this.validate();

        this.setState({ errors: errors || {} });
        if (errors) {
            this.setState({ message: "Datų išsaugoti nepavyko" })
            this.setState({ messageStyle: "alert alert-danger" })
            return;
        }

        axios
            .put(`${baseUrl}/api/queues/${this.state.updatingId}`, {
                "closingDate": new Date(queue.closingDt).toISOString(),
                "registrationClosingDate": new Date(queue.registrationClosingDt).toISOString()
            })
            .then(() => {

                axios
                    .get(`${baseUrl}/api/queues`)
                    .then((res) => {
                        this.setState({ queues: res.data })
                        this.setState({ isUpdating: false });
                        this.setState({ message: "Datos išsaugotos sėkmingai" })
                        this.setState({ messageStyle: "alert alert-success" })
                        this.timer = setTimeout(() => {
                            this.setState({ message: "" })
                            this.setState({ messageStyle: "" })
                        }, 1500);
                    })
                    .catch((err) => console.log(err));
            })
            .catch((err) => {
                this.setState({ message: "Klaida. Datų išsaugoti nepavyko" })
                this.setState({ messageStyle: "alert alert-danger" })
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
                                handleUpdate={this.handleUpdate}
                                message={this.state.message}
                                messageStyle={this.state.messageStyle}
                                updatingId={this.state.updatingId}
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
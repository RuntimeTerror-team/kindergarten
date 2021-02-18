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
                openingDate: "",
                registrationClosingDate: "",
                closingDate: "",
                id: ""
            },
            errors: {},
            message: "",
            messageStyle: "",
            isUpdating: false,
            updatingId: ""
        }
    }

    componentDidMount = () => {
        axios
            .get(`${baseUrl}/api/queues`)
            .then((res) => {
                this.setState({ queues: res.data })
            })
            .catch((err) => console.log(err));
    }
    componentDidUpdate = () => {
        console.log(this.state.errors);
    }

    toggleCreation = () => {
        this.setState({ isCreating: !this.state.isCreating })
    }

    toggleUpdate = (id) => {
        this.setState({ isUpdating: !this.state.isUpdating })
        console.log(id);
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
        if (name === "openingDate") {
            return "";
        }
        if (name === "registrationClosingDate") {
            return "";
        }
    }

    validate = () => {
        const errors = {};

        const { queue } = this.state;
        console.log(queue.openingDate.trim() === '');
        if (queue.openingDate.trim() === '')
            errors.openingDate = "is-invalid"

        if (queue.registrationClosingDate.trim() === '')
            errors.openingDate = "is-invalid"

        return Object.keys(errors).length === 0 ? null : errors;
    }

    handleSubmit = (e) => {
        e.preventDefault();

        let { queue } = this.state;

        const errors = this.validate();
        this.setState({ errors: errors || {} });
        console.log(errors);
        if (errors) {
            this.setState({ esMessage: "Eilės sukurti nepavyko" })
            this.setState({ esMessageStyle: "alert alert-danger" })
            return;
        }

        axios
            .post(`${baseUrl}/api/queues`, {
                "openingDate": new Date(queue.openingDate).toISOString()
            })
            .then(() => {
                this.setState({ message: "Eilė sukurta sėkmingai" })
                this.setState({ messageStyle: "alert alert-success" })
                queue = { openingDate: "" };
                this.setState({ queue })
                axios
                    .get(`${baseUrl}/api/queues`)
                    .then((res) => {
                        this.setState({ queues: res.data })
                    })
                    .catch((err) => console.log(err));
            })
            .catch((err) => {
                this.setState({ message: "Klaida. Eilės sukurti nepavyko" })
                this.setState({ messageStyle: "alert alert-danger" })
                console.log(err);
            });
    }


    handleUpdate = (e) => {
        e.preventDefault();
        this.toggleUpdate();
        console.log(e.target.id);

        let { queue } = this.state;

        const errors = this.validate();
        this.setState({ errors: errors || {} });

        console.log(new Date(queue.registrationClosingDate).toISOString());
        if (errors) {
            this.setState({ esMessage: "Datos išsaugoti nepavyko" })
            this.setState({ esMessageStyle: "alert alert-danger" })
            return;
        }

        console.log(new Date(queue.registrationClosingDate).toISOString());
        axios
            .post(`${baseUrl}/api/queues/${queue.id}`, {
                "closingDate": queue.closingDate,
                "registrationClosingDate": new Date(queue.registrationClosingDate).toISOString()
            })
            .then(() => {
                this.setState({ esMessage: "Data išsaugota sėkmingai" })
                this.setState({ esMessageStyle: "alert alert-success" })
                queue = {
                    openingDate: "",
                    registrationClosingDate: "",
                    closingDate: ""
                };
                this.setState({ queue })
                axios
                    .get(`${baseUrl}/api/queues`)
                    .then((res) => {
                        this.setState({ queues: res.data })
                    })
                    .catch((err) => console.log(err));
            })
            .catch((err) => {
                this.setState({ esMessage: "Klaida. Datos išsaugoti nepavyko" })
                this.setState({ esMessageStyle: "alert alert-danger" })
                console.log(err);
            });

        this.toggleUpdate();
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
import React, { Component } from 'react';
import axios from 'axios';
import baseUrl from '../../AppConfig';
import AdminNavigationComponent from '../Navigation/AdminNavigationComponent';
import HeaderComponent from '../Header/HeaderComponent';
import Footer from '../Footer/Footer';
import QueueListComponent from './QueueListComponent';

class QueueListContainer extends Component {
    constructor(props) {
        super(props);
        this.state = {
            queues: [],
            isCreating: false,
            queue: { openingDate: "" },
            errors: {},
            message: "",
            messageStyle: "",
            userRole: ""
        }
    }

    componentDidMount = () => {
        axios
            .get(`${baseUrl}/api/queues`)
            .then((res) => {
                this.setState({ queues: res.data })
            })
            .catch((err) => console.log(err));

        axios
            .get(`${baseUrl}/loggedRole`)
            .then((res) => {
                this.setState({ userRole: res.data })
                console.log(res.data);
            })
            .catch(err => console.log(err))
    }

    toggleCreation = () => {
        this.setState({ isCreating: !this.state.isCreating })
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
    }

    validate = () => {
        const errors = {};

        const { queue } = this.state;
        if (queue.openingDate.trim() === '')
            errors.openingDate = "is-invalid"

        return Object.keys(errors).length === 0 ? null : errors;
    }

    handleSubmit = (e) => {
        e.preventDefault();
        let { queue } = this.state;

        const errors = this.validate();
        this.setState({ errors: errors || {} });
        if (errors) {
            this.setState({ message: "Eilės sukurti nepavyko" })
            this.setState({ messageStyle: "alert alert-danger" })
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

    render() {
        return (
            <div className="footerBottom">
                <HeaderComponent userRole={this.state.userRole} />
                <div className="container py-4">
                    <div className="row">
                        <AdminNavigationComponent />
                        <div className="col-8">
                            <h1 className="mb-5 text-center">Eilių administravimas</h1>
                            {this.state.userRole === "ROLE_ADMIN" && <QueueListComponent
                                queues={this.state.queues}
                                toggleCreation={this.toggleCreation}
                                isCreating={this.state.isCreating}
                                queue={this.state.queue}
                                errors={this.state.errors}
                                handleSubmit={this.handleSubmit}
                                handleChange={this.handleChange}
                            />}
                            {this.state.userRole === "ROLE_EDUCATION_SPECIALIST" && <QueueListComponent
                                queues={this.state.queues}
                                toggleCreation={this.toggleCreation}
                                isUpdating={this.state.isUpdating}
                                queue={this.state.queue}
                                errors={this.state.errors}
                                handleSubmit={this.handleSubmit}
                                handleChange={this.handleChange}
                                userRole={this.state.userRole}
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
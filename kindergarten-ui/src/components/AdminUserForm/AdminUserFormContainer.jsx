import axios from 'axios';
import React, { Component } from 'react';
import baseUrl from "../../AppConfig";
import HeaderComponent from '../Header/HeaderComponent';
import Footer from '../Footer/Footer';
import AdminUserFormComponent from './AdminUserFormComponent';
import AdminNavigationComponent from '../Navigation/AdminNavigationComponent';

class AdminUserFormContainer extends Component {
    constructor() {
        super();
        this.state = {
            users: [],
            firstname: "",
            lastname: "",
            role: "",
            firstnameLength: "",
            lastnameLength: "",
            isCreated: false,
            createdUsername: "",
            changedPassword: false
        }
    }

    componentDidMount = () => {
        axios
            .get(`${baseUrl}/api/users`)
            .then((res) => {
                this.setState({ users: res.data });
            })
            .catch((err) => console.log(err))
    }

    handleChange = (e) => {
        const { name, value } = e.target;

        this.setState({ [name]: value });

        if (value.trim().length > 30 || value.trim().length < 2) {
            if (name === "firstname") {
                this.setState({ firstnameLength: "is-invalid" });
            } else {
                this.setState({ lastnameLength: "is-invalid" });
            }
        } else {
            if (name === "firstname") {
                this.setState({ firstnameLength: "" });
            } else {
                this.setState({ lastnameLength: "" });
            }
        }

        if (this.state.isCreated) {
            this.setState({ isCreated: false });
            this.setState({ createdUsername: "" });
        }
    }

    validate = (fname, lname) => {
        if (fname.trim().length < 2 || fname.trim().length > 30) {
            this.setState({ firstnameLength: "is-invalid" });
        }

        if (lname.trim().length < 2 || lname.trim().length > 30) {
            this.setState({ lastnameLength: "is-invalid" });
        }
    }

    handleSubmit = (e) => {
        e.preventDefault();

        if (this.state.isCreated) {
            this.setState({ isCreated: false });
            this.setState({ createdUsername: "" });
        }

        let typedFirstname = e.target.firstname.value;
        let typedLastname = e.target.lastname.value;

        this.validate(typedFirstname, typedLastname);

        if (typedFirstname.trim().length >= 2
            && typedFirstname.trim().length <= 30
            && typedLastname.trim().length >= 2
            && typedLastname.trim().length <= 30) {
            axios
                .post(`${baseUrl}/api/users/admin`,
                    {
                        firstName: typedFirstname,
                        lastName: typedLastname,
                        role: e.target.role.value
                    })
                .then(res => {
                    this.setState({ isCreated: true });
                    this.setState({ createdUsername: res.data })

                    axios
                        .get(`${baseUrl}/api/users`)
                        .then((res) => {
                            this.setState({ users: res.data });
                        })
                        .catch((err) => console.log(err))
                })
                .catch(err => console.log(err));

            this.setState({ firstname: "" })
            this.setState({ lastname: "" })
            this.setState({ role: "" })
            this.setState({ firstnameLength: "" })
            this.setState({ lastnameLength: "" })
        }
    }

    downloadUserData = (e) => {
        axios
            .request({
                url: `${baseUrl}/api/user-data/${e.target.id}`,
                method: 'GET',
                responseType: 'blob'
            })
            .then(({ data }) => {
                const downloadUrl = window.URL.createObjectURL(new Blob([data]));
                const link = document.createElement('a');
                link.href = downloadUrl;
                link.setAttribute('download', 'duomenys.zip');
                document.body.appendChild(link);
                link.click();
                link.remove();
            })
            .catch((err) => console.log(err))
    }

    restoreOriginalPassword = (e) => {

        let usernameDto = {
            username: e.target.value
        }

        axios.post(baseUrl + "/api/users/restore", usernameDto)
              .then(res => this.setState({changedPassword: true}))
              .catch(err => console.log(err))
    }

    closeAlert = (e) => {

        this.setState({changedPassword: false})
    }

    render() {
        return (
            <div className="templatemo-flex-row">
                <AdminNavigationComponent />
                <div className="templatemo-content light-gray-bg col px-0">
                    <HeaderComponent userRole="ROLE_ADMIN" />
                    <div className="templatemo-content-container">
                        <h1 className="mb-5 text-center page-name"><strong>Paskyrų administravimas</strong></h1>
                        <AdminUserFormComponent
                            handleSubmit={this.handleSubmit}
                            handleChange={this.handleChange}
                            isCreated={this.state.isCreated}
                            changedPassword={this.state.changedPassword}
                            closeAlert={this.closeAlert}
                            downloadUserData={this.downloadUserData}
                            restoreOriginalPassword={this.restoreOriginalPassword}
                            {...this.state}
                        />
                        <Footer />
                    </div>
                </div>
            </div>
        )
    }
}

export default AdminUserFormContainer;
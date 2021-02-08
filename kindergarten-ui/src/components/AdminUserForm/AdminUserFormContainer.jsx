import Axios from 'axios';
import React, { Component } from 'react';
import baseUrl from "../../AppConfig";
import ServicesContext from "../../context/ServicesContext";
import AdminUserTableComponent from '../AdminUserTable/AdminUserTableComponent';
import AdminUserFormComponent from './AdminUserFormComponent';


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
            createdUsername: ""
        }
    }

    componentDidMount = () => {
        Axios
        .get(`${baseUrl}/api/users`)
        .then((res) => {
            this.setState({users: res.data});
        })
        .catch((err) => console.log(err))
    }

    handleChange = (e) => {
        const { name, value } = e.target;

        this.setState({ [name]: value });

        if (value.trim().length > 30 || value.trim().length < 2) {
            if (name === "firstname") {
                this.setState({firstnameLength: "is-invalid"});
            } else {
                this.setState({lastnameLength: "is-invalid"});
            }
        } else {
            if (name === "firstname") {
                this.setState({firstnameLength: ""});
            } else {
                this.setState({lastnameLength: ""});
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

        if (typedFirstname.length >= 2 
            && typedFirstname.length <=30
            && typedLastname.length >= 2
            && typedLastname.length <=30) {
            Axios
                .post(`${baseUrl}/api/users/admin`,
                    {
                        firstName: typedFirstname,
                        lastName: typedLastname,
                        role: e.target.role.value
                    })
                .then(res => {
                    this.setState({ isCreated: true });
                    this.setState({ createdUsername: res.data })

                    Axios
                    .get(`${baseUrl}/api/users`)
                    .then((res) => {
                        this.setState({users: res.data});
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


    render() {

        return (
            <div>
                <AdminUserFormComponent
                    handleSubmit={this.handleSubmit}
                    handleChange={this.handleChange}
                    {...this.state}
                />
                {this.state.users.length > 0 && <AdminUserTableComponent
                    users={this.state.users}
                />}
            </div>
        )

    }

}

AdminUserFormContainer.contextType = ServicesContext;

export default AdminUserFormContainer;
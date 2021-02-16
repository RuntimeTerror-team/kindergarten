import Axios from 'axios';
import React, { Component } from 'react';
import baseUrl from '../../AppConfig'
import ESNavigationComponent from '../Navigation/ESNavigationComponent';
import HeaderComponent from '../Header/HeaderComponent';
import Footer from '../Footer/Footer';
import GroupCreationFormComponent from './GroupCreationFormComponent';

class GroupCreationFormContainer extends Component {
    constructor(props) {
        super(props);
        this.state = {
            kindergartenId: "",
            groups: [],
            ageRanges: [],
            title: "",
            ageRangeId: "",
            childrenCount: "",
            titleValidation: "",
            ageRangeValidation: "",
            childrenCountValidation: "",
            message: "",
            messageStyle: "",
            kindergarten: ""
        }
    }

    componentDidMount = () => {
        this.setState({ kindergartenId: this.props.match.params.id })

        Axios
            .get(`${baseUrl}/api/kindergartens/${this.props.match.params.id}/groups`)
            .then((res) => {
                this.setState({ groups: res.data });
            })
            .catch((err) => console.log(err));

        Axios
            .get(`${baseUrl}/api/ageRanges`)
            .then((res) => {
                this.setState({ ageRanges: res.data })
            })
            .catch((err) => console.log(err));

        Axios
            .get(`${baseUrl}/api/kindergartens/${this.props.match.params.id}`)
            .then((res) => {
                this.setState({ kindergarten: res.data })
            })
            .catch((err) => console.log(err));
    }

    handleFormChange = (e) => {
        const { name, value } = e.target;
        this.setState({ [name]: value });

        if (name === "title") {
            if (value.trim().length < 5 || value.trim().length > 20) {
                this.setState({ titleValidation: "is-invalid" })
            } else {
                this.setState({ titleValidation: "" })
            }
        }
        if (name === "ageRangeId") {
            if (value !== "Pasirinkti...") {
                this.setState({ ageRangeValidation: "" })
            } else {
                this.setState({ ageRangeValidation: "is-invalid" })
            }
        }
        if (name === "childrenCount") {
            if (value >= 0 && value <= 99) {
                this.setState({ childrenCountValidation: "" })
            } else {
                this.setState({ childrenCountValidation: "is-invalid" })
            }
        }

        this.setState({ message: "" })
        this.setState({ messageStyle: "" })
    }

    validateBlank = () => {
        if (this.state.title.trim().length === 0) {
            this.setState({ titleValidation: "is-invalid" })
        }
        if (this.state.ageRangeId.length === 0) {
            this.setState({ ageRangeValidation: "is-invalid" })
        }
        if (this.state.childrenCount.length === 0) {
            this.setState({ childrenCountValidation: "is-invalid" })
        }
    }

    handleGroupCreation = (e) => {
        e.preventDefault();

        this.validateBlank();

        if ((this.state.titleValidation === "" && this.state.title.trim().length !== 0)
            && (this.state.ageRangeValidation === "" && this.state.ageRangeId.length !== 0)
            && (this.state.childrenCountValidation === "" && this.state.childrenCount.trim().length !== 0)) {
            Axios
                .post(`${baseUrl}/api/kindergartens/${this.state.kindergartenId}/groups/${e.target.ageRangeId.value}`, {
                    childrenCount: e.target.childrenCount.value,
                    id: 0,
                    title: e.target.title.value
                })
                .then(() => {
                    Axios
                        .get(`${baseUrl}/api/kindergartens/${this.state.kindergartenId}/groups`)
                        .then((res) => {
                            this.setState({ groups: res.data })
                        })
                        .catch((err) => console.log(err));
                })
                .then(() => {
                    this.setState({ message: "Darželio grupė sėkmingai išsaugota" })
                    this.setState({ messageStyle: "alert alert-success" })
                    e.target.reset();
                })
                .catch((err) => {
                    // we can put a message from backend why saving not succeeded
                    this.setState({ message: "Darželio grupės sukurti nepavyko. Pasitikrinkite duomenis." })
                    this.setState({ messageStyle: "alert alert-danger" })
                    console.log(err)
                });
        } else {
            this.setState({ message: "Darželio grupės sukurti nepavyko. Pasitikrinkite duomenis." })
            this.setState({ messageStyle: "alert alert-danger" })
        }
    }

    render() {
        return (
            <div>
                <div className="footerBottom">
                    <HeaderComponent userRole="ROLE_EDUCATION_SPECIALIST" />
                    <div className="container py-4">
                        <div className="row">
                            <ESNavigationComponent />
                            <div className="col-8">
                                <h1 className="text-center">Kurti darželio grupę</h1>
                                <p className="text-center"><strong>{this.state.kindergarten.title}</strong> {this.state.kindergarten.address}</p>
                                <GroupCreationFormComponent
                                    groups={this.state.groups}
                                    ageRanges={this.state.ageRanges}
                                    otherProps={this.state}
                                    handleFormChange={this.handleFormChange}
                                    handleGroupCreation={this.handleGroupCreation}
                                    titleValidation={this.state.titleValidation}
                                    ageRangeValidation={this.state.ageRangeValidation}
                                    childrenCountValidation={this.state.childrenCountValidation}
                                    kindergartenId={this.state.kindergartenId}
                                    message={this.state.message}
                                    messageStyle={this.state.messageStyle}
                                />
                            </div>
                        </div>
                    </div>
                    <Footer />
                </div>
            </div>

        )
    }

}

export default GroupCreationFormContainer;
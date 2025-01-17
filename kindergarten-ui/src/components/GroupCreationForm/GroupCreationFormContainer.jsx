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
            ageRangeId: "",
            childrenCount: "",
            ageRangeValidation: "",
            childrenCountValidation: "",
            message: "",
            messageStyle: "",
            duplicateMessage: "",
            duplicateMessageStyle: "",
            kindergarten: "",
            group: null,
            buttonStatus: ""
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

        let path = window.location.pathname;

        if (path.split("/")[path.split("/").length - 1] !== "new") {
            Axios
                .get(`${baseUrl}/api/kindergartens/${this.props.match.params.id}/groups/${this.props.match.params.groupId}`)
                .then((res) => {
                    this.setState({ group: res.data });
                    this.setState({ childrenCount: res.data.childrenCount.toString() });
                })
                .catch((err) => console.log(err));
        }

        Axios
            .get(`${baseUrl}/api/queues`)
            .then((res) => {
                let thisYearQueues = res.data.filter(q => new Date(q.openingDate).getFullYear() === new Date().getFullYear());

                if (thisYearQueues.length === 1) {
                    if (thisYearQueues[0].status === "ACTIVE") {
                        this.setState({ buttonStatus: "Keisti skaičių" })
                    } else if (thisYearQueues[0].status === "LOCKED") {
                        this.setState({ buttonStatus: "Didinti skaičių" })
                    } else {
                        this.setState({ buttonStatus: "Negalima keisti dydžio" })
                    }
                } else {
                    this.setState({ buttonStatus: "Negalima keisti dydžio" })
                }

            })
            .catch((err) => console.log(err));
    }

    handleFormChange = (e) => {
        const { name, value } = e.target;
        this.setState({ [name]: value });

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
        if (this.state.ageRangeId.length === 0) {
            this.setState({ ageRangeValidation: "is-invalid" })
        }
        if (this.state.childrenCount.length === 0) {
            this.setState({ childrenCountValidation: "is-invalid" })
        }
    }

    findAgeRangeById = (id) => {
        return this.state.groups.find((group) => {
            return group.ageRange.id === +id
        })
    }

    handleGroupCreation = (e) => {
        e.preventDefault();

        this.validateBlank();

        this.setState({ duplicateMessage: "" })
        this.setState({ duplicateMessageStyle: "" })

        if (this.state.buttonStatus === "Didinti skaičių" && this.props.match.params.groupId && this.state.childrenCount <= this.state.group.childrenCount) {
            this.setState({ duplicateMessage: "Galite tik didinti vaikų skaičių grupėje" })
            this.setState({ duplicateMessageStyle: "alert alert-danger" })
            return;
        }

        if (this.findAgeRangeById(this.state.ageRangeId)) {

            this.setState({ duplicateMessage: "Toks amžiaus intervalas jau išsaugotas kitoje grupėje" })
            this.setState({ duplicateMessageStyle: "alert alert-danger" })

        } else if (this.props.match.params.groupId && (this.state.childrenCountValidation === "" && this.state.childrenCount.trim().length !== 0)) {
            Axios
                .put(`${baseUrl}/api/kindergartens/${this.state.kindergartenId}/groups/${this.props.match.params.groupId}/update`, {
                    childrenCount: e.target.childrenCount.value
                })
                .then((res) => {
                    this.setState({ group: res.data });
                    this.setState({ message: "Grupė sėkmingai atnaujinta" })
                    this.setState({ messageStyle: "alert alert-success" })
                })
                .catch((err) => console.log(err))

        } else if ((this.state.ageRangeValidation === "" && this.state.ageRangeId.length !== 0)
            && (this.state.childrenCountValidation === "" && this.state.childrenCount.trim().length !== 0)) {
            Axios
                .post(`${baseUrl}/api/kindergartens/${this.state.kindergartenId}/groups/${e.target.ageRangeId.value}`, {
                    childrenCount: e.target.childrenCount.value,
                    id: 0
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
                    this.setState({ message: "Darželio grupės sukurti nepavyko. Pasitikrinkite duomenis." })
                    this.setState({ messageStyle: "alert alert-danger" })
                    console.log(err)
                });
        } else {
            this.setState({ message: "Darželio grupės sukurti/atnaujinti nepavyko. Pasitikrinkite duomenis." })
            this.setState({ messageStyle: "alert alert-danger" })
        }

    }

    render() {
        return (
            <div className="templatemo-flex-row">
                <ESNavigationComponent />
                <div className="templatemo-content light-gray-bg col px-0">
                    <HeaderComponent userRole="ROLE_EDUCATION_SPECIALIST" />
                    <div className="templatemo-content-container">
                        <h1 className="mb-5 text-center page-name"><strong>Kurti darželio grupę</strong></h1>
                        <GroupCreationFormComponent
                            groups={this.state.groups}
                            ageRanges={this.state.ageRanges}
                            otherProps={this.state}
                            handleFormChange={this.handleFormChange}
                            handleGroupCreation={this.handleGroupCreation}
                            ageRangeValidation={this.state.ageRangeValidation}
                            childrenCountValidation={this.state.childrenCountValidation}
                            kindergartenId={this.state.kindergartenId}
                            message={this.state.message}
                            messageStyle={this.state.messageStyle}
                            duplicateMessage={this.state.duplicateMessage}
                            duplicateMessageStyle={this.state.duplicateMessageStyle}
                            group={this.state.group}
                            childrenCount={this.state.childrenCount}
                        />
                        <Footer />
                    </div>
                </div>
            </div>
        )
    }

}

export default GroupCreationFormContainer;
import Axios from 'axios';
import React, { Component } from 'react';
import GroupInfoComponent from '../GroupInfo/GroupInfoComponent'
import baseUrl from '../../AppConfig'
import ESNavigationComponent from '../Navigation/ESNavigationComponent';
import HeaderComponent from '../Header/HeaderComponent';
import Footer from '../Footer/Footer';

class GroupInfoContainer extends Component {
    constructor(props) {
        super(props);
        this.state = {
            kindergartenId: "",
            groups: [],
            kindergarten: "",
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
            .get(`${baseUrl}/api/kindergartens/${this.props.match.params.id}`)
            .then((res) => {
                this.setState({ kindergarten: res.data })
            })
            .catch((err) => console.log(err));


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

    render() {
        return (
            <div>
                <div className="footerBottom">
                    <HeaderComponent userRole="ROLE_EDUCATION_SPECIALIST" />
                    <div className="container py-4">
                        <div className="row">
                            <ESNavigationComponent />
                            <div className="col-8">
                                <h1 className="text-center">Darželio grupių sąrašas</h1>
                                <p className="mb-5 text-center"><strong>{this.state.kindergarten.title}</strong> {this.state.kindergarten.address}</p>

                                <GroupInfoComponent
                                    groups={this.state.groups}
                                    kindergartenId={this.state.kindergartenId}
                                    buttonStatus={this.state.buttonStatus}
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

export default GroupInfoContainer;
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
            <div className="templatemo-flex-row">
                <ESNavigationComponent />
                <div className="templatemo-content light-gray-bg col px-0">
                    <HeaderComponent userRole="ROLE_EDUCATION_SPECIALIST" />
                    <div className="templatemo-content-container">
                        <h1 className="mb-5 text-center page-name"><strong>Darželio grupių sąrašas</strong></h1>
                        <GroupInfoComponent
                            groups={this.state.groups}
                            kindergartenId={this.state.kindergartenId}
                            buttonStatus={this.state.buttonStatus}
                        />
                        <Footer />
                    </div>
                </div>
            </div>
        )
    }
}

export default GroupInfoContainer;
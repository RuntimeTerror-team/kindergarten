import Axios from 'axios';
import React, {Component} from 'react';
import { withRouter } from "react-router-dom";
import GroupFormComponent from './GroupFormComponent';
import baseUrl from "../../AppConfig";
import ServicesContext from "../../context/ServicesContext";
import  '../../styles/groupsForm.css';


class GroupFormContainer extends Component {
    constructor(){

        super();
        this.state = {

            ageRangesList: [],
            fromAge: "",
            toAge: "",
            fromAgeFieldValidation: "",
            toAgeFieldValidation: "",
            invalidInterval: false,
            requestMessage: "",
            messageStyle: "",
        }
    }


    componentDidMount(){

        Axios.get(baseUrl + "/api/ageRanges")
        .then(res => { this.setState({ageRangesList: res.data})})
        .catch(err => console.log(err));

    }

    handleChangeFromAge = (e) => {
        e.preventDefault();
        this.setState({fromAge: e.target.value})
    }

    handleChangeToAge = (e) => {
        e.preventDefault();
        this.setState({toAge: e.target.value})
    }

    handleSubmit = (e) => {
        e.preventDefault();

        this.setState({fromAgeFieldValidation: ""})
        this.setState({toAgeFieldValidation: ""})
        this.setState({invalidInterval: false})
        this.setState({requestMessage: ""})
        this.setState({messageStyle: ""})

        this.validate(this.state.fromAge, this.state.toAge);

        if(this.validInterval(this.state.fromAge, this.state.toAge)){

            let ageRange = {

                minAge: this.state.fromAge,
                maxAge: this.state.toAge
            }

        Axios
        .post(baseUrl + "/api/ageRanges", ageRange)
        .then(res => {

            this.setState({requestMessage: res.data.message})

            if(res.data.status){

                this.setState({messageStyle: "alert alert-success mt-4"})
            } else{

                this.setState({messageStyle: "alert alert-danger mt-4"})
            }
        }

        )
        .catch(err => console.log(err));

        } 
        this.setState({fromAge: ""})
        this.setState({toAge: ""})
       
    }

    validate = (fromAge, toAge) => {

        if (fromAge === "") {

            this.setState({ fromAgeFieldValidation: "is-invalid" });
        }

        if (toAge === "") {

            this.setState({ toAgeFieldValidation: "is-invalid" });
        }

    }

    validInterval = (fromAge, toAge) => {

        if(fromAge > toAge && fromAge !== "" && toAge !== ""){

            this.setState({invalidInterval: true})
            return false;
        }

        if(fromAge <= toAge && fromAge !== "" && toAge !== ""){

            return true

        } else{

            return false
        }

    }
            
    render(){

        return(

            <div className="groupsRegistration">

            <GroupFormComponent

            fromAge={this.state.fromAge}
            toAge={this.state.toAge}
            fromAgeFieldValidation={this.state.fromAgeFieldValidation}
            toAgeFieldValidation={this.state.toAgeFieldValidation}
            invalidInterval={this.state.invalidInterval}
            requestMessage={this.state.requestMessage}
            messageStyle={this.state.messageStyle}
            onSubmit={this.handleSubmit}
            onFromAgeChange={this.handleChangeFromAge}
            onToAgeChange={this.handleChangeToAge}
       
            />
           
            </div>
        )

    }

    }

    GroupFormContainer.contextType = ServicesContext;

export default withRouter(GroupFormContainer);
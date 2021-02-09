import React, { Component } from "react";
import { withRouter } from "react-router-dom";
import ServicesContext from "../../context/ServicesContext";
import Axios from 'axios';
import baseUrl from '../../AppConfig';
import ApplicationComponent from './ApplicationComponent';

class ApplicationContainer extends Component{

    constructor() {
        super();
        this.state = {
            kinderGartenList: [],
            optionsValuesList:[],
            showChoices: false,
            name: "",
            surname: "",
            birthDate: "",
            id: "",
            street: "",
            city: "",
            priorities: [
              {value: "isLivingInCity", isChecked: false},
              {value: "isAdopted", isChecked: false},
              {value: "isMultiChild", isChecked: false},
              {value: "isGuardianStudent", isChecked: false},
              {value: "isGuardianDisabled", isChecked: false}
            ]
        };
      }

      

      componentDidMount(){

        Axios.get(baseUrl + "/api/kindergartens")
          .then(res => this.setState({kinderGartenList: res.data}))
          .catch(err => console.log(err));

          Axios.get(baseUrl + "/api/children")
          .then(res => this.setState({children: res.data}))
          .catch(err => console.log(err));

      }


      handleChangeOptions = (e) =>{

        e.preventDefault();
        
        if(this.state.optionsValuesList.length !== 5 && this.state.optionsValuesList.indexOf(e.target.value) === -1){

        this.state.optionsValuesList.push(e.target.value);

        console.log(this.state.optionsValuesList);

        }
      }

      handleChosenKindergartens = (e)=>{

        e.preventDefault();
        this.setState({showChoices:true})

      }

      handleDeleteSelection = (e)=>{

        e.preventDefault();
        let newArray = this.state.optionsValuesList.filter(kindergarten => kindergarten !== e.target.value);
        this.setState({optionsValuesList: newArray});

        }

        handleChildDetails = (e) =>{

          e.preventDefault();

          this.setState({[e.target.name]: e.target.value})
        }

        handleCheckPriorities = (e) => {
          let priorities = this.state.priorities
          priorities.forEach(priority => {
             if (priority.value === e.target.value)
                priority.isChecked =  e.target.checked
          })
          this.setState({priorities: priorities})
        }

      handleSubmit = (e) =>{
        
        e.preventDefault();
        this.setState({showChoices: false})
        this.setState({optionsValuesList: []})
        this.setState({priorities: [
          {value: "isLivingInCity", isChecked: false},
          {value: "isAdopted", isChecked: false},
          {value: "isMultiChild", isChecked: false},
          {value: "isGuardianStudent", isChecked: false},
          {value: "isGuardianDisabled", isChecked: false}
        ]})
        this.setState({name: ""})
        this.setState({surname: ""})
        this.setState({birthDate: ""})
        this.setState({id: ""})
        this.setState({street: ""})
        this.setState({city: ""})
      }



      render(){

        return(

            <ApplicationComponent
              kinderGartenList={this.state.kinderGartenList}
              optionsValuesList={this.state.optionsValuesList}
              showChoices={this.state.showChoices}
              name={this.state.name}
              surname={this.state.surname}
              birthDate={this.state.birthDate}
              id={this.state.id}
              street={this.state.street}
              city={this.state.city}
              priorities={this.state.priorities}
              handleOnOptionsChange={this.handleChangeOptions}
              onChosenKindergartens={this.handleChosenKindergartens}
              onDeleteSelection={this.handleDeleteSelection}
              onChildDetailsChange={this.handleChildDetails}
              onPrioritiesChange={this.handleCheckPriorities}
              onSubmit={this.handleSubmit}
            />

        )
      }
}

ApplicationContainer.contextType = ServicesContext;

export default withRouter(ApplicationContainer);
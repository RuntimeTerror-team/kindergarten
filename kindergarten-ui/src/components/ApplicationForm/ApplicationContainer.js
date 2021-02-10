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
            currentStep: 1,
            kinderGartenList: [],
            optionsValuesList:[],
            showChoices: false,
            guardianName: "",
            guardianSurname: "",
            guardianId: "",
            guardianPhone: "",
            guardianAddress: "",
            guardianCity: "",
            guardianPostalCode: "",
            guardianEmail: "",
            secondGuardianName: "",
            secondGuardianSurname: "",
            secondGuardianId: "",
            secondGuardianPhone: "",
            secondGuardianAddress: "",
            secondGuardianCity: "",
            secondGuardianPostalCode: "",
            secondGuardianEmail: "",
            childName: "",
            childSurname: "",
            childBirthDate: "",
            childId: "",
            childStreet: "",
            childCity: "",
            showSecondGuardianForm: false,
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

      next = () =>{
        let currentStep = this.state.currentStep
        currentStep = currentStep >= 3? 4: currentStep + 1
        this.setState({
          currentStep: currentStep
        })
      }
    
      prev = () => {
        let currentStep = this.state.currentStep
        currentStep = currentStep <= 1? 1: currentStep - 1
        this.setState({
          currentStep: currentStep
        })
      }

      previousButton = () =>{
        let currentStep = this.state.currentStep;
        if(currentStep !==1){
          return (
            <button 
            className="btn btn-secondary" 
            type="button" 
            onClick={this.prev}>
                Atgal
            </button>
          )
        }
        return null;
      }
      
      nextButton = () =>{
        let currentStep = this.state.currentStep;
        if(currentStep <4){
          return (
            <button 
            className="btn btn-primary float-right" 
            type="button" 
            onClick={this.next}>
              Toliau
            </button> 
          )
        }
        return null;
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

        handleDetails = (e) =>{

          e.preventDefault();

          this.setState({[e.target.name]: e.target.value})
        }

        fillSecondGuardian = (e) =>{

          e.preventDefault();
          this.setState({showSecondGuardianForm: !this.state.showSecondGuardianForm});

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
        this.setState({showSecondGuardianForm: false});
        this.setState({guardianName: ""})
        this.setState({guardianSurname: ""})
        this.setState({guardianId: ""})
        this.setState({guardianPhone: ""})
        this.setState({guardianAddress: ""})
        this.setState({guardianCity: ""})
        this.setState({guardianPostalCode: ""})
        this.setState({guardianEmail: ""})
        this.setState({secondGuardianName: ""})
        this.setState({secondGuardianSurname: ""})
        this.setState({secondGuardianId: ""})
        this.setState({secondGuardianPhone: ""})
        this.setState({secondGuardianAddress: ""})
        this.setState({secondGuardianCity: ""})
        this.setState({secondGuardianPostalCode: ""})
        this.setState({secondGuardianEmail: ""})
        this.setState({childName: ""})
        this.setState({childSurname: ""})
        this.setState({childBirthDate: ""})
        this.setState({childId: ""})
        this.setState({childStreet: ""})
        this.setState({childCity: ""})
        this.setState({currentStep: 1})
      }



      render(){

        return(

            <ApplicationComponent
              currentStep={this.state.currentStep}
              kinderGartenList={this.state.kinderGartenList}
              optionsValuesList={this.state.optionsValuesList}
              showChoices={this.state.showChoices}
              guardianName={this.state.guardianName}
              guardianSurname={this.state.guardianSurname}
              guardianId={this.state.guardianId}
              guardianPhone={this.state.guardianPhone}
              guardianAddress={this.state.guardianAddress}
              guardianCity={this.state.guardianCity}
              guardianPostalCode={this.state.guardianPostalCode}
              guardianEmail={this.state.guardianEmail}
              secondGuardianName={this.state.secondGuardianName}
              secondGuardianSurname={this.state.secondGuardianSurname}
              secondGuardianId={this.state.secondGuardianId}
              secondGuardianPhone={this.state.secondGuardianPhone}
              secondGuardianAddress={this.state.secondGuardianAddress}
              secondGuardianCity={this.state.secondGuardianCity}
              secondGuardianPostalCode={this.state.secondGuardianPostalCode}
              secondGuardianEmail={this.state.secondGuardianEmail}
              childName={this.state.childName}
              childSurname={this.state.childSurname}
              childBirthDate={this.state.childBirthDate}
              childId={this.state.childId}
              childStreet={this.state.childStreet}
              childCity={this.state.childCity}
              priorities={this.state.priorities}
              showSecondGuardianForm={this.state.showSecondGuardianForm}
              handleOnOptionsChange={this.handleChangeOptions}
              onChosenKindergartens={this.handleChosenKindergartens}
              onDeleteSelection={this.handleDeleteSelection}
              onDetailsChange={this.handleDetails}
              fillSecondGuardian={this.fillSecondGuardian}
              onPrioritiesChange={this.handleCheckPriorities}
              onSubmit={this.handleSubmit}
              prev={this.prev}
              next={this.next}
              previousButton={this.previousButton}
              nextButton={this.nextButton}
            />

        )
      }
}

ApplicationContainer.contextType = ServicesContext;

export default withRouter(ApplicationContainer);
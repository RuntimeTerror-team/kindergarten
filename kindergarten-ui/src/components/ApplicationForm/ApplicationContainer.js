import React, { Component } from "react";
import GuardianNavigationComponent from '../Navigation/GuardianNavigationComponent'
import ApplicationFormHeader from '../Header/ApplicationFormHeader';
import Footer from '../Footer/Footer';
import { withRouter } from "react-router-dom";
import urls from '../../constants/urls';
import Axios from 'axios';
import baseUrl from '../../AppConfig';
import ApplicationComponent from './ApplicationComponent'
import '../../styles/pages.css';

class ApplicationContainer extends Component {

  constructor(props) {
    super(props);
    this.state = {
      username: "",
      userPerson: {},
      currentStep: 1,
      kinderGartenList: [],
      optionsValuesList: [],
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
        { value: "isLivingInCity", isChecked: false },
        { value: "isAdopted", isChecked: false },
        { value: "isMultiChild", isChecked: false },
        { value: "isGuardianStudent", isChecked: false },
        { value: "isGuardianDisabled", isChecked: false }
      ],
      guardianNameValidation: "",
      guardianSurnameValidation: "",
      guardianIdValidation: "",
      guardianPhoneValidation: "",
      guardianAddressValidation: "",
      guardianCityValidation: "",
      guardianPostalCodeValidation: "",
      guardianEmailValidation: "",
      secondGuardianNameValidation: "",
      secondGuardianSurnameValidation: "",
      secondGuardianIdValidation: "",
      secondGuardianPhoneValidation: "",
      secondGuardianAddressValidation: "",
      secondGuardianCityValidation: "",
      secondGuardianPostalCodeValidation: "",
      secondGuardianEmailValidation: "",
      childNameValidation: "",
      childSurnameValidation: "",
      childBirthDateValidation: "",
      childIdValidation: "",
      childStreetValidation: "",
      childCityValidation: "",
      emptyInputsMessage: "",
      emptyInputsMessageStyle: "",
      emptyChildInputsMessage: "",
      emptyChildInputsMessageStyle: "",
      emptyGuardianInputsMessage: "",
      emptyGuardianInputsMessageStyle: "",
      noneKindergartenSelectedMessage: "",
      noneKindergartenSelectedMessageStyle: "",
      guardian: [],
      secondGuardian: [],
      child: [],
      childMessage: "",
      childMessageStyle: "",
      guardianMessage: "",
      guardianMessageStyle: "",
      secondGuardianMessage: "",
      secondGuardianMessageStyle: "",
      childAdded: false,
      noChildMessage: "",
      noChildMessageStyle: "",
      guardianAdded: false,
      noGuardianMessage: "",
      noGuardianMessageStyle: "",
      guardianButtonText: "Redaguoti",
      isDisabled: true,
      isActiveQueue: true
    };
  }

  componentDidMount() {
    Axios
      .get(`${baseUrl}/api/queues`)
      .then((res) => {
        this.setState({ queues: res.data })
      })
      .then(() => {
        if (this.state.queues.length > 0) {
          let activeArr = this.state.queues.filter(q => q.status === "ACTIVE");
          if (activeArr.length > 0) {
            this.setState({ isActiveQueue: true })
          } else {
            this.setState({ isActiveQueue: false })
          }
        } else {
          this.setState({ isActiveQueue: false })
          console.log("not active");
        }
      })
      .then(() => {
        Axios.get(baseUrl + "/api/kindergartens")
          .then(res => this.setState({ kinderGartenList: res.data }))
          .catch(err => console.log(err));

        Axios.get(baseUrl + "/api/children")
          .then(res => this.setState({ children: res.data }))
          .catch(err => console.log(err));

        Axios.get(baseUrl + "/api/queues")
          .then(res => {
            this.setState({ activeQueues: res.data })
            console.log("ilgis" + this.state.activeQueues.length)
            this.state.activeQueues.length === 0 ? this.setState({ currentStep: 5 }) : this.setState({ currentStep: 1 })
          })
          .catch(err => console.log(err))

        Axios
          .get(`${baseUrl}/loggedUsername`)
          .then((res) => {
            this.setState({ username: res.data })
            Axios
              .get(baseUrl + "/api/users/" + this.state.username + "/details")
              .then((res) => {
                this.setState({ userPerson: res.data.personDetails });
                this.setState({ guardianName: this.state.userPerson.firstName })
                this.setState({ guardianSurname: this.state.userPerson.lastName })
                this.setState({ guardianId: this.state.userPerson.personalCode })
                this.setState({ guardianPhone: this.state.userPerson.phoneNumber })
                this.setState({ guardianAddress: this.state.userPerson.address })
                this.setState({ guardianCity: this.state.userPerson.cityEnum })
                this.setState({ guardianPostalCode: this.state.userPerson.postalCode })
                this.setState({ guardianEmail: this.state.userPerson.email })
              })
              .catch((err) => console.log(err))
          })
          .catch((err) => console.log(err))
      })
      .catch((err) => console.log(err));
  }

  next = () => {
    let currentStep = this.state.currentStep
    currentStep = currentStep === 1 ? 2 : 2
    this.setState({
      currentStep: currentStep
    })
  }

  prev = () => {
    let currentStep = this.state.currentStep
    currentStep = currentStep === 1 ? 1 : 1
    this.setState({
      currentStep: currentStep
    })
  }

  previousButton = () => {
    let currentStep = this.state.currentStep;
    if (currentStep !== 1) {
      return (
        <button
          className="btn btn-yellow float-right mr-1 mt-2"
          type="button"
          onClick={this.prev}>
          Atgal
        </button>
      )
    }
    return null;
  }

  nextButton = () => {
    let currentStep = this.state.currentStep;
    if (currentStep === 1) {
      return (
        <button
          className="btn btn-info"
          type="button"
          onClick={this.next}>
          Toliau
        </button>
      )
    }
    return null;
  }


  handleChangeOptions = (e) => {

    e.preventDefault();


    if (this.state.optionsValuesList.length !== 5 && this.state.optionsValuesList.indexOf(e.target.value) === -1) {

      this.state.optionsValuesList.push(e.target.value);
      this.setState({ noneKindergartenSelectedMessage: "" })
      this.setState({ noneKindergartenSelectedMessageStyle: "" })

    }
  }

  handleChosenKindergartens = (e) => {

    e.preventDefault();
    this.setState({ showChoices: true })

  }

  handleDeleteSelection = (e) => {

    e.preventDefault();
    let newArray = this.state.optionsValuesList.filter(kindergarten => kindergarten !== e.target.value);
    this.setState({ optionsValuesList: newArray });

  }

  handleDetails = (e) => {

    e.preventDefault();

    let { name, value } = e.target;
    this.setState({ [name]: value });
    this.checkInputs(name, value)


  }


  handleChildSave = (e) => {

    e.preventDefault();

    if (this.checkEmptyChildInputs(this.state.childName, this.state.childSurname, this.state.childId, this.state.childStreet, this.state.childCity)) {

      this.setState({ emptyChildInputsMessage: "Užpildykite privalomus laukus" })
      this.setState({ emptyChildInputsMessageStyle: "alert alert-danger mt-4" })
      this.setState({ applicationMessage: "" })
      this.setState({ applicationMessageStyle: "" })

    } else {

      if (this.childValidation(this.state.childName, this.state.childSurname, this.state.childId, this.state.childStreet, this.state.childCity)) {

        let city = this.state.childCity.toUpperCase();

        if (city !== "VILNIUS") {

          city = "OTHER";
        }

        let child = {

          firstName: this.state.childName,
          lastName: this.state.childSurname,
          personalCode: this.state.childId,
          address: this.state.childStreet,
          cityEnum: city
        }


        Axios.post(baseUrl + "/api/persons", child)
          .then(res => {

            if (res.status === 201 || res.status === 200) {
              this.setState({ childMessage: "Vaiko duomenys sėkmingai išsaugoti" })
              this.setState({ childMessageStyle: "alert alert-success mt-4" })
              this.setState({ childAdded: true })
              this.setState({ noChildMessage: "" })
              this.setState({ noChildMessageStyle: "" })
              this.setState({ emptyChildInputsMessage: "" })
              this.setState({ emptyChildInputsMessageStyle: "" })
              this.setState({ childRegistratedMessage: "" })
              this.setState({ childRegistratedMessageStyle: "" })
              this.setState({ applicationMessage: "" })
              this.setState({ applicationMessageStyle: "" })
            }


            Axios.get(baseUrl + "/api/persons/byPersonalCode/" + this.state.childId)
              .then(res => { this.setState({ child: res.data }) })
              .catch(err => { console.log(err) });
          }
          )
          .catch(err => {
            this.setState({ childRegistratedMessage: "Vaikas su tokiu nurodytu asmens kodu jau yra išsaugotas kitame prašyme" })
            this.setState({ childRegistratedMessageStyle: "alert alert-danger mt-4" })
            this.setState({ childMessageStyle: "" })
            this.setState({ childMessage: "" })
            this.setState({ applicationMessage: "" })
            this.setState({ applicationMessageStyle: "" })
            console.log(err)
          })


      }

    }
  }

  handleGuardianSave = (e) => {

    e.preventDefault();

    if (this.state.isDisabled) {

      this.setState({ isDisabled: false });
      this.setState({ guardianButtonText: "Išsaugoti" })

    } else if (!this.state.isDisabled) {

      if (this.checkEmptyGuardianInputs(this.state.guardianName, this.state.guardianSurname, this.state.guardianId, this.state.guardianPhone,
        this.state.guardianAddress, this.state.guardianCity, this.state.guardianPostalCode, this.state.guardianEmail)) {

        this.setState({ emptyInputsMessage: "Užpildykite privalomus laukus" })
        this.setState({ emptyInputsMessageStyle: "alert alert-danger mt-4" })
        this.setState({ applicationMessage: "" })
        this.setState({ applicationMessageStyle: "" })
        this.setState({ isDisabled: false });
        this.setState({ guardianButtonText: "Išsaugoti" })
        return;

      } else {

        if (this.guardiansValidation(this.state.guardianName, this.state.guardianSurname, this.state.guardianId, this.state.guardianPhone,
          this.state.guardianAddress, this.state.guardianCity, this.state.guardianPostalCode, this.state.guardianEmail)) {

          let city = this.state.guardianCity.toUpperCase();

          if (city !== "VILNIUS") {

            city = "OTHER";
          }


          let guardian = {

            firstName: this.state.guardianName,
            lastName: this.state.guardianSurname,
            personalCode: this.state.guardianId,
            phoneNumber: this.state.guardianPhone,
            address: this.state.guardianAddress,
            cityEnum: city,
            postalCode: this.state.guardianPostalCode,
            email: this.state.guardianEmail
          }


          console.log("person id" + this.state.userPerson.id)
          Axios.put(baseUrl + "/api/persons/" + this.state.userPerson.id, guardian)
            .then(res => {

              if (res.status === 201 || res.status === 200) {
                this.setState({ guardianMessage: "Sėkmingai atnaujinti duomenys" })
                this.setState({ guardianMessageStyle: "alert alert-success mt-4" })
                this.setState({ emptyInputsMessage: "" })
                this.setState({ emptyInputsMessageStyle: "" })
                this.setState({ guardianAdded: true })
                this.setState({ noGuardianMessage: "" })
                this.setState({ noGuardianMessageStyle: "" })
                this.setState({ applicationMessage: "" })
                this.setState({ applicationMessageStyle: "" })
                this.setState({ userName: this.state.guardianName })
                this.setState({ userSurname: this.state.guardianSurname })


              }

            })
            .catch(err => console.log(err));


        }

      }

      this.setState({ isDisabled: true });
      this.setState({ guardianButtonText: "Redaguoti" })

    }

  }

  handleSecondGuardianSave = (e) => {

    e.preventDefault();

    if (this.checkEmptyGuardianInputs(this.state.secondGuardianName, this.state.secondGuardianSurname, this.state.secondGuardianId, this.state.secondGuardianPhone,
      this.state.secondGuardianAddress, this.state.secondGuardianCity, this.state.secondGuardianPostalCode, this.state.secondGuardianEmail)) {

      this.setState({ emptyGuardianInputsMessage: "Užpildykite privalomus laukus" })
      this.setState({ emptyGuardianInputsMessageStyle: "alert alert-danger mt-4" })

    } else {

      if (this.guardiansValidation(this.state.secondGuardianName, this.state.secondGuardianSurname, this.state.secondGuardianId, this.state.secondGuardianPhone,
        this.state.secondGuardianAddress, this.state.secondGuardianCity, this.state.secondGuardianPostalCode, this.state.secondGuardianEmail)) {

        let city = this.state.secondGuardianCity.toUpperCase();

        if (city !== "VILNIUS") {

          city = "OTHER";
        }


        let secondGuardian = {

          firstName: this.state.secondGuardianName,
          lastName: this.state.secondGuardianSurname,
          personalCode: this.state.secondGuardianId,
          phoneNumber: this.state.secondGuardianPhone,
          address: this.state.secondGuardianAddress,
          cityEnum: city,
          postalCode: this.state.secondGuardianPostalCode,
          email: this.state.secondGuardianEmail
        }

        Axios.post(baseUrl + "/api/persons", secondGuardian)
          .then(res => {

            console.log("status: " + res.status)

            if (res.status === 201 || res.status === 200) {
              this.setState({ secondGuardianMessage: "Vaiko atstovo duomenys sėkmingai išsaugoti" })
              this.setState({ secondGuardianMessageStyle: "alert alert-success mt-4" })
              this.setState({ emptyGuardianInputsMessage: "" })
              this.setState({ emptyGuardianInputsMessageStyle: "" })
              this.setState({ applicationMessage: "" })
              this.setState({ applicationMessageStyle: "" })
            }

            Axios.get(baseUrl + "/api/persons/byPersonalCode/" + this.state.secondGuardianId)
              .then(res => { this.setState({ secondGuardian: res.data }) })
              .catch(err => { console.log(err) });
          })
          .catch(err => { console.log(err) })

      }

    }

  }


  childValidation = (name, surname, personalCode, address, city) => {

    return (name.length >= 2 && name.length <= 30)
      && (surname.length >= 2 && surname.length <= 30)
      && (personalCode.length === 11 && /^[0-9]*$/.test(personalCode))
      && (address.length >= 8 && address.length <= 50)
      && (city.length >= 4 && city.length <= 19)

  }

  guardiansValidation = (name, surname, personalCode, phone, address, city, postalCode, email) => {

    return (name.length >= 3 && name.length <= 30)
      && (surname.length >= 2 && surname.length <= 30)
      && (personalCode.length === 11 && /^[0-9]*$/.test(personalCode))
      && (phone.length === 12 && /^\+?370[0-9]*$/.test(phone))
      && (address.length >= 8 && address.length <= 50)
      && (city.length >= 4 && city.length <= 19)
      && (postalCode.length === 5 && /^[0-9]*$/.test(postalCode))
      && (email.length && /(?=.@)/.test(email))

  }


  checkEmptyGuardianInputs = (name, surname, personalCode, phone, address, city, postalCode, email) => {

    return (name.trim() === "" || surname.trim() === "" || personalCode.trim() === "" || phone.trim() === "" || address.trim() === ""
      || city.trim() === "" || postalCode.trim() === "" || email.trim() === "")

  }

  checkEmptyChildInputs = (name, surname, personalCode, address, city) => {

    return (name.trim() === "" || surname.trim() === "" || personalCode.trim() === "" || address.trim() === "" || city.trim() === "")
  }

  checkInputs = (name, value) => {


    if (name === "guardianName") {

      if (value.trim().length < 3 || value.length > 20 || /\d/.test(value)) {

        this.setState({ guardianNameValidation: "is-invalid" })

      } else {

        this.setState({ guardianNameValidation: "" })

      }

    }

    if (name === "guardianSurname") {

      if (value.trim().length < 3 || value.length > 30 || /\d/.test(value)) {

        this.setState({ guardianSurnameValidation: "is-invalid" })

      } else {

        this.setState({ guardianSurnameValidation: "" })

      }

    }

    if (name === "guardianId") {

      if (value.trim().length !== 11 || /[^\d]/.test(value)) {

        this.setState({ guardianIdValidation: "is-invalid" })

      } else {

        this.setState({ guardianIdValidation: "" })

      }

    }

    if (name === "guardianPhone") {

      if (value.trim().length === 12 && /^\+?370[0-9]*$/.test(value)) {

        this.setState({ guardianPhoneValidation: "" })

      } else {

        this.setState({ guardianPhoneValidation: "is-invalid" })

      }

    }

    if (name === "guardianAddress") {

      if (value.trim().length < 8 || value.trim().length > 50) {

        this.setState({ guardianAddressValidation: "is-invalid" })

      } else {

        this.setState({ guardianAddressValidation: "" })

      }

    }

    if (name === "guardianCity") {

      if (value.trim().length < 4 || value.trim().length > 19) {

        this.setState({ guardianCityValidation: "is-invalid" })

      } else {

        this.setState({ guardianCityValidation: "" })

      }

    }

    if (name === "guardianPostalCode") {

      if (value.trim().length === 5 && /^[0-9]*$/.test(value)) {

        this.setState({ guardianPostalCodeValidation: "" })

      } else {

        this.setState({ guardianPostalCodeValidation: "is-invalid" })

      }

    }

    if (name === "guardianEmail") {

      if (/^[\w-.]+@([\w-]+\.)+[\w-]{2,4}$/.test(value)) {

        this.setState({ guardianEmailValidation: "" })

      } else {

        this.setState({ guardianEmailValidation: "is-invalid" })

      }

    }



    if (name === "secondGuardianName") {


      if (value.trim().length < 3 || value.length > 20 || /\d/.test(value)) {

        this.setState({ secondGuardianNameValidation: "is-invalid" })

      } else {

        this.setState({ secondGuardianNameValidation: "" })

      }

    }

    if (name === "secondGuardianSurname") {

      if (value.trim().length < 3 || value.length > 30 || /\d/.test(value)) {

        this.setState({ secondGuardianSurnameValidation: "is-invalid" })

      } else {

        this.setState({ secondGuardianSurnameValidation: "" })

      }

    }

    if (name === "secondGuardianId") {

      if (value.trim().length !== 11 || /[^\d]/.test(value)) {

        this.setState({ secondGuardianIdValidation: "is-invalid" })

      } else {

        this.setState({ secondGuardianIdValidation: "" })

      }

    }

    if (name === "secondGuardianPhone") {

      if (value.trim().length === 12 && /^\+?370[0-9]*$/.test(value)) {

        this.setState({ secondGuardianPhoneValidation: "" })

      } else {

        this.setState({ secondGuardianPhoneValidation: "is-invalid" })

      }

    }

    if (name === "secondGuardianAddress") {

      if (value.trim().length < 8 || value.trim().length > 50) {

        this.setState({ secondGuardianAddressValidation: "is-invalid" })

      } else {

        this.setState({ secondGuardianAddressValidation: "" })

      }

    }

    if (name === "secondGuardianCity") {

      if (value.trim().length < 4 || value.trim().length > 19) {

        this.setState({ secondGuardianCityValidation: "is-invalid" })

      } else {

        this.setState({ secondGuardianCityValidation: "" })

      }

    }

    if (name === "secondGuardianPostalCode") {

      if (value.trim().length === 5 && /^[0-9]*$/.test(value)) {

        this.setState({ secondGuardianPostalCodeValidation: "" })

      } else {

        this.setState({ secondGuardianPostalCodeValidation: "is-invalid" })

      }

    }

    if (name === "secondGuardianEmail") {

      if (/^[\w-.]+@([\w-]+\.)+[\w-]{2,4}$/.test(value)) {

        this.setState({ secondGuardianEmailValidation: "" })

      } else {

        this.setState({ secondGuardianEmailValidation: "is-invalid" })

      }

    }


    if (name === "childName") {

      if (value.trim().length < 3 || value.length > 20 || /\d/.test(value)) {

        this.setState({ childNameValidation: "is-invalid" })

      } else {

        this.setState({ childNameValidation: "" })

      }

    }


    if (name === "childSurname") {

      if (value.trim().length < 3 || value.length > 30 || /\d/.test(value)) {

        this.setState({ childSurnameValidation: "is-invalid" })

      } else {

        this.setState({ childSurnameValidation: "" })

      }

    }

    if (name === "childId") {

      if (value.trim().length !== 11 || /[^\d]/.test(value)) {

        this.setState({ childIdValidation: "is-invalid" })

      } else {

        this.setState({ childIdValidation: "" })

      }

    }

    if (name === "childStreet") {

      if (value.trim().length < 8 || value.trim().length > 50) {

        this.setState({ childStreetValidation: "is-invalid" })

      } else {

        this.setState({ childStreetValidation: "" })

      }

    }

    if (name === "childCity") {

      if (value.trim().length < 4 || value.trim().length > 19) {

        this.setState({ childCityValidation: "is-invalid" })

      } else {

        this.setState({ childCityValidation: "" })

      }

    }

  }

  fillSecondGuardian = (e) => {

    e.preventDefault();
    this.setState({ showSecondGuardianForm: !this.state.showSecondGuardianForm });

  }

  handleCheckPriorities = (e) => {
    let priorities = this.state.priorities
    priorities.forEach(priority => {
      if (priority.value === e.target.value)
        priority.isChecked = e.target.checked
    })
    this.setState({ priorities: priorities })
  }

  async waitforSecond() {

    await new Promise(r => setTimeout(1000));

  }

  handleSubmit = (e) => {

    e.preventDefault();

    if (this.state.optionsValuesList.length === 0) {

      this.setState({ noneKindergartenSelectedMessage: "Prašome pridėti bent vieną darželį" })
      this.setState({ noneKindergartenSelectedMessageStyle: "alert alert-danger mt-4" })
    }

    if (this.state.childAdded === false) {

      this.setState({ noChildMessage: "Prašome išsaugoti vaiko duomenys" })
      this.setState({ noChildMessageStyle: "alert alert-danger mt-4" })

    }

    else {

      let selectedKindergartens = this.state.optionsValuesList.map(title => {

        return this.state.kinderGartenList.find(kindergarten => { return kindergarten.title === title })
      })

      let kindergartensMap = {}
      let index = 0;
      selectedKindergartens.forEach(kindergarten => {

        kindergartensMap[index] = kindergarten.id;
        index++;
      });


      let application = {

        firstParentId: this.state.userPerson.id,
        childId: this.state.child.id,
        secondParentId: this.state.secondGuardian.id,
        priorityForKindergartenID: kindergartensMap,
        isAdopted: this.state.priorities[1].isChecked,
        isMultiChild: this.state.priorities[2].isChecked,
        isGuardianStudent: this.state.priorities[3].isChecked,
        isGuardianDisabled: this.state.priorities[4].isChecked
      }

      Axios.post(baseUrl + "/api/applications", application)
        .then(res => {
          if (res.status === 200) {

            this.setState({ applicationMessage: "Prašymas sėkmingai pateiktas" })
            this.setState({ applicationMessageStyle: "alert alert-success mt-4" })
            this.waitforSecond()

          }
        })
        .catch(err => { console.log(err) })

      Axios
        .get(`${baseUrl}/loggedUsername`)
        .then((res) => {
          this.setState({ username: res.data })
          Axios
            .get(baseUrl + "/api/users/" + this.state.username + "/details")
            .then((res) => {
              this.setState({ userPerson: res.data.personDetails });
              this.setState({ guardianName: this.state.userPerson.firstName })
              this.setState({ guardianSurname: this.state.userPerson.lastName })
              this.setState({ guardianId: this.state.userPerson.personalCode })
              this.setState({ guardianPhone: this.state.userPerson.phoneNumber })
              this.setState({ guardianAddress: this.state.userPerson.address })
              this.setState({ guardianCity: this.state.userPerson.cityEnum })
              this.setState({ guardianPostalCode: this.state.userPerson.postalCode })
              this.setState({ guardianEmail: this.state.userPerson.email })
              this.setState({ userName: this.state.guardianName })
              this.setState({ userSurname: this.state.guardianSurname })

            })
            .catch((err) => console.log(err))
        })
        .catch((err) => console.log(err))

      this.setState({ showChoices: false })
      this.setState({ optionsValuesList: [] })
      this.setState({
        priorities: [
          { value: "isLivingInCity", isChecked: false },
          { value: "isAdopted", isChecked: false },
          { value: "isMultiChild", isChecked: false },
          { value: "isGuardianStudent", isChecked: false },
          { value: "isGuardianDisabled", isChecked: false }
        ]
      })
      this.setState({ showSecondGuardianForm: false });
      this.setState({ guardianName: "" })
      this.setState({ guardianSurname: "" })
      this.setState({ guardianId: "" })
      this.setState({ guardianPhone: "" })
      this.setState({ guardianAddress: "" })
      this.setState({ guardianCity: "" })
      this.setState({ guardianPostalCode: "" })
      this.setState({ guardianEmail: "" })
      this.setState({ secondGuardianName: "" })
      this.setState({ secondGuardianSurname: "" })
      this.setState({ secondGuardianId: "" })
      this.setState({ secondGuardianPhone: "" })
      this.setState({ secondGuardianAddress: "" })
      this.setState({ secondGuardianCity: "" })
      this.setState({ secondGuardianPostalCode: "" })
      this.setState({ secondGuardianEmail: "" })
      this.setState({ childName: "" })
      this.setState({ childSurname: "" })
      this.setState({ childBirthDate: "" })
      this.setState({ childId: "" })
      this.setState({ childStreet: "" })
      this.setState({ childCity: "" })
      this.setState({ currentStep: 1 })
      this.setState({ noneKindergartenSelectedMessage: "" })
      this.setState({ noneKindergartenSelectedMessageStyle: "" })
      this.setState({ childMessage: "" })
      this.setState({ childMessageStyle: "" })
      this.setState({ guardianMessage: "" })
      this.setState({ guardianMessageStyle: "" })
      this.setState({ secondGuardianMessage: "" })
      this.setState({ secondGuardianMessageStyle: "" })
      this.setState({ childAdded: false })
      this.setState({ noChildMessage: "" })
      this.setState({ noChildMessageStyle: "" })
      this.setState({ guardianAdded: false })
      this.setState({ noGuardianMessage: "" })
      this.setState({ noGuardianMessageStyle: "" })
      this.setState({ guardian: [] })
      this.setState({ secondGuardian: [] })
      this.setState({ child: [] })



    }
  }



  render() {
    if (this.state.isActiveQueue) {
      return (
        <div className="footerBottom">
          <ApplicationFormHeader
            userRole="ROLE_GUARDIAN"
            name={this.state.userName}
            surname={this.state.userSurname} />
          <div className="container py-4">
            <div className="row">
              <GuardianNavigationComponent />
              <div className="col-8">
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
                  childMessage={this.state.childMessage}
                  childMessageStyle={this.state.childMessageStyle}
                  guardianMessage={this.state.guardianMessage}
                  guardianMessageStyle={this.state.guardianMessageStyle}
                  secondGuardianMessage={this.state.secondGuardianMessage}
                  secondGuardianMessageStyle={this.state.secondGuardianMessageStyle}
                  childAdded={this.state.childAdded}
                  noChildMessage={this.state.noChildMessage}
                  noChildMessageStyle={this.state.noChildMessageStyle}
                  guardianAdded={this.state.guardianAdded}
                  noGuardianMessage={this.state.noGuardianMessage}
                  noGuardianMessageStyle={this.state.noGuardianMessageStyle}
                  guardianNameValidation={this.state.guardianNameValidation}
                  guardianSurnameValidation={this.state.guardianSurnameValidation}
                  guardianIdValidation={this.state.guardianIdValidation}
                  guardianPhoneValidation={this.state.guardianPhoneValidation}
                  guardianAddressValidation={this.state.guardianAddressValidation}
                  guardianCityValidation={this.state.guardianCityValidation}
                  guardianPostalCodeValidation={this.state.guardianPostalCodeValidation}
                  guardianEmailValidation={this.state.guardianEmailValidation}
                  secondGuardianNameValidation={this.state.secondGuardianNameValidation}
                  secondGuardianSurnameValidation={this.state.secondGuardianSurnameValidation}
                  secondGuardianIdValidation={this.state.secondGuardianIdValidation}
                  secondGuardianPhoneValidation={this.state.secondGuardianPhoneValidation}
                  secondGuardianAddressValidation={this.state.secondGuardianAddressValidation}
                  secondGuardianCityValidation={this.state.secondGuardianCityValidation}
                  secondGuardianPostalCodeValidation={this.state.secondGuardianPostalCodeValidation}
                  secondGuardianEmailValidation={this.state.secondGuardianEmailValidation}
                  childNameValidation={this.state.childNameValidation}
                  childSurnameValidation={this.state.childSurnameValidation}
                  childBirthDateValidation={this.state.childBirthDateValidation}
                  childIdValidation={this.state.childIdValidation}
                  childStreetValidation={this.state.childStreetValidation}
                  childCityValidation={this.state.childCityValidation}
                  emptyInputsMessage={this.state.emptyInputsMessage}
                  emptyInputsMessageStyle={this.state.emptyInputsMessageStyle}
                  emptyChildInputsMessage={this.state.emptyChildInputsMessage}
                  emptyChildInputsMessageStyle={this.state.emptyChildInputsMessageStyle}
                  emptyGuardianInputsMessage={this.state.emptyGuardianInputsMessage}
                  emptyGuardianInputsMessageStyle={this.state.emptyGuardianInputsMessageStyle}
                  noneKindergartenSelectedMessage={this.state.noneKindergartenSelectedMessage}
                  noneKindergartenSelectedMessageStyle={this.state.noneKindergartenSelectedMessageStyle}
                  guardianButtonText={this.state.guardianButtonText}
                  isDisabled={this.state.isDisabled}
                  showSecondGuardianForm={this.state.showSecondGuardianForm}
                  handleOnOptionsChange={this.handleChangeOptions}
                  onChosenKindergartens={this.handleChosenKindergartens}
                  onDeleteSelection={this.handleDeleteSelection}
                  onDetailsChange={this.handleDetails}
                  saveChild={this.handleChildSave}
                  saveGuardian={this.handleGuardianSave}
                  saveSecondGuardian={this.handleSecondGuardianSave}
                  fillSecondGuardian={this.fillSecondGuardian}
                  onPrioritiesChange={this.handleCheckPriorities}
                  onSubmit={this.handleSubmit}
                  prev={this.prev}
                  next={this.next}
                  previousButton={this.previousButton}
                  nextButton={this.nextButton}
                />

              </div>
            </div>
          </div>
          <Footer />
        </div>
      )
    } else {
      const goApp = () => this.props.history.push(`${urls.guardian.applicationBase}`);
      return (
        <div className="container">
          <div className='m-5'>
            <div className="alert alert-warning" role="alert">
              Šiuo metu eilės yra uždarytos ir nėra galimybės pildyti prašymų.
            </div>
            <button className='btn btn-primary mt-3' onClick={goApp}>Eiti į pagrindinį puslapį</button>
          </div>
        </div>
      )
    }
  }
}


export default withRouter(ApplicationContainer);
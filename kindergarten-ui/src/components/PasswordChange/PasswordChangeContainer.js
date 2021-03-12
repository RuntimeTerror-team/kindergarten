import axios from 'axios';
import React, { Component } from 'react';
import { withRouter } from "react-router-dom";
import PasswordChangeComponent from './PasswordChangeComponent';
import GuardianNavigationComponent from '../Navigation/GuardianNavigationComponent'
import EditableGuardianInfoComponent from './EditableGuardianInfoComponent'
import ApplicationFormHeader from '../Header/ApplicationFormHeader';
import Footer from '../Footer/Footer';
import baseUrl from "../../AppConfig";
import urls from '../../constants/urls';

axios.defaults.withCredentials = true;

class PasswordChangeContainer extends Component {
  constructor() {
    super();
    this.state = {
      currentStep: 1,
      username: "",
      password: "",
      password2: "",
      oldPassword: "",
      role: "",
      detailsGot: false,
      userPerson: {},
      userName: "",
      userSurname: "",
      guardianName: "",
      guardianSurname: "",
      guardianId: "",
      guardianPhone: "",
      guardianAddress: "",
      guardianCity: "",
      guardianPostalCode: "",
      guardianEmail: "",
      passwordValidation: "",
      password2Validation: "",
      oldPasswordValidation: "",
      guardianNameValidation: "",
      guardianSurnameValidation: "",
      guardianIdValidation: "",
      guardianPhoneValidation: "",
      guardianAddressValidation: "",
      guardianCityValidation: "",
      guardianPostalCodeValidation: "",
      guardianEmailValidation: "",
      emptyInputsMessage: "",
      emptyInputsMessageStyle: "",
      notMatchingMessage: "",
      notMatchingMessageStyle: "",
      successMessage: "",
      successMessageStyle: "",
      wrongOldPasswordMessage: "",
      wrongOldPasswordMessageStyle: "",
      guardianButtonText: "Redaguoti",
      isDisabled: true,
    }
  }

  componentDidMount = () => {

    axios
      .get(`${baseUrl}/loggedUsername`)
      .then((res) => {
        this.setState({ username: res.data })
        axios
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

    axios
      .get(`${baseUrl}/loggedRole`)
      .then((res) => {
        this.setState({ role: res.data })
        console.log(res.data)

      })
      .catch((err) => console.log(err))


  }

  handleChange = (e) => {
    const { name, value } = e.target;

    this.setState({ [name]: value });

    if (this.state.passwordValidation !== "" && name === "password") {
      this.setState({ passwordValidation: "" });
    }

    if (this.state.password2Validation !== "" && name === "password2") {
      this.setState({ password2Validation: "" });
    }

    if (this.state.oldPasswordValidation !== "" && name === "oldPassword") {
      this.setState({ oldPasswordValidation: "" });
    }

    if (value.trim().length === 0 && name === "oldPassword") {
      this.setState({ oldPasswordValidation: "is-invalid" });
    }

    if (!/^(?=.*[a-ząčęėįšųū])(?=.*[A-ZĄČĘĖĮŠŲŪ])(?=.*\d)[a-ząčęėįšųūA-ZĄČĘĖĮŠŲŪ\d]{8,}$/.test(value) && name === "password") {
      this.setState({ passwordValidation: "is-invalid" })
    }

    if (!/^(?=.*[a-ząčęėįšųū])(?=.*[A-ZĄČĘĖĮŠŲŪ])(?=.*\d)[a-ząčęėįšųūA-ZĄČĘĖĮŠŲŪ\d]{8,}$/.test(value) && name === "password2") {
      this.setState({ password2Validation: "is-invalid" })
    }

  }

  resetState = () => {
    this.setState({ password: "" });
    this.setState({ password2: "" });
    this.setState({ oldPassword: "" })
    this.setState({ passwordValidation: "" });
    this.setState({ password2Validation: "" });
    this.setState({ oldPasswordValidation: "" })
  }

  handleDetails = (e) => {

    e.preventDefault();

    let { name, value } = e.target;
    this.setState({ [name]: value });
    this.checkInputs(name, value)


  }

  checkFieldsValidation = () => {

    if (this.state.guardianName.trim().length < 3 || this.state.guardianName.trim().length > 20 || /\d/.test(this.state.guardianName)) {

      this.setState({ guardianNameValidation: "is-invalid" })

    } else {

      this.setState({ guardianNameValidation: "" })

    }

    if (this.state.guardianSurname.trim().length < 3 || this.state.guardianSurname.trim().length > 30 || /\d/.test(this.state.guardianSurname)) {

      this.setState({ guardianSurnameValidation: "is-invalid" })

    } else {

      this.setState({ guardianSurnameValidation: "" })

    }

    if (this.state.guardianId.trim().length !== 11 || /[^\d]/.test(this.state.guardianId)) {

      this.setState({ guardianIdValidation: "is-invalid" })

    } else {

      this.setState({ guardianIdValidation: "" })

    }

    if (this.state.guardianPhone.trim().length === 12 && /^\+?370[0-9]*$/.test(this.state.guardianPhone)) {

      this.setState({ guardianPhoneValidation: "" })

    } else {

      this.setState({ guardianPhoneValidation: "is-invalid" })

    }

    if (this.state.guardianAddress.trim().length < 8 || this.state.guardianAddress.trim().length > 50) {

      this.setState({ guardianAddressValidation: "is-invalid" })

    } else {

      this.setState({ guardianAddressValidation: "" })

    }

    if (this.state.guardianCity.trim().length < 4 || this.state.guardianCity.trim().length > 19) {

      this.setState({ guardianCityValidation: "is-invalid" })

    } else {

      this.setState({ guardianCityValidation: "" })

    }

    if (this.state.guardianPostalCode.trim().length === 5 && /^[0-9]*$/.test(this.state.guardianPostalCode)) {

      this.setState({ guardianPostalCodeValidation: "" })

    } else {

      this.setState({ guardianPostalCodeValidation: "is-invalid" })

    }

    if (/^[\w-.]+@([\w-]+\.)+[\w-]{2,4}$/.test(this.state.guardianEmail)) {

      this.setState({ guardianEmailValidation: "" })

    } else {

      this.setState({ guardianEmailValidation: "is-invalid" })

    }
  }

  checkInputs = (name, value) => {


    if (name === "guardianName") {

      if (value.trim().length < 3 || value.trim().length > 20 || /\d/.test(value)) {

        this.setState({ guardianNameValidation: "is-invalid" })

      } else {

        this.setState({ guardianNameValidation: "" })

      }

    }

    if (name === "guardianSurname") {

      if (value.trim().length < 3 || value.trim().length > 30 || /\d/.test(value)) {

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

  }

  checkEmptyGuardianInputs = (name, surname, personalCode, phone, address, city, postalCode, email) => {

    if (name.trim() === "") {

      this.setState({ guardianNameValidation: "is-invalid" })

    } else {

      this.setState({ guardianNameValidation: "" })

    }

    if (surname.trim() === "") {

      this.setState({ guardianSurnameValidation: "is-invalid" })

    } else {

      this.setState({ guardianSurnameValidation: "" })

    }

    if (personalCode.trim() === "") {

      this.setState({ guardianIdValidation: "is-invalid" })

    } else {

      this.setState({ guardianIdValidation: "" })

    }

    if (phone.trim() === "") {

      this.setState({ guardianPhoneValidation: "is-invalid" })

    } else {

      this.setState({ guardianPhoneValidation: "" })

    }

    if (address.trim() === "") {

      this.setState({ guardianAddressValidation: "is-invalid" })

    } else {

      this.setState({ guardianAddressValidation: "" })

    }

    if (city.trim() === "") {

      this.setState({ guardianCityValidation: "is-invalid" })

    } else {

      this.setState({ guardianCityValidation: "" })

    }

    if (postalCode.trim() === "") {

      this.setState({ guardianPostalCodeValidation: "is-invalid" })

    } else {

      this.setState({ sguardianPostalCodeValidation: "" })

    }

    if (email.trim() === "") {

      this.setState({ guardianEmailValidation: "is-invalid" })

    } else {

      this.setState({ guardianEmailValidation: "" })

    }

    return (name.trim() === "" || surname.trim() === "" || personalCode.trim() === "" || phone.trim() === "" || address.trim() === ""
      || city.trim() === "" || postalCode.trim() === "" || email.trim() === "")

  }

  guardiansValidation = (name, surname, personalCode, phone, address, city, postalCode, email) => {

    return (name.trim().length >= 3 && name.trim().length <= 20)
      && (surname.trim().length >= 3 && surname.trim().length <= 30)
      && (personalCode.length === 11 && /^[0-9]*$/.test(personalCode))
      && (phone.length === 12 && /^\+?370[0-9]*$/.test(phone))
      && (address.length >= 8 && address.length <= 50)
      && (city.length >= 4 && city.length <= 19)
      && (postalCode.length === 5 && /^[0-9]*$/.test(postalCode))
      && (email.length && /^[\w-.]+@([\w-]+\.)+[\w-]{2,4}$/.test(email))

  }

  handleGuardianSave = (e) => {

    e.preventDefault();

    if (this.state.isDisabled) {

      this.setState({ isDisabled: false });
      this.setState({ guardianButtonText: "Išsaugoti" })

    } else if (!this.state.isDisabled) {

      if (this.checkEmptyGuardianInputs(this.state.guardianName, this.state.guardianSurname, this.state.guardianId, this.state.guardianPhone,
        this.state.guardianAddress, this.state.guardianCity, this.state.guardianPostalCode, this.state.guardianEmail)
        || !this.guardiansValidation(this.state.guardianName, this.state.guardianSurname, this.state.guardianId, this.state.guardianPhone,
          this.state.guardianAddress, this.state.guardianCity, this.state.guardianPostalCode, this.state.guardianEmail)) {

        this.setState({ emptyInputsMessage: "Užpildykite privalomus laukus" })
        this.setState({ emptyInputsMessageStyle: "alert alert-danger mt-4" })
        this.setState({ applicationMessage: "" })
        this.setState({ applicationMessageStyle: "" })
        this.setState({ isDisabled: false });
        this.setState({ guardianButtonText: "Išsaugoti" })
        this.checkFieldsValidation();
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
          axios.put(baseUrl + "/api/persons/" + this.state.userPerson.id, guardian)
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
                this.timer = setTimeout(() => {
                  this.setState({ guardianMessage: "" })
                  this.setState({ guardianMessageStyle: "" })
                }, 3000);


              }

            })
            .catch(err => console.log(err));


        }

      }

      this.setState({ isDisabled: true });
      this.setState({ guardianButtonText: "Redaguoti" })

    }

  }

  handleSubmit = (e) => {
    e.preventDefault();

    this.setState({ notMatchingMessage: "" })
    this.setState({ notMatchingMessageStyle: "" })
    this.setState({ successMessage: "" })
    this.setState({ successMessageStyle: "" })
    this.setState({ wrongOldPasswordMessage: "" })
    this.setState({ wrongOldPasswordMessageStyle: "" })
    let passwordFromUser = e.target.password.value;
    let password2FromUser = e.target.password2.value;
    let oldPasswordFromUser = e.target.oldPassword.value;

    this.doValidation(passwordFromUser, password2FromUser, oldPasswordFromUser);






    // if(wrongPassword){

    //     this.setState({wrongOldPasswordMessage: "Įvestas neteisingas senas slaptažodis" })
    //     this.setState({wrongOldPasswordMessageStyle: "alert alert-danger mt-4"})

    // }



    if (this.state.passwordValidation === "" && this.state.password2Validation === "" && this.state.oldPasswordValidation === ""
      && /^(?=.*[a-ząčęėįšųū])(?=.*[A-ZĄČĘĖĮŠŲŪ])(?=.*\d)[a-ząčęėįšųūA-ZĄČĘĖĮŠŲŪ\d]{8,}$/.test(passwordFromUser)
      && /^(?=.*[a-ząčęėįšųū])(?=.*[A-ZĄČĘĖĮŠŲŪ])(?=.*\d)[a-ząčęėįšųūA-ZĄČĘĖĮŠŲŪ\d]{8,}$/.test(password2FromUser)) {

      if (passwordFromUser !== password2FromUser) {
        this.setState({ notMatchingMessage: "Slaptažodžiai nesutampa. Prašome bandyti vėl" })
        this.setState({ notMatchingMessageStyle: "alert alert-danger mt-4" })
        return;
      }

      let userData = new URLSearchParams();
      userData.append('username', this.state.username);
      userData.append('password', oldPasswordFromUser);

      this.setState({ detailsGot: false })

      axios
        .post(`${baseUrl}/login`,
          userData,
          { headers: { 'Content-type': 'application/x-www-form-urlencoded' } })
        .then((res) => {

          console.log("gets executed")
          if (res.status === 200) {

            let userDto = {
              username: this.state.username,
              password: this.state.password,
              role: this.state.role
            }

            axios
              .put(`${baseUrl}/api/users`, userDto)
              .then((res) => {

                if (res.status === 200) {

                  this.setState({ successMessage: "Slaptažodis sėkmingai pakeistas" })
                  this.setState({ successMessageStyle: "alert alert-success mt-4" })
                }

                let destitation = ""

                if (this.state.role === "ROLE_GUARDIAN") {
                  destitation = urls.guardian.applicationBase;
                }

                if (this.state.role === "ROLE_EDUCATION_SPECIALIST") {
                  destitation = urls.educationSpecialist.kindergartenBase;
                }

                this.timer = setTimeout(() => {
                  this.props.history.push(destitation)
                }, 3000);

              })
              .catch((e) => { console.log(e) });
          }

        })
        .catch((e) => {
          if (e.response.status === 401) {
            this.setState({ wrongOldPasswordMessage: "Įvestas neteisingas senas slaptažodis" })
            this.setState({ wrongOldPasswordMessageStyle: "alert alert-danger mt-4" })
          }
        });


    }




  }

  doValidation = (password, password2, oldPassword) => {
    if (password.trim().length === 0 || !/^(?=.*[a-ząčęėįšųū])(?=.*[A-ZĄČĘĖĮŠŲŪ])(?=.*\d)[a-ząčęėįšųūA-ZĄČĘĖĮŠŲŪ\d]{8,}$/.test(password)) {
      this.setState({ passwordValidation: "is-invalid" });

    }

    if (password2.trim().length === 0 || !/^(?=.*[a-ząčęėįšųū])(?=.*[A-ZĄČĘĖĮŠŲŪ])(?=.*\d)[a-ząčęėįšųūA-ZĄČĘĖĮŠŲŪ\d]{8,}$/.test(password2)) {
      this.setState({ password2Validation: "is-invalid" });

    }

    if (oldPassword.trim().length === 0) {
      this.setState({ oldPasswordValidation: "is-invalid" });

    }


  }


  render() {

    return (
      <div className="templatemo-flex-row">
        <GuardianNavigationComponent />
        <div className="templatemo-content light-gray-bg col px-0">
          <ApplicationFormHeader
            userRole="ROLE_GUARDIAN"
            name={this.state.userName}
            surname={this.state.userSurname} />
          <div className="templatemo-content-container">
            <h1 className="mb-5 text-center page-name"><strong>Sveikatos pažymos</strong></h1>
            <div className="row col-12 px-0">
              <div className="col-6 px-0">
                <PasswordChangeComponent
                  password={this.state.password}
                  password2={this.state.password2}
                  oldPassword={this.state.oldPassword}
                  passwordValidation={this.state.passwordValidation}
                  password2Validation={this.state.password2Validation}
                  oldPasswordValidation={this.state.oldPasswordValidation}
                  notMatchingMessage={this.state.notMatchingMessage}
                  notMatchingMessageStyle={this.state.notMatchingMessageStyle}
                  successMessage={this.state.successMessage}
                  successMessageStyle={this.state.successMessageStyle}
                  wrongOldPasswordMessage={this.state.wrongOldPasswordMessage}
                  wrongOldPasswordMessageStyle={this.state.wrongOldPasswordMessageStyle}
                  onSubmit={this.handleSubmit}
                  onPasswordChange={this.handleChange}
                  onPassword2Change={this.handleChange}
                  onOldPasswordChange={this.handleChange}
                />
              </div>
              <div className="col-6 px-0">
                <EditableGuardianInfoComponent
                  name={this.state.guardianName}
                  surname={this.state.guardianSurname}
                  id={this.state.guardianId}
                  phone={this.state.guardianPhone}
                  address={this.state.guardianAddress}
                  city={this.state.guardianCity}
                  postalCode={this.state.guardianPostalCode}
                  email={this.state.guardianEmail}
                  message={this.state.guardianMessage}
                  messageStyle={this.state.guardianMessageStyle}
                  guardianButtonText={this.state.guardianButtonText}
                  isDisabled={this.state.isDisabled}
                  guardianNameValidation={this.state.guardianNameValidation}
                  guardianSurnameValidation={this.state.guardianSurnameValidation}
                  guardianIdValidation={this.state.guardianIdValidation}
                  guardianPhoneValidation={this.state.guardianPhoneValidation}
                  guardianAddressValidation={this.state.guardianAddressValidation}
                  guardianCityValidation={this.state.guardianCityValidation}
                  guardianPostalCodeValidation={this.state.guardianPostalCodeValidation}
                  guardianEmailValidation={this.state.guardianEmailValidation}
                  emptyInputsMessage={this.state.emptyInputsMessage}
                  emptyInputsMessageStyle={this.state.emptyInputsMessageStyle}
                  onDetailsChange={this.handleDetails}
                  saveGuardian={this.handleGuardianSave}
                />
              </div>
            </div>
            <Footer />
          </div>
        </div>
      </div>
    )
  }
}

export default withRouter(PasswordChangeContainer);
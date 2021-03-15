import axios from 'axios';
import React, { Component } from 'react';
import baseUrl from "../../AppConfig";
import HeaderComponent from '../Header/HeaderComponent';
import Footer from '../Footer/Footer';
import AdminUserFormComponent from './AdminUserFormComponent';
import AdminNavigationComponent from '../Navigation/AdminNavigationComponent';

const USERS_PER_PAGE = 10;

class AdminUserFormContainer extends Component {
    state = {
      users: [],
      firstname: "",
      lastname: "",
      role: "",
      firstnameLength: "",
      lastnameLength: "",
      isCreated: false,
      createdUsername: "",
      changedPassword: false,

      currentPage: 1,
    }

    componentDidMount() {
      this.updateUserList(this.state.currentPage);
    }


    updateUserList(currentPage) {
      currentPage -= 1;
      axios.get(baseUrl + "/api/users/search/all/?page=" + currentPage + "&size=" + USERS_PER_PAGE)
        .then((res) => {
          this.setState({
            users: res.data.content,
            totalPages: res.data.totalPages,
            totalElements: res.data.totalElements,
            currentPage: res.data.number + 1,
          });
        })
        .catch((err) => {
          console.log(err);
        });
    }

    handleChange = (e) => {
        const { name, value } = e.target;

        this.setState({ [name]: value });

        if (value.trim().length > 30 || value.trim().length < 2) {
            if (name === "firstname") {
                this.setState({ firstnameLength: "is-invalid" });
            } else {
                this.setState({ lastnameLength: "is-invalid" });
            }
        } else {
            if (name === "firstname") {
                this.setState({ firstnameLength: "" });
            } else {
                this.setState({ lastnameLength: "" });
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

        if (typedFirstname.trim().length >= 2
            && typedFirstname.trim().length <= 30
            && typedLastname.trim().length >= 2
            && typedLastname.trim().length <= 30) {
            axios
                .post(`${baseUrl}/api/users/admin`,
                    {
                        firstName: typedFirstname,
                        lastName: typedLastname,
                        role: e.target.role.value
                    })
                .then(res => {
                    this.setState({ isCreated: true });
                    this.setState({ createdUsername: res.data })
                    
                    // axios
                    //     .get(`${baseUrl}/api/users`)
                    //     .then((res) => {
                    //         this.setState({ users: res.data });
                    //     })
                    //     .catch((err) => console.log(err))
                    this.updateUserList();
                })
                .catch(err => console.log(err));

            this.setState({ 
              firstname: "" ,
              lastname: "" ,
              role: "" ,
              firstnameLength: "" ,
              lastnameLength: ""
            })
        }
    }

    downloadUserData = (e) => {
        axios
            .request({
                url: `${baseUrl}/api/user-data/${e.target.id}`,
                method: 'GET',
                responseType: 'blob'
            })
            .then(({ data }) => {
                const downloadUrl = window.URL.createObjectURL(new Blob([data]));
                const link = document.createElement('a');
                link.href = downloadUrl;
                link.setAttribute('download', 'duomenys.zip');
                document.body.appendChild(link);
                link.click();
                link.remove();
            })
            .catch((err) => console.log(err))
    }

    restoreOriginalPassword = (e) => {

        let usernameDto = {
            username: e.target.value
        }

        axios.post(baseUrl + "/api/users/restore", usernameDto)
              .then(res => this.setState({changedPassword: true}))
              .catch(err => console.log(err))
    }

    closeAlert = (e) => {

        this.setState({changedPassword: false})
    }



    firstPage = () => {
      let firstPage = 1;
      if (this.state.currentPage > firstPage) {
        this.updateUserList(firstPage);
      }
    };
  
    prevPage = () => {
      let prevPage = 1;
      if (this.state.currentPage > prevPage) {
        this.updateUserList(this.state.currentPage - prevPage);
      }
    };
  
    lastPage = () => {
      let condition = Math.ceil(this.state.totalElements / USERS_PER_PAGE);
      if (this.state.currentPage < condition) {
        this.updateUserList(condition);
      }
    };
  
    nextPage = () => {
      if (this.state.currentPage < Math.ceil(this.state.totalElements / USERS_PER_PAGE)) {
        this.updateUserList(this.state.currentPage + 1);
      }
    };
  
    changePage = (event) => {
      let targetPage = parseInt(event.target.value);
      // this.updateUserList(targetPage);
      this.setState({
        [event.target.name]: targetPage,
      }, this.updateUserList(targetPage));
    };

    // changePage = (event) => {
    //   let targetPage = parseInt(event.target.value);
    //   this.updateUserList(targetPage);
    //   this.setState({
    //     [event.target.name]: targetPage,
    //   })
    // };

    updateSearchInputValue = (ev) => {  
      let currentPage = this.state.currentPage - 1;
  
      if (!ev.target.value) {
        this.updateUserList(this.state.currentPage);
        return;
      }
  
      axios.get(baseUrl + "/api/users/search/" + ev.target.value + "?page=" + currentPage + "&size=" + USERS_PER_PAGE)
        .then((res) => {
          this.setState({
            users: res.data.content,
            totalPages: res.data.totalPages,
            totalElements: res.data.totalElements,
            currentPage: res.data.number + 1,
          });
        })
        .catch((err) => {
          console.log(err);
        });
    }

    render() {
      const { currentPage } = this.state
        return (
            <div className="templatemo-flex-row">
                <AdminNavigationComponent />
                <div className="templatemo-content light-gray-bg col px-0">
                    <HeaderComponent userRole="ROLE_ADMIN" />
                    <div className="templatemo-content-container">
                        <h1 className="mb-5 text-center page-name"><strong>Paskyrų administravimas</strong></h1>
                        <AdminUserFormComponent
                            handleSubmit={this.handleSubmit}
                            handleChange={this.handleChange}
                            isCreated={this.state.isCreated}
                            changedPassword={this.state.changedPassword}
                            closeAlert={this.closeAlert}
                            downloadUserData={this.downloadUserData}
                            restoreOriginalPassword={this.restoreOriginalPassword}
                            updateSearchInputValue={this.updateSearchInputValue}
                            currentPage={currentPage}
                            totalPages={this.state.totalPages}
                            firstPage={this.firstPage}
                            prevPage={this.prevPage}
                            lastPage={this.lastPage}
                            nextPage={this.nextPage}
                            {...this.state}
                        />
                        {/* <Footer /> */}
                    </div>
                </div>
            </div>
        )
    }
}

export default AdminUserFormContainer;
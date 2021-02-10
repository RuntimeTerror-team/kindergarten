class UserService {
    constructor() {
        this._currentUser = "";
        this._userRole = "";
        this._userFirstname = "";
        this._userLastname = "";
    }
    getCurrentUser = () => {
        return this._currentUser;
    }

    setCurrentUser = (username) => {
        this._currentUser = username;
    }

    getUserRole = () => {
        return this._userRole;
    }

    setUserRole = (role) => {
        this._userRole = role;
    }

    getUserFirstname = () => {
        return this._userFirstname;
    }

    setUserFirstname = (fname) => {
        this._userFirstname = fname;
    }

    getUserLastname = () => {
        return this._userLastname;
    }

    setUserLastname = (lname) => {
        this._userLastname = lname;
    }


    updateCurrentUser = () => { }
    updateUserRole = () => { }

    updateUserFirstname = () => { }
    updateUserLastname = () => { }

}

export default UserService;
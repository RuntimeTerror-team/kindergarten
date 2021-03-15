class UserService {
    constructor() {
        this._currentUser = "";
        this._userRole = "";
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

    updateCurrentUser = () => { }
    updateUserRole = () => { }

}

export default UserService;
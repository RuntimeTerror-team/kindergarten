class UserService {
    constructor() {
        this._currentUser = undefined;
        this._userRole = undefined;
    }
    getCurrentUser = () => {
        return this._currentUser;
    }

    setCurrentUser = (username) => {
        this._currentUser = username;
    }

    getUserRole = () => {
        return this._cartCount;
    }

    setUserRole = (role) => {
        this._userRole = role;
    }

    updateCurrentUser = () => { }
    updateUserRole = () => { }

}

export default UserService;
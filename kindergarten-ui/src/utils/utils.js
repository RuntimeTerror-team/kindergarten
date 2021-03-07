const TOKEN_KEY = 'jwt';

export const login = (role) => {
    localStorage.setItem(TOKEN_KEY, role);
}

export const logout = () => {
    localStorage.removeItem(TOKEN_KEY);
}

export const isLogin = () => {
    if (localStorage.getItem(TOKEN_KEY)) {
        return true;
    }
    return false;
}

export const getLoggedInRole = () => {
    return localStorage.getItem(TOKEN_KEY);
}
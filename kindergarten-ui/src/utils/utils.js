const TOKEN_KEY = 'jwt';

export const login = (role) => {
    let hashedRole = hashCode(role);
    localStorage.setItem(TOKEN_KEY, hashedRole);
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

export const hashCode = (s) => s.split('').reduce((a, b) => { a = ((a << 5) - a) + b.charCodeAt(0); return a & a }, 0)
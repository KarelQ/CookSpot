import tokenAxios from "./TokenService.jsx";

const REST_API_BASE_URL = 'http://localhost:8080/auth/admin';

export const isAdmin = () => {
    return tokenAxios.post(`${REST_API_BASE_URL}/check`);
};

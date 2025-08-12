// tokenAxios.js
import axios from "axios";

const tokenAxios = axios.create();

// Interceptor dodający token
tokenAxios.interceptors.request.use((config) => {
    const token = sessionStorage.getItem("sessionToken");
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
});

export default tokenAxios;

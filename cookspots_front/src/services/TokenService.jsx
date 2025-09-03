
import axios from "axios";

const tokenAxios = axios.create();


tokenAxios.interceptors.request.use((config) => {
    const token = sessionStorage.getItem("sessionToken");
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
});

export default tokenAxios;

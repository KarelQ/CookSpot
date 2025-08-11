import axios from "axios";

// Tworzymy instancję Axios
const api = axios.create({
    baseURL: "http://localhost:8080", // adres backendu
    headers: {
        "Content-Type": "application/json",
    },
});

// Interceptor dodający token do każdego requestu
api.interceptors.request.use(
    (config) => {
        const token = sessionStorage.getItem("token");
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    (error) => Promise.reject(error)
);

// Interceptor obsługujący odpowiedzi (np. 401 Unauthorized)
api.interceptors.response.use(
    (response) => response,
    (error) => {
        if (error.response && error.response.status === 401) {
            console.warn("Token wygasł lub jest nieprawidłowy. Wylogowywanie...");
            sessionStorage.removeItem("token");
            window.location.href = "/login"; // przekierowanie do logowania
        }
        return Promise.reject(error);
    }
);

export default api;

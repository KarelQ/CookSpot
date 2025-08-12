import React, {useEffect, useState} from "react";
import style from "/src/css/login.module.css";
import {login} from "../services/UserService.jsx";
import {v4 as uuidv4} from "uuid";
import {useNavigate} from "react-router-dom";

const Login = () => {
    const [messages, setMessages] = useState([""]); // Przykładowe dane

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const navigate = useNavigate();



    function loginRequest(e) {
        e.preventDefault();


        const user = {
            username,
            password,

        }
        console.log(user);

        login(user)
            .then((response) => {
                console.log(response.data);

                // jeśli serwer zwróci token
                sessionStorage.setItem("sessionToken", response.data);
                navigate(`/mainpage`);
            })
            .catch((error) => {
                if (error.response && error.response.status === 401) {
                    setMessages(["Wrong email or password"]);
                } else {
                    setMessages(["Unexpected error occurred"]);
                }
                console.error(error);
            });
    }

    return (

        <div id={"login"} className={style["base-container-login"]}>
            <div className={style["login-container"]}>
                <img src="/src/assets/logo_with_name.png" alt="logo alt"/>

                <form>
          <span className={style["messages"]}>
            {messages &&
                messages.map((message, index) => (
                    <p key={index}>{message}</p>
                ))}
          </span>
                    <input
                        name="usernme"
                        type="text"
                        placeholder="email"
                        className={style["input-text"]}
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                    />
                    <input
                        name="password"
                        type="password"
                        placeholder="password"
                        className={style["input-text"]}
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                    <button onClick={loginRequest} type="submit" className={style["input-text"]}>
                        Login
                    </button>
                    <a href="register">
                        {`Don't have an account?`} <span>Create account</span>
                    </a>
                </form>
            </div>
        </div>

        );
        };

        export default Login;

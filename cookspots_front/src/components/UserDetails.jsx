import React, {useEffect, useState} from "react";
import style from "/src/css/my-profile.module.css";
import {getSessionUser, getUserDetails} from "../services/UserService.jsx";

const Profile = () => {


    const [user, setUser] = useState({});



    useEffect(() => {
        getSessionUser().then((response) => {
            setUser(response.data);
        }).catch((error) => {
            console.error(error);
        })
    }, []);


    return (
        <>
            <style>{`
                .button  {
                border: solid 2px #FB8A22;
                border-radius: 2em;
                text-align: center;
                color: black;
                background-color: #ffffff;
                letter-spacing: 0.7px;
                width: 90%;
                padding: 0.5em;

                font-style: normal;
                font-weight: 400;
                font-size: 15px;
                line-height: 18px;
                }

                `}</style>

        <main id="profile">
            <section className={style.details}>
                {/* User Account Details */}
                <div className={`${style.post} ${style.details}`}>
                    <h3>Your Account</h3>
                    <p>Username: {user.username}</p>
                    <p>Email: {user.email}</p>
                </div>
                <div>
                    <a href="changeusername" className={`"button" ${style.details}`}>
                        Change Username
                    </a>
                    <a href="changeemail" className={`"button" ${style.details}`}>
                        Change Email
                    </a>
                    <a href="changepassword" className={`"button" ${style.details}`}>
                        Change Password
                    </a>
                </div>



                {/* User Additional Details */}

            </section>
        </main>
        </>
    );
};

export default Profile;

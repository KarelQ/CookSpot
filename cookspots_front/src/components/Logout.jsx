import {useNavigate} from "react-router-dom";
import {useEffect} from "react";



const Logout = () => {
    const navigate = useNavigate();

    useEffect(() => {
        sessionStorage.clear();
        navigate("/login");
    }, [navigate]);

    return null;
};

export default Logout;

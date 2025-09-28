import React, {useEffect, useState} from "react";
import { updateStars } from "../services/UserInteractionService.jsx"
import style from '/src/css/post-detales.module.css'
import ReportIcon from "./ReportIcon.jsx";
import StarIcon from "./StarIcon.jsx";



const StarRating = ({ idPost, vote}) => {
    const [rating, setRating] = useState(vote);

    useEffect(() => {
        setRating(vote);
    }, [vote]);

    const handleClick = async (value) => {
        setRating(value);

        try {
            await updateStars({
                idPost: idPost,
                stars: value.toString(), // backend oczekuje stringa
            });
            console.log("Wysłano ocenę:", value);
        } catch (err) {
            console.error("Błąd przy wysyłaniu oceny:", err);
        }
    };

    return (<>

        <div className={style["stars"]}>
            {[1, 2, 3, 4, 5].map((star) => (
                <StarIcon key={star} value={star} rating={rating} onClick={handleClick} />
            ))}
        </div>
            <ReportIcon idPost={idPost} />
        </>
    );
};

export default StarRating;

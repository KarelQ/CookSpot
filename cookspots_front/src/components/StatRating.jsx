import React, {useEffect, useState} from "react";
import { updateStars } from "../services/UserInteractionService.jsx"
import style from '/src/css/post-detales.module.css'


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

    return (
        <div className={style["stars"]}>
            {[1, 2, 3, 4, 5].map((star) => (
                <i
                    key={star}
                    className="material-symbols-outlined"
                    onClick={() => handleClick(star)}
                    style={{
                        color: star <= rating ? "#FB8A22" : "#ccc", // złote gwiazdki albo szare
                        marginRight: "5px",
                    }}
                >
                    star
                </i>
            ))}
        </div>
    );
};

export default StarRating;

import React from "react";
import styled from "styled-components";

const StarIcon = styled.i`
    
    margin-right: 5px;
    color: ${(props) => (props.active ? "#FB8A22" : "#ccc")};
    cursor: pointer;
    transition: color 0.2s;
    font-size:  2em;
    

    &:hover {
        color: #ffb84d;
    }
`;

const Star = ({ value, rating, onClick }) => {
    return (
        <StarIcon
            className="material-symbols-outlined"
            active={value <= rating}
            onClick={() => onClick(value)}
        >
            star
        </StarIcon>
    );
};

export default Star;

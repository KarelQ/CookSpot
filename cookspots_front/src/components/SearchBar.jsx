import React from "react";
import styled from "styled-components";

const BackgroundBorder = styled.div`
    padding: 10px;
    background-color: #2A3C4C;
    width: 60%;
    border-radius: 2em;
    display: flex;
    align-items: center;
`;

const SearchBarWrapper = styled.div`
    width: 100%;
    display: flex;
    align-items: center;
    border: solid 2px #FB8A22; /* tylko część stylów z .button */
    border-radius: 2em;
    padding: 0.5em;
    background-color: #ffffff;
    color: black;
`;

const Input = styled.input`
    width: 100%;
    text-align: left;
    margin: 0;
    box-sizing: border-box;
    outline: none;
    border: none;
`;

const SearchBar = () => {
    return (
        <BackgroundBorder>
            <SearchBarWrapper>
                <Input placeholder="Search" />
            </SearchBarWrapper>
        </BackgroundBorder>
    );
};

export default SearchBar;

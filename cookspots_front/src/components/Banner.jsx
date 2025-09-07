import React from "react";
import styled from "styled-components";
import SearchBar from "./SearchBar.jsx";


const Header = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 1em 2em;
`;

const WelcomeText = styled.h1`
  margin: 1.2em;
  font-size: 4vw;
  color: #ffffff;
`;

const Banner = ({ message, noSerchbar}) => {
    if (!message) return null;

    if (noSerchbar){
        return (
            <Header>
                <WelcomeText>{message}</WelcomeText>
            </Header>
        );
    }

    return (
        <Header>
            <WelcomeText>{message}</WelcomeText>
            <SearchBar></SearchBar>
        </Header>
    );
};

export default Banner;

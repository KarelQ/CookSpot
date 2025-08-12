import React from "react";
import styled from "styled-components";


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

const Messages = styled.div`
  text-decoration: none;
  text-align: center;
  width: 80%;
  font-size: 7px;
  color: #ff2100;
  padding: 7px;
`;

const Banner = ({ message }) => {
    if (!message) return null;

    return (
        <Header>
            <WelcomeText>{message}</WelcomeText>
        </Header>
    );
};

export default Banner;

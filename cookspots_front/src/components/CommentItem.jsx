import React from "react";
import styled from "styled-components";

const CommentContainer = styled.div`
  background-color: #2a3c4c;
  color: #d1d1d1;
  border: 2px solid #fb8a22;
  border-radius: 12px;
  padding: 5%;
  margin-left: 5%;
  margin-right: 5%;
  margin-bottom: 10px;
    
`;

const Header = styled.div`
  display: flex;
  align-items: center;
  margin-bottom: 8px;
`;

const Icon = styled.i`
  color: #fb8a22;
  margin-right: 8px;
`;

const Username = styled.span`
  font-weight: bold;
  color: #fff;
  margin-right: 10px;
`;

const Date = styled.span`
  font-size: 12px;
  color: #d1d1d1;
`;

const Content = styled.p`
  margin: 0;
  line-height: 1.4;
`;

const CommentItem = ({ username, commentContent, commentDate }) => {
    const dateOnly = commentDate.split("T")[0]; // tylko data

    return (
        <CommentContainer>
            <Header>
                <Icon className="material-symbols-outlined">account_circle</Icon>
                <Username>{username}</Username>
                <Date>{dateOnly}</Date>
            </Header>
            <Content>{commentContent}</Content>
        </CommentContainer>
    );
};

export default CommentItem;

import React, { useState } from "react";
import styled from "styled-components";
import { addComment } from "../services/UserInteractionService.jsx";
import {useNavigate} from "react-router-dom";

const FormContainer = styled.div`
    background-color: #2A3C4C;
    border-radius: 12px;
    border: 2px solid #FB8A22;
    color: #D1D1D1;
    padding: 5%;
    margin-left: 5%;
    margin-right: 5%;
    margin-bottom: 10px;
    
    
`;



const StyledForm = styled.form`
    width: 100%;
    display: flex;
    flex-direction: column; /* input nad przyciskiem */
    align-items: flex-start; /* wyrównanie dzieci do lewej */
`;

const Input = styled.textarea`
    width: 100%;
    padding: 10px;
    border-radius: 8px;
    border: 1px solid #FB8A22;
    background: #1e2a35;
    color: #fff;
    resize: none;
    min-height: 80px;
    box-sizing: border-box;
    margin-bottom: 10px; /* odstęp od przycisku */
`;

const Button = styled.button`
    background-color: #FB8A22;
    color: #fff;
    border: none;
    padding: 10px 20px;
    border-radius: 8px;
    cursor: pointer;
    font-weight: bold;

    &:disabled {
        background-color: #888;
        cursor: not-allowed;
    }
`;

const CommentForm = ({ isCommentedByLoggedUser, idPost }) => {
    const [comment, setComment] = useState("");
    const [error, setError] = useState("");
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (!comment.trim()) {
            setError("Comment can't be empty");
            return;
        }

        try {
            await addComment({
                idPost: idPost,
                comment_content: comment, // zgodnie z backendem
            });
            setComment("");
            setError("");
            navigate(`/postpage/${idPost}`);

        } catch (err) {
            console.error("error while sending comment:", err);
            setError("comment not sent.");
        }
    };

    if (isCommentedByLoggedUser) return null;

    return (
        <FormContainer>
            <StyledForm onSubmit={handleSubmit}>
                <Input
                    value={comment}
                    onChange={(e) => setComment(e.target.value)}
                    placeholder="Whrite your comment..."
                />
                {error && <p style={{ color: "red" }}>{error}</p>}
                <Button type="submit" disabled={!comment.trim()}>
                    Submit
                </Button>
            </StyledForm>
        </FormContainer>
    );
};

export default CommentForm;

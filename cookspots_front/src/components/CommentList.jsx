import React, { useEffect, useState } from "react";
import CommentItem from "./CommentItem";
import { getComments } from "../services/UserInteractionService.jsx";
import CommentForm from "./CommentForm.jsx";
import styled from "styled-components";

const NoComments = styled.p`
  text-align: center;
  color: #d1d1d1;
  font-style: italic;
  font-size: 14px;

  border: 1px dashed #fb8a22;
  border-radius: 8px;
  background-color: #2a3c4c;
    padding: 10px;
    margin-left: 5%;    
    margin-right: 5%;
    margin-bottom: 10px;
`;


const CommentsList = ({ idPost, isCommentedByLoggedUser}) => {
    const [comments, setComments] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        if (!idPost) return;

        setLoading(true);
        getComments(idPost)
            .then((response) => {
                setComments(response.data);
                setLoading(false);
            })
            .catch((err) => {
                console.error("Błąd przy pobieraniu komentarzy:", err);
                setError("Nie udało się pobrać komentarzy.");
                setLoading(false);
            });
    }, [idPost]);

    if (loading) return <p>loading comments...</p>;
    if (error) return <p>{error}</p>;

    return (
        <div>
            <CommentForm idPost={idPost} isCommentedByLoggedUser={isCommentedByLoggedUser} />

            {(!comments || comments.length === 0) ? (
                <NoComments>There are no comments yet.</NoComments>
            ) : (
                comments.map((comment, index) => (
                    <CommentItem
                        key={index}
                        username={comment.username}
                        commentContent={comment.commentContent}
                        commentDate={comment.commentDate}
                    />
                ))
            )}
        </div>
    );
};

export default CommentsList;

import { useEffect, useState } from "react";
import PostDetails from "./PostDetails.jsx";
import { useParams } from "react-router-dom";
import { postDetailsById } from "../services/PostService.jsx";

import Error from "./Error.jsx";
import {checkUserInteractions} from "../services/UserInteractionService.jsx";

const PostPage = () => {
    const { id } = useParams();

    const [post, setPost] = useState(null);
    const [interactions, setInteractions] = useState({
        idUser: "non",
        idPost: "non",
        booked: false,
        isLiked: 0,
        owner: false,
        stars: 0,
        comment: false}
    );
    const [error, setError] = useState(null);

    useEffect(() => {
        if (id) {
            postDetailsById(id)
                .then((response) => setPost(response.data))
                .catch((err) => {
                    console.error(err);
                    setError("Failed to fetch post.");
                });

            checkUserInteractions(id)
                .then((response) => {
                    // zakładam, że response.data wygląda np. { book: true, rate: 5, owner: false }
                    setInteractions(response.data);
                })
                .catch((err) => {
                    console.error(err);
                    setError("Failed to fetch post interactions.");
                });
        }
    }, [id]);

    if (error) {
        return <Error error={error} />;
    }

    if (!post) {
        return <div>Loading...</div>;
    }

    console.log(interactions);

    return (
        <PostDetails
            post={post}
            book={interactions.booked}
            rate={interactions.isLiked}
            owner={interactions.owner}
            stars={interactions.stars}
            isComment={interactions.comment}
        />
    );
};

export default PostPage;

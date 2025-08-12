import {useEffect, useState} from "react";
import {postList} from "../services/PostService.jsx";
import PostList from "../components/PostList";
import Banner from "./Banner.jsx";


const MainPagePosts = () => {
    const [posts, setPosts] = useState([]);

        useEffect(() => {
            postList().then((response) => {
                setPosts(response.data);
            }).catch((error) => {
                console.error(error);
            })
        }, []);

    return (
            <PostList posts={posts} message="What would you like to cook today?" />
    )
}

export default MainPagePosts;
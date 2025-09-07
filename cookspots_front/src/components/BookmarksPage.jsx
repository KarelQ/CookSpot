import {useEffect, useState} from "react";
import {getUserBookmarks} from "../services/PostService.jsx";
import PostList from "../components/PostList";
import Banner from "./Banner.jsx";


const BookmarksPage = () => {
    const [posts, setPosts] = useState([]);

    useEffect(() => {
        getUserBookmarks().then((response) => {
            setPosts(response.data);
        }).catch((error) => {
            console.error(error);
        })
    }, []);

    return (
        <main>
            <PostList posts={posts} message="Yours Bookmarks" />
        </main>

    )
}

export default BookmarksPage;
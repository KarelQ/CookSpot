import {useEffect, useState} from "react";
import {postList, postListByCategoryId} from "../services/PostService.jsx";
import PostList from "../components/PostList";
import {useParams} from "react-router-dom";
import Banner from "./Banner.jsx";


const ExploreCategory = () => {
    const [posts, setPosts] = useState([]);
    const { id } = useParams();
    const { name } = useParams();

        useEffect(() => {
            postListByCategoryId(id).then((response) => {
                setPosts(response.data);
            }).catch((error) => {
                console.error(error);
            })
        }, []);

    return (
        <main>
            <Banner message={name}/>
            <PostList posts={posts} />
        </main>


    )
}

export default ExploreCategory;
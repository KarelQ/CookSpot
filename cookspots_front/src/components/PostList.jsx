import Banner from "./Banner.jsx";

const Post = ({ post }) => {

    return (
        <div id={post.idPost}>
            <img src={`http://localhost:8080/auth/img/${post.image}`} alt="post image" />
            <div>
                <div className="post-desc">
                    <h1><a href={`/postpage/${post.idPost}`}>{post.title}</a></h1>
                    <h1>{post.username}</h1>
                    <p>{post.description}</p>
                </div>

                <div className="post-icons">
                    <div>
                        <i className="material-symbols-outlined">signal_cellular_alt</i>
                        <span>{post.difficulty}</span>
                    </div>
                    <div>
                        <i className="material-symbols-outlined">star</i>
                        <span>{post.starRating ?? 0}</span>
                    </div>
                    <div>
                        <i className="material-symbols-outlined">timer</i>
                        <span>{post.prepTime}</span>
                    </div>
                    <div>
                        <i className="material-symbols-outlined">Restaurant</i>
                        <span>for {post.numberOfServings}</span>
                    </div>
                </div>
            </div>
        </div>
    );
};


const PostList = ({ posts, message }) => {
    return (
        <>
            <Banner message={message}/>
            <section className="posts">
                {posts.map((post) => (
                    <Post key={post.idPost} post={post} />
                ))}
            </section>
        </>
    );
};



export default PostList;

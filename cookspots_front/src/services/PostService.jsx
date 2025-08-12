import axios from "axios";
import tokenAxios from "./TokenService.jsx";

const REST_API_BASE_URL = 'http://localhost:8080/auth/posts';

export const postList = () => axios.get(REST_API_BASE_URL);


export const postListByUserId = (user_id) => axios.get(REST_API_BASE_URL+"/user/"+user_id);

export const postListByCategoryId = (category_id) => axios.get(REST_API_BASE_URL+"/category/"+category_id);

export const postDetailsById = (post_id) => axios.get(REST_API_BASE_URL + '/id/' + post_id);

export const deletePostById = (post_id) => axios.delete(REST_API_BASE_URL + '/delete/' + post_id);







export const createPost = (post) => {return tokenAxios.post(`${REST_API_BASE_URL}/addpost`, post);
};



// export const createPost = (post) => {
//     const token = sessionStorage.getItem("sessionToken");
//     return axios.post(REST_API_BASE_URL + "/addpost", post, {
//         headers: {
//             "Authorization": `Bearer ${token}`
//         }
//     });
// };


//export  const createPost = (post) => axios.post(REST_API_BASE_URL+'/addpost', post);

//export  const saveImgFile = (img_) => axios.post(REST_API_BASE_URL+'/addpost', post);
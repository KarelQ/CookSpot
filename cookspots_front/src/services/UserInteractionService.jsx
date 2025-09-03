import axios from "axios";
import tokenAxios from "./TokenService.jsx";

const REST_API_BASE_URL = 'http://localhost:8080/auth/user_interaction';

export const checkUserInteractions = (idPost) => {
    return tokenAxios.post(`${REST_API_BASE_URL}/check/${idPost}`);
};




export const isPostIdBookmarkedByUserId = (post_id, user_id) => axios.get(REST_API_BASE_URL +'/' + post_id +'/'+user_id);

export const newBookmark = (post_id, user_id) => axios.post(REST_API_BASE_URL +'/new/' + post_id +'/'+user_id);

export const deleteBookmark = (post_id, user_id) => axios.post(REST_API_BASE_URL +'/delete/' + post_id +'/'+user_id);


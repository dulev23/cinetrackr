import axiosInstance from "./axiosInstance";

const BASE_URL = "/api/user-media";

export const getUserMedia = (userId, status) => {
    if (status) {
        return axiosInstance.get(
            `${BASE_URL}/user/${userId}?status=${status}`
        );
    }
    return axiosInstance.get(`${BASE_URL}/user/${userId}`);
};

export const addUserMedia = (data) =>
    axiosInstance.post(BASE_URL, data);

export const updateUserMedia = (id, data) =>
    axiosInstance.put(`${BASE_URL}/${id}`, data);

export const deleteUserMedia = (id) =>
    axiosInstance.delete(`${BASE_URL}/${id}`);
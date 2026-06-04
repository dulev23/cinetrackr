import axiosInstance from './axiosInstance';

export const getUserMedia = (userId, status) =>
    axiosInstance.get(`/api/user-media/user/${userId}`, {
        params: status ? { status } : {},
    });

export const addUserMedia = (dto) => axiosInstance.post('/api/user-media', dto);
export const updateUserMedia = (id, dto) => axiosInstance.put(`/api/user-media/${id}`, dto);
export const deleteUserMedia = (id) => axiosInstance.delete(`/api/user-media/${id}`);
import axiosInstance from './axiosInstance';

export const getAllMedia = () => axiosInstance.get('/api/media');
export const getMediaById = (id) => axiosInstance.get(`/api/media/${id}`);
export const createMedia = (dto) => axiosInstance.post('/api/media', dto);
export const updateMedia = (id, dto) => axiosInstance.put(`/api/media/${id}`, dto);
export const deleteMedia = (id) => axiosInstance.delete(`/api/media/${id}`);
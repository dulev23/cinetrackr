import axiosInstance from './axiosInstance';

const BASE_URL = '/api/users';

export const getUsers = () => axiosInstance.get(BASE_URL);

export const createUser = (user) => axiosInstance.post(BASE_URL, user);

export const registerUser = (data) => axiosInstance.post(`${BASE_URL}/register`, data);

export const loginUser = (data) => axiosInstance.post(`${BASE_URL}/login`, data);

export const getUserById = (id) => axiosInstance.get(`${BASE_URL}/${id}`);
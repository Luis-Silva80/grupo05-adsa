import axios from "axios";

const api = axios.create({
  baseURL: "http://18.214.213.57:8090/",
});

export default api;
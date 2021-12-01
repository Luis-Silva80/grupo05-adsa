import axios from "axios";

const api = axios.create({
  baseURL: "https://thothlibs.azurewebsites.net/",
});

export default api;
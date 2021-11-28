import axios from "axios";

const apiPython = axios.create({
  baseURL: "http://localhost:5000/",
});


export default apiPython;
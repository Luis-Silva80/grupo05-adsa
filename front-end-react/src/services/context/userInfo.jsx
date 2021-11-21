import React from 'react';
import api from "../api";

export const userInfo = () => {
    const userId = parseInt(localStorage.getItem('userId'))

        api
            .get(`/aluno/${userId}`)
            .then((response) => {
                console.log("context carregado");
                return response.data;
            })
            .catch((err) => {
                console.error("ops! ocorreu um erro" + err);
            });
}

export const UserInfoContext = React.createContext(userInfo);
import React, { useEffect, useState } from 'react'
import api from "./api";
import { useHistory } from 'react-router-dom';


function Autentication() {
    const history = useHistory();

    useEffect(async () => {

        await api
        .get(`/autenticacao/usuariosLogados`)
        .then((response) => {
            response.data.map(user => {
                console.log(!user.autenticado);
                if (user.id != localStorage.getItem('userId')) {
                    if (user.autenticado) {
                        history.push("/login")
                    }
                }
            })
        })
        .catch((err) => {
            console.error("ops! ocorreu um erro" + err);
        });
    }, []);

};

export default Autentication;
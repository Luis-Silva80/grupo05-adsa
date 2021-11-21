import React, { useEffect, useState } from 'react'
import api from "./api";
import { useHistory } from 'react-router-dom';


function AutenticationAdmin() {
    const history = useHistory();

    useEffect(async () => {
        await api
        .get(`/admin/` + localStorage.getItem('userId'))
        .then((response) => {
        if (response.status != 200) {
                history.push("/notAllowed");
            }
        })
        .catch((err) => {
            console.error("ops! ocorreu um erro" + err);
        });
    }, []);


};

export default AutenticationAdmin;
import React, { useEffect } from 'react'
import api from "./api";


function AutenticationAdmin() {

    useEffect(async () => {

        await api
        .get(`/admin/` + localStorage.getItem('userId'))
        .then((response) => {
            if (response.status != 200) {
                
            }
        })
        .catch((err) => {
            console.error("ops! ocorreu um erro" + err);
        });
    }, []);

};

export default AutenticationAdmin;
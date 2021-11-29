import React, { useEffect, useState } from 'react'
import api from "./api";
import { useHistory } from 'react-router-dom';


function Autentication() {
    const history = useHistory();

    useEffect(async () => {
            if (!localStorage.getItem('userId')) {
                history.push("/login")
            }
    }, []);

};

export default Autentication;
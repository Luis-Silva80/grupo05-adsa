import React, { useState } from 'react';
import './style.scss';

import Popup from '../../components/popup/Popup';
import { Link } from 'react-router-dom';



function BookCardUser( props ) {
    const [visible, setVisible] = useState(false);

    return(
        
        <>
            
            <Popup visible={visible} onClose={() => setVisible(false)} titulo="teste de props"/>

            <div class="book">
                <Link to="./livro" class="book_link">
                    <img src={props.image} class="book_link_img" alt="book preview"/>
                </Link>
                <div class="book_info">
                    <h4 class="book_info_title">{props.titulo}</h4>
                    <p class="book_info_date devolution">Devolver até: <span id="devolutionDate">{props.date}</span></p>
                    <button class="book_info_btn" onClick={() => setVisible(true)}>Ver Reserva</button>
                </div>
            </div>

        </>
    );
}

export default BookCardUser;
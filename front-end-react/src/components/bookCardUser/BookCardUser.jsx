import React, { useState } from 'react';
import './style.scss';

import Popup from '../../components/popup/Popup';
import { Link } from 'react-router-dom';



function BookCardUser( props ) {
    const [visible, setVisible] = useState(false);

    return(
        
        <>
            
            <Popup visible={visible} onClose={() => setVisible(false)} titulo="teste de props"/>

            <div className="book">
                <Link to="./livro" className="book_link">
                    <img src={props.image} className="book_link_img" alt="book preview"/>
                </Link>
                <div className="book_info">
                    <h4 className="book_info_title">{props.titulo}</h4>
                    <p className="book_info_date devolution">Devolver até: <span id="devolutionDate">{props.date}</span></p>
                    <button className="book_info_btn" onClick={() => setVisible(true)}>Ver Reserva</button>
                </div>
            </div>

        </>
    );
}

export default BookCardUser;
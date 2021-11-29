import React, { useState } from 'react';
import './style.scss';

import { Link } from 'react-router-dom';

function storeId(value) {
    localStorage.setItem('bookId', parseInt(value))
}

function BookCard( props ) {
    return(
        
        <>
            <div className="book">
                <Link to="./livro" className="book_link">
                    <img src={props.image} className="book_link_img" alt="book preview"/>
                </Link>
                <div className="book_infos">
                    <span id="bookId" className="book_infos_info_id">{props?.idLivro}</span>
                    <h4 className="book_infos_info title">{props?.titulo}</h4>
                    <p className="book_infos_info">{props?.autor}</p>
                    {props.status.toLowerCase().replaceAll(' ', '') == "disponivel" 
                    ? <p className="book_infos_info available">{props?.status}</p> 
                    : <p className="book_infos_info unavailable">{props?.status}</p>}
                    {/* <Link onClick={() => storeId(props.idLivro)} to="/livro"  className="book_info_btn">Ver Mais</Link> */}
                    <Link onClick={() => storeId(props?.idLivro)}  to="/livro"  className="book_infos_info_btn">Ver Mais</Link>
                </div>
            </div>

        </>
    );
}

export default BookCard;
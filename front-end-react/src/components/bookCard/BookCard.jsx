import React, { useState } from 'react';
import './style.scss';

import { Link } from 'react-router-dom';

function storeId(value) {
    localStorage.setItem('bookId', parseInt(value))
}

function BookCard( props ) {
    return(
        
        <>
            <div class="book">
                <Link to="./livro" class="book_link">
                    <img src={props.image} class="book_link_img" alt="book preview"/>
                </Link>
                <div class="book_info">
                    <span id="bookId" className="book_info_id">{props.idLivro}</span>
                    <h4 class="book_info_title">{props.titulo}</h4>
                    <p class="book_info_desc">{props.descricao}</p>
                    {/* <Link onClick={() => storeId(props.idLivro)} to="/livro"  class="book_info_btn">Ver Mais</Link> */}
                    <Link onClick={() => storeId(props.idLivro)}  to="/livro"  class="book_info_btn">Ver Mais</Link>
                </div>
            </div>

        </>
    );
}

export default BookCard;
import React, { useState } from 'react';
import './style.scss';

import Popup from '../../components/popup/Popup';

function BookCard( props ) {
    const [visible, setVisible] = useState(false);

    return(
        
        <>
            
            <Popup visible={visible} onClose={() => setVisible(false)} titulo="teste de props"/>

            <li class="main_listaLivros_lista_livro">
                <a href="./livro" class="main_listaLivros_lista_livro_linkImg">
                    <img src={props.image} class="main_listaLivros_lista_livro_linkImg_image"/>
                </a>
                <div class="main_listaLivros_lista_livro_contentBox">
                    <h4 class="main_listaLivros_lista_livro_contentBox_name">{props.title}</h4>
                    <p class="main_listaLivros_lista_livro_contentBox_status devolution">Devolver até: <span id="devolutionDate">{props.date}</span></p>
                    <button class="main_listaLivros_lista_livro_contentBox_button" onClick={() => setVisible(true)}>Devolver</button>
                </div>
            </li>

        </>
    );
}

export default BookCard;
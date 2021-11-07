import React from 'react';
import './style.scss';

function BookCard( props ) {
    return(
        <>

            <li class="main_listaLivros_lista_livro">
                <a href="./livro" class="main_listaLivros_lista_livro_linkImg">
                    <img src={props.image} class="main_listaLivros_lista_livro_linkImg_image"/>
                </a>
                <div class="main_listaLivros_lista_livro_contentBox">
                    <h4 class="main_listaLivros_lista_livro_contentBox_name">{props.title}</h4>
                    <p class="main_listaLivros_lista_livro_contentBox_status devolution">Devolver até: <span id="devolutionDate">{props.date}</span></p>
                    <a href="#" class="main_listaLivros_lista_livro_contentBox_button">Devolver</a>
                </div>
            </li>

        </>
    );
}

export default BookCard;
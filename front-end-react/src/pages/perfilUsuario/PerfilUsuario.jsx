import React from "react";

//Importanto componentes e o css
import './style.scss';
import SideBar from '../../components/sideBar/SideBar';
import Footer from '../../components/footer/Footer';

//IMAGENS

import perfilIcon from     '../../assets/perfilIcon.png';
import pencil from         '../../assets/pencil.png';
import conversation from   '../../assets/conversation.png';
import openBookPerfil from '../../assets/open-book-perfil.png';
import imageLivro from     '../../assets/image-livro.png';

function PerfilUsuario() {

    return (
        <>
            <SideBar/>
            <main class="main container" id="rootPerfilUsuario">

                <div class="main_perfilInfo">

                    <div class="main_perfilInfo_box">
                        <img src={perfilIcon} class="main_perfilInfo_box_icon"/>
                        <div class ="main_perfilInfo_box_content">
                        <h3 class ="main_perfilInfo_box_content_name">Matheus Alencar Garcia <img src={pencil} class ="main_perfilInfo_box_content_name_icon"/></h3>
                        <p class ="main_perfilInfo_box_content_email">matheus.garcia @bandtec.com.br</p>
                        <div class ="main_perfilInfo_box_content_box">
                        <p class ="main_perfilInfo_box_content_box_ra">123123123</p>
                        <p class ="main_perfilInfo_box_content_box_number">(11) 989892311</p>
                        </div>
                        </div>
                    </div>

                    <div class="main_perfilInfo_perfilGame">
                        <p class="main_perfilInfo_perfilGame_level">Usuário Nível<span class="main_perfilInfo_perfilGame_level_number" id="perfilLevel">10</span></p>

                        <div class="main_perfilInfo_perfilGame_progressBarGrey"><div class="main_perfilInfo_perfilGame_progressBarBlue"></div></div>

                        <div class="main_perfilInfo_perfilGame_status">
                            <div class="main_perfilInfo_perfilGame_status_box">
                                <img src={openBookPerfil} class="main_perfilInfo_perfilGame_status_box_image"/>
                                <p class ="main_perfilInfo_perfilGame_status_box_content"><span class ="main_perfilInfo_perfilGame_status_box_content_number" id="numberBooksReaded">12</span> livros lidos</p>
                            </div>

                            <div class="main_perfilInfo_perfilGame_status_box">
                                <img src={conversation} class="main_perfilInfo_perfilGame_status_box_image"/>
                                <p class ="main_perfilInfo_perfilGame_status_box_content"><span class ="main_perfilInfo_perfilGame_status_box_content_number" id="numberBooksReview">5</span> resenhas</p>
                            </div>

                        </div>
                    </div>
                </div>

                <div class="main_listaLivros">

                    <div class="main_listaLivros_title">
                        <select class="main_listaLivros_title_filter">
                            <option class="main_listaLivros_title_filter_value" value="#">Filtrar por:</option>
                            <option class="main_listaLivros_title_filter_value" value="pendencia">Categoria</option>
                            <option class="main_listaLivros_title_filter_value" value="nomeDesc">Nome Desc</option>
                            <option class="main_listaLivros_title_filter_value" value="nomeAsc">Nome Asc</option>
                        </select>

                        <h2 class="main_listaLivros_title_text">Livros reservados</h2>
                    </div>

                    <ul class="main_listaLivros_lista">

                        <li class="main_listaLivros_lista_livro">
                            <a href="./livro.html" class="main_listaLivros_lista_livro_linkImg">
                                <img src={imageLivro} class="main_listaLivros_lista_livro_linkImg_image"/>
                            </a>
                            <div class="main_listaLivros_lista_livro_contentBox">
                                <h4 class="main_listaLivros_lista_livro_contentBox_name">Programando com javaScript</h4>
                                <p class="main_listaLivros_lista_livro_contentBox_devolution">Devolver até: <span id="devolutionDate">25/09/2021</span></p>
                                <a href="#" class="main_listaLivros_lista_livro_contentBox_button">Devolver</a>
                            </div>
                        </li>

                        <li class="main_listaLivros_lista_livro">
                            <a href="./livro.html" class="main_listaLivros_lista_livro_linkImg">
                            <img src={imageLivro} class="main_listaLivros_lista_livro_linkImg_image"/>
                            </a>
                            <div class="main_listaLivros_lista_livro_contentBox">
                                <h4 class="main_listaLivros_lista_livro_contentBox_name">Programando com javaScript</h4>
                                <p class="main_listaLivros_lista_livro_contentBox_devolution">Devolver até: <span id="devolutionDate">25/09/2021</span></p>
                                <a href="#" class="main_listaLivros_lista_livro_contentBox_button">Devolver</a>
                            </div>
                        </li>

                        <li class="main_listaLivros_lista_livro">
                            <a href="./livro.html" class="main_listaLivros_lista_livro_linkImg">
                            <img src={imageLivro} class="main_listaLivros_lista_livro_linkImg_image"/>
                            </a>
                            <div class="main_listaLivros_lista_livro_contentBox">
                                <h4 class="main_listaLivros_lista_livro_contentBox_name">Programando com javaScript</h4>
                                <p class="main_listaLivros_lista_livro_contentBox_devolution">Devolver até: <span id="devolutionDate">25/09/2021</span></p>
                                <a href="#" class="main_listaLivros_lista_livro_contentBox_button">Devolver</a>
                            </div>
                        </li>

                        <li class="main_listaLivros_lista_livro">
                            <a href="./livro.html" class="main_listaLivros_lista_livro_linkImg">
                            <img src={imageLivro} class="main_listaLivros_lista_livro_linkImg_image"/>
                            </a>
                            <div class="main_listaLivros_lista_livro_contentBox">
                                <h4 class="main_listaLivros_lista_livro_contentBox_name">Programando com javaScript</h4>
                                <p class="main_listaLivros_lista_livro_contentBox_devolution">Devolver até: <span id="devolutionDate">25/09/2021</span></p>
                                <a href="#" class="main_listaLivros_lista_livro_contentBox_button">Devolver</a>
                            </div>
                        </li>

                        <li class="main_listaLivros_lista_livro">
                            <a href="./livro.html" class="main_listaLivros_lista_livro_linkImg">
                            <img src={imageLivro} class="main_listaLivros_lista_livro_linkImg_image"/>
                            </a>
                            <div class="main_listaLivros_lista_livro_contentBox">
                                <h4 class="main_listaLivros_lista_livro_contentBox_name">Programando com javaScript</h4>
                                <p class="main_listaLivros_lista_livro_contentBox_devolution">Devolver até: <span id="devolutionDate">25/09/2021</span></p>
                                <a href="#" class="main_listaLivros_lista_livro_contentBox_button">Devolver</a>
                            </div>
                        </li>

                        <li class="main_listaLivros_lista_livro">
                            <a href="./livro.html" class="main_listaLivros_lista_livro_linkImg">
                            <img src={imageLivro} class="main_listaLivros_lista_livro_linkImg_image"/>
                            </a>
                            <div class="main_listaLivros_lista_livro_contentBox">
                                <h4 class="main_listaLivros_lista_livro_contentBox_name">Programando com javaScript</h4>
                                <p class="main_listaLivros_lista_livro_contentBox_devolution">Devolver até: <span id="devolutionDate">25/09/2021</span></p>
                                <a href="#" class="main_listaLivros_lista_livro_contentBox_button">Devolver</a>
                            </div>
                        </li>


                    </ul>
                </div>
            </main>
            <Footer/>
        </>
    )
}

export default PerfilUsuario;

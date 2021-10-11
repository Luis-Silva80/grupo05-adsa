import React, { useEffect } from 'react';
// import './style.scss';
import perfil from '../../assets/perfilIcon.png';
import pencil from '../../assets/pencil.png';
import openBook from '../../assets/open-book-perfil.png';
import conversation from '../../assets/conversation.png';
import imgLivro from '../../assets/image-livro.png';

// import components-
import Footer from '../../components/footer/Footer';
import SideBar from '../../components/sideBar/SideBar';

function ListaUsuarios() {
    return (
        <>
            <SideBar />
            <main className="main container">

                <div className="main_perfilInfo">

                    <div className="main_perfilInfo_box">
                        <img src={perfil} className="main_perfilInfo_box_icon" />
                        <div className="main_perfilInfo_box_content">
                            <h3 className="main_perfilInfo_box_content_name">Matheus Alencar Garcia <img src={pencil} className="main_perfilInfo_box_content_name_icon" /></h3>
                            <p className="main_perfilInfo_box_content_email">matheus.garcia @bandtec.com.br</p>
                            <div className="main_perfilInfo_box_content_box">
                                <p className="main_perfilInfo_box_content_box_ra">123123123</p>
                                <p className="main_perfilInfo_box_content_box_number">(11) 989892311</p>
                            </div>
                        </div>
                    </div>

                    <div className="main_perfilInfo_perfilGame">
                        <p className="main_perfilInfo_perfilGame_level">Usuário Nível<span className="main_perfilInfo_perfilGame_level_number" id="perfilLevel">10</span></p>

                        <div className="main_perfilInfo_perfilGame_progressBarGrey"><div className="main_perfilInfo_perfilGame_progressBarBlue"></div></div>

                        <div className="main_perfilInfo_perfilGame_status">
                            <div className="main_perfilInfo_perfilGame_status_box">
                                <img src={openBook} className="main_perfilInfo_perfilGame_status_box_image" />
                                <p className="main_perfilInfo_perfilGame_status_box_content"><span className="main_perfilInfo_perfilGame_status_box_content_number" id="numberBooksReaded">12</span> livros lidos</p>
                            </div>

                            <div className="main_perfilInfo_perfilGame_status_box">
                                <img src={conversation} className="main_perfilInfo_perfilGame_status_box_image" />
                                <p className="main_perfilInfo_perfilGame_status_box_content"><span className="main_perfilInfo_perfilGame_status_box_content_number" id="numberBooksReview">5</span> resenhas</p>
                            </div>

                        </div>
                    </div>
                </div>

                <div className="main_listaLivros">

                    <div className="main_listaLivros_title">
                        <select className="main_listaLivros_title_filter">
                            <option className="main_listaLivros_title_filter_value" value="#">Filtrar por:</option>
                            <option className="main_listaLivros_title_filter_value" value="pendencia">Categoria</option>
                            <option className="main_listaLivros_title_filter_value" value="nomeDesc">Nome Desc</option>
                            <option className="main_listaLivros_title_filter_value" value="nomeAsc">Nome Asc</option>
                        </select>

                        <h2 className="main_listaLivros_title_text">Livros reservados</h2>
                    </div>

                    <ul className="main_listaLivros_lista">

                        <li className="main_listaLivros_lista_livro">
                            <a href="./livro.html" className="main_listaLivros_lista_livro_linkImg">
                                <img src={imgLivro} className="main_listaLivros_lista_livro_linkImg_image" />
                            </a>
                            <div className="main_listaLivros_lista_livro_contentBox">
                                <h4 className="main_listaLivros_lista_livro_contentBox_name">Programando com javaScript</h4>
                                <p className="main_listaLivros_lista_livro_contentBox_devolution">Devolver até: <span id="devolutionDate">25/09/2021</span></p>
                                <a href="#" className="main_listaLivros_lista_livro_contentBox_button">Devolver</a>
                            </div>
                        </li>

                        <li className="main_listaLivros_lista_livro">
                            <a href="./livro.html" className="main_listaLivros_lista_livro_linkImg">
                                <img src={imgLivro} className="main_listaLivros_lista_livro_linkImg_image" />
                            </a>
                            <div className="main_listaLivros_lista_livro_contentBox">
                                <h4 className="main_listaLivros_lista_livro_contentBox_name">Programando com javaScript</h4>
                                <p className="main_listaLivros_lista_livro_contentBox_devolution">Devolver até: <span id="devolutionDate">25/09/2021</span></p>
                                <a href="#" className="main_listaLivros_lista_livro_contentBox_button">Devolver</a>
                            </div>
                        </li>

                        <li className="main_listaLivros_lista_livro">
                            <a href="./livro.html" className="main_listaLivros_lista_livro_linkImg">
                                <img src={imgLivro} className="main_listaLivros_lista_livro_linkImg_image" />
                            </a>
                            <div className="main_listaLivros_lista_livro_contentBox">
                                <h4 className="main_listaLivros_lista_livro_contentBox_name">Programando com javaScript</h4>
                                <p className="main_listaLivros_lista_livro_contentBox_devolution">Devolver até: <span id="devolutionDate">25/09/2021</span></p>
                                <a href="#" className="main_listaLivros_lista_livro_contentBox_button">Devolver</a>
                            </div>
                        </li>

                        <li className="main_listaLivros_lista_livro">
                            <a href="./livro.html" className="main_listaLivros_lista_livro_linkImg">
                                <img src={imgLivro} className="main_listaLivros_lista_livro_linkImg_image" />
                            </a>
                            <div className="main_listaLivros_lista_livro_contentBox">
                                <h4 className="main_listaLivros_lista_livro_contentBox_name">Programando com javaScript</h4>
                                <p className="main_listaLivros_lista_livro_contentBox_devolution">Devolver até: <span id="devolutionDate">25/09/2021</span></p>
                                <a href="#" className="main_listaLivros_lista_livro_contentBox_button">Devolver</a>
                            </div>
                        </li>

                        <li className="main_listaLivros_lista_livro">
                            <a href="./livro.html" className="main_listaLivros_lista_livro_linkImg">
                                <img src={imgLivro} className="main_listaLivros_lista_livro_linkImg_image" />
                            </a>
                            <div className="main_listaLivros_lista_livro_contentBox">
                                <h4 className="main_listaLivros_lista_livro_contentBox_name">Programando com javaScript</h4>
                                <p className="main_listaLivros_lista_livro_contentBox_devolution">Devolver até: <span id="devolutionDate">25/09/2021</span></p>
                                <a href="#" className="main_listaLivros_lista_livro_contentBox_button">Devolver</a>
                            </div>
                        </li>

                        <li className="main_listaLivros_lista_livro">
                            <a href="./livro.html" className="main_listaLivros_lista_livro_linkImg">
                                <img src={imgLivro} className="main_listaLivros_lista_livro_linkImg_image" />
                            </a>
                            <div className="main_listaLivros_lista_livro_contentBox">
                                <h4 className="main_listaLivros_lista_livro_contentBox_name">Programando com javaScript</h4>
                                <p className="main_listaLivros_lista_livro_contentBox_devolution">Devolver até: <span id="devolutionDate">25/09/2021</span></p>
                                <a href="#" className="main_listaLivros_lista_livro_contentBox_button">Devolver</a>
                            </div>
                        </li>


                    </ul>
                </div>

            </main>
            <Footer />
        </>
    );
}

export default ListaUsuarios;
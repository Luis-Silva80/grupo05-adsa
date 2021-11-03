import React, { useEffect, useState } from 'react';
import api from "../../services/api";


//Importanto componentes e o css
import './style.scss';
import SideBar from '../../components/sideBar/SideBar';
import Footer from '../../components/footer/Footer';
import BookCard from '../../components/bookCard/BookCard';

//IMAGENS

import perfilIcon from '../../assets/perfilIcon.png';
import pencil from '../../assets/pencil.png';
import conversation from '../../assets/conversation.png';
import openBookPerfil from '../../assets/open-book-perfil.png';
import imageLivro from '../../assets/book.png';

function PerfilUsuario() {

    // const userId = localStorage.getItem('userId')
    const [ userData, setUserData ] = useState([]);

    useEffect(() => {
        api
            .get("/aluno/2")
            .then((response) => {
                setUserData(response.data);
                console.log(response.data);
            })
            .catch((err) => {
                console.error("ops! ocorreu um erro" + err);
            });
    }, []);
  

    return (
        <>
            <SideBar />
            <main className="main container" id="rootPerfilUsuario">           

                <div className="main_perfilInfo">

                    <div className="main_perfilInfo_box">
                        <img src={perfilIcon} className="main_perfilInfo_box_icon" />
                        <div className="main_perfilInfo_box_content">
                            <h3 className="main_perfilInfo_box_content_name">teste nome <img src={pencil} className="main_perfilInfo_box_content_name_icon" /></h3>
                            <p className="main_perfilInfo_box_content_email">teste email ajhhhh</p>
                            <div className="main_perfilInfo_box_content_box">
                                <p className="main_perfilInfo_box_content_box_ra">teste item ra</p>
                                <p className="main_perfilInfo_box_content_box_number">teste item  number</p>
                            </div>
                        </div>
                    </div>

                    <div className="main_perfilInfo_perfilGame">
                        <p className="main_perfilInfo_perfilGame_level">Usuário Nível<span className="main_perfilInfo_perfilGame_level_number" id="perfilLevel">10</span></p>

                        <div className="main_perfilInfo_perfilGame_progressBarGrey"><div className="main_perfilInfo_perfilGame_progressBarBlue"></div></div>

                        <div className="main_perfilInfo_perfilGame_status">
                            <div className="main_perfilInfo_perfilGame_status_box">
                                <img src={openBookPerfil} className="main_perfilInfo_perfilGame_status_box_image" />
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
                        <BookCard image={imageLivro} title="Programando com javascript" date="10/02/2021" />
                        <BookCard image={imageLivro} title="Programando com javascript" date="10/02/2021" />
                        <BookCard image={imageLivro} title="Programando com javascript" date="10/02/2021" />
                        <BookCard image={imageLivro} title="Programando com javascript" date="10/02/2021" />
                        <BookCard image={imageLivro} title="Programando com javascript" date="10/02/2021" />
                        <BookCard image={imageLivro} title="Programando com javascript" date="10/02/2021" />
                        <BookCard image={imageLivro} title="Programando com javascript" date="10/02/2021" />
                        <BookCard image={imageLivro} title="Programando com javascript" date="10/02/2021" />
                    </ul>
                </div>
            </main>
            <Footer />
        </>
    )
}

export default PerfilUsuario;

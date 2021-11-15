/* eslint-disable */

import React, { useEffect, useState } from 'react';
import api from "../../services/api";
import Autentication from "../../services/autentication";
import { useHistory, Link } from 'react-router-dom';


//Importanto componentes e o css
import './style.scss';
import Loading from '../../components/loading/Loading';
import Footer from '../../components/footer/Footer';
import SideBar from '../../components/sideBar/SideBar';
import LinkButton from '../../components/button/Button';
import livro from '../../assets/image-livro.png';

function Livro() {

  Autentication();



  const bookId = parseInt(localStorage.getItem('bookId'))
  const [userInfo, setUserInfo] = useState([]);
  const [bookInfo, setBookInfo] = useState([]);

  useEffect(async () => {

    await api
      .get(`/biblioteca/${bookId}`)
      .then((response) => {
        setBookInfo(response.data);
        console.log("User data:", response.data);
      })
      .catch((err) => {
        console.error("ops! ocorreu um erro" + err);
      });
  }, []);

  return (
    <div id="rootLivro">
      <SideBar />

      {userInfo.length === 0 ?
        <Loading /> :
        <main className="main">
          <div className="main_container">
            <div className="main_container_upBox">
              <div className="main_container_upBox_imgBox">
                <img src={livro} className="main_container_upBox_imgBox_img" />
              </div>
              <div className="main_container_upBox_content">
                <h3 className="main_container_upBox_content_title">{userInfo.titulo}</h3>
                <div className="main_container_upBox_content_main">
                  <div className="main_container_upBox_content_main_left">
                    <div className="main_container_upBox_content_main_left_paragraph">
                      <p className="main_container_upBox_content_main_left_paragraph_pTitle">Autor:</p>
                      <p className="main_container_upBox_content_main_left_paragraph_pContent">{userInfo.autor}</p>
                    </div>
                    <div className="main_container_upBox_content_main_left_paragraph">
                      <p className="main_container_upBox_content_main_left_paragraph_pTitle">Data publicação:</p>
                      <p className="main_container_upBox_content_main_left_paragraph_pContent">10/08/2020</p>
                    </div>
                    <div className="main_container_upBox_content_main_left_paragraph">
                      <p className="main_container_upBox_content_main_left_paragraph_pTitle">Valor: </p>
                      <p className="main_container_upBox_content_main_left_paragraph_pContent">R$ 100,00</p>
                    </div>
                  </div>
                  <div className="main_container_upBox_content_main_rigth">
                    <div className="main_container_upBox_content_main_rigth_paragraph">
                      <p className="main_container_upBox_content_main_rigth_paragraph_pTitle">Status:</p>
                      <p className="main_container_upBox_content_main_left_paragraph_pContent">Disponível</p>
                    </div>
                    <div className="main_container_upBox_content_main_rigth_paragraph">
                      <p className="main_container_upBox_content_main_rigth_paragraph_pTitle">Avaliação</p>
                      <p className="main_container_upBox_content_main_rigth_paragraph_pContent">10/10</p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div className="main_container_downBox">
              <LinkButton content="Reservar" className="main_container_downBox_button" />
              <LinkButton content="Comprar" className="main_container_downBox_button" />
              <LinkButton content="Baixar" className="main_container_downBox_button" />
            </div>
          </div>
          <div className="main_description">
            <h3>Descrição</h3>
            <p>{userInfo.descricao}</p>
          </div>
        </main>
      }
      <Footer />
    </div>
  )
}
export default Livro;
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
import Resp from '../../components/resp/Resp';
import LinkButton from '../../components/button/Button';
import livro from '../../assets/book.png';

function Livro() {

  Autentication();



  const bookId = parseInt(localStorage.getItem('bookId'))
  const userId = parseInt(localStorage.getItem('userId'))
  
  const [respInfo, setRespInfo] = useState([]);
  const [bookInfo, setBookInfo] = useState([]);
  const [ showResp, setShowResp ] = useState(false);

  useEffect(async () => {
    setRespInfo({ titulo: "Sucesso", parag: "Entre em seu perfil para verificar o livro clicando no botão abaixo"})

    await api
      .get(`/biblioteca/${bookId}`)
      .then((response) => {
        setBookInfo(response.data);
        console.log("Book data:", response.data);
      })
      .catch((err) => {
        console.error("ops! ocorreu um erro" + err);
      });
  }, []);

  // useEffect(() => {
  //   setRespInfo({ titulo: "deu bom", parag: "qq coisa", btn: "perfil usuario", link:"perfilUsuario" });

  //   console.log("resp info aqui",respInfo);
    
  // }, []);

  useEffect(() => {
    const resp = document.getElementById('respReserv');

    if (showResp) {
      resp.classList.add("active");
      
      api
      .put(`/biblioteca/reservar/${bookInfo.id}/${userId}`)
      .then((response) => {
        setRespInfo({ titulo: "Sucesso", parag: "Entre em seu perfil para verificar o livro clicando no botão abaixo", btn: "Perfil", link:"/perfilUsuario" })
        resp.classList.add("success");
        resp.classList.add("active");
        localStorage.setItem("idReserva", response.data)

        console.log("id da reserva:", response.data);
        console.log("resp info aqui",respInfo);
      })
      .catch((err) => {
        console.log("deu erro aqui pô");
        setRespInfo({ titulo: "Ocorreu um erro", parag: "Entre em contato com o nosso time de suporte clicando no botão abaixo", btn: "Contato", link:"/contato" })
        resp.classList.add("error");
        resp.classList.add("active");
        console.error("ops! ocorreu um erro" + err);
      });
    }
  },[showResp])

  // function reserve(idLivro, idUsuario) {
  //   let resp = document.getElementById('resp')

  //   resp.classList.add("active")

  //   api
  //     .put(`/biblioteca/reserva/${bookInfo.id}/${userId}`)
  //     .then((response) => {
  //       setIdReserva(response.data);

  //       localStorage.setItem("idReserva", response.data)
  //       console.log("id da reserva:", response.data);
  //       console.log("resp info aqui",respInfo);

  //     })
  //     .catch((err) => {
  //       console.log("deu erro aqui pô");
  //       console.error("ops! ocorreu um erro" + err);
  //   });
   

  // }

  return (
    <div id="rootLivro">
      <SideBar />

      {bookInfo.length === 0 ?
        <Loading /> :
        <main className="main">
          <Resp  titulo={respInfo.titulo} parag={respInfo.parag} btn={respInfo.btn} link={respInfo.link} /> 
          <div className="main_container">
            <div className="main_container_upBox">
              <div className="main_container_upBox_imgBox">
                <img src={livro} className="main_container_upBox_imgBox_img" />
              </div>
              <div className="main_container_upBox_content">
                <h3 className="main_container_upBox_content_title">{bookInfo.titulo}</h3>
                <div className="main_container_upBox_content_main">
                  <div className="main_container_upBox_content_main_left">
                    <div className="main_container_upBox_content_main_left_paragraph">
                      <p className="main_container_upBox_content_main_left_paragraph_pTitle">Autor:</p>
                      <p className="main_container_upBox_content_main_left_paragraph_pContent">{bookInfo.autor}</p>
                    </div>
                    <div className="main_container_upBox_content_main_left_paragraph">
                      <p className="main_container_upBox_content_main_left_paragraph_pTitle">Data publicação:</p>
                      <p className="main_container_upBox_content_main_left_paragraph_pContent">10/08/2020</p>
                    </div>
                    {/* <div className="main_container_upBox_content_main_left_paragraph">
                      <p className="main_container_upBox_content_main_left_paragraph_pTitle">Valor: </p>
                      <p className="main_container_upBox_content_main_left_paragraph_pContent">R$ 100,00</p>
                    </div> */}
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
                <div className="buttons">
                  <button onClick={() => setShowResp(true)} className="buttons_btn" >Reservar</button>
                  {/* <LinkButton content="Reservar" onclick={() => reserve(bookInfo.id, userId)} className="main_container_downBox_button" />
                  <LinkButton content="Comprar" className="main_container_downBox_button" />
                  <LinkButton content="Baixar" className="main_container_downBox_button" /> */}
                </div>
              </div>
            </div>
            
          </div>
          <div className="main_description">
            <h3>Descrição</h3>
            <p>{bookInfo.descricao}</p>
          </div>
        </main>
      }
      <Footer />
    </div>
  )
}
export default Livro;
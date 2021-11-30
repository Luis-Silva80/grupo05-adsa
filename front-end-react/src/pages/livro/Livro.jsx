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
import indisponivel from "../../assets/fotoIndisponivel.png"
import LinkButton from '../../components/button/Button';
import livro from '../../assets/book.png';
let admin = undefined;


function Livro() {
    
  Autentication();

  const userId = parseInt(localStorage.getItem('userId'))
  const [ isAluno, setIsAluno ] = useState(false);
  const [ isAdmin, setIsAdmin ] = useState(false);  
  const bookId = parseInt(localStorage.getItem('bookId'));
  const bookName = localStorage.getItem('bookName');  
  const [respInfo, setRespInfo] = useState([]);
  const [bookInfoGoogle, setBookInfoGoogle] = useState([]);
  const [bookInfoApi, setBookInfoApi] = useState([]);


  useEffect(async() => {
    await api
    .get("/aluno")
    .then((response) => {
      if (response.data.length === 0) {
        admin = 1;
      }
      response.data.map(user => {
        if (userId == user.id) {
          admin = 0;
        }
      })
      if (admin == undefined) {
        admin = 1;
      }
    })
    .catch((err) => {
          console.error("ops! ocorreu um erro" + err);
      });
  }, []);

  useEffect(async () => {
    setRespInfo({ titulo: "Sucesso", parag: "Entre em seu perfil para verificar o livro clicando no botão abaixo"})

    await api
      .get(`/biblioteca/${bookId}`)
      .then((response) => {
        setBookInfoApi(response.data);
        console.log(response.data);
      })
      .catch((err) => {
        console.error("ops! ocorreu um erro" + err);
      });
  }, []);

  useEffect(async () => {
    await api
    .get(`https://www.googleapis.com/books/v1/volumes?q=intitle:${bookName}`)
    .then(response => {
      setBookInfoGoogle(response.data.items[0]);
      console.log(response.data.items[0]);
    })
  },[])

  function reserve() {
    const resp = document.getElementById('respReserv');

    api
    .put(`/biblioteca/reservar/${userId}/${bookId}`)
    .then((response) => {
      if (response.status === 200) {
        setRespInfo({ titulo: "Sucesso", parag: "Entre em seu perfil para verificar o livro clicando no botão abaixo", btn: "Perfil", link:"/perfilUsuario" })
        resp.classList.add("success");
        resp.classList.add("active");
        localStorage.setItem("idReserva", response.data)
      } else {
        setRespInfo({ titulo: "Ocorreu um erro", parag: "Entre em contato com o nosso time de suporte clicando no botão abaixo", btn: "Contato", link:"/contato" })
      }
    })
    .catch((err) => {
      setRespInfo({ titulo: "Ocorreu um erro", parag: "Entre em contato com o nosso time de suporte clicando no botão abaixo", btn: "Contato", link:"/contato" })
      resp.classList.add("error");
      resp.classList.add("active");
      console.error("ops! ocorreu um erro" + err);
    });
  }
  function deleted(){
  const resp = document.getElementById('respReserv');

  api
  .delete(`/biblioteca/${bookInfoApi.id}`)
  .then((response) => {
    if (response.status === 200) {
      setRespInfo({ titulo: "Sucesso", parag: "Livro excluido, voltar a lista de livros", btn:"Lista de livros",link:"/listaLivros" })
      resp.classList.add("success");
      resp.classList.add("active");
      localStorage.setItem("idReserva", response.data)
    } else {
      setRespInfo({ titulo: "Ocorreu um erro", parag: "Entre em contato com o nosso time de suporte clicando no botão abaixo", btn: "Contato", link:"/contato" })
    }
  })
  .catch((err) => {
    setRespInfo({ titulo: "Ocorreu um erro", parag: "Entre em contato com o nosso time de suporte clicando no botão abaixo", btn: "Contato", link:"/contato" })
    resp.classList.add("error");
    resp.classList.add("active");
    console.error("ops! ocorreu um erro" + err);
  });
}

  return (
    <div id="rootLivro">
      <SideBar />

      {bookInfoGoogle.length === 0 || bookInfoApi.length === 0 ?
        <Loading /> :
        <main className="main">
          <Resp  titulo={respInfo.titulo} parag={respInfo.parag} btn={respInfo.btn} link={respInfo.link} /> 
          <div className="main_container">
            <div className="main_container_upBox">
              <div className="main_container_upBox_imgBox">
                <img src={bookInfoGoogle.volumeInfo?.imageLinks.thumbnail ? bookInfoGoogle.volumeInfo?.imageLinks.thumbnail : indisponivel} className="main_container_upBox_imgBox_img" />
              </div>
              <div className="main_container_upBox_content">
                <h3 className="main_container_upBox_content_title">{bookInfoGoogle.volumeInfo?.title}</h3>
                <div className="main_container_upBox_content_main">
                  <div className="main_container_upBox_content_main_left">
                    <div className="main_container_upBox_content_main_left_paragraph">
                      <p className="main_container_upBox_content_main_left_paragraph_pTitle">Autor:</p>
                      <p className="main_container_upBox_content_main_left_paragraph_pContent">{bookInfoGoogle.volumeInfo?.authors[0]}</p>
                    </div>
                    <div className="main_container_upBox_content_main_left_paragraph">
                      <p className="main_container_upBox_content_main_left_paragraph_pTitle">Data publicação:</p>
                      <p className="main_container_upBox_content_main_left_paragraph_pContent">{bookInfoGoogle.volumeInfo?.publishedDate?.replaceAll("-", "/")}</p>
                    </div>
                    <div className="main_container_upBox_content_main_left_paragraph">
                      <p className="main_container_upBox_content_main_left_paragraph_pTitle">Preço do mercado</p>
                      <p className="main_container_upBox_content_main_left_paragraph_pContent">R$: {bookInfoGoogle.saleInfo?.listPrice?.amount ? bookInfoGoogle.saleInfo?.listPrice?.amount : "00.00"}</p>
                    </div>
                    {/* <div className="main_container_upBox_content_main_left_paragraph">
                      <p className="main_container_upBox_content_main_left_paragraph_pTitle">Valor: </p>
                      <p className="main_container_upBox_content_main_left_paragraph_pContent">R$ 100,00</p>
                    </div> */}
                  </div>
                  <div className="main_container_upBox_content_main_rigth">
                    <div className="main_container_upBox_content_main_rigth_paragraph">
                      <p className="main_container_upBox_content_main_rigth_paragraph_pTitle">Status:</p>
                      <p className="main_container_upBox_content_main_left_paragraph_pContent">{bookInfoApi.statusLivro}</p>
                    </div>
                    <div className="main_container_upBox_content_main_rigth_paragraph">
                      <p className="main_container_upBox_content_main_rigth_paragraph_pTitle">Avaliação</p>
                      <p className="main_container_upBox_content_main_rigth_paragraph_pContent">10/10</p>
                    </div>
                  </div>  
                </div>
                <div className="buttons">
                  
                  <button onClick={() => reserve()} className="buttons_btn" >Reservar</button>
                  {/* <LinkButton content="Reservar" onclick={() => reserve()} className="main_container_downBox_button" /> */}
                  <a href={bookInfoGoogle.volumeInfo?.infoLink} target="_blank" className="main_container_downBox_button" >Comprar</a>
                  <a href={bookInfoGoogle.accessInfo?.pdf.acsTokenLink} target="_blank" className="main_container_downBox_button" >Baixar</a>
                  {
                  admin == 1
                  ?
                   <button onClick={() => deleted()} className="buttons_btn" id="btn_deletar" >Deletar</button>
                  :
                  "" 
                  }
                   {/* <LinkButton content="Reservar" onclick={() => reserve(bookInfo.id, userId)} className="main_container_downBox_button" />
                  <LinkButton content="Comprar" className="main_container_downBox_button" />
                  <LinkButton content="Baixar" className="main_container_downBox_button" /> */}
                </div>
              </div>
            </div>
            
          </div>
          <div className="main_description">
            <h3>Descrição</h3>
            <p>{bookInfoGoogle.volumeInfo?.description ? bookInfoGoogle.volumeInfo?.description : bookInfoApi.descricao}</p>
          </div>
        </main>
      }
      <Footer />
    </div>
  )
}
export default Livro;
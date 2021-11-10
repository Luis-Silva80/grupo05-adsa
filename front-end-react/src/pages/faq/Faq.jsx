import React from "react";
import "./style.scss";

// import components
import Footer from '../../components/footer/Footer';
import SideBar from '../../components/sideBar/SideBar';
import Autentication from "../../services/autentication";

function Question({idonClick, idDiv, text, title}) {

  Autentication();

  function ativar() {
    var div = document.getElementById(`${idDiv}`);
    var bttn = document.getElementById(`${idonClick}`);
  
    div.classList.toggle("active");
    bttn.classList.toggle("active");
  }

  return(
    <>
      <div id={idDiv} className="main_question">
        <h2 className="main_question_title">{title}</h2>
        <button id={idonClick} className="main_question_btn" onClick={ativar}></button>
        <p className="main_question_parag">{text}</p>
      </div>
    </>
  );
}

function Faq() {

  return (
    <>
      <SideBar/>
      <main className="main container" id="rootFaq">
        <h1 className="main_title">Perguntas Frequentes</h1>

        <Question idonClick="btn1" idDiv="d1" title="Como faço para reservar um livro?" text="Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempora commodo consequat."/>
        <Question idonClick="btn2" idDiv="d2" title="Como faço para reservar um livro?" text="Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempora commodo consequat."/>
        <Question idonClick="btn3" idDiv="d3" title="Como faço para reservar um livro?" text="Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempora commodo consequat."/>
        <Question idonClick="btn4" idDiv="d4" title="Como faço para reservar um livro?" text="Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempora commodo consequat."/>
        <Question idonClick="btn5" idDiv="d5" title="Como faço para reservar um livro?" text="Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempora commodo consequat."/>
        <Question idonClick="btn6" idDiv="d6" title="Como faço para reservar um livro?" text="Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempora commodo consequat."/>

      </main>
      <Footer/>
    </>
  );
}

export default Faq;
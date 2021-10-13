import React from "react";
import "./style.scss";

// import components
import Footer from '../../components/footer/Footer';
import SideBar from '../../components/sideBar/SideBar';

function Faq() {
  return (
    <>
      <SideBar/>
      <main className="main container" id="rootFaq">
        <h1 className="main_title">Perguntas Frequentes</h1>

        <div id="d1" className="main_question">
          <h2 className="main_question_title">Como faço para reservar um livro?</h2>
          <button id="btn1" className="main_question_btn" onclick="ativar('btn1' , 'd1')"></button>
          <p className="main_question_parag">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
            tempora commodo consequat.</p>
        </div>

        <div id="d2" className="main_question">
          <h2 className="main_question_title">Como faço para reservar um livro?</h2>
          <button id="btn2" className="main_question_btn" onclick="ativar('btn2' , 'd2')"></button>
          <p className="main_question_parag">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
            tempora commodo consequat.</p>
        </div>

        <div id="d3" className="main_question">
          <h2 className="main_question_title">Como faço para reservar um livro?</h2>
          <button id="btn3" className="main_question_btn" onclick="ativar('btn3' , 'd3')"></button>
          <p className="main_question_parag">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
            tempora commodo consequat.</p>
        </div>
        <div id="d4" className="main_question">
          <h2 className="main_question_title">Como faço para reservar um livro?</h2>
          <button id="btn3" className="main_question_btn" onclick="ativar('btn4' , 'd4')"></button>
          <p className="main_question_parag">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
            tempora commodo consequat.</p>
        </div>
        <div id="d5" className="main_question">
          <h2 className="main_question_title">Como faço para reservar um livro?</h2>
          <button id="btn3" className="main_question_btn" onclick="ativar('btn5' , 'd5')"></button>
          <p className="main_question_parag">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
            tempora commodo consequat.</p>
        </div>
        <div id="d6" className="main_question">
          <h2 className="main_question_title">Como faço para reservar um livro?</h2>
          <button id="btn3" className="main_question_btn" onclick="ativar('btn6' , 'd6')"></button>
          <p className="main_question_parag">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
            tempora commodo consequat.</p>
        </div>
        <div id="d7" className="main_question">
          <h2 className="main_question_title">Como faço para reservar um livro?</h2>
          <button id="btn3" className="main_question_btn" onclick="ativar('btn7' , 'd7')"></button>
          <p className="main_question_parag">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
            tempora commodo consequat.</p>
        </div>
        <div id="d8" className="main_question">
          <h2 className="main_question_title">Como faço para reservar um livro?</h2>
          <button id="btn3" className="main_question_btn" onclick="ativar('btn8' , 'd8')"></button>
          <p className="main_question_parag">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
            tempora commodo consequat.</p>
        </div>
      </main>
      <Footer/>
    </>
  );
}

export default Faq;
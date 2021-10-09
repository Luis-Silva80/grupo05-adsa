import React from "react";

// import components
import Footer from '../../components/footer/Footer';
import SideBar from '../../components/sideBar/SideBar';

function Faq() {
  return (
    <>
      <SideBar/>
      <main class="main container">
        <h1 class="main_title">Perguntas Frequentes</h1>

        <div id="d1" class="main_question">
          <h2 class="main_question_title">Como faço para reservar um livro?</h2>
          <button id="btn1" class="main_question_btn" onclick="ativar('btn1' , 'd1')"></button>
          <p class="main_question_parag">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
            tempora commodo consequat.</p>
        </div>

        <div id="d2" class="main_question">
          <h2 class="main_question_title">Como faço para reservar um livro?</h2>
          <button id="btn2" class="main_question_btn" onclick="ativar('btn2' , 'd2')"></button>
          <p class="main_question_parag">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
            tempora commodo consequat.</p>
        </div>

        <div id="d3" class="main_question">
          <h2 class="main_question_title">Como faço para reservar um livro?</h2>
          <button id="btn3" class="main_question_btn" onclick="ativar('btn3' , 'd3')"></button>
          <p class="main_question_parag">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
            tempora commodo consequat.</p>
        </div>
        <div id="d4" class="main_question">
          <h2 class="main_question_title">Como faço para reservar um livro?</h2>
          <button id="btn3" class="main_question_btn" onclick="ativar('btn4' , 'd4')"></button>
          <p class="main_question_parag">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
            tempora commodo consequat.</p>
        </div>
        <div id="d5" class="main_question">
          <h2 class="main_question_title">Como faço para reservar um livro?</h2>
          <button id="btn3" class="main_question_btn" onclick="ativar('btn5' , 'd5')"></button>
          <p class="main_question_parag">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
            tempora commodo consequat.</p>
        </div>
        <div id="d6" class="main_question">
          <h2 class="main_question_title">Como faço para reservar um livro?</h2>
          <button id="btn3" class="main_question_btn" onclick="ativar('btn6' , 'd6')"></button>
          <p class="main_question_parag">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
            tempora commodo consequat.</p>
        </div>
        <div id="d7" class="main_question">
          <h2 class="main_question_title">Como faço para reservar um livro?</h2>
          <button id="btn3" class="main_question_btn" onclick="ativar('btn7' , 'd7')"></button>
          <p class="main_question_parag">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
            tempora commodo consequat.</p>
        </div>
        <div id="d8" class="main_question">
          <h2 class="main_question_title">Como faço para reservar um livro?</h2>
          <button id="btn3" class="main_question_btn" onclick="ativar('btn8' , 'd8')"></button>
          <p class="main_question_parag">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
            tempora commodo consequat.</p>
        </div>
      </main>
      <Footer/>
    </>
  );
}

export default Faq;
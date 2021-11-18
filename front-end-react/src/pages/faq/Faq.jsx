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
    <div id="rootFaq">
      <SideBar/>
      <main className="main" >
        <h1 className="main_title">Perguntas Frequentes</h1>

        <Question idonClick="btn1" idDiv="d1" title="Esqueci a minha senha. O que fazer?" text="Na página de login, é só clicar em 'Esqueci a minha senha' e começará um rápido processo de recuperação da senha utilizando confirmação via e-mail."/>
        <Question idonClick="btn2" idDiv="d2" title="Como funciona os critérios para o ranking dos usuários?" text="Ao concluir a devolução de um livro na data correta, manter uma boa participação na plataforma ao ganhar estrelas de outros usuários ao escrever resenhas sobre livros, o usuário ganhará mais xp, aumentando seu nível e sua posição no ranking."/>
        <Question idonClick="btn3" idDiv="d3" title="Como posso saber os benefícios do nível em que estou?" text="Ao alcançar o próximo nível você receberá uma mensagem com os atuais benefícios do seu usuário, além de surgir novas interações e possibilidades como por exemplo: ao chegar no nível 20, você poderá renovar o mesmo livro por uma terceira vez sem que precise efetuar a sua devolução."/>
        <Question idonClick="btn4" idDiv="d4" title="Por quanto tempo é possível reservar um livro?" text="Para uma conta iniciante, o tempo padrão é possível fazer a reserva por 10 dias, e conforme o nível, o usuário poderá escolher opções com mais dias para a reserva do livro."/>
        <Question idonClick="btn5" idDiv="d5" title="Minha conta foi inativada. O que fazer?" text="Ao passar o prazo de 30 dias, sua conta será permanentemente excluída. Caso queira reativar sua conta, basta clicar em 'desejo reativar minha conta' e seguir o passo a passo, ou solicitar via email para um dos administradores da plataforma."/>

      </main>
      <Footer/>
    </div>
  );
}

export default Faq;
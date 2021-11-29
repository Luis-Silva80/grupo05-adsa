import React, { useState } from 'react';
import "./style.scss";

import Footer from '../../components/footer/Footer';
import Header from '../../components/header/Header';
import Resp from '../../components/resp/Resp';

import api from "../../services/api";
import { Link } from 'react-router-dom';
import { useHistory } from 'react-router-dom';


function Login() {

  const history = useHistory();
  const [ email, setEmail ] = useState();
  const [ password, setPassword ] = useState();
  
  function Submit(event) {
    
    event.preventDefault();
    
    api({
      method: 'post',
      url: '/autenticacao/' + email + '/' + password
    }).then(response => {
      if (response.status === 200) {
        localStorage.setItem('userId', response.data);
        history.push("/perfilUsuario");
      }
    })
    .catch((erro) => {
      console.log(erro);
      document.querySelector(".main_errorLogin").classList.add("active");
    });
  };

  return (
    <div id="rootLogin"y>
      <Header />
        <main className="main">
          <h1 className="main_title">Login</h1>
          <p className="main_parag">Preencha os campos abaixo para acessar a sua conta.</p>
          <form action="" className="main_form" id="form" onSubmit={Submit}>
            <label className="main_form_label">Email:</label>
            <input onChange={e => setEmail(e.target.value)} type="text" id="email" name="email" required className="main_form_input" placeholder="exemplo.exemplo@email.com" />
            
            <label className="main_form_label">Senha:</label>
            <input onChange={e => setPassword(e.target.value)} type="password" id="password" name="password" required className="main_form_input" placeholder="*************" />
            <input type="submit" className="main_form_button" id="formBtn" value="Entrar" />
          </form>
          <p className="main_errorLogin">E-mail ou senha incorreto</p>
          <a className="main_forgot" href="#">Esqueci a minha senha</a>
          <div className="main_box">
            <p className="main_box_parag">Ainda não tem conta?</p>
            <Link to="/cadastroUsuario" className="main_box_link">Cadastrar</Link>
          </div>
        </main>
      <Footer />
    </div>
  );
}
export default Login;
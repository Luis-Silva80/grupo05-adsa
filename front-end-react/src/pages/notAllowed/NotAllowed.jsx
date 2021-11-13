import React from 'react';
import './style.scss';

// import components-
import Footer from '../../components/footer/Footer';
import Header from '../../components/header/Header';
import { Link } from 'react-router-dom';


import notAllowed from '../../assets/401.png';

function NotFound() {

  return (
    <div id="rootNotAllowed">
      <Header />
        <main className="main">
          <h1 className="main_title">Ops! Parece que você não tem acesso a essa página.</h1>
          <img className="main_img" src={notAllowed} alt="Page not found"/>
          <p className="main_parag">Caso algo não pareça certo, entre em <Link to="/contato">contato</Link></p>
          <Link to="/" className="main_btn">Voltar</Link>
        </main>
      <Footer />
    </div>
  );
}

export default NotFound;
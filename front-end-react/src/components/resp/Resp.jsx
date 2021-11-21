import React from 'react';
import { Link } from 'react-router-dom';
import './style.scss';
import closeButton from "../../assets/close.png";

function Resp(props) {
  return (
    <>
      <section id="respReserv" className="resp">
        <h3 className="resp_title">{props.titulo}</h3>
        <h3 className="resp_parag">{props.parag}</h3>
        {
          props.btn != undefined ?
          <Link className="resp_button" to={props.link}>{props.btn}</Link>
          :
          ""
        }
        <button className="resp_close" onClick={() => document.getElementById('respReserv').classList.remove("active")}><img src={closeButton} alt="Fechar" /></button>
      </section>
    </>
  )
}
export default Resp;
import React from 'react';
import './style.scss';

import closeButton from "../../assets/close.png";
import bookImg from "../../assets/book.png";

function Popup(props) {
  return (
    <>
    {props.visible && (
      <section className="popup">
        <img className="popup_img" src={bookImg} alt="Selected book" />
        <div className="popup_box">
          <h2 className="popup_box_title">{props.titulo}</h2>
          <div className="popup_box_infos">
            <h4 className="popup_box_infos_info">Status: <b>{props.status}</b></h4>
            <h4 className="popup_box_infos_info">Devolver até: <b>{props.devolver}</b></h4>
            <h4 className="popup_box_infos_info">Reservado em: <b>{props.reservar}</b></h4>
          </div>
          <div className="popup_box_btns">
            <button className="popup_box_btns_btn">Devolver</button>
            <button className="popup_box_btns_btn">Prorrogar</button>
          </div>
          <button className="popup_box_close" onClick={props.onClose}><img src={closeButton} alt="Fechar" /></button>
        </div>
      </section>
    )}
    </>
  )
}
export default Popup;
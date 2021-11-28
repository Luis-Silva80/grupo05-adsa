/* eslint-disable */  
import React, { useEffect, useState } from 'react';
import './style.scss';

import api from '../../services/api';

import closeButton from "../../assets/close.png";
import perfilIcon from '../../assets/perfilIcon.png';


function PopupUser(props) {



  const [userInfo, setUserInfo] = useState([]);

  useEffect(async () => {

    await api
      .get(`/aluno/${props.id}`)
      .then((response) => {
        setUserInfo(response.data);
        console.log("User data:", response.data);
      })
      .catch((err) => {
        console.error("ops! ocorreu um erro" + err);
      });
  }, []);









  return (
    <>
      {props.visible && (
        <section id="popup" className="main_popup">
          <img className="popup_img" src={perfilIcon} alt="user" />
          <img className="popup_close" src={closeButton} alt="close popup" />
          {/* <img className="popup_close" src={closeButton} onClick={CallPopup} alt="close popup"/> */}
          <div className="popup_user">
            <h2 className="popup_user_name">Usuário 2</h2>
            <h4 className="popup_user_email">Email: <b>lucas@gmail.com</b></h4>
            <h4 className="popup_user_status">Status: <b>Ativo</b></h4>
            <h4 className="popup_user_bookName">Livro reservado: <b>css for babies</b></h4>
            <h4 className="popup_user_reserved">Reservado em: <b>10/02/2021</b></h4>
            <h4 className="popup_user_return">Devolver em: <b>20/02/2021</b></h4>
            <div className="popup_user_box">
              <button className="popup_user_box_btn">Enviar email</button>
              <button className="popup_user_box_btn">Prorrogar</button>
            </div>
          </div>
        </section>
      )}
    </>
  )
}
export default PopupUser;
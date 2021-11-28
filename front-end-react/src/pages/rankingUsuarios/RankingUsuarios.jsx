import React, { useEffect, useState } from 'react';
import './style.scss';

// import api and links
import api from "../../services/api";

// import components-
import Footer from '../../components/footer/Footer';
import SideBar from '../../components/sideBar/SideBar';
import Autentication from "../../services/autentication";

// import images
import medal from '../../assets/medal.png';
import medal2 from '../../assets/medal2.png';
import medal3 from '../../assets/medal3.png';

function RankingUsuarios() {

  Autentication();

  const [userData, setUserData] = useState([]);

  useEffect(() => {
    api
      .get("/aluno")
      .then((response) => {
        setUserData(response.data);
        console.log(response.data);
      })
      .catch((err) => {
        console.error("ops! ocorreu um erro" + err);
      });
  }, []);

  return (

    <div id="rootListaUsuario">
      <SideBar />
      <main class="main container">
        <h1 class="main_title">Ranking de usuários</h1>

        <table class="main_table" id="table">
          <tbody>
            {/* {userData.map(item => (
              <tr class="main_table_user">
                <td class="main_table_user_item frst"><img class="main_table_user_img" src={usuarioImg} alt="user img" /></td>
                <td class="main_table_user_item name">{item.nome}</td>
                <td class="main_table_user_item email">{item.email}</td>
              </tr>
            ))} */}


            <tr class="main_table_user">
              <td class="main_table_user_item place"><img class="main_table_user_img" src={medal} alt="First user" /></td>
              <td class="main_table_user_item name">{userData.nome}</td>
              <td class="main_table_user_item pts">345 Pts</td>
              <td class="main_table_user_item nvl">Nvl 12</td>
            </tr>
            <tr class="main_table_user">
              <td class="main_table_user_item place"><img class="main_table_user_img" src={medal2} alt="First user" /></td>
              <td class="main_table_user_item name">Usuário 0002</td>
              <td class="main_table_user_item pts">313 Pts</td>
              <td class="main_table_user_item nvl">Nvl 11</td>
            </tr>
            <tr class="main_table_user">
              <td class="main_table_user_item place"><img class="main_table_user_img" src={medal3} alt="First user" /></td>
              <td class="main_table_user_item name">Usuário 0003</td>
              <td class="main_table_user_item pts">238 Pts</td>
              <td class="main_table_user_item nvl">Nvl 09</td>
            </tr>
          </tbody>
        </table>
      </main>
      <Footer />
    </div>
  );
}

export default RankingUsuarios;
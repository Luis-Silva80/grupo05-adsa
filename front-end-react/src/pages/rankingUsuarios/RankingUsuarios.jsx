import React, { useEffect, useState } from 'react';
import './style.scss';

// import api and links
import api from "../../services/api";

// import components-
import Footer from '../../components/footer/Footer';
import SideBar from '../../components/sideBar/SideBar';
import Autentication from "../../services/autentication";
import GamificationRanking from "../../services/gamificationRanking";


// import images
import medal from '../../assets/medal.png';
import medal2 from '../../assets/medal2.png';
import medal3 from '../../assets/medal3.png';

function RankingUsuarios() {

  Autentication();
  
  let n = 4;
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

  userData.sort((a, b) => (a.pontos > b.pontos) ? -1 : 1)
  return (

    <div id="rootRanking">
      <SideBar />
      <main class="main container">
        <h1 class="main_title">Ranking de usuários</h1>

        <table class="main_table" id="table">
          <tbody>

            {userData.map(item => (
              <tr class="main_table_user">
                {userData.indexOf(item) === 0 ?
                  <td class="main_table_user_item frst place"><img class="main_table_user_img" src={medal} alt="First user" /></td>
                  : userData.indexOf(item) === 1 ?
                  <td class="main_table_user_item frst place"><img class="main_table_user_img" src={medal2} alt="First user" /></td>
                  : userData.indexOf(item) === 2 ?
                  <td class="main_table_user_item frst place"><img class="main_table_user_img" src={medal3} alt="First user" /></td>
                  :
                  <td class="main_table_user_item frst place">{n++}º</td>
                }

                
                <td class="main_table_user_item name">{item.nome}</td>
                <td class="main_table_user_item pontos">{item.pontos}Pts</td>
                <td class="main_table_user_item lst level">{GamificationRanking(item.pontos)}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </main>
      <Footer />
    </div>
  );
}

export default RankingUsuarios;
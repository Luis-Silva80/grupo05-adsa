import React, { useEffect, useState } from 'react';
import './style.scss';

// import api and links
import api from "../../services/api";

// import components-
import Footer from '../../components/footer/Footer';
import SideBar from '../../components/sideBar/SideBar';
import Autentication from "../../services/autentication";
import GamificationRanking from "../../services/gamificationRanking";
import Loading from '../../components/loading/Loading';


// import images
import medal from '../../assets/medal.png';
import medal2 from '../../assets/medal2.png';
import medal3 from '../../assets/medal3.png';

function RankingUsuarios() {

  Autentication();

  const [userData, setUserData] = useState([]);
  const [bookData, setBookData] = useState([]);
  let n = 4;
  let n2 = 4;
  let tableBook = document.getElementById("tableBook")
  let tableUser = document.getElementById("tableUser")
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
  useEffect(() => {

    api
      .get("/biblioteca/favoritos")
      .then((response) => {
        setBookData(response.data);
        console.log(response.data);
      })
      .catch((err) => {
        console.error("ops! ocorreu um erro" + err);
      });
  }, []);

  userData.sort((a, b) => (a.pontos > b.pontos) ? -1 : 1)

  function callUser() {
    document.getElementById("tableUser").classList.add("active");
    document.getElementById("tableBook").classList.remove("active");
  }
  function callBook() {
    document.getElementById("tableBook").classList.add("active");
    document.getElementById("tableUser").classList.remove("active");
  }

  return (

    <div id="rootRanking">
      <SideBar />

      {userData.length === 0 ?
        <Loading /> :
        <main class="main">
          <h1 class="main_title">Ranking</h1>
          <div className="main_nav">
            <button className="main_nav_btn" onClick={() => callUser()}>Ranking de usuários</button>
            <button className="main_nav_btn" onClick={() => callBook()}>Ranking de livros</button>
          </div>

          <table class="main_tableUser active" id="tableUser">
            <tbody>
              {userData.map(item => (
                <tr class="main_tableUser_user">
                  {
                    userData.indexOf(item) === 0 ?
                      <td class="main_tableUser_user_item frst place"><img class="main_tableUser_user_img" src={medal} alt="First user" /></td>
                    : userData.indexOf(item) === 1 ?
                      <td class="main_tableUser_user_item frst place"><img class="main_tableUser_user_img" src={medal2} alt="First user" /></td>
                    : userData.indexOf(item) === 2 ?
                      <td class="main_tableUser_user_item frst place"><img class="main_tableUser_user_img" src={medal3} alt="First user" /></td>
                    :
                    <td class="main_tableUser_user_item frst place">{n++}º</td>
                  }
                  <td class="main_tableUser_user_item name">{item.nome}</td>
                  <td class="main_tableUser_user_item pontos">{item.pontos}Pts</td>
                  <td class="main_tableUser_user_item lst level">{GamificationRanking(item.pontos)}</td>
                </tr>
              ))}
            </tbody>
          </table>
          <table class="main_tableBook" id="tableBook">
            <tbody>
              {bookData.map(item => (
                <tr class="main_tableBook_user">
                  {bookData.indexOf(item) === 0 ?
                    <td class="main_tableBook_user_item frst place"><img class="main_tableBook_user_img" src={medal} alt="First user" /></td>
                    : bookData.indexOf(item) === 1 ?
                      <td class="main_tableBook_user_item frst place"><img class="main_tableBook_user_img" src={medal2} alt="First user" /></td>
                      : bookData.indexOf(item) === 2 ?
                        <td class="main_tableBook_user_item frst place"><img class="main_tableBook_user_img" src={medal3} alt="First user" /></td>
                        :
                        <td class="main_tableBook_user_item frst place">{n2++}º</td>
                  }
                  <td class="main_tableBook_user_item name">{item.titulo}</td>
                  <td class="main_tableBook_user_item pontos">Reservado {item.qtdReservas}X</td>
                  {
                    item.statusLivro.toLowerCase() === "indisponivel" ?
                    <td class="main_tableBook_user_item lst inactive">{item.statusLivro.toLowerCase()}</td>
                    :<td class="main_tableBook_user_item lst">{item.statusLivro.toLowerCase()}</td>

                  }
                </tr>
              ))}
            </tbody>
          </table>
        </main>
      }
      <Footer />
    </div>
  );
}

export default RankingUsuarios;
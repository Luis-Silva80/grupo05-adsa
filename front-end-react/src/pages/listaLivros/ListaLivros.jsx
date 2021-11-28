import React, { useEffect, useState } from 'react';
import api from "../../services/api";
import "./style.scss";
import Autentication from "../../services/autentication";
// import { Link } from 'react-router-dom';

// import components-
import Footer from '../../components/footer/Footer';
import SideBar from '../../components/sideBar/SideBar';
import Loading from '../../components/loading/Loading';
import BookCard from '../../components/bookCard/BookCard';
import Resp from '../../components/resp/Resp';


import indisponivel from "../../assets/fotoIndisponivel.png"
import imageLivro from '../../assets/book.png';

function ListaLivros() {

  Autentication();

  const [booksInfo, setBooksInfo] = useState([]);
  const [respInfo, setRespInfo] = useState([]);

  var bookTitle = [];
  var bookAZ = [];
  var bookZA = [];

  useEffect(async () => {
    console.log("estou no use");
    const resp = document.getElementById('respReserv');
    await api
      .get('/biblioteca')
      .then(response => {
        if (response.status === 204) {
          setRespInfo({ titulo: "Não encontrado", parag: "Nenhum livro foi encontrado no sistema", btn: "Contato", link:"/contato" })
          resp.classList.add("active");
          resp.classList.add("error");
          resp.classList.remove("success");
        }
        setBooksInfo(response.data);
        console.log("Livros :", response.data);
      })
      .catch((err) => {
        console.error("ops! ocorreu um erro" + err);
      });
  }, []);


  // --------------------- função responsavel por pesquisar na api do google books
  // const options = {
  //   method: 'GET',
  //   headers: { 'Content-Type': 'application/json', Accept: 'application/vnd.vtex.ds.v10+json' }
  // };
  // let index = 0
  // function search() {
  //   // let alert = document.getElementById('alert')
  //   let searchCallback = document.getElementById('searchCallback')
  //   let resp = document.getElementById('resp')
  //   let bookName = document.getElementById('name').value
  //   if (bookName.length < 3) {
  //     // alert.classList.add('show')
  //   } else {
  //     // alert.classList.remove('show')
  //     resp.innerHTML = ""
  //     // alterar os valores do index para implementar o scroll infinito    &startIndex=1
  //     fetch(`https://codeby-cors.integrationby.com.br/https://www.googleapis.com/books/v1/volumes?q=intitle:${bookName}&maxResults=40&startIndex=${index}`, options)
  //       .then(response => response.text())
  //       .then(result => {
  //         let data = JSON.parse(result)
  //         // index += 40
  //         console.log("data: ",data);

  //         searchCallback.innerHTML = `<h3>Exibindo ${data.totalItems} resultados encontrados para: ${bookName}</h3>`

  //         data.items.forEach(element => {

  //           resp.innerHTML +=
  //             `
  //               <div className="resp_book">
  //               <img className="resp_book_img" src="${element.volumeInfo.imageLinks ? element.volumeInfo.imageLinks.thumbnail : `${indisponivel}`}">
  //               ${element.volumeInfo.previewLink ? `<a className="resp_book_tag" target="_blank" href='${element.volumeInfo.previewLink}'>Preview</a>` : ""}
  //               <h3 className="resp_book_title">${element.volumeInfo.title}</h3>
  //               <a  className="resp_book_btn" onClick={storeId(${element.id})}>Ver mais</a>
  //               </div>
  //             `
  //         });
  //       })
  //       .catch(err => console.error(err));
  //   }
  // }

  // function infiniteSearch() {
  //   // alterar os valores do index para implementar o scroll infinito    &startIndex=1
  //   let resp = document.getElementById('resp')
  //   let bookName = document.getElementById('name').value

  //   console.log(window.scrollY);

  //   if (window.scrollY > 6000) {

  //     let index = 41;
  //     fetch(`https://codeby-cors.integrationby.com.br/https://www.googleapis.com/books/v1/volumes?q=intitle:${bookName}&maxResults=40&startIndex=${index}`, options)
  //       .then(response => response.text())
  //       .then(result => {
  //         let data = JSON.parse(result)
  //         data.items.forEach(element => {

  //           resp.innerHTML +=
  //             `
  //             <div className="resp_book">
  //             <img className="resp_book_img" src="${element.volumeInfo.imageLinks ? element.volumeInfo.imageLinks.thumbnail : `${indisponivel}`}">
  //             ${element.volumeInfo.previewLink ? `<a className="resp_book_tag" target="_blank" href='${element.volumeInfo.previewLink}'>Preview</a>` : ""}
  //             <h3 className="resp_book_title">${element.volumeInfo.title}</h3>
  //             <a href="/cadastroUsuario" className="resp_book_btn">Ver mais</a>
  //             </div>
  //           `
  //         });
  //       })
  //       .catch(err => console.error(err));

  //   }
  // }
  function storeId(value) {
    localStorage.setItem('id do livro', value)
  }

  function filter() {
    console.log("Cliquei no filtro");
    switch (document.getElementById('filter_combo').value) {
      case "tituloA-Z":
        booksInfo.map(book => {
          bookTitle.push(book.titulo)
        });
        bookTitle.sort();
        bookTitle.map(title => {
          booksInfo.map(book => {
            if (book.titulo == title) {
              bookAZ.push(book)
            }
          })
        })
        setBooksInfo(bookAZ)
        break;
      case "tituloZ-A":
        booksInfo.map(book => {
          bookTitle.push(book.titulo)
        });
        bookTitle.sort();
        bookTitle.map(title => {
          booksInfo.map(book => {
            if (book.titulo == title) {
              bookAZ.push(book)
            }
          })
        })
        for (let i = bookAZ.length -1; i >= 0; i--) {
          bookZA.push(bookAZ[i]);
        }
        setBooksInfo(bookZA)
        break;
      default:
          break;
    } 
  }

  return (
    <div id="rootListaLivro">
      <SideBar />

      {booksInfo.length === 0 ?
        <Loading /> :
        <main className="main" >
          <h1 className="main_title">Livros cadastrados</h1>

          <div className="main_nav">
            <input className="main_nav_input" placeholder="Digite o nome do livro" type="text" name="name" id="name" />
            <button className="main_nav_btn" >Pesquisar</button>
            <select id="filter_combo" onChange={() => filter()} className="main_nav_filter">
              <option className="main_nav_filter_value" value="#">Filtrar por: </option>
              <option className="main_nav_filter_value" value="tituloA-Z">Título A-Z</option>
              <option className="main_nav_filter_value" value="tituloZ-A">Título Z-A</option>
              <option className="main_nav_filter_value" value="pendencia">Categoria</option>
            </select>
          </div>
          <h3 id="searchCallback" className="main_callback"></h3>
          <div id="resp" className="resp">
            {
              booksInfo.map(item => (
                <BookCard key={item.id} image={imageLivro} idLivro={item.id} titulo={item.titulo} autor={item.autor} status={item.statusLivro} acao="Ver mais"/>
              ))
            }

          </div>
          {/* <button className="main_nav_btn" onClick={search}>Ver mais</button> */}

          {/* <div id="resp" className="resp" onScroll={infiniteSearch}></div> */}
          {/* <span className='main_alert'>Digite ao menos 3 letras</span> */}
          <Resp titulo={respInfo.titulo} parag={respInfo.parag} btn={respInfo.btn} link={respInfo.link} />
        </main>
      }
      <Footer />
    </div>
  );
}

export default ListaLivros;
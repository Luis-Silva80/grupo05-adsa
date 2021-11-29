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
  const [ googleBooks, setGoogleBooks ] = useState();
  const [ springBooks, setSpringBooks ] = useState();

  let bookTitleFilter = [];
  let bookAZFilter = [];
  let bookZAFilter = [];
  let getBooksSearch = [];


  function storeId(value) {
    localStorage.setItem('id do livro', value)
  }

  function filter() {
    console.log("Cliquei no filtro");
    switch (document.getElementById('filter_combo').value) {
      case "tituloA-Z":
        booksInfo.map(book => {
          bookTitleFilter.push(book.titulo)
        });
        bookTitleFilter.sort();
        bookTitleFilter.map(title => {
          booksInfo.map(book => {
            if (book.titulo === title) {
              bookAZFilter.push(book)
            }
          })
        })
        setBooksInfo(bookAZFilter)
        break;
      case "tituloZ-A":
        let finalBookZAFilter = [];
        booksInfo.map(book => {
          bookTitleFilter.push(book.titulo)
        });
        bookTitleFilter.sort();
        bookTitleFilter.map(title => {
          booksInfo.map(book => {
            if (book.titulo == title) {
              bookZAFilter.push(book)
            }
          })
        })
        for (let i = bookZAFilter.length -1; i >= 0; i--) {
          finalBookZAFilter.push(bookZAFilter[i]);
        }
        setBooksInfo(finalBookZAFilter)
        break;
      default:
          break;
    } 
  }

  useEffect(async () => {
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


  // async function getBooksOnLoad () {

  //       await api
  //       .get(`https://www.googleapis.com/books/v1/volumes?q=intitle:`)
  //       .then(response => {
  //         setGoogleBooks(response.data.items);
  //       })
    
  //       await api
  //       .get('/biblioteca')
  //       .then(response => {
  //         setSpringBooks(response.data);
  //       })
  //       .catch((err) => {
  //         console.error("ops! ocorreu um erro" + err);
  //       });
  //       googleBooks?.map(book => {
  //         springBooks?.map(springBook => {
  //           const bookTitleLower = book.volumeInfo.title.toLowerCase();
  //           const springBookTitleLower = springBook.titulo.toLowerCase();
    
  //           const googlePublisherLower = book.volumeInfo.publisher?.toLowerCase();
  //           const springPublisherLower = springBook.editora.toLowerCase();
  
  //           if (bookTitleLower == springBookTitleLower && springPublisherLower == googlePublisherLower) {
  //             getBooksSearch.push({
  //               ...book,
  //               ...springBook
  //             });
  //             console.log("É parecido", getBooksSearch);
  //           }
  //         })
  //       });
        
  //       if (getBooksSearch.length != 0) {
  //         setBooksInfo(getBooksSearch);
  //       }
        
  //       console.log("BooksInfo aquii", booksInfo);
        
  //       console.log("googleBooks aquii", googleBooks);
  //       console.log("SpringBooks aquii", springBooks);
    
  // }

  async function getBooks() {
    let bookSearch = document.getElementById('name').value.toLowerCase();
    
    await api
    .get(`https://www.googleapis.com/books/v1/volumes?q=intitle:${bookSearch}&maxResults=40`)
    .then(response => {
      setGoogleBooks(response.data.items);
    })

    await api
    .get('/biblioteca')
    .then(response => {
      setSpringBooks(response.data);
    })
    .catch((err) => {
      console.error("ops! ocorreu um erro" + err);
    });

    googleBooks?.map(book => {
      springBooks?.map(springBook => {
        const bookTitleLower = book.volumeInfo.title.toLowerCase();
        const springBookTitleLower = springBook.titulo.toLowerCase();

        if (bookTitleLower.includes(bookSearch) && springBookTitleLower.includes(bookSearch)) {
          const googlePublisherLower = book.volumeInfo.publisher?.toLowerCase();
          const springPublisherLower = springBook.editora.toLowerCase();

          if (bookTitleLower == springBookTitleLower && springPublisherLower == googlePublisherLower) {
            getBooksSearch.push({
              ...book,
              ...springBook
            });
          }
        }
      })
    });

    if (getBooksSearch.length != 0) {
      setBooksInfo(getBooksSearch);
    }
  }

  return (
    <div id="rootListaLivro">
      <SideBar />
      {/* {
        booksInfo.length != 0 ? <span></span> : getBooksOnLoad(), <span></span>
      } */}
      {booksInfo.length === 0 ?
        <Loading /> : 
        <main className="main" >
          <h1 className="main_title">Livros cadastrados</h1>

          <div className="main_nav">
            <input className="main_nav_input" placeholder="Digite o nome do livro" type="text" name="name" id="name" />
            <button className="main_nav_btn" onClick={() => getBooks()} >Pesquisar</button>
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
                <BookCard key={item.id} image={item.volumeInfo?.imageLinks.thumbnail ? item.volumeInfo?.imageLinks.thumbnail : indisponivel} idLivro={item.id} titulo={item.titulo} autor={item.autor} status={item.statusLivro} acao="Ver mais"/>
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
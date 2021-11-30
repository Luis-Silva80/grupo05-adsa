import React from "react";
import { BrowserRouter, Switch, Route } from "react-router-dom";

// Importando páginas
import Home from "./pages/home/Home";
import CadastroUsuario from "./pages/cadastroUsuario/CadastroUsuario";
import Faq from "./pages/faq/Faq";
import Contato from "./pages/contato/Contato";
import Livro from "./pages/livro/Livro";
import ListaLivros from "./pages/listaLivros/ListaLivros";
import ListaUsuarios from "./pages/listaUsuario/ListaUsuario";
import PerfilUsuario from "./pages/perfilUsuario/PerfilUsuario";
import Login from "./pages/login/Login";
import NotFound from "./pages/notFound/NotFound";
import CadastroLivro from "./pages/cadastroLivro/CadastroLivro";
import RankingUsuarios from "./pages/rankingUsuarios/RankingUsuarios";
import Graficos from "./pages/graficos/Graficos";
import NotAllowed from "./pages/notAllowed/NotAllowed";

import testePython from  "./pages/cadastroUsuario/testePython";


export default function Routes() {
    return (
        <BrowserRouter>
            <Switch>
                <Route path="/" exact component={Home} />
                <Route path="/cadastroUsuario" exact component={CadastroUsuario} />
                <Route path="/login" exact component={Login}/>
                <Route path="/listaUsuarios" exact component={ListaUsuarios} />
                <Route path="/perfilUsuario" exact component={PerfilUsuario}/>
                <Route path="/rankingUsuarios" exact component={RankingUsuarios}/>
                <Route path="/listaLivros" exact component={ListaLivros} />
                <Route path="/livro" exact component={Livro}/>
                <Route path="/contato" exact component={Contato} />
                <Route path="/faq" exact component={Faq} />
                <Route path="/cadastroLivro" exact component={CadastroLivro}/>
                <Route path="/graficos" exact component={Graficos}/>
                <Route path="/notAllowed" exact component={NotAllowed}/>

                <Route path="/teste" exact component={testePython}/>

                <Route path="*" exact component={NotFound} />

                
            </Switch>
        </BrowserRouter>
    );
}
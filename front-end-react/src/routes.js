import React from "react";
import { BrowserRouter, Switch, Route } from "react-router-dom";

// Importando páginas
import CadastroUsuario from "./pages/cadastroUsuario/CadastroUsuario";
import Home from "./pages/home/Home";
import Faq from "./pages/faq/Faq";
import Login from "./pages/login/Login";
import Livro from "./pages/livro/Livro";




export default function Routes() {
    return (
        <BrowserRouter>
            <Switch>
                <Route path="/" exact component={Home} />
                <Route path="/cadastroUsuario" exact component={CadastroUsuario} />
                <Route path="/faq" exact component={Faq} />
                <Route path="/login" exact component={Login} />
                <Route path="/livro" exact component={Livro} />

            </Switch>
        </BrowserRouter>
    );
}
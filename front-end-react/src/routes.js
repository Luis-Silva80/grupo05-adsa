import React from "react";
import { BrowserRouter, Switch, Route } from "react-router-dom";

// Importando páginas
import CadastroUsuario from "./pages/cadastroUsuario/CadastroUsuario";
import Home from "./pages/home/Home";
import Faq from "./pages/faq/Faq";


export default function Routes() {
    return (
        <BrowserRouter>
            <Switch>
                <Route path="/" exact component={Home} />
                <Route path="/cadastroUsuario" exact component={CadastroUsuario} />
                <Route path="/faq" exact component={Faq} />
            </Switch>
        </BrowserRouter>
    );
}
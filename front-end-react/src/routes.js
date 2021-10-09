import React from "react";
import { BrowserRouter, Switch, Route } from "react-router-dom";

// Importando páginas
import CadastroUsuario from "./pages/cadastroUsuario/CadastroUsuario";
import Home from "./pages/home/Home";


export default function Routes() {
    return (
        <BrowserRouter>
            <Switch>
                <Route path="/" exact component={Home} />
                <Route path="/cadastroUsuario" exact component={CadastroUsuario} />
            </Switch>
        </BrowserRouter>
    );
}
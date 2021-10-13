import React from "react";
import { BrowserRouter, Switch, Route } from "react-router-dom";

// Importando páginas
import Home from "./pages/home/Home";
import CadastroUsuario from "./pages/cadastroUsuario/CadastroUsuario";
import Faq from "./pages/faq/Faq";
import Contato from "./pages/contato/Contato"
import PerfilUsuario from "./pages/perfilUsuario/PerfilUsuario";


export default function Routes() {
    return (
        <BrowserRouter>
            <Switch>
                <Route path="/home" exact component={Home} />
                <Route path="/cadastroUsuario" exact component={CadastroUsuario} />
                <Route path="/faq" exact component={Faq} />
                <Route path="/contato" exact component={Contato}/>
                <Route path="/perfilUsuario" exact component={PerfilUsuario}/>
            </Switch>
        </BrowserRouter>
    );
}
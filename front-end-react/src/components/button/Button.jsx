import React from 'react';
import "./style.scss";
import { Link } from 'react-router-dom';

function Button({ path, content }) {
    return(
        <>
            <Link className="button_link"to={path}>{content}</Link>
        </>
    );
}

export default Button;
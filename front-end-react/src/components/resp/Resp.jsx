import React from 'react';
import { Link } from 'react-router-dom';
import './style.scss';


function Resp(props) {
  return (
    <>
      <section id="resp" className="resp">
        <h3 className="resp_title">{props.title}</h3>
          <h3 className="resp_parag">{props.parag}</h3>
          <Link className="resp_button" to={props.link}>{props.btn}</Link>
      </section>
    </>
  )
}
export default Resp;
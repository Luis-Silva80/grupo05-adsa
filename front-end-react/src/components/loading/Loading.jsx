import React from 'react';
import './style.scss';

import loadingGif from "../../assets/loading2.gif";

function Loading() {
  return (
    <div className="loading">
      <h2 className="loading_title">Carregando informações...</h2>
      <img className="loading_img" src={loadingGif} alt="loading gif" />
    </div>
  )
}
export default Loading;
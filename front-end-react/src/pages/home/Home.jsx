// React and hooks
import React from 'react';
import { Link } from 'react-router-dom';

// import components
import Header from '../../components/header/Header';
import Footer from '../../components/footer/Footer';

// import css
import './home.scss';

// import imgs
import mainImg from '../../assets/main-image.png';
import graph1 from '../../assets/graph.png';


function Home() {
    return(
        <>
            <Header />

            
                <main className="homeMain">
                    <div className="homeMain-box">
                        <h1 className="homeMain-box-title">ThothLib</h1>
                        <p className="homeMain-box-parag">Especialistas em Softwares de gestão e ERP.</p>
                        <Link className="homeMain-box-link">Saiba mais</Link>
                    </div>
                    <img className="homeMain-img" src={mainImg} alt="TothLib main banner" />
                    <img className="homeMain-graph" src={graph1} alt="TothLib graph" />
                </main>

            
            <Footer />
        </>
    );
}

export default Home;
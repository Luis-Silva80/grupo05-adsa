// React and hooks
import React from 'react';
import { Link } from 'react-router-dom';

// import components
import Header from '../../components/header/Header';
import Footer from '../../components/footer/Footer';

// import css
import './style.scss';

// import imgs
import mainImg from '../../assets/main-image.png';
import quemSomos from '../../assets/quemSomos.png';
import graph1 from '../../assets/graph.png';
import graph2 from '../../assets/graph (1).png';
import arrowScroll from '../../assets/arrowScroll.png';


function Home() {
    return(
        <>
            <Header />

                <section id="rootHome">
                    <main className="homeMain">
                        <div className="homeMain-box">
                            <h1 className="homeMain-box-title">ThothLib</h1>
                            <p className="homeMain-box-parag">Especialistas em Softwares de gestão e ERP.</p>
                            <Link className="homeMain-box-link">Saiba mais</Link>
                        </div>
                        <img className="homeMain-img" src={mainImg} alt="TothLib main banner" />
                        <img className="homeMain-arrowScroll" src={arrowScroll} alt="TothLib arrow scroll" />
                        
                    </main>

                    <section className="homeSection">
                        <div className="homeSection-box">
                            <h3 className="homeSection-box-title">Quem Somos</h3>
                            
                            <p className="homeSection-box-parag">
                            Antes de tudo SOMOS UM TIME!
                            </p>
                            <p className="homeSection-box-parag">Apaixonados por tecnologia e desenvolvimento, formamos uma fábrica de softwares especializados em gestão e controle de produtos. </p>
                        </div>
                        <img className="homeSection-img" src={quemSomos} alt="TothLib quem somos" />
                        <img className="homeSection-graph" src={graph2} alt="TothLib graph" />
                    </section>
                </section>
                
            
            <Footer />
        </>
    );
}

export default Home;
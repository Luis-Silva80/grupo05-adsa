// React and hooks
import React from 'react';
import { Link } from 'react-router-dom';

// import components
import Header from '../../components/header/Header';
import Footer from '../../components/footer/Footer';
import BenefitCard from '../../components/benefitCard/BenefitCard';

// import css
import './style.scss';

// import imgs
import mainImg from '../../assets/main-image.png';
import quemSomos from '../../assets/quemSomos.png';
import graph1 from '../../assets/graph.png';
import graph2 from '../../assets/graph (1).png';
import arrowScroll from '../../assets/arrowScroll.png';
import dollarBenefit from '../../assets/dollar_benefit.png';
import bookBenefit from '../../assets/book_benefit.png';
import webProgrammingBenefit from '../../assets/web-programming_benefit.png';
import recycleBenefit from '../../assets/recycle-sign_benefit.png';
import agileBenefit from '../../assets/agile_benefit.png';
import contactUs from '../../assets/contact-us-amico.png';

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

                    <section className="homeSectionBenefit container">
                        <div className="homeSectionBenefit_description">
                            <h1 className="homeSectionBenefit_description_title">Benefícios</h1>
                            <p className="homeSectionBenefit_description_paragraph">Nossa aplicação agrega valor ao negócio, veja os principais benefícios abaixo.</p>
                        </div>
                        <div className="homeSectionBenefit_benefities">
                            <BenefitCard image={dollarBenefit} title="Custo benefício" description="Vamos proporcionar economia e tem um benefício proporcional." />
                            <BenefitCard image={bookBenefit} title="Organização" description="Vamos proporcionar uma organização no fluxo inteiro." />
                            <BenefitCard image={webProgrammingBenefit} title="Automatização" description="Aplicação completamente automatizada, com pouca interação humana." />
                            <BenefitCard image={recycleBenefit} title="Sustentabilidade" description="Aplicação totalmente auto sustentável." />
                            <BenefitCard image={agileBenefit} title="Agilidade" description="Rapidez em todas as informações e locações." />
                        </div>
                    </section>

                    <section className="homeSectionContactUs container">
                        <img className="homeSectionContactUs_img" src={contactUs} />
                        <div className="homeSectionContactUs_box">
                            <div className="homeSectionContactUs_box_content">
                                <h1 className="homeSectionContactUs_box_content_title">Fale conosco</h1>
                                <p className="homeSectionContactUs_box_content_paragraph">Sua opinião é muito inportante para nós! Entre em contato preenchendo os campos abaixo.</p>
                            </div>
                            <div className="homeSectionContactUs_box_inputs">
                                <input className="homeSectionContactUs_box_inputs_title" type="text" placeholder="Título"/>
                                <textarea className="homeSectionContactUs_box_inputs_description" type="text" placeholder="Decrição"/>
                            </div>
                            <Link className="homeSectionContactUs_box_button">Enviar</Link>
                        </div>
                    </section>

                </section>
                
            
            <Footer />
        </>
    );
}

export default Home;
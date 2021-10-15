// React and hooks
import React from 'react';
import { Link } from 'react-router-dom';
// import AOS from 'aos';

// import components
import Header from '../../components/header/Header';
import Footer from '../../components/footer/Footer';
import BenefitCard from '../../components/benefitCard/BenefitCard';

// import css
// import "https://unpkg.com/aos@2.3.1/dist/aos.css";
import './style.scss';

// import imgs
import mainImg from '../../assets/main-image.png';
import quemSomos from '../../assets/quemSomos.png';
import oqFazemos from '../../assets/oqFazemos.png';
import arrowScroll from '../../assets/arrowScroll.png';
import dollarBenefit from '../../assets/dollar_benefit.png';
import bookBenefit from '../../assets/book_benefit.png';
import webProgrammingBenefit from '../../assets/web-programming_benefit.png';
import recycleBenefit from '../../assets/recycle-sign_benefit.png';
import agileBenefit from '../../assets/agile_benefit.png';
import contactUs from '../../assets/contact-us-amico.png';

function Home() {
    // AOS.init();
    return(
        <>
            <Header />
                <section id="rootHome">
                    <main className="main">
                        <div className="main-box">
                            <h1 className="main-box-title">ThothLib</h1>
                            <p className="main-box-parag">Especialistas em Softwares de gestão e ERP.</p>
                            <Link className="main-box-link">Saiba mais</Link>
                        </div>
                        <img className="main-img" src={mainImg} alt="TothLib main banner" />
                        <img className="main-arrowScroll" src={arrowScroll} alt="TothLib arrow scroll" />
                    </main>
                    {/* <section className="about" data-aos="fade-right"> */}
                    <section className="about">
                        <div className="about-box">
                            <h3 className="about-box-title">Quem Somos</h3>
                            <p className="about-box-parag">Antes de tudo SOMOS UM TIME!</p>
                            <p className="about-box-parag">Apaixonados por tecnologia e desenvolvimento, formamos uma fábrica de softwares especializados em gestão e controle de produtos. </p>
                        </div>
                        <img className="about-img" src={quemSomos} alt="TothLib quem somos" />
                    </section>
                    {/* <section className="weDo reverse" data-aos="fade-left"> */}
                    <section className="weDo reverse">
                        <div className="weDo-box">
                            <h3 className="weDo-box-title">O que fazemos?</h3>
                            <p className="weDo-box-parag">Somos uma fábrica de softwares, especializados em gestão e controle de produtos. Nossos sístemas garantem mais segurança, controle, agilidade, e é escalável para diversos modelos de negócios.</p>
                        </div>
                        <img className="weDo-img" src={oqFazemos} alt="TothLib quem somos" />
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
// import css, React and hooks
import React from 'react';
// import 'style.scss';

// import components
import Header from '../../components/header/Header';
import Footer from '../../components/footer/Footer';

// import imgs
import mainImg from '../../assets/main-image.png';

function Index() {
    return(
        <>
            <Header />

            <div>
                <main>
                    <div>
                        <h1>ThothLib</h1>
                        <p>Especialistas em Softwares de gestão e ERP.</p>
                    </div>
                    <img src={mainImg} />
                </main>

            </div>
            
            <Footer />
        </>
    );
}

export default Index;
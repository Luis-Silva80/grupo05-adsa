import React from 'react';
import './style.scss';

function BenefitCard({ image, title, description }) {
    return(
        <>
            <div className="benefitCard_container">
                <div className="benefitCard_container_main">
                    <img className="benefitCard_container_main_img" src={image} />
                    <h1 className="benefitCard_container_main_title">{title}</h1>
                    <p className="benefitCard_container_main_description">{description}</p>
                </div>
            </div>
        </>
    );
}

export default BenefitCard;
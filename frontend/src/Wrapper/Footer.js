import facebook from '../media/images/logos/facebook.png';
import instagram from '../media/images/logos/instagram.png';
import tiktok from '../media/images/logos/tic-tac.png';
import linkedin from '../media/images/logos/linkedin.png';
// import '../App.css';
import './Footer.css';
// import { Routes, Route } from 'react-router-dom';
// import Accueil from '../pages/Accueil/accueil';
// import Compte from '../pages/Compte/compte';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
// import { Link } from "react-router-dom";


const Footer = () => {
    return (
        <footer className='footer'>
            <Container fluid>
                <Row className="align-items-center">
                    <Col sm={3}>
                        <div className="d-flex flex-wrap align-items-center">
                            <div><img src={instagram} className="img-footer" alt="instagram" /></div>
                            <div><img src={facebook} className="img-footer" alt="facebook" /></div>
                            <div><img src={tiktok} className="img-footer" alt="tiktok" /></div>
                            <div><img src={linkedin} className="img-footer" alt="linkedin" /></div>
                        </div>
                    </Col>
                    <Col sm={3} className="d-flex flex-column justify-content-center">
                        <div className='Initiative79'><b>INITIATIVE DEUX SEVRES</b></div>
                        <div>Pépinière d'Entreprises du Niortais</div>
                        <div>4, BD Louis Tardy</div>
                        <div>79000 Niort</div>
                    </Col>
                    <Col sm={3} className="d-flex flex-column justify-content-center">
                        <div>Téléphone : +33 6 79 87 56 09</div>
                        <div>accompagnement@initiativedeuxsevres.fr</div>
                    </Col>
                    <Col sm={3} className="d-flex flex-column justify-content-center">
                        <div>
                            <div>Du lundi au vendredi</div>
                            <div>de 08h30 à 12h30</div>
                            <div>et de 13h30 à 17h30</div>
                        </div>
                    </Col>
                </Row>
                <Row className="justify-content-md-center">
                    <Col md="auto">© 2020 Initiative France - Intranet</Col>  
                </Row>
            </Container>
        </footer>
    )
}
export default Footer;

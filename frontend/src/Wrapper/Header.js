import logo from '../media/images/logos/Nouvelle_Aquitaine-Logo-Reseau_Initiative.webp';
import logo_TTM_vert from '../media/images/logos/logo_TTM_vert.png';
import favicon_TTM from '../media/images/logos/favicon_TTM.png';
// import '../App.css';
import './Header.css';
import { Routes, Route } from 'react-router-dom';
// import Accueil from './pages/Accueil';
// import Compte from './pages/Compte';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Button from 'react-bootstrap/Button';
import { Link } from "react-router-dom";


const Header = () => {
    return (
        <header className="header">
            <Container fluid>
                <Row >
                    <Col sm={2} className="d-flex justify-content-first align-items-center">
                        <Link to = "/">
                            <div className="favicon-container">
                                <img src={favicon_TTM} className="favicon" alt="favicon_TTM" />
                            </div>
                        </Link>
                    </Col>
{/*                     <ul className="navbar-nav">
                        <li className="nav-item">
                            <Link className="nav-link" aria-current="page" to={"/"}>Mon compte</Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link" to={"/contact"}>Profils disponibles</Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link" to={"/services"}>Messages</Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link" to={"/blog"}>Mes Matchs</Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link" to={"/business"}>Ressources</Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link" to={"/business"}>Indicateurs</Link>
                        </li>
                    </ul> */}
                   <Col sm={1} className="d-flex justify-content-first align-items-top">
                            {/* <div className='menu'>Mon compte</div> */}
                            <Link className="menu" to={"/monCompte"}>Mon compte</Link>

                    </Col>
                    <Col sm={1} className="d-flex justify-content-center align-items-top">
                            <Link className='menu' to={"/profils"}>Profils disponibles</Link>
                    </Col>
                    <Col sm={1} className="d-flex justify-content-center align-items-top">
                            <Link className='menu' to={"/messages"}>Messages</Link>
                    </Col>
                    <Col sm={1} className="d-flex justify-content-center align-items-top">
                            <Link className='menu' to={"/matchs"}>Mes Matchs</Link>
                    </Col>
                    <Col sm={1} className="d-flex justify-content-center align-items-top">
                            <Link className='menu' to={"/ressources"}>Ressources</Link>
                    </Col>
                    <Col sm={1} className="d-flex justify-content-center align-items-top">
                            <Link className='menu' to={"/indicateurs"}>Indicateurs</Link>
                    </Col>
                    <Col sm={3} className="d-flex justify-content-end align-items-top">
                        <div className='bouton'>
                            <Link to="/connexion">
                                <button type="button" class="btn btn-outline-dark btn-sm" >Se connecter</button>
                            </Link>
                        </div>
                    </Col>
                    <Col sm={1} className="d-flex justify-content-center align-items-top">
                        <div className='bouton'>
                            <Link to="/creationCompte">
                                <button  type="button" class=" btn btn-dark btn-sm" >Créer un compte</button> 
                            </Link>
                        </div>
                    </Col>
                    <Col sm={1} className="d-flex justify-content-end align-items-center">
                        <div className="favicon-container">
                        </div>
                    </Col>

                </Row>
                <Row className="align-items-center">
                    <Col sm={6} className="d-flex justify-content-center align-items-center">
                        <div className="logo-container">
                            <img src={logo_TTM_vert} className="logo" alt="logo_TTM" />
                            

                        </div>
                    </Col>
                    <Col sm={6} className="d-flex justify-content-center align-items-center">
                        <div className="logo-container">
                            <img src={logo} className="logo" alt="logo" />


                        </div>
                    </Col>
                    <div class="line" />
                </Row>
            </Container>

        </header>
    );
};

export default Header;
import Wrapper from '../../Wrapper/Index'
import { useEffect, useState } from "react";
import { useLocation } from 'react-router-dom';
import { ChampSaisie } from '../CreationCompte/ChampSaisie';
import './filtres.css';
import Container from 'react-bootstrap/esm/Container';
import send from '../../media/images/logos/send_blanc.png';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
// import Container from 'react-bootstrap/Container';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../../AuthContext';

const Filtres = () => {

  //  Initialisation des états des valeurs de utilisateurDto
  const [utilisateurDto, setUtilisateurDto] = useState({
    idUtilisateur: '',
    accompagnementTypeList: [],
    secteursReseauxList: [],
  });

  const [isSubmitted, setIsSubmitted] = useState(false);
  const location = useLocation(); // Ce hook permet d’accéder à l’objet location qui représente l’URL actuelle de l’application 

  const idUtilisateur = localStorage.getItem('idUtilisateur');
  console.log('recupération de idUtilisateur - page - filtres :', idUtilisateur);

  const [secteursReseaux, setSecteursReseaux] = useState({
    secteur1: false,
    secteur2: false,
    secteur3: false,
    secteur4: false,
    secteur5: false,
  })

  const [accompagnements, setAccompagnements] = useState({
    typeAccompagnement1: false,
    typeAccompagnement2: false,
    typeAccompagnement3: false,
    typeAccompagnement4: false,
    typeAccompagnement5: false,
  })
  
  
  useEffect(() => {
    setUtilisateurDto({
      idUtilisateur: idUtilisateur,
      accompagnementTypeList: [],
      secteursReseauxList: []
    })
  }, [location]);

  const handleChange = (event) => {
    setUtilisateurDto({
      ...utilisateurDto, // L’opérateur de décomposition (...utilisateurDto) est utilisé pour copier toutes les propriétés existantes de utilisateurDto.
      // [name]: value
    });


    const { name, checked } = event.target;

    // Différencier les cases cochées
    if (name.startsWith("typeAccompagnement")) {
      setAccompagnements((prev) => ({
        ...prev,
        [name]: checked,
      }));
    } else if (name.startsWith("secteur")) {
      setSecteursReseaux((prev) => ({
        ...prev,
        [name]: checked,
      }));
    }
  }
  
  const handleSubmit = (event) => {
    event.preventDefault();
    console.log("Formulaire soumis"); // Formulaire soumis
    
    // Convertir les objets en tableaux de valeurs cochées
    const accompagnementTypeList = Object.keys(accompagnements)
      .filter(option => accompagnements[option])
      .map(option => parseInt(option.replace('typeAccompagnement', ''))); // Convertir en numéros d'ID;

    const secteursReseauxList = Object.keys(secteursReseaux)
      .filter(option => secteursReseaux[option])
      .map(option => parseInt(option.replace('secteur', ''))); // Convertir en numéros d'ID;

    // Mettre à jour utilisateurDto avec les valeurs sélectionnées
    setUtilisateurDto((prevUtilisateurDto) => {
      const utilisateurDto = {
        ...prevUtilisateurDto, // Utiliser l'état précédent pour garantir que l'on travaille avec la version la plus récente
        accompagnementTypeList,
        secteursReseauxList,
      };

      console.log("UtilisateurDto à envoyer :", utilisateurDto);

      fetch('http://localhost:8080/creationCompte/filtres', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(utilisateurDto)
      })
      .then(response => {
        if (!response.ok) {
          throw new Error('La réponse du réseau n\'était pas correcte');
        }
        return response.json();
      })
      .then(data => {
        console.log("Données reçues :", data);
        setIsSubmitted(true); // Indiquer que le formulaire a été soumis avec succès

        console.log("Secteurs/Réseaux sélectionnés :", secteursReseauxList.join(', '))
        console.log("Types accompagnements sélectionnés :", accompagnementTypeList.join(', '))
      })
      .catch(error => {
        console.error('Il y a eu un problème avec l\'opération fetch :', error);
      });

    return utilisateurDto;
    });
  }

  return (
    <Wrapper>
        <Container fluid>
      <form onSubmit={handleSubmit}> 
          <Row className="align-items-start">
            <Col sm={1}></Col>
            <Col sm={5} className="d-flex justify-content-start flex-column">
              <div className='titre'>
                <h1 className='text'>Secteurs/Reseaux</h1>
              </div>
                <label className="checkbox-label">
                  Activités de services administratifs et de soutien
                  <input
                    type="checkbox"
                    name="secteur1"
                    checked={secteursReseaux.secteur1}
                    onChange={handleChange}
                  /> 
                </label>
                <label className="checkbox-label">
                  Activités spécialisées, scientifiques et techniques
                  <input
                    type="checkbox"
                    name="secteur2"
                    checked={secteursReseaux.secteur2}
                    onChange={handleChange}
                  /> 
                </label>
                <label className="checkbox-label">
                  Agriculture, sylviculture, pêche
                  <input
                    type="checkbox"
                    name="secteur3"
                    checked={secteursReseaux.secteur3}
                    onChange={handleChange}
                  />
                </label>
                <label className="checkbox-label">
                  Arts, spectacles et activités récréatives
                  <input
                    type="checkbox"
                    name="secteur4"
                    checked={secteursReseaux.secteur4}
                    onChange={handleChange}
                    /> 
                </label>
                <label className="checkbox-label">
                  Commerce et réparation
                  <input
                    type="checkbox"
                    name="secteur5"
                    checked={secteursReseaux.secteur5}
                    onChange={handleChange}
                    /> 
                </label>
                <label className="checkbox-label">
                  Commerce et réparation
                  <input
                    type="checkbox"
                    name="secteur6"
                    checked={secteursReseaux.secteur6}
                    onChange={handleChange}
                  /> 
                </label><br />
              {/* </form> */}
            </Col>
            {/* <Col sm={1}></Col> */}
            <Col sm={5} className="d-flex justify-content-start flex-column">
              <div className='titre'>
                <h1 className='text'>Types d’accompagnement (demandés / proposés)</h1>
              </div>
              {/* <form onSubmit={handleSubmit}>  */}
                <br />
                <label className="checkbox-label">
                  Ressources humaines
                  <input
                    type="checkbox"
                    name="typeAccompagnement1"
                    checked={accompagnements.typeAccompagnement1}
                    onChange={handleChange}
                  /> 
                </label><br />
                <label className="checkbox-label">
                  Finance / Comptabilité
                  <input
                    type="checkbox"
                    name="typeAccompagnement2"
                    checked={accompagnements.typeAccompagnement2}
                    onChange={handleChange}
                  /> 
                </label><br />
                <label className="checkbox-label">
                  Juridique
                  <input
                    type="checkbox"
                    name="typeAccompagnement3"
                    checked={accompagnements.typeAccompagnement3}
                    onChange={handleChange}
                  />
                </label><br />
                <label className="checkbox-label">
                  Informatique
                  <input
                    type="checkbox"
                    name="typeAccompagnement4"
                    checked={accompagnements.typeAccompagnement4}
                    onChange={handleChange}
                    /> 
                </label><br />
                <label className="checkbox-label">
                  Commercial / Communication
                  <input
                    type="checkbox"
                    name="typeAccompagnement5"
                    checked={accompagnements.typeAccompagnement5}
                    onChange={handleChange}
                  /> 
                </label><br />
            </Col>
            <Col sm={1}></Col>
          </Row>
          {/* </Container>
          <Container fluid> */}
            <div className="position-bouton">
                <button
                  type="submit"
                  className="bouton-envoyer mt-3"
                  >
                  Enregistrer
                  {/* <img src={send} className="img-send" alt="send" /> */}
                </button>
            </div>
        </form>
          </Container>
      </Wrapper>
    )
  };
  
  export default Filtres;


        {/*
        <div class="column">
          <div className='titre'>
            <h1 className='text'>Secteurs/Reseaux</h1>
          </div>
          <form onSubmit={handleSubmit}> 
            <label className="checkbox-label">
              Activités de services administratifs et de soutien
              <input
                type="checkbox"
                name="secteur1"
                checked={secteursReseaux.secteur1}
                onChange={handleChange}
              /> 
            </label>
            <label className="checkbox-label">
              Activités spécialisées, scientifiques et techniques
              <input
                type="checkbox"
                name="secteur2"
                checked={secteursReseaux.secteur2}
                onChange={handleChange}
              /> 
            </label>
            <label className="checkbox-label">
              Agriculture, sylviculture, pêche
              <input
                type="checkbox"
                name="secteur3"
                checked={secteursReseaux.secteur3}
                onChange={handleChange}
              />
            </label>
            <label className="checkbox-label">
              Arts, spectacles et activités récréatives
              <input
                type="checkbox"
                name="secteur4"
                checked={secteursReseaux.secteur4}
                onChange={handleChange}
                /> 
            </label>
            <label className="checkbox-label">
              Commerce et réparation
              <input
                type="checkbox"
                name="secteur5"
                checked={secteursReseaux.secteur5}
                onChange={handleChange}
              /> 
            </label>
            <label className="checkbox-label">
              Commerce et réparation
              <input
                type="checkbox"
                name="secteur5"
                checked={secteursReseaux.secteur5}
                onChange={handleChange}
              /> 
            </label><br />
          </form>
        </div>
        <div class="column">
          <div className='titre'>
            <h1 className='text'>Secteurs/Reseaux</h1>
          </div>
          <form onSubmit={handleSubmit}> 
            <label className="checkbox-label">
              Activités de services administratifs et de soutien
              <input
                type="checkbox"
                name="secteur1"
                checked={secteursReseaux.secteur1}
                onChange={handleChange}
              /> 
            </label><br />
            <label className="checkbox-label">
              Activités spécialisées, scientifiques et techniques
              <input
                type="checkbox"
                name="secteur2"
                checked={secteursReseaux.secteur2}
                onChange={handleChange}
              /> 
            </label><br />
            <label className="checkbox-label">
              Agriculture, sylviculture, pêche
              <input
                type="checkbox"
                name="secteur3"
                checked={secteursReseaux.secteur3}
                onChange={handleChange}
              />
            </label><br />
            <label className="checkbox-label">
              Arts, spectacles et activités récréatives
              <input
                type="checkbox"
                name="secteur4"
                checked={secteursReseaux.secteur4}
                onChange={handleChange}
                /> 
            </label><br />
            <label className="checkbox-label">
              Commerce et réparation
              <input
                type="checkbox"
                name="secteur5"
                checked={secteursReseaux.secteur5}
                onChange={handleChange}
              /> 
            </label><br />
          </form>
        </div>
      </div> */}



{/* {!isSubmitted ? (
) : (
  <div className='row g-2 mb-3'>
    <div className="col-md d-flex align-items-center">
      <label htmlFor="idUtilisateur" className="form-label me-2">Identifiant : </label>
      <div className="custom-container">
        <input
          className="form-control custom-id"
          disabled
          name="idUtilisateur"
          id="idUtilisateur"
          value={utilisateurDto.idUtilisateur}
        />
      </div>
    </div>
  </div>
)} */}
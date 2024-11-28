import Wrapper from '../../Wrapper/Index'
import { useEffect, useState } from "react";
import { useLocation } from 'react-router-dom';
import { ChampSaisie } from '../CreationCompte/ChampSaisie';
import './monCompteParrain.css';
import Container from 'react-bootstrap/esm/Container';
import send from '../../media/images/logos/send_blanc.png';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../../AuthContext';



const otherRegex = /^[a-zA-ZÀ-ÿ\- ]{1,}$/; // minimum 2 caractères pour les autres champs
// const nomRegex = /^[A-ZÀ-ÿ\- ]{2,}$/; // NOM en MAJUSCULES
// const codeRegex = /^[a-zA-ZÀ-ÿ\- ]{1}\d{3}$/; // code admis :  1 lettre suivie de 3 chiffres

const MonCompteParrain = () => {

  // Initialisation des états des valeurs de utilisateurDto
  // const [utilisateurDto, setUtilisateurDto] = useState({
  //   idUtilisateur: '',
  //   presentationParcours: '',
  //   branchesReseau: '',
  //   domainesExpertise: '',
  //   secteurGeographique: '',
  //   disponibilites: '',
  // });

  const [parrainDto, setParrainDto] = useState({
    idUtilisateur: '',
    presentationParcours: '',
    branchesReseau: '',
    domainesExpertise: '',
    secteurGeographique: '',
    disponibilites: '',
  });

  const [isSubmitted, setIsSubmitted] = useState(false);
  const location = useLocation(); // Ce hook permet d’accéder à l’objet location qui représente l’URL actuelle de l’application 
  const navigate = useNavigate();

  const idUtilisateur = localStorage.getItem('idUtilisateur');
  console.log('recupération de idUtilisateur:', idUtilisateur);

  // Réinitialisation des états des valeurs de parrainDto
  // lorsque le composant est monté (c’est-à-dire lorsque la page est chargée ou actualisée).
  useEffect(() => {
    setParrainDto({
      idUtilisateur: idUtilisateur,
      presentationParcours: '',
      branchesReseau: '',
      domainesExpertise: '',
      secteurGeographique: '',
      disponibilites: '',
    });

    setIsSubmitted(false);
  }, [location]); // A chaque fois que l’URL change (info connue grâce à l'objet location), le useEffect est déclenché pour réinitialiser la page.

  const [errors, setErrors] = useState({});

  const validate = () => {
    const newErrors = {};

    if (!parrainDto.presentationParcours || parrainDto.presentationParcours.trim() === '') newErrors.presentationParcours = 'La présentation du parcours est requis';
    if (!parrainDto.branchesReseau || parrainDto.branchesReseau.trim() === '') newErrors.branchesReseau = 'Le type de réseau est requis';
    if (!parrainDto.domainesExpertise || parrainDto.domainesExpertise.trim() === '') newErrors.domainesExpertise = 'Le domaine d\'expertise est requis';
    if (!parrainDto.secteurGeographique || parrainDto.secteurGeographique.trim() === '') newErrors.secteurGeographique = 'Le secteur géographique est requis';
    if (!parrainDto.disponibilites || parrainDto.disponibilites.trim() === '') newErrors.disponibilites = 'Les disponibilités sont requises';

    return newErrors;
  };


  // Met à jour dynamiquement les propriétés de parrainDto à chaque changer de valeur
  const handleChange = (name, value) => {
    setParrainDto({
      ...parrainDto,
      // L’opérateur de décomposition (...parrainDto) est utilisé pour copier toutes les propriétés existantes de parrainDto.
      [name]: value
    });
    console.log("Valeurs mises à jour:", { ...parrainDto, [name]: value }); // Log des valeurs mises à jour

    // Validation des champs
    const newErrors = { ...errors };
    if (value.trim() === '') {
      newErrors[name] = 'Ce champ est requis';
    } else {
      delete newErrors[name];
    }
    setErrors(newErrors);
  };


  // Envoie des données de l’utilisateur au serveur, reception de la réponse contenant l’ID de l’utilisateur créé, 
  // et mise à jour l’état local avec cet ID. 
  // Cela permet de garder l’interface utilisateur synchronisée avec les données du serveur.
  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Formulaire soumis"); // Formulaire soumis

    const validationErrors = validate();
    if (Object.keys(validationErrors).length > 0) {
      console.log("Erreurs de validation:", validationErrors); // Tous les champs sont-ils remplis ?
      setErrors(validationErrors);
      return;
    }

    console.log("Données envoyées:", parrainDto);
    console.log("Données envoyées (JSON.stringify):", JSON.stringify(parrainDto));
    fetch('http://localhost:8080/creationCompte/completercompteparrain', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(parrainDto)
    })
    .then(response => {
      console.log("Réponse du serveur:", response);  // reponse du serveur après la requête
      if (!response.ok) {
        return response.json().then(err => { throw new Error(err.message || 'Erreur inconnue'); });
      }
      return response.json();
    })
    .then(data => {
      console.log("Données reçues du serveur:", data);
      if (data && data.idUtilisateur) {
        console.log('Utilisateur trouvé, id:', data.idUtilisateur);
        console.log('presentationParcours:', data.presentationParcours);

        // Mettez à jour l'état ici après avoir reçu la réponse
        setParrainDto({
          idUtilisateur: data.idUtilisateur, // Assurez-vous que l'ID est récupéré correctement
          presentationParcours: data.presentationParcours || '',
          branchesReseau: data.branchesReseau || '',
          domainesExpertise: data.domainesExpertise || '',
          secteurGeographique: data.secteurGeographique || '',
          disponibilites: data.disponibilites || '',
          type: data.type || '', // Ajoutez le type si nécessaire
        });

        // Vérifiez si le type de l'utilisateur est "parrain" avant de naviguer
        navigate('/filtres'); // Rediriger vers la page : filtres -> "Secteurs/Réseaux et Type d'accompagnement"
      } else {
        console.log("else data.exists")
        // setUserNotFound(true); // Afficher le message "Utilisateur inconnu"
      }
    })
    .catch(error => {
      console.error('Erreur lors de la soumission du formulaire!', error);
    });
  }

  return (
    <Wrapper>
      <div className='titre'>
        <h1 className='text'>compte Parrain</h1>
      </div>
      <form onSubmit={handleSubmit} className='col-6 mx-auto my-3'>

        {errors.presentationParcours && <div className="error">{errors.presentationParcours}</div>}
        <ChampSaisie
          setValue={(value) => handleChange('presentationParcours', value)}
          label="Rapide présentation du parcours :"
          name="presentationParcours"
          value={parrainDto.presentationParcours}
          regex={otherRegex}
        />

        {errors.branchesReseau && <div className="error">{errors.branchesReseau}</div>}
        <ChampSaisie setValue={(value) => handleChange('branchesReseau', value)} label="Branches sur lesquelles il a un bon réseau :" name="branchesReseau" value={parrainDto.branchesReseau} regex={otherRegex} ></ChampSaisie>

        {errors.domainesExpertise && <div className="error">{errors.domainesExpertise}</div>}
        <ChampSaisie setValue={(value) => handleChange('domainesExpertise', value)} value={parrainDto.domainesExpertise} label="Domaine d’expertise particulier :" name="domainesExpertise" regex={otherRegex}  ></ChampSaisie>

        {errors.secteurGeographique && <div className="error">{errors.secteurGeographique}</div>}
        <ChampSaisie setValue={(value) => handleChange('secteurGeographique', value)} value={parrainDto.secteurGeographique} label="Lieux sur lesquels il souhaite se déplacer :" name="secteurGeographique" regex={otherRegex}  ></ChampSaisie>

        {errors.disponibilites && <div className="error">{errors.disponibilites}</div>}
        <ChampSaisie setValue={(value) => handleChange('disponibilites', value)} label="Disponibilités :" value={parrainDto.disponibilites} name="disponibilites" regex={otherRegex}  ></ChampSaisie>

        {/* {errors.type && <div className="error">{errors.type}</div>} */}


        <Container fluid>
          <div className="position-bouton">
            {!isSubmitted ? (
              <button
                type="submit"
                className="bouton-envoyer"
              >
                Envoyer
                <img src={send} className="img-send" alt="send" />
              </button>
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
                      value={parrainDto.idUtilisateur}
                    />
                  </div>
                </div>
              </div>
            )}
          </div>
        </Container>
      </form>
    </Wrapper>
  )
};

export default MonCompteParrain;
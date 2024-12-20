import Wrapper from '../../Wrapper/index'
import { useEffect, useState, useRef } from "react";
import { ChampSaisie } from '../CreationCompte/champSaisie';
import '../CreationCompte/creationCompte.css';
import './connexion.css';
import Container from 'react-bootstrap/esm/Container';
import { useNavigate } from 'react-router-dom';
import { useLocation } from 'react-router-dom';
import { useAuth } from '../../AuthContext';


const otherRegex = /^[a-zA-ZÀ-ÿ\- ]{1,}$/; // minimum 2 caractères pour les autres champs
const nomRegex = /^[A-ZÀ-ÿ\- ]{2,}$/; // NOM en MAJUSCULES
const codeRegex = /^[a-zA-ZÀ-ÿ\- ]{1}\d{3}$/; // code admis :  1 lettre suivie de 3 chiffres

const Connexion = () => {

    const nomUtilisateurRef = useRef(null);

    const [utilisateurDto, setUtilisateurDto] = useState({
        // idUtilisateur:'',
        nomUtilisateur: '',
        prenomUtilisateur: '',
        codeUtilisateur: '',
        typeUtilisateur: ''
    });

    const [isSubmitted, setIsSubmitted] = useState(false);
    const location = useLocation(); // Ce hook permet d’accéder à l’objet location qui représente l’URL actuelle de l’application 

    const idUtilisateur = localStorage.getItem('idUtilisateur');
    console.log('recupération de idUtilisateur:', idUtilisateur);
    const typeUtilisateur = localStorage.getItem('typeUtilisateur');
    console.log('recupération de typeUtilisateur:', typeUtilisateur);

    // Réinitialisation des états des valeurs de parrainDto
    // lorsque le composant est monté (c’est-à-dire lorsque la page est chargée ou actualisée).
    useEffect(() => {
        setUtilisateurDto({
        // idUtilisateur: idUtilisateur,
        typeUtilisateur: typeUtilisateur
        });
        // setIsSubmitted(false);
    }, [location]); // A chaque fois que l’URL change (info connue grâce à l'objet location), le useEffect est déclenché pour réinitialiser la page.


    const [errors, setErrors] = useState({});
    const [userNotFound, setUserNotFound] = useState(false);
    const navigate = useNavigate();
    const { login } = useAuth();

    const validate = () => {
        const newErrors = {};

        if (!utilisateurDto.nomUtilisateur) newErrors.nomUtilisateur = 'Le nom est requis';
        if (!utilisateurDto.prenomUtilisateur) newErrors.prenomUtilisateur = 'Le prénom est requis';
        if (!utilisateurDto.codeUtilisateur) newErrors.codeUtilisateur = 'Le code d\'accès est requis';

        return newErrors;
    };

    const handleChange = (name, value) => {
        setUtilisateurDto({
            ...utilisateurDto,
            [name]: value
        });

        const newErrors = { ...errors };
        if (value.trim() === '') {
            newErrors[name] = 'Ce champ est requis';
        } else {
            delete newErrors[name];
        }
        setErrors(newErrors);

        // Réinitialiser userNotFound à false lorsque l'utilisateur modifie un champ
        setUserNotFound(false);
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        // Validation des champs
        const validationErrors = validate();
        if (Object.keys(validationErrors).length > 0) {
            setErrors(validationErrors);
            return;
        }

        // Requête pour vérifier l'existence de l'utilisateur
        fetch('http://localhost:8080/creationCompte/checkutilisateur', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(utilisateurDto)
        })
        .then(response => response.json())
        .then(data => {
            if (data.idUtilisateur) {
                console.log('Utilisateur trouvé, id:', data.idUtilisateur);
                console.log('Utilisateur trouvé, type:', data.typeUtilisateur);
                console.log('Utilisateur trouvé, data:', data);

                localStorage.setItem('idUtilisateur', data.idUtilisateur);
                localStorage.setItem('typeUtilisateur', data.typeUtilisateur);

                // Mettez à jour l'état ici après avoir reçu la réponse
                setUtilisateurDto({
                    idUtilisateur: data.idUtilisateur, // Assurez-vous que l'ID est récupéré correctement
                    typeUtilisateur: data.typeUtilisateur
                    // presentationParcours: data.presentationParcours || '',
                    // branchesReseau: data.branchesReseau || '',
                    // domainesExpertise: data.domainesExpertise || '',
                    // secteurGeographique: data.secteurGeographique || '',
                    // disponibilites: data.disponibilites || '',
                    // type: data.type || '', // Ajoutez le type si nécessaire
                });

                // sauvegarderIdUtilisateur();

                login(); // Mettre à jour l'état de connexion
                // Vérifiez si le type de l'utilisateur est "parrain" avant de naviguer
                console.log('Utilisateur trouvé, type:', data.typeUtilisateur);
                if (data.typeUtilisateur === 'parrain') {
                    navigate('/monCompteParrain'); // Rediriger vers la page "monCompteParrain"
                } else {
                    navigate('/monComptePorteur'); // Rediriger vers la page "monComptePorteur"
                }
            } else {
                setUserNotFound(true); // Afficher le message "Utilisateur inconnu"
            }
        })
        .catch(error => {
            console.error('Erreur lors de la soumission du formulaire!', error);
        });

    };

    return (
        <Wrapper>
            <div className='titre'>
                <h1 className='text'>Se connecter</h1>
            </div>
            <form onSubmit={handleSubmit} className='col-6 mx-auto my-3'>


                {errors.prenomUtilisateur && <div className="error">{errors.nomUtilisateur}</div>}
                <ChampSaisie
                    ref={nomUtilisateurRef}
                    setValue={(value) => handleChange('nomUtilisateur', value)}
                    label="Nom :"
                    name="nomUtilisateur"
                    value={utilisateurDto.nomUtilisateur}
                    regex={otherRegex}
                />

                {errors.prenomUtilisateur && <div className="error">{errors.prenomUtilisateur}</div>}
                <ChampSaisie setValue={(value) => handleChange('prenomUtilisateur', value)} label="Prenom :" name="prenomUtilisateur" value={utilisateurDto.prenomUtilisateur} regex={otherRegex} ></ChampSaisie>

                {errors.codeUtilisateur && <div className="error">{errors.codeUtilisateur}</div>}
                <ChampSaisie setValue={(value) => handleChange('codeUtilisateur', value)} label="Code d'accès :" value={utilisateurDto.codeUtilisateur} name="codeUtilisateur" regex={otherRegex}  ></ChampSaisie>

                <Container fluid>
                    <div className="position-bouton d-flex align-items-center justify-content-end">
                        {userNotFound && (
                            <div>
                                <input
                                    className="error-input"
                                    value="Utilisateur inconnu"
                                    disabled
                                />
                            </div>
                        )}
                        <button
                            type="submit"
                            className="bouton-suivant"
                        >
                            Suivant
                        </button>
                    </div>
                </Container>
            </form>
        </Wrapper>
    )
};

export default Connexion;